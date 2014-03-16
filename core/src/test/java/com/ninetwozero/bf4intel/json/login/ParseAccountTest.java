package com.ninetwozero.bf4intel.json.login;

import com.ninetwozero.bf4intel.util.IntelJsonParser;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertNotNull;

public class ParseAccountTest {

    @Test
    public void should_parse_Json() throws IOException {
        final SoldierListingRequest request = IntelJsonParser.parse("/json/soldier_listing.json", SoldierListingRequest.class);

        assertNotNull(request.getGameExpansions());
        assertNotNull(request.getGames());
        assertNotNull(request.getSoldiers());

        assertNotNull(request.getSoldiers().get(0));
        assertNotNull(request.getSoldiers().get(0).getPersonaName());
        assertNotNull(request.getSoldiers().get(0).getPersonaId());
    }

}
