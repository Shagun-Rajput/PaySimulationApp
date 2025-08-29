package com.app.dvm.model;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private LocalDateTime timestamp;
    private String message;
    private T data;
    private int code;
    //constructors
    public ApiResponse(String message, T data, int code) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
        this.code = code;
    }
    // Getters and Setters


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
