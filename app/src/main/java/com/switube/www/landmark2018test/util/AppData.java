package com.switube.www.landmark2018test.util;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonObject;
import com.switube.www.landmark2018test.database.entity.MusicEntity;
import com.switube.www.landmark2018test.entity.ECarBonList;
import com.switube.www.landmark2018test.entity.ECarbon;
import com.switube.www.landmark2018test.entity.EClusterItem;
import com.switube.www.landmark2018test.entity.ECreateAttraction;
import com.switube.www.landmark2018test.entity.EEco;
import com.switube.www.landmark2018test.entity.EEcoList;
import com.switube.www.landmark2018test.entity.EEditComment;
import com.switube.www.landmark2018test.entity.EFeatures;
import com.switube.www.landmark2018test.entity.ESwapData;
import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.gson.GPlaceIdData;
import com.switube.www.landmark2018test.gson.GPlayer;
import com.switube.www.landmark2018test.gson.GStrokeList;
import com.switube.www.landmark2018test.service.callback.IFloatPlayerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppData {
    private List<GInfoData.Article> articleList = new ArrayList<>();
    public List<GInfoData.Article> getArticleList() {
        return articleList;
    }
    public void setArticleList(List<GInfoData.Article> articleList) {
        this.articleList = articleList;
    }

    private String selectedTag = "";
    public void setSelectedTag(String selectedTag) {
        this.selectedTag = selectedTag;
    }
    public String getSelectedTag() {
        return selectedTag;
    }

    private String artid = "";
    public void setArtid(String artid) {
        this.artid = artid;
    }
    public String getArtid() {
        return artid;
    }

    private List<GInfoData.Message> messageList = new ArrayList<>();
    public void setMessageList(List<GInfoData.Message> messageList) {
        this.messageList = messageList;
    }
    public List<GInfoData.Message> getMessageList() {
        return messageList;
    }

    private String maid = "";
    private String wsid = "";
    private String type = "";
    private String privacy = "";
    public void setPersonalData(String maid, String wsid, String type, String privacy) {
        this.maid = maid;
        this.wsid = wsid;
        this.type = type;
        this.privacy = privacy;
    }
    public String getMaid() {
        return maid;
    }
    public String getWsid() {
        return wsid;
    }
    public String getType() {
        return type;
    }
    public String getPrivacy() {
        return privacy;
    }

    private List<String> photoList = new ArrayList<>();
    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
    }
    public List<String> getPhotoList() {
        return photoList;
    }

    private String attractionName = "";
    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }
    public String getAttractionName() {
        return attractionName;
    }

    private boolean isCreateNewAttractionFailure = false;
    public void setCreateNewAttractionFailure(boolean createNewAttractionFailure) {
        isCreateNewAttractionFailure = createNewAttractionFailure;
    }
    public boolean isCreateNewAttractionFailure() {
        return isCreateNewAttractionFailure;
    }

    private boolean isFromPersonalSteaming = false;
    public void setFromPersonalSteaming(boolean fromPersonalSteaming) {
        isFromPersonalSteaming = fromPersonalSteaming;
    }
    public boolean isFromPersonalSteaming() {
        return isFromPersonalSteaming;
    }

    private boolean isFromSearchAttraction = false;
    public void setFromSearchAttraction(boolean fromSearchAttraction) {
        isFromSearchAttraction = fromSearchAttraction;
    }
    public boolean isFromSearchAttraction() {
        return isFromSearchAttraction;
    }

    //從哪進入播放頁
    private int musicMode = 0;
    public void setMusicMode(int musicMode) {
        this.musicMode = musicMode;
    }
    public int getMusicMode() {
        return musicMode;
    }

    //行動音樂的類型ＩＤ
    private String taid = "";
    public void setTaid(String taid) {
        this.taid = taid;
    }
    public String getTaid() {
        return taid;
    }

    //從搜尋頁面返回push行動頁面所需的value
    private String value = "";
    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    //創建地標時避免換頁後清空已輸入的網址
    private String tubeUrl = "";
    public void setTubeUrl(String tubeUrl) {
        this.tubeUrl = tubeUrl;
    }
    public String getTubeUrl() {
        return tubeUrl;
    }

    //所選擇的圖片
    private List<String> selectedPhotos = new ArrayList<>();
    public void setSelectedPhotos(List<String> selectedPhotos) {
        this.selectedPhotos = selectedPhotos;
    }
    public List<String> getSelectedPhotos() {
        return selectedPhotos;
    }

    //進播放頁前所選擇的頻道ＩＤ
    private String webId = "";
    public void setWebId(String webId) {
        this.webId = webId;
    }
    public String getWebId() {
        return webId;
    }

    //進播放頁前所選擇的頻道名稱
    private String channelName = "";
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    public String getChannelName() {
        return channelName;
    }

    //目前播放的所有曲目清單
    private List<MusicEntity> musicEntities = new ArrayList<>();
    public void setMusicEntities(List<MusicEntity> musicEntities) {
        this.musicEntities = musicEntities;
    }
    public List<MusicEntity> getMusicEntities() {
        return musicEntities;
    }

    //懸浮播放的interface
    private IFloatPlayerService iFloatPlayerService = null;
    public void setiFloatPlayerService(IFloatPlayerService iFloatPlayerService) {
        this.iFloatPlayerService = iFloatPlayerService;
    }
    public IFloatPlayerService getiFloatPlayerService() {
        return iFloatPlayerService;
    }

    //現正是否播放中
    private boolean isPlaying = false;
    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
    public boolean isPlaying() {
        return isPlaying;
    }

    //現正播放曲目
    private int playingIndex = 0;
    public void setPlayingIndex(int playingIndex) {
        this.playingIndex = playingIndex;
    }
    public int getPlayingIndex() {
        return playingIndex;
    }

    //播放頁該顯示滑動頁面還是歌曲清單
    private boolean isSlide = true;
    public void setSlide(boolean slide) {
        isSlide = slide;
    }
    public boolean isSlide() {
        return isSlide;
    }

    //從伺服器取得的播放頁所有資料
    private GPlayer gPlayer;
    public void setgPlayer(GPlayer gPlayer) {
        this.gPlayer = gPlayer;
    }
    public GPlayer getgPlayer() {
        return gPlayer;
    }

    //播放頁滑動胖豆的模式
    private int slideMode = 0;
    public void setSlideMode(int slideMode) {
        this.slideMode = slideMode;
    }
    public int getSlideMode() {
        return slideMode;
    }

    //播放頁滑動胖豆的滑動邊界
    private int rangeWith = 0;
    private int rangeHeight = 0;
    public void setRange(int rangeWith, int rangeHeight) {
        this.rangeWith = rangeWith;
        this.rangeHeight = rangeHeight;
    }
    public int getRangeWith() {
        return rangeWith;
    }
    public int getRangeHeight() {
        return rangeHeight;
    }

    //播放頁滑動胖豆的所在區域
    private int areaX = 0;
    private int areaY = 0;
    public void setAreaX(int areaX) {
        this.areaX = areaX;
    }
    public void setAreaY(int areaY) {
        this.areaY = areaY;
    }
    public int getAreaX() {
        return areaX;
    }
    public int getAreaY() {
        return areaY;
    }

    //播放頁滑動胖豆所在座標
    private int pointX = 0;
    private int pointY = 0;
    public void setPointX(int pointX) {
        this.pointX = pointX;
    }
    public void setPointY(int pointY) {
        this.pointY = pointY;
    }
    public int getPointX() {
        return pointX;
    }
    public int getPointY() {
        return pointY;
    }

    //是否可刷新播放清單
    private boolean canRefreshList = false;
    public void setCanRefreshList(boolean canRefreshList) {
        this.canRefreshList = canRefreshList;
    }
    public boolean isCanRefreshList() {
        return canRefreshList;
    }

    //播放頁播放模式
    private boolean isRandom = false;
    public void setRandom(boolean random) {
        isRandom = random;
    }
    public boolean isRandom() {
        return isRandom;
    }

    //目前頁面是否為播放頁面
    private boolean isPlayerPage = false;
    public void setPlayerPage(boolean playerPage) {
        isPlayerPage = playerPage;
    }
    public boolean isPlayerPage() {
        return isPlayerPage;
    }

    //目前是否為清單頁面
    private boolean isPlaylist = false;
    public void setPlaylist(boolean playlist) {
        isPlaylist = playlist;
    }
    public boolean isPlaylist() {
        return isPlaylist;
    }

    //播放頁開啟外連留言頁所需資料
    private String phpName = "";
    public void setPhpName(String phpName) {
        this.phpName = phpName;
    }
    public String getPhpName() {
        return phpName;
    }

    //是否由音樂目錄進入播放頁
    private boolean isFromMobileMusic = false;
    public void setFromMobileMusic(boolean fromMobileMusic) {
        isFromMobileMusic = fromMobileMusic;
    }
    public boolean isFromMobileMusic() {
        return isFromMobileMusic;
    }

    //播放頁是否被加入收藏
    private boolean isLove = false;
    public void setLove(boolean love) {
        isLove = love;
    }
    public boolean isLove() {
        return isLove;
    }

    //需要自動更換胖豆位置
    private boolean needAuto = false;
    public void setNeedAuto(boolean needAuto) {
        this.needAuto = needAuto;
    }
    public boolean isNeedAuto() {
        return needAuto;
    }

    //胖豆大小
    private int slideSize = 0;
    public void setSlideSize(int slideSize) {
        this.slideSize = slideSize;
    }
    public int getSlideSize() {
        return slideSize;
    }

    /**
     * 快捷鍵模式
     * 0 ==> 預設
     * 1 ==> 行動音樂
     * 2 ==> 行程
     */
    private int hotKeyMode = 1;
    public void setHotKeyMode(int hotKeyMode) {
        this.hotKeyMode = hotKeyMode;
    }
    public int getHotKeyMode() {
        return hotKeyMode;
    }

    //文字模式或者圖片模式
    private boolean isPhotoMode = true;
    public void setPhotoMode(boolean photoMode) {
        isPhotoMode = photoMode;
    }
    public boolean isPhotoMode() {
        return isPhotoMode;
    }

    //是否由小螢幕返回播放頁
    private boolean isBackWithFloat = false;
    public void setBackWithFloat(boolean backWithFloat) {
        isBackWithFloat = backWithFloat;
    }
    public boolean isBackWithFloat() {
        return isBackWithFloat;
    }

    //map zoom size {
    private int zoomSize = 15;
    public void setZoomSize(int zoomSize) {
        this.zoomSize = zoomSize;
    }
    public int getZoomSize() {
        return zoomSize;
    }

    //播放器是否為第一次播放
    private boolean isFirstPlaying = true;
    public void setFirstPlaying(boolean firstPlaying) {
        isFirstPlaying = firstPlaying;
    }
    public boolean isFirstPlaying() {
        return isFirstPlaying;
    }

    //播放頁切換推薦頻道時紀錄切換前頻道資料
    private GPlayer.Push push = new GPlayer.Push();
    public void setPush(GPlayer.Push push) {
        this.push = push;
    }
    public GPlayer.Push getPush() {
        return push;
    }

    private GPlayer.Push pushBuffer = new GPlayer.Push();
    public void setPushBuffer(GPlayer.Push pushBuffer) {
        this.pushBuffer = pushBuffer;
    }
    public GPlayer.Push getPushBuffer() {
        return pushBuffer;
    }

    //是否點擊播放頁的推薦頻道
    private boolean isPush = false;
    public void setPush(boolean push) {
        isPush = push;
    }
    public boolean isPush() {
        return isPush;
    }

    private String menuId = "";
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    public String getMenuId() {
        return menuId;
    }

    //是否從收藏頁面進入播放頁面
    private boolean isFromFavorite = false;
    public void setFromFavorite(boolean fromFavorite) {
        isFromFavorite = fromFavorite;
    }
    public boolean isFromFavorite() {
        return isFromFavorite;
    }

    //所選的行程類型
    private String raid = "";
    public void setRaid(String raid) {
        this.raid = raid;
    }
    public String getRaid() {
        return raid;
    }

    //行程模式
    private int strokeMode = 0;
    public void setStrokeMode(int strokeMode) {
        this.strokeMode = strokeMode;
    }
    public int getStrokeMode() {
        return strokeMode;
    }

    private boolean needScrollToTop = false;
    public void setNeedScrollToTop(boolean needScrollToTop) {
        this.needScrollToTop = needScrollToTop;
    }
    public boolean isNeedScrollToTop() {
        return needScrollToTop;
    }

    //urid
    private String urid = "";
    public void setUrid(String urid) {
        this.urid = urid;
    }
    public String getUrid() {
        return urid;
    }

    //對應urid的title
    private String titleForUrid = "";
    public void setTitleForUrid(String titleForUrid) {
        this.titleForUrid = titleForUrid;
    }
    public String getTitleForUrid() {
        return titleForUrid;
    }

    private boolean isNormalMap = true;
    public void setNormalMap(boolean normalMap) {
        isNormalMap = normalMap;
    }
    public boolean isNormalMap() {
        return isNormalMap;
    }

    private GStrokeList gStrokeList;
    public void setgStrokeList(GStrokeList gStrokeList) {
        this.gStrokeList = gStrokeList;
    }
    public GStrokeList getgStrokeList() {
        return gStrokeList;
    }

    private boolean isFromMapToPersonal = false;
    public void setFromMapToPersonal(boolean fromMapToPersonal) {
        isFromMapToPersonal = fromMapToPersonal;
    }
    public boolean isFromMapToPersonal() {
        return isFromMapToPersonal;
    }

    private boolean isDefaultHotKye = true;
    public void setDefaultHotKye(boolean defaultHotKye) {
        isDefaultHotKye = defaultHotKye;
    }
    public boolean isDefaultHotKye() {
        return isDefaultHotKye;
    }

    private GPlaceIdData gPlaceIdData;
    public void setgPlaceIdData(GPlaceIdData gPlaceIdData) {
        this.gPlaceIdData = gPlaceIdData;
    }
    public GPlaceIdData getgPlaceIdData() {
        return gPlaceIdData;
    }

    private List<ESwapData> eSwapDataList = new ArrayList<>();
    public void seteSwapDataList(List<ESwapData> eSwapDataList) {
        this.eSwapDataList = eSwapDataList;
    }
    public List<ESwapData> geteSwapDataList() {
        return eSwapDataList;
    }

    private List<ESwapData> eSwapDataListForCollect = new ArrayList<>();
    public void seteSwapDataListForCollect(List<ESwapData> eSwapDataListForCollect) {
        this.eSwapDataListForCollect = eSwapDataListForCollect;
    }
    public List<ESwapData> geteSwapDataListForCollect() {
        return eSwapDataListForCollect;
    }

    //公版地圖地標
    private List<EClusterItem> globalClusterItemList = new ArrayList<>();
    public void setGlobalClusterItemList(List<EClusterItem> globalClusterItemList) {
        this.globalClusterItemList = globalClusterItemList;
    }
    public List<EClusterItem> getGlobalClusterItemList() {
        return globalClusterItemList;
    }

    //紀錄是否按下定位鈕
    private boolean isFocus = false;
    public void setFocus(boolean focus) {
        isFocus = focus;
    }
    public boolean isFocus() {
        return isFocus;
    }

    //是否為我的收藏頁面
    private boolean isCollectionPage = false;
    public void setCollectionPage(boolean collectionPage) {
        isCollectionPage = collectionPage;
    }
    public boolean isCollectionPage() {
        return isCollectionPage;
    }

    //是否使用搜尋地標紀錄
    private boolean isUsingSettingData = false;
    public void setUsingSettingData(boolean usingSettingData) {
        isUsingSettingData = usingSettingData;
    }
    public boolean isUsingSettingData() {
        return isUsingSettingData;
    }

    //搜尋地標設定頁面，所選的類別記錄
    private List<EFeatures> eFeaturesListForSetting = new ArrayList<>();
    private int distanceForSetting = 0;
    public void seteFeaturesListForSetting(List<EFeatures> eFeaturesListForSetting) {
        this.eFeaturesListForSetting = eFeaturesListForSetting;
    }
    public List<EFeatures> geteFeaturesListForSetting() {
        return eFeaturesListForSetting;
    }
    public void setDistanceForSetting(int distanceForSetting) {
        this.distanceForSetting = distanceForSetting;
    }
    public int getDistanceForSetting() {
        return distanceForSetting;
    }

    //msid for show features page
    private String msid = "";
    public void setMsid(String msid) {
        this.msid = msid;
    }
    public String getMsid() {
        return msid;
    }
    //mscid for show features page
    private String mscid = "";
    public void setMscid(String mscid) {
        this.mscid = mscid;
    }
    public String getMscid() {
        return mscid;
    }
    //mtid list for show features page
    private List<String> mtidList = new ArrayList<>();
    public void setMtidList(List<String> mtidList) {
        this.mtidList = mtidList;
    }
    public List<String> getMtidList() {
        return mtidList;
    }
    //mstid list for show features page
    private List<String> mstidList = new ArrayList<>();
    public void setMstidList(List<String> mstidList) {
        this.mstidList = mstidList;
    }
    public List<String> getMstidList() {
        return mstidList;
    }

    //紀錄地標資訊頁的資料
    private GInfoData gInfoData;
    public void setgInfoData(GInfoData gInfoData) {
        this.gInfoData = gInfoData;
    }
    public GInfoData getgInfoData() {
        return gInfoData;
    }

    //edit attraction page style
    private String style = "";
    public void setStyle(String style) {
        this.style = style;
    }
    public String getStyle() {
        return style;
    }

    //編輯地標頁面，有變動的項目
    private List<Boolean> isEdit = new ArrayList<>();
    public void setIsEdit(List<Boolean> isEdit) {
        this.isEdit = isEdit;
    }
    public List<Boolean> getIsEdit() {
        return isEdit;
    }
    //是否由編輯地標頁面進入時間編輯，並返回
    private boolean isFromEditAttraction = false;
    public void setFromEditAttraction(boolean fromEditAttraction) {
        isFromEditAttraction = fromEditAttraction;
    }
    public boolean isFromEditAttraction() {
        return isFromEditAttraction;
    }
    //紀錄來自地標編輯的時間更動
    private ECreateAttraction.Time time;
    public void setTime(ECreateAttraction.Time time) {
        this.time = time;
    }
    public ECreateAttraction.Time getTime() {
        return time;
    }
    //是否由地標編輯頁進入類別選擇
    private boolean isFormEditAttractionForType = false;
    public void setFormEditAttractionForType(boolean formEditAttractionForType) {
        isFormEditAttractionForType = formEditAttractionForType;
    }
    public boolean isFormEditAttractionForType() {
        return isFormEditAttractionForType;
    }

    //紀錄更動地址時新地址與衛星定位座標以及地標ＩＤ和導航用網址
    private JsonObject address;
    public void setAddress(JsonObject address) {
        this.address = address;
    }
    public JsonObject getAddress() {
        return address;
    }
    //紀錄更動地址時，新地址的所在城市
    private JsonObject city;
    public void setCity(JsonObject city) {
        this.city = city;
    }
    public JsonObject getCity() {
        return city;
    }
    //紀錄更動地址時，新地址的地點名稱
    private Map<String, String> name = new HashMap<>();
    public void setName(Map<String, String> name) {
        this.name = name;
    }
    public Map<String, String> getName() {
        return name;
    }

    //是否來自我的收藏
    private boolean isFromMyCollection = false;
    public void setFromMyCollection(boolean fromMyCollection) {
        isFromMyCollection = fromMyCollection;
    }
    public boolean isFromMyCollection() {
        return isFromMyCollection;
    }

    //是否來自stroke attraction list
    private boolean isFromStrokeAttractionList = false;
    public void setFromStrokeAttractionList(boolean fromStrokeAttractionList) {
        isFromStrokeAttractionList = fromStrokeAttractionList;
    }
    public boolean isFromStrokeAttractionList() {
        return isFromStrokeAttractionList;
    }

    //是否來自Stroke
    private boolean isFromStroke = false;
    public void setFromStroke(boolean fromStroke) {
        isFromStroke = fromStroke;
    }
    public boolean isFromStroke() {
        return isFromStroke;
    }

    //是否為編輯模式
    private boolean isEditMode = false;
    public void setEditMode(boolean editMode) {
        isEditMode = editMode;
    }
    public boolean isEditMode() {
        return isEditMode;
    }
    //是否為編輯留言模式
    private boolean isEditCommentMode = false;
    public void setEditCommentMode(boolean editCommentMode) {
        isEditCommentMode = editCommentMode;
    }
    public boolean isEditCommentMode() {
        return isEditCommentMode;
    }
    //是否為編輯標籤模式
    private boolean isEditTagMode = false;
    public void setEditTagMode(boolean editTagMode) {
        isEditTagMode = editTagMode;
    }
    public boolean isEditTagMode() {
        return isEditTagMode;
    }
    //紀錄編輯資料
    private EEditComment eEditComment = null;
    public void seteEditComment(EEditComment eEditComment) {
        this.eEditComment = eEditComment;
    }
    public EEditComment geteEditComment() {
        return eEditComment;
    }

    //個人地圖是否可編輯
    private boolean canEditInPersonalMap = false;
    public void setCanEditInPersonalMap(boolean canEditInPersonalMap) {
        this.canEditInPersonalMap = canEditInPersonalMap;
    }
    public boolean isCanEditInPersonalMap() {
        return canEditInPersonalMap;
    }

    //是否來自地標串流頁
    private boolean isFromAttractionSteaming = false;
    public void setFromAttractionSteaming(boolean fromAttractionSteaming) {
        isFromAttractionSteaming = fromAttractionSteaming;
    }
    public boolean isFromAttractionSteaming() {
        return isFromAttractionSteaming;
    }

    //是否來自點擊地標資訊頁圖片
    private boolean isFromClickInfoHeadPhoto = false;
    public void setFromClickInfoHeadPhoto(boolean fromClickInfoHeadPhoto) {
        isFromClickInfoHeadPhoto = fromClickInfoHeadPhoto;
    }
    public boolean isFromClickInfoHeadPhoto() {
        return isFromClickInfoHeadPhoto;
    }

    //未登入狀態，側邊選單的紀錄
    private List<String> selectedMsid = new ArrayList<>();
    public void setSelectedMsid(List<String> selectedMsid) {
        this.selectedMsid = selectedMsid;
    }
    public List<String> getSelectedMsid() {
        return selectedMsid;
    }

    //紀錄打卡時所輸入的文字，送出打卡或者退出時清除
    private String message = "";
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    //紀錄狀態欄高度
    private int statusHeight = 0;
    public void setStatusHeight(int statusHeight) {
        this.statusHeight = statusHeight;
    }
    public int getStatusHeight() {
        return statusHeight;
    }

    //紀錄手機可使用的畫面高度
    private int useableHeight = 0;
    public void setUseableHeight(int useableHeight) {
        this.useableHeight = useableHeight;
    }
    public int getUseableHeight() {
        return useableHeight;
    }

    //手動放大地圖或者移動地圖後的座標
    private LatLng manualGPS = null;
    public void setManualGPS(LatLng manualGPS) {
        this.manualGPS = manualGPS;
    }
    public LatLng getManualGPS() {
        return manualGPS;
    }

    //是否手動移動地圖
    private boolean manualMode = false;
    public void setManualMode(boolean manualMode) {
        this.manualMode = manualMode;
    }
    public boolean isManualMode() {
        return manualMode;
    }

    //是否處在行動音樂模組
    private boolean mobileMusicMode = false;
    public void setMobileMusicMode(boolean mobileMusicMode) {
        this.mobileMusicMode = mobileMusicMode;
    }
    public boolean isMobileMusicMode() {
        return mobileMusicMode;
    }

    //是否從側邊選單點選快選模組
    private boolean isSideMenu = false;
    public void setSideMenu(boolean sideMenu) { isSideMenu = sideMenu; }
    public boolean isSideMenu() { return isSideMenu; }

    //是否來自錢包
    private boolean isFromWallet = false;
    public void setFromWallet(boolean fromWallet) { isFromWallet = fromWallet; }
    public boolean isFromWallet() { return isFromWallet; }

    //碳足跡是否為啟動狀態
    private boolean isCarbon = false;
    public void setCarbon(boolean carbon) { isCarbon = carbon; }
    public boolean isCarbon() { return isCarbon; }

    //碳足跡數據
    private ArrayList<ECarbon> carbons = new ArrayList<>();
    public ArrayList<ECarbon> getCarbons() { return carbons; }

    //選中的碳足跡數據
    private ECarBonList eCarBonList = new ECarBonList();
    public void seteCarBonList(ECarBonList eCarBonList) { this.eCarBonList = eCarBonList; }
    public ECarBonList geteCarBonList() { return eCarBonList; }

    //eco是否為啟動狀態
    private boolean isEco = false;
    public void setEco(boolean eco) { isEco = eco; }
    public boolean isEco() { return isEco; }

    //eco data
    private ArrayList<EEco> eEcos = new ArrayList<>();
    public ArrayList<EEco> geteEcos() { return eEcos; }

    //selected eco data
    private EEcoList eEcoList = new EEcoList();
    public void seteEcoList(EEcoList eEcoList) { this.eEcoList = eEcoList; }
    public EEcoList geteEcoList() { return eEcoList; }
}
