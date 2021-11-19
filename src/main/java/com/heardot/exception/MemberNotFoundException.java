package com.heardot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends BusinessException {
    public MemberNotFoundException(String message) {
        super(message);
    }
}
