package com.switube.www.swiofficialthird.map.gson;

import java.util.List;

public class PlaceDataEntity {
    private List<Results> results;

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public List<Results> getResults() {
        return results;
    }

    public static class Results {
        private String place_id;

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public String getPlace_id() {
            return place_id;
        }
    }
}
