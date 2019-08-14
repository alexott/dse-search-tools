package com.datastax.alexott.search;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.Base64;
import java.util.List;

public class Heatmap {
    @JsonProperty
    int gridLevel;
    @JsonProperty
    int columns;
    @JsonProperty
    int rows;
    @JsonProperty
    double minX, minY, maxX, maxY;

    @JsonProperty
    String counts_png;
    @JsonProperty("counts_ints2D")
    List<List<Integer>> counts;


    public int getGridLevel() {
        return gridLevel;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    @JsonGetter("counts_png")
    public String getCountsPngRaw() {
        return counts_png;
    }

    public List<List<Integer>> getCounts() {
        return counts;
    }

    @JsonIgnore
    public byte[] getCountsPng() {
        if (counts_png == null) {
            return null;
        }
        return Base64.getDecoder().decode(counts_png);
    }

    @Override
    public String toString() {
        return "Heatmap{" +
                "gridLevel=" + gridLevel +
                ", columns=" + columns +
                ", rows=" + rows +
                ", minX=" + minX +
                ", minY=" + minY +
                ", maxX=" + maxX +
                ", maxY=" + maxY +
                ", counts_png='" + counts_png + '\'' +
                ", counts=" + counts +
                '}';
    }
}
