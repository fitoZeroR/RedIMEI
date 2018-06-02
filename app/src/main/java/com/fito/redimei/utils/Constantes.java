package com.fito.redimei.utils;

/**
 * Created by luisr on 17/10/2017.
 */

public class Constantes {
    //public static final String URL_WEB_INSTITUCION = "http://www.grupoeducativoimei.edu.mx/nuestra-institucion/";
    //public static final String URL_WEB_AVISO_PRIVACIDAD = "http://www.grupoeducativoimei.edu.mx/aviso-de-privacidad/";
    public static final String URL = "http://grupoeducativoimei.com/";

    public static final class Endpoint {
        public static final String URL_OPCIONES = "json/types.json";
        public static final String URL_PLANTELES = "json/planteles.json";
        public static final String URL_ENVIAR_INFORMACION = "api/index.php/welcome/form";
        public static final String URL_LOGIN = "api/index.php/app/login";
        public static final String URL_ENVIAR_FOTOGRAFIA = "api/index.php/app/photo";
        public static final String URL_ASIGNATURAS_PAGOS = "api/index.php/app/asignaturaspagos";
        public static final String URL_DESCARGA_BOLETA = "api/index.php/app/boleta";
        public static final String URL_RECUPERAR_PASSWORD = "api/index.php/app/recuperarPassword";
    }

    public static final class SerializedOpciones {
        public static final String SOMOS = "somos";
        public static final String KINDER = "Kinder";
        public static final String PRIMARIA = "Primaria";
        public static final String BACHILLERATO = "Bachillerato";
        public static final String lICENCIATURAS = "Licenciaturas";
        public static final String MAESTRIAS = "Maestrias";
        public static final String DOCTORADOS = "Doctorados";
        public static final String DIPLOMADOS = "Diplomados";
    }

    public static final class SerializedInstitucion {
        public static final String TITULO = "titulo";
        public static final String DESCRIPCION = "descripcion";
        public static final String DESCRIPCION_AVISO = "descripcionAviso";
        public static final String PLANTELES = "planteles";
    }

    public static final class SerializedPlanteles {
        public static final String NOMBRE = "nombre";
        public static final String LATITUD = "latitud";
        public static final String LONGITUD = "longitud";
        public static final String MAPA_PLANTELES = "Planteles";
    }

    public static final class SerializedEnviarInformacion {
        public static final String CODE = "Code";
        public static final String DATA = "Data";
        public static final String MESSAGE = "Message";
        public static final String TRACE = "Trace";
    }

    public static final class SerializedData {
        public static final String TOKEN_SESION = "TokenSesion";
        public static final String ALUMNO = "Alumno";
    }

    public static final class SerializedDescargaBoleta {
        public static final String BOLETA_URL = "BoletaURL";
    }

    public static final class SerializedAlumno {
        public static final String ID_ALUMNO = "IdAlumno";
        public static final String NOMBRE = "Nombre";
        public static final String PATERNO = "Paterno";
        public static final String MATERNO = "Materno";
        public static final String CUATRIMESTRE = "Cuatrimestre";
        public static final String ID_LICENCIATURA = "IdLicenciatura";
        public static final String ID_PLANTEL = "IdPlantel";
        public static final String CURP = "Curp";
        public static final String TELEFONO = "Telefono";
        public static final String MATRICULA = "Matricula";
        public static final String LICENCIATURA = "Licenciatura";
        public static final String PLANTEL = "Plantel";
        public static final String FOTO = "Foto";
        public static final String NACIMIENTO = "Nacimiento";
    }

    public static final class SerializedPagosAsignaturas {
        public static final String PAGOS = "Pagos";
        public static final String PLAN = "Plan";
        public static final String PAGO = "Pago";
        public static final String ESTATUS = "Estatus";
        public static final String ID_CUATRIMESTRE = "IdCuatrimestre";
        public static final String MATERIAS = "Materias";
        public static final String ID_MATERIA = "IdMateria";
        public static final String MATERIA = "Materia";
    }

    // Bundle
    public final static String BUNDLE_DESCRIPCION = "descripcion";
    public final static String BUNDLE_OPCION_SELECCIONADA = "opcionSeleccionada";
    public final static String BUNDLE_NOMBRE_OPCION = "nombreOpcion";
    public final static String BUNDLE_INFORMACION_USUARIO = "informacionUsuario";
    public final static String BUNDLE_INFORMACION_PAGOS_ASIGNATURAS = "informacionPagosAsignaturas";

    // codigo de peticion
    public static final int SELECT_PICTURE = 200;
    public static final int TAKE_PICTURE = 100;

    // Etiquetas
    public static final String NOMBRE_ARCHIVO_PDF = "Boleta(IMEI).pdf";
    public static final String MIME_PDF = "application/pdf";
    public static final String RUTA_ARCHIVO_PDF = "/Download/";
    public final static String FORMATO_CUMPLEANOS = "dd MMMM yyyy";
    //public final static String FORMATO_CUMPLEANOS = "d 'de' MMMM 'de' yyyy";

    // SharedPreferences
    public static final String MATRICULA = "matricula";
    public static final String PASSWORD = "password";
    public static final String JSON_LOGIN = "jsonLogin";
    public static final String JSON_ASIGNATURAS_PAGOS = "jsonAsignaturasPagos";
}