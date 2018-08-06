package com.fito.redimei.di.modules;

import com.fito.redimei.ImeiAplication;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.orhanobut.logger.*;

import javax.inject.Singleton;

import dagger.*;

/**
 * Created by luisr on 29/12/2017.
 */

@Module
public class ImeiMainModule {
    private final ImeiAplication imeiAplication;

    public ImeiMainModule(ImeiAplication imeiAplication) {
        this.imeiAplication = imeiAplication;
    }

    @Provides
    @Singleton
    ImeiAplication provideImeiApplicationContext() {
        return this.imeiAplication;
    }

    @Provides
    @Singleton
    LogAdapter provideLogAdapterConfig() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .methodOffset(3)
                .tag("Services")
                .build();
        return new AndroidLogAdapter(formatStrategy);
    }

    @Provides
    @Singleton
    FirebaseAnalytics provideFirebaseAnalytics() {
        return FirebaseAnalytics.getInstance(provideImeiApplicationContext());
    }
}