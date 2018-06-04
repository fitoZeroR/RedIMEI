package com.fito.redimei.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.fito.redimei.R;

import butterknife.BindView;

import static com.fito.redimei.utils.Constantes.*;

public class DescripcionActivity extends ToolBarActivity {
    @BindView(R.id.txv_descripcion_id)
    TextView txvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left);

        Bundle bundle = getIntent().getExtras();
        String descripcion = null;
        if (bundle != null) {
            descripcion = bundle.getString(BUNDLE_DESCRIPCION);
            setTitle(bundle.getString(BUNDLE_NOMBRE_OPCION));
        }

        txvDescripcion.setText(Html.fromHtml (descripcion), TextView.BufferType.SPANNABLE);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_descripcion;
    }

    @Override
    public Context context() {
        return DescripcionActivity.this;
    }
}