package com.microservicesjmp.songapp.resourceservice.validation;

import com.microservicesjmp.songapp.resourceservice.service.storage.impl.BinaryStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
@RequiredArgsConstructor
public class ResourceIdConstraintValidator implements ConstraintValidator<ResourceId, Integer> {

    private final BinaryStorageService storageService;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return storageService.isResourceExist(id);
    }
}

