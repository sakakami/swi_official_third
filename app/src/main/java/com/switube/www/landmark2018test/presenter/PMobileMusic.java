package com.switube.www.landmark2018test.presenter;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.gson.GMusicRadio;
import com.switube.www.landmark2018test.gson.GPushMusic;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.model.MMobileMusic;
import com.switube.www.landmark2018test.presenter.callback.IPMobileMusic;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IVMobileMusic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PMobileMusic implements IPMobileMusic {
    private IVMobileMusic ivMobileMusic;
    private MMobileMusic mMobileMusic;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd", Locale.TAIWAN);
    public PMobileMusic(IVMobileMusic ivMobileMusic) {
        this.ivMobileMusic = ivMobileMusic;
        mMobileMusic = new MMobileMusic(this);
    }

    public void getMusicRadioData(String taid, boolean isCollect) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("taid", taid);
        mMobileMusic.getMusicRadioData(map, isCollect);
    }

    public void getPushMusicData(String taid, String value, String type) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("type", type);
        map.put("value", value);
        map.put("taid", taid);
        switch (MyApplication.getLanguageIndex()) {
            case 1:
                map.put("lang", "TW");
                break;
            case 2:
                map.put("lang", "CH");
                break;
            case 3:
                map.put("Lang", "JP");
                break;
            default:
                map.put("lang", "EN");
                break;
        }
        mMobileMusic.getPushMusicData(map);
    }

    public void handleSearchData(String keyword) {
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        if (keyword.length() > 3) {
            getPushMusicData(MyApplication.getAppData().getTaid(), keyword, "search");
        } else {
            StringBuilder stringBuilder = new StringBuilder(keyword);
            if (keyword.length() != 1) {
                stringBuilder.delete(1, stringBuilder.length());
            }
            Matcher matcher = pattern.matcher(stringBuilder.toString());
            if (matcher.matches()) {
                getPushMusicData(MyApplication.getAppData().getTaid(), keyword, "search");
            } else {
                ivMobileMusic.showSearchError();
            }
        }
    }

    public void handlePushMusic() {
        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("musicTaid", "null").equals("null")) {
            getMusicRadioData("", false);
        } else {
            String oldDate = SharePreferencesUtil.getInstance().getSharedPreferences().getString("musicDate", "null");
            String nowDate = simpleDateFormat.format(new Date());
            if (oldDate.equals(nowDate)) {
                String taid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("musicTaid", "null");
                String value = SharePreferencesUtil.getInstance().getSharedPreferences().getString("musicValue", "null");
                MyApplication.getAppData().setTaid(taid);
                getPushMusicData(taid, value, "channel");
            } else {
                getMusicRadioData("", false);
            }
        }
    }

    public void sendLove(int index, String type, String taid, String webid, String lat, String lng) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("type", type);
        map.put("taid", taid);
        map.put("webid", webid);
        map.put("lat", lat);
        map.put("lng", lng);
        mMobileMusic.sendLove(map, index);
    }

    @Override
    public void handleMusicRadioData(GMusicRadio gMusicRadio, boolean isCollect) {
        if (isCollect) {
            MyApplication.getAppData().setTaid(gMusicRadio.getTdData().get(0).getTaid());
            MyApplication.getAppData().setMenuId(gMusicRadio.getTdData().get(0).getMenuid());
            getPushMusicData(MyApplication.getAppData().getTaid(), "", "collect");
        } else {
            if (MyApplication.getAppData().getMusicMode() == 0) {
                List<String> musicTitle = new ArrayList<>();
                List<String> musicPhotoUrl = new ArrayList<>();
                int size = gMusicRadio.getTdData().size();
                for (int i = 0; i < size; i++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    switch (MyApplication.getLanguageIndex()) {
                        case 1:
                            stringBuilder.append(gMusicRadio.getTdData().get(i).getM_winame_tw());
                            break;
                        case 2:
                            stringBuilder.append(gMusicRadio.getTdData().get(i).getM_winame_ch());
                            break;
                        case 3:
                            stringBuilder.append(gMusicRadio.getTdData().get(i).getM_winame_jp());
                            break;
                        default:
                            stringBuilder.append(gMusicRadio.getTdData().get(i).getM_winame());
                            break;
                    }
                    if (stringBuilder.toString().contains("<>")) {
                        int index = stringBuilder.indexOf("<");
                        stringBuilder.replace(index, index + 2, " ");
                    } else if (stringBuilder.toString().contains("&amp;#039;")) {
                        int index = stringBuilder.indexOf("&");
                        stringBuilder.replace(index, index + 10, "'");
                    }
                    musicTitle.add(stringBuilder.toString());
                    musicPhotoUrl.add(gMusicRadio.getTdData().get(i).getWpath());
                }
                MyApplication.getAppData().setTaid(gMusicRadio.getTdData().get(0).getTaid());
                MyApplication.getAppData().setMenuId(gMusicRadio.getTdData().get(0).getMenuid());
                ivMobileMusic.init(gMusicRadio, musicTitle, musicPhotoUrl);
            } else {
                int size = gMusicRadio.getTdData().size();
                List<String> musicTaid = new ArrayList<>();
                List<String> menuid = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    if (gMusicRadio.getTdData().get(i).getIs_push().equals("1")) {
                        musicTaid.add(gMusicRadio.getTdData().get(i).getTaid());
                        menuid.add(gMusicRadio.getTdData().get(i).getMenuid());
                    }
                }
                size = musicTaid.size();
                Random random = new Random();
                int index = random.nextInt(size - 1);
                MyApplication.getAppData().setTaid(musicTaid.get(index));
                MyApplication.getAppData().setMenuId(menuid.get(index));
                SharePreferencesUtil.getInstance().getEditor().putString("musicTaid", musicTaid.get(index)).apply();
                SharePreferencesUtil.getInstance().getEditor().putString("musicValue", menuid.get(index)).apply();
                SharePreferencesUtil.getInstance().getEditor().putString("musicDate", simpleDateFormat.format(new Date())).apply();
                getPushMusicData(musicTaid.get(index), menuid.get(index), "channel");
            }
        }
    }

    @Override
    public void handlePushMusicData(GPushMusic gPushMusic) {
        List<String> musicTitle = new ArrayList<>();
        List<String> musicPhotoUrl = new ArrayList<>();
        List<String> musicCount = new ArrayList<>();
        List<String> musicLove = new ArrayList<>();
        int size = gPushMusic.getPushMusicData().size();
        for (int i = 0; i < size; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    stringBuilder.append(gPushMusic.getPushMusicData().get(i).getWtitle_tw());
                    break;
                case 2:
                    stringBuilder.append(gPushMusic.getPushMusicData().get(i).getWtitle_ch());
                    break;
                case 3:
                    stringBuilder.append(gPushMusic.getPushMusicData().get(i).getWtitle_jp());
                    break;
                default:
                    stringBuilder.append(gPushMusic.getPushMusicData().get(i).getWtitle());
                    break;
            }
            if (stringBuilder.toString().contains("<>")) {
                int index = stringBuilder.indexOf("<");
                stringBuilder.replace(index, index + 2, " ");
            } else if (stringBuilder.toString().contains("&amp;#039;")) {
                int index = stringBuilder.indexOf("&");
                stringBuilder.replace(index, index + 10, "'");
            }
            musicTitle.add(stringBuilder.toString());
            musicPhotoUrl.add(gPushMusic.getPushMusicData().get(i).getPath_img());
            musicCount.add(gPushMusic.getPushMusicData().get(i).getCount());
            musicLove.add(gPushMusic.getPushMusicData().get(i).getLove());
        }
        ivMobileMusic.init(gPushMusic, musicTitle, musicPhotoUrl, musicCount, musicLove);
    }

    @Override
    public void handleLoveData(GSendLove gSendLove, int index) {
        ivMobileMusic.refreshLoveData(gSendLove.getSave(), index);
    }
}
