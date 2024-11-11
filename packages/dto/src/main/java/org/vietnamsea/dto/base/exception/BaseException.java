package org.vietnamsea.dto.base.exception;
import org.springframework.http.HttpStatus;
import org.vietnamsea.dto.base.constant.ApplicationCode;
import org.vietnamsea.dto.base.response.BaseResponseObject;
import org.vietnamsea.dto.base.response.ResponseBuilder;

import lombok.Setter;

@Setter
public abstract class BaseException extends RuntimeException {
    protected BaseResponseObject<Object> errorMessage;
    public BaseException(Throwable e) {
        super(e);
        errorMessage = ResponseBuilder
        .error(HttpStatus.OK, 
        getLocalizedMessage(), 
        null, 
        ApplicationCode.CREATE_SUCCESS);
    }
    public BaseException(String message) {
        super(message);
    }
}
