package com.fito.redimei.view.activity;

import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.DecelerateInterpolator;

import com.fito.redimei.ImeiAplication;
import com.fito.redimei.R;
import com.fito.redimei.di.HasComponent;
import com.fito.redimei.di.components.ClienteComponent;
import com.fito.redimei.di.components.DaggerClienteComponent;
import com.fito.redimei.di.modules.ActivityModule;
import com.fito.redimei.di.modules.ClienteModule;
import com.fito.redimei.modelo.*;
import com.fito.redimei.presenter.ImeiPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.fito.redimei.utils.Tools.mensajeInformativo;

/**
 * Created by luisr on 25/01/2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements ImeiPresenter.View, HasComponent<ClienteComponent> {
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Inject
    ImeiPresenter imeiPresenter;
    @Inject
    SharedPreferences preferencias;

    private ClienteComponent clienteComponent;
    private ProgressDialog progressDialog;

    public ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);

        initializeDagger();
        imeiPresenter.setView(this);
    }

    @Override
    public void onDestroy() {
        imeiPresenter.terminate();
        super.onDestroy();
    }

    @Override
    public ClienteComponent getComponent() {
        return clienteComponent;
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void showLoading() {
        progressDialog = ProgressDialog.show(BaseActivity.this, "", getString(R.string.prb_cargando), true, false);
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String mensaje) {
        String mensajeAlerta = mensaje == null ? getString(R.string.error) : mensaje;
        mensajeInformativo(this, mensajeAlerta, false);
    }

    @Override
    public void despliegaOpciones(Opciones opciones) {}

    @Override
    public void despliegaPlanteles(InformacionPlanteles informacionPlanteles) {}

    @Override
    public void respuestaEnvioInformacion(EnviarInformacion enviarInformacion) {}

    @Override
    public void respuestaLogin(Login login) {}

    @Override
    public void despliegaPagosAsignaturas(PagosAsignaturas pagosAsignaturas) {}

    @Override
    public void compartirBoleta(DescargaBoleta descargaBoleta) {}

    @Override
    public void muestraRespuestaRecuperarPassword(RecuperarPassword recuperarPassword) {}

    protected abstract int getLayoutResource();

    private void initializeDagger() {
        clienteComponent = DaggerClienteComponent.builder()
                .activityModule(new ActivityModule(this))
                .clienteModule(new ClienteModule())
                .imeiMainComponent(((ImeiAplication) getApplication()).getImeiMainComponent())
                .build();

        getComponent().inject(this);
    }

    public void animateToBackArrow() {
        ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);
        anim.addUpdateListener(valueAnimator -> {
            float slideOffset = (Float) valueAnimator.getAnimatedValue();
            if (mDrawerToggle != null)
                mDrawerToggle.onDrawerSlide(mDrawerLayout, slideOffset);
        });
        anim.setInterpolator(new DecelerateInterpolator());
        // You can change this duration to more closely match that of the default animation.
        anim.setDuration(500);
        anim.start();
    }

    public void animatedToMenu() {
        ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);
        anim.addUpdateListener(valueAnimator -> {
            if (mDrawerToggle != null)
                mDrawerToggle.onDrawerClosed(mDrawerLayout);
        });
        anim.setInterpolator(new DecelerateInterpolator());
        // You can change this duration to more closely match that of the default animation.
        anim.setDuration(500);
        anim.start();
    }

    public void addFragment(Fragment fragment, int containerViewId, boolean addBackStag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (addBackStag) {
            transaction.replace(containerViewId, fragment, fragment.getClass().getSimpleName());
        } else {
            transaction.replace(containerViewId, fragment);
        }

        transaction.commit();
    }
}