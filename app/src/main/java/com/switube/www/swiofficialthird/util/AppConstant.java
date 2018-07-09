package com.switube.www.swiofficialthird.util;

public class AppConstant {
    //比對版本用
    public static final int VERSION = 4110;

    public static final String APP_INFO = "?appname=testapp";

    //取得menu list, language package, goods list, invite mission list
    public static final String NET_GET_APP_INFO = "GetAppInfoIOS_v1.php";
    //取得選中頁面中所有資訊
    public static final String NET_GET_MENU_CONTENT = "GetAppWebsiteIOS_v2.php";
    //新增頻道或移除頻道
    public static final String NET_SEND_CHANNEL_TO_CARD = "SendChannelToCard_v2.php";
    //註冊或登入帳號
    public static final String NET_SEND_MEMBER_INFO = "SendMemberInfo_v1.php";
    //送出欲登出的裝置
    public static final String NET_SEND_DEVICE_SIGN_OUT = "SendDeviceSignOut_v1.php";
    //從目前進度轉至完成
    public static final String NET_SEND_TRAIN_TO_COMPLETE = "SendTrainToComplete_v1.php";
    //傳送卡片購買或續購完成資訊到後台
    public static final String NET_SEND_GOODS_ORDER = "SendGoodsOrderIOS_v1.php";
    //傳送feedback到後台
    public static final String NET_SEND_FEEDBACK = "sendFeedBack_v2.php";
    //獲得所選頻道中所有影片
    public static final String NET_GET_YOUTUBE_LIST = "GetYoutubeListTravel_v6.php";
    //傳送曲目是否需要播放到後台
    public static final String NET_SEND_YOUTUBE_TO_CARD_LIST = "SendCardPlaybackStatusIOS_v1.php";
    //從後台取得學習進度中所選選項的內容
    public static final String NET_GET_APP_IMITATE = "GetAppImitateIOS_v1.php";
    //從後台取得自己的推薦碼以及fb推薦使用日期
    public static final String NET_GET_MEMBER_WSID = "GetMemberWsid_v1.php";
    //使用FB邀請好友之後，傳回後台以取得免費卡片
    public static final String NET_SEND_MEMBER_CARD_VOUCHER = "SendMemberCardVoucher_v1.php";
    //通知後台重發認證信
    public static final String NET_SEND_NEW_VERIFY_TO_MEMBER = "SendNewVerifyToMember_v1.php";
    //傳送目前影片進度到後台
    public static final String NET_SEND_CHANNEL_TO_TRAIN = "SendChannelToTrain_v2.php";
    //移除所選學習進度
    public static final String NET_SEND_TRAIN_TO_REMOVE = "SendTrainToRemove_v1.php";
    //關閉app時傳送關閉資訊到後台
    public static final String NET_SEND_APP_SHUTDOWN = "SendAPPShutDown_v1.php";
    //取得單人遊戲內容列表
    public static final String NET_GET_ALONE_GAME_MEMBER = "GetAloneGameMember_v1.php";
    //取得單人遊戲闖關關卡內容
    public static final String NET_GET_ALONE_GAME_LEVEL = "GetAloneGameLevel_v1.php";
    //取得所選關卡的頻道內容
    public static final String NET_GET_ALONE_GAME_LEVLE_CHANNEL = "GetAloneGameLevelChannel_v1.php";
    //更換闖關內容
    public static final String NET_GET_ALONE_GAME_LEVEL_CHANNEL_RESET = "GetAloneGameLevelChannelReset_v1.php";
    //回報已領取100%獎勵訊息
    public static final String NET_SEND_ALONE_GAME_LEVEL_FETTLE = "SendAloneGameLevelFettle_v1.php";
    //取得E Bike模板和景點列表
    public static final String NET_GET_TRAVEL_CLASS = "GetTravelClass_v1.php";
    //取得E Bike標籤明細列表
    public static final String NET_GET_TRAVEL_LABEL_DETAILS = "GetTravelLabelDetails_v1.php";
    //儲存E Bike旅行景點資訊
    public static final String NET_SEND_NEW_TRAVEL_INFO = "SendNewTravelInfo_v3.php";
    //儲存E Bike景點打卡留言和新標籤
    public static final String NET_SEND_NEW_TRAVEL_CHECK_IN = "SendNewTravelCheckin_v2.php";
    //儲存E Bike景點圖片
    public static final String NET_SEND_TRAVEL_IMAGE = "SendTravelImage_v2.php";
    //取得E Bike景點資訊列表
    public static final String NET_GET_TRAVEL_INFO_DETAIL = "GetTravelInfoDetails_v2.php";
    //儲存E Bike腳踏車旅行路徑
    public static final String NET_SEND_TRAVEL_PATH = "SendBikeTravelPath_v1.php";
    //儲存E Bike景點打卡留言和標籤點擊是否喜歡
    public static final String NET_SEND_TRAVEL_LIKE = "SendTravelLike_v2.php";
    //儲存E Bike景點評分
    public static final String NET_SEND_TRAVEL_RATIO = "SendTravelRatio_v1.php";
    //取得E Bike景點串流資訊
    public static final String GET_TRAVEL_CHECK_IN_DETAILS = "GetTravelCheckinDetails_v2.php";
    //取得個人串流頁資訊
    public static final String GET_TRAVEL_MEMBER_CHECKIN_DETAILS = "GetTravelMemberCheckinDetails_v2.php";
    //刪除已上傳圖片
    public static final String SEND_DEL_TRAVEL_IMAGE = "SendDelTravelImage_v1.php";
    //http://192.168.1.200

    public static final String SETTING_HOME_PAGE = "http://www.switube.com/album/SwiAPPs.php";
    //public static final String SETTING_HOME_PAGE = "https://www.switube.com/topic/Topic_CoEditor.php?appname=SwiTube&maid=fbA1S7KM94&serialid=RQ30026UD0&pattern=Sony_F3115&lang=tw";
    public static final String SETTING_TERMS_OF_USE = "http://www.switube.com/TermsOfUse.php";
    public static final String SETTING_PRIVACY = "http://www.switube.com/Privacy.php";
    public static final String SETTING_SHARE = "https://www.youtube.com/watch?v=";
    public static final String SETTING_SHARE_APP =
            "https://play.google.com/store/apps/details?id=com.swibeat.www.baseswibeatplayer";
    public static final String APP_URI = "market://details?id=com.swibeat.www.baseswibeatplayer";

    public static final String GUIDE_FACEBOOK = "https://m.facebook.com/SwiTubeTV";
    public static final String APP_PHOTO = "https://www.switube.com/mobile_switube/1200x628_Singer.jpg";

    public static final String DEFAULT_PICTURE = "https://i.ytimg.com/vi/aJYrpLbVZa8/maxresdefault.jpg";
}
