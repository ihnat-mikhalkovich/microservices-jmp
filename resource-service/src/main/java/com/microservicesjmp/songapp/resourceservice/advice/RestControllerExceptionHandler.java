package com.microservicesjmp.songapp.resourceservice.advice;

import com.microservicesjmp.songapp.resourceservice.exception.FileDownloadException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.util.List;

@RestControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FileDownloadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleConstraintViolationException(HttpServletRequest request) {
        return ExceptionResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Have an issue with downloading your song.")
                .path(request.getServletPath())
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(
            ConstraintViolationException e,
            HttpServletRequest request
    ) {
        final List<String> messages = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        final HttpStatus httpStatus = e.getConstraintViolations().stream()
                .findFirst().map(violation ->
                        (HttpStatus) violation.getConstraintDescriptor().getAttributes().get("responseStatus")
                ).orElse(HttpStatus.BAD_REQUEST);

        final ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(messages)
                .path(request.getServletPath())
                .build();

        return ResponseEntity.status(httpStatus)
                .body(exceptionResponse);
    }

/*
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleGeneralException(HttpServletRequest request) {
        return ExceptionResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("Internal service error occurred.")
                .path(request.getServletPath())
                .build();
    }
*/
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ExceptionResponse {
        private ZonedDateTime timestamp;
        private String error;
        private int status;
        private Object message;
        private String path;
    }
}
