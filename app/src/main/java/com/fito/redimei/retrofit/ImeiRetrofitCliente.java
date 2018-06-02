package com.fito.redimei.retrofit;

import com.fito.redimei.modelo.*;
import com.fito.redimei.utils.Constantes;
import com.google.gson.*;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by luisr on 25/10/2017.
 */

public abstract class ImeiRetrofitCliente {
    private ImeiRetrofitServicio imeiRetrofitServicio;

    public ImeiRetrofitCliente() {
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofit = retrofitBuilder();
        imeiRetrofitServicio = retrofit.create(getConsumoServiceClass());
    }

    private Retrofit retrofitBuilder() {
        return new Retrofit.Builder().baseUrl(Constantes.URL)
                .addConverterFactory(GsonConverterFactory.create(getConsumoDeserializer()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Class<ImeiRetrofitServicio> getConsumoServiceClass() {
        return ImeiRetrofitServicio.class;
    }

    public Gson getConsumoDeserializer() {
        return new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(Login.class, (JsonDeserializer<Login>) (json, typeOfT, context) -> {
                    return new Gson().fromJson(json, Login.class);
                })
                .registerTypeAdapter(RecuperarPassword.class, (JsonDeserializer<RecuperarPassword>) (json, typeOfT, context) -> {
                    return new Gson().fromJson(json, RecuperarPassword.class);
                })
                .registerTypeAdapter(PagosAsignaturas.class, (JsonDeserializer<PagosAsignaturas>) (json, typeOfT, context) -> {
                    return new Gson().fromJson(json, PagosAsignaturas.class);
                })
                .create();
    }

    protected ImeiRetrofitServicio getConsumoService() {
        return imeiRetrofitServicio;
    }
}