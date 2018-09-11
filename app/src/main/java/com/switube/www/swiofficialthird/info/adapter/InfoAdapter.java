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
                setVisibility(holder, 2);
                break;
            case 2:
                setVisibility(holder, 3);
                holder.mTextViewsInHead.get(7).setText(mRate);
                holder.mRatingInHead.setRating(Float.parseFloat(mRate));
                break;
            case 3:
                setVisibility(holder, 0);
                break;
            case 4:
                setVisibility(holder, 4);
                break;
            case 5:
                setVisibility(holder, 0);
                break;
            case 6:
                setVisibility(holder, 5);
                break;
            case 7:
                setVisibility(holder, 0);
                break;
            case 8:
                setVisibility(holder, 6);
                break;
            case 9:
                setVisibility(holder, 0);
                break;
            case 10:
                setVisibility(holder, 7);
                break;
            case 11:
                setVisibility(holder, 0);
                break;
            case 12:
                setVisibility(holder, 8);
                break;
            case 13:
                setVisibility(holder, 0);
                break;
            case 14:
                setVisibility(holder, 9);
                holder.mRatingBar.setRating(Float.parseFloat(mRate));
                break;
            case 15:
                setVisibility(holder, 0);
                break;
            case 16:
                setVisibility(holder, 10);
                break;
            case 17:
                setVisibility(holder, 11);
                break;
            case 18:
                setVisibility(holder, 12);
                break;
            default:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return 19;
    }

    private void setVisibility(@NotNull ViewHolder viewHolder, int i) {
        int size = viewHolder.mLayouts.size();
        for (int j = 0; j < size; j++) {
            viewHolder.mLayouts.get(j).setVisibility(View.GONE);
        }
        viewHolder.mTextTitleInInfoHolder.setVisibility(View.GONE);
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
            case 11:
                viewHolder.mLayouts.get(10).setVisibility(View.VISIBLE);
                break;
            case 12:
                viewHolder.mLayouts.get(11).setVisibility(View.VISIBLE);
                break;
            default:
                viewHolder.mTextTitleInInfoHolder.setVisibility(View.VISIBLE);
                break;
        }
    }

    private String mRate = "4.8";
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindViews({R.id.layoutHeadPicture, R.id.layoutHeadTitle, R.id.layoutHead, R.id.layoutGoodsInfo,
                R.id.layoutGoodsTag, R.id.layoutAttractionInfo, R.id.layoutIntroduce, R.id.layoutMediaInfo,
                R.id.layoutRating, R.id.layoutMessageHead, R.id.layoutMessageBody, R.id.layoutMessageEnd})
        List<RelativeLayout> mLayouts;
        @BindView(R.id.textTitleInInfoHolder)
        TextView mTextTitleInInfoHolder;
        @BindView(R.id.imagePictureInHeadPicture)
        ImageView mImagePictureInHeadPicture;
        @BindView(R.id.textTitleInHeadTitle)
        TextView mTextTitleInHeadTitle;
        @BindViews({R.id.textTitleSubInHead, R.id.textSendInHead, R.id.textLikeInHead, R.id.textTrackInHead,
                R.id.textRecommendInHead, R.id.textSaveInHead, R.id.textServiceInfoInHead, R.id.textRatioInHead,
                R.id.textStateInHead})
        List<TextView> mTextViewsInHead;
        @BindViews({R.id.imageLikeInHead, R.id.imageTrackInHead, R.id.imageRecommendInHead, R.id.imageSaveInHead})
        List<ImageView> mImageViewsInHead;
        @BindView(R.id.ratingInHead)
        RatingBar mRatingInHead;
        @BindViews({R.id.imagePicture1InGoodsInfo, R.id.imagePicture2InGoodsInfo, R.id.imagePicture3InGoodsInfo,
                R.id.imagePicture4InGoodsInfo, R.id.imagePicture5InGoodsInfo, R.id.imageLicenseInGoodsInfo})
        List<ImageView> mImageViewsInGoodsInfo;
        @BindViews({R.id.textTitle1InGoodsInfo, R.id.textCount1InGoodsInfo, R.id.textTitle2InGoodsInfo,
                R.id.textCount2InGoodsInfo, R.id.textTitle3InGoodsInfo, R.id.textCount3InGoodsInfo,
                R.id.textTitle4InGoodsInfo, R.id.textCount4InGoodsInfo, R.id.textTitle5InGoodsInfo,
                R.id.textCount5InGoodsInfo, R.id.textTitleInGoodsInfo})
        List<TextView> mTextViewsInGoodsInfo;
        @BindViews({R.id.imagePicture1InGoodsTag, R.id.imagePicture2InGoodsTag, R.id.imagePicture3InGoodsTag,
                R.id.imageHead1InGoodsTag, R.id.imageHead2InGoodsTag, R.id.imageHead3InGoodsTag,
                R.id.imageHead4InGoodsTag, R.id.imageHead5InGoodsTag, R.id.imageHead6InGoodsTag,
                R.id.imageHead7InGoodsTag, R.id.imageHead8InGoodsTag})
        List<ImageView> mImageViewsInGoodsTag;
        @BindViews({R.id.textTagA1InGoodsTag, R.id.textTagB1InGoodsTag, R.id.textTagA2InGoodsTag,
                R.id.textTagB2InGoodsTag, R.id.textTagA3InGoodsTag, R.id.textTagB3InGoodsTag,
                R.id.textContentInGoodsTag, R.id.textTitleInGoodsTag})
        List<TextView> mTextViewsInGoodsTag;
        @BindViews({R.id.imageHead1InAttractionInfo, R.id.imageHead2InAttractionInfo,
                R.id.imageHead3InAttractionInfo, R.id.imageHead4InAttractionInfo})
        List<ImageView> mImageViewsInAttractionInfo;
        @BindViews({R.id.textAddressInAttractionInfo, R.id.textPhoneInAttractionInfo, R.id.textStateInAttractionInfo,
                R.id.textTimeInAttractionInfo, R.id.textShowMoreInAttractionInfo, R.id.textTimeDetailInAttractionInfo,
                R.id.textTitleInAttractionInfo})
        List<TextView> mTextViewsInAttractionInfo;
        @BindViews({R.id.textTitleInIntroduce, R.id.textIntroduceInIntroduce})
        List<TextView> mTextViewsInIntroduce;
        @BindView(R.id.imagePhotoInMediaInfo)
        ImageView mImageViewInMediaInfo;
        @BindViews({R.id.textNameInMediaInfo, R.id.textWatchMoreInMediaInfo, R.id.textTitleInMediaInfo})
        List<TextView> mTextViewsInMediaIndo;
        @BindView(R.id.imageHeadInRating)
        ImageView mImageHeadInRating;
        @BindViews({R.id.textMessage1InRating, R.id.textMessage2InRating})
        List<TextView> mTextViewsInRating;
        @BindView(R.id.ratingInRating)
        RatingBar mRatingBar;
        @BindViews({R.id.imageHeadInMessageHead, R.id.imagePhotoInMessageHead, R.id.imageMoreInMessageHead})
        List<ImageView> mImageViewsInMessageHead;
        @BindViews({R.id.textIDInMessageHead, R.id.textDateInMessageHead, R.id.textMessageInMessageHead,
                R.id.textShowMoreInMessageHead})
        List<TextView> mTextViewsInMessageHead;
        @BindViews({R.id.imagePermissionInMessageBody, R.id.imageMessageInMessageBody, R.id.imageShareInMessageBody,
                R.id.imageLoveInMessageBody, R.id.imageBadInMessageBody, R.id.imageGoodInMessageBody})
        List<ImageView> mImageViewsInMessageBody;
        @BindViews({R.id.textTagInMessageBody, R.id.textCostInMessageBody, R.id.textSillInMessageBody,
                R.id.textSillDetailInMessageBody, R.id.textPermissionInMessageBody, R.id.textPermissionDetailInMessageBody,
                R.id.textPermissionLinkInMessageBody, R.id.textOkInMessageBody, R.id.textCountInMessageBody})
        List<TextView> mTextViewsInMessageBody;
        @BindViews({R.id.imagePhotoOneInMessageEnd, R.id.imagePhotoTwoInMessageEnd,
                R.id.imagePhotoThreeInMessageEnd, R.id.imagePhotoFourInMessageEnd, R.id.imageSendInMessageEnd})
        List<ImageView> mImageViewsInMessageEnd;
        @BindViews({R.id.textNameOneInMessageEnd, R.id.textMessageOneInMessageEnd, R.id.textMoreOneInMessageEnd,
                R.id.textNameTwoInMessageEnd, R.id.textMessageTwoInMessageEnd, R.id.textMoreTwoInMessageEnd,
                R.id.textNameThreeInMessageEnd, R.id.textMessageThreeInMessageEnd, R.id.textMoreThreeInMessageEnd})
        List<TextView> mTextViewsInMessageEnd;
        @BindView(R.id.editMessageInMessageEnd)
        EditText mEditTextInMessageEnd;

        private boolean opened = false;
        private boolean openedInTagInfo = false;
        private int likeCount = 0;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //mRatingInHead.setIsIndicator(false);
            mRatingInHead.setRating(Float.parseFloat(mRate));
            /*mRatingInHead.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    ratingBar.setRating(v);
                }
            });*/
            mRatingBar.setIsIndicator(false);
            mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    ratingBar.setRating(rating);
                    //mRatingInHead.setRating(rating);
                    mRate = String.valueOf(rating);
                }
            });
            RxView.clicks(mTextViewsInHead.get(1))
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
            RxView.clicks(mTextViewsInIntroduce.get(1))
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            int lines = mTextViewsInIntroduce.get(1).getMaxLines() == 5 ? Integer.MAX_VALUE : 5;
                            mTextViewsInIntroduce.get(1).setMaxLines(lines);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            /*RxView.clicks(mTextViewsInMessageHead.get(3))
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
                    });*/
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
            RxView.clicks(mImagePictureInHeadPicture)
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
            /*RxView.clicks(mImageViewsInHead.get(1))
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
                    });*/
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
            mEditTextInMessageEnd.requestFocusFromTouch();
            InputMethodManager inputMethodManager = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(mEditTextInMessageEnd, InputMethodManager.SHOW_FORCED);
        }
    }
}
