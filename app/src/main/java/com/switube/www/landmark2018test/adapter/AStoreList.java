package com.switube.www.landmark2018test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAStoreList;
import com.switube.www.landmark2018test.entity.EStoreList;

import java.util.ArrayList;
import java.util.List;

public class AStoreList extends RecyclerView.Adapter<AStoreList.ViewHolder> {
    private IAStoreList iaStoreList;
    private List<EStoreList> list;
    public AStoreList(IAStoreList iaStoreList) {
        this.iaStoreList = iaStoreList;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_store_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textName.setText(list.get(position).getName());
        holder.textMessage.setText(list.get(position).getMessage());
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
        private ImageView imageView;
        private TextView textName;
        private TextView textMessage;
        private View viewPepper;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagePhotoInItemStoreList);
            textName = itemView.findViewById(R.id.textTitleInItemStoreList);
            textMessage = itemView.findViewById(R.id.textContentInItemStoreList);
            viewPepper = itemView.findViewById(R.id.viewPepperInItemStoreList);
            viewPepper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iaStoreList.onItemClick(list.get(getAdapterPosition()).getId());
                }
            });
        }
    }
}
