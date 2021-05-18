package com.example.alexstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Lista_Produse extends AppCompatActivity implements View.OnClickListener {


    private TextView filtru;
    private BDComunicare bdComunicare;
    private RecyclerView.Adapter madapter;
    private RecyclerView lista_produse;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_produse_activity);
        lista_produse=findViewById(R.id.lista_produse);
        filtru=findViewById(R.id.filtru);
        bdComunicare=new BDComunicare();


        filtru.setOnClickListener(this);
        bdComunicare.getFirestore().collection("Colectie_1").document("Haine").collection("Bluze").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()) {
                    ArrayList<Produs> produse=new ArrayList<>();
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        Produs produs= documentSnapshot.toObject(Produs.class);
                        produse.add(produs);
                    }

                    madapter= new ProduseAdapter(produse);
                    lista_produse.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                    lista_produse.setAdapter(madapter);
                }
            }
        });




    }


    @Override
    public void onClick(View v) {
        if(v==filtru)
        {
            Intent intent=new Intent(this,Filtre.class);
            startActivity(intent);
            this.getFragmentManager().popBackStack();
        }
        }



}
