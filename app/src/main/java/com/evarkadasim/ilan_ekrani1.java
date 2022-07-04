package com.evarkadasim;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.evarkadasim.login_fragment.login_user_email;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.depozito1;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.detaylar1;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.evdetay1;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.icozellikler1;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.illl;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.illlce;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.image;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.kira1;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.konutturu1;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.latt;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.longg;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.names;

import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.telefonnn;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.ulasim1;

public class ilan_ekrani1 extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {


    TextView username1;
    CircleImageView photo;

    public static int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ilanlar1);

        TextView telefon = findViewById(R.id.telefon);
        username1 = findViewById(R.id.name);
        TextView konum = findViewById(R.id.konum);
        TextView kira = findViewById(R.id.kira);
        TextView ozellik = findViewById(R.id.ozellik);
        TextView ulasim = findViewById(R.id.ulasim);
        TextView detaylar = findViewById(R.id.detaylar);
        TextView arananlar = findViewById(R.id.arananlar);
        photo = findViewById(R.id.photo);

        a = 1;


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapview);
        mapFragment.getMapAsync(this);


        ImageView genislet = findViewById(R.id.genislet);

        genislet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilan.harita_durumu = 3;
                Intent intent = new Intent(ilan_ekrani1.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(ilan_ekrani1.this);
            }
        });

        ImageView mesaj = findViewById(R.id.sendmessage);

        mesaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ev_arkadasi_fragment.email_kontrol.equals(login_user_email)) {

                    Toast.makeText(ilan_ekrani1.this, "Kendinize Mesaj Gönderemezsiniz", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ilan_ekrani1.this, MessageActivity.class);
                    startActivity(intent);
                }
            }
        });

        telefon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:" + telefonnn);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });


        detaylar.setText("Ev ile İlgili Diğer Detaylar : " + evdetay1);

        telefon.setText(telefonnn);
        username1.setText(names);
        konum.setText(illl + "-" + illlce);
        kira.setText(kira1);
        ozellik.setText("Konum : " + illl + "-" + illlce + "\n\nKonutun Türü : " + konutturu1 + "\n\nOda ve Salon Sayısı : " + icozellikler1 + "\n\nDepozito Tutarı : " + depozito1);
        ulasim.setText(ulasim1);
        arananlar.setText(detaylar1);
        photo.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));

        ilan_ekrani.telefon_num = telefon.getText().toString();

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng latLng = new LatLng(latt, longg);
        googleMap.addMarker(new MarkerOptions().position(latLng));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));

    }
}
