package org.vietnamsea.dto.base.response;

import java.util.List;
import org.springframework.http.HttpStatus;

public class ResponseObject<T> {
    private HttpStatus status;
    private String message;
    private boolean success;
    private String code;
    private List<String> errors;
    private T data;
    private Integer page;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;

    private ResponseObject() {}

    public static <T> ResponseObject<T> ok() {
        ResponseObject<T> builder = new ResponseObject<>();
        builder.status = HttpStatus.OK;
        builder.message = "Success";
        return builder;
    }

    public static <T> ResponseObject<T> ok(T data) {
        return ResponseObject.<T>ok().data(data);
    }

    public static <T> ResponseObject<T> ok(T data, int page, int size, long totalElements, int totalPages) {
        return ok(data).page(page).size(size).totalElements(totalElements).totalPages(totalPages);
    }

    public ResponseObject<T> errors(List<String> errors) {
        this.errors = errors;
        return this;
    }

    public ResponseObject<T> code(String code) {
        this.code = code;
        return this;
    }

    public ResponseObject<T> success(boolean success) {
        this.success = success;
        return this;
    }

    public ResponseObject<T> message(String message) {
        this.message = message;
        return this;
    }

    public ResponseObject<T> data(T data) {
        this.data = data;
        return this;
    }

    public ResponseObject<T> page(int page) {
        this.page = page;
        return this;
    }

    public ResponseObject<T> size(int size) {
        this.size = size;
        return this;
    }

    public ResponseObject<T> totalElements(long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public ResponseObject<T> totalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public Object build() {
        if (page != null && size != null && totalElements != null && totalPages != null) {
            return new PaginationResponse.Builder<T>()
                    .page(page)
                    .size(size)
                    .totalElements(totalElements)
                    .totalPages(totalPages)
                    .status(status)
                    .message(message)
                    .data(data)
                    .code(code)
                    .success(success)
                    .errors(errors)
                    .build();
        } else {
            return new BaseResponse.Builder<T>()
                    .status(status)
                    .message(message)
                    .data(data)
                    .code(code)
                    .success(success)
                    .errors(errors)
                    .build();
        }
    }
}
