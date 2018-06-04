package com.fito.redimei.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fito.redimei.R;
import com.fito.redimei.enumeradores.EListado;
import com.fito.redimei.view.adapter.CustomAdapterOpciones;

import java.util.List;

import butterknife.BindView;

import static com.fito.redimei.utils.Constantes.*;

public class ListaOpcionSeleccionadaDiplomadosActivity extends ToolBarActivity {
    @BindView(R.id.rcv_opciones_id)
    RecyclerView rcvOpciones;

    private List<String> listaOpcionDiplomados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left);

        Bundle bundleExtras = getIntent().getExtras();
        if (bundleExtras != null) {
            listaOpcionDiplomados = bundleExtras.getStringArrayList(BUNDLE_OPCION_SELECCIONADA);
            //Log.i("RLM", "Tamaño = " + listaOpcionDiplomados.size());
            setTitle(bundleExtras.getString(BUNDLE_NOMBRE_OPCION));
        }
        llamaAdaptador();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_opciones;
    }

    private void llamaAdaptador() {
        CustomAdapterOpciones customAdapterSeleccionadaDiplomados = new CustomAdapterOpciones(listaOpcionDiplomados, null, EListado.SELECCIONA_OPCION_DIPLOMADO);
        customAdapterSeleccionadaDiplomados.getViewClickedObservable()
                .subscribe(itemView -> {
                    //Log.i("RLM", "Pulsado el elemento " + rcvOpciones.getChildLayoutPosition(itemView));
                });
        rcvOpciones.setLayoutManager(new LinearLayoutManager(this));
        rcvOpciones.setHasFixedSize(true);
        rcvOpciones.swapAdapter(customAdapterSeleccionadaDiplomados, true);
    }

    @Override
    public Context context() {
        return ListaOpcionSeleccionadaDiplomadosActivity.this;
    }
}