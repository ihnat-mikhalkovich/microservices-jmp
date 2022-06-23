package com.microservicesjmp.songapp.resourceservice.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3ClientConfiguration {

    @Value("${aws.s3.endpoint-url}")
    private String endpointUrl;

    @Value("${aws.s3.region}")
    private String region;

    @Bean
    public AmazonS3 amazonS3() {
        final AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(endpointUrl,
                region);
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withPathStyleAccessEnabled(true)
                .build();
    }
}
