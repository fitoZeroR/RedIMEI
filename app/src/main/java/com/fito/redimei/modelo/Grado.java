package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import static com.fito.redimei.utils.Constantes.SerializedInstitucion.*;

/**
 * Created by luisr on 01/11/2017.
 */

public class Grado {
    @SerializedName(TITULO)
    private String titulo;
    @SerializedName(DESCRIPCION)
    private String descripcion;
    @SerializedName(DESCRIPCION_AVISO)
    private String descripcionAviso;
    @SerializedName(PLANTELES)
    private String planteles;

    public Grado() {}

    public Grado(String titulo, String descripcion, String descripcionAviso, String planteles) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.descripcionAviso = descripcionAviso;
        this.planteles = planteles;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionAviso() {
        return descripcionAviso;
    }

    public void setDescripcionAviso(String descripcionAviso) {
        this.descripcionAviso = descripcionAviso;
    }

    public String getPlanteles() {
        return planteles;
    }

    public void setPlanteles(String planteles) {
        this.planteles = planteles;
    }
}