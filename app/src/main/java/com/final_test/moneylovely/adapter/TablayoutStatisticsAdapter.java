package com.final_test.moneylovely.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.final_test.moneylovely.fragment.Sta_Day_Fragment;
import com.final_test.moneylovely.fragment.Sta_Month_Fragment;
import com.final_test.moneylovely.fragment.Sta_Year_Fragment;

public class TablayoutStatisticsAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;

    public TablayoutStatisticsAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                Sta_Day_Fragment sta_day_fragment = new Sta_Day_Fragment();
                return sta_day_fragment;
            case 1:
                Sta_Month_Fragment sta_month_fragment = new Sta_Month_Fragment();
                return sta_month_fragment;
            case 2:
                Sta_Year_Fragment sta_year_fragment = new Sta_Year_Fragment();
                return sta_year_fragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
