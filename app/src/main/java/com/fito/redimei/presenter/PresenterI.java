package com.fito.redimei.presenter;

public interface PresenterI {
    void consultaListaOpciones();
    void consultaListaPlanteles();
    void enviaInformacion(String nombre, String telefono, String correo, String comentarios, String interes);
    void autentificaUsuario(String matricula, String password);
    void recuperaPassword(String matricula);
    void enviarImagen(String tokenSesion, String imagen);
    void consultaListaAsignaturasPagos(String tokenSesion);
    void descargaBoleta(String tokenSesion);
    void obtieneVista(View imeiView);
    void finalizar();
}