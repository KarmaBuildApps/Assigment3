package uk.ac.tae.myapp.asosonline.model.MainDisplayPage;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import uk.ac.tae.myapp.asosonline.R;

/**
 * Created by Karma on 20/12/15.
 */
public class ItemDetailPage extends Fragment {
    private ViewPager imageSlider;
    private CirclePageIndicator cpi;
    private TextView tvProductDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_fragment, container, false);
        imageSlider = (ViewPager) v.findViewById(R.id.vpImageSlider);
        cpi = (CirclePageIndicator) v.findViewById(R.id.cpiImageSlider);
        tvProductDetails = (TextView) v.findViewById(R.id.tvProductDetail);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
