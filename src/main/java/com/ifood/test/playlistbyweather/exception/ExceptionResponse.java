package com.ifood.test.playlistbyweather.exception;


import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String path;
    private Integer status;


    public ExceptionResponse(Date timestamp, String message, String path, Integer status) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
