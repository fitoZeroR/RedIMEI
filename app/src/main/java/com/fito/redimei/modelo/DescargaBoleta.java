package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import static com.fito.redimei.utils.Constantes.SerializedEnviarInformacion.*;

/**
 * Created by luisr on 06/03/2018.
 */

public class DescargaBoleta {
    @SerializedName(CODE)
    private int code;
    @SerializedName(DATA)
    private DataDescargaBoleta data;
    @SerializedName(TRACE)
    private String trace;
    @SerializedName(MESSAGE)
    private String message;

    public DescargaBoleta(int code, DataDescargaBoleta data, String trace, String message) {
        this.code = code;
        this.data = data;
        this.trace = trace;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataDescargaBoleta getData() {
        return data;
    }

    public void setData(DataDescargaBoleta data) {
        this.data = data;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}