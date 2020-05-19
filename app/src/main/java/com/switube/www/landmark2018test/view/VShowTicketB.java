package com.switube.www.landmark2018test.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.presenter.PShowTicketB;
import com.switube.www.landmark2018test.view.callback.IVShowTicketB;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VShowTicketB extends Fragment implements IVShowTicketB {
    private Unbinder unbinder;
    private PShowTicketB pShowTicketB;
    @BindViews({R.id.textContentTitleInShowTicketB, R.id.textFromInShowTicketB, R.id.textUseDateInShowTicketB,
            R.id.textNumberInShowTicketB, R.id.textNameInShowTicketB, R.id.textBackInShowTicketB})
    List<TextView> textViews;
    public VShowTicketB() {
        pShowTicketB = new PShowTicketB(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_show_ticket_b, container, false);
        unbinder = ButterKnife.bind(this, view);
        pShowTicketB.init();
        textViews.get(5).setOnClickListener(view1 -> getParentFragmentManager().popBackStack());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showDetail(String msgA, String msgB, String msgC, String msgD, String msgE) {
        textViews.get(0).setText(msgA);
        textViews.get(1).setText(msgB);
        textViews.get(2).setText(msgC);
        textViews.get(3).setText(msgD);
        textViews.get(4).setText(msgE);
    }
}
