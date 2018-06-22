package com.fito.redimei.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fito.redimei.R;
import com.fito.redimei.modelo.Pagos;
import com.fito.redimei.modelo.Plan;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstadisticasFragment extends Fragment {
    @BindView(R.id.bar_chart_id)
    BarChart barChart;
    @BindView(R.id.pie_chart_id)
    PieChart pieChart;

    private List<Pagos> pagos;
    private List<Plan> plan;
    private int aprobada, cursada, noCursada, pagado, noPagado, demorado;

    public EstadisticasFragment() {}

    @SuppressLint("ValidFragment")
    public EstadisticasFragment(List<Pagos> pagos, List<Plan> plan) {
        this.pagos = pagos;
        this.plan = plan;
    }

    private static EstadisticasFragment instancia;

    public static EstadisticasFragment newInstance(List<Pagos> pagos, List<Plan> plan) {
        if (instancia == null) {
            instancia = new EstadisticasFragment(pagos, plan);
        }
        return instancia;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_estadisticas, container, false);
        ButterKnife.bind(this, view);

        // BarChart
        obtieneStatusPago();
        BarDataSet barDataSet = new BarDataSet(addValuesToBarEntryPagos(), "Etiqueta");
        BarData barData = new BarData(addValuesToBarEntryLabelsPagos(), barDataSet);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(barData);
        barChart.setDescription(getString(R.string.title_fragment_pagos));
        barChart.animateY(3000);

        // PieChart
        obtieneStatusAsignaturas();
        PieDataSet pieDataSet = new PieDataSet(addValuesToPieEntryAsignaturas(), "");
        PieData pieData = new PieData(addValuesToEntryLabelsAsignaturas(), pieDataSet);
        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        pieChart.setData(pieData);
        pieChart.setDescription(getString(R.string.title_fragment_asignaturas));
        pieChart.animateY(3000);

        aprobada = cursada = noCursada = pagado = noPagado = demorado =0;

        return view;
    }

    public List<BarEntry> addValuesToBarEntryPagos(){
        List<BarEntry> barEntryList = new ArrayList<>();
        barEntryList.add(new BarEntry(pagado, 0));
        barEntryList.add(new BarEntry(noPagado, 1));
        barEntryList.add(new BarEntry(demorado, 2));
        return barEntryList;

    }

    public List<String> addValuesToBarEntryLabelsPagos(){
        List<String>  entryLabels = new ArrayList<>();
        entryLabels.add("Pagado");
        entryLabels.add("No Pagado");
        entryLabels.add("Demorado");
        return entryLabels;
    }

    private void obtieneStatusPago() {
        for (int x = 0; x < pagos.size(); x++) {
            for (int y = 0; y < pagos.get(x).getEstatusPagos().size(); y++)
                if(pagos.get(x).getEstatusPagos().get(y).getEstatus().equals("Pagado")) {
                    pagado++;
                } else if(pagos.get(x).getEstatusPagos().get(y).getEstatus().equals("No Pagado")) {
                    noPagado++;
                } else {
                    demorado++;
                }
        }
    }

    private List<Entry> addValuesToPieEntryAsignaturas(){
        List<Entry> entries = new ArrayList<>();
        entries.add(new BarEntry(aprobada, 0));
        entries.add(new BarEntry(cursada, 1));
        entries.add(new BarEntry(noCursada, 2));
        return  entries;
    }

    private List<String> addValuesToEntryLabelsAsignaturas(){
        List<String> entryLabels = new ArrayList<>();
        entryLabels.add("Aprobada");
        entryLabels.add("Cursada");
        entryLabels.add("No Cursada");
        return entryLabels;
    }

    private void obtieneStatusAsignaturas() {
        for (int x = 0; x < plan.size(); x++) {
            for (int y = 0; y < plan.get(x).getMateria().size(); y++)
                if(plan.get(x).getMateria().get(y).getEstatus().equals("Aprobada")) {
                aprobada++;
                } else if(plan.get(x).getMateria().get(y).getEstatus().equals("Cursada")) {
                cursada++;
                } else {
                noCursada++;
            }
        }
    }
}