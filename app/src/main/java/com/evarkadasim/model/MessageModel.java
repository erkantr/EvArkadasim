package com.evarkadasim.model;


public class MessageModel {


    private String alici;
    private String gonderen;
    private String mesaj;

    public MessageModel(String alici, String gonderen, String mesaj) {

        this.gonderen = gonderen;
        this.alici = alici;
        this.mesaj = mesaj;
    }

    public String getAlici() {
        return alici;
    }

    public void setAlici(String alici) {
        this.alici = alici;
    }

    public String getGonderen() {
        return gonderen;
    }

    public void setGonderen(String gonderen) {
        this.gonderen = gonderen;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }
}
