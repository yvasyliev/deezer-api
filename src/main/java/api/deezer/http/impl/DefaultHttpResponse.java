package api.deezer.http.impl;

import api.deezer.http.HttpResponse;


public class DefaultHttpResponse implements HttpResponse {

    private int statusCode;
    private String body;

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "DefaultHttpResponse{" +
                "statusCode=" + statusCode +
                ", body='" + body + '\'' +
                '}';
    }
}
