package com.microservicesjmp.songapp.songservice.validation;

import com.microservicesjmp.songapp.songservice.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
@RequiredArgsConstructor
public class SongIdConstraintValidator implements ConstraintValidator<SongId, Integer> {

    private final SongService songService;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return songService.existsById(id);
    }
}