package com.fito.redimei.di.modules;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fito.redimei.cliente.*;
import com.fito.redimei.di.PerActivity;
import com.fito.redimei.interactor.ImeiInteractor;
import com.fito.redimei.presenter.ImeiPresenter;
import com.fito.redimei.presenter.PresenterI;

import dagger.*;

/**
 * Created by luisr on 08/01/2018.
 */

@Module
public class ClienteModule {
    public ClienteModule() {}

    @Provides
    @PerActivity
    PresenterI providePresenter() {
        return new ImeiPresenter(new ImeiInteractor(new ImeiCliente()));
    }

    @Provides
    @PerActivity
    SharedPreferences providesSharedPreferences(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity);
    }
}