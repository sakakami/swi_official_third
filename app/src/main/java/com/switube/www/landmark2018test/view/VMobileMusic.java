package com.switube.www.landmark2018test.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.AMobileMusic;
import com.switube.www.landmark2018test.adapter.callback.IAMobileMusic;
import com.switube.www.landmark2018test.gson.GMusicRadio;
import com.switube.www.landmark2018test.gson.GPlayer;
import com.switube.www.landmark2018test.gson.GPushMusic;
import com.switube.www.landmark2018test.presenter.PMobileMusic;
import com.switube.www.landmark2018test.util.AlertDialogUtil;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.util.SignInUtil;
import com.switube.www.landmark2018test.view.callback.IFragmentBackHandler;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVMobileMusic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 * 0===>discovery list
 * 1===>music radio
 * 2===>my favorite
 */
public class VMobileMusic extends Fragment implements IAMobileMusic, IVMobileMusic, IFragmentBackHandler {
    @BindViews({R.id.textBackInMobileMusic, R.id.textTitleInMobileMusic})
    List<TextView> textViewList;
    @BindViews({R.id.imageSwitchInMobileMusic, R.id.imageSearchInMobileMusic})
    List<ImageView> imageViewList;
    @BindView(R.id.searchViewInMobileMusic)
    SearchView searchView;
    @BindView(R.id.recyclerInMobileMusic)
    RecyclerView recyclerView;
    @BindView(R.id.viewSearchInMobileMusic)
    View viewSearch;
    private PMobileMusic pMobileMusic;
    private Unbinder unbinder;
    private boolean isSearch = false;
    private boolean isFromDiscovery = false;
    private IMainActivity iMainActivity;
    private String channelName = "";
    private AMobileMusic aMobileMusic;
    private GMusicRadio gMusicRadio;
    private GPushMusic gPushMusic;

