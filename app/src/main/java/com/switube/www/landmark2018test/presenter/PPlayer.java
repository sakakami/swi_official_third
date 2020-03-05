package com.switube.www.landmark2018test.presenter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.database.entity.MusicEntity;
import com.switube.www.landmark2018test.gson.GPlayer;
import com.switube.www.landmark2018test.gson.GPushNewVideo;
import com.switube.www.landmark2018test.gson.GReport;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.model.MPlayer;
import com.switube.www.landmark2018test.presenter.callback.IPPlayer;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PPlayer implements IPPlayer {
    private IVPlayer ivPlayer;
    private MPlayer mPlayer;
    private Random random;
    public PPlayer(IVPlayer ivPlayer) {
        this.ivPlayer = ivPlayer;
        mPlayer = new MPlayer(this);
        random = new Random();
    }

    public void getMusicData(String webid, String pwebid) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("webid", webid);
        map.put("pwebid", pwebid);
        mPlayer.getMusicData(map);
    }

    public void handleSwitchMusic(int index) {
        String stid = MyApplication.getAppData().getMusicEntities().get(index).getStid();
        MyApplication.getAppData().getiFloatPlayerService().handleLoadVideo(stid, 0);
    }

    public void getPushMusicData(int index) {
        String webid;
        String pwebid;
        boolean nowLove = MyApplication.getAppData().isLove();
        GPlayer.Push pushNowPlaying;
        if (nowLove) {
            MyApplication.getAppData().getPush().setLove("1");
        } else {
            MyApplication.getAppData().getPush().setLove("0");
        }
        pushNowPlaying = MyApplication.getAppData().getPush();
        /*if (index == 0) {
            if (MyApplication.getAppData().getPushBuffer() != null) {
                MyApplication.getAppData().setPush(MyApplication.getAppData().getPushBuffer());
            } else {
                MyApplication.getAppData().setPush(MyApplication.getAppData().getgPlayer().getPushData().get(index));
            }
        } else {
            MyApplication.getAppData().setPush(MyApplication.getAppData().getgPlayer().getPushData().get(index - 1));
        }*/
        if (MyApplication.getAppData().getPushBuffer() != null) {
            if (index == 0) {
                MyApplication.getAppData().setPush(MyApplication.getAppData().getPushBuffer());
            } else {
                MyApplication.getAppData().setPush(MyApplication.getAppData().getgPlayer().getPushData().get(index - 1));
            }
        } else {
            MyApplication.getAppData().setPush(MyApplication.getAppData().getgPlayer().getPushData().get(index));
        }
        if (pushNowPlaying != null) {
            MyApplication.getAppData().setPushBuffer(pushNowPlaying);
        }
        webid = MyApplication.getAppData().getPush().getWebid();
        pwebid = MyApplication.getAppData().getPush().getWebid_push();
        switch (MyApplication.getLanguageIndex()) {
            case 1:
                MyApplication.getAppData().setChannelName(MyApplication.getAppData().getPush().getWtitle_tw());
                break;
            case 2:
                MyApplication.getAppData().setChannelName(MyApplication.getAppData().getPush().getWtitle_ch());
                break;
            case 3:
                MyApplication.getAppData().setChannelName(MyApplication.getAppData().getPush().getWtitle_jp());
                break;
            default:
                MyApplication.getAppData().setChannelName(MyApplication.getAppData().getPush().getWtitle());
                break;
        }
        MyApplication.getAppData().setLove(MyApplication.getAppData().getgPlayer().getPushData().get(index).getLove().equals("1"));
        MyApplication.getAppData().setWebId(webid);
        getMusicData(webid, pwebid);
    }

    public void handleLikeInPush(int index) {
        String type;
        String taid = MyApplication.getAppData().getTaid();
        String webid;
        if (MyApplication.getAppData().isPush()) {
            if (index == 0) {
                if (MyApplication.getAppData().getPushBuffer() != null) {
                    webid = MyApplication.getAppData().getPushBuffer().getWebid();
                    if (MyApplication.getAppData().getPushBuffer().getLove().equals("0")) {
                        type = "add";
                    } else {
                        type = "del";
                    }
                } else {
                    webid = MyApplication.getAppData().getgPlayer().getPushData().get(index).getWebid();
                    if (MyApplication.getAppData().getgPlayer().getPushData().get(index).getLove().equals("0")) {
                        type = "add";
                    } else {
                        type = "del";
                    }
                }
            } else {
                if (MyApplication.getAppData().getPushBuffer() != null) {
                    webid = MyApplication.getAppData().getgPlayer().getPushData().get(index - 1).getWebid();
                    if (MyApplication.getAppData().getgPlayer().getPushData().get(index - 1).getLove().equals("0")) {
                        type = "add";
                    } else {
                        type = "del";
                    }
                } else {
                    webid = MyApplication.getAppData().getgPlayer().getPushData().get(index).getWebid();
                    if (MyApplication.getAppData().getgPlayer().getPushData().get(index).getLove().equals("0")) {
                        type = "add";
                    } else {
                        type = "del";
                    }
                }
            }
        } else {
            webid = MyApplication.getAppData().getgPlayer().getPushData().get(index).getWebid();
            if (MyApplication.getAppData().getgPlayer().getPushData().get(index).getLove().equals("0")) {
                type = "add";
            } else {
                type = "del";
            }
        }
        sendLove(type, taid, webid, "", "", true, index);
    }

    public void handlePushData() {
        List<String> name = new ArrayList<>();
        List<String> img = new ArrayList<>();
        List<String> count = new ArrayList<>();
        List<String> love = new ArrayList<>();
        int size = MyApplication.getAppData().getgPlayer().getPushData().size();
        if (MyApplication.getAppData().getPushBuffer() != null) {
            size++;
        }
        for (int i = 0; i < size; i++) {
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    if (MyApplication.getAppData().getPushBuffer() != null) {
                        if (i == 0) {
                            name.add(MyApplication.getAppData().getPushBuffer().getWtitle_tw());
                        } else {
                            name.add(MyApplication.getAppData().getgPlayer().getPushData().get(i - 1).getWtitle_tw());
                        }
                    } else {
                        name.add(MyApplication.getAppData().getgPlayer().getPushData().get(i).getWtitle_tw());
                    }
                    break;
                case 2:
                    if (MyApplication.getAppData().getPushBuffer() != null) {
                        if (i == 0) {
                            name.add(MyApplication.getAppData().getPushBuffer().getWtitle_ch());
                        } else {
                            name.add(MyApplication.getAppData().getgPlayer().getPushData().get(i - 1).getWtitle_ch());
                        }
                    } else {
                        name.add(MyApplication.getAppData().getgPlayer().getPushData().get(i).getWtitle_ch());
                    }
                    break;
                case 3:
                    if (MyApplication.getAppData().getPushBuffer() != null) {
                        if (i == 0) {
                            name.add(MyApplication.getAppData().getPushBuffer().getWtitle_jp());
                        } else {
                            name.add(MyApplication.getAppData().getgPlayer().getPushData().get(i - 1).getWtitle_jp());
                        }
                    } else {
                        name.add(MyApplication.getAppData().getgPlayer().getPushData().get(i).getWtitle_jp());
                    }
                    break;
                default:
                    if (MyApplication.getAppData().getPushBuffer() != null) {
                        if (i == 0) {
                            name.add(MyApplication.getAppData().getPushBuffer().getWtitle());
                        } else {
                            name.add(MyApplication.getAppData().getgPlayer().getPushData().get(i - 1).getWtitle());
                        }
                    } else {
                        name.add(MyApplication.getAppData().getgPlayer().getPushData().get(i).getWtitle());
                    }
                    break;
            }
            if (MyApplication.getAppData().getPushBuffer() != null) {
                if (i == 0) {
                    img.add(MyApplication.getAppData().getPushBuffer().getPath_img());
                    count.add(MyApplication.getAppData().getPushBuffer().getCount());
                    love.add(MyApplication.getAppData().getPushBuffer().getLove());
                } else {
                    img.add(MyApplication.getAppData().getgPlayer().getPushData().get(i - 1).getPath_img());
                    count.add(MyApplication.getAppData().getgPlayer().getPushData().get(i - 1).getCount());
                    love.add(MyApplication.getAppData().getgPlayer().getPushData().get(i - 1).getLove());
                }
            } else {
                img.add(MyApplication.getAppData().getgPlayer().getPushData().get(i).getPath_img());
                count.add(MyApplication.getAppData().getgPlayer().getPushData().get(i).getCount());
                love.add(MyApplication.getAppData().getgPlayer().getPushData().get(i).getLove());
            }
        }
        ivPlayer.init(name, img, count, love);
    }

    public void handlePlaylistData() {
        List<String> name = new ArrayList<>();
        int size = MyApplication.getAppData().getMusicEntities().size();
        for (int i = 0; i < size; i++) {
            name.add(MyApplication.getAppData().getMusicEntities().get(i).getName());
        }
        ivPlayer.init(name);
    }

    public void handleRandomPlaying() {
        mPlayer.getMusicDataByRandom(false, -1, false);
    }

    public void handleNamePlaying() {
        mPlayer.getMusicDataByName(false, -1);
    }

    public void handleRefreshListWithXY(int x, int y) {
        int rangeX = MyApplication.getAppData().getRangeWith() / 9;
        int rangeY = MyApplication.getAppData().getRangeHeight() / 9;
        int areaX = 0;
        int areaY = 0;
        if (x < rangeX) {
            areaX = 1;
        } else if (x < rangeX * 2) {
            areaX = 2;
        } else if (x < rangeX * 3) {
            areaX = 3;
        } else if (x < rangeX * 4) {
            areaX = 4;
        } else if (x < rangeX * 5) {
            areaX = 5;
        } else if (x < rangeX * 6) {
            areaX = 6;
        } else if (x < rangeX * 7) {
            areaX = 7;
        } else if (x < rangeX * 8) {
            areaX = 8;
        } else if (x < rangeX * 9) {
            areaX = 9;
        }

        if (y < rangeY) {
            areaY = 1;
        } else if (y < rangeY * 2) {
            areaY = 2;
        } else if (y < rangeY * 3) {
            areaY = 3;
        } else if (y < rangeY * 4) {
            areaY = 4;
        } else if (y < rangeY * 5) {
            areaY = 5;
        } else if (y < rangeY * 6) {
            areaY = 6;
        } else if (y < rangeY * 7) {
            areaY = 7;
        } else if (y < rangeY * 8) {
            areaY = 8;
        } else if (y < rangeY * 9) {
            areaY = 9;
        }
        handleBasePhoto(areaX, areaY);
        switch (MyApplication.getAppData().getSlideMode()) {
            case 0:
                mPlayer.getSmallRange(areaX, areaY);
                break;
            case 1:
                mPlayer.getMiddleRange(areaX, areaX + 1, areaX - 1, areaY, areaY + 1, areaY - 1);
                break;
            case 2:
                mPlayer.getLargeRange(areaX, areaX + 1, areaX + 2, areaX - 1, areaX - 2, areaY, areaY + 1, areaY + 2, areaY - 1, areaY - 2);
                break;
            default:
                break;
        }
    }

    public void handleBasePhotoByType2(int x, int y) {
        double maxWidth =  MyApplication.getAppData().getRangeWith();
        double maxHeight = MyApplication.getAppData().getRangeHeight();
        double constantRatio = maxHeight / maxWidth;
        double nowY1 = constantRatio * x;
        double nowY2 = constantRatio * (maxWidth - x);
        if (y <= nowY1 && y <= nowY2) {
            ivPlayer.initBasePhoto(R.drawable.bg_square02_v2_2x);
            handleMapRuleTwo("1");
        } else if (y > nowY1 && y > nowY2) {
            ivPlayer.initBasePhoto(R.drawable.bg_square07_v2_2x);
            handleMapRuleTwo("2");
        } else if (y > nowY1 && y <= nowY2) {
            ivPlayer.initBasePhoto(R.drawable.bg_square04_v2_2x);
            handleMapRuleTwo("3");
        } else {
            ivPlayer.initBasePhoto(R.drawable.bg_square03_v2_2x);
            handleMapRuleTwo("4");
        }
    }

    public void showBasePhoto(int x, int y) {
        double maxWidth =  MyApplication.getAppData().getRangeWith();
        double maxHeight = MyApplication.getAppData().getRangeHeight();
        double constantRatio = maxHeight / maxWidth;
        double nowY1 = constantRatio * x;
        double nowY2 = constantRatio * (maxWidth - x);
        if (y <= nowY1 && y <= nowY2) {
            ivPlayer.initBasePhoto(R.drawable.bg_square02_v2_2x);
        } else if (y > nowY1 && y > nowY2) {
            ivPlayer.initBasePhoto(R.drawable.bg_square07_v2_2x);
        } else if (y > nowY1 && y <= nowY2) {
            ivPlayer.initBasePhoto(R.drawable.bg_square04_v2_2x);
        } else {
            ivPlayer.initBasePhoto(R.drawable.bg_square03_v2_2x);
        }
    }

    public void handleSendAddVideo(String webid, String tubeid) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("webid", webid);
        map.put("tubeid", tubeid);
        mPlayer.handelSendAddVideo(map);
    }

    public void sendLove(String type, String taid, String webid, String lat, String lng, boolean isPush, int pushIndex) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("type", type);
        map.put("taid", taid);
        map.put("webid", webid);
        map.put("lat", lat);
        map.put("lng", lng);
        mPlayer.sendLove(map, isPush, pushIndex);
    }

    public void handleSlidePoint() {
        int rangeWidth = MyApplication.getAppData().getRangeWith() / 9;
        int rangeHeight = MyApplication.getAppData().getRangeHeight() / 9;
        int x = 0;
        int y = 0;
        switch (MyApplication.getAppData().getAreaX()) {
            case 1:
                x = random.nextInt(rangeWidth);
                break;
            case 2:
                x = rangeWidth + random.nextInt(rangeWidth);
                break;
            case 3:
                x = rangeWidth * 2 + random.nextInt(rangeWidth);
                break;
            case 4:
                x = rangeWidth * 3 + random.nextInt(rangeWidth);
                break;
            case 5:
                x = rangeWidth * 4 + random.nextInt(rangeWidth);
                break;
            case 6:
                x = rangeWidth * 5 + random.nextInt(rangeWidth);
                break;
            case 7:
                x = rangeWidth * 6 + random.nextInt(rangeWidth);
                break;
            case 8:
                x = rangeWidth * 7 + random.nextInt(rangeWidth);
                break;
            case 9:
                x = rangeWidth * 8 + random.nextInt(rangeWidth);
                break;
            default:
                break;
        }
        switch (MyApplication.getAppData().getAreaY()) {
            case 1:
                y = random.nextInt(rangeHeight);
                break;
            case 2:
                y = rangeHeight + random.nextInt(rangeHeight);
                break;
            case 3:
                y = rangeHeight * 2 + random.nextInt(rangeHeight);
                break;
            case 4:
                y = rangeHeight * 3 + random.nextInt(rangeHeight);
                break;
            case 5:
                y = rangeHeight * 4 + random.nextInt(rangeHeight);
                break;
            case 6:
                y = rangeHeight * 5 + random.nextInt(rangeHeight);
                break;
            case 7:
                y = rangeHeight * 6 + random.nextInt(rangeHeight);
                break;
            case 8:
                y = rangeHeight * 7 + random.nextInt(rangeHeight);
                break;
            case 9:
                y = rangeHeight * 8 + random.nextInt(rangeHeight);
                break;
            default:
                break;
        }
        showBasePhoto(x, y);
        ivPlayer.handleMoveSlide(x, y);
    }

    public void handleReportOne(View view) {
        List<String> reportTitle = new ArrayList<>();
        int size = MyApplication.getAppData().getgPlayer().getRaData().size();
        for (int i = 0; i < size; i++) {
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    reportTitle.add(MyApplication.getAppData().getgPlayer().getRaData().get(i).getRaname_tw());
                    break;
                case 2:
                    reportTitle.add(MyApplication.getAppData().getgPlayer().getRaData().get(i).getRaname_ch());
                    break;
                case 3:
                    reportTitle.add(MyApplication.getAppData().getgPlayer().getRaData().get(i).getRaname_jp());
                    break;
                default:
                    reportTitle.add(MyApplication.getAppData().getgPlayer().getRaData().get(i).getRaname());
                    break;
            }
        }
        ivPlayer.initReport(reportTitle, view);
    }

    public void handleReportTwo(View view, int index, TextView textView) {
        if (index == -1) {
            index = 0;
        }
        List<String> title = new ArrayList<>();
        String name = "";
        int size = MyApplication.getAppData().getgPlayer().getRaData().get(index).getData().size();
        for (int i = 0; i < size; i++) {
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    if (i == 0) {
                        name = MyApplication.getAppData().getgPlayer().getRaData().get(index).getRaname_tw();
                    }
                    title.add(MyApplication.getAppData().getgPlayer().getRaData().get(index).getData().get(i).getRiname_tw());
                    break;
                case 2:
                    if (i == 0) {
                        name = MyApplication.getAppData().getgPlayer().getRaData().get(index).getRaname_ch();
                    }
                    title.add(MyApplication.getAppData().getgPlayer().getRaData().get(index).getData().get(i).getRiname_ch());
                    break;
                case 3:
                    if (i == 0) {
                        name = MyApplication.getAppData().getgPlayer().getRaData().get(index).getRaname_jp();
                    }
                    title.add(MyApplication.getAppData().getgPlayer().getRaData().get(index).getData().get(i).getRiname_jp());
                    break;
                default:
                    if (i == 0) {
                        name = MyApplication.getAppData().getgPlayer().getRaData().get(index).getRaname();
                    }
                    title.add(MyApplication.getAppData().getgPlayer().getRaData().get(index).getData().get(i).getRiname());
                    break;
            }
        }
        ivPlayer.initReport(title, view, textView, name, index);
    }

    public void sendReportData(int index, int indexOne) {
        if (index == -1) {
            index = 0;
        }
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("tubeid", MyApplication.getAppData().getMusicEntities().get(MyApplication.getAppData().getPlayingIndex()).getStid());
        map.put("riid", MyApplication.getAppData().getgPlayer().getRaData().get(indexOne).getData().get(index).getId());
        map.put("webid", MyApplication.getAppData().getMusicEntities().get(MyApplication.getAppData().getPlayingIndex()).getWebid());
        Log.e("map", map.toString());
        mPlayer.sendReport(map);
    }

    @Override
    public void handleMusicData(GPlayer gPlayer) {
        int size = gPlayer.getData().size();
        List<MusicEntity> musicEntities = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            musicEntities.add(new MusicEntity(gPlayer.getData().get(i).getWebid(), gPlayer.getData().get(i).getStid(),
                    gPlayer.getData().get(i).getName(), gPlayer.getData().get(i).getXx(),
                    gPlayer.getData().get(i).getYy(), gPlayer.getData().get(i).getAtdate(),
                    gPlayer.getData().get(i).getLengths(), gPlayer.getData().get(i).getView()));
        }
        mPlayer.insertMusicData(musicEntities, gPlayer);
    }

    @Override
    public void handleFinishInsert(GPlayer gPlayer) {
        MyApplication.getAppData().setgPlayer(gPlayer);
        switch (gPlayer.getSmartData().getWtype()) {
            case "0":
                handleMapRuleOne(gPlayer.getSmartData().getWsort());
                break;
            case "1":
                handleMapRuleThree();
                break;
            case "2":
                handleMapRuleTwo(gPlayer.getSmartData().getWsmart());
                handleBasePhoto(0, 0);
                break;
            default:
                break;
        }
    }

    @Override
    public void init(List<MusicEntity> musicEntities, boolean needRandom, int randomMode, boolean isAllShow) {
        ivPlayer.handleSwitchMapOrList();
        ivPlayer.handleSwitchSlidePhoto(!isAllShow);
        ivPlayer.handleSwitchPlayingMode();
        if (needRandom) {
            handleRandomList(randomMode, musicEntities);
        } else {
            int size = musicEntities.size();
            List<String> name = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                name.add(musicEntities.get(i).getName());
            }
            MyApplication.getAppData().setMusicEntities(musicEntities);
            MyApplication.getAppData().setPlayingIndex(0);
            if (MyApplication.getAppData().isFirstPlaying()) {
                MyApplication.getAppData().setFirstPlaying(false);
                MyApplication.getAppData().getiFloatPlayerService().handlePlayerInitialized(musicEntities.get(0).getStid(), 0);
            } else {
                MyApplication.getAppData().getiFloatPlayerService().handleLoadVideo(musicEntities.get(0).getStid(), 0);
            }
            MyApplication.getAppData().setPlaying(true);
            ivPlayer.init(name);
        }
    }

    @Override
    public void handleSmallRange(List<MusicEntity> musicEntities) {
        if (musicEntities.size() > 0) {
            init(musicEntities, false, -1, false);
        } else {
            ivPlayer.handleZoomOutSlide();
            handleMapRuleThree();
        }
    }

    @Override
    public void handleMiddleRange(List<MusicEntity> musicEntities) {
        if (musicEntities.size() > 0) {
            init(musicEntities, false, -1, false);
        } else {
            ivPlayer.handleZoomOutSlide();
            handleMapRuleThree();
        }
    }

    @Override
    public void handleLargeRange(List<MusicEntity> musicEntities) {
        if (musicEntities.size() > 0) {
            init(musicEntities, false, -1, false);
        } else {
            mPlayer.getMusicDataByRandom(false, -1, true);
        }
    }

    @Override
    public void handleFinishSend() {
        ivPlayer.handleFinishSend();
    }

    @Override
    public void handleLoveData(GSendLove gSendLove, boolean isPush, int pushIndex) {
        switch (gSendLove.getSave()) {
            case "1":
                if (isPush) {
                    if (MyApplication.getAppData().isPush()) {
                        if (pushIndex == 0) {
                            if (MyApplication.getAppData().getPushBuffer() != null) {
                                MyApplication.getAppData().getPushBuffer().setLove("1");
                            } else {
                                MyApplication.getAppData().getgPlayer().getPushData().get(pushIndex).setLove("1");
                            }
                        } else {
                            if (MyApplication.getAppData().getPushBuffer() != null) {
                                MyApplication.getAppData().getgPlayer().getPushData().get(pushIndex - 1).setLove("1");
                            } else {
                                MyApplication.getAppData().getgPlayer().getPushData().get(pushIndex).setLove("1");
                            }
                        }
                    } else {
                        MyApplication.getAppData().getgPlayer().getPushData().get(pushIndex).setLove("1");
                    }
                } else {
                    MyApplication.getAppData().setLove(true);
                }
                break;
            case "10":
                if (isPush) {
                    if (MyApplication.getAppData().isPush()) {
                        if (pushIndex == 0) {
                            if (MyApplication.getAppData().getPushBuffer() != null) {
                                MyApplication.getAppData().getPushBuffer().setLove("0");
                            } else {
                                MyApplication.getAppData().getgPlayer().getPushData().get(pushIndex).setLove("0");
                            }
                        } else {
                            if (MyApplication.getAppData().getPushBuffer() != null) {
                                MyApplication.getAppData().getgPlayer().getPushData().get(pushIndex - 1).setLove("0");
                            } else {
                                MyApplication.getAppData().getgPlayer().getPushData().get(pushIndex).setLove("0");
                            }
                        }
                    } else {
                        MyApplication.getAppData().getgPlayer().getPushData().get(pushIndex).setLove("0");
                    }
                } else {
                    MyApplication.getAppData().setLove(false);
                }
                break;
            default:
                break;
        }
        if (isPush) {
            switch (gSendLove.getSave()) {
                case "1":
                    ivPlayer.handleRefreshLoveInPush(pushIndex, "1");
                    break;
                case "10":
                    ivPlayer.handleRefreshLoveInPush(pushIndex, "0");
                    break;
                default:
                    break;
            }
        } else {
            ivPlayer.handleLove(true);
        }
    }

    @Override
    public void handleReportSuccess(GReport gReport) {
        if (gReport.getRenew().equals("1")) {
            ivPlayer.handleReportSuccess();
        }
    }

    @Override
    public void handlePushNewVideoSuccess(GPushNewVideo gPushNewVideo) {
        if (gPushNewVideo.getRenew().equals("1")) {
            ivPlayer.handlePushNewSuccess();
        }
    }

    private void handleMapRuleOne(String sort) {
        switch (sort) {
            case "1":
                MyApplication.getAppData().setRandom(false);
                mPlayer.getMusicDataByName(false, -1);
                break;
            case "2":
                MyApplication.getAppData().setRandom(true);
                mPlayer.getMusicDataByRandom(false, -1, false);
                break;
            case "3":
                MyApplication.getAppData().setRandom(false);
                mPlayer.getMusicDataByView(false, -1);
                break;
            case "4":
                MyApplication.getAppData().setRandom(false);
                mPlayer.getMusicDataByDateD(false, -1);
                break;
            default:
                break;
        }
    }

    private void handleMapRuleTwo(String smart) {
        switch (smart) {
            case "1":
                MyApplication.getAppData().setRandom(false);
                mPlayer.getMusicDataByDateD(true, 5);
                break;
            case "2":
                MyApplication.getAppData().setRandom(false);
                mPlayer.getMusicDataByDateA(true, 6);
                break;
            case "3":
                MyApplication.getAppData().setRandom(false);
                mPlayer.getMusicDataByLengthD(true, 8);
                break;
            case "4":
                MyApplication.getAppData().setRandom(false);
                mPlayer.getMusicDataByLengthA(true, 7);
                break;
            case "5":
                MyApplication.getAppData().setRandom(true);
                mPlayer.getMusicDataByRandom(false, -1, false);
                break;
            default:
                break;
        }
    }

    private void handleMapRuleThree() {
        int areaX = random.nextInt(8) + 1;
        int areaY = random.nextInt(8) + 1;
        MyApplication.getAppData().setAreaX(areaX);
        MyApplication.getAppData().setAreaY(areaY);
        handleBasePhoto(areaX, areaY);
        MyApplication.getAppData().setNeedAuto(true);
        switch (MyApplication.getAppData().getSlideMode()) {
            case 0:
                mPlayer.getSmallRange(areaX, areaY);
                break;
            case 1:
                mPlayer.getMiddleRange(areaX, areaX + 1, areaX - 1, areaY, areaY + 1, areaY - 1);
                break;
            case 2:
                mPlayer.getLargeRange(areaX, areaX + 1, areaX + 2, areaX - 1, areaX - 2, areaY, areaY + 1, areaY + 2, areaY - 1, areaY - 2);
                break;
            default:
                break;
        }
    }

    private void handleBasePhoto(int x, int y) {
        int resource = 0;
        if (MyApplication.getAppData().getgPlayer().getSmartData().getWtype().equals("1")) {
            if (x < 4) {
                if (y < 4) {
                    resource = R.drawable.bg_square01_v2_2x;
                } else if (y < 7) {
                    resource = R.drawable.bg_square04_v2_2x;
                } else {
                    resource = R.drawable.bg_square07_v2_2x;
                }
            } else if (x < 7) {
                if (y < 4) {
                    resource = R.drawable.bg_square02_v2_2x;
                } else if (y < 7) {
                    resource = R.drawable.bg_square05_v2_2x;
                } else {
                    resource = R.drawable.bg_square08_v2_2x;
                }
            } else if (x < 10) {
                if (y < 4) {
                    resource = R.drawable.bg_square03_v2_2x;
                } else if (y < 7) {
                    resource = R.drawable.bg_square06_v2_2x;
                } else {
                    resource = R.drawable.bg_square09_v2_2x;
                }
            }
        } else {
            switch (MyApplication.getAppData().getgPlayer().getSmartData().getWsort()) {
                case "1":
                    resource = R.drawable.bg_square02_v2_2x;
                    break;
                case "2":
                    resource = R.drawable.bg_square07_v2_2x;
                    break;
                case "3":
                    resource = R.drawable.bg_square04_v2_2x;
                    break;
                case "4":
                    resource = R.drawable.bg_square03_v2_2x;
                    break;
                default:
                    //resource = R.drawable.bg_square01_v2_2x;
                    break;
            }
        }
        ivPlayer.initBasePhoto(resource);
    }

    private void handleRandomList(int each, List<MusicEntity> musicEntities) {
        int size = musicEntities.size();
        int times = size / each;
        int residue = size % each;
        int nowTimes = 1;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            if (i == each * nowTimes) {
                nowTimes++;
            }
            int zero = each * (nowTimes - 1);
            if (nowTimes <= times) {
                int index = random.nextInt(5) + zero;
                MusicEntity musicEntity = musicEntities.get(index);
                int indexTwo = random.nextInt(5) + zero;
                MusicEntity musicEntity1 = musicEntities.get(indexTwo);
                if (index != indexTwo) {
                    musicEntities.set(index, musicEntity1);
                    musicEntities.set(indexTwo, musicEntity);
                }
            } else {
                int index = random.nextInt(residue) + zero;
                MusicEntity musicEntity = musicEntities.get(index);
                int indexTwo = random.nextInt(residue) + zero;
                MusicEntity musicEntity1 = musicEntities.get(indexTwo);
                if (index != indexTwo) {
                    musicEntities.set(index, musicEntity1);
                    musicEntities.set(indexTwo, musicEntity);
                }
            }
        }
        size = musicEntities.size();
        List<String> name = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            name.add(musicEntities.get(i).getName());
        }
        MyApplication.getAppData().setMusicEntities(musicEntities);
        MyApplication.getAppData().setPlayingIndex(0);
        if (MyApplication.getAppData().isFirstPlaying()) {
            MyApplication.getAppData().setFirstPlaying(false);
            MyApplication.getAppData().getiFloatPlayerService().handlePlayerInitialized(musicEntities.get(0).getStid(), 0);
        } else {
            MyApplication.getAppData().getiFloatPlayerService().handleLoadVideo(musicEntities.get(0).getStid(), 0);
        }
        MyApplication.getAppData().setPlaying(true);
        ivPlayer.init(name);
    }
}
