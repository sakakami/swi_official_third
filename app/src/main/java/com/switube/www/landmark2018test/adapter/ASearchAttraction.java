package com.switube.www.landmark2018test.adapter;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IASearchAttraction;
import com.switube.www.landmark2018test.gson.GSearchAttractionDetail;
import com.switube.www.landmark2018test.util.AppConstant;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ASearchAttraction extends RecyclerView.Adapter<ASearchAttraction.ViewHolder> {
    private GSearchAttractionDetail mSearchPlaceDeatilDataEntity;
    private List<String> mStyle;
    private List<String> mTime;
    private List<String> mOpen;
    private IASearchAttraction iaSearchAttraction;
    private RequestOptions mOptions;
    public ASearchAttraction(IASearchAttraction iaSearchAttraction, GSearchAttractionDetail gSearchAttractionDetail, List<String> style, List<String> time, List<String> open) {
        mSearchPlaceDeatilDataEntity = gSearchAttractionDetail;
        mStyle = style;
        mTime = time;
        mOpen = open;
        this.iaSearchAttraction = iaSearchAttraction;
        mOptions = new RequestOptions()
                .skipMemoryCache(true);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_place, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mSearchPlaceDeatilDataEntity.getData().get(position).getPhoto().size() == 0 || mSearchPlaceDeatilDataEntity.getData().get(position).getPhoto().get(0).equals("null")) {
            Glide.with(MyApplication.getInstance())
                    .load(R.drawable.none_1)
                    .into(holder.imagePhoto);
        } else {
            Glide.with(holder.itemView.getContext().getApplicationContext())
                    .load(Uri.parse(AppConstant.BASE_URL + mSearchPlaceDeatilDataEntity.getData().get(position).getPhoto().get(0)))
                    .apply(mOptions)
                    .into(holder.imagePhoto);
        }
        holder.mTextView.setText(mSearchPlaceDeatilDataEntity.getData().get(position).getPlace());
        holder.mTextViewsA.get(0).setText(mSearchPlaceDeatilDataEntity.getData().get(position).getRating());
        holder.mRatingBar.setRating(Float.parseFloat(mSearchPlaceDeatilDataEntity.getData().get(position).getRating()));
        holder.mTextViewsA.get(2).setText(mStyle.get(position));
        switch (mOpen.get(position)) {
            case "0":
                String text = MyApplication.getInstance().getString(R.string.map_status_open);
                holder.mTextViewsA.get(3).setText(text);
                break;
            case "1":
                text = MyApplication.getInstance().getString(R.string.map_status_close);
                holder.mTextViewsA.get(3).setText(text);
                break;
            default:
                holder.mTextViewsA.get(3).setText(R.string.no_time_data);
                break;
        }
        holder.mTextViewsA.get(4).setText(mTime.get(position));
        if (mSearchPlaceDeatilDataEntity.getData().get(position).getColl().length() > 0) {
            holder.mImageSave.setImageResource(R.drawable.mark_on_v14);
        } else {
            holder.mImageSave.setImageResource(R.drawable.mark_off_v12);
        }
    }

    @Override
    public int getItemCount() {
        return mSearchPlaceDeatilDataEntity.getData().size();
    }

    public void refreshAdapter(GSearchAttractionDetail gSearchAttractionDetail, List<String> style, List<String> time, List<String> open) {
        mSearchPlaceDeatilDataEntity = gSearchAttractionDetail;
        mStyle = style;
        mTime = time;
        mOpen = open;
        notifyDataSetChanged();
    }

    public void refreshAdapter(int index, String sucid) {
        mSearchPlaceDeatilDataEntity.getData().get(index).setColl(sucid);
        notifyDataSetChanged();
    }

    public String getSpid(int index) {
        return mSearchPlaceDeatilDataEntity.getData().get(index).getSpid();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imagePhotoInItemPlace)
        ImageView imagePhoto;
        @BindView(R.id.textTitleInItemPlace)
        TextView mTextView;
        @BindView(R.id.imageSaveInItemPlace)
        ImageView mImageSave;
        @BindView(R.id.imageCollectInItemPlace)
        ImageView mImageCollect;
        @BindView(R.id.layoutAInItemPlace)
        RelativeLayout relativeLayout;
        @BindViews({R.id.textRatingAInItemPlace, R.id.textCountAInItemPlace, R.id.textTypeAInItemPlace,
                R.id.textStatusAInItemPlace, R.id.textTimeAInItemPlace})
        List<TextView> mTextViewsA;
        @BindView(R.id.ratingAInItemPlace)
        RatingBar mRatingBar;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(relativeLayout)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaSearchAttraction.SwitchPage(mSearchPlaceDeatilDataEntity.getData().get(getAdapterPosition()).getSpid());
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("placeError", e.toString());
                        }

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageSave)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (mSearchPlaceDeatilDataEntity.getData().get(getAdapterPosition()).getColl().length() > 0) {
                                iaSearchAttraction.handleSaveAttractions(mSearchPlaceDeatilDataEntity.getData().get(getAdapterPosition()).getColl(), false, getAdapterPosition());
                            } else {
                                iaSearchAttraction.handleSaveAttractions(mSearchPlaceDeatilDataEntity.getData().get(getAdapterPosition()).getSpid(), true, getAdapterPosition());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageCollect)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaSearchAttraction.handleCollectAttractions(mSearchPlaceDeatilDataEntity.getData().get(getAdapterPosition()).getSpid());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextView)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaSearchAttraction.SwitchPage(mSearchPlaceDeatilDataEntity.getData().get(getAdapterPosition()).getSpid());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imagePhoto)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaSearchAttraction.SwitchPage(mSearchPlaceDeatilDataEntity.getData().get(getAdapterPosition()).getSpid());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
