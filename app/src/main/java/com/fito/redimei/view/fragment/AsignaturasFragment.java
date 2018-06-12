package com.fito.redimei.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fito.redimei.R;
import com.fito.redimei.modelo.Plan;
import com.fito.redimei.view.adapter.CustomAdapterAsignaturas;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AsignaturasFragment extends Fragment {
    @BindView(R.id.rcv_principal_id)
    RecyclerView rcvPrincipal;

    private static AsignaturasFragment instancia;
    private List<Plan> plan;

    private CustomAdapterAsignaturas customAdapterAsignaturas;

    public AsignaturasFragment() {}

    @SuppressLint("ValidFragment")
    private AsignaturasFragment(List<Plan> plan) {
        this.plan = plan;
    }

    public static AsignaturasFragment initInstance(List<Plan> plan) {
        AsignaturasFragment asignaturasFragment = new AsignaturasFragment(plan);
        instancia = asignaturasFragment;
        return asignaturasFragment;
    }

    public static AsignaturasFragment newInstance(List<Plan> plan) {
        if (instancia == null) {
            instancia = new AsignaturasFragment(plan);
        }
        return instancia;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_bottom_navigation_view, container, false);
        ButterKnife.bind(this, view);

        customAdapterAsignaturas = new CustomAdapterAsignaturas(plan, getActivity());
        rcvPrincipal.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvPrincipal.setHasFixedSize(true);
        rcvPrincipal.swapAdapter(customAdapterAsignaturas, true);

        return view;
    }

    public void filtro(String palabra) {
        customAdapterAsignaturas.getFilter().filter(palabra);
    }
}