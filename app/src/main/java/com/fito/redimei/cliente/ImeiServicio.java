package com.fito.redimei.cliente;

import com.fito.redimei.modelo.*;

import io.reactivex.Observable;

/**
 * Created by luisr on 25/10/2017.
 */

public interface ImeiServicio {
    Observable<Opciones> consultaOpciones();
    Observable<InformacionPlanteles> consultaPlanteles();
    Observable<EnviarInformacion> envioInformacion(String nombre, String telefono, String correo, String comentarios, String interes);
    Observable<Login> loginUsuario(String matricula, String password);
    Observable<Foto> enviaFoto(String tokenSesion, String foto);
    Observable<PagosAsignaturas> consultaAsignaturasPagos(String tokenSesion);
    Observable<DescargaBoleta> descargaBoleta(String tokenSesion);
    Observable<RecuperarPassword> cambiaPassword(String matricula);
}