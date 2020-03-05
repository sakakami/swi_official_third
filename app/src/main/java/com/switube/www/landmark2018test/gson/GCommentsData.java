package com.switube.www.landmark2018test.gson;

import java.util.List;

public class GCommentsData {
    private List<GInfoData.Message> MsgData;

    public void setMsgData(List<GInfoData.Message> msgData) {
        MsgData = msgData;
    }

    public List<GInfoData.Message> getMsgData() {
        return MsgData;
    }

    /*public static class msgData {
        private String maname;
        private String maimg;
        private String time_txt;
        private String txt;
        private List<Reply> reply;

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public void setTime_txt(String time_txt) {
            this.time_txt = time_txt;
        }

        public void setManame(String maname) {
            this.maname = maname;
        }

        public void setMaimg(String maimg) {
            this.maimg = maimg;
        }

        public void setReply(List<Reply> reply) {
            this.reply = reply;
        }

        public String getTxt() {
            return txt;
        }

        public String getTime_txt() {
            return time_txt;
        }

        public String getManame() {
            return maname;
        }

        public String getMaimg() {
            return maimg;
        }

        public List<Reply> getReply() {
            return reply;
        }
    }

    public static class Reply {
        private String maname;
        private String maimg;
        private String time_txt;
        private String txt;

        public void setMaimg(String maimg) {
            this.maimg = maimg;
        }

        public void setManame(String maname) {
            this.maname = maname;
        }

        public void setTime_txt(String time_txt) {
            this.time_txt = time_txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public String getMaimg() {
            return maimg;
        }

        public String getManame() {
            return maname;
        }

        public String getTime_txt() {
            return time_txt;
        }

        public String getTxt() {
            return txt;
        }
    }*/
}
