package com.ninetwozero.bf4intel.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class IntelJsonParser {

    private IntelJsonParser() {
    }

    public static final <T extends Object> T parse(String fileName, Class<T> clazz) throws IOException {
        Reader reader = new InputStreamReader(clazz.getResourceAsStream(fileName));
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject dataJson = parser.parse(reader).getAsJsonObject().getAsJsonObject("data");
        reader.close();
        return gson.fromJson(dataJson, clazz);
    }

    public static final <T extends Object> T parse(String fileName, Class<T> clazz, Gson gson) throws IOException {
        Reader reader = new InputStreamReader(clazz.getResourceAsStream(fileName));
        JsonParser parser = new JsonParser();
        JsonObject dataJson = parser.parse(reader).getAsJsonObject().getAsJsonObject("data");
        reader.close();
        return gson.fromJson(dataJson, clazz);
    }
}
