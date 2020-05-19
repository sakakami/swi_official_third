package com.switube.www.landmark2018test.util;

public class PostCode {
    public String getCityId(String city, String district) {
        switch (city) {
            case "Taichung City":
                return handleTaichung(district);
            case "Kaohsiung City":
                return handleKaohsiung(district);
            case "Natou Country":
                return handleNantou(district);
            case "Taipei City":
                return handleTaipei(district);
            case "New Taipei City":
                return handleNewTaipei(district);
            case "Keelung City":
                return handleKeelung(district);
            case "Taoyuan City":
                return handleTaoyuan(district);
            case "Hsinchu City":
                return "300";
            case "Hsinchu County":
                return handleHsinchu(district);
            case "Miaoli County":
                return handleMiaoli(district);
            case "Changhua County":
                return handleChanghua(district);
            case "Yunlin County":
                return handleYunlin(district);
            case "Chiayi City":
                return "600";
            case "Chiayi County":
                return handleChiayi(district);
            case "Tainan City":
                return handleTainan(district);
            case "Pingtung County":
                return handlePingtung(district);
            case "Taitung County":
                return handleTaitung(district);
            case "Hualien County":
                return handleHualien(district);
            case "Yilan County":
                return handleYilan(district);
            case "Penghu County":
                return handlePenghu(district);
            case "Kinmen County":
                return handleKinmen(district);
            case "Lienchiang County":
                return handleLienchiang(district);
            case "Nanhai":
                if (district.equals("Dongsha Islands")) {
                    return "817";
                } else {
                    return "819";
                }
            default:
                return "";
        }
    }

    private String handleKeelung(String district) {
        switch (district) {
            case "Ren’ai District":
            case "Xinyi District":
            case "Zhongshan District":
                return "200";
            case "Zhongzheng District":
                return "201";
            case "Anle District":
                return "203";
            case "Nuannuan District":
                return "205";
            case "Qidu District":
                return "206";
            default:
                return "";
        }
    }

    private String handleTaipei(String district) {
        switch (district) {
            case "Zhongzheng District":
                return "100";
            case "Datong District":
                return "103";
            case "Zhongshan District":
                return "104";
            case "Songshan District":
                return "105";
            case "Da’an District":
                return "106";
            case "Wanhua District":
                return "108";
            case "Xinyi District":
                return "110";
            case "Shilin District":
                return "111";
            case "Beitou District":
                return "112";
            case "Neihu District":
                return "114";
            case "Nangang District":
                return "115";
            case "Wenshan District":
                return "116";
            default:
                return "";
        }
    }

    private String handleNewTaipei(String district) {
        switch (district) {
            case "Wanli District":
                return "207";
            case "Jinshan District":
                return "208";
            case "Banqiao District":
                return "220";
            case "Xizhi District":
                return "221";
            case "Shenkeng District":
                return "222";
            case "Shiding District":
                return "223";
            case "Ruifang District":
                return "224";
            case "Pingxi District":
                return "226";
            case "Shuangxi District":
                return "227";
            case "Gongliao District":
                return "228";
            case "Xindian District":
                return "231";
            case "Pinglin District":
                return "232";
            case "Wulai District":
                return "233";
            case "Yonghe District":
                return "234";
            case "Zhonghe District":
                return "235";
            case "Tucheng District":
                return "236";
            case "Sanxia District":
                return "237";
            case "Shulin District":
                return "238";
            case "Yingge District":
                return "239";
            case "Sanchong District":
                return "241";
            case "Xinzhuang District":
                return "242";
            case "Taishan District":
                return "243";
            case "Linkou District":
                return "244";
            case "Luzhou District":
                return "247";
            case "Wugu District":
                return "248";
            case "Bali District":
                return "249";
            case "Tamsui District":
                return "251";
            case "Sanzhi District":
                return "252";
            case "Shimen District":
                return "253";
            default:
                return "";
        }
    }

    private String handleTaoyuan(String district) {
        switch (district) {
            case "Zhongli District":
                return "320";
            case "Pingzhen District":
                return "324";
            case "Longtan District":
                return "325";
            case "Yangmei District":
                return "326";
            case "Xinwu District":
                return "327";
            case "Guanyin District":
                return "328";
            case "Taoyuan District":
                return "330";
            case "Guishan District":
                return "333";
            case "Bade District":
                return "334";
            case "Daxi District":
                return "335";
            case "Fuxing District":
                return "336";
            case "Dayuan District":
                return "337";
            case "Luzhu District":
                return "338";
            default:
                return "";
        }
    }

