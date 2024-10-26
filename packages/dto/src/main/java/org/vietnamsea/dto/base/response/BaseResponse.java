package org.vietnamsea.dto.base.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse <T> {
    private HttpStatus status;
    private String message;
    private List<String> errors;
    private boolean success;
    private String code;
    private T data;

    protected BaseResponse(Builder<T> builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.data = builder.data;
        this.errors = builder.errors;
        this.success = builder.success;
        this.code = builder.code;
    }

    public static class Builder<T> {
        private HttpStatus status;
        private String message;
        private List<String> errors;
        private boolean success;
        private String code;
        private T data;

        public Builder<T> status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder<T> errors(List<String> errors) {
            this.errors = errors;
            return this;
        }
        public Builder<T> code(String code) {
            this.code = code;
            return this;
        }

        public BaseResponse<T> build() {
            return new BaseResponse<>(this);
        }
    }
}
