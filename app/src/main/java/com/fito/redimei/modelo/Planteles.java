package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import static com.fito.redimei.utils.Constantes.SerializedPlanteles.*;

/**
 * Created by fito on 12/5/17.
 */

public class Planteles {
    @SerializedName(NOMBRE)
    private String nombre;
    @SerializedName(LATITUD)
    private String latitud;
    @SerializedName(LONGITUD)
    private String longitud;

    public Planteles(String nombre, String latitud, String longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}