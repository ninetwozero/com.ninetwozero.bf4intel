package com.ninetwozero.bf4intel.resources.maps;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class WebsiteErrorMessageMap {
    private static final Map<String, Integer> map = new HashMap<String, Integer>() {
        {
            put("gate_redirect", R.string.msg_error_gate_redirect);
        }
    };

    public static int get(final String key) {
        return map.containsKey(key) ? map.get(key) : R.string.na;
    }
}
