package com.switube.www.landmark2018test.presenter;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.gson.GMusicRadio;
import com.switube.www.landmark2018test.model.MMore;
import com.switube.www.landmark2018test.presenter.callback.IPMore;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVMore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PMore implements IPMore {
    private IVMore ivMore;
    private MMore mMore;
    public PMore(IVMore ivMore) {
        this.ivMore = ivMore;
        mMore = new MMore(this);
    }

    public void getMoreData() {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("taid", "");
        mMore.getMoreData(map);
    }

    @Override
    public void handleMoreData(GMusicRadio gMusicRadio) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        mMore.getStrokeData(map, gMusicRadio);
    }

    @Override
    public void handleStrokeData(GMusicRadio gMusicRadio, GMusicRadio more) {
        more.setRaData(gMusicRadio.getRaData());
        List<String> name = new ArrayList<>();
        List<String> img = new ArrayList<>();
        int taSize = more.getTaData().size();
        int raSize = more.getRaData().size();
        int size = taSize + raSize;
        for (int i = 0; i < size; i++) {
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    if (i < taSize) {
                        name.add(more.getTaData().get(i).getTitle_tw());
                    } else {
                        name.add(more.getRaData().get(i - taSize).getTitle_tw());
                    }
                    break;
                case 2:
                    if (i < taSize) {
                        name.add(more.getTaData().get(i).getTitle_ch());
                    } else {
                        name.add(more.getRaData().get(i - taSize).getTitle_ch());
                    }
                    break;
                case 3:
                    if (i < taSize) {
                        name.add(more.getTaData().get(i).getTitle_jp());
                    } else {
                        name.add(more.getRaData().get(i - taSize).getTitle_jp());
                    }
                    break;
                default:
                    if (i < taSize) {
                        name.add(more.getTaData().get(i).getTitle_en());
                    } else {
                        name.add(more.getRaData().get(i - taSize).getTitle_en());
                    }
                    break;
            }
            if (i < taSize) {
                img.add(more.getTaData().get(i).getPath_img());
            } else {
                img.add(more.getRaData().get(i - taSize).getPath_img());
            }
        }
        ivMore.init(more, name, img);
    }
}
