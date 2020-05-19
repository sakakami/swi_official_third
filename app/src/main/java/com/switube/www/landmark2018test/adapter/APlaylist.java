package com.switube.www.landmark2018test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAPlaylist;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class APlaylist extends RecyclerView.Adapter<APlaylist.ViewHolder> {
    private IAPlaylist iaPlaylist;
    public APlaylist(IAPlaylist iaPlaylist) {
        this.iaPlaylist = iaPlaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_playlist, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(name.get(position));
        if (position == MyApplication.getAppData().getPlayingIndex()) {
            holder.viewList.get(0).setVisibility(View.VISIBLE);
            if (MyApplication.getAppData().isPlaying()) {
                holder.imageView.setImageResource(R.drawable.ic_list_stop_bt_v1_2x);
            } else {
                holder.imageView.setImageResource(R.drawable.ic_list_play_bt_v2_2x);
            }
        } else {
            holder.imageView.setImageResource(R.drawable.ic_list_music_bt_v1_2x);
            holder.viewList.get(0).setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    private List<String> name = new ArrayList<>();
    public void refreshAdapter(List<String> name) {
        this.name = name;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindViews({R.id.viewBarHighLightInItemPlaylist, R.id.viewBarLineInPlaylist})
        List<View> viewList;
        @BindView(R.id.textTitleInItemPlaylist)
        TextView textView;
        @BindView(R.id.imageMarkInItemPlaylist)
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(textView)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (getAdapterPosition() >= 0) {
                                if (getAdapterPosition() == MyApplication.getAppData().getPlayingIndex()) {
                                    if (MyApplication.getAppData().getiFloatPlayerService().getCheckFloatCanSee()) {
                                        if (MyApplication.getAppData().isPlaying()) {
                                            MyApplication.getAppData().getiFloatPlayerService().switchPlayMode(false);
                                        } else {
                                            MyApplication.getAppData().getiFloatPlayerService().switchPlayMode(true);
                                        }
                                    }
                                } else {
                                    MyApplication.getAppData().setPlayingIndex(getAdapterPosition());
                                    iaPlaylist.handleClick(getAdapterPosition());
                                }
                                notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
