package com.switube.www.landmark2018test.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IATag;
import com.switube.www.landmark2018test.entity.ETagQB;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ATagType extends RecyclerView.Adapter<ATagType.ViewHolder> {
    private List<ETagQB> eTagQB;
    private List<Boolean> selected = new ArrayList<>();
    private IATag iATag;
    public ATagType(List<ETagQB> eTagQB, IATag iATag) {
        this.eTagQB = eTagQB;
        this.iATag = iATag;
        int size = eTagQB.size();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                selected.add(true);
            } else {
                selected.add(false);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextTitle.setText(eTagQB.get(position).getQb_name());
        if (selected.get(position)) {
            holder.mTextTitle.setBackgroundResource(R.drawable.bg_gray_full_7b7b7b);
            holder.mTextTitle.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.mTextTitle.setBackgroundResource(R.drawable.bg_white_full_f0f0f0);
            holder.mTextTitle.setTextColor(Color.parseColor("#808080"));
        }
    }

    @Override
    public int getItemCount() {
        return eTagQB.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textTitleInItemTypeTag) TextView mTextTitle;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(mTextTitle)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iATag.handleGetData(eTagQB.get(getAdapterPosition()).getQbid());
                            int index = selected.indexOf(true);
                            selected.set(index, false);
                            selected.set(getAdapterPosition(), true);
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
