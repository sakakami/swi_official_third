package com.switube.www.landmark2018test.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAWallet;

import java.util.ArrayList;
import java.util.List;

public class AWallet extends RecyclerView.Adapter<AWallet.ViewHolder> {
    private IAWallet iaWallet;
    private List<String> list;
    public AWallet(IAWallet iaWallet) {
        this.iaWallet = iaWallet;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_wallet, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<String> item) {
        list = item;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textNameInItemWallet);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iaWallet.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
