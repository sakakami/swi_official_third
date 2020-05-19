package com.switube.www.landmark2018test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAExchangeList;
import com.switube.www.landmark2018test.entity.EStoreList;

import java.util.ArrayList;
import java.util.List;

public class AExchangeList extends RecyclerView.Adapter<AExchangeList.ViewHolder> {
    private IAExchangeList iaExchangeList;
    private List<EStoreList> list;
    public AExchangeList(IAExchangeList iaExchangeList) {
        this.iaExchangeList = iaExchangeList;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_exchange_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textName.setText(list.get(position).getName());
        holder.textCount.setText(list.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<EStoreList> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //private ImageView imageView;
        private TextView textName;
        private TextView textCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //imageView = itemView.findViewById(R.id.imagePhotoInItemExchangeList);
            textName = itemView.findViewById(R.id.textNameInItemExchangeList);
            textCount = itemView.findViewById(R.id.textCountInItemExchangeList);
            View viewPepper = itemView.findViewById(R.id.viewPepperInItemExchangeList);
            viewPepper.setOnClickListener(view -> iaExchangeList.onItemClick(list.get(getAdapterPosition())));
        }
    }
}
