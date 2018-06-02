package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import static com.fito.redimei.utils.Constantes.SerializedPagosAsignaturas.*;

/**
 * Created by luisr on 01/03/2018.
 */

public class Materia {
    @SerializedName(ID_MATERIA)
    private String idMateria;
    @SerializedName(MATERIA)
    private String materia;
    @SerializedName(ESTATUS)
    private String estatus;

    public Materia(String idMateria, String materia, String estatus) {
        this.idMateria = idMateria;
        this.materia = materia;
        this.estatus = estatus;
    }

    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}