package com.evarkadasim.model;


import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.evarkadasim.R;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.mesajlasma_durumu;


public class ListvAdapter2 extends CursorAdapter {

    public ListvAdapter2(Context context, Cursor cursor, int flag) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.ilanlar_item, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String kullanici_adi = cursor.getString(cursor.getColumnIndexOrThrow("kullanici_adi"));
        String il = cursor.getString(cursor.getColumnIndexOrThrow("il"));
        String ilce = cursor.getString(cursor.getColumnIndexOrThrow("ilce"));
        String kira = cursor.getString(cursor.getColumnIndexOrThrow("kira"));
        byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow("profil_fotografi"));
        CircleImageView profil_fotografi = view.findViewById(R.id.imageViewUserPicture);

        if (image != null) {
            if (image.length > 3) {


                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                Bitmap profilfoto = BitmapFactory.decodeByteArray(image, 0, image.length);


                profil_fotografi.setImageBitmap(profilfoto);

            } else {
                profil_fotografi.setImageResource(R.drawable.ic_account_circle_black_24dp);
            }
        }
        TextView k_adi = view.findViewById(R.id.username);
        k_adi.setText(kullanici_adi);


        TextView bilgi = view.findViewById(R.id.bilgi);
        bilgi.setText(kullanici_adi + " " + il + " Kalacak Yer Arıyor");
        TextView konum = view.findViewById(R.id.konum);
        konum.setText(il + "-" + ilce);
        TextView k_kira = view.findViewById(R.id.para);
        k_kira.setText(kira);

    }
}

