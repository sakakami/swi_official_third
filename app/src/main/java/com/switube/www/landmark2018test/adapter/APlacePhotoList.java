package com.switube.www.landmark2018test.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAPlacePhotoList;
import com.switube.www.landmark2018test.util.AppConstant;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class APlacePhotoList extends RecyclerView.Adapter<APlacePhotoList.ViewHolder> {
    private List<String> mPhoto;
    private RequestOptions mOptions;
    private int size;
    private IAPlacePhotoList iaPlacePhotoList;
    APlacePhotoList(IAPlacePhotoList iaPlacePhotoList) {
        this.iaPlacePhotoList = iaPlacePhotoList;
        size = 0;
        mOptions = new RequestOptions()
                .error(R.drawable.none_1)
                .skipMemoryCache(true);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_place_place, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mPhoto.size() == 0) {
            Glide.with(MyApplication.getInstance())
                    .load(R.drawable.none_1)
                    .into(holder.mImageView);
        } else {
            if (position == 0 && mPhoto.get(position).equals("null")) {
                Glide.with(MyApplication.getInstance())
                        .load(R.drawable.none_1)
                        .into(holder.mImageView);
            } else {
                Glide.with(holder.itemView.getContext().getApplicationContext())
                        .load(Uri.parse(AppConstant.BASE_URL + mPhoto.get(position)))
                        .apply(mOptions)
                        .into(holder.mImageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (size > 3) {
            return 3;
        } else {
            if (size == 0) {
                return 1;
            } else {
                return size;
            }
        }
    }

    private int index = -1;
    public void refreshAdapter(List<String> photo, boolean hadLimit, int index) {
        if (hadLimit) {
            size = Math.min(photo.size(), 3);
        } else {
            size = photo.size();
        }
        mPhoto = photo;
        this.index = index;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imagePictureInItemPlacePlace)
        ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(mImageView)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaPlacePhotoList.handlePhotoClicked(index);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
