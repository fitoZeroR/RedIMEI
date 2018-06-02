package com.fito.redimei.view.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fito.redimei.ImeiAplication;
import com.fito.redimei.R;
import com.fito.redimei.di.HasComponent;
import com.fito.redimei.di.components.ClienteComponent;
import com.fito.redimei.di.components.DaggerClienteComponent;
import com.fito.redimei.di.modules.ActivityModule;
import com.fito.redimei.di.modules.ClienteModule;
import com.fito.redimei.modelo.DescargaBoleta;
import com.fito.redimei.modelo.EnviarInformacion;
import com.fito.redimei.modelo.InformacionPlanteles;
import com.fito.redimei.modelo.Login;
import com.fito.redimei.modelo.Opciones;
import com.fito.redimei.modelo.PagosAsignaturas;
import com.fito.redimei.modelo.RecuperarPassword;
import com.fito.redimei.presenter.ImeiPresenter;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.fito.redimei.utils.Constantes.*;
import static com.fito.redimei.utils.Tools.*;

public class LoginActivity extends Activity implements ImeiPresenter.View, HasComponent<ClienteComponent> {
    @BindView(R.id.btn_entrar_id)
    Button btnEntrar;
    @BindView(R.id.txv_presiona_aqui_no_alumno_id)
    TextView txvNoAlumno;
    @BindView(R.id.txv_olvidar_password_id)
    TextView txvOlvidarPassword;
    @BindView(R.id.edt_matricula_alumno_id)
    TextInputEditText edtMatriculaAlumno;
    @BindView(R.id.edt_password_id)
    TextInputEditText edtPassword;

    @Inject
    ImeiPresenter imeiPresenter;
    @Inject
    SharedPreferences preferencias;

    private ClienteComponent clienteComponent;
    private ProgressDialog progressDialog;
    private Login loginResponse;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0:
                    disparaDialogRecuperarPassword();
                    break;
                case 1:
                case 3:
                    mensajeInformativo(LoginActivity.this, msg.what == 1 ? getString(R.string.msg_no_campos_vacios) : getString(R.string.msg_no_conexion_internet), false);
                    break;
                case 2:
                    imeiPresenter.autentificaUsuario(edtMatriculaAlumno.getText().toString(), edtPassword.getText().toString());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDagger();
        imeiPresenter.setView(this);

