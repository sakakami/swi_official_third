package com.switube.www.landmark2018test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAEco;
import com.switube.www.landmark2018test.entity.EEcoList;

import java.util.ArrayList;

public class AEco extends RecyclerView.Adapter<AEco.ViewHolder> {
    private ArrayList<EEcoList> eEcoLists = new ArrayList<>();
    private IAEco iaEco;
    public AEco(ArrayList<EEcoList> list, IAEco iaEco) {
        eEcoLists.addAll(list);
        this.iaEco = iaEco;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_cash_flow, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(eEcoLists.get(position).getDate());
        holder.name.setText(eEcoLists.get(position).getName());
        holder.distance.setText(eEcoLists.get(position).getDistance());
        holder.carbon.setText(eEcoLists.get(position).getSpeed());
    }

    @Override
    public int getItemCount() {
        return eEcoLists.size();
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
            date.setOnClickListener(view -> iaEco.handleItemClick(eEcoLists.get(getAdapterPosition())));
            name.setOnClickListener(view -> iaEco.handleItemClick(eEcoLists.get(getAdapterPosition())));
            distance.setOnClickListener(view -> iaEco.handleItemClick(eEcoLists.get(getAdapterPosition())));
            carbon.setOnClickListener(view -> iaEco.handleItemClick(eEcoLists.get(getAdapterPosition())));
        }
    }
}