    private String handleHsinchu(String district) {
        switch (district) {
            case "Zhubei City":
                return "302";
            case "Hukou Township":
                return "303";
            case "Xinfeng Township":
                return "304";
            case "Xinpu Township":
                return "305";
            case "Guanxi Township":
                return "306";
            case "Qionglin Township":
                return "307";
            case "Baoshan Township":
                return "300";
            case "Zhudong Township":
                return "310";
            case "Wufeng Township":
                return "311";
            case "Hengshan Township":
                return "312";
            case "Jianshi Township":
                return "313";
            case "Beipu Township":
                return "314";
            case "Emei Township":
                return "315";
            default:
                return "";
        }
    }

    private String handleMiaoli(String district) {
        switch (district) {
            case "Zhunan Township":
            case "Toufen City":
                return "350";
            case "Sanwan Township":
                return "352";
            case "Nanzhuang Township":
                return "353";
            case "Shitan Township":
                return "354";
            case "Houlong Township":
                return "356";
            case "Tongxiao Township":
                return "357";
            case "Yuanli Township":
                return "358";
            case "Miaoli City":
                return "360";
            case "Zaoqiao Township":
                return "361";
            case "Touwu Township":
                return "362";
            case "Gongguan Township":
                return "363";
            case "Dahu Township":
                return "364";
            case "Tai’an Township":
                return "365";
            case "Tongluo Township":
                return "366";
            case "Sanyi Township":
                return "367";
            case "Xihu Township":
                return "368";
            case "Zhuolan Township":
                return "369";
            default:
                return "";
        }
    }

    private String handleTaichung(String district) {
        switch (district) {
            case "Central District":
                return "400";
            case "East District":
                return "401";
            case "South District":
                return "402";
            case "West District":
                return "403";
            case "North District":
                return "404";
            case "Beitun District":
                return "406";
            case "Xitun District":
                return "407";
            case "Nantun District":
                return "408";
            case "Taiping District":
                return "411";
            case "Dali District":
                return "412";
            case "Wufeng District":
                return "413";
            case "Wuri District":
                return "414";
            case "Fengyuan District":
                return "420";
            case "Houli District":
                return "421";
            case "Shigang District":
                return "422";
            case "Dongshi District":
                return "423";
            case "Heping District":
                return "424";
            case "Xinshe District":
                return "426";
            case "Tanzi District":
                return "427";
            case "Daya District":
                return "428";
            case "Shengang District":
                return "429";
            case "Dadu District":
                return "432";
            case "Shalu District":
                return "433";
            case "Longjing District":
                return "434";
            case "Wuqi District":
                return "435";
            case "Qingshui District":
                return "436";
            case "Dajia District":
                return "437";
            case "Waipu District":
                return "438";
            case "Da'an District":
                return "439";
            default:
                return "";
        }
    }

    private String handleChanghua(String district) {
        switch (district) {
            case "Changhua City":
                return "500";
            case "Fenyuan Township":
                return "502";
            case "Huatan Township":
                return "503";
            case "Xiushui Township":
                return "504";
            case "Lukang Township":
                return "505";
            case "Fuxing Township":
                return "506";
            case "Xianxi Township":
                return "507";
            case "Hemei Township":
                return "508";
            case "Shengang Township":
                return "509";
            case "Yuanlin City":
                return "510";
            case "Shetou Township":
                return "511";
            case "Yongjing Township":
                return "512";
            case "Puxin Township":
                return "513";
            case "Xihu Township":
                return "514";
            case "Dacun Township":
                return "515";
            case "Puyan Township":
                return "516";
            case "Tianzhong Township":
                return "520";
            case "Beidou Township":
                return "521";
            case "Tianwei Township":
                return "522";
            case "Pitou Township":
                return "523";
            case "Xizhou Township":
                return "524";
            case "Zhutang Township":
                return "525";
            case "Erlin Township":
                return "526";
            case "Dacheng Township":
                return "527";
            case "Fangyuan Township":
                return "528";
            case "Ershui Township":
                return "530";
            default:
                return "";
        }
    }

    private String handleNantou(String district) {
        switch (district) {
            case "Nantou City":
                return "540";
            case "Zhongliao Township":
                return "541";
            case "Caotun Township":
                return "542";
            case "Guoxing Township":
                return "544";
            case "Puli Township":
                return "545";
            case "Ren’ai Township":
                return "546";
            case "Mingjian Township":
                return "551";
            case "Jiji Township":
                return "552";
            case "Shuili Township":
                return "553";
            case "Yuchi Township":
                return "555";
            case "Xinyi Township":
                return "556";
            case "Zhushan Township":
                return "557";
            case "Lugu Township":
                return "558";
            default:
                return "";
        }
    }

