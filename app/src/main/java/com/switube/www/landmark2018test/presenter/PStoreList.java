package com.switube.www.landmark2018test.presenter;

import com.switube.www.landmark2018test.entity.EStoreList;
import com.switube.www.landmark2018test.view.callback.IVStoreList;

import java.util.ArrayList;
import java.util.List;

public class PStoreList {
    private IVStoreList ivStoreList;
    public PStoreList(IVStoreList ivStoreList) {
        this.ivStoreList = ivStoreList;
    }

    public void init() {
        List<EStoreList> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            EStoreList eStoreList = new EStoreList();
            eStoreList.setId(String.valueOf(i));
            switch (i) {
                case 0:
                    eStoreList.setName("Swi早餐");
                    eStoreList.setMessage("20點~60點");
                    eStoreList.setId("store0001");
                    break;
                case 1:
                    eStoreList.setName("Swi便當");
                    eStoreList.setMessage("60點~90點");
                    eStoreList.setId("store0002");
                    break;
                case 2:
                    eStoreList.setName("Swi麵食館");
                    eStoreList.setMessage("40點~120點");
                    eStoreList.setId("store0003");
                    break;
                case 3:
                    eStoreList.setName("Swi鮮奶茶");
                    eStoreList.setMessage("25點~70點");
                    eStoreList.setId("store0004");
                    break;
                default:
                    eStoreList.setName("Swi義式料理");
                    eStoreList.setMessage("180點~280點");
                    eStoreList.setId("store0005");
                    break;
            }
            list.add(eStoreList);
        }
        ivStoreList.initAdapter(list);
    }
}
