package com.evarkadasim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.evarkadasim.ilan_ekrani1.a;
import static com.evarkadasim.login_fragment.login_user_email;
import static com.evarkadasim.ui.kalacak_yer.kalacak_yer_fragment.detaylar2;
import static com.evarkadasim.ui.kalacak_yer.kalacak_yer_fragment.illl1;
import static com.evarkadasim.ui.kalacak_yer.kalacak_yer_fragment.illlce1;
import static com.evarkadasim.ui.kalacak_yer.kalacak_yer_fragment.image1;
import static com.evarkadasim.ui.kalacak_yer.kalacak_yer_fragment.kira2;
import static com.evarkadasim.ui.kalacak_yer.kalacak_yer_fragment.konutturu2;
import static com.evarkadasim.ui.kalacak_yer.kalacak_yer_fragment.names1;
import static com.evarkadasim.ui.kalacak_yer.kalacak_yer_fragment.telefonnn1;

public class ilan_ekrani extends AppCompatActivity {

    TextView username1;
    CircleImageView photo;
    public static String telefon_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ilanlar2);
        TextView telefon = findViewById(R.id.telefon);
        username1 = findViewById(R.id.name);
        TextView konum = findViewById(R.id.konum);
        TextView kira = findViewById(R.id.kira);
        TextView evtanimi = findViewById(R.id.evtanimi);
        TextView konutTuru = findViewById(R.id.konutturu);
        TextView arananlar = findViewById(R.id.arananlar);
        photo = findViewById(R.id.photo);

        a = 2;

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(ilan_ekrani.this);
            }
        });

        ImageView mesaj = findViewById(R.id.sendmessage);

        mesaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ev_arkadasi_fragment.email_kontrol.equals(login_user_email)) {

                    Toast.makeText(ilan_ekrani.this, "Kendinize Mesaj Gönderemezsiniz", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ilan_ekrani.this, MessageActivity.class);
                    startActivity(intent);
                }
            }
        });

        telefon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:" + telefonnn1);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        telefon.setText(telefonnn1);
        username1.setText(names1);
        konum.setText(illl1 + "-" + illlce1);
        kira.setText(kira2);
        konutTuru.setText(konutturu2);
        evtanimi.setText("Konum : " + illl1 + "-" + illlce1 + "\n\nKonutun Türü : " + konutturu2 + "\n\nÖdeyebileceğim Kira : " + kira2);
        arananlar.setText(detaylar2);
        photo.setImageBitmap(BitmapFactory.decodeByteArray(image1, 0, image1.length));

        telefon_num = telefon.getText().toString();

    }

}
