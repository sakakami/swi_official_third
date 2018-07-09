package com.switube.www.swiofficialthird.info.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.info.InterfaceInfo;
import com.switube.www.swiofficialthird.info.view.PhotoViewFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {
    private Context mContext;
    private InterfaceInfo mInterfaceInfo;

    public InfoAdapter(Context context, InterfaceInfo interfaceInfo) {
        mContext = context;
        mInterfaceInfo = interfaceInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_info_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (position) {
            case 0:
                setVisibility(holder, 1);
                break;
            case 1:
                setVisibility(holder, 0);
                holder.mTextTitle.setText("標籤");
                break;
            case 2:
                setVisibility(holder, 2);
                break;
            case 3:
                setVisibility(holder, 0);
                holder.mTextTitle.setText("特色");
                break;
            case 4:
                setVisibility(holder, 3);
                break;
            case 5:
                setVisibility(holder, 0);
                holder.mTextTitle.setText("網友評價");
                break;
            case 6:
                setVisibility(holder, 4);
                break;
            case 7:
                setVisibility(holder, 0);
                holder.mTextTitle.setText("聯絡方式");
                break;
            case 8:
                setVisibility(holder, 5);
                break;
            case 9:
                setVisibility(holder, 0);
                holder.mTextTitle.setText("關於我們");
                break;
            case 10:
                setVisibility(holder, 6);
                break;
            case 11:
                setVisibility(holder, 0);
                holder.mTextTitle.setText("影音資訊");
                break;
            case 12:
                setVisibility(holder, 7);
                break;
            case 13:
                setVisibility(holder, 0);
                holder.mTextTitle.setText("留言");
                break;
            case 14:
                setVisibility(holder, 8);
                break;
            case 15:
                setVisibility(holder, 9);
                break;
            case 16:
                setVisibility(holder, 10);
                break;
            default:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return 17;
    }

    private void setVisibility(@NotNull ViewHolder viewHolder, int i) {
        int size = viewHolder.mLayouts.size();
        for (int j = 0; j < size; j++) {
            viewHolder.mLayouts.get(j).setVisibility(View.GONE);
        }
        viewHolder.mTextTitle.setVisibility(View.GONE);
        switch (i) {
            case 1:
                viewHolder.mLayouts.get(0).setVisibility(View.VISIBLE);
                break;
            case 2:
                viewHolder.mLayouts.get(1).setVisibility(View.VISIBLE);
                break;
            case 3:
                viewHolder.mLayouts.get(2).setVisibility(View.VISIBLE);
                break;
            case 4:
                viewHolder.mLayouts.get(3).setVisibility(View.VISIBLE);
                break;
            case 5:
                viewHolder.mLayouts.get(4).setVisibility(View.VISIBLE);
                break;
            case 6:
                viewHolder.mLayouts.get(5).setVisibility(View.VISIBLE);
                break;
            case 7:
                viewHolder.mLayouts.get(6).setVisibility(View.VISIBLE);
                break;
            case 8:
                viewHolder.mLayouts.get(7).setVisibility(View.VISIBLE);
                break;
            case 9:
                viewHolder.mLayouts.get(8).setVisibility(View.VISIBLE);
                break;
            case 10:
                viewHolder.mLayouts.get(9).setVisibility(View.VISIBLE);
                break;
            default:
                viewHolder.mTextTitle.setVisibility(View.VISIBLE);
                break;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textTitleInInfoHolder) TextView mTextTitle;
        @BindViews({R.id.textRatioInHead, R.id.textMessageCountInHead, R.id.textCheckInInHead})
        List<TextView> mTextViewsInHead;
        @BindViews({R.id.imageLeftInHead, R.id.imageCenterInHead, R.id.imageRightInHead})
        List<ImageView> mImageViewsInHead;
        @BindView(R.id.ratingInHead) RatingBar mRatingBar;
        @BindViews({R.id.textTagA1InGoodsTag, R.id.textTagB1InGoodsTag, R.id.textCount1InGoodsTag,
                R.id.textTagA2InGoodsTag, R.id.textTagB2InGoodsTag, R.id.textCount2InGoodsTag,
                R.id.textTagA3InGoodsTag, R.id.textTagB3InGoodsTag, R.id.textCount3InGoodsTag,
                R.id.textTagA4InGoodsTag, R.id.textTagB4InGoodsTag, R.id.textCount4InGoodsTag,
                R.id.textTagA5InGoodsTag, R.id.textTagB5InGoodsTag, R.id.textCount5InGoodsTag,
                R.id.textTagA6InGoodsTag, R.id.textTagB6InGoodsTag, R.id.textCount6InGoodsTag,
                R.id.textTagA7InGoodsTag, R.id.textTagB7InGoodsTag, R.id.textCount7InGoodsTag,
                R.id.textTagA8InGoodsTag, R.id.textTagB8InGoodsTag, R.id.textCount8InGoodsTag,
                R.id.textTagA9InGoodsTag, R.id.textTagB9InGoodsTag, R.id.textCount9InGoodsTag,
                R.id.textTagA10InGoodsTag, R.id.textTagB10InGoodsTag, R.id.textCount10InGoodsTag,
                R.id.textShowMoreInGoodsTag})
        List<TextView> mTextViewsInGoodsTag;
        @BindViews({R.id.textTitle1InGoodsInfo, R.id.textMessage1InGoodsInfo,
                R.id.textTitle2InGoodsInfo, R.id.textMessage2InGoodsInfo,
                R.id.textTitle3InGoodsInfo, R.id.textMessage3InGoodsInfo,
                R.id.textTitle4InGoodsInfo, R.id.textMessage4InGoodsInfo,
                R.id.textTitle5InGoodsInfo, R.id.textMessage5InGoodsInfo,
                R.id.textTitle6InGoodsInfo, R.id.textMessage6InGoodsInfo,
                R.id.textTitle7InGoodsInfo, R.id.textMessage7InGoodsInfo,
                R.id.textTitle8InGoodsInfo, R.id.textMessage8InGoodsInfo,
                R.id.textTitle9InGoodsInfo, R.id.textMessage9InGoodsInfo,
                R.id.textTitle10InGoodsInfo, R.id.textMessage10InGoodsInfo,
                R.id.textTitle11InGoodsInfo, R.id.textMessage11InGoodsInfo,
                R.id.textTitle12InGoodsInfo, R.id.textMessage12InGoodsInfo,
                R.id.textTitle13InGoodsInfo, R.id.textMessage13InGoodsInfo,
                R.id.textTitle14InGoodsInfo, R.id.textMessage14InGoodsInfo,
                R.id.textTitle15InGoodsInfo, R.id.textMessage15InGoodsInfo,
                R.id.textShowMoreInGoodsInfo})
        List<TextView> mTextViewsInGoodsInfo;
        @BindViews({R.id.textTagA1InNetTag, R.id.textTagB1InNetTag, R.id.textCount1InNetTag,
                R.id.textTagA2InNetTag, R.id.textTagB2InNetTag, R.id.textCount2InNetTag,
                R.id.textTagA3InNetTag, R.id.textTagB3InNetTag, R.id.textCount3InNetTag,
                R.id.textTagA4InNetTag, R.id.textTagB4InNetTag, R.id.textCount4InNetTag,
                R.id.textTagA5InNetTag, R.id.textTagB5InNetTag, R.id.textCount5InNetTag})
        List<TextView> mTextViewsInNetTag;
        @BindViews({R.id.image1InNetTag, R.id.image2InNetTag, R.id.image3InNetTag,
                R.id.image4InNetTag, R.id.image5InNetTag})
        List<ImageView> mImageViewsInNetTag;
        @BindViews({R.id.textTitleInAttractionInfo, R.id.textAddressInAttractionInfo,
                R.id.textPhoneInAttractionInfo, R.id.textNetInAttractionInfo,
                R.id.textStateInAttractionInfo, R.id.textTimeInAttractionInfo,
                R.id.textShowMoreInAttractionInfo, R.id.textTimeDetailInAttractionInfo,
                R.id.textAddPhotoInAttractionInfo, R.id.textEditInAttractionInfo,
                R.id.textDirectionsInAttractionInfo, R.id.textSaveInAttractionInfo})
        List<TextView> mTextViewsInAttractionInfo;
        @BindViews({R.id.imageAddPhotoInAttractionInfo, R.id.imageEditInAttractionInfo,
                R.id.imageDirectionsInAttractionInfo, R.id.imageSaveInAttractionInfo})
        List<ImageView> mImageViewsInAttractionInfo;
        @BindViews({R.id.textIntroduceInIntroduce, R.id.textMoreInIntroduce})
        List<TextView> mTextViewsInIntroduce;
        @BindViews({R.id.textNameInMediaInfo, R.id.textWatchMoreInMediaInfo})
        List<TextView> mTextViewsInMediaInfo;
        @BindView(R.id.viewInMediaInfo) View mViewBarInMediaInfo;
        @BindView(R.id.imagePhotoInMediaInfo) ImageView mImagePhotoInMediaInfo;
        @BindViews({R.id.textIDInMessageHead, R.id.textDateInMessageHead,
                R.id.textMessageInMessageHead, R.id.textShowMoreInMessageHead})
        List<TextView> mTextViewsInMessageHead;
        @BindViews({R.id.imageHeadInMessageHead, R.id.imagePhotoInMessageHead, R.id.imageMoreInMessageHead})
        List<ImageView> mImageViewsInMessageHead;
        @BindViews({R.id.textTagInMessageBody, R.id.textCostInMessageBody, R.id.textSillInMessageBody,
                R.id.textSillDetailInMessageBody, R.id.textPermissionInMessageBody,
                R.id.textPermissionDetailInMessageBody, R.id.textPermissionLinkInMessageBody,
                R.id.textOkInMessageBody, R.id.textCountInMessageBody})
        List<TextView> mTextViewsInMessageBody;
        @BindViews({R.id.imagePermissionInMessageBody, R.id.imageMessageInMessageBody, R.id.imageShareInMessageBody,
                R.id.imageLoveInMessageBody, R.id.imageGoodInMessageBody, R.id.imageBadInMessageBody})
        List<ImageView> mImageViewsInMessageBody;
        @BindViews({R.id.textNameOneInItemMessageEnd, R.id.textMessageOneInItemMessageEnd, R.id.textMoreOneInItemMessageEnd,
                R.id.textNameTwoInItemMessageEnd, R.id.textMessageTwoInItemMessageEnd, R.id.textMoreTwoInItemMessageEnd,
                R.id.textNameThreeInItemMessageEnd, R.id.textMessageThreeInItemMessageEnd, R.id.textMoreThreeInItemMessageEnd})
        List<TextView> mTextViewsInMessageEnd;
        @BindViews({R.id.imagePhotoOneInItemMessageEnd, R.id.imagePhotoTwoInItemMessageEnd, R.id.imagePhotoThreeInItemMessageEnd,
                R.id.imagePhotoFourInItemMessageEnd, R.id.imageSendInItemMessageEnd})
        List<ImageView> mImageViewsInMessageEnd;
        @BindView(R.id.editMessageInItemMessageEnd)
        EditText mEditViewInMessageInItemMessageEnd;
        @BindViews({R.id.layoutHead, R.id.layoutGoodsTag, R.id.layoutGoodsInfo, R.id.layoutNetTag,
                R.id.layoutAttractionInfo, R.id.layoutIntroduce, R.id.layoutMediaInfo,
                R.id.layoutMessageHead, R.id.layoutMessageBody, R.id.layoutMessageEnd})
        List<RelativeLayout> mLayouts;

        private boolean opened = false;
        private boolean openedInTagInfo = false;
        private int likeCount = 0;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mRatingBar.setIsIndicator(false);
            mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    ratingBar.setRating(rating);
                    mTextViewsInHead.get(0).setText(String.valueOf(rating));
                }
            });
            RxView.clicks(mTextViewsInHead.get(2))
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Object o) {
                            mInterfaceInfo.handleSwitchPage(0);
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
            RxView.clicks(mTextViewsInGoodsTag.get(30))
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (opened) {
                                mTextViewsInGoodsTag.get(15).setVisibility(View.GONE);
                                mTextViewsInGoodsTag.get(16).setVisibility(View.GONE);
                                mTextViewsInGoodsTag.get(17).setVisibility(View.GONE);
                                mTextViewsInGoodsTag.get(18).setVisibility(View.GONE);
                                mTextViewsInGoodsTag.get(19).setVisibility(View.GONE);
                                mTextViewsInGoodsTag.get(20).setVisibility(View.GONE);
                                mTextViewsInGoodsTag.get(21).setVisibility(View.GONE);
                                mTextViewsInGoodsTag.get(22).setVisibility(View.GONE);
                                mTextViewsInGoodsTag.get(23).setVisibility(View.GONE);
                                mTextViewsInGoodsTag.get(24).setVisibility(View.GONE);
                                mTextViewsInGoodsTag.get(25).setVisibility(View.GONE);
                                mTextViewsInGoodsTag.get(26).setVisibility(View.GONE);
                                mTextViewsInGoodsTag.get(27).setVisibility(View.GONE);
                                mTextViewsInGoodsTag.get(28).setVisibility(View.GONE);
                                mTextViewsInGoodsTag.get(29).setVisibility(View.GONE);
                                opened = false;
                            } else {
                                mTextViewsInGoodsTag.get(15).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsTag.get(16).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsTag.get(17).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsTag.get(18).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsTag.get(19).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsTag.get(20).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsTag.get(21).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsTag.get(22).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsTag.get(23).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsTag.get(24).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsTag.get(25).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsTag.get(26).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsTag.get(27).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsTag.get(28).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsTag.get(29).setVisibility(View.VISIBLE);
                                opened = true;
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInGoodsInfo.get(30))
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (openedInTagInfo) {
                                openedInTagInfo = false;
                                mTextViewsInGoodsInfo.get(10).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(11).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(12).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(13).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(14).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(15).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(16).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(17).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(18).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(19).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(20).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(21).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(22).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(23).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(24).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(25).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(26).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(27).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(28).setVisibility(View.GONE);
                                mTextViewsInGoodsInfo.get(29).setVisibility(View.GONE);
                            } else {
                                openedInTagInfo = true;
                                mTextViewsInGoodsInfo.get(10).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(11).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(12).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(13).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(14).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(15).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(16).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(17).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(18).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(19).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(20).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(21).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(22).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(23).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(24).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(25).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(26).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(27).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(28).setVisibility(View.VISIBLE);
                                mTextViewsInGoodsInfo.get(29).setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInIntroduce.get(1))
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            int lines = mTextViewsInIntroduce.get(0).getMaxLines() == 5 ? Integer.MAX_VALUE : 5;
                            mTextViewsInIntroduce.get(0).setMaxLines(lines);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInMessageHead.get(3))
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            int lines = mTextViewsInMessageHead.get(2).getMaxLines() == 4 ? Integer.MAX_VALUE : 4;
                            mTextViewsInMessageHead.get(2).setMaxLines(lines);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsInMessageBody.get(4))
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_on_v1_2);
                            mImageViewsInMessageBody.get(5).setImageResource(R.drawable.unlike_v1_1);
                            likeCount++;
                            mTextViewsInMessageBody.get(8).setText(String.valueOf(likeCount));
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsInMessageBody.get(5))
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mImageViewsInMessageBody.get(5).setImageResource(R.drawable.unlike_on_v1_1);
                            mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_v1_1);
                            if (likeCount > 0) {
                                likeCount--;
                            }
                            mTextViewsInMessageBody.get(8).setText(String.valueOf(likeCount));
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsInHead.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            PhotoViewFragment.isShowCamera = true;
                            mInterfaceInfo.handleSwitchPage(1);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsInHead.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            PhotoViewFragment.isShowCamera = true;
                            mInterfaceInfo.handleSwitchPage(1);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsInHead.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            PhotoViewFragment.isShowCamera = true;
                            mInterfaceInfo.handleSwitchPage(1);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsInMessageHead.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            PhotoViewFragment.isShowCamera = false;
                            mInterfaceInfo.handleSwitchPage(1);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsInMessageBody.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handleEditFocus();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsInMessageEnd.get(4))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mInterfaceInfo.handleSwitchPage(2);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInMessageEnd.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mInterfaceInfo.handleSwitchPage(2);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInMessageEnd.get(4))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mInterfaceInfo.handleSwitchPage(2);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInMessageEnd.get(7))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mInterfaceInfo.handleSwitchPage(2);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInMessageEnd.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mInterfaceInfo.handleSwitchPage(3);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInMessageEnd.get(5))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mInterfaceInfo.handleSwitchPage(3);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInMessageEnd.get(8))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mInterfaceInfo.handleSwitchPage(3);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }

        private void handleEditFocus() {
            mEditViewInMessageInItemMessageEnd.requestFocusFromTouch();
            InputMethodManager inputMethodManager = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(mEditViewInMessageInItemMessageEnd, InputMethodManager.SHOW_FORCED);
        }
    }
}
