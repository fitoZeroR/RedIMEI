package com.fito.redimei.di.components;

import com.fito.redimei.ImeiAplication;
import com.fito.redimei.di.modules.ImeiMainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by luisr on 29/12/2017.
 */

@Singleton
@Component(modules = ImeiMainModule.class)
public interface ImeiMainComponent {
    void inject(ImeiAplication app);

    //Exposed to sub-graphs.
    ImeiAplication app();
}