package com.example.alexstore;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Filtre extends AppCompatActivity implements View.OnClickListener {

    private SeekBar pretfiltrubar;
    private TextView pret;
    private RecyclerView.Adapter madapter,marimeadapter;
    private RecyclerView lista_culori,lista_marimi;
    private Button pret_button,culoare_button,marimi_button,salvati_filtre;

    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.filtre_layout);
        setTitle("Filtre");
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        getWindow().setLayout((int) (w * .9), (int) (h * .8));
        pretfiltrubar = findViewById(R.id.pretfiltrubar);
        pret = findViewById(R.id.pret);
        lista_culori=findViewById(R.id.lista_culori);
        lista_marimi=findViewById(R.id.lista_marimi);
        salvati_filtre=findViewById(R.id.salvati_filtre);
        pret.setVisibility(View.GONE);
        pretfiltrubar.setVisibility(View.GONE);
        pret_button = findViewById(R.id.pret_button);
        culoare_button=findViewById(R.id.culoare_button);
        marimi_button=findViewById(R.id.marimi_button);
        pret_button.setOnClickListener(this);
        culoare_button.setOnClickListener(this);
        marimi_button.setOnClickListener(this);
        salvati_filtre.setOnClickListener(this);
        pretfiltrubar.setOnSeekBarChangeListener(seekBarChangeListener);
        pret.setText("Pret: " + pretfiltrubar.getProgress() + " RON");


        ArrayList<Culoare_filtru> culori=new ArrayList<>();
        culori.add(new Culoare_filtru("rosu","#DC0000"));
        culori.add(new Culoare_filtru("albastru","#0000FF"));
        culori.add(new Culoare_filtru("verde","#07DC00"));
        culori.add(new Culoare_filtru("mov","#6700DC"));
        culori.add(new Culoare_filtru("alb","#FFFFFF"));
        culori.add(new Culoare_filtru("negru","#000000"));


        madapter= new Lista_CuloriAdapter(culori);
        lista_culori.setLayoutManager(new GridLayoutManager(getApplicationContext(),8));
        lista_culori.setAdapter(madapter);
        lista_culori.setVisibility(View.GONE);
        marimeadapter=new MarimiAdapter();
        lista_marimi.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));
        lista_marimi.setAdapter(marimeadapter);
        lista_marimi.setVisibility(View.GONE);


    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            pret.setText("Pret: " + progress + " RON");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    @Override
    public void onClick(View v) {
        if (v == pret_button) {
            if (pretfiltrubar.getVisibility() == v.VISIBLE || pret.getVisibility() == v.VISIBLE) {
                pretfiltrubar.setVisibility(v.GONE);
                pret.setVisibility(v.GONE);
                pret_button.setBackgroundResource(R.drawable.down_arrow);
            }
            else
            {

                pretfiltrubar.setVisibility(v.VISIBLE);
                pret.setVisibility(v.VISIBLE);
                pret_button.setBackgroundResource(R.drawable.up_arrow);
            }
        }
        if (v == culoare_button) {
            if (lista_culori.getVisibility() == v.VISIBLE) {
                lista_culori.setVisibility(v.GONE);
                culoare_button.setBackgroundResource(R.drawable.down_arrow);
            }
            else
            {

                lista_culori.setVisibility(v.VISIBLE);
                culoare_button.setBackgroundResource(R.drawable.up_arrow);
            }
        }
        if(v== marimi_button)
        {
            if (lista_marimi.getVisibility() == v.VISIBLE) {
                lista_marimi.setVisibility(v.GONE);
                culoare_button.setBackgroundResource(R.drawable.down_arrow);
            }
            else
            {

                lista_marimi.setVisibility(v.VISIBLE);
                culoare_button.setBackgroundResource(R.drawable.up_arrow);
            }
        }
    }
}
