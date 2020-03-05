package com.switube.www.landmark2018test.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAMyCollection;
import com.switube.www.landmark2018test.gson.GMyCollection;
import com.switube.www.landmark2018test.util.AppConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AMyCollection extends RecyclerView.Adapter<AMyCollection.ViewHolder> {
    private IAMyCollection iaMyCollection;
    private int size;
    public AMyCollection(IAMyCollection iaMyCollection) {
        this.iaMyCollection = iaMyCollection;
        size = 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_my_collection, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (gMyCollection.getData().get(position).getPhoto().size() > 0 && gMyCollection.getData().get(position).getPhoto() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(Uri.parse(AppConstant.BASE_URL2 + gMyCollection.getData().get(position).getPhoto().get(0)))
                    .into(holder.imageViewList.get(0));
        } else {
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.none_1)
                    .into(holder.imageViewList.get(0));
        }
        holder.textViewList.get(0).setText(gMyCollection.getData().get(position).getPlace());
        holder.textViewList.get(1).setText(distance.get(position));
        holder.textViewList.get(2).setText(gMyCollection.getData().get(position).getAddr());
    }

    @Override
    public int getItemCount() {
        return size;
    }

    private GMyCollection gMyCollection;
    private List<String> distance = new ArrayList<>();
    public void init(GMyCollection gMyCollection, List<String> distance) {
        size = gMyCollection.getData().size();
        this.gMyCollection = gMyCollection;
        this.distance = distance;
        notifyDataSetChanged();
    }

    public void finishRemove(int index) {
        gMyCollection.getData().remove(index);
        size = gMyCollection.getData().size();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindViews({R.id.textTitleInItemMyCollection, R.id.textSubInItemMyCollection, R.id.textAddressInItemMyCollection})
        List<TextView> textViewList;
        @BindViews({R.id.imagePhotoInItemMyCollection, R.id.imageRemoveInItemMyCollection, R.id.imageSaveInItemMyCollection})
        List<ImageView> imageViewList;
        @BindView(R.id.viewBarInItemMyCollection)
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(imageViewList.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaMyCollection.handleRemove(getAdapterPosition(), gMyCollection.getData().get(getAdapterPosition()).getSucid());
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
                            iaMyCollection.handleSave(gMyCollection.getData().get(getAdapterPosition()).getSpid());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewList.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaMyCollection.handleToInfoPage(gMyCollection.getData().get(getAdapterPosition()).getSpid());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(view)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaMyCollection.handleToInfoPage(gMyCollection.getData().get(getAdapterPosition()).getSpid());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
