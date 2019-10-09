package com.fito.redimei.interactor;

import com.fito.redimei.cliente.ImeiServicio;
import com.fito.redimei.modelo.*;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by luisr on 01/11/2017.
 */

public class ImeiInteractor implements ImeiServicio {
    private final ImeiServicio imeiServicio;

    @Inject
    public ImeiInteractor(ImeiServicio imeiServicio) {
        this.imeiServicio = imeiServicio;
    }

    @Override
    public Observable<Opciones> consultaOpciones() {
        return imeiServicio.consultaOpciones();
    }

    @Override
    public Observable<InformacionPlanteles> consultaPlanteles() {
        return imeiServicio.consultaPlanteles();
    }

    @Override
    public Observable<EnviarInformacion> envioInformacion(String nombre, String telefono, String correo, String comentarios, String interes) {
        return imeiServicio.envioInformacion(nombre, telefono, correo, comentarios, interes);
    }

    @Override
    public Observable<Login> loginUsuario(String matricula, String password) {
        return imeiServicio.loginUsuario(matricula, password);
    }

    @Override
    public Observable<Foto> enviaFoto(String tokenSesion, String foto) {
        return imeiServicio.enviaFoto(tokenSesion, foto);
    }

    @Override
    public Observable<PagosAsignaturas> consultaAsignaturasPagos(String tokenSesion) {
        return imeiServicio.consultaAsignaturasPagos(tokenSesion);
    }

    @Override
    public Observable<DescargaBoleta> descargaBoleta(String tokenSesion) {
        return imeiServicio.descargaBoleta(tokenSesion);
    }

    @Override
    public Observable<RecuperarPassword> cambiaPassword(String matricula) {
        return imeiServicio.cambiaPassword(matricula);
    }
}