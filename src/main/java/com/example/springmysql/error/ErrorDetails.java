package com.example.springmysql.error;


public class ErrorDetails {
    private String message;
    private String uri;
    //private Data timestamp;

    public String getMessage() {
        return message;
    }

    /*public Data getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Data timestamp) {
        this.timestamp = timestamp;
    }*/

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setMessage(String message) {
        this.message = message;
    }
   

    public ErrorDetails(String message, String uri) {
        this.message = message;
        this.uri = uri;
    }
}
