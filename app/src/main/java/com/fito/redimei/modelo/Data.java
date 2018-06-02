package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import static com.fito.redimei.utils.Constantes.SerializedData.*;

/**
 * Created by luisr on 07/02/2018.
 */

public class Data {
    @SerializedName(TOKEN_SESION)
    private String tokenSesion;
    @SerializedName(ALUMNO)
    private Alumno alumno;

    public Data(){}

    public Data(String tokenSesion, Alumno alumno) {
        this.tokenSesion = tokenSesion;
        this.alumno = alumno;
    }

    public String getTokenSesion() {
        return tokenSesion;
    }

    public void setTokenSesion(String tokenSesion) {
        this.tokenSesion = tokenSesion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}