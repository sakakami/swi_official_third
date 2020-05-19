package com.switube.www.landmark2018test.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.ATicketList;
import com.switube.www.landmark2018test.adapter.callback.IATicketList;
import com.switube.www.landmark2018test.gson.GTicket;
import com.switube.www.landmark2018test.presenter.PTicketList;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.view.callback.IVMyExchange;
import com.switube.www.landmark2018test.view.callback.IVTicketList;

import java.util.List;

public class VTicketList extends LinearLayout implements IVTicketList, IATicketList {
    private ATicketList aTicketList;
    private PTicketList pTicketList;
    private IVMyExchange ivMyExchange;
    public VTicketList(Context context) {
        this(context, null);
    }

    public VTicketList(Context context, IVMyExchange ivMyExchange) {
        super(context);
        this.ivMyExchange = ivMyExchange;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_ticket_list, this, false);
        pTicketList = new PTicketList(this);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerInTicketList);
        aTicketList = new ATicketList(this);
        recyclerView.addItemDecoration(new ItemDecorationUtil(context, 0, 8, 0, 0));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(aTicketList);
        addView(view);
    }

    public void setPageOne(boolean pageOne) {
        pTicketList.getTickets(pageOne);
    }

    @Override
    public void refreshAdapter(List<GTicket.Data> list) {
        aTicketList.setList(list);
    }

    @Override
    public void onItemClick(GTicket.Data data) {
        MyApplication.getAppData().setTicketData(data);
        ivMyExchange.toNextPage();
    }
}
