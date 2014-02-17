package com.ninetwozero.bf4intel.resources.maps;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WebsiteErrorMessageMap {
    public static final String GATE_REDIRECT = "gate_redirect";
    public static final String NOT_FOUND = "not_found";
    public static final String ALREADY_UPVOTED = "already_upvoted";

    private static final Map<String, Integer> map = new HashMap<String, Integer>() {
        {
            put(GATE_REDIRECT, R.string.msg_error_gate_redirect);
            put(NOT_FOUND, R.string.msg_error_not_found);
            put(ALREADY_UPVOTED, R.string.msg_error_already_upvoted);
        }
    };

    public static int get(final String key) {
        return map.containsKey(key.toLowerCase(Locale.getDefault())) ? map.get(key.toLowerCase()) : R.string.na;
    }
}
