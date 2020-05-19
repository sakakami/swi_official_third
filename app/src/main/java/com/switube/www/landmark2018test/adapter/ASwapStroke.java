package com.switube.www.landmark2018test.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.entity.ESwapData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ASwapStroke extends RecyclerView.Adapter<ASwapStroke.ViewHolder> {
    private List<ESwapData> eSwapDataList;
    public ASwapStroke() {
        eSwapDataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_swap_stroke, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(eSwapDataList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return eSwapDataList.size();
    }

    public void init(List<ESwapData> eSwapDataList) {
        this.eSwapDataList = eSwapDataList;
        notifyDataSetChanged();
    }

    public List<ESwapData> getgStrokeList() {
        return eSwapDataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textTitleInItemSwapStroke)
        TextView textView;
        @BindView(R.id.viewBarInItemSwapStroke)
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