        if(preferencias.getString(MATRICULA, "").equals("") & preferencias.getString(PASSWORD, "").equals("")) {
            setContentView(R.layout.activity_login);
            ButterKnife.bind(this);

            requestForSpecificPermission();
            asignaEventosComponentes();
        } else {
            inicializaActividadPrincipal(preferencias.getString(JSON_LOGIN, ""), preferencias.getString(JSON_ASIGNATURAS_PAGOS, ""));
        }
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
        return LoginActivity.this;
    }

    @Override
    public void showLoading() {
        progressDialog = ProgressDialog.show(LoginActivity.this, "", getString(R.string.prb_cargando), true, false);
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
    public void compartirBoleta(DescargaBoleta descargaBoleta) {}

    @Override
    public void muestraRespuestaRecuperarPassword(RecuperarPassword recuperarPassword) {
        if (recuperarPassword.getCode() == 0) {
            mensajeInformativo(this, recuperarPassword.getMessage(), false);
        } else {
            mensajeInformativo(this, recuperarPassword.getTrace(), false);
        }
    }

    @Override
    public void respuestaLogin(Login login) {
        if(login.getMessage().equals(getString(R.string.msg_operacion_exitosa))) {
            loginResponse = login;
            imeiPresenter.consultaListaAsignaturasPagos(login.getData().getTokenSesion());
        } else {
            hideLoading();
            mensajeInformativo(this, getString(R.string.msg_usuario_no_valido), false);
        }
    }

    @Override
    public void despliegaPagosAsignaturas(PagosAsignaturas pagosAsignaturas) {
        SharedPreferences.Editor editorPreferencias = preferencias.edit();
        editorPreferencias.putString(MATRICULA, edtMatriculaAlumno.getText().toString());
        editorPreferencias.putString(PASSWORD, edtPassword.getText().toString());
        editorPreferencias.putString(JSON_LOGIN, new Gson().toJson(loginResponse));
        editorPreferencias.putString(JSON_ASIGNATURAS_PAGOS, new Gson().toJson(pagosAsignaturas));
        editorPreferencias.commit();

        inicializaActividadPrincipal(new Gson().toJson(loginResponse), new Gson().toJson(pagosAsignaturas));
    }

    private void inicializaActividadPrincipal(String responseLogin, String responsePagosAsignaturas) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(BUNDLE_INFORMACION_USUARIO, responseLogin);
        intent.putExtra(BUNDLE_INFORMACION_PAGOS_ASIGNATURAS, responsePagosAsignaturas);
        startActivity(intent);
        finish();
    }

    private void initializeDagger() {
        // Forma de instanciar un modulo  dependiente de @PerActivity
        clienteComponent = DaggerClienteComponent.builder()
                .activityModule(new ActivityModule(this))
                .clienteModule(new ClienteModule())
                .imeiMainComponent(((ImeiAplication) getApplication()).getImeiMainComponent())
                .build();

        getComponent().inject(this);
    }

    private void disparaDialogRecuperarPassword(){
        EditText edtMatriculaRP = new EditText(this);
        TextInputLayout tilMatriculaRP = new TextInputLayout(this);
        tilMatriculaRP.setHint(getString(R.string.hint_dialog_mensaje));
        tilMatriculaRP.addView(edtMatriculaRP);

        final AlertDialog dialogRecuperaPassword = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.msg_dialog_titulo))
                .setMessage(getString(R.string.msg_dialog_mensaje) + " " + getString(R.string.hint_dialog_mensaje).toLowerCase())
                .setView(tilMatriculaRP)

                .setPositiveButton(getString(R.string.action_enviar), (dialog, id) -> {
                    dialog.dismiss();
                    hideKeyboard(LoginActivity.this);
                    if (TextUtils.isEmpty(edtMatriculaRP.getText())) {
                        mensajeInformativo(LoginActivity.this, getString(R.string.msg_matricula_vacio), false);
                    } else {
                        if (isConnectionNetwork(LoginActivity.this)) {
                            imeiPresenter.recuperaPassword(edtMatriculaRP.getText().toString());
                        } else {
                            mHandler.sendEmptyMessage(3);
                        }
                    }
                })

                .setNegativeButton(getString(R.string.action_cancel), (dialog, id) -> {
                    dialog.dismiss();
                })
                .setCancelable(false)
                .create();

        // set the focus change listener of the EditText
        // this part will make the soft keyboard automaticall visible
        edtMatriculaRP.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                dialogRecuperaPassword.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        });
        dialogRecuperaPassword.show();
    }

    private void asignaEventosComponentes() {
        RxView.clicks(btnEntrar)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    hideKeyboard(this);
                    if (TextUtils.isEmpty(edtMatriculaAlumno.getText()) || TextUtils.isEmpty(edtPassword.getText())) {
                        mHandler.sendEmptyMessage(1);
                    } else {
                        if (isConnectionNetwork(this)) {
                            mHandler.sendEmptyMessage(2);
                        } else {
                            mHandler.sendEmptyMessage(3);
                        }
                    }
                });

        RxView.clicks(txvNoAlumno)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    hideKeyboard(this);
                    if (isConnectionNetwork(this)) {
                        startActivity(new Intent(this, OpcionesActivity.class));
                    } else {
                        mHandler.sendEmptyMessage(3);
                    }
                });

        RxView.clicks(txvOlvidarPassword)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    hideKeyboard(this);
                    mHandler.sendEmptyMessage(0);
                });
    }

    private void requestForSpecificPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {
                        // The permission has been granted//
                    } else {
                        // The permission has been denied//
                    }
                });
    }
}