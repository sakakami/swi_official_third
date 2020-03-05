package com.switube.www.landmark2018test.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GInfoData {
    @SerializedName("Data")
    private List<Data> data = new ArrayList<>();

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }

    public static class Data {
        private String spid = "";
        private String maid = "";
        private String placeid = "";
        private String mmid = "";
        private String msid = "";
        private String mmspid = "";
        private String mscid = "";
        private String countryid = "";
        private String cityid = "";
        private String place = "";
        private String lat = "";
        private String lng = "";
        private String info = "";
        private String addr = "";
        private String phone = "";
        private String email = "";
        private String website = "";
        private String rating = "";
        private String urating = "";
        private String coll = "";
        private List<String> mon = new ArrayList<>();
        private List<String> tue = new ArrayList<>();
        private List<String> wed = new ArrayList<>();
        private List<String> thu = new ArrayList<>();
        private List<String> fri = new ArrayList<>();
        private List<String> sat = new ArrayList<>();
        private List<String> sun = new ArrayList<>();
        private List<String> photo = new ArrayList<>();
        private List<String> label = new ArrayList<>();
        private List<Article> article = new ArrayList<>();
        private List<Term> term = new ArrayList<>();
        @SerializedName("TubeWeb")
        private List<TubeWeb> tubeWeb;

        public void setSpid(String spid) {
            this.spid = spid;
        }

        public void setMaid(String maid) {
            this.maid = maid;
        }

        public void setMsid(String msid) {
            this.msid = msid;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public void setCountryid(String countryid) {
            this.countryid = countryid;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public void setMscid(String mscid) {
            this.mscid = mscid;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public void setMmspid(String mmspid) {
            this.mmspid = mmspid;
        }

        public void setMmid(String mmid) {
            this.mmid = mmid;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public void setPlaceid(String placeid) {
            this.placeid = placeid;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setSat(List<String> sat) {
            this.sat = sat;
        }

        public void setSun(List<String> sun) {
            this.sun = sun;
        }

        public void setMon(List<String> mon) {
            this.mon = mon;
        }

        public void setTue(List<String> tue) {
            this.tue = tue;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setWed(List<String> wed) {
            this.wed = wed;
        }

        public void setThu(List<String> thu) {
            this.thu = thu;
        }

        public void setFri(List<String> fri) {
            this.fri = fri;
        }

        public void setPhoto(List<String> photo) {
            this.photo = photo;
        }

        public void setLabel(List<String> label) {
            this.label = label;
        }

        public void setArticle(List<Article> article) {
            this.article = article;
        }

        public void setTerm(List<Term> term) {
            this.term = term;
        }

        public void setTubeWeb(List<TubeWeb> tubeWeb) {
            this.tubeWeb = tubeWeb;
        }

        public void setUrating(String urating) {
            this.urating = urating;
        }

        public void setColl(String coll) {
            this.coll = coll;
        }

        public String getSpid() {
            return spid;
        }

        public String getMaid() {
            return maid;
        }

        public String getMsid() {
            return msid;
        }

        public String getPlace() {
            return place;
        }

        public String getCityid() {
            return cityid;
        }

        public String getLat() {
            return lat;
        }

        public String getLng() {
            return lng;
        }

        public String getCountryid() {
            return countryid;
        }

        public String getInfo() {
            return info;
        }

        public String getMscid() {
            return mscid;
        }

        public String getMmid() {
            return mmid;
        }

        public String getRating() {
            return rating;
        }

        public String getMmspid() {
            return mmspid;
        }

        public String getAddr() {
            return addr;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getPlaceid() {
            return placeid;
        }

        public List<String> getMon() {
            return mon;
        }

        public List<String> getTue() {
            return tue;
        }

        public List<String> getSun() {
            return sun;
        }

        public List<String> getSat() {
            return sat;
        }

        public String getWebsite() {
            return website;
        }

        public List<String> getThu() {
            return thu;
        }

        public List<String> getWed() {
            return wed;
        }

        public List<String> getFri() {
            return fri;
        }

        public List<String> getPhoto() {
            return photo;
        }

        public List<String> getLabel() {
            return label;
        }

        public List<Article> getArticle() {
            return article;
        }

        public List<Term> getTerm() {
            return term;
        }

        public List<TubeWeb> getTubeWeb() {
            return tubeWeb;
        }

        public String getUrating() {
            return urating;
        }

        public String getColl() {
            return coll;
        }
    }

    public static class Article {
        private String artid = "";
        private String maname = "";
        private String maimg = "";
        private String maid = "";
        private String wsid = "";
        private String time_txt = "";
        private String content = "";
        private String count = "";
        private String privacy = "";
        private String like = "";
        private String coding = "";
        private String spid = "";
        private List<String> img = new ArrayList<>();
        private List<String> tag = new ArrayList<>();
        private List<String> title = new ArrayList<>();
        private List<Message> msg = new ArrayList<>();
        private List<TagId> tagid = new ArrayList<>();

        public void setMaimg(String maimg) {
            this.maimg = maimg;
        }

        public void setManame(String maname) {
            this.maname = maname;
        }

        public void setImg(List<String> img) {
            this.img = img;
        }

        public void setArtid(String artid) {
            this.artid = artid;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setTag(List<String> tag) {
            this.tag = tag;
        }

        public void setMsg(List<Message> msg) {
            this.msg = msg;
        }

        public void setTime_txt(String time_txt) {
            this.time_txt = time_txt;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public void setPrivacy(String privacy) {
            this.privacy = privacy;
        }

        public void setLike(String like) {
            this.like = like;
        }

        public void setMaid(String maid) {
            this.maid = maid;
        }

        public void setWsid(String wsid) {
            this.wsid = wsid;
        }

        public void setCoding(String coding) {
            this.coding = coding;
        }

        public void setTitle(List<String> title) {
            this.title = title;
        }

        public void setTagid(List<TagId> tagid) {
            this.tagid = tagid;
        }

        public void setSpid(String spid) {
            this.spid = spid;
        }

        public String getMaimg() {
            return maimg;
        }

        public String getManame() {
            return maname;
        }

        public List<Message> getMsg() {
            return msg;
        }

        public List<String> getImg() {
            return img;
        }

        public List<String> getTag() {
            return tag;
        }

        public String getArtid() {
            return artid;
        }

        public String getContent() {
            return content;
        }

        public String getTime_txt() {
            return time_txt;
        }

        public String getCount() {
            return count;
        }

        public String getPrivacy() {
            return privacy;
        }

        public String getLike() {
            return like;
        }

        public String getMaid() {
            return maid;
        }

        public String getWsid() {
            return wsid;
        }

        public String getCoding() {
            return coding;
        }

        public List<String> getTitle() {
            return title;
        }

        public List<TagId> getTagid() {
            return tagid;
        }

        public String getSpid() {
            return spid;
        }
    }

    public static class Message {
        private String maname = "";
        private String maimg = "";
        private String time_txt = "";
        private String txt = "";
        private String like = "";
        private String count = "";
        private String acid = "";
        private String maid = "";
        private List<Reply> reply = new ArrayList<>();

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public void setManame(String maname) {
            this.maname = maname;
        }

        public void setMaimg(String maimg) {
            this.maimg = maimg;
        }

        public void setTime_txt(String time_txt) {
            this.time_txt = time_txt;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public void setLike(String like) {
            this.like = like;
        }

        public void setReply(List<Reply> reply) {
            this.reply = reply;
        }

        public void setAcid(String acid) {
            this.acid = acid;
        }

        public void setMaid(String maid) {
            this.maid = maid;
        }

        public String getTxt() {
            return txt;
        }

        public String getManame() {
            return maname;
        }

        public String getMaimg() {
            return maimg;
        }

        public String getTime_txt() {
            return time_txt;
        }

        public String getCount() {
            return count;
        }

        public String getLike() {
            return like;
        }

        public List<Reply> getReply() {
            return reply;
        }

        public String getAcid() {
            return acid;
        }

        public String getMaid() {
            return maid;
        }
    }

    public static class Reply {
        private String arid = "";
        private String maname = "";
        private String maimg = "";
        private String time_txt = "";
        private String txt = "";
        private String like = "";
        private String count = "";
        private String maid = "";

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public void setManame(String maname) {
            this.maname = maname;
        }

        public void setMaimg(String maimg) {
            this.maimg = maimg;
        }

        public void setTime_txt(String time_txt) {
            this.time_txt = time_txt;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public void setLike(String like) {
            this.like = like;
        }

        public void setArid(String arid) {
            this.arid = arid;
        }

        public void setMaid(String maid) {
            this.maid = maid;
        }

        public String getTxt() {
            return txt;
        }

        public String getManame() {
            return maname;
        }

        public String getMaimg() {
            return maimg;
        }

        public String getTime_txt() {
            return time_txt;
        }

        public String getCount() {
            return count;
        }

        public String getLike() {
            return like;
        }

        public String getArid() {
            return arid;
        }

        public String getMaid() {
            return maid;
        }
    }

    public static class Term {
        private String mstid = "";
        private String mtid = "";
        private String miid = "";

        public void setMiid(String miid) {
            this.miid = miid;
        }

        public void setMstid(String mstid) {
            this.mstid = mstid;
        }

        public void setMtid(String mtid) {
            this.mtid = mtid;
        }

        public String getMiid() {
            return miid;
        }

        public String getMstid() {
            return mstid;
        }

        public String getMtid() {
            return mtid;
        }
    }

    public static class TubeWeb {
        private String img = "";
        private String title = "";
        private String url = "";

        public void setImg(String img) {
            this.img = img;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }
    }

    public class TagId {
        private String tqbid = "";
        private String naid = "";

        public void setNaid(String naid) {
            this.naid = naid;
        }
        public String getNaid() {
            return naid;
        }

        public void setTqbid(String tqbid) {
            this.tqbid = tqbid;
        }
        public String getTqbid() {
            return tqbid;
        }
    }
}
