package com.final_test.moneylovely.view;

import android.view.KeyEvent;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.final_test.moneylovely.R;
import com.final_test.moneylovely.fragment.HomeFragment;

public class CallbackFragment {
    public static void callBackFragment(View view, final FragmentManager fragmentManager) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    HomeFragment fragment = new HomeFragment();
                    fragmentTransaction.replace(R.id.content_main, fragment, fragment.getTag()).commit();

                    return true;
                }
            }
            return false;
        });

    }
}
