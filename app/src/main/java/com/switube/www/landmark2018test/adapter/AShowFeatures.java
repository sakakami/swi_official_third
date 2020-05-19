package com.switube.www.landmark2018test.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.entity.EFeatures;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AShowFeatures extends RecyclerView.Adapter<AShowFeatures.ViewHolder> {
    private List<EFeatures> eFeaturesList;
    public AShowFeatures() {
        eFeaturesList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_show_features, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (eFeaturesList.get(position).isCanShow()) {
            holder.imageView.setVisibility(View.VISIBLE);
            holder.view.setVisibility(View.GONE);
        } else {
            holder.imageView.setVisibility(View.GONE);
            if (eFeaturesList.get(position).getFrom().equals("n")) {
                holder.view.setVisibility(View.VISIBLE);
            } else {
                holder.view.setVisibility(View.GONE);
            }
        }
        holder.textView.setText(eFeaturesList.get(position).getTitle());
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
        @BindView(R.id.imageHeadInItemShowFeatures)
        ImageView imageView;
        @BindView(R.id.textContentInItemShowFeatures)
        TextView textView;
        @BindView(R.id.viewBarInItemShowFeatures)
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
