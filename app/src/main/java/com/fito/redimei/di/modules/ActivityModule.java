package com.fito.redimei.di.modules;

import android.app.Activity;

import com.fito.redimei.di.PerActivity;

import dagger.*;


/**
 * Created by luisr on 05/01/2018.
 */

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return activity;
    }
}