package com.fito.redimei.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fito.redimei.R;
import com.fito.redimei.view.adapter.CustomAdapterDirectorio;

import java.util.Arrays;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DirectorioFragment extends Fragment {
    @BindView(R.id.rcv_principal_id)
    RecyclerView rcvPrincipal;

    @BindArray(R.array.lista_plantel_directorio)
    String[] tituloPlanteles;
    @BindArray(R.array.lista_telefono_directorio)
    String[] tituloTelefonos;

    private static DirectorioFragment instancia;

    public static DirectorioFragment newInstance() {
        if (instancia == null) {
            instancia = new DirectorioFragment();
        }
        return instancia;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_bottom_navigation_view, container, false);
        ButterKnife.bind(this, view);

        CustomAdapterDirectorio customAdapterDirectorio = new CustomAdapterDirectorio(Arrays.asList(tituloPlanteles), Arrays.asList(tituloTelefonos));
        customAdapterDirectorio.getViewClickedObservable()
                .subscribe(itemView -> {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(phoneNumberFormat(tituloTelefonos[rcvPrincipal.getChildAdapterPosition(itemView)]));
                    startActivity(intent);
                });
        rcvPrincipal.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvPrincipal.setHasFixedSize(true);
        rcvPrincipal.swapAdapter(customAdapterDirectorio, true);

        return view;
    }

    private Uri phoneNumberFormat(String number) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("tel:");
        stringBuilder.append(number);
        return Uri.parse(stringBuilder.toString());
    }
}