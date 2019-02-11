package com.ifood.test.playlistbyweather.exception;


import org.springframework.http.HttpStatus;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String path;
    private HttpStatus status;


    public ExceptionResponse(Date timestamp, String message, String path, HttpStatus status) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
        this.status = status;
    }
}
