package com.ninetwozero.bf4intel.resources.maps;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class WebsiteErrorMessageMap {
    private static final Map<String, Integer> map = new HashMap<String, Integer>() {
        {
            put("gate_redirect", R.string.msg_error_gate_redirect);
            put("not_found", R.string.msg_error_not_found);
        }
    };

    public static int get(final String key) {
        return map.containsKey(key.toLowerCase()) ? map.get(key.toLowerCase()) : R.string.na;
    }
}
