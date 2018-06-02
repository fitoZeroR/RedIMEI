package com.fito.redimei;

import android.app.Application;

import com.fito.redimei.di.components.DaggerImeiMainComponent;
import com.fito.redimei.di.components.ImeiMainComponent;
import com.fito.redimei.di.modules.ImeiMainModule;
import com.orhanobut.logger.*;

import javax.inject.Inject;

/**
 * Created by luisr on 22/11/2017.
 */

public class ImeiAplication extends Application {
    @Inject
    LogAdapter logAdapter;

    private ImeiMainComponent imeiMainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeInjector();

        Logger.addLogAdapter(logAdapter);
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
}