package com.example.alexstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class Inregistrare extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private EditText email,parola,cparola,nume,prenume,adresa,oras,cod_postal,mobil;
    private BDComunicare comunicare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inregistrare_activity);
        btn=(Button)findViewById(R.id.salvarebutton);
        email=(EditText) findViewById(R.id.emaillogare);
        parola=(EditText) findViewById(R.id.parola_inregistrare);
        cparola=(EditText) findViewById(R.id.confirmare_parola);
        nume=(EditText) findViewById(R.id.nume_inregistrare);
        prenume=(EditText) findViewById(R.id.prenume_inregistrare);
        adresa=(EditText) findViewById(R.id.adresa_inregistrare);
        oras=(EditText) findViewById(R.id.orasul_inregistrare);
        cod_postal=(EditText) findViewById(R.id.codpostal_inregistrare);
        mobil=(EditText) findViewById(R.id.telefon_inregistrare);
        btn.setOnClickListener(this);
        comunicare=new BDComunicare();



    }

    @Override
    public void onClick(View v) {

        if(v==btn)
        {
            String nume = this.nume.getText().toString().trim();
            String prenume = this.prenume.getText().toString().trim();
            String email = this.email.getText().toString().trim();
            String parola = this.parola.getText().toString().trim();
            String cparola=this.cparola.getText().toString().trim();
            String adresa=this.adresa.getText().toString().trim();
            String oras=this.oras.getText().toString().trim();
            String mobil=this.mobil.getText().toString().trim();
            String cod_postal=this.cod_postal.getText().toString().trim();

            if(verificaempty(nume,prenume,email,parola,cparola,adresa,oras,mobil,cod_postal)==false)
            {
                Toast.makeText(this,"Toate campurile sunt obligatorii",Toast.LENGTH_LONG).show();
                return;
            }
            if(verificaremail(email)==false)
            {
                Toast.makeText(this,"Trebuie introdus un email valid",Toast.LENGTH_LONG).show();
                return;
            }
            if(parola.length()<6)
            {
                Toast.makeText(this,"Parola trebuie sa contina cel putin 6 caractere",Toast.LENGTH_LONG).show();
                return;
            }
            if(!parola.equals(cparola))
            {
                Toast.makeText(this,"Cele 2 parole nu se potrivesc",Toast.LENGTH_LONG).show();
                return;
            }
            comunicare.inregistrare();
            comunicare.getAutentifica().createUserWithEmailAndPassword(email,parola).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Utilizator utilizator=new Utilizator(nume,prenume,email,parola,cod_postal,adresa,oras,mobil);
                        comunicare.getReferinta().child(comunicare.getAutentifica().getUid()).setValue(utilizator);
                        Toast.makeText(Inregistrare.this,"Utilizator inregistrat cu succes",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Inregistrare.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(Inregistrare.this,"Email-ul este deja folosit la un alt cont",Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            });
        }
    }

    boolean verificaremail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    boolean verificaempty(String nume,String prenume,String email,String parola,String cparola,String adresa,String oras,String mobil,String cod_postal)
    {

        if(!nume.isEmpty()&!prenume.isEmpty()&!cparola.isEmpty()&!parola.isEmpty()&!adresa.isEmpty()&!email.isEmpty()&!oras.isEmpty()&!mobil.isEmpty()&!cod_postal.isEmpty())
        {
            return true;
        }
        return false;
    }
}
