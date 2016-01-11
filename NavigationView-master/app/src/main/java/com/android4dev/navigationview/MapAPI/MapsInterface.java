package com.android4dev.navigationview.MapAPI;

import android.telecom.Call;

import com.android4dev.navigationview.MapModel.Maps;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Karma on 06/01/16.
 */
public interface MapsInterface {
    @GET("/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&types=food&name=cruise&key=AIzaSyAUy6z0XQMk0wSTtaV15hNzubbd8uLUJgA")
    public void getCruises(Callback<Maps> response);
}
