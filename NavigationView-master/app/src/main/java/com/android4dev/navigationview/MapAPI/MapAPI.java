package com.android4dev.navigationview.MapAPI;

import com.android4dev.navigationview.MapConstants.Constants;

import retrofit.RestAdapter;

/**
 * Created by Karma on 07/01/16.
 */
public class MapAPI {
    private static MapsInterface mapsInterface;

    static {
        setUpRestAPI();
    }

    private MapAPI() {
    }

    public static MapsInterface getMapAPI() {
        return mapsInterface;
    }

    private static void setUpRestAPI() {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL).build();
        mapsInterface = restAdapter.create(MapsInterface.class);
    }
}
