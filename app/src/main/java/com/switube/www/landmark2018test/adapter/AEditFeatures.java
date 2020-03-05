package com.switube.www.landmark2018test.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.entity.EFeatures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AEditFeatures extends RecyclerView.Adapter<AEditFeatures.ViewHolder> {
    private List<EFeatures> eFeaturesList;
    public AEditFeatures() {
        eFeaturesList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_select_service, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (eFeaturesList.get(position).isCanShow()) {
            if (eFeaturesList.get(position).isCanSelect()) {
                holder.imageView.setVisibility(View.VISIBLE);
            } else {
                holder.imageView.setVisibility(View.GONE);
            }
            if (eFeaturesList.get(position).isCanSelect()) {
                holder.viewList.get(0).setVisibility(View.VISIBLE);
            } else {
                holder.viewList.get(0).setVisibility(View.INVISIBLE);
            }
            holder.viewList.get(1).setVisibility(View.GONE);
            holder.textView.setVisibility(View.VISIBLE);
            holder.textView.setText(eFeaturesList.get(position).getTitle());
        } else {
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.textView.setVisibility(View.INVISIBLE);
            holder.viewList.get(0).setVisibility(View.INVISIBLE);
            holder.viewList.get(1).setVisibility(View.VISIBLE);
        }

        if (eFeaturesList.get(position).isSelect()) {
            holder.viewList.get(0).setBackgroundResource(R.drawable.bg_blue_full);
            holder.imageView.setImageResource(R.drawable.check_w_v1_1);
        } else {
            holder.viewList.get(0).setBackgroundResource(R.drawable.bg_gray_2_surround);
            holder.imageView.setImageResource(R.drawable.add_v1_1);
        }
    }

    @Override
    public int getItemCount() {
        return eFeaturesList.size();
    }

    public void init(List<EFeatures> eFeaturesList) {
        this.eFeaturesList = eFeaturesList;
        notifyDataSetChanged();
    }

    public List<EFeatures> geteFeaturesList() {
        return eFeaturesList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textContentInItemSelectService)
        TextView textView;
        @BindView(R.id.imageHeadInItemSelectService)
        ImageView imageView;
        @BindViews({R.id.viewBarInItemSelectService, R.id.viewBar2InItemSelectService})
        List<View> viewList;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(viewList.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (eFeaturesList.get(getAdapterPosition()).isCanSelect()) {
                                if (eFeaturesList.get(getAdapterPosition()).isSingle()) {
                                    int size = eFeaturesList.size();
                                    for (int i = 0; i < size; i++) {
                                        if (eFeaturesList.get(i).isSingle()) {
                                            eFeaturesList.get(i).setSelect(false);
                                        }
                                    }
                                    eFeaturesList.get(getAdapterPosition()).setSelect(true);
                                } else {
                                    if (eFeaturesList.get(getAdapterPosition()).isSelect()) {
                                        eFeaturesList.get(getAdapterPosition()).setSelect(false);
                                    } else {
                                        eFeaturesList.get(getAdapterPosition()).setSelect(true);
                                    }
                                }
                            }
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
