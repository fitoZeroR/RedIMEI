package com.fito.redimei;

import android.app.Application;

import com.fito.redimei.di.components.DaggerImeiMainComponent;
import com.fito.redimei.di.components.ImeiMainComponent;
import com.fito.redimei.di.modules.ImeiMainModule;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.orhanobut.logger.*;

import javax.inject.Inject;

/**
 * Created by luisr on 22/11/2017.
 */

public class ImeiAplication extends Application {
    @Inject
    LogAdapter logAdapter;
    @Inject
    FirebaseAnalytics mFirebaseAnalytics;

    private ImeiMainComponent imeiMainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeInjector();

        Logger.addLogAdapter(logAdapter);

        /*try {
            ProviderInstaller.installIfNeeded(this);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }*/
    }

    private void initializeInjector() {
        imeiMainComponent = DaggerImeiMainComponent.builder()
                .imeiMainModule(new ImeiMainModule(this))
                .build();
        imeiMainComponent.inject(this);
    }

    public ImeiMainComponent getImeiMainComponent() {
        return imeiMainComponent;
    }

    /**
     * Gets the default {@link FirebaseAnalytics} for this {@link Application}.
     * @return tracker
     */
    synchronized public FirebaseAnalytics getDefaultAnalytics() {
        return mFirebaseAnalytics;
    }
}