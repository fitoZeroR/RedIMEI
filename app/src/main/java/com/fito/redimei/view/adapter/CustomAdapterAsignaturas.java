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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fito.redimei.R;
import com.fito.redimei.modelo.Plan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.fito.redimei.utils.Tools.dpToPx;

/**
 * Created by luisr on 07/03/2018.
 */

public class CustomAdapterAsignaturas extends RecyclerView.Adapter<CustomAdapterAsignaturas.AsignaturasViewHolder> implements Filterable {
    private List<Plan> plan;
    private List<Plan> planFiltro;
    private CustomFilterAsignaturas mFilter;
    private Activity activity;

    public CustomAdapterAsignaturas(List<Plan> plan, Activity activity) {
        this.plan = plan;
        this.planFiltro = new ArrayList<>();
        this.planFiltro.addAll(plan);
        this.mFilter = new CustomFilterAsignaturas(CustomAdapterAsignaturas.this);
        this.activity = activity;
    }

    @Override
    public AsignaturasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AsignaturasViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_asignaturas_pagos, parent, false));
    }

    @Override
    public void onBindViewHolder(AsignaturasViewHolder holder, int position) {
        holder.txvTituloCuatrimestre.setText(planFiltro.get(position).getNombre());
        LinearLayout linearLayout = new LinearLayout(activity);
        for (int x = 0; x < planFiltro.get(position).getMateria().size(); x++) {
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            TextView txvMateria = new TextView(activity);
            LinearLayout.LayoutParams layoutParamsMateria = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParamsMateria.topMargin = dpToPx(5);
            txvMateria.setLayoutParams(layoutParamsMateria);
            txvMateria.setText(planFiltro.get(position).getMateria().get(x).getMateria());
            txvMateria.setTypeface(txvMateria.getTypeface(), Typeface.BOLD);
            txvMateria.setId(position);
            linearLayout.addView(txvMateria);

            TextView txvEstatus = new TextView(activity);
            LinearLayout.LayoutParams layoutParamsEstatus = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParamsEstatus.bottomMargin = dpToPx(5);
            txvEstatus.setLayoutParams(layoutParamsEstatus);
            txvEstatus.setText(planFiltro.get(position).getMateria().get(x).getEstatus());
            txvEstatus.setId(position);
            linearLayout.addView(txvEstatus);

            if (x < planFiltro.get(position).getMateria().size() - 1) {
                View separador = new View(activity);
                separador.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(1)));
                separador.setId(position);
                separador.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorAccent));
                linearLayout.addView(separador);
            }
        }

        holder.llyContenido.removeAllViews();
        holder.llyContenido.addView(linearLayout);
    }

    @Override
    public int getItemCount() {
        return planFiltro.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    static class AsignaturasViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txv_titulo_cuatrimestre_id)
        TextView txvTituloCuatrimestre;
        @BindView(R.id.lly_contenido_id)
        LinearLayout llyContenido;

        public AsignaturasViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /*Filtro*/
    public class CustomFilterAsignaturas extends Filter {
        private CustomAdapterAsignaturas customAdapterAsignaturas;

        private CustomFilterAsignaturas(CustomAdapterAsignaturas customAdapterAsignaturas) {
            super();
            this.customAdapterAsignaturas = customAdapterAsignaturas;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            planFiltro.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                planFiltro.addAll(plan);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Plan p : plan) {
                    if (p.getNombre().toLowerCase().contains(filterPattern)) {
                        planFiltro.add(p);
                    }
                }
            }
            results.values = planFiltro;
            results.count = planFiltro.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.customAdapterAsignaturas.notifyDataSetChanged();
        }
    }
}