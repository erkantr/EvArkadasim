package com.evarkadasim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.evarkadasim.ilan.page;
import static com.evarkadasim.login_fragment.login_depozito;
import static com.evarkadasim.login_fragment.login_detaylar;
import static com.evarkadasim.login_fragment.login_email;
import static com.evarkadasim.login_fragment.login_evdetay;
import static com.evarkadasim.login_fragment.login_icozellikler;
import static com.evarkadasim.login_fragment.login_id;
import static com.evarkadasim.login_fragment.login_il;
import static com.evarkadasim.login_fragment.login_ilce;
import static com.evarkadasim.login_fragment.login_image;
import static com.evarkadasim.login_fragment.login_kira;
import static com.evarkadasim.login_fragment.login_konutturu;
import static com.evarkadasim.login_fragment.login_name;
import static com.evarkadasim.login_fragment.login_odasalon;
import static com.evarkadasim.login_fragment.login_ulasim;
import static com.evarkadasim.login_fragment.login_user_id;

public class ilan_guncelleme extends AppCompatActivity {

    //MaterialEditText il, ilce, kira, depozito, odasalon, ozellikler, ulasim, detaylar, konutturu, evdetaylari;
    CircleImageView imageView;

    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHandler = new DBHandler(this);
        dbHandler.open();

        if (page == 1) {
            setContentView(R.layout.guncelle2);
            final TextView il = findViewById(R.id.il);
            final TextView ilce = findViewById(R.id.ilce);
            final TextView kira = findViewById(R.id.kira);
            final TextView detaylar = findViewById(R.id.detaylar);
            imageView = findViewById(R.id.photo);
            final TextView konutturu = findViewById(R.id.konutturu);
            Button guncelle = findViewById(R.id.btn_guncelle);

            il.setText(login_il);
            ilce.setText(login_ilce);
            kira.setText(login_kira);
            detaylar.setText(login_detaylar);
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(login_image, 0, login_image.length));
            konutturu.setText(login_konutturu);

            ImageView delete = findViewById(R.id.delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ilan_guncelleme.this);
                    builder.setTitle("");
                    builder.setMessage("İlanı Silmek İstediğinize Emin misiniz?");
                    builder.setNegativeButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dbHandler.deleteRow(login_email);

                            login_name = null;

                            finish();
                            Intent intent = new Intent(ilan_guncelleme.this, StartActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setPositiveButton("Hayır", null);
                    builder.show();
                }
            });

            ImageView back = findViewById(R.id.back);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavUtils.navigateUpFromSameTask(ilan_guncelleme.this);
                }
            });

            guncelle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String new_il = il.getText().toString();
                    final String new_ilce = ilce.getText().toString();
                    final String new_kira = kira.getText().toString();
                    final String new_detay = detaylar.getText().toString();
                    final String new_konutturu = konutturu.getText().toString();

                    AlertDialog.Builder builder = new AlertDialog.Builder(ilan_guncelleme.this);
                    builder.setTitle("");
                    builder.setMessage("İlanı Güncellemek İstediğinize Emin misiniz?");
                    builder.setNegativeButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dbHandler.edit3(login_id, new_il, new_ilce, new_kira, new_detay, new_konutturu);

                            login_il = new_il;
                            login_ilce = new_ilce;
                            login_kira = new_kira;
                            login_detaylar = new_detay;
                            login_konutturu = new_konutturu;

                        }
                    });
                    builder.setPositiveButton("Hayır", null);
                    builder.show();


                }
            });

        }
        if (page == 2) {
            setContentView(R.layout.guncelle1);
            final TextView il1 = findViewById(R.id.il);
            final TextView ilce1 = findViewById(R.id.ilce);
            final TextView kira1 = findViewById(R.id.kira);
            final TextView depozito1 = findViewById(R.id.depozito);
            final TextView odasalon1 = findViewById(R.id.odasalon);
            final TextView ozellikler1 = findViewById(R.id.ozellikler);
            final TextView ulasim1 = findViewById(R.id.ulasim);
            final TextView detaylar1 = findViewById(R.id.detaylar);
            imageView = findViewById(R.id.photo);
            final TextView konutturu1 = findViewById(R.id.konutturu);
            final TextView evdetaylari1 = findViewById(R.id.evdetaylari);
            Button guncelle1 = findViewById(R.id.btn_guncelle);

            il1.setText(login_il);
            ilce1.setText(login_ilce);
            kira1.setText(login_kira);
            depozito1.setText(login_depozito);
            odasalon1.setText(login_odasalon);
            ozellikler1.setText(login_icozellikler);
            ulasim1.setText(login_ulasim);
            detaylar1.setText(login_detaylar);
            konutturu1.setText(login_konutturu);
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(login_image, 0, login_image.length));
            evdetaylari1.setText(login_evdetay);

            ImageView back = findViewById(R.id.back);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavUtils.navigateUpFromSameTask(ilan_guncelleme.this);
                }
            });

            ImageView delete = findViewById(R.id.delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ilan_guncelleme.this);
                    builder.setTitle("");
                    builder.setMessage("İlanı Silmek İstediğinize Emin misiniz?");
                    builder.setNegativeButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dbHandler.deleteRow1(login_email);
                            finish();
                            Intent intent = new Intent(ilan_guncelleme.this, StartActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setPositiveButton("Hayır", null);
                    builder.show();
                }
            });

            guncelle1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String new_il = il1.getText().toString();
                    final String new_ilce = ilce1.getText().toString();
                    final String new_depozito = depozito1.getText().toString();
                    final String new_odasalon = odasalon1.getText().toString();
                    final String new_ozellikler = ozellikler1.getText().toString();
                    final String new_ulasim = ulasim1.getText().toString();
                    final String new_kira = kira1.getText().toString();
                    final String new_detay = detaylar1.getText().toString();
                    final String new_konutturu = konutturu1.getText().toString();
                    final String new_evdetay = evdetaylari1.getText().toString();

                    AlertDialog.Builder builder = new AlertDialog.Builder(ilan_guncelleme.this);
                    builder.setTitle("");
                    builder.setMessage("İlanı Güncellemek İstediğinize Emin misiniz?");
                    builder.setNegativeButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            dbHandler.edit4(login_user_id, new_il, new_ilce, new_kira, new_depozito, new_konutturu, new_ozellikler, new_ulasim, new_evdetay, new_detay, new_odasalon);

                            login_il = new_il;
                            login_ilce = new_ilce;
                            login_depozito = new_depozito;
                            login_odasalon = new_odasalon;
                            login_icozellikler = new_ozellikler;
                            login_ulasim = new_ulasim;
                            login_kira = new_kira;
                            login_detaylar = new_detay;
                            login_konutturu = new_konutturu;
                            login_evdetay = new_evdetay;
                        }
                    });
                    builder.setPositiveButton("Hayır", null);
                    builder.show();

                }
            });
        }
    }
}
