package com.ninetwozero.bf4intel.network;

import android.os.Bundle;

import com.github.kevinsawicki.http.HttpRequest;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SimplePostRequest extends BaseSimpleRequest {
    private Bundle postData;

    public SimplePostRequest(final String requestUrl, final Bundle postData) {
        super(requestUrl);
        this.postData = postData;
    }

    public SimplePostRequest(final URL requestUrl, final Bundle postData) {
        super(requestUrl);
        this.postData = postData;
    }

    public SimplePostRequest(final URL requestUrl, final RequestType requestType, final Bundle postData) {
        super(requestUrl, requestType);
        this.postData = postData;
    }

    protected HttpRequest getHttpRequest() {
        HttpRequest request = HttpRequest.post(requestUrl)
            .readTimeout(READ_TIMEOUT)
            .connectTimeout(CONNECT_TIMEOUT)
            .headers(getHeaders())
            .form(fetchBundleAsMap(postData));
        return request;
    }

    private Map<String, String> fetchBundleAsMap(final Bundle postData) {
        final Map<String, String> map = new HashMap<String, String>();
        for (String key : postData.keySet()) {
            map.put(key, String.valueOf(postData.get(key)));
        }
        return map;
    }
}
