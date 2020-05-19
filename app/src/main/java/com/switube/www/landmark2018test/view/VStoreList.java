package com.switube.www.landmark2018test.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.AStoreList;
import com.switube.www.landmark2018test.adapter.callback.IAStoreList;
import com.switube.www.landmark2018test.entity.EStoreList;
import com.switube.www.landmark2018test.presenter.PStoreList;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.view.callback.IVStoreList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VStoreList extends Fragment implements IVStoreList, IAStoreList {
    private Unbinder unbinder;
    private AStoreList aStoreList;
    private PStoreList pStoreList;
    @BindView(R.id.recyclerInStoreList)
    RecyclerView recyclerView;
    @BindView(R.id.textCashInStoreList)
    TextView textView;
    public VStoreList() {
        pStoreList = new PStoreList(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_store_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        aStoreList = new AStoreList(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new ItemDecorationUtil(getContext(), 0, 8, 8, 8));
        recyclerView.setAdapter(aStoreList);
        textView.setText(MyApplication.getAppData().getCarbonCash());
        pStoreList.init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void initAdapter(List<EStoreList> list) {
        aStoreList.setList(list);
    }

    @Override
    public void onItemClick(String storeId) {
        MyApplication.getAppData().setSelectedStoreId(storeId);
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VExchangeList()).addToBackStack("StoreList").commit();
    }
}
