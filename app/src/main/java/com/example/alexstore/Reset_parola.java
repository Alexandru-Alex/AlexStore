package com.example.alexstore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Reset_parola extends AppCompatActivity implements View.OnClickListener {


    private Button recuperare;
    private ProgressBar progressBar;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetati_parola_activity);
        recuperare = (Button) findViewById(R.id.resetare_parola);
        email = (TextView) findViewById(R.id.emaillogare);
        progressBar = (ProgressBar) findViewById(R.id.progressreset);


    }

    @Override
    public void onClick(View v) {

        String emailu = email.getText().toString().trim();
        progressBar.setVisibility(View.VISIBLE);

        if (!emailu.isEmpty() && verificaremail(emailu) == true) {
            BDComunicare bdComunicare = new BDComunicare();
            bdComunicare.inregistrare();
            bdComunicare.getAutentifica().sendPasswordResetEmail(emailu).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isComplete()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(Reset_parola.this, "Email-ul a fost trimis,daca se afla in baza de date", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Reset_parola.this, "Nu sa putut trimite email!", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(Reset_parola.this, "Furnizati un mail valid", Toast.LENGTH_SHORT).show();
        }

    }

    boolean verificaremail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
