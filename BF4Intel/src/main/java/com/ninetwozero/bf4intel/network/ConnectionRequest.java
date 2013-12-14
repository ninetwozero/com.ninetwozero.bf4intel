package com.ninetwozero.bf4intel.network;

import com.github.kevinsawicki.http.HttpRequest;
import com.ninetwozero.bf4intel.utils.exception.Failure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class ConnectionRequest {
    private static final int CONNECT_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 15000;
    private final String requestUrl;

    public ConnectionRequest(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public ConnectionRequest(URL requestUrl) {
        this.requestUrl = requestUrl.toString();
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
            throw new Failure("Provided URL been null. Cannot execute ConnectionRequest");
        }
    }

    private HttpRequest getHttpRequest() {
        HttpRequest request = HttpRequest.get(requestUrl)
            .readTimeout(READ_TIMEOUT)
            .connectTimeout(CONNECT_TIMEOUT)
            .header("X-Requested-With", "XMLHttpRequest")
            .header("Cookie", "beaker.session.id=<YOUR COOKIE HERE>");
        return request;
    }

    private String fromStream(InputStream in) throws IOException {
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
}
