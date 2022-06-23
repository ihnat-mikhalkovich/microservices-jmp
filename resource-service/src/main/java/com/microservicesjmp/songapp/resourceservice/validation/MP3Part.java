package com.microservicesjmp.songapp.resourceservice.validation;

import org.springframework.http.HttpStatus;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MP3PartConstraintValidator.class)
public @interface MP3Part {
    String message() default "The file is not audio/mpeg.";
    HttpStatus responseStatus() default HttpStatus.BAD_REQUEST;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
