package com.switube.www.landmark2018test.adapter.callback;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.entity.ECashFlow;

import java.util.ArrayList;

public class ACashFlow extends RecyclerView.Adapter<ACashFlow.ViewHolder> {
    private ArrayList<ECashFlow> data;

    public ACashFlow(ArrayList<ECashFlow> cashFlows) {
        data = new ArrayList<>();
        data.addAll(cashFlows);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_cash_flow, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cash.setText(data.get(position).getCash());
        holder.name.setText(data.get(position).getName());
        holder.date.setText(data.get(position).getDate());
        holder.type.setText(data.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView name;
        TextView type;
        TextView cash;
        ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.textDateItemCashFlow);
            name = itemView.findViewById(R.id.textNameItemCashFlow);
            type = itemView.findViewById(R.id.textMessageItemCashFlow);
            cash = itemView.findViewById(R.id.textCashItemCashFlow);
        }
    }
}
