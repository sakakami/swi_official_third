package com.switube.www.landmark2018test.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
                        (dialog, which) -> Objects.requireNonNull(getActivity()).onBackPressed()
                );
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    private void handleFinishUpdate() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layoutContainer, new VMap(), "Map").commit();
    }
}
