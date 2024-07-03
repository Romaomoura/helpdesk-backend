package com.devromaomoura.helpdesk.resources.exceptions;

public class StandardError {
    private Long timesTamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError() {
    }

    public StandardError(Long timesTamp, Integer status, String error, String message, String path) {
        this.timesTamp = timesTamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Long getTimesTamp() {
        return timesTamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

}
