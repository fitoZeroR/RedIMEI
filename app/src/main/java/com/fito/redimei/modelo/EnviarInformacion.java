package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import static com.fito.redimei.utils.Constantes.SerializedEnviarInformacion.*;

/**
 * Created by luisr on 08/12/2017.
 */

public class EnviarInformacion {
    @SerializedName(CODE)
    private int code;
    @SerializedName(DATA)
    private String data;
    @SerializedName(MESSAGE)
    private String message;
    @SerializedName(TRACE)
    private String trace;

    public EnviarInformacion(){}

    public EnviarInformacion(int code, String data, String message, String trace) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.trace = trace;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }
}