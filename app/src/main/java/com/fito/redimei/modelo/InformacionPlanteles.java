package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.fito.redimei.utils.Constantes.SerializedPlanteles.MAPA_PLANTELES;

/**
 * Created by fito on 12/5/17.
 */

public class InformacionPlanteles {
    @SerializedName(MAPA_PLANTELES)
    private List<Planteles> planteles;

    public InformacionPlanteles(List<Planteles> planteles) {
        this.planteles = planteles;
    }

    public List<Planteles> getPlanteles() {
        return planteles;
    }

    public void setPlanteles(List<Planteles> planteles) {
        this.planteles = planteles;
    }
}