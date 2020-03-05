package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.entity.ESwapData;

import java.util.List;

public interface IVSwapStroke {
    void init(List<ESwapData> eSwapDataList);
    void handleFinishSave();
}
