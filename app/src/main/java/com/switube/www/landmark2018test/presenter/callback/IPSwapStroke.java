package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GSendLove;

import java.util.List;

public interface IPSwapStroke {
    void handleFinishToStroke(GSendLove gSendLove, List<Integer> fromPositionList, List<Integer> toPositionList);
}
