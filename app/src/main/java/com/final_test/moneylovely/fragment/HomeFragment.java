package com.final_test.moneylovely.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.final_test.moneylovely.R;
import com.final_test.moneylovely.adapter.TabLayoutAdapter;
import com.google.android.material.tabs.TabLayout;


public class HomeFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        anhxa(view);
        tabLayout(view);
        callBack(view);
        return view;
    }

    private void anhxa(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        fragmentManager = getActivity().getSupportFragmentManager();
    }
    //fisnish activity
    private void callBack(View view){
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    AlertDialog.Builder aBuilder = new AlertDialog.Builder(getActivity());
                    aBuilder.setTitle("Cảnh báo");
                    aBuilder.setMessage("Bạn có muốn thoát!");
                    aBuilder.setPositiveButton("Có", (dialogInterface, i) -> getActivity().finish()).setNegativeButton("Không", (dialogInterface, i) -> {

                    });

                    aBuilder.show();
                    return true;
                }
            }
            return false;
        });
    }

    private void tabLayout(View view) {
        TabLayoutAdapter tabLayoutAdapter = new TabLayoutAdapter(getChildFragmentManager(), view.getContext(), tabLayout.getTabCount());
        viewPager.setAdapter(tabLayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_revenue).setText("Doanh thu");
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_expenditure);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_statistics);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {


            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tabLayout.getTabAt(0).setText("Doanh thu");
                        tabLayout.getTabAt(0).setIcon(R.drawable.ic_revenue_clicked);

                        break;
                    case 1:
                        tabLayout.getTabAt(1).setText("Khoản chi");
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_expenditure_clicked);
                        break;
                    case 2:
                        tabLayout.getTabAt(2).setText("Thống kê");
                        tabLayout.getTabAt(2).setIcon(R.drawable.ic_statistic_clicked);


                        break;


                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        tabLayout.getTabAt(0).setText("");
                        tabLayout.getTabAt(0).setIcon(R.drawable.ic_revenue);
                        break;
                    case 1:
                        tabLayout.getTabAt(1).setText("");
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_expenditure);
                        break;
                    case 2:
                        tabLayout.getTabAt(2).setText("");
                        tabLayout.getTabAt(2).setIcon(R.drawable.ic_statistics);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}

