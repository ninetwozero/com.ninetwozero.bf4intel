package com.ninetwozero.bf4intel.network;

import com.github.kevinsawicki.http.HttpRequest;

import java.net.URL;

public class SimpleGetRequest extends BaseSimpleRequest {
    public SimpleGetRequest(final URL requestUrl) {
        super(requestUrl);
    }

    public SimpleGetRequest(final URL requestUrl, final RequestType requestType) {
        super(requestUrl, requestType);
    }

    protected HttpRequest getHttpRequest() {
        HttpRequest request = HttpRequest.get(requestUrl)
            .readTimeout(READ_TIMEOUT)
            .connectTimeout(CONNECT_TIMEOUT)
            .headers(getHeaders());
        return request;
    }
}