package com.fito.redimei.view.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fito.redimei.R;
import com.fito.redimei.modelo.*;
import com.fito.redimei.picasso.PicassoCircleTransformation;
import com.fito.redimei.view.fragment.AsignaturasFragment;
import com.fito.redimei.view.fragment.DirectorioFragment;
import com.fito.redimei.view.fragment.EstadisticasFragment;
import com.fito.redimei.view.fragment.PagosFragment;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.squareup.picasso.Picasso;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindArray;
import butterknife.BindView;

import static com.fito.redimei.utils.Constantes.*;
import static com.fito.redimei.utils.Tools.*;

public class MainActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.txv_cerrar_sesion_id)
    TextView txvCerrarSesion;
    @BindView(R.id.txv_descarga_boleta_id)
    TextView txvDescargaBoleta;
    @BindView(R.id.imv_estudiante_id)
    AppCompatImageView imvEstudiante;
    @BindView(R.id.imv_camara_id)
    AppCompatImageView imvCamara;
    @BindView(R.id.txv_matricula_id)
    TextView txvMatricula;
    @BindView(R.id.txv_nombre_completo_id)
    TextView txvNombreCompleto;
    @BindView(R.id.txv_fecha_nacimiento_id)
    TextView txvFechaNacimiento;
    @BindView(R.id.txv_curp_id)
    TextView txvCurp;
    @BindView(R.id.txv_telefono_id)
    TextView txvTelefono;
    @BindView(R.id.txv_carrera_id)
    TextView txvCarrera;
    @BindView(R.id.txv_plantel_id)
    TextView txvPlantel;
    @BindView(R.id.txv_cuatrimestre_id)
    TextView txvCuatrimestre;
    @BindView(R.id.filtroEdt_id)
    EditText filtroEdt;

    @BindArray(R.array.lista_opcion_foto)
    String[] tituloObtenerFoto;

    private Uri uriImagen;
    private Login login;
    private PagosAsignaturas pagosAsignaturas;
    private Picasso picasso;

    private Fragment selectedFragment;
    private PagosFragment pagosFragment;
    private AsignaturasFragment asignaturasFragment;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0:
                    mensajeInformativo(MainActivity.this, getString(R.string.msg_no_conexion_internet), false);
                    break;
                case 1:
                case 2:
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    if(msg.what == 1) {
                        alertaOpcional(true, getString(R.string.msg_valida_deslogueo));
                    } else {
                        comparteArchivo();
                    }
                    break;
                case 3:
                    seleccionaImagen();
                    break;
            }
        }

        private void seleccionaImagen() {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getString(R.string.msg_seleccione_opcion));
            //builder.setCancelable(false);
            builder.setItems(tituloObtenerFoto, (dialog, item) -> {
                Intent iPicture = null;
                int code = TAKE_PICTURE;
                switch (item) {
                    case 0:
                        iPicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        break;
                    case 1:
                        iPicture = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        iPicture.setType("image/*");
                        code = SELECT_PICTURE;
                        break;
                }
                startActivityForResult(iPicture, code);
                dialog.dismiss();
            }).show();
        }

        private void comparteArchivo() {
            imeiPresenter.descargaBoleta(login.getData().getTokenSesion());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            login = new Gson().fromJson(bundle.getString(BUNDLE_INFORMACION_USUARIO), Login.class);
            pagosAsignaturas = new Gson().fromJson(bundle.getString(BUNDLE_INFORMACION_PAGOS_ASIGNATURAS), PagosAsignaturas.class);
        }

        confingToolBar();
        createMenuBottomNavigationView();
        inicializaActividad();
        inicializaMenuLateral();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            alertaOpcional(true, getString(R.string.msg_valida_deslogueo));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
            uriImagen = data.getData();
            alertaOpcional(false, getString(R.string.msg_actualiza_imagen));
        }
        if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK) {
            uriImagen = getImageUri(data.getParcelableExtra("data"));
            alertaOpcional(false, getString(R.string.msg_actualiza_imagen));
        }
    }

    @Override
    public void compartirBoleta(DescargaBoleta descargaBoleta) {
        // registrer receiver in order to verify when download is complete
        registerReceiver(new DonwloadCompleteReceiver(), new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(descargaBoleta.getData().getBoletaUrl()));
        request.setDescription("Descargando Archivo: " + NOMBRE_ARCHIVO_PDF);
        request.setTitle("Descargando");
        // in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, NOMBRE_ARCHIVO_PDF);

        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    private class DonwloadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                // DO SOMETHING WITH THIS FILE
                //Obtiene la Uri del recurso.
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + RUTA_ARCHIVO_PDF, NOMBRE_ARCHIVO_PDF));
                //Crea intent para enviar el email.
                Intent intentEnvioArchivo = new Intent(Intent.ACTION_SEND);
                intentEnvioArchivo.setType(MIME_PDF);
                //Agrega email o emails de destinatario.
                String[] accountNames = getAccountNames();
                intentEnvioArchivo.putExtra(Intent.EXTRA_EMAIL, new String[] { accountNames.length == 0 ? "" : accountNames[0] });
                intentEnvioArchivo.putExtra(Intent.EXTRA_SUBJECT, "Envio de archivo PDF.");
                intentEnvioArchivo.putExtra(Intent.EXTRA_TEXT, "Hola te envío un archivo PDF.");
                intentEnvioArchivo.putExtra(Intent.EXTRA_STREAM,  uri);
                startActivity(Intent.createChooser(intentEnvioArchivo, getString(R.string.msg_compartir_archivo)));
            }
        }
    }

    private void inicializaActividad() {
        setTitle(getString(R.string.title_fragment_pagos));
        addFragment(pagosFragment = PagosFragment.initInstance(pagosAsignaturas.getData().getPagos()), R.id.frame_container, true);
        asignaturasFragment = AsignaturasFragment.initInstance(pagosAsignaturas.getData().getPlan());
        asignaEventos();
    }

    private void asignaEventos() {
        RxTextView.textChanges(filtroEdt)
                .subscribe(charSequence -> {
                    if (charSequence.toString().length() > 0) {
                        if (selectedFragment instanceof PagosFragment || selectedFragment == null) {
                            pagosFragment.filtro(charSequence.toString());
                        } else if (selectedFragment instanceof AsignaturasFragment) {
                            asignaturasFragment.filtro(charSequence.toString());
                        }
                    }
                });

        RxView.clicks(txvCerrarSesion)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    mHandler.sendEmptyMessage(1);
                });

        RxView.clicks(txvDescargaBoleta)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    if (isConnectionNetwork(this)) {
                        mHandler.sendEmptyMessage(2);
                    } else {
                        mHandler.sendEmptyMessage(0);
                    }
                });

        RxView.clicks(imvCamara)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    mHandler.sendEmptyMessage(3);
                });
    }

    private void confingToolBar() {
        setSupportActionBar(mToolBar);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        animateToBackArrow();
        animatedToMenu();
    }

    private void createMenuBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener ((item) -> {
            switch (item.getItemId()) {
                case R.id.menu_pagos_item:
                    selectedFragment = PagosFragment.newInstance(pagosAsignaturas.getData().getPagos());
                    setTitle(getString(R.string.title_fragment_pagos));
                    filtroEdt.setVisibility(View.VISIBLE);
                    break;
                case R.id.menu_asignaturas_item:
                    selectedFragment = AsignaturasFragment.newInstance(pagosAsignaturas.getData().getPlan());
                    setTitle(getString(R.string.title_fragment_asignaturas));
                    filtroEdt.setVisibility(View.VISIBLE);
                    break;
                case R.id.menu_estadisticas_item:
                    selectedFragment = EstadisticasFragment.newInstance(pagosAsignaturas.getData().getPagos(), pagosAsignaturas.getData().getPlan());
                    setTitle(getString(R.string.title_fragment_estadisticas));
                    filtroEdt.setVisibility(View.GONE);
                    break;
                case R.id.menu_directorio_item:
                    selectedFragment = DirectorioFragment.newInstance();
                    setTitle(getString(R.string.title_fragment_directorio));
                    filtroEdt.setVisibility(View.GONE);
                    break;
            }
            addFragment(selectedFragment, R.id.frame_container, true);
            limpiaFiltro();
            return true;
        });
    }

    private void inicializaMenuLateral() {
        txvMatricula.setText(login.getData().getAlumno().getMatricula());
        txvNombreCompleto.setText(login.getData().getAlumno().getNombre() + " " + login.getData().getAlumno().getPaterno() + " " + login.getData().getAlumno().getMaterno());
        txvFechaNacimiento.setText(parsearFechaCumpleanos(login.getData().getAlumno().getNacimiento(), FORMATO_CUMPLEANOS));
        txvCurp.setText(login.getData().getAlumno().getCurp());
        txvTelefono.setText(login.getData().getAlumno().getTelefono());
        txvCarrera.setText(login.getData().getAlumno().getLicenciatura());
        txvPlantel.setText(login.getData().getAlumno().getPlantel());
        txvCuatrimestre.setText(login.getData().getAlumno().getCuatrimestre() + " Cuatrimestre");

        //picasso = Picasso.with(getApplicationContext());
        picasso = Picasso.get();
        obtieneImagenUsuario();
    }

    private void obtieneImagenUsuario() {
        if (uriImagen != null) {
            picasso.load(uriImagen)
                    .transform(new PicassoCircleTransformation())
                    .into(imvEstudiante);
        } else if (login.getData().getAlumno().getFoto() == null || login.getData().getAlumno().getFoto().equals("")) {
            picasso.load(R.drawable.silueta_usuario)
                    .transform(new PicassoCircleTransformation())
                    .into(imvEstudiante);
        } else {
            picasso.load(login.getData().getAlumno().getFoto())
                    .transform(new PicassoCircleTransformation())
                    .placeholder(R.drawable.silueta_usuario)
                    .error(R.drawable.silueta_usuario)
                    .into(imvEstudiante);
            //.into((ImageView) findViewById(R.id.imv_estudiante_id));
        }
    }

    private Uri getImageUri(Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void alertaOpcional(boolean bandera, String mensaje) {
        mensajeOpcional(this, mensaje)
                .setPositiveButton(R.string.action_accept, (dialog, which) -> {
                    if (bandera) {
                        SharedPreferences.Editor editorPreferencias = preferencias.edit();
                        editorPreferencias.putString(MATRICULA, "");
                        editorPreferencias.putString(PASSWORD, "");
                        editorPreferencias.putString(JSON_LOGIN, "");
                        editorPreferencias.putString(JSON_ASIGNATURAS_PAGOS, "");
                        editorPreferencias.commit();

                        startActivity(new Intent(this, LoginActivity.class));
                        finish();
                    } else {
                        if (isConnectionNetwork(this)) {
                            obtieneImagenUsuario();
                            imeiPresenter.enviarImagen(login.getData().getTokenSesion(), archivoBase64(getFilePathFromContentUri(uriImagen)));
                        } else {
                            mHandler.sendEmptyMessage(0);
                        }
                    }
                }).show();
    }

    private String[] getAccountNames() {
        AccountManager mAccountManager = AccountManager.get(this);
        Account[] accounts = mAccountManager.getAccountsByType("com.google");
        String[] names = new String[accounts.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = accounts[i].name;
        }
        return names;
    }

    private String archivoBase64(String archivo) {
        String base64 = "";
        if(archivo != null && !archivo.isEmpty() && !archivo.equals("null")){
            File file = new File(archivo);
            try {
                byte[] bytes = FileUtils.readFileToByteArray(file);
                base64 = Base64.encodeToString(bytes, Base64.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return base64;
    }

    private String getFilePathFromContentUri(Uri selectedImageUri) {
        String filePath = null;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};

        Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    private void limpiaFiltro() {
        filtroEdt.setText("");
    }
}