package com.plancancer.plancancernews.core_services.applications;

import android.app.Application;
import android.content.Context;

/**
 * Created by Assou on 21/08/2015.
 */
public class NewsApplication extends Application {
    private static NewsApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
    }



    public static NewsApplication getInstance(){
        return sInstance;
    }

    public static Context getNewsContext(){
        return sInstance.getApplicationContext();
    }
}
