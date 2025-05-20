package com.formula.formula.exceptions;

import java.text.MessageFormat;
import java.util.function.Supplier;

public class PilotException extends RuntimeException {

    public PilotException(String message) {
        super(message);
    }

    public PilotException(String message, Throwable cause) {
        super(message, cause);
    }

    public PilotException(Throwable cause) {
        super(cause);
    }

    public PilotException(String pattern, Object... args) {
        super(MessageFormat.format(pattern, args));
    }

    public static class NotFoundException extends PilotException {

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

    public static class ResourseAlreadyExistsException extends PilotException {

        public ResourseAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class InvalidStateException extends PilotException {

        public InvalidStateException(String message) {
            super(message);
        }
    }

    public static class InvalidStatusException extends PilotException {

        public InvalidStatusException(String message) {
            super(message);
        }
    }
}
