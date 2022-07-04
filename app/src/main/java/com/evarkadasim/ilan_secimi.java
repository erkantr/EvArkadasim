package com.evarkadasim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ilan_secimi extends AppCompatActivity {


    public static int ilan_secim_sinifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        ilan_secim_sinifi = 1;

        Button ev_arkadasi = findViewById(R.id.ev_arkadasi);
        Button kalacak_yer = findViewById(R.id.kalacak_yer);

        ev_arkadasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ilan_secimi.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        kalacak_yer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ilan_secimi.this, kalacak_yer_kayit.class);
                startActivity(intent);
            }
        });

    }
}
