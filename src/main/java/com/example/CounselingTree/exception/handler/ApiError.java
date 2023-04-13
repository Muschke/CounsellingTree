package com.example.CounselingTree.exception.handler;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
public class ApiError {
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;

    public ApiError(String message, HttpStatus status, LocalDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
