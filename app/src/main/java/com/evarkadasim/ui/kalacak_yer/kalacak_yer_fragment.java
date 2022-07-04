package com.evarkadasim.ui.kalacak_yer;

import android.annotation.SuppressLint;
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
import androidx.recyclerview.widget.RecyclerView;

import com.evarkadasim.DBHandler;
import com.evarkadasim.R;
import com.evarkadasim.ilan_ekrani;
import com.evarkadasim.model.ListvAdapter2;
import com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment;

import java.util.ArrayList;
import java.util.Objects;


public class kalacak_yer_fragment extends Fragment {


    DBHandler dbHandler;
    public static String names1;
    public static String illl1;
    public static String illlce1;
    public static String kira2;
    public static String konutturu2;
    public static String detaylar2;
    public static String telefonnn1;
    public static byte[] image1;
    View root;


    @SuppressLint("UseRequireInsteadOfGet")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dbHandler = new DBHandler(getContext());
        dbHandler.open();
        Cursor cursor1 = dbHandler.getEmptList();

        if (cursor1 == null || cursor1.getCount() == 0) {
            root = inflater.inflate(R.layout.kalacak_yer_bos_fragment, container, false);
        } else {
            root = inflater.inflate(R.layout.kalacak_yer_fragment, container, false);

            ListvAdapter2 adapter1 = new ListvAdapter2(getContext(), cursor1, 0);

            ListView listView = root.findViewById(R.id.liste);
            listView.setAdapter(adapter1);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> listView, View view, int position, long id) {

                    Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                    ev_arkadasi_fragment.email_kontrol = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                    telefonnn1 = cursor.getString(cursor.getColumnIndexOrThrow("telefon"));
                    names1 = cursor.getString(cursor.getColumnIndexOrThrow("kullanici_adi"));
                    illl1 = cursor.getString(cursor.getColumnIndexOrThrow("il"));
                    illlce1 = cursor.getString(cursor.getColumnIndexOrThrow("ilce"));
                    kira2 = cursor.getString(cursor.getColumnIndexOrThrow("kira"));
                    detaylar2 = cursor.getString(cursor.getColumnIndexOrThrow("detay"));
                    konutturu2 = cursor.getString(cursor.getColumnIndexOrThrow("konutturu"));
                    image1 = cursor.getBlob(cursor.getColumnIndexOrThrow("profil_fotografi"));


                    Intent intent = new Intent(getContext(), ilan_ekrani.class);
                    startActivity(intent);
                }
            });
        }

        return root;
    }
}
