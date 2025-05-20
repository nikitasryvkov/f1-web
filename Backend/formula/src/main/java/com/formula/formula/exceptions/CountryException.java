package com.formula.formula.exceptions;

import java.text.MessageFormat;
import java.util.function.Supplier;

public class CountryException extends RuntimeException {

    public CountryException(String message) {
        super(message);
    }

    public CountryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CountryException(Throwable cause) {
        super(cause);
    }

    public CountryException(String pattern, Object... args) {
        super(MessageFormat.format(pattern, args));
    }

    public static class NotFoundException extends CountryException {

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

    public static class ResourseAlreadyExistsException extends CountryException {

        public ResourseAlreadyExistsException(String message) {
            super(message);
        }
    }
}
