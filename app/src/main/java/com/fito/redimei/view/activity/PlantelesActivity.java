package com.fito.redimei.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;

import com.fito.redimei.R;
import com.fito.redimei.modelo.InformacionPlanteles;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

import static com.fito.redimei.utils.Constantes.BUNDLE_NOMBRE_OPCION;

public class PlantelesActivity extends ToolBarActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleMap googleMap;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_chevron_left);

        Bundle bundleExtras = getIntent().getExtras();
        if (bundleExtras != null) {
            setTitle(bundleExtras.getString(BUNDLE_NOMBRE_OPCION));
        }

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        imeiPresenter.consultaListaPlanteles();
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_planteles;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        this.googleMap.setMyLocationEnabled(true);
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        //Se ha interrumpido la conexiÃ³n con Google Play Services
        //Log.e("RLM", "Se ha interrumpido la conexiÃ³n con Google Play Services");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //Se ha producido un error que no se puede resolver automÃ¡ticamente
        //y la conexiÃ³n con los Google Play Services no se ha establecido.
        //Log.e("RLM", "Error grave al conectar con Google Play Services");
    }

    @Override
    public void despliegaPlanteles(InformacionPlanteles informacionPlanteles) {
        for (int x = 0; x < informacionPlanteles.getPlanteles().size(); x++) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(informacionPlanteles.getPlanteles().get(x).getLatitud()), Double.parseDouble(informacionPlanteles.getPlanteles().get(x).getLongitud())))
                    .title(informacionPlanteles.getPlanteles().get(x).getNombre()));

            if (x == informacionPlanteles.getPlanteles().size() - 1) {
                CameraUpdate zoomCamara = CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(informacionPlanteles.getPlanteles().get(x).getLatitud()), Double.parseDouble(informacionPlanteles.getPlanteles().get(x).getLongitud())), 10);
                googleMap.moveCamera(zoomCamara);
            }
        }

        this.googleMap.setOnMarkerClickListener((marker) -> {
            for (int x = 0; x < informacionPlanteles.getPlanteles().size(); x++) {
                if (marker.getTitle().equals(informacionPlanteles.getPlanteles().get(x).getNombre())) {
                    Uri uri = Uri.parse("geo:"+informacionPlanteles.getPlanteles().get(x).getLatitud()+"," + informacionPlanteles.getPlanteles().get(x).getLongitud() + "?q=" + informacionPlanteles.getPlanteles().get(x).getLatitud()+"," + informacionPlanteles.getPlanteles().get(x).getLongitud() + "(" + informacionPlanteles.getPlanteles().get(x).getNombre() +")");
                    Intent intentMapas = new Intent(Intent.ACTION_VIEW, uri);

                    // Crea e inicia el diálogo de selección
                    Intent chooser = Intent.createChooser(intentMapas, getResources().getText(R.string.msg_seleccione_app));
                    startActivity(chooser);
                    break;
                }

            }
            return true;
        });
    }

    @Override
    public Context context() {
        return PlantelesActivity.this;
    }
}