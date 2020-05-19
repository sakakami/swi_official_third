package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.AReplies;
import com.switube.www.landmark2018test.adapter.callback.IAReplies;
import com.switube.www.landmark2018test.gson.GCommentsData;
import com.switube.www.landmark2018test.presenter.PReplies;
import com.switube.www.landmark2018test.util.AppConstant;
import com.switube.www.landmark2018test.util.MyEditText;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVReplies;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class VReplies extends Fragment implements IVReplies, IAReplies {
    @BindViews({R.id.imagePhotoHeadInReplies, R.id.imagePhotoInReplies})
    List<CircleImageView> circleImageViews;
    @BindViews({R.id.imageGoodInReplies, R.id.imageBadInReplies, R.id.imageMenuInReplies})
    List<ImageView> imageViews;
    @BindViews({R.id.textBackInReplies, R.id.textNameInReplies, R.id.textMessageInReplies,
            R.id.textDateInReplies, R.id.textReplyInReplies, R.id.textCountInReplies, R.id.textSendInReplies})
    List<TextView> textViews;
    @BindView(R.id.editMessageInReplies)
    MyEditText editText;
    @BindView(R.id.recyclerInReplies)
    RecyclerView recyclerView;
    @BindView(R.id.viewInReplies)
    View viewBackground;
    private PReplies pReplies;
    private Unbinder unbinder;
    private String message = "";
    private AReplies aReplies;
    private IMainActivity iMainActivity;

    public VReplies() {
        pReplies = new PReplies(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_replies, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        RxTextView.textChanges(editText)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(CharSequence charSequence) {
                        message = charSequence.toString();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViews.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        getParentFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViews.get(6))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (message.length() > 0) {
                            if (isEdit) {
                                if (index == 999) {
                                    pReplies.sendEditData(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getAcid(), message, true, index, iMainActivity.getMessageIndex());
                                } else {
                                    pReplies.sendEditData(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getReply().get(index).getArid(), message, false, index, iMainActivity.getMessageIndex());
                                }
                            } else {
                                pReplies.sendReplyData(
                                        MyApplication.getAppData().getArtid(),
                                        MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getAcid(),
                                        message);
                                message = "";
                                editText.setText("");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(imageViews.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        pReplies.sendLikeData(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getAcid(),
                                "1", iMainActivity.getMessageIndex(), false);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(imageViews.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        pReplies.sendLikeData(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getAcid(),
                                "2", iMainActivity.getMessageIndex(), false);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(imageViews.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        showMenu();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        editText.setOnBackPressedListener(() -> {
            message = "";
            editText.setText("");
            isEdit = false;
            viewBackground.setVisibility(View.GONE);
        });
        return view;
    }

    private void init() {
        Glide.with(MyApplication.getInstance())
                .load(Uri.parse(AppConstant.BASE_URL2 + MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getMaimg()))
                .into(circleImageViews.get(0));
        StringBuilder stringBuilder = new StringBuilder(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
        if (stringBuilder.toString().equals("null")) {
            circleImageViews.get(1).setImageResource(R.drawable.person_unlogin);
        } else {
            stringBuilder.delete(0, 2);
            Glide.with(MyApplication.getInstance())
                    .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                    .into(circleImageViews.get(1));
        }
        textViews.get(1).setText(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getManame());
        textViews.get(2).setText(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getTxt());
        textViews.get(3).setText(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getTime_txt());
        textViews.get(5).setText(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getCount());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        aReplies = new AReplies(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getReply(), this);
        recyclerView.setAdapter(aReplies);
        switch (MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getLike()) {
            case "1":
                imageViews.get(0).setImageResource(R.drawable.like_fornote_on_v11);
                imageViews.get(1).setImageResource(R.drawable.dislike_fornote_v11);
                break;
            case "2":
                imageViews.get(0).setImageResource(R.drawable.like_fornote_v11);
                imageViews.get(1).setImageResource(R.drawable.dislike_fornote_on_v11);
                break;
            default:
                imageViews.get(0).setImageResource(R.drawable.like_fornote_v11);
                imageViews.get(1).setImageResource(R.drawable.dislike_fornote_v11);
                break;
        }
        if (MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getMaid().equals(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"))) {
            imageViews.get(2).setVisibility(View.VISIBLE);
        } else {
            imageViews.get(2).setVisibility(View.GONE);
        }
    }

    @Override
    public void handleRefresh(GCommentsData gCommentsData) {
        gCommentsData.getMsgData().get(iMainActivity.getMessageIndex()).getReply().get(0).setMaid(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"));
        MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getReply().add(0, gCommentsData.getMsgData().get(iMainActivity.getMessageIndex()).getReply().get(0));
        aReplies.refreshAdapter(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getReply());
    }

    @Override
    public void handleRefresh(String count, String like, int index, boolean isReply) {
        if (isReply) {
            MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getReply().get(index).setLike(like);
            MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getReply().get(index).setCount(count);
            aReplies.refreshAdapter(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getReply());
        } else {
            MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).setLike(like);
            MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).setCount(count);
            textViews.get(5).setText(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getCount());
            switch (MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getLike()) {
                case "1":
                    imageViews.get(0).setImageResource(R.drawable.like_fornote_on_v11);
                    imageViews.get(1).setImageResource(R.drawable.dislike_fornote_v11);
                    break;
                case "2":
                    imageViews.get(0).setImageResource(R.drawable.like_fornote_v11);
                    imageViews.get(1).setImageResource(R.drawable.dislike_fornote_on_v11);
                    break;
                default:
                    imageViews.get(0).setImageResource(R.drawable.like_fornote_v11);
                    imageViews.get(1).setImageResource(R.drawable.dislike_fornote_v11);
                    break;
            }
        }
    }

    @Override
    public void handleClickLike(String like, int index) {
        pReplies.sendLikeData(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getReply().get(index).getArid(), like, index, true);
    }

    @Override
    public void handleFocus() {
        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    private boolean isEdit = false;
    private int index = -1;
    @Override
    public void handleEdit(int index) {
        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
        viewBackground.setVisibility(View.VISIBLE);
        isEdit = true;
        this.index = index;
        if (index == 999) {
            editText.setText(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getTxt());
        } else {
            editText.setText(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getReply().get(index).getTxt());
        }
    }

    @Override
    public void handleDelete(int index) {
        if (index == 999) {
            pReplies.sendDelData(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getAcid(), index, true, iMainActivity.getMessageIndex());
        } else {
            pReplies.sendDelData(MyApplication.getAppData().getMessageList().get(iMainActivity.getMessageIndex()).getReply().get(index).getArid(), index, false, iMainActivity.getMessageIndex());
        }
    }

    @Override
    public void handleFinishEdit(boolean isComment, String message, int index) {
        if (isComment) {
            textViews.get(2).setText(message);
        } else {
            aReplies.refreshAdapter(MyApplication.getAppData().getMessageList().get(index).getReply());
        }
        isEdit = false;
        this.message = "";
        editText.setText("");
        editText.clearFocus();
        InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        viewBackground.setVisibility(View.GONE);
    }

    @Override
    public void handleFinishDel(boolean isComment, int index) {
        if (isComment) {
            getParentFragmentManager().popBackStack();
        } else {
            aReplies.refreshAdapter(MyApplication.getAppData().getMessageList().get(index).getReply());
        }
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity) context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void showMenu() {
        PopupMenu popupMenu = new PopupMenu(getContext(), imageViews.get(2));
        popupMenu.getMenuInflater().inflate(R.menu.menu_info_reply, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.menuEditInReply) {
                handleEdit(999);
            } else {
                handleDelete(999);
            }
            return true;
        });
    }
}
