package com.switube.www.landmark2018test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IACarbon;
import com.switube.www.landmark2018test.entity.ECarBonList;

import java.util.ArrayList;

public class ACarbon extends RecyclerView.Adapter<ACarbon.ViewHolder> {
    private ArrayList<ECarBonList> eCarBonLists = new ArrayList<>();
    private IACarbon iaCarbon;
    public ACarbon(ArrayList<ECarBonList> list, IACarbon iaCarbon) {
        eCarBonLists.addAll(list);
        this.iaCarbon = iaCarbon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_cash_flow, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(eCarBonLists.get(position).getDate());
        holder.name.setText(eCarBonLists.get(position).getName());
        holder.distance.setText(eCarBonLists.get(position).getDistance());
        holder.carbon.setText(eCarBonLists.get(position).getCarbon());
    }

    @Override
    public int getItemCount() {
        return eCarBonLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView name;
        TextView distance;
        TextView carbon;
        public ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.textDateItemCashFlow);
            name = itemView.findViewById(R.id.textNameItemCashFlow);
            distance = itemView.findViewById(R.id.textMessageItemCashFlow);
            carbon = itemView.findViewById(R.id.textCashItemCashFlow);
            date.setOnClickListener(view -> iaCarbon.handleItemClick(eCarBonLists.get(getAdapterPosition())));
            name.setOnClickListener(view -> iaCarbon.handleItemClick(eCarBonLists.get(getAdapterPosition())));
            distance.setOnClickListener(view -> iaCarbon.handleItemClick(eCarBonLists.get(getAdapterPosition())));
            carbon.setOnClickListener(view -> iaCarbon.handleItemClick(eCarBonLists.get(getAdapterPosition())));
        }
    }
}
