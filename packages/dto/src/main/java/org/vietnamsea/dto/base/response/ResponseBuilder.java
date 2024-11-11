package org.vietnamsea.dto.base.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.vietnamsea.dto.base.constant.ApplicationCode;

public class ResponseBuilder<T> {
    private HttpStatus status;
    private String message;
    private List<String> errors;
    private boolean success;
    private ApplicationCode code;
    private T content;
    private ApplicationPagination pagination;

    private ResponseBuilder() {}

    public static <T> ResponseBuilder<T> builder() {
        return new ResponseBuilder<>();
    }

    public ResponseBuilder<T> status(HttpStatus status) {
        this.status = status;
        return this;
    }

    public ResponseBuilder<T> message(String message) {
        this.message = message;
        return this;
    }

    public ResponseBuilder<T> errors(List<String> errors) {
        this.errors = errors;
        return this;
    }

    public ResponseBuilder<T> success(boolean success) {
        this.success = success;
        return this;
    }

    public ResponseBuilder<T> code(ApplicationCode code) {
        this.code = code;
        return this;
    }

    public ResponseBuilder<T> content(T content) {
        this.content = content;
        return this;
    }

    public ResponseBuilder<T> pagination(ApplicationPagination pagination) {
        this.pagination = pagination;
        return this;
    }
    public static <T> BaseResponseObject<T> success(T content) {
        return ResponseBuilder.<T>builder()
            .status(HttpStatus.OK)
            .message("Request processed successfully")
            .success(true)
            .code(ApplicationCode.CREATE_SUCCESS)
            .content(content)
            .pagination(null)
            .build();
    }
    public static <T> BaseResponseObject<T> successWithPagination(T content, ApplicationPagination pagination) {
        return ResponseBuilder.<T>builder()
            .status(HttpStatus.OK)
            .message("Request processed successfully")
            .success(true)
            .code(ApplicationCode.CREATE_SUCCESS)
            .content(content)
            .pagination(pagination)
            .build();
    }
    public static <T> BaseResponseObject<T> error(HttpStatus status, String message, List<String> errors, ApplicationCode code) {
        return ResponseBuilder.<T>builder()
            .status(status)
            .message(message)
            .errors(errors)
            .success(false)
            .code(code)
            .content(null)
            .pagination(null)
            .build();
    }
    public BaseResponseObject<T> build() {
        BaseResponseObject<T> response = new BaseResponseObject<>();
        response.status(this.status);
        response.message(this.message);
        response.errors(this.errors);
        response.success(this.success);
        response.code(this.code);
        response.content(this.content);
        response.pagination(this.pagination);
        return response;
    }
}
