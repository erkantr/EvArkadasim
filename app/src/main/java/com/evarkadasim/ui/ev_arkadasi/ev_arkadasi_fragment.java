package com.evarkadasim.ui.ev_arkadasi;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.evarkadasim.DBHandler;
import com.evarkadasim.R;
import com.evarkadasim.ilan_ekrani1;
import com.evarkadasim.model.ListvAdapter1;

import java.util.ArrayList;
import java.util.Collections;

public class ev_arkadasi_fragment extends Fragment {

    View root;
    DBHandler dbHandler;
    public static String names;
    public static String illl;
    public static String illlce;
    public static String kira1;
    public static String detaylar1;
    public static int depozito1;
    public static String konutturu1;
    public static String evdetay1;
    public static String icozellikler1;
    public static String ulasim1;
    public static String telefonnn;
    public static byte[] image;
    public static double latt;
    public static double longg;
    public static String email_kontrol;

    public static int mesajlasma_durumu;


    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dbHandler = new DBHandler(getContext());
        dbHandler.open(); 
        final Cursor cursor1 = dbHandler.getEmptList1();
        if (cursor1 == null || cursor1.getCount() == 0) {
            root = inflater.inflate(R.layout.ev_arkadasi_bos_fragment, container, false);
        } else {
            root = inflater.inflate(R.layout.ev_arkadasi_fragment, container, false);

            ListvAdapter1 adapter1 = new ListvAdapter1(getContext(), cursor1, 0);

            ListView listView = root.findViewById(R.id.liste);
            listView.setAdapter(adapter1);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                    Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                    telefonnn = cursor.getString(cursor.getColumnIndexOrThrow("telefon"));
                    email_kontrol = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                    names = cursor.getString(cursor.getColumnIndexOrThrow("kullanici_adi"));
                    illl = cursor.getString(cursor.getColumnIndexOrThrow("il"));
                    illlce = cursor.getString(cursor.getColumnIndexOrThrow("ilce"));
                    kira1 = cursor.getString(cursor.getColumnIndexOrThrow("kira"));
                    detaylar1 = cursor.getString(cursor.getColumnIndexOrThrow("detay"));
                    konutturu1 = cursor.getString(cursor.getColumnIndexOrThrow("konutturu"));
                    icozellikler1 = cursor.getString(cursor.getColumnIndexOrThrow("odasalon"));
                    ulasim1 = cursor.getString(cursor.getColumnIndexOrThrow("ulasim"));
                    depozito1 = cursor.getInt(cursor.getColumnIndexOrThrow("depozito"));
                    evdetay1 = cursor.getString(cursor.getColumnIndexOrThrow("evdetay"));
                    image = cursor.getBlob(cursor.getColumnIndexOrThrow("profil_fotografi"));
                    latt = cursor.getDouble(cursor.getColumnIndexOrThrow("latitude"));
                    longg = cursor.getDouble(cursor.getColumnIndexOrThrow("longitude"));

                    Intent intent = new Intent(getContext(), ilan_ekrani1.class);
                    startActivity(intent);
                }
            });

        }
        return root;
    }
}
