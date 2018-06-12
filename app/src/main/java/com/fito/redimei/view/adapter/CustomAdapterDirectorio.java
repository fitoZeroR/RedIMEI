package com.fito.redimei.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fito.redimei.R;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by luisr on 31/01/2018.
 */

public class CustomAdapterDirectorio extends RecyclerView.Adapter<CustomAdapterDirectorio.DirectorioViewHolder> {
    private List<String> listaPlanteles, listaTelefonos;
    private PublishSubject<View> mViewClickSubject;

    public CustomAdapterDirectorio(List<String> listaPlanteles, List<String> listaTelefonos) {
        this.listaPlanteles = listaPlanteles;
        this.listaTelefonos = listaTelefonos;
        mViewClickSubject = PublishSubject.create();
    }

    @Override
    public DirectorioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_directorio, parent, false);

        RxView.clicks(itemView)
                .takeUntil(RxView.detaches(parent))
                .map(aVoid -> itemView)
                .subscribe(mViewClickSubject);

        return new DirectorioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DirectorioViewHolder holder, int position) {
        holder.txvPlantel.setText(listaPlanteles.get(position));
        holder.txvTelefono.setText(listaTelefonos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaPlanteles.size();
    }

    public Observable<View> getViewClickedObservable() {
        return mViewClickSubject.hide();
    }

    static class DirectorioViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txv_plantel_id)
        TextView txvPlantel;
        @BindView(R.id.txv_telefono_id)
        TextView txvTelefono;

        public DirectorioViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}