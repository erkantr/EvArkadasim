package com.evarkadasim;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static com.evarkadasim.ilan.harita_durumu;
import static com.evarkadasim.login_fragment.login_latt;
import static com.evarkadasim.login_fragment.login_longg;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.latt;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.longg;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;

    List<Marker> markerList;

    public static double latitude;
    public static double longitude;
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        this.setTitle("Evinizin konumunu belirleyin");
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);

        if (harita_durumu == 1) {
            latLng = new LatLng(login_latt, login_longg);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));
        }
        if (harita_durumu == 3) {
            latLng = new LatLng(latt, longg);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));
        }

        markerList = new ArrayList<>();

        for (Marker m : markerList) {
            if (harita_durumu == 2) {
                latLng = new LatLng(m.getPosition().latitude, m.getPosition().longitude);
                mMap.addMarker(new MarkerOptions().position(latLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
            }
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        if (harita_durumu == 2) {

            mMap.clear();

            latitude = latLng.latitude;
            longitude = latLng.longitude;

            LatLng latLng1 = new LatLng(latitude, longitude);

            mMap.addMarker(new MarkerOptions().position(latLng1));

            AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
            builder.setTitle("");
            builder.setMessage("Konum Bilgisi Kaydedilsin mi?");
            builder.setPositiveButton("Hayır", null);
            builder.setNegativeButton("Evet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    intent1();

                }
            });
            builder.show();

        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    public void intent1() {
        Intent intent = new Intent(MapsActivity.this, ev_arkadasi_kayit.class);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        startActivity(intent);
    }
}