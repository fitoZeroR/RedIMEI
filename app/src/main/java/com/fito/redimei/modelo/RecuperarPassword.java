package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.fito.redimei.utils.Constantes.SerializedEnviarInformacion.*;

/**
 * Created by luisr on 15/03/2018.
 */

public class RecuperarPassword {
    @SerializedName(CODE)
    private int code;
    @SerializedName(DATA)
    private List<String> data;
    @SerializedName(TRACE)
    private String trace;
    @SerializedName(MESSAGE)
    private String message;

    public RecuperarPassword(){}

    public RecuperarPassword(int code, List<String> data, String trace, String message) {
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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
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