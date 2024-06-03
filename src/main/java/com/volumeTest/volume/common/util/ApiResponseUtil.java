package com.volumeTest.volume.common.util;

/**
 * ApiResponseUtil
 * Api 응답을 생성하는 유틸리티 클래스
 */
public class ApiResponseUtil {

    /**
     * 성공 응답 생성
     * @param response 응답 데이터
     * @param <T> 응답 데이터 타입
     * @return ApiResult
     */
    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(true, response);
    }

    /**
     * 실패 응답 생성
     * @param throwable 실패 이유
     * @return ApiResult
     */
    public static ApiResult<?> error(Throwable throwable) {
        return new ApiResult<>(false, new ApiError(throwable));
    }

    /**
     * 실패 응답 생성
     * @param message 실패 이유
     * @return ApiResult
     */
    public static ApiResult<?> error(String message) {
        return new ApiResult<>(false, new ApiError(message));
    }

    /**
     * 실패 응답 class
     */
    public static class ApiError {
        private final String message;

        /**
         * 실패 응답 생성
         * @param throwable 실패 이유
         */
        ApiError(Throwable throwable) {
            this(throwable.getMessage());
        }

        /**
         * 실패 응답 생성
         * @param message 실패 이유
         */
        ApiError(String message) {
            this.message = message;
        }

        /**
         * 실패 이유 반환
         * @return 실패 이유
         */
        public String getMessage() {
            return message;
        }
    }

    /**
     * Api 응답 class
     */
    public static class ApiResult<T> {
        private final boolean success;
        private final T response;

        /**
         * Api 응답 생성
         * @param success 성공 여부
         * @param response 응답 데이터
         */
        private ApiResult(boolean success, T response) {
            this.success = success;
            this.response = response;
        }

        /**
         * 성공 여부 반환
         * @return 성공 여부
         */
        public boolean isSuccess() {
            return success;
        }

        /**
         * 응답 데이터 반환
         * @return 응답 데이터
         */
        public T getResponse() {
            return response;
        }

    }

}
