package com.fito.redimei.presenter;

import com.fito.redimei.modelo.DescargaBoleta;
import com.fito.redimei.modelo.EnviarInformacion;
import com.fito.redimei.modelo.InformacionPlanteles;
import com.fito.redimei.modelo.Login;
import com.fito.redimei.modelo.Opciones;
import com.fito.redimei.modelo.PagosAsignaturas;
import com.fito.redimei.modelo.RecuperarPassword;

public interface View extends Presenter.View {
        void showLoading();
        void hideLoading();
        void showError(String mensaje);
        void despliegaOpciones(Opciones opciones);
        void despliegaPlanteles(InformacionPlanteles informacionPlanteles);
        void respuestaEnvioInformacion(EnviarInformacion enviarInformacion);
        void respuestaLogin(Login login);
        void despliegaPagosAsignaturas(PagosAsignaturas pagosAsignaturas);
        void compartirBoleta(DescargaBoleta descargaBoleta);
        void muestraRespuestaRecuperarPassword(RecuperarPassword recuperarPassword);
}