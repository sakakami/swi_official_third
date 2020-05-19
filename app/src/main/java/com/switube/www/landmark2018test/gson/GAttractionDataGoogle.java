package com.switube.www.landmark2018test.gson;

import java.util.List;

public class GAttractionDataGoogle {
    private String error_message = "";
    private Result result;

    public void setResult(Result result) {
        this.result = result;
    }
    public Result getResult() {
        return result;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
    public String getError_message() {
        return error_message;
    }

    public static class Result {
        private String formatted_address;
        private String formatted_phone_number;
        private String name;
        private String place_id;
        private String rating;
        private String website;
        private String url;
        private OpeningHours opening_hours;
        private List<Photos> photos;
        private List<Address> address_components;
        private Geometry geometry;

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_phone_number(String formatted_phone_number) {
            this.formatted_phone_number = formatted_phone_number;
        }

        public String getFormatted_phone_number() {
            return formatted_phone_number;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }
        public String getPlace_id() {
            return place_id;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }
        public String getRating() {
            return rating;
        }

        public void setWebsite(String website) {
            this.website = website;
        }
        public String getWebsite() {
            return website;
        }

        public void setOpening_hours(OpeningHours opening_hours) {
            this.opening_hours = opening_hours;
        }

        public List<Photos> getPhotos() {
            return photos;
        }
        public void setPhotos(List<Photos> photos) {
            this.photos = photos;
        }

        public OpeningHours getOpening_hours() {
            return opening_hours;
        }

        public void setAddress_components(List<Address> address_components) {
            this.address_components = address_components;
        }
        public List<Address> getAddress_components() {
            return address_components;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }
        public Geometry getGeometry() {
            return geometry;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        public String getUrl() {
            return url;
        }
    }

    public static class OpeningHours {
        private String[] weekday_text;
        private List<Period> periods;
        private String open_now;

        public void setWeekday_text(String[] weekday_text) {
            this.weekday_text = weekday_text;
        }

        public void setPeriods(List<Period> periods) {
            this.periods = periods;
        }

        public void setOpen_now(String open_now) {
            this.open_now = open_now;
        }

        public String[] getWeekday_text() {
            return weekday_text;
        }

        public List<Period> getPeriods() {
            return periods;
        }

        public String getOpen_now() {
            return open_now;
        }
    }

    public static class Photos {
        private String photo_reference;

        public void setPhoto_reference(String photo_reference) {
            this.photo_reference = photo_reference;
        }

        public String getPhoto_reference() {
            return photo_reference;
        }
    }

    public static class Address {
        private String long_name;
        private String short_name;
        private String[] types;

        public void setLong_name(String long_name) {
            this.long_name = long_name;
        }

        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }

        public void setTypes(String[] types) {
            this.types = types;
        }

        public String getLong_name() {
            return long_name;
        }

        public String getShort_name() {
            return short_name;
        }

        public String[] getTypes() {
            return types;
        }
    }

    public static class Geometry {
        private Location location;

        public void setLocation(Location location) {
            this.location = location;
        }

        public Location getLocation() {
            return location;
        }
    }

    public static class Location {
        private String lat;
        private String lng;

        public void setLng(String lng) {
            this.lng = lng;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLat() {
            return lat;
        }

        public String getLng() {
            return lng;
        }
    }

    public static class Period {
        private OCTime open;
        private OCTime close;

        public void setClose(OCTime close) {
            this.close = close;
        }

        public void setOpen(OCTime open) {
            this.open = open;
        }

        public OCTime getClose() {
            return close;
        }

        public OCTime getOpen() {
            return open;
        }
    }

    public static class OCTime {
        private int day;
        private String time;

        public void setTime(String time) {
            this.time = time;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getTime() {
            return time;
        }

        public int getDay() {
            return day;
        }
    }
}
