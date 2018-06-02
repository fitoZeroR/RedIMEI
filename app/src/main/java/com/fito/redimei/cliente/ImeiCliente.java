package com.fito.redimei.cliente;

import com.fito.redimei.modelo.*;
import com.fito.redimei.retrofit.ImeiRetrofitCliente;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by luisr on 25/10/2017.
 */

public class ImeiCliente extends ImeiRetrofitCliente implements ImeiServicio {
    @Override
    public Observable<Opciones> consultaOpciones() {
        return getConsumoService().obtieneOpciones()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<InformacionPlanteles> consultaPlanteles() {
        return getConsumoService().obtienePlanteles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<EnviarInformacion> envioInformacion(String nombre, String telefono, String correo, String comentarios, String interes) {
        return getConsumoService().enviarInformacion(nombre, telefono, correo, comentarios, interes)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Login> loginUsuario(String matricula, String password) {
        return getConsumoService().autenticarUsuario(matricula, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Foto> enviaFoto(String tokenSesion, String foto) {
        return getConsumoService().enviarFotografia(tokenSesion, foto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<PagosAsignaturas> consultaAsignaturasPagos(String tokenSesion) {
        return getConsumoService().obtieneAsignaturasPagos(tokenSesion)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<DescargaBoleta> descargaBoleta(String tokenSesion) {
        return getConsumoService().obtieneBoleta(tokenSesion)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<RecuperarPassword> cambiaPassword(String matricula) {
        return getConsumoService().recuperaPassword(matricula)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}