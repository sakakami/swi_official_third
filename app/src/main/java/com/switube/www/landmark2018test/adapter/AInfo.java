package com.switube.www.landmark2018test.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAInfo;
import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.util.AppConstant;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AInfo extends RecyclerView.Adapter<AInfo.ViewHolder> {
    private IAInfo iaInfo;
    private GInfoData gInfoData;
    private int customerCount1 = 0;
    private int customerCount2 = 0;
    private int customerCount3 = 0;
    private int customerCount4 = 0;
    private int customerCount5 = 0;
    private String style;
    private String item;
    private String rateForTop;
    public AInfo(String style, String item, IAInfo iaInfo, GInfoData gInfoData) {
        this.iaInfo = iaInfo;
        this.gInfoData = gInfoData;
        this.style = style;
        this.item = item;
        if (gInfoData.getData().get(0).getRating().length() > 3) {
            StringBuilder stringBuilder = new StringBuilder(gInfoData.getData().get(0).getRating());
            stringBuilder.delete(3, stringBuilder.length());
            rateForTop = stringBuilder.toString();
        } else {
            rateForTop = gInfoData.getData().get(0).getRating();
        }
        mRate = gInfoData.getData().get(0).getRating();
        uRate = gInfoData.getData().get(0).getUrating();
        init();
        handleTime();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_info_holder, parent, false));
    }

    private StringBuilder stringBuilder = new StringBuilder();
    private SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int size;
        String count;
        switch (position) {
            case 0:
                setVisibility(holder, 1);
                if (gInfoData.getData().get(0).getPhoto().size() > 0) {
                    Glide.with(holder.itemView.getContext())
                            .load(Uri.parse(AppConstant.BASE_URL + gInfoData.getData().get(0).getPhoto().get(0)))
                            .into(holder.mImagePictureInHeadPicture);
                } else {
                    Glide.with(MyApplication.getInstance())
                            .load(R.drawable.none_1)
                            .into(holder.mImagePictureInHeadPicture);
                }
                break;
            case 1:
                setVisibility(holder, 2);
                holder.mTextTitleInHeadTitle.setText(gInfoData.getData().get(0).getPlace());
                break;
            case 2:
                setVisibility(holder, 3);
                holder.mTextViewsInHead.get(7).setText(rateForTop);
                holder.mTextViewsInHead.get(0).setText(style);
                holder.mTextViewsInHead.get(6).setText(item);
                if (gInfoData.getData().get(0).getColl().length() > 0) {
                    holder.mImageViewsInHead.get(2).setImageResource(R.drawable.mark_on_v14);
                } else {
                    holder.mImageViewsInHead.get(2).setImageResource(R.drawable.mark_off_v12);
                }
                switch (isOpen) {
                    case "0":
                        holder.mTextViewsInHead.get(8).setText(R.string.map_status_open);
                        break;
                    case "1":
                        holder.mTextViewsInHead.get(8).setText(R.string.map_status_close);
                        break;
                    default:
                        holder.mTextViewsInHead.get(8).setText(R.string.no_time_data);
                        break;
                }
                holder.mRatingInHead.setRating(Float.parseFloat(rateForTop));
                break;
            case 3:
                setVisibility(holder, -1);
                break;
            case 4:
                setVisibility(holder, -1);
                break;
            case 5:
                if (gInfoData.getData().get(0).getLabel().size() == 0) {
                    setVisibility(holder, -1);
                } else {
                    setVisibility(holder, 0);
                }
                break;
            case 6:
                if (gInfoData.getData().get(0).getLabel().size() == 0) {
                    setVisibility(holder, -1);
                } else  {
                    setVisibility(holder, 5);
                    holder.mImageViewsInGoodsTag.get(2).setVisibility(View.GONE);
                    holder.mTextViewsInGoodsTag.get(2).setVisibility(View.GONE);
                    holder.mTextViewsInGoodsTag.get(7).setVisibility(View.GONE);
                    holder.mImageViewsInGoodsTag.get(1).setVisibility(View.GONE);
                    holder.mTextViewsInGoodsTag.get(1).setVisibility(View.GONE);
                    holder.mTextViewsInGoodsTag.get(6).setVisibility(View.GONE);
                    stringBuilder.delete(0, stringBuilder.length());
                    stringBuilder.append("·   #");
                    stringBuilder.append(gInfoData.getData().get(0).getLabel().get(0));
                    size = stringBuilder.indexOf(":");
                    stringBuilder.delete(size, stringBuilder.length());
                    holder.mTextViewsInGoodsTag.get(0).setText(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.length());
                    stringBuilder.append(gInfoData.getData().get(0).getLabel().get(0));
                    size = stringBuilder.indexOf(":");
                    stringBuilder.delete(0, size + 1);
                    holder.mTextViewsInGoodsTag.get(5).setText(stringBuilder.toString());
                    if (gInfoData.getData().get(0).getLabel().size() > 1) {
                        holder.mImageViewsInGoodsTag.get(1).setVisibility(View.VISIBLE);
                        holder.mTextViewsInGoodsTag.get(1).setVisibility(View.VISIBLE);
                        holder.mTextViewsInGoodsTag.get(6).setVisibility(View.VISIBLE);
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder.append("·   #");
                        stringBuilder.append(gInfoData.getData().get(0).getLabel().get(1));
                        size = stringBuilder.indexOf(":");
                        stringBuilder.delete(size, stringBuilder.length());
                        holder.mTextViewsInGoodsTag.get(1).setText(stringBuilder.toString());
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder.append(gInfoData.getData().get(0).getLabel().get(1));
                        size = stringBuilder.indexOf(":");
                        stringBuilder.delete(0, size + 1);
                        holder.mTextViewsInGoodsTag.get(6).setText(stringBuilder.toString());
                    }
                    if (gInfoData.getData().get(0).getLabel().size() > 2) {
                        holder.mImageViewsInGoodsTag.get(2).setVisibility(View.VISIBLE);
                        holder.mTextViewsInGoodsTag.get(2).setVisibility(View.VISIBLE);
                        holder.mTextViewsInGoodsTag.get(7).setVisibility(View.VISIBLE);
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder.append("·   #");
                        stringBuilder.append(gInfoData.getData().get(0).getLabel().get(2));
                        size = stringBuilder.indexOf(":");
                        stringBuilder.delete(size, stringBuilder.length());
                        holder.mTextViewsInGoodsTag.get(2).setText(stringBuilder.toString());
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder.append(gInfoData.getData().get(0).getLabel().get(2));
                        size = stringBuilder.indexOf(":");
                        stringBuilder.delete(0, size + 1);
                        holder.mTextViewsInGoodsTag.get(7).setText(stringBuilder.toString());
                    }
                }
                break;
            case 7:
                setVisibility(holder, 0);
                break;
            case 8:
                setVisibility(holder, 6);
                if (gInfoData.getData().get(0).getMaid().equals(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"))) {
                    holder.mImageViewsInAttractionInfo.get(4).setVisibility(View.VISIBLE);
                } else {
                    holder.mImageViewsInAttractionInfo.get(4).setVisibility(View.GONE);
                }
                if (gInfoData.getData().get(0).getAddr().length() > 0) {
                    holder.mTextViewsInAttractionInfo.get(0).setText(gInfoData.getData().get(0).getAddr());
                } else {
                    holder.mTextViewsInAttractionInfo.get(0).setVisibility(View.GONE);
                }
                if (gInfoData.getData().get(0).getPhone().length() > 0) {
                    holder.mTextViewsInAttractionInfo.get(1).setText(gInfoData.getData().get(0).getPhone());
                } else {
                    holder.mTextViewsInAttractionInfo.get(1).setText(R.string.no_data);
                }
                holder.mTextViewsInAttractionInfo.get(5).setText(detailTime);
                if (gInfoData.getData().get(0).getWebsite().equals("")) {
                    holder.mTextViewsInAttractionInfo.get(7).setVisibility(View.GONE);
                    holder.mImageViewsInAttractionInfo.get(2).setVisibility(View.GONE);
                } else {
                    holder.mTextViewsInAttractionInfo.get(7).setVisibility(View.VISIBLE);
                    holder.mImageViewsInAttractionInfo.get(2).setVisibility(View.VISIBLE);
                    holder.mTextViewsInAttractionInfo.get(7).setText(gInfoData.getData().get(0).getWebsite());
                }
                break;
            case 9:
                if (gInfoData.getData().get(0).getInfo().length() > 0) {
                    setVisibility(holder, 0);
                } else {
                    setVisibility(holder, -1);
                }
                break;
            case 10:
                if (gInfoData.getData().get(0).getInfo().length() > 0) {
                    setVisibility(holder, 7);
                    holder.mTextViewsInIntroduce.get(1).setText(gInfoData.getData().get(0).getInfo());
                    if (gInfoData.getData().get(0).getMaid().equals(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"))) {
                        holder.imageViewInIntroduce.setVisibility(View.VISIBLE);
                    } else {
                        holder.imageViewInIntroduce.setVisibility(View.GONE);
                    }
                } else {
                    setVisibility(holder, -1);
                }
                break;
            case 11:
                if (gInfoData.getData().get(0).getTubeWeb().size() == 0) {
                    setVisibility(holder, -1);
                } else {
                    setVisibility(holder, 0);
                }
                break;
            case 12:
                if (gInfoData.getData().get(0).getTubeWeb().size() == 0) {
                    setVisibility(holder, -1);
                } else {
                    if (gInfoData.getData().get(0).getTubeWeb().get(0).getUrl().equals("")) {
                        setVisibility(holder, -1);
                    } else {
                        String url = gInfoData.getData().get(0).getTubeWeb().get(0).getUrl();
                        if (url.contains("www.switube.com")) {
                            setVisibility(holder, 8);
                            holder.imageViewListInMediaInfo.get(0).setVisibility(View.VISIBLE);
                            Glide.with(holder.itemView.getContext())
                                    .load(Uri.parse(gInfoData.getData().get(0).getTubeWeb().get(0).getImg()))
                                    .into(holder.imageViewListInMediaInfo.get(0));
                        } else {
                            setVisibility(holder, -1);
                        }
                    }
                    if (gInfoData.getData().get(0).getTubeWeb().get(0).getTitle().equals("")) {
                        holder.mTextViewsInMediaInfo.get(0).setVisibility(View.GONE);
                    } else {
                        holder.mTextViewsInMediaInfo.get(0).setVisibility(View.VISIBLE);
                        holder.mTextViewsInMediaInfo.get(0).setText(gInfoData.getData().get(0).getTubeWeb().get(0).getTitle());
                    }
                    if (gInfoData.getData().get(0).getMaid().equals(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"))) {
                        holder.imageViewListInMediaInfo.get(1).setVisibility(View.VISIBLE);
                    } else {
                        holder.imageViewListInMediaInfo.get(1).setVisibility(View.GONE);
                    }
                }
                break;
            case 13:
                setVisibility(holder, 0);
                break;
            case 14:
                setVisibility(holder, 9);
                holder.mRatingBar.setRating(Float.parseFloat(uRate));
                stringBuilder.delete(0, stringBuilder.length());
                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                if (stringBuilder.toString().equals("null")) {
                    holder.mImageHeadInRating.setImageResource(R.drawable.person_unlogin);
                } else {
                    stringBuilder.delete(0, 2);
                    Glide.with(holder.itemView.getContext())
                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                            .into(holder.mImageHeadInRating);
                }
                break;
            case 15:
                if (gInfoData.getData().get(0).getArticle().size() > 0) {
                    setVisibility(holder, 0);
                } else {
                    setVisibility(holder, -1);
                }
                break;
            default:
                if (position < 16 + customerCount1) {
                    switch (position - 15) {
                        case 1:
                            setVisibility(holder, 10);
                            holder.mTextViewsInMessageHead.get(0).setText(gInfoData.getData().get(0).getArticle().get(0).getManame());
                            Glide.with(holder.itemView.getContext())
                                    .load(Uri.parse(AppConstant.BASE_URL + gInfoData.getData().get(0).getArticle().get(0).getMaimg()))
                                    .into(holder.mImageViewsInMessageHead.get(0));
                            String date = gInfoData.getData().get(0).getArticle().get(0).getTime_txt() + " ·";
                            holder.mTextViewsInMessageHead.get(1).setText(date);
                            if (gInfoData.getData().get(0).getArticle().get(0).getPrivacy().equals("1")) {
                                holder.mImageViewsInMessageHead.get(1).setImageResource(R.drawable.pr_public_v1_1);
                            } else {
                                holder.mImageViewsInMessageHead.get(1).setImageResource(R.drawable.pr_anonymous_v1_1);
                            }
                            if (gInfoData.getData().get(0).getArticle().get(0).getMaid().equals(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"))) {
                                if (gInfoData.getData().get(0).getArticle().get(0).getCoding().equals("0")) {
                                    holder.mImageViewsInMessageHead.get(2).setVisibility(View.VISIBLE);
                                } else {
                                    holder.mImageViewsInMessageHead.get(2).setVisibility(View.GONE);
                                }
                            } else {
                                holder.mImageViewsInMessageHead.get(2).setVisibility(View.GONE);
                            }
                            break;
                        case 2:
                            setVisibility(holder, 18);
                            if (gInfoData.getData().get(0).getArticle().get(0).getCoding().equals("1")) {
                                holder.mTextViewsInMessageHeadCustomer.get(1).setVisibility(View.VISIBLE);
                                int length = gInfoData.getData().get(0).getArticle().get(0).getTitle().get(0).length();
                                StringBuilder stringBuilder = new StringBuilder(gInfoData.getData().get(0).getArticle().get(0).getManame());
                                stringBuilder.append(" 建立了地標『");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(0).getTitle().get(0));
                                stringBuilder.append("』");
                                int lengthAll = stringBuilder.length();
                                SpannableString spannableString = new SpannableString(stringBuilder.toString());
                                spannableString.setSpan(new UnderlineSpan(), lengthAll - length - 1, lengthAll - 1, 0);
                                spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), lengthAll - length - 1, lengthAll - 1, 0);
                                holder.mTextViewsInMessageHeadCustomer.get(0).setText(spannableString);
                                stringBuilder.delete(0, lengthAll);
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(0).getManame());
                                stringBuilder.append(" created a new place ");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(0).getTitle().get(1));
                                stringBuilder.append(".");
                                length = gInfoData.getData().get(0).getArticle().get(0).getTitle().get(1).length();
                                lengthAll = stringBuilder.length();
                                spannableString = new SpannableString(stringBuilder.toString());
                                spannableString.setSpan(new UnderlineSpan(), lengthAll - length - 1, lengthAll - 1, 0);
                                spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), lengthAll - length - 1, lengthAll - 1, 0);
                                holder.mTextViewsInMessageHeadCustomer.get(1).setText(spannableString);
                            } else {
                                holder.mTextViewsInMessageHeadCustomer.get(1).setVisibility(View.GONE);
                                holder.mTextViewsInMessageHeadCustomer.get(0).setText(gInfoData.getData().get(0).getArticle().get(0).getContent());
                            }
                            break;
                        case 3:
                            setVisibility(holder, 16);
                            if (gInfoData.getData().get(0).getArticle().get(0).getImg().size() != 0) {
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL + gInfoData.getData().get(0).getArticle().get(0).getImg().get(0)))
                                        .into(holder.mImageInMessageHeadPhoto);
                                holder.mTextViewsInMessageHeadPhoto.get(1).setVisibility(View.VISIBLE);
                                String photoCount = "1/" + String.valueOf(gInfoData.getData().get(0).getArticle().get(0).getImg().size());
                                holder.mTextViewsInMessageHeadPhoto.get(1).setText(photoCount);
                            } else {
                                holder.mTextViewsInMessageHeadPhoto.get(1).setVisibility(View.GONE);
                            }
                            size = gInfoData.getData().get(0).getArticle().get(0).getTag().size();
                            spannableStringBuilder.delete(0, spannableStringBuilder.length());
                            for (int i = 0; i < size; i++) {
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append("#");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(0).getTag().get(i));
                                int index = stringBuilder.indexOf(":");
                                stringBuilder.delete(index, stringBuilder.length());
                                spannableStringBuilder.append(stringBuilder.toString(), new StyleSpan(Typeface.BOLD), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append("#");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(0).getTag().get(i));
                                if (i != size - 1) {
                                    stringBuilder.append("\n");
                                }
                                stringBuilder.delete(0, index + 1);
                                spannableStringBuilder.append(stringBuilder.toString());
                            }
                            holder.mTextViewsInMessageHeadPhoto.get(0).setText(spannableStringBuilder);
                            break;
                        case 4:
                            setVisibility(holder, 11);
                            size = gInfoData.getData().get(0).getArticle().get(0).getMsg().size();
                            count = String.valueOf(size) + " " + holder.itemView.getContext().getString(R.string.info_comments);
                            holder.mTextViewsInMessageBody.get(1).setText(count);
                            holder.mTextViewsInMessageBody.get(0).setText(gInfoData.getData().get(0).getArticle().get(0).getCount());
                            switch (gInfoData.getData().get(0).getArticle().get(0).getLike()) {
                                case "0":
                                    holder.mImageViewsInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_v11);
                                    holder.mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_fornote_v11);
                                    break;
                                case "1":
                                    holder.mImageViewsInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_v11);
                                    holder.mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_fornote_on_v11);
                                    break;
                                case "2":
                                    holder.mImageViewsInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_on_v11);
                                    holder.mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_fornote_v11);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 5:
                            if (position - 15 == customerCount1) {
                                setVisibility(holder, 12);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                                if (stringBuilder.toString().equals("null")) {
                                    holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                                } else {
                                    stringBuilder.delete(0, 2);
                                    Glide.with(holder.itemView.getContext())
                                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                            .into(holder.imagePhotoInMessageEnd);
                                }
                            } else {
                                setVisibility(holder, 19);
                                holder.mTextViewsInMessageEndMessage.get(0).setText(
                                        gInfoData.getData().get(0).getArticle().get(0).getMsg().get(0).getManame());
                                holder.mTextViewsInMessageEndMessage.get(1).setText(
                                        gInfoData.getData().get(0).getArticle().get(0).getMsg().get(0).getTxt());
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL +
                                                gInfoData.getData().get(0).getArticle().get(0).getMsg().get(0).getMaimg()))
                                        .into(holder.imageViewInMessageEndMessage);
                            }
                            break;
                        case 6:
                            if (position - 15 == customerCount1) {
                                setVisibility(holder, 12);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                                if (stringBuilder.toString().equals("null")) {
                                    holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                                } else {
                                    stringBuilder.delete(0, 2);
                                    Glide.with(holder.itemView.getContext())
                                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                            .into(holder.imagePhotoInMessageEnd);
                                }
                            } else {
                                setVisibility(holder, 19);
                                holder.mTextViewsInMessageEndMessage.get(0).setText(
                                        gInfoData.getData().get(0).getArticle().get(0).getMsg().get(1).getManame());
                                holder.mTextViewsInMessageEndMessage.get(1).setText(
                                        gInfoData.getData().get(0).getArticle().get(0).getMsg().get(1).getTxt());
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL +
                                                gInfoData.getData().get(0).getArticle().get(0).getMsg().get(1).getMaimg()))
                                        .into(holder.imageViewInMessageEndMessage);
                            }
                            break;
                        case 7:
                            if (position - 15 == customerCount1) {
                                setVisibility(holder, 12);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                                if (stringBuilder.toString().equals("null")) {
                                    holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                                } else {
                                    stringBuilder.delete(0, 2);
                                    Glide.with(holder.itemView.getContext())
                                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                            .into(holder.imagePhotoInMessageEnd);
                                }
                            } else {
                                setVisibility(holder, 19);
                                holder.mTextViewsInMessageEndMessage.get(0).setText(
                                        gInfoData.getData().get(0).getArticle().get(0).getMsg().get(2).getManame());
                                holder.mTextViewsInMessageEndMessage.get(1).setText(
                                        gInfoData.getData().get(0).getArticle().get(0).getMsg().get(2).getTxt());
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL +
                                                gInfoData.getData().get(0).getArticle().get(0).getMsg().get(2).getMaimg()))
                                        .into(holder.imageViewInMessageEndMessage);
                            }
                            break;
                        case 8:
                            setVisibility(holder, 12);
                            stringBuilder.delete(0, stringBuilder.length());
                            stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                            if (stringBuilder.toString().equals("null")) {
                                holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                            } else {
                                stringBuilder.delete(0, 2);
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                        .into(holder.imagePhotoInMessageEnd);
                            }
                            break;
                    }
                } else if (position < 16 + customerCount1 + customerCount2) {
                    switch (position - 15 - customerCount1) {
                        case 1:
                            setVisibility(holder, 10);
                            holder.mTextViewsInMessageHead.get(0).setText(gInfoData.getData().get(0).getArticle().get(1).getManame());
                            Glide.with(holder.itemView.getContext())
                                    .load(Uri.parse(AppConstant.BASE_URL + gInfoData.getData().get(0).getArticle().get(1).getMaimg()))
                                    .into(holder.mImageViewsInMessageHead.get(0));
                            String date = gInfoData.getData().get(0).getArticle().get(1).getTime_txt() + " ·";
                            holder.mTextViewsInMessageHead.get(1).setText(date);
                            if (gInfoData.getData().get(0).getArticle().get(1).getPrivacy().equals("1")) {
                                holder.mImageViewsInMessageHead.get(1).setImageResource(R.drawable.pr_public_v1_1);
                            } else {
                                holder.mImageViewsInMessageHead.get(1).setImageResource(R.drawable.pr_anonymous_v1_1);
                            }
                            if (gInfoData.getData().get(0).getArticle().get(1).getMaid().equals(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"))) {
                                if (gInfoData.getData().get(0).getArticle().get(1).getCoding().equals("0")) {
                                    holder.mImageViewsInMessageHead.get(2).setVisibility(View.VISIBLE);
                                } else {
                                    holder.mImageViewsInMessageHead.get(2).setVisibility(View.GONE);
                                }
                            } else {
                                holder.mImageViewsInMessageHead.get(2).setVisibility(View.GONE);
                            }
                            break;
                        case 2:
                            setVisibility(holder, 18);
                            if (gInfoData.getData().get(0).getArticle().get(1).getCoding().equals("1")) {
                                holder.mTextViewsInMessageHeadCustomer.get(1).setVisibility(View.VISIBLE);
                                int length = gInfoData.getData().get(0).getArticle().get(1).getTitle().get(0).length();
                                StringBuilder stringBuilder = new StringBuilder(gInfoData.getData().get(0).getArticle().get(1).getManame());
                                stringBuilder.append(" 建立了地標『");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(1).getTitle().get(0));
                                stringBuilder.append("』");
                                int lengthAll = stringBuilder.length();
                                SpannableString spannableString = new SpannableString(stringBuilder.toString());
                                spannableString.setSpan(new UnderlineSpan(), lengthAll - length - 1, lengthAll - 1, 0);
                                spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), lengthAll - length - 1, lengthAll - 1, 0);
                                holder.mTextViewsInMessageHeadCustomer.get(0).setText(spannableString);
                                stringBuilder.delete(0, lengthAll);
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(1).getManame());
                                stringBuilder.append(" created a new place ");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(1).getTitle().get(1));
                                stringBuilder.append(".");
                                length = gInfoData.getData().get(0).getArticle().get(1).getTitle().get(1).length();
                                lengthAll = stringBuilder.length();
                                spannableString = new SpannableString(stringBuilder.toString());
                                spannableString.setSpan(new UnderlineSpan(), lengthAll - length - 1, lengthAll - 1, 0);
                                spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), lengthAll - length - 1, lengthAll - 1, 0);
                                holder.mTextViewsInMessageHeadCustomer.get(1).setText(spannableString);
                            } else {
                                holder.mTextViewsInMessageHeadCustomer.get(1).setVisibility(View.GONE);
                                holder.mTextViewsInMessageHeadCustomer.get(0).setText(gInfoData.getData().get(0).getArticle().get(1).getContent());
                            }
                            break;
                        case 3:
                            setVisibility(holder, 16);
                            if (gInfoData.getData().get(0).getArticle().get(1).getImg().size() != 0) {
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL + gInfoData.getData().get(0).getArticle().get(1).getImg().get(0)))
                                        .into(holder.mImageInMessageHeadPhoto);
                                holder.mTextViewsInMessageHeadPhoto.get(1).setVisibility(View.VISIBLE);
                                String photoCount = "1/" + String.valueOf(gInfoData.getData().get(0).getArticle().get(1).getImg().size());
                                holder.mTextViewsInMessageHeadPhoto.get(1).setText(photoCount);
                            } else {
                                holder.mTextViewsInMessageHeadPhoto.get(1).setVisibility(View.GONE);
                            }
                            size = gInfoData.getData().get(0).getArticle().get(1).getTag().size();
                            spannableStringBuilder.delete(0, spannableStringBuilder.length());
                            for (int i = 0; i < size; i++) {
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append("#");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(1).getTag().get(i));
                                int index = stringBuilder.indexOf(":");
                                stringBuilder.delete(index, stringBuilder.length());
                                spannableStringBuilder.append(stringBuilder.toString(), new StyleSpan(Typeface.BOLD), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append("#");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(1).getTag().get(i));
                                if (i != size - 1) {
                                    stringBuilder.append("\n");
                                }
                                stringBuilder.delete(0, index + 1);
                                spannableStringBuilder.append(stringBuilder.toString());
                            }
                            holder.mTextViewsInMessageHeadPhoto.get(0).setText(spannableStringBuilder);
                            break;
                        case 4:
                            setVisibility(holder, 11);
                            size = gInfoData.getData().get(0).getArticle().get(1).getMsg().size();
                            count = String.valueOf(size) + " " + holder.itemView.getContext().getString(R.string.info_comments);
                            holder.mTextViewsInMessageBody.get(1).setText(count);
                            holder.mTextViewsInMessageBody.get(0).setText(gInfoData.getData().get(0).getArticle().get(1).getCount());
                            switch (gInfoData.getData().get(0).getArticle().get(1).getLike()) {
                                case "0":
                                    holder.mImageViewsInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_v11);
                                    holder.mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_fornote_v11);
                                    break;
                                case "1":
                                    holder.mImageViewsInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_v11);
                                    holder.mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_fornote_on_v11);
                                    break;
                                case "2":
                                    holder.mImageViewsInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_on_v11);
                                    holder.mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_fornote_v11);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 5:
                            if (position - 15 - customerCount1 == customerCount2) {
                                setVisibility(holder, 12);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                                if (stringBuilder.toString().equals("null")) {
                                    holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                                } else {
                                    stringBuilder.delete(0, 2);
                                    Glide.with(holder.itemView.getContext())
                                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                            .into(holder.imagePhotoInMessageEnd);
                                }
                            } else {
                                setVisibility(holder, 19);
                                holder.mTextViewsInMessageEndMessage.get(0).setText(
                                        gInfoData.getData().get(0).getArticle().get(1).getMsg().get(0).getManame());
                                holder.mTextViewsInMessageEndMessage.get(1).setText(
                                        gInfoData.getData().get(0).getArticle().get(1).getMsg().get(0).getTxt());
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL +
                                                gInfoData.getData().get(0).getArticle().get(1).getMsg().get(0).getMaimg()))
                                        .into(holder.imageViewInMessageEndMessage);
                            }
                            break;
                        case 6:
                            if (position - 15 - customerCount1 == customerCount2) {
                                setVisibility(holder, 12);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                                Log.e("info", stringBuilder.toString());
                                if (stringBuilder.toString().equals("null")) {
                                    holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                                } else {
                                    stringBuilder.delete(0, 2);
                                    Glide.with(holder.itemView.getContext())
                                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                            .into(holder.imagePhotoInMessageEnd);
                                }
                            } else {
                                setVisibility(holder, 19);
                                holder.mTextViewsInMessageEndMessage.get(0).setText(
                                        gInfoData.getData().get(0).getArticle().get(1).getMsg().get(1).getManame());
                                holder.mTextViewsInMessageEndMessage.get(1).setText(
                                        gInfoData.getData().get(0).getArticle().get(1).getMsg().get(1).getTxt());
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL +
                                                gInfoData.getData().get(0).getArticle().get(1).getMsg().get(1).getMaimg()))
                                        .into(holder.imageViewInMessageEndMessage);
                            }
                            break;
                        case 7:
                            if (position - 15 - customerCount1 == customerCount2) {
                                setVisibility(holder, 12);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                                if (stringBuilder.toString().equals("null")) {
                                    holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                                } else {
                                    stringBuilder.delete(0, 2);
                                    Glide.with(holder.itemView.getContext())
                                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                            .into(holder.imagePhotoInMessageEnd);
                                }
                            } else {
                                setVisibility(holder, 19);
                                holder.mTextViewsInMessageEndMessage.get(0).setText(
                                        gInfoData.getData().get(0).getArticle().get(1).getMsg().get(2).getManame());
                                holder.mTextViewsInMessageEndMessage.get(1).setText(
                                        gInfoData.getData().get(0).getArticle().get(1).getMsg().get(2).getTxt());
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL +
                                                gInfoData.getData().get(0).getArticle().get(1).getMsg().get(2).getMaimg()))
                                        .into(holder.imageViewInMessageEndMessage);
                            }
                            break;
                        case 8:
                            setVisibility(holder, 12);
                            stringBuilder.delete(0, stringBuilder.length());
                            stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                            if (stringBuilder.toString().equals("null")) {
                                holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                            } else {
                                stringBuilder.delete(0, 2);
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                        .into(holder.imagePhotoInMessageEnd);
                            }
                            break;
                    }
                } else if (position < 16 + customerCount1 + customerCount2 + customerCount3) {
                    switch (position - 15 - customerCount1 - customerCount2) {
                        case 1:
                            setVisibility(holder, 10);
                            holder.mTextViewsInMessageHead.get(0).setText(gInfoData.getData().get(0).getArticle().get(2).getManame());
                            Glide.with(holder.itemView.getContext())
                                    .load(Uri.parse(AppConstant.BASE_URL + gInfoData.getData().get(0).getArticle().get(2).getMaimg()))
                                    .into(holder.mImageViewsInMessageHead.get(0));
                            String date = gInfoData.getData().get(0).getArticle().get(2).getTime_txt() + " ·";
                            holder.mTextViewsInMessageHead.get(1).setText(date);
                            if (gInfoData.getData().get(0).getArticle().get(2).getPrivacy().equals("1")) {
                                holder.mImageViewsInMessageHead.get(1).setImageResource(R.drawable.pr_public_v1_1);
                            } else {
                                holder.mImageViewsInMessageHead.get(1).setImageResource(R.drawable.pr_anonymous_v1_1);
                            }
                            if (gInfoData.getData().get(0).getArticle().get(2).getMaid().equals(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"))) {
                                if (gInfoData.getData().get(0).getArticle().get(2).getCoding().equals("0")) {
                                    holder.mImageViewsInMessageHead.get(2).setVisibility(View.VISIBLE);
                                } else {
                                    holder.mImageViewsInMessageHead.get(2).setVisibility(View.GONE);
                                }
                            } else {
                                holder.mImageViewsInMessageHead.get(2).setVisibility(View.GONE);
                            }
                            break;
                        case 2:
                            setVisibility(holder, 18);
                            if (gInfoData.getData().get(0).getArticle().get(2).getCoding().equals("1")) {
                                holder.mTextViewsInMessageHeadCustomer.get(1).setVisibility(View.VISIBLE);
                                int length = gInfoData.getData().get(0).getArticle().get(2).getTitle().get(0).length();
                                StringBuilder stringBuilder = new StringBuilder(gInfoData.getData().get(0).getArticle().get(2).getManame());
                                stringBuilder.append(" 建立了地標『");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(2).getTitle().get(0));
                                stringBuilder.append("』");
                                int lengthAll = stringBuilder.length();
                                SpannableString spannableString = new SpannableString(stringBuilder.toString());
                                spannableString.setSpan(new UnderlineSpan(), lengthAll - length - 1, lengthAll - 1, 0);
                                spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), lengthAll - length - 1, lengthAll - 1, 0);
                                holder.mTextViewsInMessageHeadCustomer.get(0).setText(spannableString);
                                stringBuilder.delete(0, lengthAll);
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(2).getManame());
                                stringBuilder.append(" created a new place ");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(2).getTitle().get(1));
                                stringBuilder.append(".");
                                length = gInfoData.getData().get(0).getArticle().get(2).getTitle().get(1).length();
                                lengthAll = stringBuilder.length();
                                spannableString = new SpannableString(stringBuilder.toString());
                                spannableString.setSpan(new UnderlineSpan(), lengthAll - length - 1, lengthAll - 1, 0);
                                spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), lengthAll - length - 1, lengthAll - 1, 0);
                                holder.mTextViewsInMessageHeadCustomer.get(1).setText(spannableString);
                            } else {
                                holder.mTextViewsInMessageHeadCustomer.get(1).setVisibility(View.GONE);
                                holder.mTextViewsInMessageHeadCustomer.get(0).setText(gInfoData.getData().get(0).getArticle().get(2).getContent());
                            }
                            break;
                        case 3:
                            setVisibility(holder, 16);
                            if (gInfoData.getData().get(0).getArticle().get(2).getImg().size() != 0) {
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL + gInfoData.getData().get(0).getArticle().get(2).getImg().get(0)))
                                        .into(holder.mImageInMessageHeadPhoto);
                                holder.mTextViewsInMessageHeadPhoto.get(1).setVisibility(View.VISIBLE);
                                String photoCount = "1/" + String.valueOf(gInfoData.getData().get(0).getArticle().get(2).getImg().size());
                                holder.mTextViewsInMessageHeadPhoto.get(1).setText(photoCount);
                            } else {
                                holder.mTextViewsInMessageHeadPhoto.get(1).setVisibility(View.GONE);
                            }
                            size = gInfoData.getData().get(0).getArticle().get(2).getTag().size();
                            spannableStringBuilder.delete(0, spannableStringBuilder.length());
                            for (int i = 0; i < size; i++) {
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append("#");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(2).getTag().get(i));
                                int index = stringBuilder.indexOf(":");
                                stringBuilder.delete(index, stringBuilder.length());
                                spannableStringBuilder.append(stringBuilder.toString(), new StyleSpan(Typeface.BOLD), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append("#");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(2).getTag().get(i));
                                if (i != size - 1) {
                                    stringBuilder.append("\n");
                                }
                                stringBuilder.delete(0, index + 1);
                                spannableStringBuilder.append(stringBuilder.toString());
                            }
                            holder.mTextViewsInMessageHeadPhoto.get(0).setText(spannableStringBuilder);
                            break;
                        case 4:
                            setVisibility(holder, 11);
                            size = gInfoData.getData().get(0).getArticle().get(1).getMsg().size();
                            count = String.valueOf(size) + " " + holder.itemView.getContext().getString(R.string.info_comments);
                            holder.mTextViewsInMessageBody.get(1).setText(count);
                            holder.mTextViewsInMessageBody.get(0).setText(gInfoData.getData().get(0).getArticle().get(2).getCount());
                            switch (gInfoData.getData().get(0).getArticle().get(2).getLike()) {
                                case "0":
                                    holder.mImageViewsInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_v11);
                                    holder.mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_fornote_v11);
                                    break;
                                case "1":
                                    holder.mImageViewsInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_v11);
                                    holder.mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_fornote_on_v11);
                                    break;
                                case "2":
                                    holder.mImageViewsInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_on_v11);
                                    holder.mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_fornote_v11);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 5:
                            if (position - 15 - customerCount1 - customerCount2 == customerCount3) {
                                setVisibility(holder, 12);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                                if (stringBuilder.toString().equals("null")) {
                                    holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                                } else {
                                    stringBuilder.delete(0, 2);
                                    Glide.with(holder.itemView.getContext())
                                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                            .into(holder.imagePhotoInMessageEnd);
                                }
                            } else {
                                setVisibility(holder, 19);
                                holder.mTextViewsInMessageEndMessage.get(0).setText(
                                        gInfoData.getData().get(0).getArticle().get(2).getMsg().get(0).getManame());
                                holder.mTextViewsInMessageEndMessage.get(1).setText(
                                        gInfoData.getData().get(0).getArticle().get(2).getMsg().get(0).getTxt());
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL +
                                                gInfoData.getData().get(0).getArticle().get(2).getMsg().get(0).getMaimg()))
                                        .into(holder.imageViewInMessageEndMessage);
                            }
                            break;
                        case 6:
                            if (position - 15 - customerCount1 - customerCount2 == customerCount3) {
                                setVisibility(holder, 12);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                                if (stringBuilder.toString().equals("null")) {
                                    holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                                } else {
                                    stringBuilder.delete(0, 2);
                                    Glide.with(holder.itemView.getContext())
                                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                            .into(holder.imagePhotoInMessageEnd);
                                }
                            } else {
                                setVisibility(holder, 19);
                                holder.mTextViewsInMessageEndMessage.get(0).setText(
                                        gInfoData.getData().get(0).getArticle().get(2).getMsg().get(1).getManame());
                                holder.mTextViewsInMessageEndMessage.get(1).setText(
                                        gInfoData.getData().get(0).getArticle().get(2).getMsg().get(1).getTxt());
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL +
                                                gInfoData.getData().get(0).getArticle().get(2).getMsg().get(1).getMaimg()))
                                        .into(holder.imageViewInMessageEndMessage);
                            }
                            break;
                        case 7:
                            if (position - 15 - customerCount1 - customerCount2 == customerCount3) {
                                setVisibility(holder, 12);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                                if (stringBuilder.toString().equals("null")) {
                                    holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                                } else {
                                    stringBuilder.delete(0, 2);
                                    Glide.with(holder.itemView.getContext())
                                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                            .into(holder.imagePhotoInMessageEnd);
                                }
                            } else {
                                setVisibility(holder, 19);
                                holder.mTextViewsInMessageEndMessage.get(0).setText(
                                        gInfoData.getData().get(0).getArticle().get(2).getMsg().get(2).getManame());
                                holder.mTextViewsInMessageEndMessage.get(1).setText(
                                        gInfoData.getData().get(0).getArticle().get(2).getMsg().get(2).getTxt());
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL +
                                                gInfoData.getData().get(0).getArticle().get(2).getMsg().get(2).getMaimg()))
                                        .into(holder.imageViewInMessageEndMessage);
                            }
                            break;
                        case 8:
                            setVisibility(holder, 12);
                            stringBuilder.delete(0, stringBuilder.length());
                            stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                            if (stringBuilder.toString().equals("null")) {
                                holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                            } else {
                                stringBuilder.delete(0, 2);
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                        .into(holder.imagePhotoInMessageEnd);
                            }
                            break;
                    }
                } else if (position < 16 + customerCount1 + customerCount2 + customerCount3 + customerCount4) {
                    switch (position - 15 - customerCount1 - customerCount2 - customerCount3) {
                        case 1:
                            setVisibility(holder, 10);
                            holder.mTextViewsInMessageHead.get(0).setText(gInfoData.getData().get(0).getArticle().get(3).getManame());
                            Glide.with(holder.itemView.getContext())
                                    .load(Uri.parse(AppConstant.BASE_URL + gInfoData.getData().get(0).getArticle().get(3).getMaimg()))
                                    .into(holder.mImageViewsInMessageHead.get(0));
                            String date = gInfoData.getData().get(0).getArticle().get(3).getTime_txt() + " ·";
                            holder.mTextViewsInMessageHead.get(1).setText(date);
                            if (gInfoData.getData().get(0).getArticle().get(3).getPrivacy().equals("1")) {
                                holder.mImageViewsInMessageHead.get(1).setImageResource(R.drawable.pr_public_v1_1);
                            } else {
                                holder.mImageViewsInMessageHead.get(1).setImageResource(R.drawable.pr_anonymous_v1_1);
                            }
                            if (gInfoData.getData().get(0).getArticle().get(3).getMaid().equals(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"))) {
                                if (gInfoData.getData().get(0).getArticle().get(3).getCoding().equals("0")) {
                                    holder.mImageViewsInMessageHead.get(2).setVisibility(View.VISIBLE);
                                } else {
                                    holder.mImageViewsInMessageHead.get(2).setVisibility(View.GONE);
                                }
                            } else {
                                holder.mImageViewsInMessageHead.get(2).setVisibility(View.GONE);
                            }
                            break;
                        case 2:
                            setVisibility(holder, 18);
                            if (gInfoData.getData().get(0).getArticle().get(3).getCoding().equals("1")) {
                                holder.mTextViewsInMessageHeadCustomer.get(1).setVisibility(View.VISIBLE);
                                int length = gInfoData.getData().get(0).getArticle().get(3).getTitle().get(0).length();
                                StringBuilder stringBuilder = new StringBuilder(gInfoData.getData().get(0).getArticle().get(3).getManame());
                                stringBuilder.append(" 建立了地標『");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(3).getTitle().get(0));
                                stringBuilder.append("』");
                                int lengthAll = stringBuilder.length();
                                SpannableString spannableString = new SpannableString(stringBuilder.toString());
                                spannableString.setSpan(new UnderlineSpan(), lengthAll - length - 1, lengthAll - 1, 0);
                                spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), lengthAll - length - 1, lengthAll - 1, 0);
                                holder.mTextViewsInMessageHeadCustomer.get(0).setText(spannableString);
                                stringBuilder.delete(0, lengthAll);
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(3).getManame());
                                stringBuilder.append(" created a new place ");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(3).getTitle().get(1));
                                stringBuilder.append(".");
                                length = gInfoData.getData().get(0).getArticle().get(3).getTitle().get(1).length();
                                lengthAll = stringBuilder.length();
                                spannableString = new SpannableString(stringBuilder.toString());
                                spannableString.setSpan(new UnderlineSpan(), lengthAll - length - 1, lengthAll - 1, 0);
                                spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), lengthAll - length - 1, lengthAll - 1, 0);
                                holder.mTextViewsInMessageHeadCustomer.get(1).setText(spannableString);
                            } else {
                                holder.mTextViewsInMessageHeadCustomer.get(1).setVisibility(View.GONE);
                                holder.mTextViewsInMessageHeadCustomer.get(0).setText(gInfoData.getData().get(0).getArticle().get(3).getContent());
                            }
                            break;
                        case 3:
                            setVisibility(holder, 16);
                            if (gInfoData.getData().get(0).getArticle().get(3).getImg().size() != 0) {
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL + gInfoData.getData().get(0).getArticle().get(3).getImg().get(0)))
                                        .into(holder.mImageInMessageHeadPhoto);
                                holder.mTextViewsInMessageHeadPhoto.get(1).setVisibility(View.VISIBLE);
                                String photoCount = "1/" + String.valueOf(gInfoData.getData().get(0).getArticle().get(3).getImg().size());
                                holder.mTextViewsInMessageHeadPhoto.get(1).setText(photoCount);
                            } else {
                                holder.mTextViewsInMessageHeadPhoto.get(1).setVisibility(View.GONE);
                            }
                            size = gInfoData.getData().get(0).getArticle().get(3).getTag().size();
                            spannableStringBuilder.delete(0, spannableStringBuilder.length());
                            for (int i = 0; i < size; i++) {
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append("#");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(3).getTag().get(i));
                                int index = stringBuilder.indexOf(":");
                                stringBuilder.delete(index, stringBuilder.length());
                                spannableStringBuilder.append(stringBuilder.toString(), new StyleSpan(Typeface.BOLD), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append("#");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(3).getTag().get(i));
                                if (i != size - 1) {
                                    stringBuilder.append("\n");
                                }
                                stringBuilder.delete(0, index + 1);
                                spannableStringBuilder.append(stringBuilder.toString());
                            }
                            holder.mTextViewsInMessageHeadPhoto.get(0).setText(spannableStringBuilder);
                            break;
                        case 4:
                            setVisibility(holder, 11);
                            size = gInfoData.getData().get(0).getArticle().get(3).getMsg().size();
                            count = String.valueOf(size) + " " + holder.itemView.getContext().getString(R.string.info_comments);
                            holder.mTextViewsInMessageBody.get(1).setText(count);
                            holder.mTextViewsInMessageBody.get(0).setText(gInfoData.getData().get(0).getArticle().get(3).getCount());
                            switch (gInfoData.getData().get(0).getArticle().get(3).getLike()) {
                                case "0":
                                    holder.mImageViewsInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_v11);
                                    holder.mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_fornote_v11);
                                    break;
                                case "1":
                                    holder.mImageViewsInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_v11);
                                    holder.mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_fornote_on_v11);
                                    break;
                                case "2":
                                    holder.mImageViewsInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_on_v11);
                                    holder.mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_fornote_v11);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 5:
                            if (position - 15 - customerCount1 - customerCount2 - customerCount3 == customerCount4) {
                                setVisibility(holder, 12);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                                if (stringBuilder.toString().equals("null")) {
                                    holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                                } else {
                                    stringBuilder.delete(0, 2);
                                    Glide.with(holder.itemView.getContext())
                                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                            .into(holder.imagePhotoInMessageEnd);
                                }
                            } else {
                                setVisibility(holder, 19);
                                holder.mTextViewsInMessageEndMessage.get(0).setText(
                                        gInfoData.getData().get(0).getArticle().get(3).getMsg().get(0).getManame());
                                holder.mTextViewsInMessageEndMessage.get(1).setText(
                                        gInfoData.getData().get(0).getArticle().get(3).getMsg().get(0).getTxt());
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL +
                                                gInfoData.getData().get(0).getArticle().get(3).getMsg().get(0).getMaimg()))
                                        .into(holder.imageViewInMessageEndMessage);
                            }
                            break;
                        case 6:
                            if (position - 15 - customerCount1 - customerCount2 - customerCount3 == customerCount4) {
                                setVisibility(holder, 12);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                                if (stringBuilder.toString().equals("null")) {
                                    holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                                } else {
                                    stringBuilder.delete(0, 2);
                                    Glide.with(holder.itemView.getContext())
                                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                            .into(holder.imagePhotoInMessageEnd);
                                }
                            } else {
                                setVisibility(holder, 19);
                                holder.mTextViewsInMessageEndMessage.get(0).setText(
                                        gInfoData.getData().get(0).getArticle().get(3).getMsg().get(1).getManame());
                                holder.mTextViewsInMessageEndMessage.get(1).setText(
                                        gInfoData.getData().get(0).getArticle().get(3).getMsg().get(1).getTxt());
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL +
                                                gInfoData.getData().get(0).getArticle().get(3).getMsg().get(1).getMaimg()))
                                        .into(holder.imageViewInMessageEndMessage);
                            }
                            break;
                        case 7:
                            if (position - 15 - customerCount1 - customerCount2 - customerCount3 == customerCount4) {
                                setVisibility(holder, 12);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                                if (stringBuilder.toString().equals("null")) {
                                    holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                                } else {
                                    stringBuilder.delete(0, 2);
                                    Glide.with(holder.itemView.getContext())
                                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                            .into(holder.imagePhotoInMessageEnd);
                                }
                            } else {
                                setVisibility(holder, 19);
                                holder.mTextViewsInMessageEndMessage.get(0).setText(
                                        gInfoData.getData().get(0).getArticle().get(3).getMsg().get(2).getManame());
                                holder.mTextViewsInMessageEndMessage.get(1).setText(
                                        gInfoData.getData().get(0).getArticle().get(3).getMsg().get(2).getTxt());
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL +
                                                gInfoData.getData().get(0).getArticle().get(3).getMsg().get(2).getMaimg()))
                                        .into(holder.imageViewInMessageEndMessage);
                            }
                            break;
                        case 8:
                            setVisibility(holder, 12);
                            stringBuilder.delete(0, stringBuilder.length());
                            stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                            if (stringBuilder.toString().equals("null")) {
                                holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                            } else {
                                stringBuilder.delete(0, 2);
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                        .into(holder.imagePhotoInMessageEnd);
                            }
                            break;
                    }
                } else if (position < 16 + customerCount1 + customerCount2 + customerCount3 + customerCount4 + customerCount5) {
                    switch (position - 15 - customerCount1 - customerCount2 - customerCount3 - customerCount4) {
                        case 1:
                            setVisibility(holder, 10);
                            holder.mTextViewsInMessageHead.get(0).setText(gInfoData.getData().get(0).getArticle().get(4).getManame());
                            Glide.with(holder.itemView.getContext())
                                    .load(Uri.parse(AppConstant.BASE_URL + gInfoData.getData().get(0).getArticle().get(4).getMaimg()))
                                    .into(holder.mImageViewsInMessageHead.get(0));
                            String date = gInfoData.getData().get(0).getArticle().get(4).getTime_txt() + " ·";
                            holder.mTextViewsInMessageHead.get(1).setText(date);
                            if (gInfoData.getData().get(0).getArticle().get(4).getPrivacy().equals("1")) {
                                holder.mImageViewsInMessageHead.get(1).setImageResource(R.drawable.pr_public_v1_1);
                            } else {
                                holder.mImageViewsInMessageHead.get(1).setImageResource(R.drawable.pr_anonymous_v1_1);
                            }
                            if (gInfoData.getData().get(0).getArticle().get(4).getMaid().equals(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"))) {
                                if (gInfoData.getData().get(0).getArticle().get(4).getCoding().equals("0")) {
                                    holder.mImageViewsInMessageHead.get(2).setVisibility(View.VISIBLE);
                                } else {
                                    holder.mImageViewsInMessageHead.get(2).setVisibility(View.GONE);
                                }
                            } else {
                                holder.mImageViewsInMessageHead.get(2).setVisibility(View.GONE);
                            }
                            break;
                        case 2:
                            setVisibility(holder, 18);
                            if (gInfoData.getData().get(0).getArticle().get(4).getCoding().equals("1")) {
                                holder.mTextViewsInMessageHeadCustomer.get(1).setVisibility(View.VISIBLE);
                                int length = gInfoData.getData().get(0).getArticle().get(4).getTitle().get(0).length();
                                StringBuilder stringBuilder = new StringBuilder(gInfoData.getData().get(0).getArticle().get(4).getManame());
                                stringBuilder.append(" 建立了地標『");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(4).getTitle().get(0));
                                stringBuilder.append("』");
                                int lengthAll = stringBuilder.length();
                                SpannableString spannableString = new SpannableString(stringBuilder.toString());
                                spannableString.setSpan(new UnderlineSpan(), lengthAll - length - 1, lengthAll - 1, 0);
                                spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), lengthAll - length - 1, lengthAll - 1, 0);
                                holder.mTextViewsInMessageHeadCustomer.get(0).setText(spannableString);
                                stringBuilder.delete(0, lengthAll);
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(4).getManame());
                                stringBuilder.append(" created a new place ");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(4).getTitle().get(1));
                                stringBuilder.append(".");
                                length = gInfoData.getData().get(0).getArticle().get(4).getTitle().get(1).length();
                                lengthAll = stringBuilder.length();
                                spannableString = new SpannableString(stringBuilder.toString());
                                spannableString.setSpan(new UnderlineSpan(), lengthAll - length - 1, lengthAll - 1, 0);
                                spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), lengthAll - length - 1, lengthAll - 1, 0);
                                holder.mTextViewsInMessageHeadCustomer.get(1).setText(spannableString);
                            } else {
                                holder.mTextViewsInMessageHeadCustomer.get(1).setVisibility(View.GONE);
                                holder.mTextViewsInMessageHeadCustomer.get(0).setText(gInfoData.getData().get(0).getArticle().get(4).getContent());
                            }
                            break;
                        case 3:
                            setVisibility(holder, 16);
                            if (gInfoData.getData().get(0).getArticle().get(4).getImg().size() != 0) {
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL + gInfoData.getData().get(0).getArticle().get(4).getImg().get(0)))
                                        .into(holder.mImageInMessageHeadPhoto);
                                holder.mTextViewsInMessageHeadPhoto.get(1).setVisibility(View.VISIBLE);
                                String photoCount = "1/" + String.valueOf(gInfoData.getData().get(0).getArticle().get(4).getImg().size());
                                holder.mTextViewsInMessageHeadPhoto.get(1).setText(photoCount);
                            } else {
                                holder.mTextViewsInMessageHeadPhoto.get(1).setVisibility(View.GONE);
                            }
                            size = gInfoData.getData().get(0).getArticle().get(4).getTag().size();
                            spannableStringBuilder.delete(0, spannableStringBuilder.length());
                            for (int i = 0; i < size; i++) {
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append("#");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(4).getTag().get(i));
                                int index = stringBuilder.indexOf(":");
                                stringBuilder.delete(index, stringBuilder.length());
                                spannableStringBuilder.append(stringBuilder.toString(), new StyleSpan(Typeface.BOLD), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append("#");
                                stringBuilder.append(gInfoData.getData().get(0).getArticle().get(4).getTag().get(i));
                                if (i != size - 1) {
                                    stringBuilder.append("\n");
                                }
                                stringBuilder.delete(0, index + 1);
                                spannableStringBuilder.append(stringBuilder.toString());
                            }
                            holder.mTextViewsInMessageHeadPhoto.get(0).setText(spannableStringBuilder);
                            break;
                        case 4:
                            setVisibility(holder, 11);
                            size = gInfoData.getData().get(0).getArticle().get(4).getMsg().size();
                            count = String.valueOf(size) + " " + holder.itemView.getContext().getString(R.string.info_comments);
                            holder.mTextViewsInMessageBody.get(1).setText(count);
                            holder.mTextViewsInMessageBody.get(0).setText(gInfoData.getData().get(0).getArticle().get(4).getCount());
                            switch (gInfoData.getData().get(0).getArticle().get(4).getLike()) {
                                case "0":
                                    holder.mImageViewsInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_v11);
                                    holder.mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_fornote_v11);
                                    break;
                                case "1":
                                    holder.mImageViewsInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_v11);
                                    holder.mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_fornote_on_v11);
                                    break;
                                case "2":
                                    holder.mImageViewsInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_on_v11);
                                    holder.mImageViewsInMessageBody.get(4).setImageResource(R.drawable.like_fornote_v11);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 5:
                            if (position - 15 - customerCount1 - customerCount2 - customerCount3 - customerCount4 == customerCount5) {
                                setVisibility(holder, 12);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                                if (stringBuilder.toString().equals("null")) {
                                    holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                                } else {
                                    stringBuilder.delete(0, 2);
                                    Glide.with(holder.itemView.getContext())
                                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                            .into(holder.imagePhotoInMessageEnd);
                                }
                            } else {
                                setVisibility(holder, 19);
                                holder.mTextViewsInMessageEndMessage.get(0).setText(
                                        gInfoData.getData().get(0).getArticle().get(4).getMsg().get(0).getManame());
                                holder.mTextViewsInMessageEndMessage.get(1).setText(
                                        gInfoData.getData().get(0).getArticle().get(4).getMsg().get(0).getTxt());
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL +
                                                gInfoData.getData().get(0).getArticle().get(4).getMsg().get(0).getMaimg()))
                                        .into(holder.imageViewInMessageEndMessage);
                            }
                            break;
                        case 6:
                            if (position - 15 - customerCount1 - customerCount2 - customerCount3 - customerCount4 == customerCount5) {
                                setVisibility(holder, 12);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                                if (stringBuilder.toString().equals("null")) {
                                    holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                                } else {
                                    stringBuilder.delete(0, 2);
                                    Glide.with(holder.itemView.getContext())
                                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                            .into(holder.imagePhotoInMessageEnd);
                                }
                            } else {
                                setVisibility(holder, 19);
                                holder.mTextViewsInMessageEndMessage.get(0).setText(
                                        gInfoData.getData().get(0).getArticle().get(4).getMsg().get(1).getManame());
                                holder.mTextViewsInMessageEndMessage.get(1).setText(
                                        gInfoData.getData().get(0).getArticle().get(4).getMsg().get(1).getTxt());
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL +
                                                gInfoData.getData().get(0).getArticle().get(4).getMsg().get(1).getMaimg()))
                                        .into(holder.imageViewInMessageEndMessage);
                            }
                            break;
                        case 7:
                            if (position - 15 - customerCount1 - customerCount2 - customerCount3 - customerCount4 == customerCount5) {
                                setVisibility(holder, 12);
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                                if (stringBuilder.toString().equals("null")) {
                                    holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                                } else {
                                    stringBuilder.delete(0, 2);
                                    Glide.with(holder.itemView.getContext())
                                            .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                            .into(holder.imagePhotoInMessageEnd);
                                }
                            } else {
                                setVisibility(holder, 19);
                                holder.mTextViewsInMessageEndMessage.get(0).setText(
                                        gInfoData.getData().get(0).getArticle().get(4).getMsg().get(2).getManame());
                                holder.mTextViewsInMessageEndMessage.get(1).setText(
                                        gInfoData.getData().get(0).getArticle().get(4).getMsg().get(2).getTxt());
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL +
                                                gInfoData.getData().get(0).getArticle().get(4).getMsg().get(2).getMaimg()))
                                        .into(holder.imageViewInMessageEndMessage);
                            }
                            break;
                        case 8:
                            setVisibility(holder, 12);
                            stringBuilder.delete(0, stringBuilder.length());
                            stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                            if (stringBuilder.toString().equals("null")) {
                                holder.imagePhotoInMessageEnd.setImageResource(R.drawable.person_unlogin);
                            } else {
                                stringBuilder.delete(0, 2);
                                Glide.with(holder.itemView.getContext())
                                        .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                        .into(holder.imagePhotoInMessageEnd);
                            }
                            break;
                    }
                } else {
                    if (position == getItemCount() - 1 && gInfoData.getData().get(0).getArticle().size() > 0) {
                        setVisibility(holder, 20);
                        holder.mTextTitleInHeadTitle.setText(R.string.info_more);
                    } else {
                        setVisibility(holder, -1);
                    }
                }
                break;

        }
    }

    @Override
    public int getItemCount() {
        return 16 + customerCount1 + customerCount2 + customerCount3 + customerCount4 + customerCount5 + 1;
    }

    private void setVisibility(@NotNull ViewHolder viewHolder, int i) {
        int size = viewHolder.mLayouts.size();
        for (int j = 0; j < size; j++) {
            viewHolder.mLayouts.get(j).setVisibility(View.GONE);
        }
        viewHolder.mTextTitleInInfoHolder.setVisibility(View.GONE);
        viewHolder.linearLayout.setVisibility(View.GONE);
        viewHolder.textMore.setVisibility(View.GONE);
        switch (i) {
            case 0:
                viewHolder.mTextTitleInInfoHolder.setVisibility(View.VISIBLE);
                break;
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
            case 13:
                viewHolder.mLayouts.get(12).setVisibility(View.VISIBLE);
                break;
            case 14:
                viewHolder.linearLayout.setVisibility(View.VISIBLE);
                break;
            case 15:
                viewHolder.mLayouts.get(13).setVisibility(View.VISIBLE);
                break;
            case 16:
                viewHolder.mLayouts.get(14).setVisibility(View.VISIBLE);
                break;
            case 17:
                viewHolder.mLayouts.get(15).setVisibility(View.VISIBLE);
                break;
            case 18:
                viewHolder.mLayouts.get(16).setVisibility(View.VISIBLE);
                break;
            case 19:
                viewHolder.mLayouts.get(17).setVisibility(View.VISIBLE);
                break;
            case 20:
                viewHolder.textMore.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    private String isOpen;
    private String detailTime;
    private void handleTime() {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        List<String> open;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm", Locale.getDefault());
        int index = 0;
        switch (day) {
            case 1:
                open = gInfoData.getData().get(0).getSun();
                break;
            case 2:
                open = gInfoData.getData().get(0).getMon();
                break;
            case 3:
                open = gInfoData.getData().get(0).getTue();
                break;
            case 4:
                open = gInfoData.getData().get(0).getWed();
                break;
            case 5:
                open = gInfoData.getData().get(0).getThu();
                break;
            case 6:
                open = gInfoData.getData().get(0).getFri();
                break;
            case 7:
                open = gInfoData.getData().get(0).getSat();
                break;
            default:
                open = new ArrayList<>();
                break;
        }
        int j = 0;
        boolean isNoData = true;
        boolean canLoop = true;
        List<Boolean> list = new ArrayList<>();
        list.add(gInfoData.getData().get(0).getSun().get(0).isEmpty());
        list.add(gInfoData.getData().get(0).getMon().get(0).isEmpty());
        list.add(gInfoData.getData().get(0).getTue().get(0).isEmpty());
        list.add(gInfoData.getData().get(0).getWed().get(0).isEmpty());
        list.add(gInfoData.getData().get(0).getThu().get(0).isEmpty());
        list.add(gInfoData.getData().get(0).getFri().get(0).isEmpty());
        list.add(gInfoData.getData().get(0).getSat().get(0).isEmpty());
        StringBuilder stringBuilder = new StringBuilder();
        do {
            if (!list.get(j)) {
                canLoop = false;
                isNoData = false;
            }
            j++;
        } while (canLoop && j < 7);
        if (isNoData) {
            isOpen = "2";
            detailTime = MyApplication.getInstance().getString(R.string.no_data);
        } else {
            canLoop = true;
            j = 0;
            boolean nowOpen;
            //檢察今天是否營業
            do {
                if (!open.get(j).isEmpty()) {
                    if (open.get(j).contains("24")) {
                        nowOpen = true;
                        canLoop = false;
                    } else {
                        if (stringBuilder.length() > 0) {
                            stringBuilder.delete(0, stringBuilder.length());
                        }
                        stringBuilder.append(open.get(j).replace(" ", ""));
                        if (stringBuilder.toString().contains("A")) {
                            int number = stringBuilder.indexOf("A");
                            stringBuilder.delete(number, number + 2);
                        }
                        if (stringBuilder.toString().contains("P")) {
                            int number = stringBuilder.indexOf("P");
                            stringBuilder.delete(number, number + 2);
                        }
                        String temp = stringBuilder.toString();
                        if (stringBuilder.toString().contains("–")) {
                            index = stringBuilder.indexOf("–");
                        }
                        if (stringBuilder.toString().contains("-")) {
                            index = stringBuilder.indexOf("-");
                        }
                        stringBuilder.delete(index, stringBuilder.length());
                        index = stringBuilder.indexOf(":");
                        int start = Integer.parseInt(stringBuilder.delete(index, index + 1).toString());
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder.append(temp);
                        if (stringBuilder.toString().contains("–")) {
                            index = stringBuilder.indexOf("–");
                        }
                        if (stringBuilder.toString().contains("-")) {
                            index = stringBuilder.indexOf("-");
                        }
                        stringBuilder.delete(0, index + 1);
                        index = stringBuilder.indexOf(":");
                        int end = Integer.parseInt(stringBuilder.delete(index, index + 1).toString());
                        int now = Integer.parseInt(simpleDateFormat.format(Calendar.getInstance().getTime()));
                        if (now > start) {
                            if (end > now) {
                                canLoop = false;
                                nowOpen = true;
                            } else if (start > end) {
                                canLoop = false;
                                nowOpen = true;
                            } else {
                                nowOpen = false;
                            }
                        } else {
                            canLoop = false;
                            nowOpen = false;
                        }
                    }
                } else {
                    canLoop = false;
                    nowOpen = false;
                }
                j++;
            } while (j < 4 && canLoop);
            if (!nowOpen) {
                isOpen = "1";
            } else {
                isOpen = "0";
            }
            //列出所有時間
            stringBuilder.delete(0, stringBuilder.length());
            List<String> dayOfWeek = new ArrayList<>();
            String days = "";
            for (int i = 0; i < 7; i++) {
                switch (i) {
                    case 0:
                        dayOfWeek = gInfoData.getData().get(0).getSun();
                        days = MyApplication.getInstance().getString(R.string.weekly_sun);
                        break;
                    case 1:
                        dayOfWeek = gInfoData.getData().get(0).getMon();
                        days = MyApplication.getInstance().getString(R.string.weekly_mon);
                        break;
                    case 2:
                        dayOfWeek = gInfoData.getData().get(0).getTue();
                        days = MyApplication.getInstance().getString(R.string.weekly_tue);
                        break;
                    case 3:
                        dayOfWeek = gInfoData.getData().get(0).getWed();
                        days = MyApplication.getInstance().getString(R.string.weekly_wed);
                        break;
                    case 4:
                        dayOfWeek = gInfoData.getData().get(0).getThu();
                        days = MyApplication.getInstance().getString(R.string.weekly_thu);
                        break;
                    case 5:
                        dayOfWeek = gInfoData.getData().get(0).getFri();
                        days = MyApplication.getInstance().getString(R.string.weekly_fri);
                        break;
                    case 6:
                        dayOfWeek = gInfoData.getData().get(0).getSat();
                        days = MyApplication.getInstance().getString(R.string.weekly_sat);
                        break;
                    default:
                        break;
                }
                int noTime = 0;
                for (int k = 0; k < 4; k++) {
                    if (!dayOfWeek.get(k).equals("")) {
                        stringBuilder.append(days);
                        stringBuilder.append(" : ");
                        stringBuilder.append(dayOfWeek.get(k));
                        if (dayOfWeek.get(k).equals("24")) {
                            stringBuilder.append(" ");
                            stringBuilder.append(MyApplication.getInstance().getString(R.string.open_24));
                        }
                        stringBuilder.append("\n");
                    } else {
                        noTime++;
                    }
                }
                if (noTime == 4) {
                    stringBuilder.append(days);
                    stringBuilder.append(" : ");
                    stringBuilder.append(MyApplication.getInstance().getString(R.string.map_status_close));
                    stringBuilder.append("\n");
                }
            }
            detailTime = stringBuilder.toString();
        }
    }

    private void init() {
        customerCount1 = 0;
        customerCount2 = 0;
        customerCount3 = 0;
        customerCount4 = 0;
        customerCount5 = 0;
        switch (gInfoData.getData().get(0).getArticle().size()) {
            case 1:
                if (gInfoData.getData().get(0).getArticle().get(0).getMsg().size() < 3) {
                    customerCount1 = 5 + gInfoData.getData().get(0).getArticle().get(0).getMsg().size();
                } else {
                    customerCount1 = 8;
                }
                break;
            case 2:
                if (gInfoData.getData().get(0).getArticle().get(0).getMsg().size() < 3) {
                    customerCount1 = 5 + gInfoData.getData().get(0).getArticle().get(0).getMsg().size();
                } else {
                    customerCount1 = 8;
                }
                if (gInfoData.getData().get(0).getArticle().get(1).getMsg().size() < 3) {
                    customerCount2 = 5 + gInfoData.getData().get(0).getArticle().get(1).getMsg().size();
                } else {
                    customerCount2 = 8;
                }
                break;
            case 3:
                if (gInfoData.getData().get(0).getArticle().get(0).getMsg().size() < 3) {
                    customerCount1 = 5 + gInfoData.getData().get(0).getArticle().get(0).getMsg().size();
                } else {
                    customerCount1 = 8;
                }
                if (gInfoData.getData().get(0).getArticle().get(1).getMsg().size() < 3) {
                    customerCount2 = 5 + gInfoData.getData().get(0).getArticle().get(1).getMsg().size();
                } else {
                    customerCount2 = 8;
                }
                if (gInfoData.getData().get(0).getArticle().get(2).getMsg().size() < 3) {
                    customerCount3 = 5 + gInfoData.getData().get(0).getArticle().get(2).getMsg().size();
                } else {
                    customerCount3 = 8;
                }
                break;
            case 4:
                if (gInfoData.getData().get(0).getArticle().get(0).getMsg().size() < 3) {
                    customerCount1 = 5 + gInfoData.getData().get(0).getArticle().get(0).getMsg().size();
                } else {
                    customerCount1 = 8;
                }
                if (gInfoData.getData().get(0).getArticle().get(1).getMsg().size() < 3) {
                    customerCount2 = 5 + gInfoData.getData().get(0).getArticle().get(1).getMsg().size();
                } else {
                    customerCount2 = 8;
                }
                if (gInfoData.getData().get(0).getArticle().get(2).getMsg().size() < 3) {
                    customerCount3 = 5 + gInfoData.getData().get(0).getArticle().get(2).getMsg().size();
                } else {
                    customerCount3 = 8;
                }
                if (gInfoData.getData().get(0).getArticle().get(3).getMsg().size() < 3) {
                    customerCount4 = 5 + gInfoData.getData().get(0).getArticle().get(3).getMsg().size();
                } else {
                    customerCount4 = 8;
                }
                break;
            case 5:

                if (gInfoData.getData().get(0).getArticle().get(0).getMsg().size() < 3) {
                    customerCount1 = 5 + gInfoData.getData().get(0).getArticle().get(0).getMsg().size();
                } else {
                    customerCount1 = 8;
                }
                if (gInfoData.getData().get(0).getArticle().get(1).getMsg().size() < 3) {
                    customerCount2 = 5 + gInfoData.getData().get(0).getArticle().get(1).getMsg().size();
                } else {
                    customerCount2 = 8;
                }
                if (gInfoData.getData().get(0).getArticle().get(2).getMsg().size() < 3) {
                    customerCount3 = 5 + gInfoData.getData().get(0).getArticle().get(2).getMsg().size();
                } else {
                    customerCount3 = 8;
                }
                if (gInfoData.getData().get(0).getArticle().get(3).getMsg().size() < 3) {
                    customerCount4 = 5 + gInfoData.getData().get(0).getArticle().get(3).getMsg().size();
                } else {
                    customerCount4 = 8;
                }
                if (gInfoData.getData().get(0).getArticle().get(4).getMsg().size() < 3) {
                    customerCount5 = 5 + gInfoData.getData().get(0).getArticle().get(4).getMsg().size();
                } else {
                    customerCount5 = 8;
                }
                break;
            default:
                if (gInfoData.getData().get(0).getArticle().size() > 5) {
                    if (gInfoData.getData().get(0).getArticle().get(0).getMsg().size() < 3) {
                        customerCount1 = 5 + gInfoData.getData().get(0).getArticle().get(0).getMsg().size();
                    } else {
                        customerCount1 = 8;
                    }
                    if (gInfoData.getData().get(0).getArticle().get(1).getMsg().size() < 3) {
                        customerCount2 = 5 + gInfoData.getData().get(0).getArticle().get(1).getMsg().size();
                    } else {
                        customerCount2 = 8;
                    }
                    if (gInfoData.getData().get(0).getArticle().get(2).getMsg().size() < 3) {
                        customerCount3 = 5 + gInfoData.getData().get(0).getArticle().get(2).getMsg().size();
                    } else {
                        customerCount3 = 8;
                    }
                    if (gInfoData.getData().get(0).getArticle().get(3).getMsg().size() < 3) {
                        customerCount4 = 5 + gInfoData.getData().get(0).getArticle().get(3).getMsg().size();
                    } else {
                        customerCount4 = 8;
                    }
                    if (gInfoData.getData().get(0).getArticle().get(4).getMsg().size() < 3) {
                        customerCount5 = 5 + gInfoData.getData().get(0).getArticle().get(4).getMsg().size();
                    } else {
                        customerCount5 = 8;
                    }
                }
                break;
        }
    }

    public void refreshData() {
        gInfoData = MyApplication.getAppData().getgInfoData();
        init();
        notifyDataSetChanged();
    }

    public void refreshLikeCount(String count, int num) {
        switch (num) {
            case 0:
                gInfoData.getData().get(0).getArticle().get(0).setCount(count);
                break;
            case 1:
                gInfoData.getData().get(0).getArticle().get(1).setCount(count);
                break;
            case 2:
                gInfoData.getData().get(0).getArticle().get(2).setCount(count);
                break;
            case 3:
                gInfoData.getData().get(0).getArticle().get(3).setCount(count);
                break;
            case 4:
                gInfoData.getData().get(0).getArticle().get(4).setCount(count);
                break;
            default:
                break;
        }
        notifyDataSetChanged();
    }

    public void refreshRateData(String rate) {
        rateForTop = rate;
        notifyDataSetChanged();
    }

    public void handleSave(String save) {
        gInfoData.getData().get(0).setColl(save);
        notifyDataSetChanged();
    }

    private String mRate;
    private String uRate;
    class ViewHolder extends RecyclerView.ViewHolder {
        //layouts
        @BindViews({R.id.layoutHeadPicture, R.id.layoutHeadTitle, R.id.layoutHead, R.id.layoutGoodsInfo,
                R.id.layoutGoodsTag, R.id.layoutAttractionInfo, R.id.layoutIntroduce, R.id.layoutMediaInfo,
                R.id.layoutRating, R.id.layoutMessageHead, R.id.layoutMessageBody, R.id.layoutMessageEnd,
                R.id.layoutMessageBodyGoods, R.id.layoutMessageBodyGoodsC, R.id.layoutMessageHeadPhoto,
                R.id.layoutMessageHeadGoods, R.id.layoutMessageHeadCustomer,
                R.id.layoutMessageEndMessage})
        List<RelativeLayout> mLayouts;
        @BindView(R.id.layoutMessageBodyGoodsB)
        LinearLayout linearLayout;
        @BindView(R.id.textTitleInInfoHolder)
        TextView mTextTitleInInfoHolder;
        //in head picture
        @BindView(R.id.imagePictureInHeadPicture)
        ImageView mImagePictureInHeadPicture;
        //in head title
        @BindView(R.id.textTitleInHeadTitle)
        TextView mTextTitleInHeadTitle;
        //in head
        @BindViews({R.id.textTitleSubInHead, R.id.textSendInHead, R.id.textPhotoInHead, R.id.textDirectionInHead,
                R.id.textCollectInHead, R.id.textShareInHead, R.id.textServiceInfoInHead, R.id.textRatioInHead,
                R.id.textStateInHead, R.id.textSaveInHead, R.id.textServiceInfoSubInHead})
        List<TextView> mTextViewsInHead;
        @BindViews({R.id.imagePhotoInHead, R.id.imageDirectionInHead, R.id.imageCollectInHead, R.id.imageShareInHead, R.id.imageSaveInHead})
        List<ImageView> mImageViewsInHead;
        @BindView(R.id.ratingInHead)
        RatingBar mRatingInHead;
        //in goods info
        @BindViews({R.id.imagePicture1InGoodsInfo, R.id.imagePicture2InGoodsInfo, R.id.imagePicture3InGoodsInfo,
                R.id.imagePicture4InGoodsInfo, R.id.imagePicture5InGoodsInfo, R.id.imageLicenseInGoodsInfo})
        List<ImageView> mImageViewsInGoodsInfo;
        @BindViews({R.id.textTitle1InGoodsInfo, R.id.textCount1InGoodsInfo, R.id.textTitle2InGoodsInfo,
                R.id.textCount2InGoodsInfo, R.id.textTitle3InGoodsInfo, R.id.textCount3InGoodsInfo,
                R.id.textTitle4InGoodsInfo, R.id.textCount4InGoodsInfo, R.id.textTitle5InGoodsInfo,
                R.id.textCount5InGoodsInfo, R.id.textTitleInGoodsInfo})
        List<TextView> mTextViewsInGoodsInfo;
        //in goods tag
        @BindViews({R.id.imagePicture1InGoodsTag, R.id.imagePicture2InGoodsTag, R.id.imagePicture3InGoodsTag,
                R.id.imageHead1InGoodsTag, R.id.imageHead2InGoodsTag, R.id.imageHead3InGoodsTag,
                R.id.imageHead4InGoodsTag, R.id.imageHead5InGoodsTag, R.id.imageHead6InGoodsTag,
                R.id.imageHead7InGoodsTag, R.id.imageHead8InGoodsTag})
        List<ImageView> mImageViewsInGoodsTag;
        @BindViews({R.id.textTagA1InGoodsTag, R.id.textTagA2InGoodsTag, R.id.textTagA3InGoodsTag,
                R.id.textContentInGoodsTag, R.id.textTitleInGoodsTag, R.id.textTagA11InGoodsTag,
                R.id.textTagA22InGoodsTag, R.id.textTagA33InGoodsTag})
        List<TextView> mTextViewsInGoodsTag;
        //in attraction info
        @BindViews({R.id.imageHead1InAttractionInfo, R.id.imageHead2InAttractionInfo,
                R.id.imageHead3InAttractionInfo, R.id.imageHead4InAttractionInfo, R.id.imageEditInAttractionInfo})
        List<ImageView> mImageViewsInAttractionInfo;
        @BindViews({R.id.textAddressInAttractionInfo, R.id.textPhoneInAttractionInfo, R.id.textStateInAttractionInfo,
                R.id.textTimeInAttractionInfo, R.id.textShowMoreInAttractionInfo, R.id.textTimeDetailInAttractionInfo,
                R.id.textTitleInAttractionInfo, R.id.textNetInAttractionInfo})
        List<TextView> mTextViewsInAttractionInfo;
        //in introduce
        @BindViews({R.id.textTitleInIntroduce, R.id.textIntroduceInIntroduce})
        List<TextView> mTextViewsInIntroduce;
        @BindView(R.id.imageEditInIntroduce)
        ImageView imageViewInIntroduce;
        //in media info
        @BindViews({R.id.imagePhotoInMediaInfo, R.id.imageEditInMediaInfo})
        List<ImageView> imageViewListInMediaInfo;
        @BindViews({R.id.textNameInMediaInfo, R.id.textWatchMoreInMediaInfo, R.id.textTitleInMediaInfo})
        List<TextView> mTextViewsInMediaInfo;
        //in rating
        @BindView(R.id.imageHeadInRating)
        ImageView mImageHeadInRating;
        @BindViews({R.id.textMessage1InRating, R.id.textMessage2InRating})
        List<TextView> mTextViewsInRating;
        @BindView(R.id.ratingInRating)
        RatingBar mRatingBar;
        //in message head
        @BindViews({R.id.imageHeadInMessageHead, R.id.imageStateInMessageHead, R.id.imageEditInMessageHead})
        List<ImageView> mImageViewsInMessageHead;
        @BindViews({R.id.textIDInMessageHead, R.id.textDateInMessageHead})
        List<TextView> mTextViewsInMessageHead;
        //in message body
        @BindViews({R.id.imageMessageInMessageBody, R.id.imageShareInMessageBody,
                R.id.imageLoveInMessageBody, R.id.imageBadInMessageBody, R.id.imageGoodInMessageBody})
        List<ImageView> mImageViewsInMessageBody;
        @BindViews({R.id.textCountInMessageBody, R.id.textAllMessageInMessageBody,
                R.id.textMessageInMessageBody, R.id.textShareInMessageBody})
        List<TextView> mTextViewsInMessageBody;
        //in message end
        @BindView(R.id.imagePhotoInMessageEnd)
        ImageView imagePhotoInMessageEnd;
        //@BindView(R.id.editMessageInMessageEnd)
        //EditText mEditTextInMessageEnd;
        @BindView(R.id.textMessageInMessageEnd)
        TextView textMessageInMessageEnd;
        //in message end message
        @BindViews({R.id.textNameInMessageEndMessage, R.id.textMessageInMessageEndMessage})
        List<TextView> mTextViewsInMessageEndMessage;
        @BindView(R.id.imagePhotoInMessageEndMessage)
        ImageView imageViewInMessageEndMessage;
        @BindViews({R.id.viewBarAInMessageEndMessage, R.id.viewBarBInMessageEndMessage})
        List<View> mViewBarsInMessageEndMessage;
        //in message body goods
        @BindView(R.id.textTitleInMessageBodyGoods)
        TextView mTextTitleInMessageBodyGoods;
        @BindView(R.id.viewBarInMessageBodyGoods)
        View mViewBarInMessageBodyGoods;
        //in message body goods b
        @BindViews({R.id.textTagAInMessageBodyGoodsB, R.id.textTagBInMessageBodyGoodsB})
        List<TextView> mTextViewsInMessageBodyGoodsB;
        //in message body goods c
        @BindView(R.id.textTitleInMessageBodyGoodsC)
        TextView mTextTitleInMessageBodyGoodsC;
        //in message head customer
        @BindViews({R.id.textMessageInMessageHeadCustomer, R.id.textMessage2InMessageHeadCustomer, R.id.textShowMoreInMessageHeadCustomer})
        List<TextView> mTextViewsInMessageHeadCustomer;
        //in message head goods
        @BindViews({R.id.textNameInMessageHeadGoods, R.id.textLicenceInMessageHeadGoods, R.id.textInfoInMessageHeadGoods})
        List<TextView> mTextViewsInMessageHeadGoods;
        @BindView(R.id.imageLicenceInMessageHeadGoods)
        ImageView mImageInMessageHeadGoods;
        //in message head photo
        @BindViews({R.id.textTagAInMessageHeadPhoto, R.id.textCountInMessageHeadPhoto})
        List<TextView> mTextViewsInMessageHeadPhoto;
        @BindView(R.id.imagePhotoInMessageHeadPhoto)
        ImageView mImageInMessageHeadPhoto;
        @BindView(R.id.textMoreInInfoHolder)
        TextView textMore;
        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mRatingInHead.setRating(Float.parseFloat(mRate));
            mRatingBar.setIsIndicator(false);
            mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                        iaInfo.handSignIn();
                    } else {
                        if (rating == 0) {
                            ratingBar.setRating(1);
                            uRate = String.valueOf(1);
                        } else if (rating > 0 && rating <= 1) {
                            ratingBar.setRating(1);
                            uRate = String.valueOf(1);
                        } else if (rating > 1 && rating <= 2) {
                            ratingBar.setRating(2);
                            uRate = String.valueOf(2);
                        } else if (rating > 2 && rating <= 3) {
                            ratingBar.setRating(3);
                            uRate = String.valueOf(3);
                        } else if (rating > 3 && rating <= 4) {
                            ratingBar.setRating(4);
                            uRate = String.valueOf(4);
                        } else if (rating > 4 && rating <= 5) {
                            ratingBar.setRating(5);
                            uRate = String.valueOf(5);
                        }
                        if (fromUser) {
                            iaInfo.handleRating(gInfoData.getData().get(0).getSpid(), uRate);
                        }
                    }
                }
            });
            mImageViewsInMessageBody.get(2).setVisibility(View.GONE);
            RxView.clicks(mImageViewsInMessageBody.get(3))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                iaInfo.handSignIn();
                            } else {
                                String artid = "";
                                int num = 0;
                                if (getAdapterPosition() < 16 + customerCount1) {
                                    if (!gInfoData.getData().get(0).getArticle().get(0).getLike().equals("2")) {
                                        artid = gInfoData.getData().get(0).getArticle().get(0).getArtid();
                                        num = 0;
                                        gInfoData.getData().get(0).getArticle().get(0).setLike("2");
                                    }

                                } else if (getAdapterPosition() < 16 + customerCount1 + customerCount2) {
                                    if (!gInfoData.getData().get(0).getArticle().get(1).getLike().equals("2")) {
                                        artid = gInfoData.getData().get(0).getArticle().get(1).getArtid();
                                        num = 1;
                                        gInfoData.getData().get(0).getArticle().get(1).setLike("2");
                                    }
                                } else if (getAdapterPosition() < 16 + customerCount1 + customerCount2 + customerCount3) {
                                    if (!gInfoData.getData().get(0).getArticle().get(2).getLike().equals("2")) {
                                        artid = gInfoData.getData().get(0).getArticle().get(2).getArtid();
                                        num = 2;
                                        gInfoData.getData().get(0).getArticle().get(2).setLike("2");
                                    }
                                } else if (getAdapterPosition() < 16 + customerCount1 + customerCount2 + customerCount3 + customerCount4) {
                                    if (!gInfoData.getData().get(0).getArticle().get(3).getLike().equals("2")) {
                                        artid = gInfoData.getData().get(0).getArticle().get(3).getArtid();
                                        num = 3;
                                        gInfoData.getData().get(0).getArticle().get(3).setLike("2");
                                    }
                                } else {
                                    if (!gInfoData.getData().get(0).getArticle().get(4).getLike().equals("2")) {
                                        artid = gInfoData.getData().get(0).getArticle().get(4).getArtid();
                                        num = 4;
                                        gInfoData.getData().get(0).getArticle().get(4).setLike("2");
                                    }
                                }
                                iaInfo.handleLikeOrUnlike(artid, "0", num);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsInMessageBody.get(4))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                iaInfo.handSignIn();
                            } else {
                                String artid = "";
                                int num = 0;
                                if (getAdapterPosition() < 16 + customerCount1) {
                                    if (!gInfoData.getData().get(0).getArticle().get(0).getLike().equals("1")) {
                                        artid = gInfoData.getData().get(0).getArticle().get(0).getArtid();
                                        num = 0;
                                        gInfoData.getData().get(0).getArticle().get(0).setLike("1");
                                    }
                                } else if (getAdapterPosition() < 16 + customerCount1 + customerCount2) {
                                    if (!gInfoData.getData().get(0).getArticle().get(1).getLike().equals("1")) {
                                        artid = gInfoData.getData().get(0).getArticle().get(1).getArtid();
                                        num = 1;
                                        gInfoData.getData().get(0).getArticle().get(1).setLike("1");
                                    }
                                } else if (getAdapterPosition() < 16 + customerCount1 + customerCount2 + customerCount3) {
                                    if (!gInfoData.getData().get(0).getArticle().get(2).getLike().equals("1")) {
                                        artid = gInfoData.getData().get(0).getArticle().get(2).getArtid();
                                        num = 2;
                                        gInfoData.getData().get(0).getArticle().get(2).setLike("1");
                                    }
                                } else if (getAdapterPosition() < 16 + customerCount1 + customerCount2 + customerCount3 + customerCount4) {
                                    if (!gInfoData.getData().get(0).getArticle().get(3).getLike().equals("1")) {
                                        artid = gInfoData.getData().get(0).getArticle().get(3).getArtid();
                                        num = 3;
                                        gInfoData.getData().get(0).getArticle().get(3).setLike("1");
                                    }
                                } else {
                                    if (!gInfoData.getData().get(0).getArticle().get(4).getLike().equals("1")) {
                                        artid = gInfoData.getData().get(0).getArticle().get(4).getArtid();
                                        num = 4;
                                        gInfoData.getData().get(0).getArticle().get(4).setLike("1");
                                    }
                                }
                                iaInfo.handleLikeOrUnlike(artid, "1", num);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInHead.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaInfo.handleSwitchPage(0);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textMessageInMessageEnd)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handSelectedMessage(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            //click media info more
            RxView.clicks(mTextViewsInMediaInfo.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaInfo.saveSwiTubeUrl(gInfoData.getData().get(0).getTubeWeb().get(0).getUrl());
                            iaInfo.handleSwitchPage(3);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInMediaInfo.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaInfo.saveSwiTubeUrl(gInfoData.getData().get(0).getTubeWeb().get(0).getUrl());
                            iaInfo.handleSwitchPage(3);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewListInMediaInfo.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaInfo.saveSwiTubeUrl(gInfoData.getData().get(0).getTubeWeb().get(0).getUrl());
                            iaInfo.handleSwitchPage(3);
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
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Object o) {
                            iaInfo.handleSwitchPage(4);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
            RxView.clicks(mImageViewsInMessageBody.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handSelectedMessage(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInMessageBody.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handSelectedMessage(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mViewBarsInMessageEndMessage.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handSelectedMessage(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mViewBarsInMessageEndMessage.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handSelectedMessage(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            /*RxView.clicks(mTextViewsInMessageEndMessage.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handSelectedMessage(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });*/
            RxView.clicks(mImagePictureInHeadPicture)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            MyApplication.getAppData().setFromClickInfoHeadPhoto(true);
                            iaInfo.handleSwitchPage(1);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageInMessageHeadPhoto)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (getAdapterPosition() < 16 + customerCount1) {
                                iaInfo.handViewCustomerPhoto(gInfoData.getData().get(0).getArticle().get(0).getImg());
                            } else if (getAdapterPosition() < 16 + customerCount1 + customerCount2) {
                                iaInfo.handViewCustomerPhoto(gInfoData.getData().get(0).getArticle().get(1).getImg());
                            } else if (getAdapterPosition() < 16 + customerCount1 + customerCount2 + customerCount3) {
                                iaInfo.handViewCustomerPhoto(gInfoData.getData().get(0).getArticle().get(2).getImg());
                            } else if (getAdapterPosition() < 16 + customerCount1 + customerCount2 + customerCount3 + customerCount4) {
                                iaInfo.handViewCustomerPhoto(gInfoData.getData().get(0).getArticle().get(3).getImg());
                            } else {
                                iaInfo.handViewCustomerPhoto(gInfoData.getData().get(0).getArticle().get(4).getImg());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            //click open photo view
            RxView.clicks(mImageViewsInHead.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Object o) {
                            iaInfo.handleSwitchPage(5);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
            //click save attraction
            RxView.clicks(mImageViewsInHead.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (MyApplication.getAppData().getRaid().length() == 0) {
                                MyApplication.getAppData().setRaid("WebSite");
                            }
                            if (gInfoData.getData().get(0).getColl().length() > 0) {
                                iaInfo.handleClickCollect(false, gInfoData.getData().get(0).getColl());
                            } else {
                                iaInfo.handleClickCollect(true, gInfoData.getData().get(0).getColl());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInHead.get(4))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (MyApplication.getAppData().getRaid().length() == 0) {
                                MyApplication.getAppData().setRaid("WebSite");
                            }
                            if (gInfoData.getData().get(0).getColl().length() > 0) {
                                iaInfo.handleClickCollect(false, gInfoData.getData().get(0).getColl());
                            } else {
                                iaInfo.handleClickCollect(true, gInfoData.getData().get(0).getColl());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            //click tag a
            RxView.clicks(mTextViewsInGoodsTag.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            MyApplication.getAppData().setSelectedTag(gInfoData.getData().get(0).getLabel().get(0));
                            iaInfo.handleSwitchPage(2);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInGoodsTag.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            MyApplication.getAppData().setSelectedTag(gInfoData.getData().get(0).getLabel().get(1));
                            iaInfo.handleSwitchPage(2);}

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInGoodsTag.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            MyApplication.getAppData().setSelectedTag(gInfoData.getData().get(0).getLabel().get(2));
                            iaInfo.handleSwitchPage(2);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            //click tag b
            RxView.clicks(mTextViewsInGoodsTag.get(5))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            MyApplication.getAppData().setSelectedTag(gInfoData.getData().get(0).getLabel().get(0));
                            iaInfo.handleSwitchPage(2);}

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInGoodsTag.get(6))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            MyApplication.getAppData().setSelectedTag(gInfoData.getData().get(0).getLabel().get(1));
                            iaInfo.handleSwitchPage(2);}

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInGoodsTag.get(7))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            MyApplication.getAppData().setSelectedTag(gInfoData.getData().get(0).getLabel().get(2));
                            iaInfo.handleSwitchPage(2);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textMore)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            MyApplication.getAppData().setSelectedTag("null");
                            iaInfo.handleSwitchPage(2);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsInMessageHead.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            String maid;
                            String wsid;
                            String type;
                            String privacy;
                            if (getAdapterPosition() < 16 + customerCount1) {
                                maid = gInfoData.getData().get(0).getArticle().get(0).getMaid();
                                wsid = gInfoData.getData().get(0).getArticle().get(0).getWsid();
                                privacy = gInfoData.getData().get(0).getArticle().get(0).getPrivacy();
                            } else if (getAdapterPosition() < 16 + customerCount1 + customerCount2) {
                                maid = gInfoData.getData().get(0).getArticle().get(1).getMaid();
                                wsid = gInfoData.getData().get(0).getArticle().get(1).getWsid();
                                privacy = gInfoData.getData().get(0).getArticle().get(1).getPrivacy();
                            } else if (getAdapterPosition() < 16 + customerCount1 + customerCount2 + customerCount3) {
                                maid = gInfoData.getData().get(0).getArticle().get(2).getMaid();
                                wsid = gInfoData.getData().get(0).getArticle().get(2).getWsid();
                                privacy = gInfoData.getData().get(0).getArticle().get(2).getPrivacy();
                            } else if (getAdapterPosition() < 16 + customerCount1 + customerCount2 + customerCount3 + customerCount4) {
                                maid = gInfoData.getData().get(0).getArticle().get(3).getMaid();
                                wsid = gInfoData.getData().get(0).getArticle().get(3).getWsid();
                                privacy = gInfoData.getData().get(0).getArticle().get(3).getPrivacy();
                            } else {
                                maid = gInfoData.getData().get(0).getArticle().get(4).getMaid();
                                wsid = gInfoData.getData().get(0).getArticle().get(4).getWsid();
                                privacy = gInfoData.getData().get(0).getArticle().get(4).getPrivacy();
                            }
                            if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals(maid)) {
                                type = "me";
                            } else {
                                type = "they";
                            }
                            iaInfo.handleClickHeadPhoto(maid, wsid, type, privacy);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInMessageHeadCustomer.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (getAdapterPosition() <= 16 + customerCount1) {
                                if (gInfoData.getData().get(0).getArticle().get(0).getCoding().equals("1")) {

                                    iaInfo.handleLinkClick(gInfoData.getData().get(0).getArticle().get(0).getTitle().get(2));
                                }
                            } else if (getAdapterPosition() <= 16 + customerCount1 + customerCount2) {
                                if (gInfoData.getData().get(0).getArticle().get(1).getCoding().equals("1")) {

                                    iaInfo.handleLinkClick(gInfoData.getData().get(0).getArticle().get(1).getTitle().get(2));
                                }
                            } else if (getAdapterPosition() <= 16 + customerCount1 + customerCount2 + customerCount3) {
                                if (gInfoData.getData().get(0).getArticle().get(2).getCoding().equals("1")) {

                                    iaInfo.handleLinkClick(gInfoData.getData().get(0).getArticle().get(2).getTitle().get(2));
                                }
                            } else if (getAdapterPosition() <= 16 + customerCount1 + customerCount2 + customerCount3 + customerCount4) {
                                if (gInfoData.getData().get(0).getArticle().get(3).getCoding().equals("1")) {

                                    iaInfo.handleLinkClick(gInfoData.getData().get(0).getArticle().get(3).getTitle().get(2));
                                }
                            } else {
                                if (gInfoData.getData().get(0).getArticle().get(4).getCoding().equals("1")) {

                                    iaInfo.handleLinkClick(gInfoData.getData().get(0).getArticle().get(4).getTitle().get(2));
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInMessageHeadCustomer.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (getAdapterPosition() <= 16 + customerCount1) {
                                if (gInfoData.getData().get(0).getArticle().get(0).getCoding().equals("1")) {

                                    iaInfo.handleLinkClick(gInfoData.getData().get(0).getArticle().get(0).getTitle().get(2));
                                }
                            } else if (getAdapterPosition() <= 16 + customerCount1 + customerCount2) {
                                if (gInfoData.getData().get(0).getArticle().get(1).getCoding().equals("1")) {

                                    iaInfo.handleLinkClick(gInfoData.getData().get(0).getArticle().get(1).getTitle().get(2));
                                }
                            } else if (getAdapterPosition() <= 16 + customerCount1 + customerCount2 + customerCount3) {
                                if (gInfoData.getData().get(0).getArticle().get(2).getCoding().equals("1")) {

                                    iaInfo.handleLinkClick(gInfoData.getData().get(0).getArticle().get(2).getTitle().get(2));
                                }
                            } else if (getAdapterPosition() <= 16 + customerCount1 + customerCount2 + customerCount3 + customerCount4) {
                                if (gInfoData.getData().get(0).getArticle().get(3).getCoding().equals("1")) {

                                    iaInfo.handleLinkClick(gInfoData.getData().get(0).getArticle().get(3).getTitle().get(2));
                                }
                            } else {
                                if (gInfoData.getData().get(0).getArticle().get(4).getCoding().equals("1")) {

                                    iaInfo.handleLinkClick(gInfoData.getData().get(0).getArticle().get(4).getTitle().get(2));
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsInHead.get(4))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (MyApplication.getAppData().getRaid().length() > 0) {
                                iaInfo.handleClickSave();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInHead.get(9))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (MyApplication.getAppData().getRaid().length() > 0) {
                                iaInfo.handleClickSave();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInHead.get(6))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaInfo.handleSwitchPage(6);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsInAttractionInfo.get(4))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaInfo.handleSwitchPage(7);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewInIntroduce)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaInfo.handleSwitchPage(7);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewListInMediaInfo.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaInfo.handleSwitchPage(7);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsInMessageHead.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            showMenu(itemView.getContext());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInHead.get(10))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaInfo.handleSwitchPage(6);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInHead.get(5))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            //Toast.makeText(itemView.getContext(), R.string.float_message_coming_soon, Toast.LENGTH_SHORT).show();
                            String language = "";
                            switch (MyApplication.getLanguageIndex()) {
                                case 1:
                                    language = "&l=TW";
                                    break;
                                case 2:
                                    language = "&l=CH";
                                    break;
                                case 3:
                                    language = "&l=JP";
                                    break;
                                default:
                                    language = "&l=EN";
                                    break;
                            }
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://www.switube.com/M/PinDetail.php?spid=" + gInfoData.getData().get(0).getSpid() + language));
                            itemView.getContext().startActivity(intent);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsInHead.get(3))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            //Toast.makeText(itemView.getContext(), R.string.float_message_coming_soon, Toast.LENGTH_SHORT).show();
                            String language = "";
                            switch (MyApplication.getLanguageIndex()) {
                                case 1:
                                    language = "&l=TW";
                                    break;
                                case 2:
                                    language = "&l=CH";
                                    break;
                                case 3:
                                    language = "&l=JP";
                                    break;
                                default:
                                    language = "&l=EN";
                                    break;
                            }
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://www.switube.com/M/PinDetail.php?spid=" + gInfoData.getData().get(0).getSpid() + language));
                            itemView.getContext().startActivity(intent);
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
                            Toast.makeText(itemView.getContext(), R.string.float_message_coming_soon, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsInMessageBody.get(3))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            Toast.makeText(itemView.getContext(), R.string.float_message_coming_soon, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }

        private void handSelectedMessage(int index) {
            if (index <= 16 + customerCount1) {
                iaInfo.handleSelectArtid(0);
            } else if (index <= 16 + customerCount1 + customerCount2) {
                iaInfo.handleSelectArtid(1);
            } else if (index <= 16 + customerCount1 + customerCount2 + customerCount3) {
                iaInfo.handleSelectArtid(2);
            } else if (index <= 16 + customerCount1 + customerCount2 + customerCount3 + customerCount4) {
                iaInfo.handleSelectArtid(3);
            } else {
                iaInfo.handleSelectArtid(4);
            }
        }

        private void showMenu(Context context) {
            PopupMenu popupMenu = new PopupMenu(context, mImageViewsInMessageHead.get(2));
            popupMenu.getMenuInflater().inflate(R.menu.menu_info_comment, popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (getAdapterPosition() < 16 + customerCount1) {
                        if (menuItem.getItemId() == R.id.menuDeleteInComment) {
                            iaInfo.handleDeleteComment(0, gInfoData);
                        } else if (menuItem.getItemId() == R.id.menuEditCommentInComment) {
                            iaInfo.handleEdit(0, gInfoData, true);
                        } else {
                            iaInfo.handleEdit(0, gInfoData, false);
                        }
                    } else if (getAdapterPosition() < 16 + customerCount1 + customerCount2) {
                        if (menuItem.getItemId() == R.id.menuDeleteInComment) {
                            iaInfo.handleDeleteComment(1, gInfoData);
                        } else if (menuItem.getItemId() == R.id.menuEditCommentInComment) {
                            iaInfo.handleEdit(1, gInfoData, true);
                        } else {
                            iaInfo.handleEdit(1, gInfoData, false);
                        }
                    } else if (getAdapterPosition() < 16 + customerCount1 + customerCount2 + customerCount3) {
                        if (menuItem.getItemId() == R.id.menuDeleteInComment) {
                            iaInfo.handleDeleteComment(2, gInfoData);
                        } else if (menuItem.getItemId() == R.id.menuEditCommentInComment) {
                            iaInfo.handleEdit(2, gInfoData, true);
                        } else {
                            iaInfo.handleEdit(2, gInfoData, false);
                        }
                    } else if (getAdapterPosition() < 16 + customerCount1 + customerCount2 + customerCount3 + customerCount4) {
                        if (menuItem.getItemId() == R.id.menuDeleteInComment) {
                            iaInfo.handleDeleteComment(3, gInfoData);
                        } else if (menuItem.getItemId() == R.id.menuEditCommentInComment) {
                            iaInfo.handleEdit(3, gInfoData, true);
                        } else {
                            iaInfo.handleEdit(3, gInfoData, false);
                        }
                    } else {
                        if (menuItem.getItemId() == R.id.menuDeleteInComment) {
                            iaInfo.handleDeleteComment(4, gInfoData);
                        } else if (menuItem.getItemId() == R.id.menuEditCommentInComment) {
                            iaInfo.handleEdit(4, gInfoData, true);
                        } else {
                            iaInfo.handleEdit(4, gInfoData, false);
                        }
                    }
                    return true;
                }
            });
        }
    }
}
