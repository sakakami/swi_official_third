package com.switube.www.landmark2018test.view.callback;

import android.view.View;
import android.widget.TextView;

import java.util.List;

public interface IVPlayer {
    void init(List<String> name);
    void init(List<String> name, List<String> img, List<String> count, List<String> love);
    void handleZoomOutSlide();
    void handleMoveSlide(int x, int y);
    void initBasePhoto(int resource);
    void handleFinishSend();
    void handleSwitchSlidePhoto(boolean isSwitch);
    void handleSwitchPlayingMode();
    void handleSwitchMapOrList();
    void handleLove(boolean needToast);
    void handleRefreshLoveInPush(int index, String love);
    void initReport(List<String> name, View view);
    void initReport(List<String> name, View view, TextView textView, String title, int index);
    void handleReportSuccess();
    void handlePushNewSuccess();
}
