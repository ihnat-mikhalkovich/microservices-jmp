package com.microservicesjmp.songapp.resourceservice.service.storage;

import com.microservicesjmp.songapp.resourceservice.entity.BinaryResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {

    boolean isResourceExist(int id);

    void save(int id, MultipartFile resource);

    void save(BinaryResource resource);

    BinaryResource getById(int id);

    void delete(List<Integer> ids);

    void delete(int id);
}
