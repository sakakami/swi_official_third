package com.switube.www.landmark2018test.view;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.presenter.PLogo;
import com.switube.www.landmark2018test.util.AlertDialogUtil;

import java.util.Objects;

public class VLogo extends Fragment {
    private PLogo pLogo;

    public VLogo() {
        pLogo = new PLogo();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (pLogo.checkNetworkState(container.getContext().getApplicationContext())) {
            handleFinishUpdate();
        } else {
            showNetworkMessage();
        }
        return inflater.inflate(R.layout.fragment_v_logo, container, false);
    }

    private void showNetworkMessage() {
        AlertDialogUtil
                .getInstance()
                .initDialogBuilder(
                        getContext(),
                        R.string.network_error_message,
                        R.string.network_error_ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Objects.requireNonNull(getActivity()).onBackPressed();
                            }
                        }
                );
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    public void handleFinishUpdate() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layoutContainer, new VMap(), "Map").commit();
    }
}
