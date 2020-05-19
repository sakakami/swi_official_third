package com.switube.www.landmark2018test.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.AExchangeList;
import com.switube.www.landmark2018test.adapter.callback.IAExchangeList;
import com.switube.www.landmark2018test.entity.EStoreList;
import com.switube.www.landmark2018test.presenter.PExchangeList;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.view.callback.IVExchangeList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VExchangeList extends Fragment implements IVExchangeList, IAExchangeList {
    private Unbinder unbinder;
    private PExchangeList pExchangeList;
    private AExchangeList aExchangeList;
    @BindView(R.id.recyclerInExchangeList)
    RecyclerView recyclerView;
    @BindView(R.id.textTitleInExchangeList)
    TextView textView;
    public VExchangeList() {
        pExchangeList = new PExchangeList(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_exchange_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        aExchangeList = new AExchangeList(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new ItemDecorationUtil(getContext(), 0, 20, 18, 0));
        recyclerView.setAdapter(aExchangeList);
        String cash = "碳幣餘額：" + MyApplication.getAppData().getCarbonCash();
        textView.setText(cash);
        pExchangeList.init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(EStoreList eStoreList) {
        MyApplication.getAppData().seteStoreList(eStoreList);
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VExchangeItem()).addToBackStack("ExchangeList").commit();
    }

    @Override
    public void initAdapter(List<EStoreList> list) {
        aExchangeList.setList(list);
    }
}
