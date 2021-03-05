package com.eservices.breakingbad;

import android.app.Application;

import com.eservices.breakingbad.data.di.FakeDependencyInjection;
import com.facebook.stetho.Stetho;

/**
 * In this application, we show the list of Breaking bag's characters
 * with the possibility to see their information.
 *
 */
public class BreakingBadApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }
}
