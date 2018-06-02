package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import static com.fito.redimei.utils.Constantes.SerializedAlumno.NOMBRE;
import static com.fito.redimei.utils.Constantes.SerializedPagosAsignaturas.*;

/**
 * Created by luisr on 01/03/2018.
 */

public class EstatusPago {
    @SerializedName(PAGO)
    private String pago;
    @SerializedName(NOMBRE)
    private String nombre;
    @SerializedName(ESTATUS)
    private String estatus;

    public EstatusPago(String pago, String nombre, String estatus) {
        this.pago = pago;
        this.nombre = nombre;
        this.estatus = estatus;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}