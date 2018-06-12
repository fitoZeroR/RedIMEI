package com.fito.redimei.view.adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fito.redimei.R;
import com.fito.redimei.modelo.Pagos;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.fito.redimei.utils.Tools.dpToPx;

/**
 * Created by luisr on 12/03/2018.
 */

public class CustomAdapterPagos extends RecyclerView.Adapter<CustomAdapterPagos.PagosViewHolder> implements Filterable {
    private List<Pagos> pagos;
    private List<Pagos> pagosFiltro;
    private CustomFilterPagos mFilter;
    private Activity activity;

    public CustomAdapterPagos(List<Pagos> pagos, Activity activity) {
        this.pagos = pagos;
        this.pagosFiltro = new ArrayList<>();
        this.pagosFiltro.addAll(pagos);
        this.mFilter = new CustomFilterPagos(CustomAdapterPagos.this);
        this.activity = activity;
    }

    @Override
    public CustomAdapterPagos.PagosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PagosViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_asignaturas_pagos, parent, false));
    }

    @Override
    public void onBindViewHolder(CustomAdapterPagos.PagosViewHolder holder, int position) {
        holder.txvTituloCuatrimestre.setText(pagosFiltro.get(position).getNombre());

        LinearLayout linearLayoutVertical = new LinearLayout(activity);
        for (int x = 0; x < pagosFiltro.get(position).getEstatusPagos().size(); x++) {
            ImageView imvEstatusPago = new ImageView(activity);
            imvEstatusPago.setId((position + 1) * 1000);
            RelativeLayout.LayoutParams layoutParamsImagen = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParamsImagen.rightMargin = dpToPx(5);
            layoutParamsImagen.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            layoutParamsImagen.addRule(RelativeLayout.CENTER_VERTICAL);
            imvEstatusPago.setLayoutParams(layoutParamsImagen);
            switch (pagosFiltro.get(position).getEstatusPagos().get(x).getEstatus()) {
                case "Pagado":
                    imvEstatusPago.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.alerta_exitoso));
                    break;
                case "Demorado":
                    imvEstatusPago.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.alerta_peligro));
                    break;
                case "No Pagado":
                    imvEstatusPago.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.alerta_error));
                    break;
            }

            TextView txvNombre = new TextView(activity);
            txvNombre.setId((position + 1) * 1001);
            RelativeLayout.LayoutParams layoutParamsMateria = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParamsMateria.topMargin = dpToPx(5);
            layoutParamsMateria.addRule(RelativeLayout.RIGHT_OF, (position + 1) * 1000);
            txvNombre.setLayoutParams(layoutParamsMateria);
            txvNombre.setText(pagosFiltro.get(position).getEstatusPagos().get(x).getNombre());
            txvNombre.setTypeface(txvNombre.getTypeface(), Typeface.BOLD);

            TextView txvEstatus = new TextView(activity);
            txvEstatus.setId((position + 1) * 1002);
            RelativeLayout.LayoutParams layoutParamsEstatus = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParamsEstatus.bottomMargin = dpToPx(5);
            layoutParamsEstatus.addRule(RelativeLayout.RIGHT_OF, (position + 1) * 1000);
            layoutParamsEstatus.addRule(RelativeLayout.BELOW, (position + 1) * 1001);
            txvEstatus.setLayoutParams(layoutParamsEstatus);
            txvEstatus.setText(pagosFiltro.get(position).getEstatusPagos().get(x).getEstatus());

            RelativeLayout relativeLayout = new RelativeLayout(activity);
            relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            relativeLayout.addView(imvEstatusPago);
            relativeLayout.addView(txvNombre);
            relativeLayout.addView(txvEstatus);

            if (x < pagosFiltro.get(position).getEstatusPagos().size() - 1) {
                View separador = new View(activity);
                RelativeLayout.LayoutParams layoutParamsView = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(1));
                layoutParamsView.addRule(RelativeLayout.RIGHT_OF, (position + 1) * 1000);
                layoutParamsView.addRule(RelativeLayout.BELOW, (position + 1) * 1002);
                separador.setLayoutParams(layoutParamsView);
                separador.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorAccent));
                relativeLayout.addView(separador);
            }

            linearLayoutVertical.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            linearLayoutVertical.setOrientation(LinearLayout.VERTICAL);
            linearLayoutVertical.addView(relativeLayout);
        }

        holder.llyContenido.removeAllViews();
        holder.llyContenido.addView(linearLayoutVertical);
    }

    @Override
    public int getItemCount() {
        return pagosFiltro.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    static class PagosViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txv_titulo_cuatrimestre_id)
        TextView txvTituloCuatrimestre;
        @BindView(R.id.lly_contenido_id)
        LinearLayout llyContenido;

        public PagosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /*Filtro*/
    public class CustomFilterPagos extends Filter {
        private CustomAdapterPagos customAdapterPagos;

        private CustomFilterPagos(CustomAdapterPagos customAdapterPagos) {
            super();
            this.customAdapterPagos = customAdapterPagos;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            pagosFiltro.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                pagosFiltro.addAll(pagos);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Pagos p : pagos) {
                    if (p.getNombre().toLowerCase().contains(filterPattern)) {
                        pagosFiltro.add(p);
                    }
                }
            }
            results.values = pagosFiltro;
            results.count = pagosFiltro.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.customAdapterPagos.notifyDataSetChanged();
        }
    }
}