package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import static com.fito.redimei.utils.Constantes.SerializedEnviarInformacion.*;

/**
 * Created by luisr on 01/03/2018.
 */

public class PagosAsignaturas {
    @SerializedName(CODE)
    private int code;
    @SerializedName(DATA)
    private DataPagosAsignaturas data;
    @SerializedName(TRACE)
    private String trace;
    @SerializedName(MESSAGE)
    private String message;

    public PagosAsignaturas(int code, DataPagosAsignaturas data, String trace, String message) {
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

    public DataPagosAsignaturas getData() {
        return data;
    }

    public void setData(DataPagosAsignaturas data) {
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