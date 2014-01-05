package com.ninetwozero.bf4intel.network;

import com.github.kevinsawicki.http.HttpRequest;
import com.ninetwozero.bf4intel.utils.exception.Failure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseSimpleRequest {
    protected static final int CONNECT_TIMEOUT = 5000;
    protected static final int READ_TIMEOUT = 15000;
    protected final String requestUrl;
    protected final RequestType requestType;

    @Deprecated
    public BaseSimpleRequest(final String requestUrl) {
        this.requestUrl = requestUrl;
        requestType = RequestType.NORMAL;
    }

    public BaseSimpleRequest(final URL requestUrl) {
        this.requestUrl = requestUrl.toString();
        this.requestType = RequestType.NORMAL;
    }

    public BaseSimpleRequest(final URL requestUrl, final RequestType requestType) {
        this.requestUrl = requestUrl.toString();
        this.requestType = requestType;
    }

    public String execute() throws Failure {
        if (requestUrl != null) {
            try {
                HttpRequest httpRequest = getHttpRequest();
                return fromStream(httpRequest.buffer());
            } catch (IOException e) {
                throw new Failure(e);
            } catch (HttpRequest.HttpRequestException e) {
                throw new Failure(e);
            }
        } else {
            throw new Failure("Provided URL is null - cannot execute request!");
        }
    }

    private String fromStream(final InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        in.close();
        reader.close();
        return out.toString();
    }

    protected Map<String, String> getHeaders() {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("X-Requested-With", "XMLHttpRequest");
        map.put("Cookie", "beaker.session.id=<YOUR COOKIE HERE>");

        if (requestType == RequestType.FROM_NAVIGATION) {
            map.put("X-AjaxNavigation", "1");
        }

        return map;
    }

    protected abstract HttpRequest getHttpRequest();

    public enum RequestType {
        NORMAL,
        FROM_NAVIGATION
    }
}
