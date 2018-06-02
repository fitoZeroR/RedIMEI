package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import static com.fito.redimei.utils.Constantes.SerializedDescargaBoleta.BOLETA_URL;

/**
 * Created by luisr on 06/03/2018.
 */

public class DataDescargaBoleta {
    @SerializedName(BOLETA_URL)
    private String boletaUrl;

    public DataDescargaBoleta(String boletaUrl) {
        this.boletaUrl = boletaUrl;
    }

    public String getBoletaUrl() {
        return boletaUrl;
    }

    public void setBoletaUrl(String boletaUrl) {
        this.boletaUrl = boletaUrl;
    }
}