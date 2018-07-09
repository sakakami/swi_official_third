package com.switube.www.swiofficialthird.map.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.map.InterfaceAttraction;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.ViewHolder> {
    private Context mContext;
    private InterfaceAttraction mInterfaceAttraction;
    public AttractionAdapter(Context context, InterfaceAttraction interfaceAttraction) {
        mContext = context;
        mInterfaceAttraction = interfaceAttraction;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.holder_item_create, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imagePhotoInItemCreate) ImageView mImagePhoto;
        @BindView(R.id.textTitleInItemCreate) TextView mTextTitle;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mTextTitle.setText("台中秀泰影城");
            RxView.clicks(mTextTitle).subscribe(new Observer<Object>() {
                @Override
                public void onSubscribe(Disposable d) {}

                @Override
                public void onNext(Object o) {
                    mInterfaceAttraction.handleSwitch();
                }

                @Override
                public void onError(Throwable e) {}

                @Override
                public void onComplete() {}
            });
        }
    }
}
