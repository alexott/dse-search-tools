package com.datastax.alexott.search;

import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FacetParsersTest extends TestCase {
    public void testHeatmapNormal() throws IOException {
        String normalJson = new String(Files.readAllBytes(Paths.get("src/test/resources/heatmap-normal.json")));
        assertNotNull(normalJson);
        Map<String, Heatmap> m = FacetParsers.parseHeatmap(normalJson);
        assertNotNull(m);
        Heatmap h = m.get("lat_long");
        assertNotNull(h);
        assertEquals(32, h.getRows());
        assertEquals(32, h.getColumns());
    }

    public void testHeatmapPng() throws IOException {
        String normalJson = new String(Files.readAllBytes(Paths.get("src/test/resources/heatmap-png.json")));
        assertNotNull(normalJson);
        Map<String, Heatmap> m = FacetParsers.parseHeatmap(normalJson);
        assertNotNull(m);
        Heatmap h = m.get("lat_long");
        assertNotNull(h);
        assertEquals(32, h.getRows());
        assertEquals(32, h.getColumns());
    }
}