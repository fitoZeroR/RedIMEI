package com.fito.redimei.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fito.redimei.R;
import com.fito.redimei.enumeradores.EListado;
import com.fito.redimei.modelo.Opciones;
import com.fito.redimei.view.adapter.CustomAdapterOpciones;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

import static com.fito.redimei.utils.Constantes.*;
import static com.fito.redimei.utils.Tools.*;

public class OpcionesActivity extends ToolBarActivity {
    @BindView(R.id.rcv_opciones_id)
    RecyclerView rcvOpciones;

    @BindArray(R.array.titulo_opciones)
    String[] tituloOpciones;

    private List<String> listaOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left);

        imeiPresenter.consultaListaOpciones();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_opciones;
    }

    @Override
    public void despliegaOpciones(Opciones opciones) {
        listaOpciones = new ArrayList<>(Arrays.asList(tituloOpciones));

        CustomAdapterOpciones customAdapterOpciones = new CustomAdapterOpciones(listaOpciones, null, EListado.OPCION);
        customAdapterOpciones.getViewClickedObservable()
                .subscribe(itemView -> {
                    switch (rcvOpciones.getChildAdapterPosition(itemView)) {
                        case 1: // Kinder
                        case 2: // Primaria
                        case 3: // Bachillerato Tecnológico
                        case 4: // Licenciaturas
                        case 5: // Maestrias
                        case 6: // Doctorados
                        case 8: // Diplomados
                        case 9: // Cursos
                            Intent intentListaOpcion = new Intent(this, ListaOpcionSeleccionadaActivity.class);
                            Bundle bundleListaOpcion = new Bundle();
                            bundleListaOpcion.putString(BUNDLE_OPCION_SELECCIONADA, rcvOpciones.getChildAdapterPosition(itemView) == 1 ? new Gson().toJson(opciones.getKinder())
                                    : rcvOpciones.getChildAdapterPosition(itemView) == 2 ? new Gson().toJson(opciones.getPrimaria())
                                    : rcvOpciones.getChildAdapterPosition(itemView) == 3 ? new Gson().toJson(opciones.getBachillerato())
                                    : rcvOpciones.getChildAdapterPosition(itemView) == 4 ? new Gson().toJson(opciones.getLicenciaturas())
                                    : rcvOpciones.getChildAdapterPosition(itemView) == 5 ? new Gson().toJson(opciones.getMaestrias())
                                    : rcvOpciones.getChildAdapterPosition(itemView) == 6 ? new Gson().toJson(opciones.getDoctorados())
                                    : rcvOpciones.getChildAdapterPosition(itemView) == 8 ? new Gson().toJson(opciones.getDiplomados())
                                    : cursos);
                            bundleListaOpcion.putString(BUNDLE_NOMBRE_OPCION, listaOpciones.get(rcvOpciones.getChildAdapterPosition(itemView)));
                            intentListaOpcion.putExtras(bundleListaOpcion);
                            startActivity(intentListaOpcion);
                            break;
                        case 7: // Planteles
                            if (isConnectionNetwork(this)) {
                                Intent intentPlanteles = new Intent(this, PlantelesActivity.class);
                                Bundle bundlePlanteles = new Bundle();
                                bundlePlanteles.putString(BUNDLE_NOMBRE_OPCION, listaOpciones.get(rcvOpciones.getChildAdapterPosition(itemView)));
                                intentPlanteles.putExtras(bundlePlanteles);
                                startActivity(intentPlanteles);
                            } else {
                                mensajeInformativo(this, getString(R.string.msg_no_conexion_internet), false);
                            }
                            break;
                        case 0: // Que es Grupo Educativo IMEI
                        case 10: // Aviso de privacidad
                            Intent intentDescripcion = new Intent(this, DescripcionActivity.class);
                            Bundle bundleDescripcion = new Bundle();
                            bundleDescripcion.putString(BUNDLE_DESCRIPCION, rcvOpciones.getChildAdapterPosition(itemView) == 0 ? opciones.getSomos().get(0).getDescripcion()
                                    : opciones.getSomos().get(0).getDescripcionAviso());
                            bundleDescripcion.putString(BUNDLE_NOMBRE_OPCION, listaOpciones.get(rcvOpciones.getChildAdapterPosition(itemView)));
                            intentDescripcion.putExtras(bundleDescripcion);
                            startActivity(intentDescripcion);
                            //getChildLayoutPosition(v) == getChildAdapterPosition(V)
                            break;
                    }
                });
        rcvOpciones.setLayoutManager(new LinearLayoutManager(this));
        rcvOpciones.setHasFixedSize(true);
        rcvOpciones.swapAdapter(customAdapterOpciones, true);
    }

    @Override
    public Context context() {
        return OpcionesActivity.this;
    }//ViewSwitcher

    private String cursos = "[\n" +
            "  {\n" +
            "    \"titulo\": \"AMOR A SÍ MISMO Y AMOR A LOS DEMÁS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"AMOR INMADURO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"AMOR Y ODIO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"ANSIEDAD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"AUTOESTIMA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"BIEN Y MAL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"CARENCIAS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"CAUSA Y EFECTO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"CAPACITACION INTEGRAL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"CUANDO UN ACTO VIVIDO FORTALECE O DEBILITA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"COMO COMPARTIR EN GRUPO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"COMO SE VIVE EL AMOR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"COMO  VIVO MI SEXUALIDAD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"COMPETENCIA CON OTROS Y SER COMPETENTE CONMIGO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"CONCIENCIA DIVIDIDA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"DERROTA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"DEFENSA ANTE EL DOLOR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"DISCIPLINA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"EGOISMO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"EL AMOR ASI MISMO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"EMOCIONES EN CHOQUE\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"EL LÍMITE Y TOTALIDAD DE LO QUE SOY\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"EL MIEDO ESTANCA O SUPERA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"EL PECADO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"EL TRAUMA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"EN BUSCA DE LA CONCIENCIA (CONOCIMIENTO)\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"EN BUSCA DE LA TOTALIDAD DE UNO MISMO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"EN DONDE SE INVIERTE MI ENERGIA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"GUERRA CON EL DEPREDADOR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"GUERRAS INTERNAS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"HERRAMIENTAS PARA LOGRAR UN CARACTER DE PROSPERIDAD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"HUMILDAD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"INHABILIDAD PARA ENFRENTAR LA CRISIS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"INSEGURIDAD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"INTELIGENCIA SANA Y NEUROTICA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LA BÚSQUEDA DE SIGNIFICADO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LA BÚSQUEDA DEL BIEN UNA ACTITUD NEUROTICA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LA DESTRUCCION DE MI SER\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LA CRÍTICA COMO UNA HERRAMIENTA PARA DESPLOMAR EL OFENDERSE\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LAS DOS MENTES\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LA LUCHA ENTRE EL ENGAÑO Y EL DARSE CUENTA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LA MUJER HOY\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LA NECESIDAD DE SER UN HÉROE O DE TENER UNO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LA RAZÓN Y LO DESCONOCIDO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LA TERQUEDAD DE LA PERCEPCIÓN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LA RESPONSABILIDAD DE MI VIDA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LA RUPTURA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LENGUAJE Y ACTO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LIBERTAD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LA PREOCUPACION DESGASTA MI ENERGIA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LO ABSTRACTO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LOS AMARRES QUE SOSTIENEN LO QUE NO SOMOS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LÍMITE Y TOTALIDAD DE LO QUE SOY \"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"LOCURA O CORDURA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"MIS TRAUMAS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"MAS ALLÁ DE LA SINTAXTIS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"MEDITACION\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"MODALIDADES DE LA PERSONALIDAD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"MORAL FAMILIAR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"NECESIDADES MENTALES Y EMOCIONALES (SUFRIMIENTO)\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"NEGACIÓN DE RECUERDOS INFANTILES\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"NUESTRA HISTORIA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"RESISTENCIA AL CAMBIO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"OBSESIÓN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"OBSTÁCULOS CONCEPTUALES\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"OBSTÁCULOS EXISTENCIALES QUE IMPIDEN MI REALIZACION\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"ORGULLOS LASTIMADOS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"PENSAMIENTO CRÍTICO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"PERCEPCIÓN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"PERSONALIDAD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"PRIMERA ATENCIÓN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"POCA TOLERANCIA A LA FRUSTRACIÓN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"PODER Y MISERIA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"PORQUERIA CONCEPTUAL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"PREJUICIOS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"PREMIO Y CASTIGO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"PRINCIPALES CAUSAS DEL SUFRIMIENTO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"RELACIÓN PADRES E HIJOS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"RELACIÓN DE PAREJA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"TRINCHERA CONCEPTUAL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"SUMISION\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"TOMA DE DECISIONES\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"VIRUS MENTAL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"titulo\": \"VOLUNTAD\"\n" +
            "  }\n" +
            "]";
}