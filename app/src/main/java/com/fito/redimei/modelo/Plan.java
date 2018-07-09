package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.fito.redimei.utils.Constantes.SerializedAlumno.NOMBRE;
import static com.fito.redimei.utils.Constantes.SerializedPagosAsignaturas.ID_CUATRIMESTRE;
import static com.fito.redimei.utils.Constantes.SerializedPagosAsignaturas.MATERIAS;

/**
 * Created by luisr on 01/03/2018.
 */

public class Plan {
    @SerializedName(ID_CUATRIMESTRE)
    private String idCuatrimestre;
    @SerializedName(NOMBRE)
    private String nombre;
    @SerializedName(MATERIAS)
    private List<Materia> materia;

    public Plan(String idCuatrimestre, String nombre, List<Materia> materia) {
        this.idCuatrimestre = idCuatrimestre;
        this.nombre = nombre;
        this.materia = materia;
    }

    public String getIdCuatrimestre() {
        return idCuatrimestre;
    }

    public void setIdCuatrimestre(String idCuatrimestre) {
        this.idCuatrimestre = idCuatrimestre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Materia> getMateria() {
        return materia;
    }

    public void setMateria(List<Materia> materia) {
        this.materia = materia;
    }
}