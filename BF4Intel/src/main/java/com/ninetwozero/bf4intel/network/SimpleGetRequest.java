package com.ninetwozero.bf4intel.network;

import com.github.kevinsawicki.http.HttpRequest;

import java.net.URL;

public class SimpleGetRequest extends BaseSimpleRequest {
    @Deprecated
    public SimpleGetRequest(final String requestUrl) {
        super(requestUrl);
    }

    public SimpleGetRequest(final URL requestUrl) {
        super(requestUrl);
    }

    protected HttpRequest getHttpRequest() {
        HttpRequest request = HttpRequest.get(requestUrl)
            .readTimeout(READ_TIMEOUT)
            .connectTimeout(CONNECT_TIMEOUT)
            .header("X-Requested-With", "XMLHttpRequest")
            .header("Cookie", "beaker.session.id=<YOUR COOKIE HERE>");
        return request;
    }
}