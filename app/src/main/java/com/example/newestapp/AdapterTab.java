package com.example.newestapp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AdapterTab extends FragmentPagerAdapter {

    int mTotalTabs;

    public AdapterTab(FragmentManager fragmentManager, int totalTabs) {
        super(fragmentManager);
        this.mTotalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d("asasas", position + "");

        switch (position){
            case 0:
                return new fragment_aa();
            case 1:
                return new fragment_ab();
            case 2:
                return new fragment_ac();
            case 3:
                return new fragment_ad();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTotalTabs;
    }

}