    public VMobileMusic() {
        pMobileMusic = new PMobileMusic(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_mobile_music, container, false);
        unbinder = ButterKnife.bind(this, view);
        switch (MyApplication.getAppData().getMusicMode()) {
            case 0:
                textViewList.get(1).setText(R.string.music_title_browse);
                pMobileMusic.getMusicRadioData(MyApplication.getAppData().getTaid(), false);
                break;
            case 1:
                textViewList.get(1).setText(R.string.music_title_you);
                pMobileMusic.handlePushMusic();
                break;
            case 2:
                textViewList.get(1).setText(R.string.music_title_library);
                if (MyApplication.getAppData().getTaid().length() > 0) {
                    pMobileMusic.getPushMusicData(MyApplication.getAppData().getTaid(), "", "collect");
                } else {
                    pMobileMusic.getMusicRadioData("", true);
                }
                break;
            default:
                break;
        }
        if (MyApplication.getAppData().isPhotoMode()) {
            imageViewList.get(0).setImageResource(R.drawable.list_s_v12);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.addItemDecoration(new ItemDecorationUtil(getContext(), 10, 0, 14, 0));
        } else {
            imageViewList.get(0).setImageResource(R.drawable.list_m_v11);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(new ItemDecorationUtil(getContext(), 10, 0, 0, 0));
        }
        aMobileMusic = new AMobileMusic(this);
        recyclerView.setAdapter(aMobileMusic);
        if (!SharePreferencesUtil.getInstance().getSharedPreferences().getBoolean("checkPermission", false)) {
            iMainActivity.getPermission();
        }
        RxView.clicks(textViewList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (MyApplication.getAppData().getMusicMode() == 1) {
                            if (isFromDiscovery) {
                                if (isSearch) {
                                    isSearch = false;
                                    textViewList.get(1).setText(channelName);
                                    pMobileMusic.getPushMusicData(MyApplication.getAppData().getTaid(), MyApplication.getAppData().getValue(), "channel");
                                } else {
                                    isFromDiscovery = false;
                                    textViewList.get(1).setText(R.string.music_title_browse);
                                    MyApplication.getAppData().setMusicMode(0);
                                    pMobileMusic.getMusicRadioData(MyApplication.getAppData().getTaid(), false);
                                }
                            } else {
                                getFragmentManager().popBackStack();
                            }
                        } else {
                            if (isSearch) {
                                isSearch = false;
                                switch (MyApplication.getAppData().getMusicMode()) {
                                    case 0:
                                        textViewList.get(1).setText(R.string.music_title_browse);
                                        pMobileMusic.getMusicRadioData(MyApplication.getAppData().getTaid(), false);
                                        break;
                                    case 1:
                                        textViewList.get(1).setText(R.string.music_title_you);
                                        pMobileMusic.getMusicRadioData("", false);
                                        break;
                                    case 2:
                                        textViewList.get(1).setText(R.string.music_title_library);
                                        pMobileMusic.getPushMusicData(MyApplication.getAppData().getTaid(), "", "collect");
                                        break;
                                    default:
                                        break;
                                }
                            } else {
                                getFragmentManager().popBackStack();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(imageViewList.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        isSearch = true;
                        textViewList.get(1).setText(R.string.music_title_search);
                        imageViewList.get(1).setVisibility(View.GONE);
                        viewSearch.setVisibility(View.GONE);
                        searchView.setVisibility(View.VISIBLE);
                        searchView.setIconifiedByDefault(false);
                        searchView.setIconified(false);
                        aMobileMusic.refreshData(new ArrayList<String>(), new ArrayList<String>());
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(viewSearch)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        isSearch = true;
                        textViewList.get(1).setText(R.string.music_title_search);
                        imageViewList.get(1).setVisibility(View.GONE);
                        viewSearch.setVisibility(View.GONE);
                        searchView.setVisibility(View.VISIBLE);
                        searchView.setIconifiedByDefault(false);
                        searchView.setIconified(false);
                        aMobileMusic.refreshData(new ArrayList<String>(), new ArrayList<String>());
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                pMobileMusic.handleSearchData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        RxView.clicks(imageViewList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (MyApplication.getAppData().isPhotoMode()) {
                            imageViewList.get(0).setImageResource(R.drawable.list_m_v11);
                            MyApplication.getAppData().setPhotoMode(false);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        } else {
                            imageViewList.get(0).setImageResource(R.drawable.list_s_v12);
                            MyApplication.getAppData().setPhotoMode(true);
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                        }
                        aMobileMusic.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity) context;
    }

    @Override
    public boolean onBackPressed() {
        if (MyApplication.getAppData().getMusicMode() == 1) {
            if (isFromDiscovery) {
                if (isSearch) {
                    isSearch = false;
                    pMobileMusic.getPushMusicData(MyApplication.getAppData().getTaid(), MyApplication.getAppData().getValue(), "channel");
                } else {
                    isFromDiscovery = false;
                    textViewList.get(1).setText(R.string.music_title_browse);
                    MyApplication.getAppData().setMusicMode(0);
                    pMobileMusic.getMusicRadioData(MyApplication.getAppData().getTaid(), false);
                }
            } else {
                getFragmentManager().popBackStack();
            }
        } else {
            if (isSearch) {
                isSearch = false;
                switch (MyApplication.getAppData().getMusicMode()) {
                    case 0:
                        pMobileMusic.getMusicRadioData(MyApplication.getAppData().getTaid(), false);
                        break;
                    case 1:
                        pMobileMusic.getMusicRadioData("", false);
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            } else {
                getFragmentManager().popBackStack();
            }
        }
        return true;
    }

    @Override
    public void handleClick(int index) {
        GPlayer.Push push;
        switch (MyApplication.getAppData().getMusicMode()) {
            case 0:
                if (isSearch) {
                    MyApplication.getAppData().setFromFavorite(false);
                    MyApplication.getAppData().setWebId(gPushMusic.getPushMusicData().get(index).getWebid());
                    switch (MyApplication.getLanguageIndex()) {
                        case 1:
                            MyApplication.getAppData().setChannelName(gPushMusic.getPushMusicData().get(index).getWtitle_tw());
                            break;
                        case 2:
                            MyApplication.getAppData().setChannelName(gPushMusic.getPushMusicData().get(index).getWtitle_ch());
                            break;
                        case 3:
                            MyApplication.getAppData().setChannelName(gPushMusic.getPushMusicData().get(index).getWtitle_jp());
                            break;
                        default:
                            MyApplication.getAppData().setChannelName(gPushMusic.getPushMusicData().get(index).getWtitle());
                            break;
                    }
                    MyApplication.getAppData().setPhpName(gPushMusic.getPushMusicData().get(index).getPhpname());
                    MyApplication.getAppData().setFromMobileMusic(true);
                    if (gPushMusic.getPushMusicData().get(index).getLove().equals("0")) {
                        MyApplication.getAppData().setLove(false);
                    } else {
                        MyApplication.getAppData().setLove(true);
                    }
                    push = new GPlayer.Push();
                    push.setWebid_push(gPushMusic.getPushMusicData().get(index).getWebid());
                    push.setWebid(gPushMusic.getPushMusicData().get(index).getWebid());
                    push.setMenuid(MyApplication.getAppData().getMenuId());
                    push.setWtitle(gPushMusic.getPushMusicData().get(index).getWtitle());
                    push.setWtitle_tw(gPushMusic.getPushMusicData().get(index).getWtitle_tw());
                    push.setWtitle_ch(gPushMusic.getPushMusicData().get(index).getWtitle_ch());
                    push.setWtitle_jp(gPushMusic.getPushMusicData().get(index).getWtitle_jp());
                    push.setCount(gPushMusic.getPushMusicData().get(index).getCount());
                    push.setLove(gPushMusic.getPushMusicData().get(index).getLove());
                    push.setPath_img(gPushMusic.getPushMusicData().get(index).getPath_img());
                    push.setPhpname(gPushMusic.getPushMusicData().get(index).getPhpname());
                    push.setRenew("0");
                    MyApplication.getAppData().setPush(push);
                    MyApplication.getAppData().setPush(false);
                    MyApplication.getAppData().setPushBuffer(null);
                    getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPlayer(), "Player").addToBackStack("MobileMusic").commit();
                } else {
                    String value = gMusicRadio.getTdData().get(index).getMuid();
                    String taid = gMusicRadio.getTdData().get(index).getTaid();
                    MyApplication.getAppData().setTaid(taid);
                    MyApplication.getAppData().setValue(value);
                    MyApplication.getAppData().setMusicMode(1);
                    pMobileMusic.getPushMusicData(taid, value, "channel");
                    StringBuilder stringBuilder = new StringBuilder();
                    switch (MyApplication.getLanguageIndex()) {
                        case 1:
                            stringBuilder.append(gMusicRadio.getTdData().get(index).getM_winame_tw());
                            break;
                        case 2:
                            stringBuilder.append(gMusicRadio.getTdData().get(index).getM_winame_ch());
                            break;
                        case 3:
                            stringBuilder.append(gMusicRadio.getTdData().get(index).getM_winame_jp());
                            break;
                        default:
                            stringBuilder.append(gMusicRadio.getTdData().get(index).getM_winame());
                            break;
                    }
                    if (stringBuilder.toString().contains("<>")) {
                        int myIndex = stringBuilder.indexOf("<");
                        stringBuilder.replace(myIndex, myIndex + 2, " ");
                    } else if (stringBuilder.toString().contains("&amp;#039;")) {
                        int myIndex = stringBuilder.indexOf("&");
                        stringBuilder.replace(myIndex, myIndex + 10, "'");
                    }
                    channelName = stringBuilder.toString();
                    textViewList.get(1).setText(stringBuilder.toString());
                    isFromDiscovery = true;
                }
                break;
            case 1:
                if (gPushMusic != null) {
                    MyApplication.getAppData().setFromFavorite(false);
                    MyApplication.getAppData().setWebId(gPushMusic.getPushMusicData().get(index).getWebid());
                    switch (MyApplication.getLanguageIndex()) {
                        case 1:
                            MyApplication.getAppData().setChannelName(gPushMusic.getPushMusicData().get(index).getWtitle_tw());
                            break;
                        case 2:
                            MyApplication.getAppData().setChannelName(gPushMusic.getPushMusicData().get(index).getWtitle_ch());
                            break;
                        case 3:
                            MyApplication.getAppData().setChannelName(gPushMusic.getPushMusicData().get(index).getWtitle_jp());
                            break;
                        default:
                            MyApplication.getAppData().setChannelName(gPushMusic.getPushMusicData().get(index).getWtitle());
                            break;
                    }
                    MyApplication.getAppData().setPhpName(gPushMusic.getPushMusicData().get(index).getPhpname());
                    MyApplication.getAppData().setFromMobileMusic(true);
                    if (gPushMusic.getPushMusicData().get(index).getLove().equals("0")) {
                        MyApplication.getAppData().setLove(false);
                    } else {
                        MyApplication.getAppData().setLove(true);
                    }
                    push = new GPlayer.Push();
                    push.setWebid_push(gPushMusic.getPushMusicData().get(index).getWebid());
                    push.setWebid(gPushMusic.getPushMusicData().get(index).getWebid());
                    push.setMenuid(MyApplication.getAppData().getMenuId());
                    push.setWtitle(gPushMusic.getPushMusicData().get(index).getWtitle());
                    push.setWtitle_tw(gPushMusic.getPushMusicData().get(index).getWtitle_tw());
                    push.setWtitle_ch(gPushMusic.getPushMusicData().get(index).getWtitle_ch());
                    push.setWtitle_jp(gPushMusic.getPushMusicData().get(index).getWtitle_jp());
                    push.setCount(gPushMusic.getPushMusicData().get(index).getCount());
                    push.setLove(gPushMusic.getPushMusicData().get(index).getLove());
                    push.setPath_img(gPushMusic.getPushMusicData().get(index).getPath_img());
                    push.setPhpname(gPushMusic.getPushMusicData().get(index).getPhpname());
                    push.setRenew("0");
                    MyApplication.getAppData().setPush(push);
                    MyApplication.getAppData().setPushBuffer(null);
                    MyApplication.getAppData().setPush(false);
                    getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPlayer(), "Player").addToBackStack("MobileMusic").commit();
                }
                break;
            case 2:
                MyApplication.getAppData().setFromFavorite(true);
                MyApplication.getAppData().setWebId(gPushMusic.getPushMusicData().get(index).getWebid());
                switch (MyApplication.getLanguageIndex()) {
                    case 1:
                        MyApplication.getAppData().setChannelName(gPushMusic.getPushMusicData().get(index).getWtitle_tw());
                        break;
                    case 2:
                        MyApplication.getAppData().setChannelName(gPushMusic.getPushMusicData().get(index).getWtitle_ch());
                        break;
                    case 3:
                        MyApplication.getAppData().setChannelName(gPushMusic.getPushMusicData().get(index).getWtitle_jp());
                        break;
                    default:
                        MyApplication.getAppData().setChannelName(gPushMusic.getPushMusicData().get(index).getWtitle());
                        break;
                }
                MyApplication.getAppData().setPhpName(gPushMusic.getPushMusicData().get(index).getPhpname());
                MyApplication.getAppData().setFromMobileMusic(true);
                if (gPushMusic.getPushMusicData().get(index).getLove().equals("0")) {
                    MyApplication.getAppData().setLove(false);
                } else {
                    MyApplication.getAppData().setLove(true);
                }
                push = new GPlayer.Push();
                push.setWebid_push(gPushMusic.getPushMusicData().get(index).getWebid());
                push.setWebid(gPushMusic.getPushMusicData().get(index).getWebid());
                push.setMenuid(MyApplication.getAppData().getMenuId());
                push.setWtitle(gPushMusic.getPushMusicData().get(index).getWtitle());
                push.setWtitle_tw(gPushMusic.getPushMusicData().get(index).getWtitle_tw());
                push.setWtitle_ch(gPushMusic.getPushMusicData().get(index).getWtitle_ch());
                push.setWtitle_jp(gPushMusic.getPushMusicData().get(index).getWtitle_jp());
                push.setCount(gPushMusic.getPushMusicData().get(index).getCount());
                push.setLove(gPushMusic.getPushMusicData().get(index).getLove());
                push.setPath_img(gPushMusic.getPushMusicData().get(index).getPath_img());
                push.setPhpname(gPushMusic.getPushMusicData().get(index).getPhpname());
                push.setRenew("0");
                MyApplication.getAppData().setPush(push);
                MyApplication.getAppData().setPushBuffer(null);
                MyApplication.getAppData().setPush(false);
                getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPlayer(), "Player").addToBackStack("MobileMusic").commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void handleClickLove(int index) {
        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
            new SignInUtil(getContext(), iMainActivity);
        } else {
            if (gPushMusic.getPushMusicData().get(index).getLove().equals("0")) {
                pMobileMusic.sendLove(
                        index,
                        "add",
                        MyApplication.getAppData().getTaid(),
                        gPushMusic.getPushMusicData().get(index).getWebid(),
                        "",
                        "");
            } else {
                pMobileMusic.sendLove(
                        index,
                        "del",
                        MyApplication.getAppData().getTaid(),
                        gPushMusic.getPushMusicData().get(index).getWebid(),
                        "",
                        "");
            }
        }
    }

    @Override
    public void init(GMusicRadio gMusicRadio, List<String> musicTitle, List<String> musicPhotoUrl) {
        this.gMusicRadio = gMusicRadio;
        aMobileMusic.refreshData(musicTitle, musicPhotoUrl);
        if (isSearch) {
            searchView.setQuery("", false);
            searchView.clearFocus();
        } else {
            imageViewList.get(1).setVisibility(View.VISIBLE);
            searchView.setVisibility(View.INVISIBLE);
            viewSearch.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void init(GPushMusic gPushMusic, List<String> musicTitle, List<String> musicPhotoUrl, List<String> musicCount, List<String> musicLove) {
        this.gPushMusic = gPushMusic;
        aMobileMusic.refreshData(musicTitle, musicPhotoUrl, musicCount, musicLove, isSearch);
        if (imageViewList != null) {
            if (isSearch) {
                searchView.setQuery("", false);
                searchView.clearFocus();
            } else {
                imageViewList.get(1).setVisibility(View.VISIBLE);
                searchView.setVisibility(View.INVISIBLE);
                viewSearch.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void showSearchError() {
        AlertDialogUtil
                .getInstance()
                .initDialogBuilder(getContext(), R.string.music_search_error, R.string.music_search_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void refreshLoveData(String save, int index) {
        switch (save) {
            case "1":
                gPushMusic.getPushMusicData().get(index).setLove("1");
                Toast.makeText(getContext(), R.string.player_subscribed, Toast.LENGTH_SHORT).show();
                aMobileMusic.refreshData(index, true, isSearch);
                break;
            case "10":
                if (MyApplication.getAppData().getMusicMode() == 2 && !isSearch) {
                    gPushMusic.getPushMusicData().remove(index);
                } else {
                    gPushMusic.getPushMusicData().get(index).setLove("0");
                }
                Toast.makeText(getContext(), R.string.player_unsubscribed, Toast.LENGTH_SHORT).show();
                aMobileMusic.refreshData(index, false, isSearch);
                break;
            default:
                break;
        }
    }
}
