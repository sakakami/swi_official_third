package com.switube.www.landmark2018test.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAPushData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class APushData extends RecyclerView.Adapter<APushData.ViewHolder> {
    private IAPushData iaPushData;
    public APushData(IAPushData iaPushData) {
        this.iaPushData = iaPushData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_mobile_music, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewList.get(0).setText(name.get(position));
        holder.textViewList.get(1).setText(count.get(position));
        Glide.with(MyApplication.getInstance())
                .load(img.get(position))
                .into(holder.imageViewList.get(0));
        if (love.get(position).equals("0")) {
            holder.imageViewList.get(1).setImageResource(R.drawable.love_off_2x);
        } else {
            holder.imageViewList.get(1).setImageResource(R.drawable.love_on_2x);
        }
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    private List<String> name = new ArrayList<>();
    private List<String> img = new ArrayList<>();
    private List<String> count = new ArrayList<>();
    private List<String> love = new ArrayList<>();
    public void refreshAdapter(List<String> name, List<String> img, List<String> count, List<String> love) {
        this.name = name;
        this.img = img;
        this.count = count;
        this.love = love;
        notifyDataSetChanged();
    }
    public void refreshAdapter(int index, String love) {
        this.love.set(index, love);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindViews({R.id.imagePictureGInItemMobileMusic, R.id.imageLikeGInItemMobileMusic})
        List<ImageView> imageViewList;
        @BindViews({R.id.textTitleGInItemMobileMusic, R.id.textCountsGInItemMobileMusic})
        List<TextView> textViewList;
        @BindView(R.id.layoutGrid)
        RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            relativeLayout.setVisibility(View.VISIBLE);
            if (MyApplication.getAppData().getMusicMode() == 0) {
                imageViewList.get(1).setVisibility(View.GONE);
                textViewList.get(1).setVisibility(View.GONE);
            }
            RxView.clicks(imageViewList.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaPushData.handleClickInPush(getAdapterPosition());
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
                            iaPushData.handleClickInPush(getAdapterPosition());
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
                            iaPushData.handleLikeInPush(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
