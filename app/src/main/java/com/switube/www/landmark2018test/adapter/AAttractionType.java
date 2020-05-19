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
import com.switube.www.landmark2018test.adapter.callback.IAAttractionType;
import com.switube.www.landmark2018test.database.entity.AttractionModeEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AAttractionType extends RecyclerView.Adapter<AAttractionType.ViewHolder> {
    private List<AttractionModeEntity> mMode;
    private List<AttractionStyleEntity> mStyle;
    private IAAttractionType iaAttractionType;
    public AAttractionType(List<AttractionModeEntity> attractionModeEntities,
                           List<AttractionStyleEntity> attractionStyleEntities,
                           IAAttractionType iaAttractionType) {
        mMode = attractionModeEntities;
        mStyle = attractionStyleEntities;
        this.iaAttractionType = iaAttractionType;
        init();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_attraction_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (menuIdList.get(position).length() == 1) {
            int index = mmidList.indexOf(menuIdList.get(position));
            holder.imageView.setVisibility(View.VISIBLE);
            holder.viewBar.setVisibility(View.VISIBLE);
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    holder.textView.setText(mMode.get(index).getMmtitle_tw());
                    break;
                case 2:
                    holder.textView.setText(mMode.get(index).getMmtitle_ch());
                    break;
                case 3:
                    holder.textView.setText(mMode.get(index).getMmtitle_jp());
                    break;
                default:
                    holder.textView.setText(mMode.get(index).getMmtitle_en());
                    break;
            }
            switch (menuIdList.get(position)) {
                case "A":
                    holder.imageView.setImageResource(R.drawable.me_6_v11);
                    break;
                case "F":
                    holder.imageView.setImageResource(R.drawable.me_7_v11);
                    break;
                case "J":
                    holder.imageView.setImageResource(R.drawable.me_8_v11);
                    break;
                case "H":
                    holder.imageView.setImageResource(R.drawable.me_9_v11);
                    break;
                case "Z":
                    holder.imageView.setImageResource(R.drawable.me_10_v11);
                    break;
                case "P":
                    holder.imageView.setImageResource(R.drawable.me_11_v11);
                    break;
                default:
                    break;
            }
        } else {
            int index = list.indexOf(menuIdList.get(position));
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.viewBar.setVisibility(View.GONE);
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    holder.textView.setText(mStyle.get(index).getMstitle_tw());
                    break;
                case 2:
                    holder.textView.setText(mStyle.get(index).getMstitle_ch());
                    break;
                case 3:
                    holder.textView.setText(mStyle.get(index).getMstitle_jp());
                    break;
                default:
                    holder.textView.setText(mStyle.get(index).getMstitle_en());
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return menuIdList.size();
    }

    private List<String> menuIdList = new ArrayList<>();
    private List<String> mmidList = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private void init() {
        int size = mMode.size();
        for (int i = 0; i < size; i++) {
            mmidList.add(mMode.get(i).getMmid());
        }
        String mmid = "";
        int modeIndex = 0;
        size = mStyle.size();
        for (int i = 0; i < size; i++) {
            if (mStyle.get(i).getMmid().equals(mmid)) {
                menuIdList.add(mStyle.get(i).getMenuid());
                list.add(mStyle.get(i).getMenuid());
            } else {
                mmid = mMode.get(modeIndex).getMmid();
                menuIdList.add(mMode.get(modeIndex).getMmid());
                modeIndex++;
                i--;
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageHeadInItemAttractionType)
        ImageView imageView;
        @BindView(R.id.textTitleInItemAttractionType)
        TextView textView;
        @BindView(R.id.viewBarInItemAttractionType)
        View viewBar;
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
                            if (menuIdList.get(getAdapterPosition()).length() != 1) {
                                int index = list.indexOf(menuIdList.get(getAdapterPosition()));
                                iaAttractionType.handleSelectType(mStyle.get(index));
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
