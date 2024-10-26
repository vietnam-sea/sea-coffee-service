package org.vietnamsea.dto.base.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse<T> extends BaseResponse<T> {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private PaginationResponse(Builder<T> builder) {
        super(builder);
        this.page = builder.page;
        this.size = builder.size;
        this.totalElements = builder.totalElements;
        this.totalPages = builder.totalPages;
    }
    public static class Builder<T> extends BaseResponse.Builder<T> {
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;

        public Builder<T> page(int page) {
            this.page = page;
            return this;
        }

        public Builder<T> size(int size) {
            this.size = size;
            return this;
        }

        public Builder<T> totalElements(long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public Builder<T> totalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        @Override
        public PaginationResponse<T> build() {
            return new PaginationResponse<>(this);
        }
    }
}
