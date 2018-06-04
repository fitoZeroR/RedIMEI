package com.fito.redimei.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fito.redimei.R;
import com.fito.redimei.enumeradores.EListado;
import com.fito.redimei.modelo.Grado;
import com.fito.redimei.view.adapter.CustomAdapterOpciones;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

import static com.fito.redimei.utils.Constantes.*;

public class ListaOpcionSeleccionadaActivity extends ToolBarActivity {
    @BindView(R.id.rcv_opciones_id)
    RecyclerView rcvOpciones;

    @BindArray(R.array.lista_diplomado_psicologia)
    String[] listaDiplomadoPsicologia;
    @BindArray(R.array.lista_diplomado_derecho_criminologia)
    String[] listaDiplomadoDerechoCriminologia;
    @BindArray(R.array.lista_diplomado_criminalistica)
    String[] listaDiplomadoCriminalistica;

    private List<Grado> gradoList;
    private String titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left);

        Bundle bundleExtras = getIntent().getExtras();
        if (bundleExtras != null) {
            String opcionSeleccionada = bundleExtras.getString(BUNDLE_OPCION_SELECCIONADA);
            Type typeOfObjectsList = new TypeToken<ArrayList<Grado>>() {}.getType();
            gradoList = new Gson().fromJson(opcionSeleccionada, typeOfObjectsList);

            titulo = bundleExtras.getString(BUNDLE_NOMBRE_OPCION);
            setTitle(titulo);
        }

        llamaAdaptdor();

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_opciones;
    }

    private void llamaAdaptdor() {
        EListado eListado = titulo.equals("Cursos") || titulo.equals("Kinder") || titulo.equals("Primaria") ? EListado.SELECCIONA_OPCION_SIN_PLANTEL : EListado.SELECCIONA_OPCION;
        CustomAdapterOpciones customAdapterOpciones = new CustomAdapterOpciones(null, gradoList, eListado);
        customAdapterOpciones.getViewClickedObservable()
                .subscribe(itemView -> {
                    //Log.i("RLM", "Pulsado el elemento " + rcvOpciones.getChildLayoutPosition(v));
                    if (eListado == EListado.SELECCIONA_OPCION) {
                        Bundle bundle = new Bundle();
                        if (titulo.equals("Diplomados")) {
                            Intent intentListaOpcionSeleccionadaDiplomados = new Intent(this, ListaOpcionSeleccionadaDiplomadosActivity.class);
                            bundle.putString(BUNDLE_NOMBRE_OPCION, titulo);
                            bundle.putStringArrayList(BUNDLE_OPCION_SELECCIONADA, rcvOpciones.getChildAdapterPosition(itemView) == 0 ? new ArrayList<>(Arrays.asList(listaDiplomadoPsicologia))
                                    : rcvOpciones.getChildAdapterPosition(itemView) == 1 ? new ArrayList<>(Arrays.asList(listaDiplomadoDerechoCriminologia))
                                    : new ArrayList<>(Arrays.asList(listaDiplomadoCriminalistica)));
                            intentListaOpcionSeleccionadaDiplomados.putExtras(bundle);
                            startActivity(intentListaOpcionSeleccionadaDiplomados);
                        } else {
                            Intent intentDescripcion = new Intent(this, DescripcionActivity.class);
                            bundle.putString(BUNDLE_DESCRIPCION, gradoList.get(rcvOpciones.getChildLayoutPosition(itemView)).getDescripcion());
                            bundle.putString(BUNDLE_NOMBRE_OPCION, titulo);
                            intentDescripcion.putExtras(bundle);
                            startActivity(intentDescripcion);
                        }
                    }
                });
        rcvOpciones.setLayoutManager(new LinearLayoutManager(this));
        rcvOpciones.setHasFixedSize(true);
        rcvOpciones.swapAdapter(customAdapterOpciones, true);
    }

    @Override
    public Context context() {
        return ListaOpcionSeleccionadaActivity.this;
    }
}