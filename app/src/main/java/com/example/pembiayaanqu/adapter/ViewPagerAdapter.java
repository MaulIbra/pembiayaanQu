package com.example.pembiayaanqu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter  extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> FragmetListTitles = new ArrayList<>();


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return FragmetListTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return FragmetListTitles.get(position);
    }

    public void Addfragment (Fragment fragment, String Title){
        fragmentList.add(fragment);
        FragmetListTitles.add(Title);
    }
}
