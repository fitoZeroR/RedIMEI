package com.fito.redimei.retrofit;

import com.fito.redimei.modelo.*;
import com.fito.redimei.utils.Constantes;

import io.reactivex.Observable;
import retrofit2.http.*;

/**
 * Created by luisr on 25/10/2017.
 */

public interface ImeiRetrofitServicio {
    @GET(Constantes.Endpoint.URL_OPCIONES)
    Observable<Opciones> obtieneOpciones();

    @GET(Constantes.Endpoint.URL_PLANTELES)
    Observable<InformacionPlanteles> obtienePlanteles();

    @POST(Constantes.Endpoint.URL_ENVIAR_INFORMACION)
    @FormUrlEncoded
    Observable<EnviarInformacion> enviarInformacion(@Field("Nombre") String nombre, @Field("Telefono") String telefono, @Field("Correo") String correo,
                                                    @Field("Comentarios") String comentarios, @Field("Interes") String interes);

    @POST(Constantes.Endpoint.URL_LOGIN)
    @FormUrlEncoded
    Observable<Login> autenticarUsuario(@Field("Matricula") String matricula, @Field("Contrasena") String password);

    @POST(Constantes.Endpoint.URL_ENVIAR_FOTOGRAFIA)
    @FormUrlEncoded
    Observable<Foto> enviarFotografia(@Field("TokenSesion") String tokenSesion, @Field("Foto") String foto);

    @POST(Constantes.Endpoint.URL_ASIGNATURAS_PAGOS)
    @FormUrlEncoded
    Observable<PagosAsignaturas> obtieneAsignaturasPagos(@Field("TokenSesion") String tokenSesion);

    @POST(Constantes.Endpoint.URL_DESCARGA_BOLETA)
    @FormUrlEncoded
    Observable<DescargaBoleta> obtieneBoleta(@Field("TokenSesion") String tokenSesion);

    @POST(Constantes.Endpoint.URL_RECUPERAR_PASSWORD)
    @FormUrlEncoded
    Observable<RecuperarPassword> recuperaPassword(@Field("Matricula") String matricula);
}