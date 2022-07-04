package com.evarkadasim.model;

import android.graphics.Bitmap;

public class DBModel2 {
    private String kullanici_adi;
    private String email;
    private String sifre;
    private String telefon;
    private String il;
    private String ilce;
    private String kira;
    private String detay;
    private String konutturu;
    private Bitmap profil_fotografi;

    public DBModel2(String kullanici_adi, String email, String sifre, String telefon, String il, String ilce, String kira, String detay, String konutturu, Bitmap profil_fotografi) {
        this.kullanici_adi = kullanici_adi;
        this.email = email;
        this.sifre = sifre;
        this.telefon = telefon;
        this.il = il;
        this.ilce = ilce;
        this.kira = kira;
        this.detay = detay;
        this.konutturu = konutturu;
        this.profil_fotografi = profil_fotografi;
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

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getIlce() {
        return ilce;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }

    public String getKira() {
        return kira;
    }

    public void setKira(String kira) {
        this.kira = kira;
    }

    public String getDetay() {
        return detay;
    }

    public void setDetay(String detay) {
        this.detay = detay;
    }

    public String getKonutturu() {
        return konutturu;
    }

    public void setKonutturu(String konutturu) {
        this.konutturu = konutturu;
    }

    public Bitmap getProfil_fotografi() {
        return profil_fotografi;
    }

    public void setProfil_fotografi(Bitmap profil_fotografi) {
        this.profil_fotografi = profil_fotografi;
    }
}
