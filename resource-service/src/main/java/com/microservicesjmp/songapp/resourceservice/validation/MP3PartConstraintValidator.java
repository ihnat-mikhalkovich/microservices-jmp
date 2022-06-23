package com.microservicesjmp.songapp.resourceservice.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MP3PartConstraintValidator implements ConstraintValidator<MP3Part, MultipartFile> {

    public static final String MP3_CONTENT_TYPE = "audio/mpeg";

    @Override
    public boolean isValid(MultipartFile resource, ConstraintValidatorContext context) {
        return MP3_CONTENT_TYPE.equals(resource.getContentType());
    }
}
