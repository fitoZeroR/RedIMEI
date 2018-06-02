package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.fito.redimei.utils.Constantes.SerializedAlumno.*;
import static com.fito.redimei.utils.Constantes.SerializedPagosAsignaturas.*;

/**
 * Created by luisr on 01/03/2018.
 */

public class Pagos {
    @SerializedName(CUATRIMESTRE)
    private String cuatrimestre;
    @SerializedName(NOMBRE)
    private String nombre;
    @SerializedName(PAGOS)
    private List<EstatusPago> estatusPagos;

    public Pagos(String cuatrimestre, String nombre, List<EstatusPago> estatusPagos) {
        this.cuatrimestre = cuatrimestre;
        this.nombre = nombre;
        this.estatusPagos = estatusPagos;
    }

    public String getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(String cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<EstatusPago> getEstatusPagos() {
        return estatusPagos;
    }

    public void setEstatusPagos(List<EstatusPago> estatusPagos) {
        this.estatusPagos = estatusPagos;
    }
}