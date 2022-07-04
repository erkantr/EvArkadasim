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

import com.evarkadasim.R;

import java.util.Collections;

public class ListvAdapter3 extends CursorAdapter {
    public ListvAdapter3(Context context, Cursor c, int flag) {
        super(context, c, 10);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.chats_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String gonderen = cursor.getString(cursor.getColumnIndexOrThrow("gonderen"));
        String mesaj = cursor.getString(cursor.getColumnIndexOrThrow("mesaj"));
        //cursor.moveToLast();
        TextView k_adi = view.findViewById(R.id.username);
        k_adi.setText(gonderen);

        TextView mesaj_tv = view.findViewById(R.id.mesaj);
        mesaj_tv.setText(mesaj);

    }
}
