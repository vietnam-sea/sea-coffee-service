package org.vietnamsea.dto.base.response;

import lombok.Builder;

@Builder
public class ApplicationPagination {
    int page;
    int size;
    long totalPage;
    long totalElement;
    boolean firstPage;
    boolean lastPage;
}
