package com.fito.redimei.di.components;

import com.fito.redimei.di.PerActivity;
import com.fito.redimei.di.modules.*;
import com.fito.redimei.view.activity.*;

import dagger.Component;

/**
 * Created by luisr on 08/01/2018.
 */

@PerActivity
@Component(dependencies = ImeiMainComponent.class, modules = {ActivityModule.class, ClienteModule.class})
public interface ClienteComponent {
    void inject(ToolBarActivity toolBarActivity);
    void inject(LoginActivity loginActivity);
    void inject(BaseActivity baseActivity);
}