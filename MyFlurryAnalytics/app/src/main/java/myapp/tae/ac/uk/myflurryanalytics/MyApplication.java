package myapp.tae.ac.uk.myflurryanalytics;

import android.app.Application;

/**
 * Created by Karma on 05/01/16.
 */
//If you are shipping an app, extend the Application class if you are not already doing so:

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // configure Flurry
        FlurryAgent.setLogEnabled(false);

        // init Flurry
        FlurryAgent.init(this, MY_FLURRY_APIKEY);
    }
}
