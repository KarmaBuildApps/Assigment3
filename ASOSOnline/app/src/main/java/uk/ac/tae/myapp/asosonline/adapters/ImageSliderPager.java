package uk.ac.tae.myapp.asosonline.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Karma on 20/12/15.
 */
public class ImageSliderPager extends FragmentStatePagerAdapter {
    private LayoutInflater inflater;
    private List<String> URLS;
    private boolean isNew = false;

    public ImageSliderPager(FragmentManager fm, List<String> URL) {
        super(fm);
        this.URLS = URL;
    }


    @Override
    public int getCount() {
        return URLS.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        return super.instantiateItem(container, position);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
