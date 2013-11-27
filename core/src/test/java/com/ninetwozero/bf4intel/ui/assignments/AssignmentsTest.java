package com.ninetwozero.bf4intel.ui.assignments;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.json.assignments.Assignments;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AssignmentsTest {

    @Test
    public void should_parse_Json() throws IOException {
        Reader reader = new InputStreamReader(getClass().getResourceAsStream("/json/assignments.json"));
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject dataJson = parser.parse(reader).getAsJsonObject().getAsJsonObject("data");
        reader.close();
        Assignments assignments = gson.fromJson(dataJson, Assignments.class);
        assertEquals(200661244, assignments.getPersonaId());
        assertEquals("LittleBoySVK", assignments.getPersonaName());
        assertEquals(51, assignments.getAssignments().size());
    }
}
