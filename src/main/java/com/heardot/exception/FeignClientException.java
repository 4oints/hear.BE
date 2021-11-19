package com.heardot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;
import java.util.Map;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FeignClientException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int status;
    private final String errorMessage;

    private final Map<String, Collection<String>> headers;

    public FeignClientException(Integer status, String errorMessage, Map<String, Collection<String>> headers) {
        super(errorMessage);
        this.status = status;
        this.errorMessage = errorMessage;
        this.headers = headers;

    }
    /**
     * Http Status Code
     * @return
     */
    public Integer getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * FeignResponse Headers
     * @return
     */
    public Map<String, Collection<String>> getHeaders() {
        return headers;
    }

}
