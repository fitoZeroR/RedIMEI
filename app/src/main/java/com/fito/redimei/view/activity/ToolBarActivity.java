package com.fito.redimei.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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

public abstract class ToolBarActivity extends AppCompatActivity implements ImeiPresenter.View, HasComponent<ClienteComponent> {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    ImeiPresenter imeiPresenter;

    private ClienteComponent clienteComponent;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);

        initializeDagger();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeAsUpIndicator(R.drawable.selector_home);
        }

        toolbar.setTitleTextColor(getResources().getColor(R.color.blanco));

        imeiPresenter.setView(this);
    }

    @Override
    public void onDestroy() {
        imeiPresenter.terminate();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.enviar_informacion_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_editar_item:
                startActivity(new Intent(this, EnviarInformacionActivity.class));
                return true;
        }
        return true;
    }

    @Override
    public void showLoading() {
        progressDialog = ProgressDialog.show(ToolBarActivity.this, "", getString(R.string.prb_cargando), true, false);
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
    public ClienteComponent getComponent() {
        return clienteComponent;
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

    private void initializeDagger() {
        clienteComponent = DaggerClienteComponent.builder()
                .activityModule(new ActivityModule(this))
                .clienteModule(new ClienteModule())
                .imeiMainComponent(((ImeiAplication) getApplication()).getImeiMainComponent())
                .build();

        getComponent().inject(this);
    }

    protected abstract int getLayoutResource();

    protected void setActionBarIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }

    public void cambiarTituloActionBar(String titulo) {
        toolbar.setTitle(titulo);
    }
}