package com.hit.server;

import java.util.Map;
/* Each instance of the class is a memory unit,
with an ID in the header and information in the body*/
public class Request <T>{
    private java.util.Map<java.lang.String,java.lang.String> headers;
    private T body;
    public Request(java.util.Map<java.lang.String,java.lang.String> headers, T body){
        this.headers=headers;
        this.body=body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Request{" +
                "headers=" + headers +
                ", body=" + body +
                '}';
    }
}