    private String handleYunlin(String district) {
        switch (district) {
            case "Dounan Township":
                return "630";
            case "Dapi Township":
                return "631";
            case "Huwei Township":
                return "632";
            case "Tuku Township":
                return "633";
            case "Baozhong Township":
                return "634";
            case "Dongshi Township":
                return "635";
            case "Taixi Township":
                return "636";
            case "Lunbei Township":
                return "637";
            case "Mailiao Township":
                return "638";
            case "Douliu City":
                return "640";
            case "Linnei Township":
                return "643";
            case "Gukeng Township":
                return "646";
            case "Citong Township":
                return "647";
            case "Xiluo Township":
                return "648";
            case "Erlun Township":
                return "649";
            case "Beigang Township":
                return "651";
            case "Shuilin Township":
                return "652";
            case "Kouhu Township":
                return "653";
            case "Sihu Township":
                return "654";
            case "Yuanchang Township":
                return "655";
            default:
                return "";
        }
    }

    private String handleChiayi(String district) {
        switch (district) {
            case "Fanlu Township":
                return "602";
            case "Meishan Township":
                return "603";
            case "Zhuqi Township":
                return "604";
            case "Alishan Township":
                return "605";
            case "Zhongpu Township":
                return "606";
            case "Dapu Township":
                return "607";
            case "Shuishang Township":
                return "608";
            case "Lucao Township":
                return "611";
            case "Taibao City":
                return "612";
            case "Puzi City":
                return "613";
            case "Dongshi Township":
                return "614";
            case "Liujiao Township":
                return "615";
            case "Xingang Township":
                return "616";
            case "Minxiong Township":
                return "621";
            case "Dalin Township":
                return "622";
            case "Xikou Township":
                return "623";
            case "Yizhu Township":
                return "624";
            case "Budai Township":
                return "625";
            default:
                return "";
        }
    }

    private String handleTainan(String district) {
        switch (district) {
            case "West Central District":
                return "700";
            case "East District":
                return "701";
            case "South District":
                return "702";
            case "North District":
                return "704";
            case "Anping District":
                return "708";
            case "Annan District":
                return "709";
            case "Yongkang District":
                return "710";
            case "Guiren District":
                return "711";
            case "Xinhua District":
                return "712";
            case "Zuozhen District":
                return "713";
            case "Yujing District":
                return "714";
            case "Nanxi District":
                return "715";
            case "Nanhua District":
                return "716";
            case "Rende District":
                return "717";
            case "Guanmiao District":
                return "718";
            case "Longqi District":
                return "719";
            case "Guantian District":
                return "720";
            case "Madou District":
                return "721";
            case "Jiali District":
                return "722";
            case "Xigang District":
                return "723";
            case "Qigu District":
                return "724";
            case "Jiangjun District":
                return "725";
            case "Xuejia District":
                return "726";
            case "Beimen District":
                return "727";
            case "Xinying District":
                return "730";
            case "Houbi District":
                return "731";
            case "Baihe District":
                return "732";
            case "Dongshan District":
                return "733";
            case "Liujia District":
                return "734";
            case "Xiaying District":
                return "735";
            case "Liuying District":
                return "736";
            case "Yanshui District":
                return "737";
            case "Shanhua District":
                return "741";
            case "Danei District":
                return "742";
            case "Shanshang District":
                return "743";
            case "Xinshi District":
                return "744";
            case "Anding District":
                return "745";
            default:
                return "";
        }
    }

    private String handleKaohsiung(String district) {
        switch (district) {
            case "Xinxing District":
                return "800";
            case "Qianjin District":
                return "801";
            case "Lingya District":
                return "802";
            case "Yancheng District":
                return "803";
            case "Gushan District":
                return "804";
            case "Qijin District":
                return "805";
            case "Qianzhen District":
                return "806";
            case "Sanmin District":
                return "407";
            case "Nanzi District":
                return "811";
            case "Xiaogang District":
                return "812";
            case "Zuoying District":
                return "813";
            case "Renwu District":
                return "814";
            case "Dashe District":
                return "815";
            case "Gangshan District":
                return "820";
            case "Luzhu District":
                return "821";
            case "Alian District":
                return "822";
            case "Tianliao District":
                return "823";
            case "Yanchao District":
                return "820";
            case "Qiaotou District":
                return "825";
            case "Ziguan District":
                return "825";
            case "Mituo District":
                return "827";
            case "Yong’an District":
                return "828";
            case "Hunei District":
                return "829";
            case "Fengshan District":
                return "830";
            case "Daliao District":
                return "831";
            case "Linyuan District":
                return "832";
            case "Niaosong District":
                return "833";
            case "Dashu District":
                return "840";
            case "Qishan District":
                return "842";
            case "Meinong District":
                return "843";
            case "Liugui District":
                return "844";
            case "Neimen District":
                return "845";
            case "Shanlin District":
                return "846";
            case "Jiaxian District":
                return "847";
            case "Taoyuan District":
                return "848";
            case "Namaxia District":
                return "849";
            case "Maolin District":
                return "851";
            case "Qieding District":
                return "852";
            default:
                return "";
        }
    }

