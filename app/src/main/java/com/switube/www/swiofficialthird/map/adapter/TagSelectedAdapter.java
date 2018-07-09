package com.switube.www.swiofficialthird.map.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.map.InterfaceTag;
import com.switube.www.swiofficialthird.util.SharePreferencesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TagSelectedAdapter extends RecyclerView.Adapter<TagSelectedAdapter.ViewHolder> {
    private Context mContext;
    private static List<String> titleA = new ArrayList<>();
    private static List<String> titleB = new ArrayList<>();
    private InterfaceTag interfaceTag;
    public TagSelectedAdapter(Context context, InterfaceTag interfaceTag) {
        mContext = context;
        this.interfaceTag = interfaceTag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.holder_tiem_selected, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (titleA.size() == titleB.size()) {
            //holder.mTextViews.get(0).setVisibility(View.VISIBLE);
            //holder.mImageViews.get(0).setVisibility(View.VISIBLE);
            holder.mTextViews.get(0).setText(titleA.get(position));
            holder.mTextViews.get(1).setVisibility(View.VISIBLE);
            holder.mTextViews.get(1).setText(titleB.get(position));
            holder.mImageViews.get(1).setVisibility(View.VISIBLE);
        } else {
            //holder.mTextViews.get(0).setVisibility(View.VISIBLE);
            //holder.mImageViews.get(0).setVisibility(View.VISIBLE);
            holder.mTextViews.get(0).setText(titleA.get(position));
            if (position < titleB.size()) {
                holder.mTextViews.get(1).setVisibility(View.VISIBLE);
                holder.mTextViews.get(1).setText(titleB.get(position));
                holder.mImageViews.get(1).setVisibility(View.VISIBLE);
            } else {
                holder.mTextViews.get(1).setVisibility(View.GONE);
                //holder.mTextViews.get(1).setText(titleB.get(position));
                holder.mImageViews.get(1).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return titleA.size();
    }

    public void handleAddTagA(String tag) {
        titleA.add(tag);
        notifyDataSetChanged();
    }

    public void handleAddTagB(String tag) {
        titleB.add(tag);
        notifyDataSetChanged();
    }

    public List<String> getTitleA() {
        return titleA;
    }

    public List<String> getTitleB() {
        return titleB;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindViews({R.id.textTitleAInItemSelected, R.id.textTitleBInItemSelected})
        List<TextView> mTextViews;
        @BindViews({R.id.imageCleanAInItemSelected, R.id.imageCleanBInItemSelected})
        List<ImageView> mImageViews;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(mTextViews.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handleClickItem(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViews.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handleClickItem(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViews.get(1))
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handleClickItem(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViews.get(1))
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handleClickItem(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }

        private void handleClickItem(int position) {
            if (titleA.size() == titleB.size()) {
                titleA.remove(position);
                titleB.remove(position);
            } else {
                titleA.remove(position);
                interfaceTag.handleDefault();
            }
            notifyDataSetChanged();
        }
    }
}
