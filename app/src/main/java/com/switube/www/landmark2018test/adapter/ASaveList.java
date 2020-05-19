package com.switube.www.landmark2018test.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.gson.GSaveList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ASaveList extends RecyclerView.Adapter<ASaveList.ViewHolder> {
    private GSaveList gSaveList;
    private List<Boolean> isChecked = new ArrayList<>();
    public ASaveList(GSaveList gSaveList) {
        this.gSaveList = gSaveList;
        int size = gSaveList.getData().size();
        for (int i = 0; i < size; i++) {
            isChecked.add(false);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_stroke_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(gSaveList.getData().get(position).getTitle());
        holder.checkBox.setChecked(isChecked.get(position));
    }

    @Override
    public int getItemCount() {
        return gSaveList.getData().size();
    }

    public List<Boolean> getIsChecked() {
        return isChecked;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.checkboxInItemStrokeList)
        CheckBox checkBox;
        @BindView(R.id.textTitleInItemStrokeList)
        TextView textView;
        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(textView)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (gSaveList.getData().get(getAdapterPosition()).getCut().equals("50")) {
                                Toast.makeText(itemView.getContext(), R.string.stroke_title_action_add, Toast.LENGTH_SHORT).show();
                            } else {
                                if (isChecked.get(getAdapterPosition())) {
                                    isChecked.set(getAdapterPosition(), false);
                                } else {
                                    isChecked.set(getAdapterPosition(), true);
                                }
                                notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onError(Throwable e) { }

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(checkBox)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (gSaveList.getData().get(getAdapterPosition()).getCut().equals("50")) {
                                Toast.makeText(itemView.getContext(), R.string.stroke_title_action_add, Toast.LENGTH_SHORT).show();
                            } else {
                                if (isChecked.get(getAdapterPosition())) {
                                    isChecked.set(getAdapterPosition(), false);
                                } else {
                                    isChecked.set(getAdapterPosition(), true);
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
