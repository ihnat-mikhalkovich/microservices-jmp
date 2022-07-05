package com.microservicesjmp.songapp.resourceservice.service.storage.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.microservicesjmp.songapp.resourceservice.entity.BinaryResource;
import com.microservicesjmp.songapp.resourceservice.exception.FileDownloadException;
import com.microservicesjmp.songapp.resourceservice.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class BinaryStorageService implements StorageService {

    public static final String FILE_EXTENSION = "mp3";

    private final String fileExtension;
    private final AmazonS3 amazonS3;
    private final String bucketName;

    @Autowired
    public BinaryStorageService(AmazonS3 amazonS3, @Value("${aws.s3.bucket-name}") String bucketName) {
        this(FILE_EXTENSION, amazonS3, bucketName);
    }

    public BinaryStorageService(String fileExtension, AmazonS3 amazonS3, String bucketName) {
        this.fileExtension = fileExtension;
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;

        initializeBucket();
    }

    @Override
    public boolean isResourceExist(int id) {
        return amazonS3.doesObjectExist(bucketName, Integer.toString(id));
    }

    @Override
    public void save(int id, MultipartFile resource) {
        final InputStream inputStream = this.getInputStream(resource);

        final BinaryResource binaryResource = BinaryResource.builder()
                .id(id)
                .name(resource.getOriginalFilename())
                .extension(fileExtension)
                .size(resource.getSize())
                .inputStream(inputStream)
                .build();

        this.save(binaryResource);
    }

    @Override
    public void save(BinaryResource resource) {
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(resource.getSize());

        objectMetadata.getUserMetadata()
                .put(fileExtension, resource.getName());
        amazonS3.putObject(bucketName, Integer.toString(resource.getId()),
                resource.getInputStream(), objectMetadata);
    }

    @Override
    public BinaryResource getById(int id) {
        final S3Object object = amazonS3.getObject(bucketName, Integer.toString(id));

        final String name = object.getObjectMetadata()
                .getUserMetadata()
                .get(fileExtension);

        final long size = object.getObjectMetadata()
                .getContentLength();

        return BinaryResource.builder()
                .id(id)
                .extension(fileExtension)
                .name(name)
                .size(size)
                .inputStream(object.getObjectContent())
                .build();
    }

    @Override
    public void delete(List<Integer> ids) {
        final DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);
        final List<DeleteObjectsRequest.KeyVersion> keys = ids.stream()
                .map(id -> new DeleteObjectsRequest.KeyVersion(Integer.toString(id))).toList();
        deleteObjectsRequest.setKeys(keys);

        amazonS3.deleteObjects(deleteObjectsRequest);
    }

    @Override
    public void delete(int id) {
        amazonS3.deleteObject(bucketName, Integer.toString(id));
    }

    private InputStream getInputStream(MultipartFile resource) {
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            throw new FileDownloadException("Can't get InputStream from Resource", e);
        }
    }

    private void initializeBucket() {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            amazonS3.createBucket(bucketName);
        }
    }
}
