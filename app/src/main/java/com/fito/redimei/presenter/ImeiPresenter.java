package com.fito.redimei.presenter;

import android.util.Log;

import com.fito.redimei.interactor.ImeiInteractor;
import com.fito.redimei.modelo.*;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by luisr on 01/11/2017.
 */

public class ImeiPresenter extends Presenter<ImeiPresenter.View> {
    private ImeiInteractor imeiInteractor;

    @Inject
    public ImeiPresenter(ImeiInteractor imeiInteractor) {
        this.imeiInteractor = imeiInteractor;
    }

    @Override
    public void terminate() {
        super.terminate();
        setView(null);
    }

    public void consultaListaOpciones() {
        getView().showLoading();
        Disposable disposable = imeiInteractor.consultaListaOpciones().subscribe(opciones -> {
            if (opciones == null) {
                getView().showError(null);
            } else {
                Logger.json(new Gson().toJson(opciones));
                //Log.i("RLM", new Gson().toJson(opciones));
                getView().hideLoading();
                getView().despliegaOpciones(opciones);
            }
        }, throwable -> {
            getView().hideLoading();
            getView().showError(throwable.getMessage());
        });
        //}, Throwable::printStackTrace);

        addDisposableObserver(disposable);
    }

    public void consultaListaPlanteles() {
        getView().showLoading();
        Disposable disposable = imeiInteractor.consultaListaPlanteles().subscribe(informacionPlanteles -> {
            if (informacionPlanteles == null) {
                getView().showError(null);
            } else {
                Logger.json(new Gson().toJson(informacionPlanteles));
                //Log.i("RLM", new Gson().toJson(informacionPlanteles));
                getView().hideLoading();
                getView().despliegaPlanteles(informacionPlanteles);
            }
        }, throwable -> {
            getView().hideLoading();
            getView().showError(throwable.getMessage());
        });

        addDisposableObserver(disposable);
    }

    public void enviaInformacion(String nombre, String telefono, String correo, String comentarios, String interes) {
        getView().showLoading();
        Disposable disposable = imeiInteractor.enviarInformacion(nombre, telefono, correo, comentarios, interes).subscribe(enviarInformacion -> {
            if (enviarInformacion == null) {
                getView().showError(null);
            } else {
                Logger.json(new Gson().toJson(enviarInformacion));
                //Log.i("RLM", new Gson().toJson(enviarInformacion));
                getView().hideLoading();
                getView().respuestaEnvioInformacion(enviarInformacion);
            }
        }, throwable -> {
            getView().hideLoading();
            getView().showError(throwable.getMessage());
        });

        addDisposableObserver(disposable);
    }

    public void autentificaUsuario(String matricula, String password) {
        getView().showLoading();
        Disposable disposable = imeiInteractor.loginUsuario(matricula, password).subscribe(login -> {
            if (login == null) {
                getView().showError(null);
            } else {
                Logger.json(new Gson().toJson(login));
                //Log.i("RLM", new Gson().toJson(login));
                //getView().hideLoading();
                getView().respuestaLogin(login);
            }
        }, throwable -> {
            getView().hideLoading();
            getView().showError(throwable.getMessage());
        });

        addDisposableObserver(disposable);
    }

    public void recuperaPassword(String matricula) {
        getView().showLoading();
        Disposable disposable = imeiInteractor.cambiaPassword(matricula).subscribe(recuperarPassword -> {
            if (recuperarPassword == null) {
                getView().showError(null);
            } else {
                Logger.json(new Gson().toJson(recuperarPassword));
                //Log.i("RLM", new Gson().toJson(recuperarPassword));
                getView().hideLoading();
                getView().muestraRespuestaRecuperarPassword(recuperarPassword);
            }
        }, throwable -> {
            getView().hideLoading();
            getView().showError(throwable.getMessage());
        });

        addDisposableObserver(disposable);
    }

    /*public void enviarImagen(String tokenSesion, String imagen) {
        getView().showLoading();
        Disposable disposable = imeiInteractor.enviarFoto(tokenSesion, imagen).subscribe(foto -> {
            if (foto == null) {
                getView().showError();
            } else {
                //Logger.json(new Gson().toJson(foto));
                //Log.i("RLM", new Gson().toJson(foto));
                getView().hideLoading();
            }
        }, Throwable::printStackTrace);

        addDisposableObserver(disposable);
    }*/

    public void consultaListaAsignaturasPagos(String tokenSesion) {
        //getView().showLoading();
        Disposable disposable = imeiInteractor.consultaListaAsignaturasPagos(tokenSesion).subscribe(pagosAsignaturas -> {
            if (pagosAsignaturas == null) {
                getView().showError(null);
            } else {
                Logger.json(new Gson().toJson(pagosAsignaturas));
                //Log.i("RLM", new Gson().toJson(pagosAsignaturas));
                getView().hideLoading();
                getView().despliegaPagosAsignaturas(pagosAsignaturas);
            }
        }, throwable -> {
            getView().hideLoading();
            getView().showError(throwable.getMessage());
        });

        addDisposableObserver(disposable);
    }

    /*public void descargaBoleta(String tokenSesion) {
        getView().showLoading();
        Disposable disposable = imeiInteractor.descargaBoleta(tokenSesion).subscribe(descargaBoleta -> {
            if (descargaBoleta == null) {
                getView().showError();
            } else {
                Logger.json(new Gson().toJson(descargaBoleta));
                Log.i("RLM", new Gson().toJson(descargaBoleta));

                getView().hideLoading();
                getView().compartirBoleta(descargaBoleta);
            }
        }, Throwable::printStackTrace);

        addDisposableObserver(disposable);
    }*/

    public interface View extends Presenter.View {
        void showLoading();
        void hideLoading();
        void showError(String mensaje);
        void despliegaOpciones(Opciones opciones);
        void despliegaPlanteles(InformacionPlanteles informacionPlanteles);
        void respuestaEnvioInformacion(EnviarInformacion enviarInformacion);
        void respuestaLogin(Login login);
        void despliegaPagosAsignaturas(PagosAsignaturas pagosAsignaturas);
        void compartirBoleta(DescargaBoleta descargaBoleta);
        void muestraRespuestaRecuperarPassword(RecuperarPassword recuperarPassword);
    }
}