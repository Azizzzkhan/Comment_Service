package com.example.commentservice.exception;

import com.example.commentservice.entity.User;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CommentException extends IllegalArgumentException {
    public CommentException(String message) {
        super(message);
    }
}
