package com.switube.www.swiofficialthird.create.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.create.CreateAttractionEntity;
import com.switube.www.swiofficialthird.create.gson.AttractionDetailEntity;
import com.switube.www.swiofficialthird.create.view.ICreateAttraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CreateAttractionAdapter extends RecyclerView.Adapter<CreateAttractionAdapter.ViewHolder> {
    private Context mContext;
    private CreateAttractionEntity mCreateData;
    private ICreateAttraction mICreateAttraction;
    //0->en,1->tw,2->cn,3->jp
    private int mLanguageIndex = 0;
    public CreateAttractionAdapter(Context context, CreateAttractionEntity createAttractionEntity, ICreateAttraction iCreateAttraction) {
        mContext = context;
        mCreateData = createAttractionEntity;
        mICreateAttraction = iCreateAttraction;
        switch (Locale.getDefault().getLanguage()) {
            case "zh":
                switch (Locale.getDefault().getCountry()) {
                    case "TW":
                        mLanguageIndex = 1;
                        break;
                    case "CN":
                        mLanguageIndex = 2;
                        break;
                    default:
                        mLanguageIndex = 0;
                        break;
                }
                break;
            case "jp":
                mLanguageIndex = 3;
                break;
            default:
                mLanguageIndex = 0;
                break;
        }
        init();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.holder_item_create_attraction, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (position) {
            case 0:
                holder.mImageViews.get(0).setImageResource(R.drawable.a0001);
                holder.mTextViews.get(0).setText("地標名稱");
                holder.mEditText.setHint("Place name");
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
                holder.mImageViews.get(0).setImageResource(R.drawable.a0002);
                holder.mTextViews.get(0).setText("地標地址");
                holder.mEditText.setHint("Address");
                holder.mEditText.setText(mAddress);
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
            case 2:
                holder.mImageViews.get(0).setImageResource(R.drawable.a0003);
                holder.mTextViews.get(0).setText("地標類型");
                holder.mEditText.setHint("Category");
                holder.mTextViews.get(3).setText(mType);
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
                holder.mImageViews.get(0).setImageResource(R.drawable.a0004);
                holder.mTextViews.get(0).setText("營業時間");
                holder.mTextViews.get(1).setText(builderA.toString());
                holder.mTextViews.get(2).setText(builderB.toString());
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
                holder.mImageViews.get(0).setImageResource(R.drawable.a0001);
                holder.mTextViews.get(0).setText("聯絡電話");
                holder.mEditText.setHint("Primary phone number");
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
                holder.mImageViews.get(0).setImageResource(R.drawable.a0002);
                holder.mTextViews.get(0).setText("網址");
                holder.mEditText.setHint("Official website");
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
            case 6:
                holder.mImageViews.get(0).setImageResource(R.drawable.a0003);
                holder.mTextViews.get(0).setText("地標介紹");
                holder.mEditText.setHint("Introduce");
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
                break;
            case 7:
                holder.mImageViews.get(0).setImageResource(R.drawable.a0003);
                holder.mTextViews.get(0).setText("上傳圖片");
                holder.mImageViews.get(0).setVisibility(View.VISIBLE);
                holder.mImageViews.get(1).setVisibility(View.VISIBLE);
                holder.mImageViews.get(2).setVisibility(View.GONE);
                holder.mEditText.setVisibility(View.GONE);
                holder.mTextViews.get(0).setVisibility(View.VISIBLE);
                holder.mTextViews.get(1).setVisibility(View.GONE);
                holder.mTextViews.get(2).setVisibility(View.GONE);
                holder.mTextViews.get(3).setVisibility(View.GONE);
                holder.mTextViews.get(4).setVisibility(View.GONE);
                holder.mTextViews.get(5).setVisibility(View.VISIBLE);
                holder.mTextViews.get(5).setVisibility(View.GONE);
                holder.mCheckBox.setVisibility(View.GONE);
                holder.mView.setVisibility(View.GONE);
                break;
            case 8:
                holder.mImageViews.get(0).setImageResource(R.drawable.a0004);
                holder.mTextViews.get(6).setText("聲明商家所有權");
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
            default:
                break;
        }
    }

    private String mName = "";
    private String mAddress = "";
    private String mType = "";
    private String mTime = "";
    private StringBuilder builderA = new StringBuilder();
    private StringBuilder builderB = new StringBuilder();
    private void init() {
        switch (mLanguageIndex) {
            case 0:
                mName = mCreateData.getPlace().getPlace_en();
                mAddress = mCreateData.getAddress().getAddress_en();
                if (mICreateAttraction.getStyleEntity() != null) {
                    mType = mICreateAttraction.getStyleEntity().getMstitle_en();
                }
                break;
            case 1:
                mName = mCreateData.getPlace().getPlace_tw();
                mAddress = mCreateData.getAddress().getAddress_tw();
                if (mICreateAttraction.getStyleEntity() != null) {
                    mType = mICreateAttraction.getStyleEntity().getMstitle_tw();
                }
                break;
            case 2:
                mName = mCreateData.getPlace().getPlace_ch();
                mAddress = mCreateData.getAddress().getAddress_ch();
                if (mICreateAttraction.getStyleEntity() != null) {
                    mType = mICreateAttraction.getStyleEntity().getMstitle_ch();
                }
                break;
            case 3:
                mName = mCreateData.getPlace().getPlace_jp();
                mAddress = mCreateData.getAddress().getAddress_jp();
                if (mICreateAttraction.getStyleEntity() != null) {
                    mType = mICreateAttraction.getStyleEntity().getMstitle_jp();
                }
                break;
            default:
                break;
        }

        Log.e("hasTime", "not Null");

        if (builderA.length() != 0) {
            builderA.delete(0, builderA.length());
        }
        if (builderB.length() != 0) {
            builderB.delete(0, builderB.length());
        }
        for (int i = 0; i < 4; i++) {
            Log.e("mon", mCreateData.getTime().getMon().get(i));
            if (!mCreateData.getTime().getMon().get(i).equals("")) {
                builderA.append("星期一\n");
                builderB.append(mCreateData.getTime().getMon().get(i));
                builderB.append("\n");
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!mCreateData.getTime().getTue().get(i).equals("")) {
                builderA.append("星期二\n");
                builderB.append(mCreateData.getTime().getTue().get(i));
                builderB.append("\n");
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!mCreateData.getTime().getWed().get(i).equals("")) {
                builderA.append("星期三\n");
                builderB.append(mCreateData.getTime().getWed().get(i));
                builderB.append("\n");
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!mCreateData.getTime().getThu().get(i).equals("")) {
                builderA.append("星期四\n");
                builderB.append(mCreateData.getTime().getThu().get(i));
                builderB.append("\n");
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!mCreateData.getTime().getFri().get(i).equals("")) {
                builderA.append("星期五\n");
                builderB.append(mCreateData.getTime().getFri().get(i));
                builderB.append("\n");
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!mCreateData.getTime().getSat().get(i).equals("")) {
                builderA.append("星期六\n");
                builderB.append(mCreateData.getTime().getSat().get(i));
                builderB.append("\n");
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!mCreateData.getTime().getSun().get(i).equals("")) {
                builderA.append("星期日\n");
                builderB.append(mCreateData.getTime().getSun().get(i));
                builderB.append("\n");
            }
        }
    }

    public CreateAttractionEntity getmCreateData() {
        return mCreateData;
    }

    @Override
    public int getItemCount() {
        return 9;
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
                            switch (getAdapterPosition()) {
                                case 0:
                                    switch (mLanguageIndex) {
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
                                case 1:
                                    //mCreateData.setAddress(charSequence.toString());
                                    break;
                                case 4:
                                    mCreateData.setPhone(charSequence.toString());
                                    break;
                                case 5:
                                    mCreateData.setWebsite(charSequence.toString());
                                    break;
                                case 6:
                                    mCreateData.setInfo(charSequence.toString());
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
                            mICreateAttraction.switchPage(0);
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
                            mICreateAttraction.switchPage(1);
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
                            mICreateAttraction.switchPage(1);
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
                            mICreateAttraction.switchPage(2);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
