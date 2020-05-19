package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.APlaylist;
import com.switube.www.landmark2018test.adapter.APushData;
import com.switube.www.landmark2018test.adapter.AReportList;
import com.switube.www.landmark2018test.adapter.callback.IAPlaylist;
import com.switube.www.landmark2018test.adapter.callback.IAPushData;
import com.switube.www.landmark2018test.presenter.PPlayer;
import com.switube.www.landmark2018test.util.AlertDialogUtil;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.util.SignInUtil;
import com.switube.www.landmark2018test.view.callback.IFragmentBackHandler;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVPlayer;
import com.switube.www.landmark2018test.youtube.YouTubePlayerController;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class VPlayer extends Fragment implements IVPlayer, IAPlaylist, IAPushData, IFragmentBackHandler {
    @BindViews({R.id.imageBackInPlayer, R.id.imageLikeInPlayer, R.id.imageMoreInPlayer,
            R.id.imageSwitchViewModeInPlayer, R.id.imagePlayModeInPlayer})
    List<ImageView> imageViewList;
    @BindViews({R.id.textPushInPlayer, R.id.textPlayingInPlayer, R.id.textTitleInPlayer})
    List<TextView> textViewList;
    @BindViews({R.id.layoutAddInPlayer, R.id.layoutListInPlayer})
    List<RelativeLayout> layoutList;
    @BindView(R.id.recyclerViewInPlayer)
    RecyclerView recyclerView;
    @BindView(R.id.layoutInPlayer)
    VSlide vSlide;
    @BindViews({R.id.textAddUrlInPlayer, R.id.textToWebMessageInPlayer, R.id.textReportInPlayer})
    List<TextView> textViewListInList;
    @BindView(R.id.viewInPlayer)
    View viewInPlayer;
    @BindViews({R.id.textSendInPlayer, R.id.textCancelInPlayer})
    List<TextView> textViewListInAdd;
    @BindView(R.id.editUriInPlayer)
    EditText editText;
    private PPlayer pPlayer;
    private Unbinder unbinder;
    private APlaylist aPlaylist;
    private int mode = -1;
    private int startX = 0;
    private int startY = 0;
    private APushData aPushData;
    private IMainActivity iMainActivity;

    public VPlayer() {
        pPlayer = new PPlayer(this);
    }

    private ItemDecorationUtil itemDecorationPush;
    private ItemDecorationUtil itemDecorationList;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_v_player, container, false);
        unbinder = ButterKnife.bind(this, view);
        itemDecorationPush = new ItemDecorationUtil(container.getContext(), 10, 0, 14, 0);
        itemDecorationList = new ItemDecorationUtil(container.getContext(), 0, 0, 0, 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        aPlaylist = new APlaylist(this);
        MyApplication.getAppData().setPlayerPage(true);
        MyApplication.getAppData().setCanRefreshList(true);
        if (MyApplication.getAppData().isFromFavorite()) {
            imageViewList.get(1).setVisibility(GONE);
        } else {
            imageViewList.get(1).setVisibility(VISIBLE);
        }
        recyclerView.setAdapter(aPlaylist);
        if (MyApplication.getAppData().getiFloatPlayerService().getCheckFloatPlayerCreate()) {
            if (MyApplication.getAppData().isFromMobileMusic()) {
                MyApplication.getAppData().getiFloatPlayerService().showFloatPlayer(true);
                MyApplication.getAppData().setFromMobileMusic(false);
                MyApplication.getAppData().setSlideMode(1);
                pPlayer.getMusicData(MyApplication.getAppData().getWebId(), MyApplication.getAppData().getWebId());
            } else {
                if (MyApplication.getAppData().isPush()) {
                    pPlayer.handlePushData();
                } else {
                    pPlayer.handlePlaylistData();
                }
            }
            MyApplication.getAppData().getiFloatPlayerService().showFloatPlayer(true);
            MyApplication.getAppData().getiFloatPlayerService().showBigMode();
            vSlide.handleHintVisibility();
        } else {
            MyApplication.getAppData().setFromMobileMusic(false);
            MyApplication.getAppData().getiFloatPlayerService().initFloatPlayer(container.getContext());
            MyApplication.getAppData().getiFloatPlayerService().initFloatPlayerKiller(container.getContext());
            pPlayer.getMusicData(MyApplication.getAppData().getWebId(), MyApplication.getAppData().getWebId());
        }
        handleLove(false);
        YouTubePlayerController.canClick = true;
        RxView.clicks(imageViewList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        MyApplication.getAppData().setCanRefreshList(false);
                        MyApplication.getAppData().setPlayerPage(false);
                        MyApplication.getAppData().getiFloatPlayerService().showSmallMode();
                        if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                            getParentFragmentManager().popBackStackImmediate("Map", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        } else {
                            getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VMap()).commit();
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
                        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                            MyApplication.getAppData().getiFloatPlayerService().showSmallMode();
                            new SignInUtil(getContext(), iMainActivity);
                        } else {
                            String type;
                            if (MyApplication.getAppData().isLove()) {
                                type = "del";
                            } else {
                                type = "add";
                            }
                            pPlayer.sendLove(type,
                                    MyApplication.getAppData().getTaid(),
                                    MyApplication.getAppData().getWebId(),
                                    "",
                                    "",
                                    false,
                                    -1);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(imageViewList.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        layoutList.get(1).setVisibility(VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(imageViewList.get(3))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (MyApplication.getAppData().isSlide()) {
                            MyApplication.getAppData().setSlide(false);
                        } else {
                            MyApplication.getAppData().setSlide(true);
                        }
                        handleSwitchMapOrList();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(imageViewList.get(4))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        MyApplication.getAppData().setSlide(false);
                        if (MyApplication.getAppData().isRandom()) {
                            MyApplication.getAppData().setRandom(false);
                            imageViewList.get(4).setImageResource(R.drawable.btn_random_bt_v2_2x);
                            pPlayer.handleNamePlaying();
                        } else {
                            MyApplication.getAppData().setRandom(true);
                            imageViewList.get(4).setImageResource(R.drawable.btn_repeat_bt_v2_2x);
                            pPlayer.handleRandomPlaying();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViewList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (!MyApplication.getAppData().isPush()) {
                            MyApplication.getAppData().setPush(true);
                            pPlayer.handlePushData();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViewList.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        MyApplication.getAppData().setPush(false);
                        pPlayer.handlePlaylistData();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViewList.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        MyApplication.getAppData().setCanRefreshList(false);
                        MyApplication.getAppData().setPlayerPage(false);
                        MyApplication.getAppData().setPlaylist(true);
                        MyApplication.getAppData().getiFloatPlayerService().showSmallMode();
                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.layoutContainer, new VPlaylist(), "Playlist")
                                .addToBackStack("Player")
                                .commit();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.touches(vSlide)
                .subscribe(new Observer<MotionEvent>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(MotionEvent event) {
                        if (event.getPointerCount() > 1) {
                            if (event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
                                mode = 2;
                            }
                            if (MyApplication.getAppData().getgPlayer().getSmartData().getWtype().equals("1")) {
                                vSlide.handleEvent(event);
                            }
                        } else {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    startX = (int) event.getX();
                                    startY = (int) event.getY();
                                    mode = 0;
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    if (mode == 0 && (event.getX() - startX > 10 || event.getY() - startY > 10)) {
                                        mode = 1;
                                    }
                                    if (mode == 1) {
                                        vSlide.handleMoveSlide((int) event.getX(), (int) event.getY());
                                    }
                                    break;
                                case MotionEvent.ACTION_UP:
                                    if (mode != 2 && mode != -1) {
                                        vSlide.handleMoveSlide((int) event.getX(), (int) event.getY());
                                        MyApplication.getAppData().setPointX((int) event.getX());
                                        MyApplication.getAppData().setPointY((int) event.getY());
                                        if (MyApplication.getAppData().getgPlayer().getSmartData().getWtype().equals("1")) {
                                            pPlayer.handleRefreshListWithXY((int) event.getX(), (int) event.getY());
                                        } else {
                                            pPlayer.handleBasePhotoByType2((int) event.getX(), (int) event.getY());
                                        }
                                        MyApplication.getAppData().setSlide(false);
                                        handleSwitchMapOrList();
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViewListInList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                            layoutList.get(1).setVisibility(GONE);
                            new SignInUtil(getContext(), iMainActivity);
                            MyApplication.getAppData().getiFloatPlayerService().showSmallMode();
                        } else {
                            layoutList.get(1).setVisibility(GONE);
                            layoutList.get(0).setVisibility(VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViewListInList.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        layoutList.get(1).setVisibility(GONE);
                        String php = MyApplication.getAppData().getPhpName();
                        StringBuilder stringBuilder1 = new StringBuilder("https://www.switube.com/C/mrsp.php?s=");
                        stringBuilder1.append(php);
                        stringBuilder1.append("&l=");
                        switch (MyApplication.getLanguageIndex()) {
                            case 1:
                                stringBuilder1.append("TW");
                                break;
                            case 2:
                                stringBuilder1.append("CH");
                                break;
                            case 3:
                                stringBuilder1.append("JP");
                                break;
                            default:
                                stringBuilder1.append("EN");
                                break;
                        }
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(stringBuilder1.toString()));
                        startActivity(intent);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViewListInList.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                            layoutList.get(1).setVisibility(GONE);
                            new SignInUtil(getContext(), iMainActivity);
                            MyApplication.getAppData().getiFloatPlayerService().showSmallMode();
                        } else {
                            MyApplication.getAppData().getiFloatPlayerService().showSmallMode();
                            layoutList.get(1).setVisibility(GONE);
                            handleReportOne();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(viewInPlayer)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        layoutList.get(1).setVisibility(GONE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViewListInAdd.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        String url = editText.getText().toString();
                        StringBuilder stringBuilder = new StringBuilder(url);
                        String webid = MyApplication.getAppData()
                                .getMusicEntities().get(MyApplication.getAppData().getPlayingIndex())
                                .getWebid();
                        if (url.contains("youtube")) {
                            if (url.contains("embed")) {
                                do {
                                    int index = stringBuilder.indexOf("/");
                                    stringBuilder.delete(0, index + 1);
                                } while (stringBuilder.toString().contains("/"));
                            } else {
                                do {
                                    int index = stringBuilder.indexOf("/");
                                    stringBuilder.delete(0, index + 1);
                                } while (stringBuilder.toString().contains("/"));
                                if (stringBuilder.toString().contains("watch")) {
                                    int index = stringBuilder.indexOf("=");
                                    stringBuilder.delete(0, index + 1);
                                }
                            }
                            layoutList.get(0).setVisibility(GONE);
                            pPlayer.handleSendAddVideo(webid, stringBuilder.toString());
                        } else if (url.contains("youtu")) {
                            do {
                                int index = stringBuilder.indexOf("/");
                                stringBuilder.delete(0, index + 1);
                            } while (stringBuilder.toString().contains("/"));
                            layoutList.get(0).setVisibility(GONE);
                            pPlayer.handleSendAddVideo(webid, stringBuilder.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViewListInAdd.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        layoutList.get(0).setVisibility(GONE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    @Override
    public void init(List<String> name) {
        textViewList.get(2).setText(MyApplication.getAppData().getChannelName());
        if (MyApplication.getAppData().getRangeWith() == 0 && MyApplication.getAppData().getRangeHeight() == 0) {
            MyApplication.getAppData().setRange(vSlide.getWidth(), vSlide.getHeight());
        }
        textViewList.get(0).setBackgroundColor(Color.parseColor("#1d1d1d"));
        textViewList.get(1).setBackgroundColor(Color.parseColor("#3b3b3b"));
        if (MyApplication.getAppData().getgPlayer().getSmartData().getWtype().equals("1") && MyApplication.getAppData().isNeedAuto()) {
            MyApplication.getAppData().setNeedAuto(false);
            pPlayer.handleSlidePoint();
        } else {
            vSlide.handleMoveSlide(MyApplication.getAppData().getPointX(), MyApplication.getAppData().getPointY());
            pPlayer.showBasePhoto(MyApplication.getAppData().getPointX(), MyApplication.getAppData().getPointY());
        }
        imageViewList.get(3).setVisibility(VISIBLE);
        imageViewList.get(4).setVisibility(VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.removeItemDecoration(itemDecorationPush);
        recyclerView.addItemDecoration(itemDecorationList);
        recyclerView.setAdapter(aPlaylist);
        aPlaylist.refreshAdapter(name);
        if (MyApplication.getAppData().isSlide()) {
            imageViewList.get(3).setImageResource(R.drawable.btn_to_playlist_v1_2);
            vSlide.setVisibility(VISIBLE);
            vSlide.handleHintVisibility();
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            imageViewList.get(3).setImageResource(R.drawable.btn_to_map_v1_3_1);
            vSlide.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(VISIBLE);
        }
        handleLove(false);
    }

    @Override
    public void init(List<String> name, List<String> img, List<String> count, List<String> love) {
        textViewList.get(2).setText(MyApplication.getAppData().getChannelName());
        textViewList.get(1).setBackgroundColor(Color.parseColor("#1d1d1d"));
        textViewList.get(0).setBackgroundColor(Color.parseColor("#3b3b3b"));
        imageViewList.get(3).setVisibility(GONE);
        imageViewList.get(4).setVisibility(GONE);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        aPushData = new APushData(this);
        recyclerView.removeItemDecoration(itemDecorationList);
        recyclerView.addItemDecoration(itemDecorationPush);
        recyclerView.setAdapter(aPushData);
        aPushData.refreshAdapter(name, img, count, love);
        vSlide.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(VISIBLE);
    }

    @Override
    public void handleClick(int index) {
        pPlayer.handleSwitchMusic(index);
    }

    @Override
    public void handleClickInPush(int index) {
        if (MyApplication.getAppData().isFromFavorite()) {
            MyApplication.getAppData().setFromFavorite(false);
            imageViewList.get(1).setVisibility(VISIBLE);
        }
        MyApplication.getAppData().setPush(false);
        pPlayer.getPushMusicData(index);
    }

    @Override
    public void handleLikeInPush(int index) {
        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
            MyApplication.getAppData().getiFloatPlayerService().showSmallMode();
            new SignInUtil(getContext(), iMainActivity);
        } else {
            pPlayer.handleLikeInPush(index);
        }
    }

    @Override
    public void handleZoomOutSlide() {
        vSlide.handleZoom("zoomOut");
    }

    @Override
    public void handleMoveSlide(int x, int y) {
        vSlide.handleMoveSlide(x, y);
    }

    @Override
    public void initBasePhoto(int resource) {
        vSlide.handleChangeBaseResource(resource);
    }

    @Override
    public void handleFinishSend() {
        layoutList.get(0).setVisibility(GONE);
        editText.setText("");
        Toast.makeText(getContext(), R.string.player_add_video_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleSwitchSlidePhoto(boolean isSwitch) {
        vSlide.handleChangeSlidePhoto(isSwitch);
    }

    @Override
    public void handleSwitchPlayingMode() {
        if (MyApplication.getAppData().isRandom()) {
            imageViewList.get(4).setImageResource(R.drawable.btn_repeat_bt_v2_2x);
        } else {
            imageViewList.get(4).setImageResource(R.drawable.btn_random_bt_v2_2x);
        }
    }

    @Override
    public void handleSwitchMapOrList() {
        if (MyApplication.getAppData().isSlide()) {
            imageViewList.get(3).setImageResource(R.drawable.btn_to_playlist_v1_2);
            recyclerView.setVisibility(View.INVISIBLE);
            vSlide.setVisibility(VISIBLE);
        } else {
            imageViewList.get(3).setImageResource(R.drawable.btn_to_map_v1_3_1);
            recyclerView.setVisibility(VISIBLE);
            vSlide.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void handleLove(boolean needToast) {
        if (MyApplication.getAppData().isLove()) {
            imageViewList.get(1).setImageResource(R.drawable.love_on_2x);
            if (needToast) {
                Toast.makeText(getContext(), R.string.player_subscribed, Toast.LENGTH_SHORT).show();
            }
        } else {
            imageViewList.get(1).setImageResource(R.drawable.love_off_2x);
            if (needToast) {
                Toast.makeText(getContext(), R.string.player_unsubscribed, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void handleRefreshLoveInPush(int index, String love) {
        aPushData.refreshAdapter(index, love);
        if (love.equals("0")) {
            Toast.makeText(getContext(), R.string.player_unsubscribed, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), R.string.player_subscribed, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void initReport(List<String> name, View view) {
        aReportList.handleRefreshData(name);
        AlertDialogUtil.getInstance().initDialogBuilder(getContext(),
                view,
                getString(R.string.global_next),
                (dialogInterface, i) -> showReportTwo(aReportList.getIndex()),
                getString(R.string.global_cancel),
                (dialogInterface, i) -> MyApplication.getAppData().getiFloatPlayerService().showBigMode());
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void initReport(List<String> name, View view, TextView textView, String title, final int index) {
        textView.setText(title);
        aReportList.handleRefreshData(name);
        AlertDialogUtil.getInstance().initDialogBuilder(getContext(),
                view,
                getString(R.string.global_confirm),
                (dialogInterface, i) -> {
                    MyApplication.getAppData().getiFloatPlayerService().showBigMode();
                    pPlayer.sendReportData(aReportList.getIndex(), index);
                },
                getString(R.string.global_cancel),
                (dialogInterface, i) -> MyApplication.getAppData().getiFloatPlayerService().showBigMode());
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void handleReportSuccess() {
        Toast.makeText(getContext(), R.string.player_report_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handlePushNewSuccess() {
        Toast.makeText(getContext(), R.string.player_add_video_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity) context;
    }

    @Override
    public boolean onBackPressed() {
        if (MyApplication.getAppData().isFromSearchAttraction()) {
            MyApplication.getAppData().setFromSearchAttraction(false);
            MyApplication.getAppData().setPlayerPage(false);
            getParentFragmentManager().popBackStack();
        } else {
            if (MyApplication.getAppData().getiFloatPlayerService().getIsFullScreenMode()) {
                iMainActivity.switchScreenOrientation();
            } else {
                MyApplication.getAppData().setCanRefreshList(false);
                MyApplication.getAppData().setPlayerPage(false);
                MyApplication.getAppData().getiFloatPlayerService().showSmallMode();
                getParentFragmentManager().popBackStackImmediate("Map", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
        return true;
    }

    public void handleRefreshAdapter() {
        aPlaylist.notifyDataSetChanged();
    }

    private AReportList aReportList;
    private void handleReportOne() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_item_report, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerInItemReport);
        TextView textTitle = view.findViewById(R.id.textTitleInItemReport);
        textTitle.setText(R.string.player_report);
        aReportList = new AReportList();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(aReportList);
        pPlayer.handleReportOne(view);
    }

    private void showReportTwo(int index) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_item_report, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerInItemReport);
        TextView textTitle = view.findViewById(R.id.textTitleInItemReport);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(aReportList);
        pPlayer.handleReportTwo(view, index, textTitle);
    }
}
