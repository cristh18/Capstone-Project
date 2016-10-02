package com.udacitynanodegree.cristhian.capstoneproject.app;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.udacitynanodegree.cristhian.capstoneproject.app.config.Config;
import com.udacitynanodegree.cristhian.capstoneproject.utils.LogUtil;

public class IronHideApplication extends Application {

    private static IronHideApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        if (app == null)
            app = this;

        LogUtil.setDebug(Config.DEBUG);
    }

    public static IronHideApplication getApp() {
        return app;
    }
}
