package com.ninetwozero.bf4intel.network;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class SimplePostRequest<T> extends BaseSimpleRequest<T> {
    private Bundle postData;

    public SimplePostRequest(final URL url, final Bundle data, final Response.ErrorListener errorListener) {
        super(Method.POST, url, RequestType.NORMAL, errorListener);
        postData = data;
    }

    public SimplePostRequest(final URL url, final Bundle data, final RequestType type, final Response.ErrorListener errorListener
    ) {
        super(Method.POST, url, type, errorListener);
        postData = data;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        final Map<String, String> map = new HashMap<String, String>();
        for (String key : postData.keySet()) {
            map.put(key, String.valueOf(postData.get(key)));
        }
        return map;
    }
}