package com.ninetwozero.bf4intel.json.assignments;

import com.ninetwozero.bf4intel.util.IntelJsonParser;

import java.io.IOException;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AssignmentsTest {

    @Test
    public void should_parse_Json() throws IOException {
        Assignments assignments = IntelJsonParser.parse("/json/assignments.json", Assignments.class);
        assertEquals(200661244, assignments.getPersonaId());
        assertEquals("LittleBoySVK", assignments.getPersonaName());
        assertEquals(51, assignments.getAssignments().size());
    }
}
