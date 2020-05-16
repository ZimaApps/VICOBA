package com.zimaapps.vicoba;

        import android.content.Context;

        import androidx.annotation.Nullable;
        import androidx.annotation.StringRes;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentManager;
        import androidx.fragment.app.FragmentPagerAdapter;

        import com.zimaapps.vicoba.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class homeSectionsAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.Baraza, R.string.kwangu, R.string.ripoti, R.string.katiba};
    private final Context mContext;

    public homeSectionsAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if(position + 1 == 1){
            fragment = new BarazaFragment() ;
        }else if(position + 1 == 2){
            fragment = new MimiFragment();
        }else{
            fragment = new BarazaFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }
}