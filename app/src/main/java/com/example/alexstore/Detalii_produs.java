package com.example.alexstore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class Detalii_produs  extends AppCompatActivity implements View.OnClickListener{


    ImageView imagine;
    TextView pret,descriere;
    Button adauga;
    Produs produs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produs_detalii);
        imagine=findViewById(R.id.imagine_produs);
        pret=findViewById(R.id.pret_produs);
        descriere=findViewById(R.id.descriere_produs);
        adauga=findViewById(R.id.adauga_produs);

        produs= (Produs) getIntent().getExtras().get("produs");
        pret.setText(produs.getPret()+" RON");
        descriere.setText(produs.getDescriere());
        Picasso.get().load(produs.getPoza()).into(imagine);
        adauga.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v==adauga)
        {
            BDComunicare bdComunicare=new BDComunicare();
            bdComunicare.adaugaCos(produs);
            Toast.makeText(Detalii_produs.this,"Produsul a fost adaugat in cos cu succes ",Toast.LENGTH_LONG).show();



        }

    }
}
