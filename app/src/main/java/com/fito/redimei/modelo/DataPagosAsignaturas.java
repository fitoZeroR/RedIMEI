package com.fito.redimei.modelo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.fito.redimei.utils.Constantes.SerializedPagosAsignaturas.*;

/**
 * Created by luisr on 01/03/2018.
 */

public class DataPagosAsignaturas {
    @SerializedName(PAGOS)
    private List<Pagos> pagos;
    @SerializedName(PLAN)
    private List<Plan> plan;

    public DataPagosAsignaturas(List<Pagos> pagos, List<Plan> plan) {
        this.pagos = pagos;
        this.plan = plan;
    }

    public List<Pagos> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pagos> pagos) {
        this.pagos = pagos;
    }

    public List<Plan> getPlan() {
        return plan;
    }

    public void setPlan(List<Plan> plan) {
        this.plan = plan;
    }
}