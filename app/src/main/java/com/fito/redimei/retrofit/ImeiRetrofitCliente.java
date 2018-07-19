package com.fito.redimei.retrofit;

import com.fito.redimei.modelo.*;
import com.fito.redimei.utils.Constantes;
import com.google.gson.*;
import com.orhanobut.logger.Logger;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.fito.redimei.utils.Constantes.*;

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
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

        /**
         *
         * Enable the Network Logs
         *
         */

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        /**
         * Set the SHA256 hash obtained from the Certificate
         *
         * Run this command to get the SHA256 fingerprint
         * openssl s_client -connect api.github.com:443 | openssl x509 -pubkey -noout | openssl rsa -pubin -outform der | openssl dgst -sha256 -binary | openssl enc -base64
         *
         *
         */
        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add(HOST, PUBLIC_KEY_HASH)
                .build();


        /**
         * Force the connection to use only TLS v.1.2 avoiding the fallback to older version to avoid vulnerabilities
         *
         */
        final ConnectionSpec.Builder connectionSpec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS);
        //connectionSpec.tlsVersions(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1)
        connectionSpec.tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                        /*CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)*/
                .build();

        /**
         * Enable TLS specific version V.1.2
         * Issue Details : https://github.com/square/okhttp/issues/1934
         */

        TLSSocketFactory tlsSocketFactory;

        try {
            tlsSocketFactory = new TLSSocketFactory();
            httpBuilder.sslSocketFactory(tlsSocketFactory, tlsSocketFactory.systemDefaultTrustManager());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            Logger.i(TAG, "Failed to create Socket connection ");
        }


        //OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
        OkHttpClient okHttpClient = httpBuilder
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
                .addNetworkInterceptor(interceptor)
                .connectionSpecs(Collections.singletonList(connectionSpec.build()))
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