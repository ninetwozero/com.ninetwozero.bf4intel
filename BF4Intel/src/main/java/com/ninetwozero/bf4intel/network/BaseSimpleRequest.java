package com.ninetwozero.bf4intel.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.utils.exception.Failure;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseSimpleRequest<T> extends Request<T> {
    protected RequestType requestType;
    protected Gson gson = GsonProvider.getInstance();

    public BaseSimpleRequest(
        final int method, final URL url, final RequestType requestType, final Response.ErrorListener errorListener
    ) {
        super(method, url.toString(), errorListener);
        this.requestType = requestType;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            final String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            checkForJsonInlinedErrors(json);
            return Response.success(doParse(json), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException je) {
            return Response.error(new ParseError(je));
        } catch (Failure ex) {
            return Response.error(new ParseError(ex));
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("X-Requested-With", "XMLHttpRequest");
        if (requestType == RequestType.FROM_NAVIGATION) {
            map.put("X-AjaxNavigation", "1");
        }
        return map;
    }

    private void checkForJsonInlinedErrors(final String jsonString) throws Failure {
        if (jsonString.contains("\"type\":\"error\"")) {
            final JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
            throw new Failure(json.get("message").getAsString());
        }
    }

    public enum RequestType {
        NORMAL,
        FROM_NAVIGATION
    }

    protected abstract T doParse(final String json);
}        