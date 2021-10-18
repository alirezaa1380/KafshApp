package com.example.kafshapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.kafshapp.view.FrAcount;
import com.example.kafshapp.view.FrNottification;
import com.example.kafshapp.view.FrSetting;

public class AdapterViewPager extends FragmentStatePagerAdapter {
    public AdapterViewPager(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        if (position==0)
        {
            fragment=new FrNottification();
        }
        else if (position==1)
        {
            fragment=new FrAcount();
        }else {
            fragment=new FrSetting();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }



}
