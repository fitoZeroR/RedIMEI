package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import static com.fito.redimei.utils.Constantes.SerializedAlumno.FOTO;

/**
 * Created by luisr on 28/02/2018.
 */

public class DataFoto {
    @SerializedName(FOTO)
    private String foto;

    public DataFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}