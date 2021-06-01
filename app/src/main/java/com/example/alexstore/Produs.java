package com.example.alexstore;

import java.io.Serializable;

public class Produs implements Serializable {

    private String culoare;
    private String poza;
    private String descriere;
    private String pret;

    public Produs()
    {

    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getPret() {
        return pret;
    }

    public void setPret(String pret) {
        this.pret = pret;
    }

    public Produs(String culoare, String poza) {
        this.culoare = culoare;
        this.poza = poza;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public String getPoza() {
        return poza;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }
}
