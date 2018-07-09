package com.fito.redimei.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fito.redimei.R;
import com.fito.redimei.modelo.Pagos;
import com.fito.redimei.view.adapter.CustomAdapterPagos;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagosFragment extends Fragment {
    @BindView(R.id.rcv_principal_id)
    RecyclerView rcvPrincipal;

    private static PagosFragment instancia;
    private List<Pagos> pagos;

    private CustomAdapterPagos customAdapterPagos;

    public PagosFragment() {}

    @SuppressLint("ValidFragment")
    private PagosFragment(List<Pagos> pagos) {
        this.pagos = pagos;
    }

    public static PagosFragment initInstance(List<Pagos> pagos) {
        PagosFragment pagosFragment = new PagosFragment(pagos);
        instancia = pagosFragment;
        return pagosFragment;
    }

    public static PagosFragment newInstance(List<Pagos> pagos) {
        if (instancia == null) {
            instancia = new PagosFragment(pagos);
        }
        return instancia;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_bottom_navigation_view, container, false);
        ButterKnife.bind(this, view);

        customAdapterPagos = new CustomAdapterPagos(pagos, getActivity());
        rcvPrincipal.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvPrincipal.setHasFixedSize(true);
        rcvPrincipal.swapAdapter(customAdapterPagos, true);

        return view;
    }

    public void filtro(String palabra) {
        customAdapterPagos.getFilter().filter(palabra);
    }
}