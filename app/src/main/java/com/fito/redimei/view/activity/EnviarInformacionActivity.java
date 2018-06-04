package com.fito.redimei.view.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.fito.redimei.R;
import com.fito.redimei.modelo.EnviarInformacion;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

import static com.fito.redimei.utils.Tools.*;

public class EnviarInformacionActivity extends ToolBarActivity {
    @BindView(R.id.edt_escribe_nombre_id)
    EditText edtEscribeNombre;
    @BindView(R.id.edt_escribe_telefono_id)
    EditText edtEscribeTelefono;
    @BindView(R.id.edt_escribe_correo_electronico_id)
    EditText edtEscribeCorreoElectronico;
    @BindView(R.id.edt_informacion_interes_id)
    EditText edtInformacionInteres;
    @BindView(R.id.btn_enviar_informacion_id)
    Button btnEnviarInformacion;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 1:
                case 3:
                    mensajeInformativo(EnviarInformacionActivity.this, msg.what == 1 ? getString(R.string.msg_no_campos_vacios) : getString(R.string.msg_no_conexion_internet), false);
                    break;
                case 2:
                    imeiPresenter.enviaInformacion(edtEscribeNombre.getText().toString(), edtEscribeTelefono.getText().toString(), edtEscribeCorreoElectronico.getText().toString(),
                            "vacio", edtInformacionInteres.getText().toString());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left);

        RxView.clicks(btnEnviarInformacion)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    hideKeyboard(this);
                    if (TextUtils.isEmpty(edtEscribeNombre.getText()) || TextUtils.isEmpty(edtEscribeTelefono.getText())
                            || TextUtils.isEmpty(edtEscribeCorreoElectronico.getText()) || TextUtils.isEmpty(edtInformacionInteres.getText())) {
                        mHandler.sendEmptyMessage(1);
                    } else {
                        if (isConnectionNetwork(this)) {
                            mHandler.sendEmptyMessage(2);
                        } else {
                            mHandler.sendEmptyMessage(3);
                        }
                    }
                });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_enviar_informacion;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            int iconMenuVisible = R.id.menu_editar_item;
            if (item.getItemId() == iconMenuVisible) {
                if (iconMenuVisible == R.id.menu_editar_item) {
                    item.setVisible(false);
                }
            }
        }
        return true;
    }

    @Override
    public Context context() {
        return EnviarInformacionActivity.this;
    }

    @Override
    public void respuestaEnvioInformacion(EnviarInformacion enviarInformacion) {
        if (enviarInformacion.getCode() == 0) {
            mensajeInformativo(this, getString(R.string.msg_datos_enviados_exitosamente), true);
        } else {
            mensajeInformativo(this, enviarInformacion.getMessage(), false);
        }
    }
}