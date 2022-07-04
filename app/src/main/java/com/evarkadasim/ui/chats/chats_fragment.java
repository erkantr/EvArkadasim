package com.evarkadasim.ui.chats;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.evarkadasim.DBHandler;
import com.evarkadasim.MessageActivity;
import com.evarkadasim.R;
import com.evarkadasim.model.ListvAdapter1;
import com.evarkadasim.model.ListvAdapter3;
import com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment;
import com.evarkadasim.ui.kalacak_yer.kalacak_yer_fragment;

import java.security.cert.CollectionCertStoreParameters;
import java.util.Collections;
import java.util.List;

import static com.evarkadasim.ilan_ekrani1.a;
import static com.evarkadasim.login_fragment.login_user_name;

public class chats_fragment extends Fragment {

    View root;
    DBHandler dbHandler;
    public static String mesaj_gonderen;
    public static byte[] gonderen_profil;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dbHandler = new DBHandler(getContext());
        dbHandler.open();
        a = 3;
        final Cursor cursor1 = dbHandler.getMessageFromSender(login_user_name);
        cursor1.moveToLast();
        if (cursor1 == null || cursor1.getCount() == 0) {
            root = inflater.inflate(R.layout.chats_bos_fragment, container, false);
        } else {
            root = inflater.inflate(R.layout.chats_fragment, container, false);

            ListvAdapter3 adapter = new ListvAdapter3(getContext(), cursor1, 10);

            ListView listView = root.findViewById(R.id.liste);
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Cursor cursor = (Cursor) parent.getItemAtPosition(position);

                    String gonderen = cursor.getString(cursor.getColumnIndexOrThrow("gonderen"));
                    Cursor cursor1 = dbHandler.GetUserFromName1(gonderen);
                    Cursor cursor2 = dbHandler.GetUserFromName2(gonderen);

                    if (cursor1 != null && cursor1.getCount() > 0) {
                        mesaj_gonderen = cursor1.getString(cursor1.getColumnIndexOrThrow("kullanici_adi"));
                        gonderen_profil = cursor1.getBlob(cursor1.getColumnIndexOrThrow("profil_fotografi"));
                    }
                    if (cursor2 != null && cursor2.getCount() > 0) {
                        mesaj_gonderen = cursor2.getString(cursor2.getColumnIndexOrThrow("kullanici_adi"));
                        gonderen_profil = cursor2.getBlob(cursor2.getColumnIndexOrThrow("profil_fotografi"));
                    }
                    Intent intent = new Intent(getContext(), MessageActivity.class);
                    startActivity(intent);
                }
            });
        }

        return root;
    }
}
