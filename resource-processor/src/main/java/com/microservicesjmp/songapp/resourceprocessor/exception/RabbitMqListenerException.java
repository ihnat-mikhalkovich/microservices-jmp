package com.microservicesjmp.songapp.resourceprocessor.exception;

public class RabbitMqListenerException extends RuntimeException {
    public RabbitMqListenerException() {
    }

    public RabbitMqListenerException(String message) {
        super(message);
    }

    public RabbitMqListenerException(String message, Throwable cause) {
        super(message, cause);
    }

    public RabbitMqListenerException(Throwable cause) {
        super(cause);
    }

    public RabbitMqListenerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
