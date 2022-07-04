package com.evarkadasim;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class login_fragment extends Fragment {

    float v = 0;
    EditText email, password;
    Button login;
    DBHandler dbHandler;
    public static int ilan_turu;


    public static String login_telefon;
    public static String login_name;
    public static String login_il;
    public static String login_ilce;
    public static String login_kira;
    public static String login_detaylar;
    public static String login_konutturu;
    public static String login_evdetay;
    public static String login_icozellikler;
    public static String login_odasalon;
    public static String login_ulasim;
    public static String login_email;
    public static String login_depozito;
    public static byte[] login_image;
    public static double login_latt;
    public static double login_longg;
    public static int login_id;
    public static int login_user_id;
    public static String login_user_name;
    public static String login_user_telefon;
    public static String login_user_email;
    public static byte[] login_user_image;
    public static String login_user_pass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login, container, false);

        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.sifre);
        login = root.findViewById(R.id.login);
        dbHandler = new DBHandler(getContext());
        dbHandler.open();

        email.setTranslationX(800);
        password.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        login.setAlpha(v);


        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eposta = email.getText().toString();
                String pass = password.getText().toString();
                if (eposta.equals("") || pass.equals(""))
                    Toast.makeText(getContext(), "Tüm alanlar zorunludur", Toast.LENGTH_SHORT).show();
                else {
                    if (dbHandler.checkEmailandPassword(eposta, pass)) {

                        Cursor cursor = dbHandler.GetUser1(eposta.trim());
                        Cursor cursor1 = dbHandler.GetUser2(eposta.trim());
                        Cursor cursor2 = dbHandler.GetUser3(eposta.trim());
                        cursor.moveToFirst();
                        cursor1.moveToFirst();
                        cursor2.moveToFirst();

                        if (cursor2 != null && cursor2.getCount() > 0) {

                            login_user_email = cursor2.getString(cursor2.getColumnIndexOrThrow("email"));
                            login_user_name = cursor2.getString(cursor2.getColumnIndexOrThrow("kullanici_adi"));
                            login_user_pass = cursor2.getString(cursor2.getColumnIndexOrThrow("sifre"));
                            login_user_id = cursor2.getInt(cursor2.getColumnIndexOrThrow("_id"));
                            login_user_image = cursor2.getBlob(cursor2.getColumnIndexOrThrow("profil_fotografi"));
                            login_user_telefon = cursor2.getString(cursor2.getColumnIndexOrThrow("telefon"));

                            if (!dbHandler.ilanKontrol1(eposta, pass) && !dbHandler.ilanKontrol2(eposta, pass)) {
                                login_name = null;
                            } else {

                                if (cursor != null && cursor.getCount() > 0 && dbHandler.ilanKontrol2(eposta, pass)) {
                                    login_id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                                    login_telefon = cursor.getString(cursor.getColumnIndexOrThrow("telefon"));
                                    login_name = cursor.getString(cursor.getColumnIndexOrThrow("kullanici_adi"));
                                    login_il = cursor.getString(cursor.getColumnIndexOrThrow("il"));
                                    login_ilce = cursor.getString(cursor.getColumnIndexOrThrow("ilce"));
                                    login_kira = cursor.getString(cursor.getColumnIndexOrThrow("kira"));
                                    login_detaylar = cursor.getString(cursor.getColumnIndexOrThrow("detay"));
                                    login_image = cursor.getBlob(cursor.getColumnIndexOrThrow("profil_fotografi"));
                                    login_konutturu = cursor.getString(cursor.getColumnIndexOrThrow("konutturu"));
                                    login_email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                                    login_evdetay = null;
                                    ilan_turu = 1;
                                }

                                if (cursor1 != null && cursor1.getCount() > 0 && dbHandler.ilanKontrol1(eposta, pass)) {
                                    login_id = cursor1.getInt(cursor1.getColumnIndexOrThrow("_id"));
                                    login_telefon = cursor1.getString(cursor1.getColumnIndexOrThrow("telefon"));
                                    login_name = cursor1.getString(cursor1.getColumnIndexOrThrow("kullanici_adi"));
                                    login_il = cursor1.getString(cursor1.getColumnIndexOrThrow("il"));
                                    login_ilce = cursor1.getString(cursor1.getColumnIndexOrThrow("ilce"));
                                    login_kira = cursor1.getString(cursor1.getColumnIndexOrThrow("kira"));
                                    login_detaylar = cursor1.getString(cursor1.getColumnIndexOrThrow("detay"));
                                    login_depozito = cursor1.getString(cursor1.getColumnIndexOrThrow("depozito"));
                                    login_evdetay = cursor1.getString(cursor1.getColumnIndexOrThrow("evdetay"));
                                    login_konutturu = cursor1.getString(cursor1.getColumnIndexOrThrow("konutturu"));
                                    login_odasalon = cursor1.getString(cursor1.getColumnIndexOrThrow("odasalon"));
                                    login_icozellikler = cursor1.getString(cursor1.getColumnIndexOrThrow("ozellikler"));
                                    login_ulasim = cursor1.getString(cursor1.getColumnIndexOrThrow("ulasim"));
                                    login_image = cursor1.getBlob(cursor1.getColumnIndexOrThrow("profil_fotografi"));
                                    login_latt = cursor1.getDouble(cursor1.getColumnIndexOrThrow("latitude"));
                                    login_longg = cursor1.getDouble(cursor1.getColumnIndexOrThrow("longitude"));
                                    login_email = cursor1.getString(cursor1.getColumnIndexOrThrow("email"));
                                    ilan_turu = 2;
                                }

                            }

                        }

                        Intent anasayfa = new Intent(getContext(), MainActivity.class);
                        startActivity(anasayfa);

                    } else {
                        Toast.makeText(getContext(), "Geçersiz bilgiler", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


        return root;
    }
}
