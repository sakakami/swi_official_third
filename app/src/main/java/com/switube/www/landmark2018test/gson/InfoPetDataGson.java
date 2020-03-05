package com.switube.www.landmark2018test.gson;

import java.util.List;

public class InfoPetDataGson {
    private List<Pet> PetData;

    public List<Pet> getPetData() {
        return PetData;
    }

    public void setPetData(List<Pet> petData) {
        PetData = petData;
    }

    public static class Pet {
        private String petid;
        private String spid;
        private String maid;
        private String maname;
        private String maimg;
        private String pet_name;
        private String pact_img;
        private String pact_txt;
        private String sex;
        private String kind;
        private String age;
        private String breed;
        private String size;
        private String source;
        private String need;
        private String fee;
        private String info_txt;
        private List<Img> img_data;
        private List<Item> item_data;

        public void setPetid(String petid) {
            this.petid = petid;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public void setMaid(String maid) {
            this.maid = maid;
        }

        public void setMaimg(String maimg) {
            this.maimg = maimg;
        }

        public void setBreed(String breed) {
            this.breed = breed;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public void setManame(String maname) {
            this.maname = maname;
        }

        public void setPact_img(String pact_img) {
            this.pact_img = pact_img;
        }

        public void setPact_txt(String pact_txt) {
            this.pact_txt = pact_txt;
        }

        public void setPet_name(String pet_name) {
            this.pet_name = pet_name;
        }

        public void setImg_data(List<Img> img_data) {
            this.img_data = img_data;
        }

        public void setInfo_txt(String info_txt) {
            this.info_txt = info_txt;
        }

        public void setItem_data(List<Item> item_data) {
            this.item_data = item_data;
        }

        public void setNeed(String need) {
            this.need = need;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setSpid(String spid) {
            this.spid = spid;
        }

        public String getPetid() {
            return petid;
        }

        public String getAge() {
            return age;
        }

        public String getBreed() {
            return breed;
        }

        public String getFee() {
            return fee;
        }

        public String getKind() {
            return kind;
        }

        public String getMaid() {
            return maid;
        }

        public String getMaimg() {
            return maimg;
        }

        public String getManame() {
            return maname;
        }

        public String getPact_img() {
            return pact_img;
        }

        public String getPact_txt() {
            return pact_txt;
        }

        public String getNeed() {
            return need;
        }

        public String getPet_name() {
            return pet_name;
        }

        public String getSex() {
            return sex;
        }

        public String getInfo_txt() {
            return info_txt;
        }

        public String getSize() {
            return size;
        }

        public List<Img> getImg_data() {
            return img_data;
        }

        public List<Item> getItem_data() {
            return item_data;
        }

        public String getSpid() {
            return spid;
        }

        public String getSource() {
            return source;
        }
    }

    public static class Img {
        private String petid;
        private String img;

        public void setImg(String img) {
            this.img = img;
        }

        public void setPetid(String petid) {
            this.petid = petid;
        }

        public String getImg() {
            return img;
        }

        public String getPetid() {
            return petid;
        }
    }

    public static class Item {
        private String ttxt;
        private List<String> dtxt_data;

        public void setDtxt_data(List<String> dtxt_data) {
            this.dtxt_data = dtxt_data;
        }

        public void setTtxt(String ttxt) {
            this.ttxt = ttxt;
        }

        public List<String> getDtxt_data() {
            return dtxt_data;
        }

        public String getTtxt() {
            return ttxt;
        }
    }
}
