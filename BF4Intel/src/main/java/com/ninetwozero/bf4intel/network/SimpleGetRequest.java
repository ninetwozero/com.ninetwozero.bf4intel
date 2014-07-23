package com.ninetwozero.bf4intel.network;

import com.android.volley.Response;

import java.net.URL;

public abstract class SimpleGetRequest<T> extends BaseSimpleRequest<T> {
    public SimpleGetRequest(final URL url, final Response.ErrorListener errorListener
    ) {
        super(Method.GET, url, RequestType.NORMAL, errorListener);
    }

    public SimpleGetRequest(final URL url, final RequestType type, final Response.ErrorListener errorListener
    ) {
        super(Method.GET, url, type, errorListener);
    }
}        