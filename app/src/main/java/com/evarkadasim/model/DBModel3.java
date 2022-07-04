package com.evarkadasim.model;

import android.graphics.Bitmap;

public class DBModel3 {
    private String kullanici_adi;
    private String email;
    private String sifre;
    private String telefon;
    private Bitmap profil_fotografi;

    public DBModel3(String kullanici_adi, String email, String sifre, String telefon, Bitmap profil_fotografi) {
        this.kullanici_adi = kullanici_adi;
        this.email = email;
        this.sifre = sifre;
        this.profil_fotografi = profil_fotografi;
        this.telefon = telefon;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Bitmap getProfil_fotografi() {
        return profil_fotografi;
    }

    public void setProfil_fotografi(Bitmap profil_fotografi) {
        this.profil_fotografi = profil_fotografi;
    }
}
