package com.final_test.moneylovely.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.final_test.moneylovely.fragment.ExpenditureFragment;
import com.final_test.moneylovely.fragment.RevenueFragment;
import com.final_test.moneylovely.fragment.StatisticsFragment;

public class TabLayoutAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;

    public TabLayoutAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                RevenueFragment revenueFragment = new RevenueFragment();
                return revenueFragment;
            case 1:
                ExpenditureFragment expenditureFragment = new ExpenditureFragment();
                return expenditureFragment;
            case 2:
                StatisticsFragment statisticsFragment = new StatisticsFragment();
                return statisticsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
