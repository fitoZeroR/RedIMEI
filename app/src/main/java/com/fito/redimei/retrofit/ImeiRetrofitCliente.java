package com.fito.redimei.retrofit;

import com.fito.redimei.modelo.*;
import com.fito.redimei.utils.Constantes;
import com.google.gson.*;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by luisr on 25/10/2017.
 */

public abstract class ImeiRetrofitCliente {
    private ImeiRetrofitServicio imeiRetrofitServicio;

    protected ImeiRetrofitCliente() {
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofit = retrofitBuilder();
        imeiRetrofitServicio = retrofit.create(getConsumoServiceClass());
    }

    private Retrofit retrofitBuilder() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(Constantes.URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(getConsumoDeserializer()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private Class<ImeiRetrofitServicio> getConsumoServiceClass() {
        return ImeiRetrofitServicio.class;
    }

    private Gson getConsumoDeserializer() {
        return new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(Login.class, (JsonDeserializer<Login>) (json, typeOfT, context) -> new Gson().fromJson(json, Login.class))
                .registerTypeAdapter(RecuperarPassword.class, (JsonDeserializer<RecuperarPassword>) (json, typeOfT, context) -> new Gson().fromJson(json, RecuperarPassword.class))
                .registerTypeAdapter(PagosAsignaturas.class, (JsonDeserializer<PagosAsignaturas>) (json, typeOfT, context) -> new Gson().fromJson(json, PagosAsignaturas.class))
                .registerTypeAdapter(Opciones.class, (JsonDeserializer<Opciones>) (json, typeOfT, context) -> new Gson().fromJson(json, Opciones.class))
                .registerTypeAdapter(InformacionPlanteles.class, (JsonDeserializer<InformacionPlanteles>) (json, typeOfT, context) -> new Gson().fromJson(json, InformacionPlanteles.class))
                .registerTypeAdapter(EnviarInformacion.class, (JsonDeserializer<EnviarInformacion>) (json, typeOfT, context) -> new Gson().fromJson(json, EnviarInformacion.class))
                .registerTypeAdapter(Foto.class, (JsonDeserializer<Foto>) (json, typeOfT, context) -> new Gson().fromJson(json, Foto.class))
                .registerTypeAdapter(DescargaBoleta.class, (JsonDeserializer<DescargaBoleta>) (json, typeOfT, context) -> new Gson().fromJson(json, DescargaBoleta.class))
                .create();
    }

    protected ImeiRetrofitServicio getConsumoService() {
        return imeiRetrofitServicio;
    }
}