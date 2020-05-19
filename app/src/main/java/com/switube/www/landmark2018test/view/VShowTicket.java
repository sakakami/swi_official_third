package com.switube.www.landmark2018test.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.presenter.PShowTicket;
import com.switube.www.landmark2018test.view.callback.IVShowTicket;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VShowTicket extends Fragment implements IVShowTicket {
    private Unbinder unbinder;
    private PShowTicket pShowTicket;
    @BindViews({R.id.textBackInShowTicket, R.id.textNumberInShowTicket})
    List<TextView> textViews;
    @BindViews({R.id.imagePhotoInShowTicket, R.id.imageCodeInShowTicket})
    List<ImageView> imageViews;
    public VShowTicket() {
        pShowTicket = new PShowTicket(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_show_ticket, container, false);
        unbinder = ButterKnife.bind(this, view);
        textViews.get(0).setOnClickListener(view1 -> getParentFragmentManager().popBackStack());
        pShowTicket.init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showCode(String code, String title) {
        BarcodeEncoder encoder = new BarcodeEncoder();
        try {
            Bitmap bitmap = encoder.encodeBitmap(code, BarcodeFormat.QR_CODE, 250, 250);
            Glide.with(getContext())
                    .load(bitmap)
                    .into(imageViews.get(1));
        } catch (WriterException e) {
            e.printStackTrace();
        }
        textViews.get(1).setText(title);
    }
}
