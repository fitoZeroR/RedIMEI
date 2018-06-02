package com.fito.redimei.interactor;

import com.fito.redimei.cliente.ImeiServicio;
import com.fito.redimei.modelo.*;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by luisr on 01/11/2017.
 */

public class ImeiInteractor {
    private ImeiServicio imeiServicio;

    @Inject
    public ImeiInteractor(ImeiServicio imeiServicio) {
        this.imeiServicio = imeiServicio;
    }

    public Observable<Opciones> consultaListaOpciones() {
        return imeiServicio.consultaOpciones();
    }

    public Observable<InformacionPlanteles> consultaListaPlanteles() {
        return imeiServicio.consultaPlanteles();
    }

    public Observable<EnviarInformacion> enviarInformacion(String nombre, String telefono, String correo, String comentarios, String interes) {
        return imeiServicio.envioInformacion(nombre, telefono, correo, comentarios, interes);
    }

    public Observable<Login> loginUsuario(String matricula, String password) {
        return imeiServicio.loginUsuario(matricula, password);
    }

    public Observable<Foto> enviarFoto(String tokenSesion, String foto) {
        return imeiServicio.enviaFoto(tokenSesion, foto);
    }

    public Observable<PagosAsignaturas> consultaListaAsignaturasPagos(String tokenSesion) {
        return imeiServicio.consultaAsignaturasPagos(tokenSesion);
    }

    public Observable<DescargaBoleta> descargaBoleta(String tokenSesion) {
        return imeiServicio.descargaBoleta(tokenSesion);
    }

    public Observable<RecuperarPassword> cambiaPassword(String matricula) {
        return imeiServicio.cambiaPassword(matricula);
    }
}