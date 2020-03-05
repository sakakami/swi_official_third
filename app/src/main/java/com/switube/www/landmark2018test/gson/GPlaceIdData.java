package com.switube.www.landmark2018test.gson;

import java.util.ArrayList;
import java.util.List;

public class GPlaceIdData {
    private List<Results> results;

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public List<Results> getResults() {
        return results;
    }

    public static class Results {
        private String place_id;
        private List<AddressData> address_components = new ArrayList<>();

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }
        public String getPlace_id() {
            return place_id;
        }

        public void setAddress_components(List<AddressData> address_components) {
            this.address_components = address_components;
        }
        public List<AddressData> getAddress_components() {
            return address_components;
        }
    }

    public class AddressData {
        private String long_name = "";
        private String short_name = "";
        private List<String> types = new ArrayList<>();

        public void setLong_name(String long_name) {
            this.long_name = long_name;
        }
        public String getLong_name() {
            return long_name;
        }

        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }
        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }
        public String getShort_name() {
            return short_name;
        }
    }
}
