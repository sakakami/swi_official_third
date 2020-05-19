package com.switube.www.landmark2018test.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.ATransaction;
import com.switube.www.landmark2018test.adapter.callback.IATransaction;
import com.switube.www.landmark2018test.entity.ETransaction;
import com.switube.www.landmark2018test.presenter.PTransaction;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.view.callback.IVTransaction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VTransaction extends Fragment implements IVTransaction, IATransaction {
    private Unbinder unbinder;
    private ATransaction aTransaction;
    private PTransaction pTransaction;
    @BindView(R.id.recyclerInTransaction)
    RecyclerView recyclerView;
    public VTransaction() {
        aTransaction = new ATransaction(this);
        pTransaction = new PTransaction(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_transaction, container, false);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.addItemDecoration(new ItemDecorationUtil(getContext(), 0, 10, 10, 10));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(aTransaction);
        pTransaction.init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void initAdapter(List<ETransaction> list) {
        aTransaction.setList(list);
    }

    @Override
    public void onItemClick(ETransaction eTransaction) {
        MyApplication.getAppData().seteTransaction(eTransaction);
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VTransactionDetails()).addToBackStack("transaction").commit();
    }
}
