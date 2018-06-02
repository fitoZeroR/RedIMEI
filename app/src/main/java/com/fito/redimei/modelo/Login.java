package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import static com.fito.redimei.utils.Constantes.SerializedEnviarInformacion.*;

/**
 * Created by luisr on 07/02/2018.
 */

public class Login {
    @SerializedName(CODE)
    private int code;
    @SerializedName(DATA)
    private Data data;
    @SerializedName(MESSAGE)
    private String message;
    @SerializedName(TRACE)
    private String trace;

    public Login(){}

    public Login(int code, Data data, String message, String trace) {
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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