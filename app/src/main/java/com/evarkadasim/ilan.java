package com.evarkadasim;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.evarkadasim.login_fragment.login_depozito;
import static com.evarkadasim.login_fragment.login_detaylar;
import static com.evarkadasim.login_fragment.login_evdetay;
import static com.evarkadasim.login_fragment.login_icozellikler;
import static com.evarkadasim.login_fragment.login_il;
import static com.evarkadasim.login_fragment.login_ilce;
import static com.evarkadasim.login_fragment.login_image;
import static com.evarkadasim.login_fragment.login_kira;
import static com.evarkadasim.login_fragment.login_konutturu;
import static com.evarkadasim.login_fragment.login_latt;
import static com.evarkadasim.login_fragment.login_longg;
import static com.evarkadasim.login_fragment.login_name;
import static com.evarkadasim.login_fragment.login_telefon;
import static com.evarkadasim.login_fragment.login_ulasim;

public class ilan extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    TextView username1;
    CircleImageView photo;
    public static int page;

    public static int harita_durumu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (login_evdetay == null) {

            setContentView(R.layout.ilanlar2);
            page = 1;
            TextView telefon = findViewById(R.id.telefon);
            username1 = findViewById(R.id.name);
            TextView konum = findViewById(R.id.konum);
            TextView kira = findViewById(R.id.kira);
            TextView evtanimi = findViewById(R.id.evtanimi);
            TextView konutTuru = findViewById(R.id.konutturu);
            TextView arananlar = findViewById(R.id.arananlar);
            photo = findViewById(R.id.photo);

            ImageView back = findViewById(R.id.back);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavUtils.navigateUpFromSameTask(ilan.this);
                }
            });

            ImageView edit = findViewById(R.id.sendmessage);

            edit.setImageResource(R.drawable.edit2);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ilan.this, ilan_guncelleme.class);
                    startActivity(intent);
                }
            });

            telefon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("tel:" + login_telefon);
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(intent);
                }
            });

            telefon.setText(login_telefon);
            username1.setText(login_name);
            konum.setText(login_il + "-" + login_ilce);
            konutTuru.setText(login_konutturu);
            evtanimi.setText("Konum : " + login_il + "-" + login_ilce + "\n\nKonutun Türü : " + login_konutturu + "\n\nÖdeyebileceğim Kira : " + login_kira);
            arananlar.setText(login_detaylar);
            kira.setText(login_kira);
        } else {

            setContentView(R.layout.ilanlar1);

            page = 2;

            TextView telefon1 = findViewById(R.id.telefon);
            username1 = findViewById(R.id.name);
            TextView konum1 = findViewById(R.id.konum);
            TextView kira11 = findViewById(R.id.kira);
            TextView ozellik = findViewById(R.id.ozellik);
            TextView ulasim = findViewById(R.id.ulasim);
            TextView detaylar = findViewById(R.id.detaylar);
            TextView arananlar1 = findViewById(R.id.arananlar);
            photo = findViewById(R.id.photo);


            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.mapview);
            mapFragment.getMapAsync(this);


            ImageView genislet = findViewById(R.id.genislet);

            genislet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    harita_durumu = 1;
                    Intent intent = new Intent(ilan.this, MapsActivity.class);
                    startActivity(intent);
                }
            });

            ImageView back1 = findViewById(R.id.back);
            back1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavUtils.navigateUpFromSameTask(ilan.this);
                }
            });

            ImageView edit = findViewById(R.id.sendmessage);

            edit.setImageResource(R.drawable.edit2);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ilan.this, ilan_guncelleme.class);
                    startActivity(intent);
                }
            });

            telefon1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("tel:" + login_telefon);
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(intent);
                }
            });


            detaylar.setText("Ev ile İlgili Diğer Detaylar : " + login_evdetay);

            telefon1.setText(login_telefon);
            username1.setText(login_name);
            konum1.setText(login_il + "-" + login_ilce);
            kira11.setText(login_kira);
            ozellik.setText("Konum : " + login_il + "-" + login_ilce + "\n\nKonutun Türü : " + login_konutturu + "\n\nOda ve Salon Sayısı : " + login_icozellikler + "\n\nDepozito Tutarı : " + login_depozito);
            ulasim.setText(login_ulasim);
            arananlar1.setText(login_detaylar);
        }
        photo.setImageBitmap(BitmapFactory.decodeByteArray(login_image, 0, login_image.length));
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng latLng = new LatLng(login_latt, login_longg);
        googleMap.addMarker(new MarkerOptions().position(latLng));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
    }
}
