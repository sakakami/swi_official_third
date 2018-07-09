package com.switube.www.swiofficialthird.map.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TagTypeAdapter extends RecyclerView.Adapter<TagTypeAdapter.ViewHolder> {
    private Context mContext;
    private String[] type = new String[] {"住宿", "旅遊景點", "美食", "購物中心", "大眾運輸", "私房景點", "遊樂園"};
    private List<Boolean> selected = new ArrayList<>();
    public TagTypeAdapter(Context context) {
        mContext = context;
        selected.add(true);
        selected.add(false);
        selected.add(false);
        selected.add(false);
        selected.add(false);
        selected.add(false);
        selected.add(false);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.holder_item_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextTitle.setText(type[position]);
        if (selected.get(position)) {
            holder.mTextTitle.setBackgroundResource(R.drawable.background_white_full);
        } else {
            holder.mTextTitle.setBackground(null);
        }
    }

    @Override
    public int getItemCount() {
        return type.length;
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
