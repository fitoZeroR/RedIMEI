package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.fito.redimei.utils.Constantes.SerializedOpciones.*;

/**
 * Created by luisr on 01/11/2017.
 */

public class Opciones {
    @SerializedName(SOMOS)
    private List<Grado> somos;
    @SerializedName(KINDER)
    private List<Grado> kinder;
    @SerializedName(PRIMARIA)
    private List<Grado> primaria;
    @SerializedName(BACHILLERATO)
    private List<Grado> bachillerato;
    @SerializedName(lICENCIATURAS)
    private List<Grado> licenciaturas;
    @SerializedName(MAESTRIAS)
    private List<Grado> maestrias;
    @SerializedName(DOCTORADOS)
    private List<Grado> doctorados;
    @SerializedName(DIPLOMADOS)
    private List<Grado> diplomados;

    public Opciones() {}

    public Opciones(List<Grado> somos, List<Grado> kinder, List<Grado> primaria, List<Grado> bachillerato, List<Grado> licenciaturas, List<Grado> maestrias, List<Grado> doctorados, List<Grado> diplomados) {
        this.somos = somos;
        this.kinder = kinder;
        this.primaria = primaria;
        this.bachillerato = bachillerato;
        this.licenciaturas = licenciaturas;
        this.maestrias = maestrias;
        this.doctorados = doctorados;
        this.diplomados = diplomados;
    }

    public List<Grado> getSomos() {
        return somos;
    }

    public void setSomos(List<Grado> somos) {
        this.somos = somos;
    }

    public List<Grado> getKinder() {
        return kinder;
    }

    public void setKinder(List<Grado> kinder) {
        this.kinder = kinder;
    }

    public List<Grado> getPrimaria() {
        return primaria;
    }

    public void setPrimaria(List<Grado> primaria) {
        this.primaria = primaria;
    }

    public List<Grado> getBachillerato() {
        return bachillerato;
    }

    public void setBachillerato(List<Grado> bachillerato) {
        this.bachillerato = bachillerato;
    }

    public List<Grado> getLicenciaturas() {
        return licenciaturas;
    }

    public void setLicenciaturas(List<Grado> licenciaturas) {
        this.licenciaturas = licenciaturas;
    }

    public List<Grado> getMaestrias() {
        return maestrias;
    }

    public void setMaestrias(List<Grado> maestrias) {
        this.maestrias = maestrias;
    }

    public List<Grado> getDoctorados() {
        return doctorados;
    }

    public void setDoctorados(List<Grado> doctorados) {
        this.doctorados = doctorados;
    }

    public List<Grado> getDiplomados() {
        return diplomados;
    }

    public void setDiplomados(List<Grado> diplomados) {
        this.diplomados = diplomados;
    }
}