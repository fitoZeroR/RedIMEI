package com.fito.redimei.di.components;

import android.app.Activity;

import com.fito.redimei.di.PerActivity;
import com.fito.redimei.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by luisr on 05/01/2018.
 */

@PerActivity
@Component(dependencies = ImeiMainComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}