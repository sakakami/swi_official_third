package com.switube.www.landmark2018test.presenter;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.entity.EStoreList;
import com.switube.www.landmark2018test.view.callback.IVExchangeList;

import java.util.ArrayList;
import java.util.List;

public class PExchangeList {
    private IVExchangeList ivExchangeList;
    public PExchangeList(IVExchangeList ivExchangeList) {
        this.ivExchangeList = ivExchangeList;
    }

    public void init() {
        String storeId = MyApplication.getAppData().getSelectedStoreId();
        List<EStoreList> list = new ArrayList<>();
        switch (storeId) {
            case "store0001":
                for (int i = 0; i < 10; i++) {
                    EStoreList eStoreList = new EStoreList();
                    eStoreList.setId(String.valueOf(i));
                    eStoreList.setStore("Swi早餐");
                    switch (i) {
                        case 0:
                            eStoreList.setName("豆漿");
                            eStoreList.setMessage("20");
                            eStoreList.setId("item00010001");
                            break;
                        case 1:
                            eStoreList.setName("蛋餅");
                            eStoreList.setMessage("25");
                            eStoreList.setId("item00010004");
                            break;
                        case 2:
                            eStoreList.setName("豬肉蛋堡");
                            eStoreList.setMessage("35");
                            eStoreList.setId("item00010005");
                            break;
                        case 3:
                            eStoreList.setName("港式蘿菠糕");
                            eStoreList.setMessage("30");
                            eStoreList.setId("item00010009");
                            break;
                        case 4:
                            eStoreList.setName("肉蛋土司");
                            eStoreList.setMessage("45");
                            eStoreList.setId("item00010006");
                            break;
                        case 5:
                            eStoreList.setName("鐵板麵");
                            eStoreList.setMessage("40");
                            eStoreList.setId("item00010007");
                            break;
                        case 6:
                            eStoreList.setName("奶茶");
                            eStoreList.setMessage("30");
                            eStoreList.setId("item00010002");
                            break;
                        case 7:
                            eStoreList.setName("美式咖啡");
                            eStoreList.setMessage("40");
                            eStoreList.setId("item00010003");
                            break;
                        case 8:
                            eStoreList.setName("薯餅");
                            eStoreList.setMessage("30");
                            eStoreList.setId("item00010010");
                            break;
                        default:
                            eStoreList.setName("每日套餐");
                            eStoreList.setMessage("60");
                            eStoreList.setId("item00010008");
                            break;
                    }
                    list.add(eStoreList);
                }
                break;
            case "store0002":
                for (int i = 0; i < 10; i++) {
                    EStoreList eStoreList = new EStoreList();
                    eStoreList.setId(String.valueOf(i));
                    eStoreList.setStore("Swi便當");
                    switch (i) {
                        case 0:
                            eStoreList.setName("菜飯");
                            eStoreList.setMessage("60");
                            eStoreList.setId("item00020001");
                            break;
                        case 1:
                            eStoreList.setName("鯖魚飯");
                            eStoreList.setMessage("70");
                            eStoreList.setId("item00020002");
                            break;
                        case 2:
                            eStoreList.setName("爌肉飯");
                            eStoreList.setMessage("70");
                            eStoreList.setId("item00020003");
                            break;
                        case 3:
                            eStoreList.setName("滷雞腿飯");
                            eStoreList.setMessage("70");
                            eStoreList.setId("item00020004");
                            break;
                        case 4:
                            eStoreList.setName("烤肉飯");
                            eStoreList.setMessage("70");
                            eStoreList.setId("item00020005");
                            break;
                        case 5:
                            eStoreList.setName("烤雞翅飯");
                            eStoreList.setMessage("80");
                            eStoreList.setId("item00020006");
                            break;
                        case 6:
                            eStoreList.setName("排骨飯");
                            eStoreList.setMessage("80");
                            eStoreList.setId("item00020007");
                            break;
                        case 7:
                            eStoreList.setName("炸雞排飯");
                            eStoreList.setMessage("80");
                            eStoreList.setId("item00020008");
                            break;
                        case 8:
                            eStoreList.setName("蜜汁雞腿飯");
                            eStoreList.setMessage("90");
                            eStoreList.setId("item00020009");
                            break;
                        default:
                            eStoreList.setName("雞腿飯");
                            eStoreList.setMessage("90");
                            eStoreList.setId("item00020010");
                            break;
                    }
                    list.add(eStoreList);
                }
                break;
            case "store0003":
                for (int i = 0; i < 10; i++) {
                    EStoreList eStoreList = new EStoreList();
                    eStoreList.setId(String.valueOf(i));
                    eStoreList.setStore("Swi麵食館");
                    switch (i) {
                        case 0:
                            eStoreList.setName("陽春麵");
                            eStoreList.setMessage("40");
                            eStoreList.setId("item00030001");
                            break;
                        case 1:
                            eStoreList.setName("陽春乾麵");
                            eStoreList.setMessage("40");
                            eStoreList.setId("item00030002");
                            break;
                        case 2:
                            eStoreList.setName("麻醬麵");
                            eStoreList.setMessage("40");
                            eStoreList.setId("item00030003");
                            break;
                        case 3:
                            eStoreList.setName("餛飩乾麵");
                            eStoreList.setMessage("50");
                            eStoreList.setId("item00030004");
                            break;
                        case 4:
                            eStoreList.setName("榨菜肉絲麵");
                            eStoreList.setMessage("60");
                            eStoreList.setId("item00030005");
                            break;
                        case 5:
                            eStoreList.setName("餛飩麵");
                            eStoreList.setMessage("60");
                            eStoreList.setId("item00030006");
                            break;
                        case 6:
                            eStoreList.setName("酸辣麵");
                            eStoreList.setMessage("60");
                            eStoreList.setId("item00030007");
                            break;
                        case 7:
                            eStoreList.setName("大滷麵");
                            eStoreList.setMessage("60");
                            eStoreList.setId("item00030008");
                            break;
                        case 8:
                            eStoreList.setName("牛肉湯麵");
                            eStoreList.setMessage("90");
                            eStoreList.setId("item00030009");
                            break;
                        default:
                            eStoreList.setName("牛肉麵");
                            eStoreList.setMessage("120");
                            eStoreList.setId("item00030010");
                            break;
                    }
                    list.add(eStoreList);
                }
                break;
            case "store0004":
                for (int i = 0; i < 10; i++) {
                    EStoreList eStoreList = new EStoreList();
                    eStoreList.setId(String.valueOf(i));
                    eStoreList.setStore("Swi鮮奶茶");
                    switch (i) {
                        case 0:
                            eStoreList.setName("極品青茶");
                            eStoreList.setMessage("25");
                            eStoreList.setId("item00040001");
                            break;
                        case 1:
                            eStoreList.setName("特級綠茶");
                            eStoreList.setMessage("25");
                            eStoreList.setId("item00040002");
                            break;
                        case 2:
                            eStoreList.setName("錫蘭紅茶");
                            eStoreList.setMessage("25");
                            eStoreList.setId("item00040003");
                            break;
                        case 3:
                            eStoreList.setName("烏龍綠茶");
                            eStoreList.setMessage("30");
                            eStoreList.setId("item00040004");
                            break;
                        case 4:
                            eStoreList.setName("特級奶綠");
                            eStoreList.setMessage("40");
                            eStoreList.setId("item00040005");
                            break;
                        case 5:
                            eStoreList.setName("烏龍奶茶");
                            eStoreList.setMessage("40");
                            eStoreList.setId("item00040006");
                            break;
                        case 6:
                            eStoreList.setName("珍珠奶茶");
                            eStoreList.setMessage("50");
                            eStoreList.setId("item00040007");
                            break;
                        case 7:
                            eStoreList.setName("鮮奶茶");
                            eStoreList.setMessage("60");
                            eStoreList.setId("item00040008");
                            break;
                        case 8:
                            eStoreList.setName("冬瓜鮮奶茶");
                            eStoreList.setMessage("60");
                            eStoreList.setId("item00040009");
                            break;
                        default:
                            eStoreList.setName("珍珠鮮奶茶");
                            eStoreList.setMessage("70");
                            eStoreList.setId("item00040010");
                            break;
                    }
                    list.add(eStoreList);
                }
                break;
            default:
                for (int i = 0; i < 10; i++) {
                    EStoreList eStoreList = new EStoreList();
                    eStoreList.setId(String.valueOf(i));
                    eStoreList.setStore("Swi義式料理");
                    switch (i) {
                        case 0:
                            eStoreList.setName("經典肉醬麵");
                            eStoreList.setMessage("180");
                            eStoreList.setId("item00050001");
                            break;
                        case 1:
                            eStoreList.setName("白酒蛤蠣");
                            eStoreList.setMessage("220");
                            eStoreList.setId("item00050002");
                            break;
                        case 2:
                            eStoreList.setName("青醬海鮮");
                            eStoreList.setMessage("240");
                            eStoreList.setId("item00050003");
                            break;
                        case 3:
                            eStoreList.setName("青醬蛤蠣");
                            eStoreList.setMessage("240");
                            eStoreList.setId("item00050004");
                            break;
                        case 4:
                            eStoreList.setName("義式肉醬千層麵");
                            eStoreList.setMessage("200");
                            eStoreList.setId("item00050005");
                            break;
                        case 5:
                            eStoreList.setName("奶油焗烤海鮮飯");
                            eStoreList.setMessage("2400");
                            eStoreList.setId("item00050006");
                            break;
                        case 6:
                            eStoreList.setName("焗烤南瓜鮮蝦飯");
                            eStoreList.setMessage("240");
                            eStoreList.setId("item00050007");
                            break;
                        case 7:
                            eStoreList.setName("野菇奶油燉飯");
                            eStoreList.setMessage("200");
                            eStoreList.setId("item00050008");
                            break;
                        case 8:
                            eStoreList.setName("紅酒香煎雞排燉飯");
                            eStoreList.setMessage("240");
                            eStoreList.setId("item00050009");
                            break;
                        default:
                            eStoreList.setName("青醬海鮮燉飯");
                            eStoreList.setMessage("240");
                            eStoreList.setId("item00050010");
                            break;
                    }
                    list.add(eStoreList);
                }
                break;
        }

        ivExchangeList.initAdapter(list);
    }
}
