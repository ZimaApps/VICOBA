package com.zimaapps.vicoba;


import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by abdalla on 2/18/18.
 */

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    public static int positionz;

    PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Log.e("FRAGMENT MANAGER", "CHAT FRAGMENT RETURNED.");
                positionz = 0;
                return new ChatFragment();
            case 1:
                Log.e("FRAGMENT MANAGER", "STATUS FRAGMENT RETURNED.");
                positionz = 1;
                return new StatusFragment();
            case 2:
                Log.e("FRAGMENT MANAGER", "CALL FRAGMENT RETURNED.");
                positionz = 2;
                return new CallFragment();
            default:
                //return null;

                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
