package com.evarkadasim.model;

import android.graphics.Bitmap;

public class DBModel1 {
    private String kullanici_adi;
    private String email;
    private String sifre;
    private String telefon;
    private String il;
    private String ilce;
    private String kira;
    private String depozito;
    private String konutturu;
    private String odasalon;
    private String ozellikler;
    private String ulasim;
    private String evdetay;
    private String detay;
    private String latitude;
    private Bitmap profil_fotografi;
    private String longitude;

    public DBModel1(String kullanici_adi, String email, String sifre, String telefon, String il, String ilce, String kira, String depozito, String konutturu, String odasalon, String ozellikler, String ulasim, String evdetay, String detay, String latitude, Bitmap profil_fotografi, String longitude) {
        this.kullanici_adi = kullanici_adi;
        this.email = email;
        this.sifre = sifre;
        this.telefon = telefon;
        this.il = il;
        this.ilce = ilce;
        this.kira = kira;
        this.depozito = depozito;
        this.konutturu = konutturu;
        this.odasalon = odasalon;
        this.ozellikler = ozellikler;
        this.ulasim = ulasim;
        this.evdetay = evdetay;
        this.detay = detay;
        this.latitude = latitude;
        this.profil_fotografi = profil_fotografi;
        this.longitude = longitude;
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

    public String getDepozito() {
        return depozito;
    }

    public void setDepozito(String depozito) {
        this.depozito = depozito;
    }

    public String getKonutturu() {
        return konutturu;
    }

    public void setKonutturu(String konutturu) {
        this.konutturu = konutturu;
    }

    public String getOdasalon() {
        return odasalon;
    }

    public void setOdasalon(String odasalon) {
        this.odasalon = odasalon;
    }

    public String getOzellikler() {
        return ozellikler;
    }

    public void setOzellikler(String ozellikler) {
        this.ozellikler = ozellikler;
    }

    public String getUlasim() {
        return ulasim;
    }

    public void setUlasim(String ulasim) {
        this.ulasim = ulasim;
    }

    public String getEvdetay() {
        return evdetay;
    }

    public void setEvdetay(String evdetay) {
        this.evdetay = evdetay;
    }

    public String getDetay() {
        return detay;
    }

    public void setDetay(String detay) {
        this.detay = detay;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Bitmap getProfil_fotografi() {
        return profil_fotografi;
    }

    public void setProfil_fotografi(Bitmap profil_fotografi) {
        this.profil_fotografi = profil_fotografi;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}