    private String handlePingtung(String district) {
        switch (district) {
            case "Pingtung City":
                return "900";
            case "Sandimen Township":
                return "901";
            case "Wutai Township":
                return "902";
            case "Majia Township":
                return "903";
            case "Jiuru Township":
                return "904";
            case "Ligang Township":
                return "905";
            case "Gaoshu Township":
                return "906";
            case "Yanpu Township":
                return "907";
            case "Changzhi Township":
                return "908";
            case "Linluo Township":
                return "909";
            case "Zhutian Township":
                return "911";
            case "Neipu Township":
                return "912";
            case "Wandan Township":
                return "913";
            case "Chaozhou Township":
                return "920";
            case "Taiwu Township":
                return "921";
            case "Laiyi Township":
                return "922";
            case "Wanluan Township":
                return "923";
            case "Kanding Township":
                return "924";
            case "Xinpi Township":
                return "925";
            case "Nanzhou Township":
                return "926";
            case "Linbian Township":
                return "927";
            case "Donggang Township":
                return "928";
            case "Liuqiu Township":
                return "929";
            case "Jiadong Township":
                return "931";
            case "Xinyuan Township":
                return "932";
            case "Fangliao Township":
                return "940";
            case "Fangshan Township":
                return "941";
            case "Chunri Township":
                return "942";
            case "Shizi Township":
                return "943";
            case "Checheng Township":
                return "944";
            case "Mudan Township":
                return "945";
            case "Hengchun Township":
                return "946";
            case "Manzhou Township":
                return "947";
            default:
                return "";
        }
    }

    private String handleTaitung(String district) {
        switch (district) {
            case "Taitung City":
                return "950";
            case "Ludao Township":
                return "951";
            case "Lanyu Township":
                return "952";
            case "Yanping Township":
                return "953";
            case "Beinan Township":
                return "954";
            case "Luye Township":
                return "955";
            case "Guanshan Township":
                return "956";
            case "Haiduan Township":
                return "957";
            case "Chishang Township":
                return "958";
            case "Donghe Township":
                return "959";
            case "Chenggong Township":
                return "961";
            case "Changbin Township":
                return "962";
            case "Taimali Township":
                return "963";
            case "Jinfeng Township":
                return "964";
            case "Dawu Township":
                return "965";
            case "Daren Township":
                return "966";
            default:
                return "";
        }
    }

    private String handleHualien(String district) {
        switch (district) {
            case "Hualien City":
                return "970";
            case "Xincheng Township":
                return "971";
            case "Xiulin Township":
                return "972";
            case "Ji’an Township":
                return "973";
            case "Shoufeng Township":
                return "974";
            case "Fenglin Township":
                return "975";
            case "Guangfu Township":
                return "976";
            case "Fengbin Township":
                return "977";
            case "Ruisui Township":
                return "978";
            case "Wanrong Township":
                return "979";
            case "Yuli Township":
                return "981";
            case "Zhuoxi Township":
                return "982";
            case "Fuli Township":
                return "983";
            default:
                return "";
        }
    }

    private String handleYilan(String district) {
        switch (district) {
            case "Yilan City":
                return "260";
            case "Toucheng Township":
                return "261";
            case "Jiaoxi Township":
                return "262";
            case "Zhuangwei Township":
                return "263";
            case "Yuanshan Township":
                return "264";
            case "Luodong Township":
                return "265";
            case "Sanxing Township":
                return "266";
            case "Datong Township":
                return "267";
            case "Wujie Township":
                return "268";
            case "Dongshan Township":
                return "269";
            case "Su’ao Township":
                return "270";
            case "Nan’ao Township":
                return "272";
            case "Diauyutai":
                return "290";
            default:
                return "";
        }
    }

    private String handlePenghu(String district) {
        switch (district) {
            case "Magong City":
                return "880";
            case "Xiyu Township":
                return "881";
            case "Wang’an Township":
                return "882";
            case "Qimei Township":
                return "883";
            case "Baisha Township":
                return "884";
            case "Huxi Township":
                return "885";
            default:
                return "";
        }
    }

    private String handleKinmen(String district) {
        switch (district) {
            case "Jinsha Township":
                return "890";
            case "Jinhu Township":
                return "891";
            case "Jinning Township":
                return "892";
            case "Jincheng Township":
                return "893";
            case "Lieyu Township":
                return "894";
            case "Wuqiu Township":
                return "896";
            default:
                return "";
        }
    }

    private String handleLienchiang(String district) {
        switch (district) {
            case "Nangan Township":
                return "209";
            case "Beigan Township":
                return "210";
            case "Juguang Township":
                return "211";
            case "Dongyin Township":
                return "212";
            default:
                return "";
        }
    }
}
