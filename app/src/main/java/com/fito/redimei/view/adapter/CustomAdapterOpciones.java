package com.fito.redimei.view.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fito.redimei.R;
import com.fito.redimei.enumeradores.EListado;
import com.fito.redimei.modelo.Grado;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by luisr on 17/10/2017.
 */

public class CustomAdapterOpciones extends RecyclerView.Adapter<CustomAdapterOpciones.OpcionesViewHolder> {
    private final List<String> listaOpciones;
    private final List<Grado> gradoList;
    private final EListado eListado;
    private final PublishSubject<View> mViewClickSubject;

    public CustomAdapterOpciones(List<String> listaOpciones, List<Grado> gradoList, EListado eListado) {
        this.listaOpciones = listaOpciones;
        this.gradoList = gradoList;
        this.eListado = eListado;
        mViewClickSubject = PublishSubject.create();
    }

    @NonNull
    @Override
    public OpcionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_opciones, parent, false);
        OpcionesViewHolder opcionesViewHolder = new OpcionesViewHolder(itemView);

        RxView.clicks(itemView)
                .takeUntil(RxView.detaches(parent))
                .map(aVoid -> itemView)
                .subscribe(mViewClickSubject);

        switch (eListado) {
            case SELECCIONA_OPCION:
                opcionesViewHolder.rllSeleccionaOpcion.setVisibility(View.VISIBLE);
                opcionesViewHolder.rllOpcion.setVisibility(View.GONE);
                opcionesViewHolder.rllSeleccionaOpcionTitulo.setVisibility(View.GONE);
                opcionesViewHolder.rllOpcionSinFlecha.setVisibility(View.GONE);
                break;
            case SELECCIONA_OPCION_SIN_PLANTEL:
                opcionesViewHolder.rllSeleccionaOpcion.setVisibility(View.GONE);
                opcionesViewHolder.rllOpcion.setVisibility(View.GONE);
                opcionesViewHolder.rllSeleccionaOpcionTitulo.setVisibility(View.VISIBLE);
                opcionesViewHolder.rllOpcionSinFlecha.setVisibility(View.GONE);
                break;
            case SELECCIONA_OPCION_DIPLOMADO:
                opcionesViewHolder.rllSeleccionaOpcion.setVisibility(View.GONE);
                opcionesViewHolder.rllOpcion.setVisibility(View.GONE);
                opcionesViewHolder.rllSeleccionaOpcionTitulo.setVisibility(View.GONE);
                opcionesViewHolder.rllOpcionSinFlecha.setVisibility(View.VISIBLE);
                break;
        }
        /*if (eListado == EListado.SELECCIONA_OPCION || eListado == EListado.SELECCIONA_OPCION_SIN_PLANTEL) {
            opcionesViewHolder.rllSeleccionaOpcion.setVisibility(View.VISIBLE);
            opcionesViewHolder.rllOpcion.setVisibility(View.GONE);
        }*/
        return opcionesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OpcionesViewHolder holder, int position) {
        switch (eListado) {
            case SELECCIONA_OPCION:
                holder.txvTitulo.setText(gradoList.get(position).getTitulo());
                holder.txvPlanteles.setText(gradoList.get(position).getPlanteles());
                break;
            case SELECCIONA_OPCION_SIN_PLANTEL:
                holder.txvTituloSinPlantel.setText(gradoList.get(position).getTitulo());
                break;
            case SELECCIONA_OPCION_DIPLOMADO:
                holder.textoOpcionesSinFlecha.setText(listaOpciones.get(position));
                break;
            default:
                holder.txtOpciones.setText(listaOpciones.get(position));
                break;
        }

        /*if (eListado == EListado.OPCION || eListado == EListado.SELECCIONA_OPCION_DIPLOMADO) {
            holder.txtOpciones.setText(listaOpciones.get(position));
            //if (eListado == EListado.SELECCIONA_OPCION_DIPLOMADO) {
                //holder.imgFlechaDerecha.setVisibility(View.GONE);
            //}
        } else {
            holder.txvTitulo.setText(gradoList.get(position).getTitulo());
            if (eListado == EListado.SELECCIONA_OPCION) {
                if (gradoList.get(position).getPlanteles() != null) {
                    holder.txvPlanteles.setText(gradoList.get(position).getPlanteles());
                }
                //if (gradoList.get(position).getPlanteles() == null) {
                    //holder.txvPlanteles.setVisibility(View.GONE);
                //} else {
                    //holder.txvPlanteles.setText(gradoList.get(position).getPlanteles());
                //}
            }
        }*/
    }

    @Override
    public int getItemCount() {
        if (eListado == EListado.OPCION || eListado == EListado.SELECCIONA_OPCION_DIPLOMADO) {
            return listaOpciones.size();
        } else {
            return gradoList.size();
        }
    }

    public Observable<View> getViewClickedObservable() {
        return mViewClickSubject.hide();
    }

    static class OpcionesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.texto_opciones_id)
        TextView txtOpciones;
        @BindView(R.id.txv_titulo_id)
        TextView txvTitulo;
        @BindView(R.id.texto_opciones_sin_flecha_id)
        TextView textoOpcionesSinFlecha;
        @BindView(R.id.txv_titulo_sin_plantel_id)
        TextView txvTituloSinPlantel;
        @BindView(R.id.txv_planteles_id)
        TextView txvPlanteles;
        @BindView(R.id.rll_opcion_id)
        Group rllOpcion;
        @BindView(R.id.rll_selecciona_opcion_id)
        Group rllSeleccionaOpcion;
        @BindView(R.id.rll_selecciona_opcion_titulo_id)
        Group rllSeleccionaOpcionTitulo;
        @BindView(R.id.rll_opcion_sin_flecha_id)
        Group rllOpcionSinFlecha;
        @BindView(R.id.img_flecha_derecha_id)
        ImageView imgFlechaDerecha;

        OpcionesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}