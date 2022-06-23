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
@Constraint(validatedBy = ResourceIdConstraintValidator.class)
public @interface ResourceId {
    String message() default "Resource doesn't exist with given id.";
    HttpStatus responseStatus() default HttpStatus.NOT_FOUND;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
