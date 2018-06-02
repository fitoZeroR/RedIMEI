package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import static com.fito.redimei.utils.Constantes.SerializedAlumno.*;

/**
 * Created by luisr on 07/02/2018.
 */

public class Alumno {
    @SerializedName(ID_ALUMNO)
    private String idAlumno;
    @SerializedName(NOMBRE)
    private String nombre;
    @SerializedName(PATERNO)
    private String paterno;
    @SerializedName(MATERNO)
    private String materno;
    @SerializedName(CUATRIMESTRE)
    private String cuatrimestre;
    @SerializedName(ID_LICENCIATURA)
    private String idLicenciatura;
    @SerializedName(ID_PLANTEL)
    private String idPlantel;
    @SerializedName(CURP)
    private String curp;
    @SerializedName(TELEFONO)
    private String telefono;
    @SerializedName(MATRICULA)
    private String matricula;
    @SerializedName(NACIMIENTO)
    private String nacimiento;
    @SerializedName(FOTO)
    private String foto;
    @SerializedName(LICENCIATURA)
    private String licenciatura;
    @SerializedName(PLANTEL)
    private String plantel;

    public Alumno(){}

    public Alumno(String idAlumno, String nombre, String paterno, String materno, String cuatrimestre, String idLicenciatura, String idPlantel, String curp, String telefono, String matricula, String nacimiento, String foto, String licenciatura, String plantel) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.cuatrimestre = cuatrimestre;
        this.idLicenciatura = idLicenciatura;
        this.idPlantel = idPlantel;
        this.curp = curp;
        this.telefono = telefono;
        this.matricula = matricula;
        this.nacimiento = nacimiento;
        this.foto = foto;
        this.licenciatura = licenciatura;
        this.plantel = plantel;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(String cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public String getIdLicenciatura() {
        return idLicenciatura;
    }

    public void setIdLicenciatura(String idLicenciatura) {
        this.idLicenciatura = idLicenciatura;
    }

    public String getIdPlantel() {
        return idPlantel;
    }

    public void setIdPlantel(String idPlantel) {
        this.idPlantel = idPlantel;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLicenciatura() {
        return licenciatura;
    }

    public void setLicenciatura(String licenciatura) {
        this.licenciatura = licenciatura;
    }

    public String getPlantel() {
        return plantel;
    }

    public void setPlantel(String plantel) {
        this.plantel = plantel;
    }
}