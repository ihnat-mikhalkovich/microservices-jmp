package com.microservicesjmp.songapp.resourceprocessor;

import com.microservicesjmp.songapp.resourceprocessor.entity.SongMetadata;
import com.microservicesjmp.songapp.resourceprocessor.util.Mp3MetadataExtractor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootApplication
public class ResourceProcessorApplication {

	public static void main(String[] args) throws FileNotFoundException {
//		final ConfigurableApplicationContext run =
				SpringApplication.run(ResourceProcessorApplication.class, args);
//
//		final Mp3MetadataExtractor extractor = run.getBean(Mp3MetadataExtractor.class);
//
//		final File file = ResourceUtils.getFile("classpath:songs/thanks.mp3");
//		final FileInputStream inputStream = new FileInputStream(file);
//		final SongMetadata metadata = extractor.extract(inputStream);
//		System.out.println(metadata);
	}

}
