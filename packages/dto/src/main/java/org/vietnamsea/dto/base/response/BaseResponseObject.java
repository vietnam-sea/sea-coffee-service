package org.vietnamsea.dto.base.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.vietnamsea.dto.base.constant.ApplicationCode;

public class BaseResponseObject<T> {
    HttpStatus status;
    String message;
    List<String> errors;
    boolean success;
    String code;
    T content;
    ApplicationPagination pagination;

    public BaseResponseObject<T> status (HttpStatus status) {
        this.status = status;
        return this;
    }
    public BaseResponseObject<T> message (String message) {
        this.message = message;
        return this;
    }
    public BaseResponseObject<T> errors (List<String> errors) {
        this.errors = errors;
        return this;
    }
    public BaseResponseObject<T> success (boolean success) {
        this.success = success;
        return this;
    }
    public BaseResponseObject<T> code (ApplicationCode code) {
        this.code = code.toString();
        return this;
    }

    public BaseResponseObject<T> content (T data) {
        this.content = data;
        return this;
    }

    public BaseResponseObject<T> pagination (ApplicationPagination pagination) {
        this.pagination = pagination;
        return this;
    }
}
