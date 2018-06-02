package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import static com.fito.redimei.utils.Constantes.SerializedEnviarInformacion.*;

/**
 * Created by luisr on 28/02/2018.
 */

public class Foto {
    @SerializedName(CODE)
    private int code;
    @SerializedName(DATA)
    private DataFoto data;
    @SerializedName(MESSAGE)
    private String message;

    public Foto(int code, DataFoto data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataFoto getData() {
        return data;
    }

    public void setData(DataFoto data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}