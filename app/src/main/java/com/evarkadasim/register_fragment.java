package com.evarkadasim;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import static com.evarkadasim.ilan.harita_durumu;

public class register_fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.register, container, false);

        Button ev_arkadasi = root.findViewById(R.id.ev_arkadasi);
        Button kalacak_yer = root.findViewById(R.id.kalacak_yer);

        ilan_secimi.ilan_secim_sinifi = 0;

        ev_arkadasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                harita_durumu = 2;
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
        kalacak_yer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), kalacak_yer_kayit.class);
                startActivity(intent);
            }
        });

        return root;
    }

}
