package com.volumeTest.volume.common.exception;

public class VolumeTestException extends RuntimeException {

        private final ExceptionStatus exceptionStatus;

        public VolumeTestException(ExceptionStatus exceptionStatus) {
            this.exceptionStatus = exceptionStatus;
        }

        public ExceptionStatus getExceptionCode() {
            return exceptionStatus;
        }

        @Override
        public String getMessage() {
            return exceptionStatus.getMessage();
        }

        public int getStatus() {
            return exceptionStatus.getStatus();
        }
}
