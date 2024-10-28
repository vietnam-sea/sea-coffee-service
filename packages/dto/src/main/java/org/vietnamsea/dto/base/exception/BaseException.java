package org.vietnamsea.dto.base.exception;

import org.vietnamsea.dto.base.response.BaseResponse;

import lombok.Setter;

@Setter
public abstract class BaseException extends RuntimeException {
    protected BaseResponse<String> message;
}
