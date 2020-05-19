package com.switube.www.landmark2018test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IATicketList;
import com.switube.www.landmark2018test.gson.GTicket;

import java.util.ArrayList;
import java.util.List;

public class ATicketList extends RecyclerView.Adapter<ATicketList.ViewHolder> {
    private List<GTicket.Data> list = new ArrayList<>();
    private IATicketList iaTicketList;
    public ATicketList(IATicketList iaTicketList) {
        this.iaTicketList = iaTicketList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_tickets, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.textName.setText(list.get(position).getItemName());
        holder.textName.setText(list.get(position).getGoodName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<GTicket.Data> entities) {
        list.clear();
        list.addAll(entities);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textName;
        private TextView textMessage;
        private ImageView imageView;
        private View pepper;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textMessage = itemView.findViewById(R.id.textMessageInItemTickets);
            textName = itemView.findViewById(R.id.textNameInItemTickets);
            imageView = itemView.findViewById(R.id.imagePhotoInItemTickets);
            pepper = itemView.findViewById(R.id.viewPepperInItemTickets);
            pepper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iaTicketList.onItemClick(list.get(getAdapterPosition()));
                }
            });
        }
    }
}
