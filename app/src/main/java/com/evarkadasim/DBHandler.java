package com.evarkadasim;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.evarkadasim.model.DBModel1;
import com.evarkadasim.model.DBModel2;
import com.evarkadasim.model.DBModel3;
import com.evarkadasim.model.MessageModel;

import java.io.ByteArrayOutputStream;

public class DBHandler extends SQLiteOpenHelper {

    Context context;
    private DBHandler mDbHelper;
    private SQLiteDatabase mDb;
    private byte[] imageInBytes;
    private static final String TAG = "DBHandler";
    private static String DATABASE_NAME = "Database.db";
    private static int DATABASE_VERSION = 3;
    private static String createTable4 = "create table mesajlar (" +
            "_id integer PRIMARY KEY" + "," +
            "gonderen VARCHAR" + "," +
            "alici VARCHAR" + "," +
            "mesaj VARCHAR)";
    private static String createTable3 = "create table users (" +
            "_id integer PRIMARY KEY " + "," +
            "kullanici_adi VARCHAR" + "," +
            "email VARCHAR" + "," +
            "sifre VARCHAR" + "," +
            "telefon VARCHAR" + "," +
            "profil_fotografi BLOB)";
    private static String createTable2 = "create table kalacak_yer_ilan (" +
            "_id integer PRIMARY KEY " + "," +
            "kullanici_adi VARCHAR" + "," +
            "email VARCHAR" + "," +
            "sifre VARCHAR" + "," +
            "telefon VARCHAR" + "," +
            "il VARCHAR" + "," +
            "ilce VARCHAR" + "," +
            "kira INT" + "," +
            "detay VARCHAR" + "," +
            "konutturu VARCHAR" + "," +
            "profil_fotografi BLOB)";
    private static String createTable1 = "create table ev_arkadasi_ilan (" +
            "_id integer PRIMARY KEY " + "," +
            "kullanici_adi VARCHAR" + "," +
            "email VARCHAR" + "," +
            "sifre VARCHAR" + "," +
            "telefon VARCHAR" + "," +
            "il VARCHAR" + "," +
            "ilce VARCHAR" + "," +
            "kira INT" + "," +
            "depozito INT" + "," +
            "konutturu VARCHAR" + "," +
            "odasalon VARCHAR" + "," +
            "ozellikler VARCHAR" + "," +
            "ulasim VARCHAR" + "," +
            "evdetay VARCHAR" + "," +
            "detay VARCHAR" + "," +
            "latitude VARCHAR" + "," +
            "profil_fotografi BLOB" + "," +
            "longitude VARCHAR)";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL(createTable1);
            db.execSQL(createTable2);
            db.execSQL(createTable3);
            db.execSQL(createTable4);

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading application's database from version " + oldVersion
                + " to " + newVersion + ", which will destroy all old data!");
        db.execSQL("DROP TABLE IF EXISTS " + "kalacak_yer_ilan");
        db.execSQL("DROP TABLE IF EXISTS " + "ev_arkadasi_ilan");
        db.execSQL("DROP TABLE IF EXISTS " + "users");
        db.execSQL("DROP TABLE IF EXISTS " + "mesajlar");
        onCreate(db);

    }

    public DBHandler open() throws SQLException {
        mDbHelper = new DBHandler(context);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public Cursor getEmptList() {

        Cursor cursor = mDb.rawQuery("Select _id, kullanici_adi, email, sifre, telefon, il, ilce, kira, detay, konutturu, profil_fotografi from kalacak_yer_ilan", null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getEmptList1() {

        Cursor cursor = mDb.rawQuery("Select _id, kullanici_adi, email, sifre, telefon, il, ilce, kira, depozito, konutturu, odasalon, ozellikler, ulasim, evdetay, detay, latitude, profil_fotografi, longitude from ev_arkadasi_ilan", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getMessageFromSender(String giris) {

        Cursor cursor = mDb.query( "mesajlar", new String[]{"_id", "gonderen", "alici", "mesaj"},
                "alici" + " like '%" + giris + "%'",null,null,null,"_id" +" DESC");
        //Cursor mCursor = mDb.query("mesajlar", new String[] {"_id", "gonderen", "alici", "mesaj"},
          //      "alici" + " like '%" + giris + "%'"
            //    ,null,null,null, "gonderen" +" DESC");
        //Cursor cursor = mDb.rawQuery("SELECT * FROM " + "mesajlar" +" ORDER BY " + "_id" + " DESC",null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetchMessage() {

        Cursor cursor = mDb.query(true,"mesajlar", new String[]{"_id", "alici", "gonderen", "mesaj"},
                null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void storeMessage(MessageModel messageModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String gonderen = messageModel.getGonderen();
        String alici = messageModel.getAlici();
        String mesaj = messageModel.getMesaj();

        ContentValues contentValues = new ContentValues();

        contentValues.put("gonderen", gonderen);
        contentValues.put("alici", alici);
        contentValues.put("mesaj", mesaj);

        sqLiteDatabase.insert("mesajlar", null, contentValues);
    }

    public void store2(DBModel2 dbModel) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            Bitmap profil_fotografi = dbModel.getProfil_fotografi();
            String kullanici_adi = dbModel.getKullanici_adi();
            String email = dbModel.getEmail();
            String sifre = dbModel.getSifre();
            String telefon = dbModel.getTelefon();
            String il = dbModel.getIl();
            String ilce = dbModel.getIlce();
            String kira = dbModel.getKira();
            String detay = dbModel.getDetay();
            String konutturu = dbModel.getKonutturu();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            profil_fotografi.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            imageInBytes = byteArrayOutputStream.toByteArray();
            ContentValues contentValues = new ContentValues();

            contentValues.put("kullanici_adi", kullanici_adi);
            contentValues.put("email", email);
            contentValues.put("sifre", sifre);
            contentValues.put("telefon", telefon);
            contentValues.put("il", il);
            contentValues.put("ilce", ilce);
            contentValues.put("kira", kira);
            contentValues.put("detay", detay);
            contentValues.put("konutturu", konutturu);
            contentValues.put("profil_fotografi", imageInBytes);

            sqLiteDatabase.insert("kalacak_yer_ilan", null, contentValues);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void storeUser(DBModel3 dbModel) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            Bitmap profil_fotografi = dbModel.getProfil_fotografi();
            String kullanici_adi = dbModel.getKullanici_adi();
            String email = dbModel.getEmail();
            String sifre = dbModel.getSifre();
            String telefon = dbModel.getTelefon();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            profil_fotografi.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            imageInBytes = byteArrayOutputStream.toByteArray();
            ContentValues contentValues = new ContentValues();

            contentValues.put("kullanici_adi", kullanici_adi);
            contentValues.put("email", email);
            contentValues.put("sifre", sifre);
            contentValues.put("telefon", telefon);
            contentValues.put("profil_fotografi", imageInBytes);

            sqLiteDatabase.insert("users", null, contentValues);

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void store1(DBModel1 dbModel) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            Bitmap profil_fotografi = dbModel.getProfil_fotografi();
            String kullanici_adi = dbModel.getKullanici_adi();
            String email = dbModel.getEmail();
            String sifre = dbModel.getSifre();
            String telefon = dbModel.getTelefon();
            String il = dbModel.getIl();
            String ilce = dbModel.getIlce();
            String kira = dbModel.getKira();
            String depozito = dbModel.getDepozito();
            String konutturu = dbModel.getKonutturu();
            String odasalon = dbModel.getOdasalon();
            String ozellikler = dbModel.getOzellikler();
            String ulasim = dbModel.getUlasim();
            String evdetay = dbModel.getEvdetay();
            String detay = dbModel.getDetay();
            String latitude = dbModel.getLatitude();
            String longitude = dbModel.getLongitude();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            profil_fotografi.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            imageInBytes = byteArrayOutputStream.toByteArray();
            ContentValues contentValues = new ContentValues();

            contentValues.put("kullanici_adi", kullanici_adi);
            contentValues.put("email", email);
            contentValues.put("sifre", sifre);
            contentValues.put("telefon", telefon);
            contentValues.put("il", il);
            contentValues.put("ilce", ilce);
            contentValues.put("kira", kira);
            contentValues.put("depozito", depozito);
            contentValues.put("konutturu", konutturu);
            contentValues.put("odasalon", odasalon);
            contentValues.put("ozellikler", ozellikler);
            contentValues.put("ulasim", ulasim);
            contentValues.put("evdetay", evdetay);
            contentValues.put("detay", detay);
            contentValues.put("latitude", latitude);
            contentValues.put("profil_fotografi", imageInBytes);
            contentValues.put("longitude", longitude);

            sqLiteDatabase.insert("ev_arkadasi_ilan", null, contentValues);

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public Cursor GetUser1(String giris) {
        Cursor cursor = mDb.query(true, "kalacak_yer_ilan", new String[]{"_id", "kullanici_adi", "email", "sifre", "telefon",
                        "il", "ilce", "kira", "detay", "konutturu", "profil_fotografi"},
                "email" + " like '%" + giris + "%'", null,
                null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor GetUserFromName1(String giris) {
        Cursor cursor = mDb.query(true, "kalacak_yer_ilan", new String[]{"_id", "kullanici_adi", "email", "sifre", "telefon",
                        "il", "ilce", "kira", "detay", "konutturu", "profil_fotografi"},
                "kullanici_adi" + " like '%" + giris + "%'", null,
                null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor GetUserFromName2(String giris) {
        Cursor cursor = mDb.query(true, "ev_arkadasi_ilan", new String[]{"_id", "kullanici_adi", "email", "sifre", "telefon",
                        "il", "ilce", "kira", "depozito", "konutturu", "odasalon", "ozellikler", "ulasim", "evdetay", "detay", "latitude", "profil_fotografi", "longitude"},
                "kullanici_adi" + " like '%" + giris + "%'", null,
                null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor GetUser3(String giris) {
        Cursor cursor = mDb.query(true, "users", new String[]{"_id", "kullanici_adi", "email", "sifre", "telefon", "profil_fotografi"},
                "email" + " like '%" + giris + "%'", null,
                null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor GetUser2(String giris) {
        Cursor cursor = mDb.query(true, "ev_arkadasi_ilan", new String[]{"_id", "kullanici_adi", "email", "sifre", "telefon",
                        "il", "ilce", "kira", "depozito", "konutturu", "odasalon", "ozellikler", "ulasim", "evdetay", "detay", "latitude", "profil_fotografi", "longitude"},
                "email" + " like '%" + giris + "%'", null,
                null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void editUser(int _id, String kullanici_adi, String email, String sifre, String telefon, Bitmap profil_fotografi) {
        ContentValues contentValues = new ContentValues();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        profil_fotografi.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imageInBytes = byteArrayOutputStream.toByteArray();

        contentValues.put("kullanici_adi", kullanici_adi);
        contentValues.put("email", email);
        contentValues.put("sifre", sifre);
        contentValues.put("telefon", telefon);
        contentValues.put("profil_fotografi", imageInBytes);

        mDb.update("users", contentValues, "_id" + " = ?",
                new String[]{String.valueOf(_id)});
    }

    public void edit1(int _id, String kullanici_adi, String email, String sifre, String telefon, Bitmap profil_fotografi) {
        ContentValues contentValues = new ContentValues();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        profil_fotografi.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imageInBytes = byteArrayOutputStream.toByteArray();

        contentValues.put("kullanici_adi", kullanici_adi);
        contentValues.put("email", email);
        contentValues.put("sifre", sifre);
        contentValues.put("telefon", telefon);
        contentValues.put("profil_fotografi", imageInBytes);

        mDb.update("kalacak_yer_ilan", contentValues, "_id" + " = ?",
                new String[]{String.valueOf(_id)});

    }

    public void edit2(int _id, String kullanici_adi, String email, String sifre, String telefon, Bitmap profil_fotografi) {
        ContentValues contentValues = new ContentValues();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        profil_fotografi.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imageInBytes = byteArrayOutputStream.toByteArray();

        contentValues.put("kullanici_adi", kullanici_adi);
        contentValues.put("email", email);
        contentValues.put("sifre", sifre);
        contentValues.put("telefon", telefon);
        contentValues.put("profil_fotografi", imageInBytes);

        mDb.update("ev_arkadasi_ilan", contentValues, "_id" + " = ?",
                new String[]{String.valueOf(_id)});

    }

    public void edit3(int _id, String il, String ilce, String kira, String detay, String konutturu) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("il", il);
        contentValues.put("ilce", ilce);
        contentValues.put("kira", kira);
        contentValues.put("detay", detay);
        contentValues.put("konutturu", konutturu);

        mDb.update("kalacak_yer_ilan", contentValues, "_id" + " = ?",
                new String[]{String.valueOf(_id)});

    }

    public void edit4(int _id, String il, String ilce, String kira, String depozito, String konutturu, String ozellikler, String ulasim, String evdetay, String detay, String odasalon) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("il", il);
        contentValues.put("ilce", ilce);
        contentValues.put("kira", kira);
        contentValues.put("depozito", depozito);
        contentValues.put("konutturu", konutturu);
        contentValues.put("ozellikler", ozellikler);
        contentValues.put("odasalon", odasalon);
        contentValues.put("ulasim", ulasim);
        contentValues.put("evdetay", evdetay);
        contentValues.put("detay", detay);

        mDb.update("ev_arkadasi_ilan", contentValues, "_id" + " = ?",
                new String[]{String.valueOf(_id)});

    }

    public boolean deleteRow(String email) {
        return mDb.delete("kalacak_yer_ilan", "email" + "='" + email + "' ;", null) > 0;
    }

    public boolean deleteRow1(String email) {
        return mDb.delete("ev_arkadasi_ilan", "email" + "='" + email + "' ;", null) > 0;
    }

    public Boolean checkEmail(String email) {
        Cursor cursor;
        Cursor cursor1;
        cursor = mDb.rawQuery("Select * from ev_arkadasi_ilan where email = ?", new String[]{email});
        cursor1 = mDb.rawQuery("Select * from kalacak_yer_ilan where  email = ?", new String[]{email});
        if (cursor.getCount() > 0 || cursor1.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUserName(String kullanici_adi) {
        Cursor cursor;
        Cursor cursor1;
        cursor = mDb.rawQuery("Select * from ev_arkadasi_ilan where kullanici_adi = ?", new String[]{kullanici_adi});
        cursor1 = mDb.rawQuery("Select * from kalacak_yer_ilan where  kullanici_adi = ?", new String[]{kullanici_adi});
        if (cursor.getCount() > 0 || cursor1.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkEmailandPassword(String email, String sifre) {
        Cursor cursor = mDb.rawQuery("Select * from users where email = ? and sifre = ? ", new String[]{email, sifre});
        if (cursor.getCount() > 0) {

            return true;
        } else {
            return false;
        }

    }

    public Boolean ilanKontrol1(String email, String sifre) {
        Cursor cursor1;
        cursor1 = mDb.rawQuery("Select * from ev_arkadasi_ilan where email = ? and sifre = ?  ", new String[]{email, sifre});
        if (cursor1.getCount() > 0) {

            return true;
        } else {
            return false;
        }

    }

    public Boolean ilanKontrol2(String email, String sifre) {

        Cursor cursor2;
        cursor2 = mDb.rawQuery("Select * from kalacak_yer_ilan where  email = ? and sifre = ?", new String[]{email, sifre});
        if (cursor2.getCount() > 0) {

            return true;
        } else {
            return false;
        }

    }
}
