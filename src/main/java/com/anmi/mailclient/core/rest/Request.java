package com.anmi.mailclient.core.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public class Request<R> {
    private String uri;
    private Object body;
    private Class<R> responseClass;
    private HttpHeaders headers;
    private HttpMethod method;
    private boolean anonymous;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Class<R> getResponseClass() {
        return responseClass;
    }

    public void setResponseClass(Class<R> responseClass) {
        this.responseClass = responseClass;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public static class Builder<R> {
        private String uri = "";
        private Object body;
        private Class<R> responseClass;
        private HttpHeaders headers;
        private HttpMethod method;
        private boolean anonymous;

        public Builder(HttpMethod method) {
            this.method = method;
        }

        public Builder(HttpMethod method, String uri) {
            this(method);
            this.uri = uri;
        }

        public Builder<R> body(Object body) {
            this.body = body;
            return this;
        }

        public Builder<R> responseClass(Class<R> responseClass) {
            this.responseClass = responseClass;
            return this;
        }

        public Builder<R> headers(HttpHeaders headers) {
            this.headers = headers;
            return this;
        }

        public Builder<R> uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder<R> anonymous() {
            this.anonymous = true;
            return this;
        }

        public final Request<R> build() {
            Request<R> request = new Request<>();
            request.setMethod(method);
            request.setUri(uri);
            request.setBody(body);
            request.setResponseClass(responseClass);
            request.setHeaders(headers == null ? new HttpHeaders() : headers);
            request.setAnonymous(anonymous);
            return request;
        }


    }
}
