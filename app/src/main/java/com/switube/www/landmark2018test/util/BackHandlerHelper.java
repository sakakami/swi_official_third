package com.switube.www.landmark2018test.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.switube.www.landmark2018test.view.callback.IFragmentBackHandler;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BackHandlerHelper {
    private static boolean handleBackPress(@NotNull FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();

        if (fragments == null) return false;

        for (int i = fragments.size() - 1; i >= 0; i--) {
            Fragment child = fragments.get(i);
            if (isFragmentBackHandled(child)) {
                return true;
            }
        }
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            return true;
        }
        return false;
    }

    public static boolean handleBackPress(@NotNull FragmentActivity fragmentActivity) {
        return handleBackPress(fragmentActivity.getSupportFragmentManager());
    }

    /*
     * 判斷fragment是否處理了返回鍵
     * 如果處理了返回鍵則返回true
     */
    private static boolean isFragmentBackHandled(@NotNull Fragment fragment) {
        return fragment.isVisible()
                && fragment.getUserVisibleHint()
                && fragment instanceof IFragmentBackHandler
                && ((IFragmentBackHandler)fragment).onBackPressed();
    }
}
