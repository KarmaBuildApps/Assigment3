package uk.ac.tae.myapp.asosonline.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karma on 21/12/15.
 */
public class NavMenuFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private FragmentManager fm;
    private ArrayList<Fragment> fragments;

    public NavMenuFragmentStatePagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
