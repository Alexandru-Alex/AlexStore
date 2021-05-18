package com.example.alexstore;

public class Culoare_filtru {

    private String nume;
    private String culoare_code;


    Culoare_filtru(String nume,String culoare_code)
    {
        this.nume=nume;
        this.culoare_code=culoare_code;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCuloare_code() {
        return culoare_code;
    }

    public void setCuloare_code(String culoare_code) {
        this.culoare_code = culoare_code;
    }
}
