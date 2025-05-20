package com.formula.formula.exceptions;

import java.text.MessageFormat;
import java.util.function.Supplier;

public class TeamException extends RuntimeException {

    public TeamException(String message) {
        super(message);
    }

    public TeamException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeamException(Throwable cause) {
        super(cause);
    }

    public TeamException(String pattern, Object... args) {
        super(MessageFormat.format(pattern, args));
    }

    public static class NotFoundException extends TeamException {

        public NotFoundException() {
            super("Resource not found");
        }

        public NotFoundException(String message) {
            super(message);
        }

        public NotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

        public NotFoundException(Throwable cause) {
            super(cause);
        }

        public NotFoundException(String pattern, Object... args) {
            super(pattern, args);
        }

        public static Supplier<NotFoundException> supplier(String message, Object... args) {
            return () -> new NotFoundException(message, args);
        }
    }

    public static class ResourseAlreadyExistsException extends TeamException {

        public ResourseAlreadyExistsException(String message) {
            super(message);
        }
    }
}
