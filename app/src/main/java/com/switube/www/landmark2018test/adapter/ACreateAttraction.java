package com.switube.www.landmark2018test.adapter;

import android.graphics.Color;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IACreateAttraction;
import com.switube.www.landmark2018test.entity.ECreateAttraction;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ACreateAttraction extends RecyclerView.Adapter<ACreateAttraction.ViewHolder> {
    private ECreateAttraction mCreateData;
    private IACreateAttraction iaCreateAttraction;
    public ACreateAttraction(ECreateAttraction eCreateAttraction, IACreateAttraction iaCreateAttraction) {
        mCreateData = eCreateAttraction;
        this.iaCreateAttraction = iaCreateAttraction;
        init();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_create_attraction, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (position) {
            case 0:
                holder.mImageViews.get(0).setImageResource(R.drawable.a_name_v11);
                holder.mTextViews.get(0).setText(handleString(holder.itemView.getContext().getString(R.string.create_name)));
                holder.mEditText.setText(mName);
                holder.mImageViews.get(0).setVisibility(View.VISIBLE);
                holder.mImageViews.get(1).setVisibility(View.GONE);
                holder.mImageViews.get(2).setVisibility(View.GONE);
                holder.mEditText.setVisibility(View.VISIBLE);
                holder.mTextViews.get(0).setVisibility(View.VISIBLE);
                holder.mTextViews.get(1).setVisibility(View.GONE);
                holder.mTextViews.get(2).setVisibility(View.GONE);
                holder.mTextViews.get(3).setVisibility(View.GONE);
                holder.mTextViews.get(4).setVisibility(View.GONE);
                holder.mTextViews.get(5).setVisibility(View.GONE);
                holder.mTextViews.get(6).setVisibility(View.GONE);
                holder.mCheckBox.setVisibility(View.GONE);
                holder.mView.setVisibility(View.GONE);
                break;
            case 1:
                holder.mImageViews.get(0).setImageResource(R.drawable.a_add_v11);
                holder.mTextViews.get(0).setText(handleString(holder.itemView.getContext().getString(R.string.create_address)));
                holder.mTextViews.get(3).setText(mAddress);
                holder.mImageViews.get(0).setVisibility(View.VISIBLE);
                holder.mImageViews.get(1).setVisibility(View.GONE);
                holder.mImageViews.get(2).setVisibility(View.GONE);
                holder.mEditText.setVisibility(View.GONE);
                holder.mTextViews.get(0).setVisibility(View.VISIBLE);
                holder.mTextViews.get(1).setVisibility(View.GONE);
                holder.mTextViews.get(2).setVisibility(View.GONE);
                holder.mTextViews.get(3).setVisibility(View.VISIBLE);
                holder.mTextViews.get(4).setVisibility(View.GONE);
                holder.mTextViews.get(5).setVisibility(View.GONE);
                holder.mTextViews.get(6).setVisibility(View.GONE);
                holder.mCheckBox.setVisibility(View.GONE);
                holder.mView.setVisibility(View.VISIBLE);
                break;
            case 2:
                holder.mImageViews.get(0).setImageResource(R.drawable.a_cate_v11);
                holder.mTextViews.get(0).setText(handleString(holder.itemView.getContext().getString(R.string.create_category)));
                //holder.mEditText.setHint(R.string.create_category_hint);
                if (mType.length() > 0) {
                    holder.mTextViews.get(3).setText(mType);
                } else {
                    holder.mTextViews.get(3).setHint(R.string.create_category_hint);
                }
                holder.mImageViews.get(0).setVisibility(View.VISIBLE);
                holder.mImageViews.get(1).setVisibility(View.GONE);
                holder.mImageViews.get(2).setVisibility(View.GONE);
                holder.mEditText.setVisibility(View.GONE);
                holder.mTextViews.get(0).setVisibility(View.VISIBLE);
                holder.mTextViews.get(1).setVisibility(View.GONE);
                holder.mTextViews.get(2).setVisibility(View.GONE);
                holder.mTextViews.get(3).setVisibility(View.VISIBLE);
                holder.mTextViews.get(4).setVisibility(View.GONE);
                holder.mTextViews.get(5).setVisibility(View.GONE);
                holder.mTextViews.get(6).setVisibility(View.GONE);
                holder.mCheckBox.setVisibility(View.GONE);
                holder.mView.setVisibility(View.VISIBLE);
                break;
            case 3:
                holder.mImageViews.get(0).setImageResource(R.drawable.a_time_v11);
                holder.mTextViews.get(0).setText(R.string.create_hours);
                if (builderA.length() > 0) {
                    holder.mTextViews.get(1).setText(builderA.toString());
                    holder.mTextViews.get(2).setText(builderB.toString());
                } else {
                    holder.mTextViews.get(1).setHint(R.string.create_hours_hint);
                }
                holder.mImageViews.get(0).setVisibility(View.VISIBLE);
                holder.mImageViews.get(1).setVisibility(View.GONE);
                holder.mImageViews.get(2).setVisibility(View.GONE);
                holder.mEditText.setVisibility(View.GONE);
                holder.mTextViews.get(0).setVisibility(View.VISIBLE);
                holder.mTextViews.get(1).setVisibility(View.VISIBLE);
                holder.mTextViews.get(2).setVisibility(View.VISIBLE);
                holder.mTextViews.get(3).setVisibility(View.GONE);
                holder.mTextViews.get(4).setVisibility(View.GONE);
                holder.mTextViews.get(5).setVisibility(View.GONE);
                holder.mTextViews.get(6).setVisibility(View.GONE);
                holder.mCheckBox.setVisibility(View.GONE);
                holder.mView.setVisibility(View.GONE);
                break;
            case 4:
                holder.mImageViews.get(0).setImageResource(R.drawable.a_ad_phone_v11);
                holder.mTextViews.get(0).setText(R.string.create_phone);
                holder.mEditText.setHint(R.string.create_phone_hint);
                if (mCreateData.getPhone() != null) {
                    holder.mEditText.setText(mCreateData.getPhone());
                }
                holder.mImageViews.get(0).setVisibility(View.VISIBLE);
                holder.mImageViews.get(1).setVisibility(View.GONE);
                holder.mImageViews.get(2).setVisibility(View.GONE);
                holder.mEditText.setVisibility(View.VISIBLE);
                holder.mTextViews.get(0).setVisibility(View.VISIBLE);
                holder.mTextViews.get(1).setVisibility(View.GONE);
                holder.mTextViews.get(2).setVisibility(View.GONE);
                holder.mTextViews.get(3).setVisibility(View.GONE);
                holder.mTextViews.get(4).setVisibility(View.GONE);
                holder.mTextViews.get(5).setVisibility(View.GONE);
                holder.mTextViews.get(6).setVisibility(View.GONE);
                holder.mCheckBox.setVisibility(View.GONE);
                holder.mView.setVisibility(View.GONE);
                break;
            case 5:
                holder.mImageViews.get(0).setImageResource(R.drawable.a_message_v11);
                holder.mTextViews.get(0).setText(R.string.create_website);
                holder.mEditText.setHint(R.string.create_website_hint);
                if (mCreateData.getWebsite() != null) {
                    holder.mEditText.setText(mCreateData.getWebsite());
                }
                holder.mImageViews.get(0).setVisibility(View.VISIBLE);
                holder.mImageViews.get(1).setVisibility(View.GONE);
                holder.mImageViews.get(2).setVisibility(View.GONE);
                holder.mEditText.setVisibility(View.VISIBLE);
                holder.mTextViews.get(0).setVisibility(View.VISIBLE);
                holder.mTextViews.get(1).setVisibility(View.GONE);
                holder.mTextViews.get(2).setVisibility(View.GONE);
                holder.mTextViews.get(3).setVisibility(View.GONE);
                holder.mTextViews.get(4).setVisibility(View.GONE);
                holder.mTextViews.get(5).setVisibility(View.GONE);
                holder.mTextViews.get(6).setVisibility(View.GONE);
                holder.mCheckBox.setVisibility(View.GONE);
                holder.mView.setVisibility(View.GONE);
                break;
            case 7:
                holder.mImageViews.get(0).setImageResource(R.drawable.a_detail_v11);
                holder.mTextViews.get(0).setText(R.string.create_description);
                holder.mEditText.setHint(R.string.create_description_hint);
                if (mCreateData.getInfo() != null) {
                    holder.mEditText.setText(mCreateData.getInfo());
                }
                holder.mImageViews.get(0).setVisibility(View.VISIBLE);
                holder.mImageViews.get(1).setVisibility(View.GONE);
                holder.mImageViews.get(2).setVisibility(View.GONE);
                holder.mEditText.setVisibility(View.VISIBLE);
                holder.mTextViews.get(0).setVisibility(View.VISIBLE);
                holder.mTextViews.get(1).setVisibility(View.GONE);
                holder.mTextViews.get(2).setVisibility(View.GONE);
                holder.mTextViews.get(3).setVisibility(View.GONE);
                holder.mTextViews.get(4).setVisibility(View.VISIBLE);
                holder.mTextViews.get(5).setVisibility(View.GONE);
                holder.mTextViews.get(6).setVisibility(View.GONE);
                holder.mCheckBox.setVisibility(View.GONE);
                holder.mView.setVisibility(View.GONE);
                String temp = "0/150";
                holder.mTextViews.get(4).setText(temp);
                break;
            case 8:
                holder.mImageViews.get(0).setImageResource(R.drawable.camera_off_v11);
                holder.mTextViews.get(0).setText(R.string.create_add_photo);
                holder.mImageViews.get(0).setVisibility(View.VISIBLE);
                holder.mImageViews.get(1).setVisibility(View.VISIBLE);
                holder.mEditText.setVisibility(View.GONE);
                holder.mTextViews.get(0).setVisibility(View.VISIBLE);
                holder.mTextViews.get(1).setVisibility(View.GONE);
                holder.mTextViews.get(2).setVisibility(View.GONE);
                holder.mTextViews.get(3).setVisibility(View.GONE);
                holder.mTextViews.get(4).setVisibility(View.GONE);
                holder.mCheckBox.setVisibility(View.GONE);
                holder.mView.setVisibility(View.GONE);
                if (iaCreateAttraction.checkPhotoSize()) {
                    holder.mImageViews.get(2).setVisibility(View.VISIBLE);
                    File file = new File(iaCreateAttraction.getPhoto());
                    Glide.with(MyApplication.getInstance())
                            .load(Uri.fromFile(file))
                            .into(holder.mImageViews.get(1));
                    holder.mTextViews.get(5).setVisibility(View.VISIBLE);
                    String count = "+ " + (iaCreateAttraction.getPhotoSize() - 1) + " PHOTOS";
                    holder.mTextViews.get(5).setText(count);
                } else {
                    holder.mTextViews.get(5).setVisibility(View.GONE);
                    holder.mImageViews.get(1).setImageResource(R.drawable.add_off_v11);
                    holder.mImageViews.get(2).setVisibility(View.GONE);
                }
                break;
            case 9:
                holder.mImageViews.get(0).setImageResource(0);
                holder.mTextViews.get(6).setText(R.string.create_claim);
                holder.mImageViews.get(0).setVisibility(View.GONE);
                holder.mImageViews.get(1).setVisibility(View.GONE);
                holder.mImageViews.get(2).setVisibility(View.GONE);
                holder.mEditText.setVisibility(View.GONE);
                holder.mTextViews.get(0).setVisibility(View.GONE);
                holder.mTextViews.get(1).setVisibility(View.GONE);
                holder.mTextViews.get(2).setVisibility(View.GONE);
                holder.mTextViews.get(3).setVisibility(View.GONE);
                holder.mTextViews.get(4).setVisibility(View.GONE);
                holder.mTextViews.get(5).setVisibility(View.GONE);
                holder.mTextViews.get(6).setVisibility(View.VISIBLE);
                holder.mCheckBox.setVisibility(View.VISIBLE);
                holder.mView.setVisibility(View.GONE);
                break;
            case 6:
                holder.mImageViews.get(0).setImageResource(R.drawable.a_video);
                holder.mTextViews.get(0).setText(R.string.create_video);
                holder.mEditText.setHint(R.string.create_video_hint);
                holder.mEditText.setText(MyApplication.getAppData().getTubeUrl());
                holder.mImageViews.get(0).setVisibility(View.VISIBLE);
                holder.mImageViews.get(1).setVisibility(View.GONE);
                holder.mImageViews.get(2).setVisibility(View.GONE);
                holder.mEditText.setVisibility(View.VISIBLE);
                holder.mTextViews.get(0).setVisibility(View.VISIBLE);
                holder.mTextViews.get(1).setVisibility(View.GONE);
                holder.mTextViews.get(2).setVisibility(View.GONE);
                holder.mTextViews.get(3).setVisibility(View.GONE);
                holder.mTextViews.get(4).setVisibility(View.GONE);
                holder.mTextViews.get(5).setVisibility(View.GONE);
                holder.mTextViews.get(6).setVisibility(View.GONE);
                holder.mCheckBox.setVisibility(View.GONE);
                holder.mView.setVisibility(View.GONE);
            default:
                break;
        }
    }

    private String mName = "";
    private String mAddress = "";
    private String mType = "";
    private StringBuilder builderA = new StringBuilder();
    private StringBuilder builderB = new StringBuilder();
    private void init() {
        switch (MyApplication.getLanguageIndex()) {
            case 1:
                mName = mCreateData.getPlace().getPlace_tw();
                mAddress = mCreateData.getAddress().getAddress_tw();
                if (iaCreateAttraction.getStyleEntity() != null) {
                    mType = iaCreateAttraction.getStyleEntity().getMstitle_tw();
                }
                break;
            case 2:
                mName = mCreateData.getPlace().getPlace_ch();
                mAddress = mCreateData.getAddress().getAddress_ch();
                if (iaCreateAttraction.getStyleEntity() != null) {
                    mType = iaCreateAttraction.getStyleEntity().getMstitle_ch();
                }
                break;
            case 3:
                mName = mCreateData.getPlace().getPlace_jp();
                mAddress = mCreateData.getAddress().getAddress_jp();
                if (iaCreateAttraction.getStyleEntity() != null) {
                    mType = iaCreateAttraction.getStyleEntity().getMstitle_jp();
                }
                break;
            default:
                mName = mCreateData.getPlace().getPlace_en();
                mAddress = mCreateData.getAddress().getAddress_en();
                if (iaCreateAttraction.getStyleEntity() != null) {
                    mType = iaCreateAttraction.getStyleEntity().getMstitle_en();
                }
                break;
        }

        if (builderA.length() != 0) {
            builderA.delete(0, builderA.length());
        }
        if (builderB.length() != 0) {
            builderB.delete(0, builderB.length());
        }
        for (int i = 0; i < 4; i++) {
            if (!mCreateData.getTime().getMon().get(i).equals("")) {
                builderA.append(MyApplication.getInstance().getString(R.string.weekly_mon));
                builderA.append("\n");
                if (mCreateData.getTime().getMon().get(i).equals("24")) {
                    builderB.append(MyApplication.getInstance().getString(R.string.open_24_f));
                }
                builderB.append(mCreateData.getTime().getMon().get(i));
                if (mCreateData.getTime().getMon().get(i).equals("24")) {
                    builderB.append(MyApplication.getInstance().getString(R.string.open_24));
                }
                builderB.append("\n");
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!mCreateData.getTime().getTue().get(i).equals("")) {
                builderA.append(MyApplication.getInstance().getString(R.string.weekly_tue));
                builderA.append("\n");
                if (mCreateData.getTime().getTue().get(i).equals("24")) {
                    builderB.append(MyApplication.getInstance().getString(R.string.open_24_f));
                }
                builderB.append(mCreateData.getTime().getTue().get(i));
                if (mCreateData.getTime().getTue().get(i).equals("24")) {
                    builderB.append(MyApplication.getInstance().getString(R.string.open_24));
                }
                builderB.append("\n");
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!mCreateData.getTime().getWed().get(i).equals("")) {
                builderA.append(MyApplication.getInstance().getString(R.string.weekly_wed));
                builderA.append("\n");
                if (mCreateData.getTime().getWed().get(i).equals("24")) {
                    builderB.append(MyApplication.getInstance().getString(R.string.open_24_f));
                }
                builderB.append(mCreateData.getTime().getWed().get(i));
                if (mCreateData.getTime().getWed().get(i).equals("24")) {
                    builderB.append(MyApplication.getInstance().getString(R.string.open_24));
                }
                builderB.append("\n");
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!mCreateData.getTime().getThu().get(i).equals("")) {
                builderA.append(MyApplication.getInstance().getString(R.string.weekly_thu));
                builderA.append("\n");
                if (mCreateData.getTime().getThu().get(i).equals("24")) {
                    builderB.append(MyApplication.getInstance().getString(R.string.open_24_f));
                }
                builderB.append(mCreateData.getTime().getThu().get(i));
                if (mCreateData.getTime().getThu().get(i).equals("24")) {
                    builderB.append(MyApplication.getInstance().getString(R.string.open_24));
                }
                builderB.append("\n");
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!mCreateData.getTime().getFri().get(i).equals("")) {
                builderA.append(MyApplication.getInstance().getString(R.string.weekly_fri));
                builderA.append("\n");
                if (mCreateData.getTime().getFri().get(i).equals("24")) {
                    builderB.append(MyApplication.getInstance().getString(R.string.open_24_f));
                }
                builderB.append(mCreateData.getTime().getFri().get(i));
                if (mCreateData.getTime().getFri().get(i).equals("24")) {
                    builderB.append(MyApplication.getInstance().getString(R.string.open_24));
                }
                builderB.append("\n");
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!mCreateData.getTime().getSat().get(i).equals("")) {
                builderA.append(MyApplication.getInstance().getString(R.string.weekly_sat));
                builderA.append("\n");
                if (mCreateData.getTime().getSat().get(i).equals("24")) {
                    builderB.append(MyApplication.getInstance().getString(R.string.open_24_f));
                }
                builderB.append(mCreateData.getTime().getSat().get(i));
                if (mCreateData.getTime().getSat().get(i).equals("24")) {
                    builderB.append(MyApplication.getInstance().getString(R.string.open_24));
                }
                builderB.append("\n");
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!mCreateData.getTime().getSun().get(i).equals("")) {
                builderA.append(MyApplication.getInstance().getString(R.string.weekly_sun));
                builderA.append("\n");
                if (mCreateData.getTime().getSun().get(i).equals("24")) {
                    builderB.append(MyApplication.getInstance().getString(R.string.open_24_f));
                }
                builderB.append(mCreateData.getTime().getSun().get(i));
                if (mCreateData.getTime().getSun().get(i).equals("24")) {
                    builderB.append(MyApplication.getInstance().getString(R.string.open_24));
                }
                builderB.append("\n");
            }
        }
    }

    public ECreateAttraction getmCreateData() {
        return mCreateData;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    private SpannableString handleString(String word) {
        StringBuilder stringBuilder = new StringBuilder(word);
        int index = stringBuilder.indexOf("*");
        SpannableString spannableString = new SpannableString(stringBuilder.toString());
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), index, stringBuilder.length(), 0);
        return spannableString;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindViews({R.id.textTitleInItemCreateAttraction, R.id.textTimeAInItemCreateAttraction,
                R.id.textTimeBInItemCreateAttraction, R.id.textMessageInItemCreateAttraction,
                R.id.textCountInItemCreateAttraction, R.id.textPhotoCountInItemCreateAttraction,
                R.id.textCheckMessageInItemCreateAttraction})
        List<TextView> mTextViews;
        @BindView(R.id.editMessageInItemCreateAttraction)
        EditText mEditText;
        @BindViews({R.id.imageMarkInItemCreateAttraction, R.id.imageAInItemCreateAttraction, R.id.imageBInItemCreateAttraction})
        List<ImageView> mImageViews;
        @BindView(R.id.checkboxInItemCreateAttraction)
        CheckBox mCheckBox;
        @BindView(R.id.viewBarInItemCreateAttraction)
        View mView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxTextView.textChanges(mEditText)
                    .subscribe(new Observer<CharSequence>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(CharSequence charSequence) {
                            String count = charSequence.length() + "/150";
                            mTextViews.get(4).setText(count);
                            switch (getAdapterPosition()) {
                                case 0:
                                    switch (MyApplication.getLanguageIndex()) {
                                        case 0:
                                            mCreateData.getPlace().setPlace_en(charSequence.toString());
                                            break;
                                        case 1:
                                            mCreateData.getPlace().setPlace_tw(charSequence.toString());
                                            break;
                                        case 2:
                                            mCreateData.getPlace().setPlace_ch(charSequence.toString());
                                            break;
                                        default:
                                            mCreateData.getPlace().setPlace_jp(charSequence.toString());
                                            break;
                                    }
                                    break;
                                case 4:
                                    mCreateData.setPhone(charSequence.toString());
                                    break;
                                case 5:
                                    mCreateData.setWebsite(charSequence.toString());
                                    break;
                                case 7:
                                    switch (MyApplication.getLanguageIndex()) {
                                        case 0:
                                            mCreateData.setInfo(charSequence.toString());
                                            break;
                                        case 1:
                                            mCreateData.setInfo_tw(charSequence.toString());
                                            break;
                                        case 2:
                                            mCreateData.setInfo_ch(charSequence.toString());
                                            break;
                                        default:
                                            mCreateData.setInfo_jp(charSequence.toString());
                                            break;
                                    }
                                    break;
                                case 6:
                                    mCreateData.setSwiTubeURL(charSequence.toString());
                                    MyApplication.getAppData().setTubeUrl(charSequence.toString());
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViews.get(3))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (getAdapterPosition() == 1) {
                                iaCreateAttraction.switchPage(3);
                            } else {
                                iaCreateAttraction.switchPage(0);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViews.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaCreateAttraction.switchPage(1);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViews.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaCreateAttraction.switchPage(1);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViews.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaCreateAttraction.switchPage(2);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViews.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaCreateAttraction.switchPage(2);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViews.get(5))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaCreateAttraction.switchPage(2);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
