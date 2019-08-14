package com.datastax.alexott.search;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FacetParsers {
    final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Map<String, Heatmap> parseHeatmap(String json) {
        Map<String, Heatmap> map = new HashMap<String, Heatmap>();
        try {
            Map<String, Object> m = new HashMap<>();
            m = OBJECT_MAPPER.readValue(json, m.getClass());
            for (Map.Entry<String, Object> e: m.entrySet()) {
                Heatmap h = OBJECT_MAPPER.convertValue(e.getValue(), Heatmap.class);
                map.put(e.getKey(), h);
            }
        } catch (IOException e) {
            return null;
        }

        return map;
    }

}
