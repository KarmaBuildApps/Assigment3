package com.android4dev.navigationview.MapAdapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android4dev.navigationview.MapModel.Result;
import com.android4dev.navigationview.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Karma on 07/01/16.
 */
public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private View mInfoWindow, mInfoContent;
    private LayoutInflater inflater;

    public CustomInfoWindowAdapter(Activity context) {
        this.inflater = context.getLayoutInflater();

        mInfoContent = inflater.inflate(R.layout.info_window_content, null);
        mInfoWindow = inflater.inflate(R.layout.info_window, null);

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        renderMarkerInfoBox(marker, mInfoContent);
        return mInfoContent;
    }

    private void renderMarkerInfoBox(Marker marker, View view) {
        String title = marker.getTitle();
        String spinnet = marker.getSnippet();
        // Passing 0 to setImageResource will clear the image view.
//        ((ImageView) view.findViewById(R.id.iwIcon)).setImageResource(0);
        TextView tvtitle = (TextView) view.findViewById(R.id.iwtitle);
        TextView tvSpinnet = (TextView) view.findViewById(R.id.iwsnippet);
        if (title != null) {
            SpannableString spannableTitle = new SpannableString(title);
            spannableTitle.setSpan(new ForegroundColorSpan(Color.RED), 0, spannableTitle.length(), 0);
            tvtitle.setText(spannableTitle);
        } else {
            tvtitle.setText("");
        }

        if (spinnet != null) {
            SpannableString spannableSpinnet = new SpannableString(spinnet);
            spannableSpinnet.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, spannableSpinnet.length(), 0);
            tvSpinnet.setText(spannableSpinnet);
        } else {
            tvSpinnet.setText("");
        }

    }
}
