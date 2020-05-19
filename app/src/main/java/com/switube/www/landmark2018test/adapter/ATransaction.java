package com.switube.www.landmark2018test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IATransaction;
import com.switube.www.landmark2018test.entity.ETransaction;

import java.util.ArrayList;
import java.util.List;

public class ATransaction extends RecyclerView.Adapter<ATransaction.ViewHolder> {
    private List<ETransaction> list = new ArrayList<>();
    private IATransaction iaTransaction;
    public ATransaction(IATransaction iaTransaction) {
        this.iaTransaction = iaTransaction;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_transaction, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textCash.setText(list.get(position).getCash());
        holder.textName.setText(list.get(position).getName());
        holder.textType.setText(list.get(position).getType());
        holder.textDate.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<ETransaction> transactions) {
        list.clear();
        list.addAll(transactions);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textDate;
        private TextView textCash;
        private TextView textType;
        private TextView textName;
        private View viewPepper;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textCash = itemView.findViewById(R.id.textCashInItemTransaction);
            textDate = itemView.findViewById(R.id.textDateInItemTransaction);
            textName = itemView.findViewById(R.id.textNameInItemTransaction);
            textType = itemView.findViewById(R.id.textTypeInItemTransaction);
            viewPepper = itemView.findViewById(R.id.viewPepperInItemTransaction);
            viewPepper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iaTransaction.onItemClick(list.get(getAdapterPosition()));
                }
            });
        }
    }
}
