package com.example.alexstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class Login extends Fragment implements View.OnClickListener {

    private View view;
    private Button reset_parola, login_button;
    private EditText email, parola;
    private TextView inregistrare;
    private BDComunicare comunicare;
    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.login_activity, container, false);
        inregistrare = view.findViewById(R.id.inregistrare_buton);
        email = (EditText) view.findViewById(R.id.emaillogare);
        parola = (EditText) view.findViewById(R.id.parolalogare);
        reset_parola = (Button) view.findViewById(R.id.resetare_parola);
        login_button = (Button) view.findViewById(R.id.logarebutton);
        progressBar = (ProgressBar) view.findViewById(R.id.progresslogin);
        progressBar.setVisibility(View.GONE);
        reset_parola.setOnClickListener(this);
        inregistrare.setOnClickListener(this);
        login_button.setOnClickListener(this);
        comunicare = new BDComunicare();


        return view;


    }

    @Override
    public void onClick(View v) {

        if (v == inregistrare) {
            Intent intent = new Intent(getActivity(), Inregistrare.class);
            startActivity(intent);
            getActivity().getFragmentManager().popBackStack();

        }
        if (v == reset_parola) {

            Intent intent = new Intent(getActivity(), Reset_parola.class);
            startActivity(intent);
            getActivity().getFragmentManager().popBackStack();
        }

        if (v == login_button) {
            progressBar.setVisibility(View.VISIBLE);

            if (!email.getText().toString().trim().isEmpty() && !parola.getText().toString().trim().isEmpty()) {
                comunicare.inregistrare();
                comunicare.getAutentifica().signInWithEmailAndPassword(email.getText().toString().trim(), parola.getText().toString().trim()).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "Login cu succes", Toast.LENGTH_LONG).show();

                            if (getFragmentManager() != null) {
                                //getActivity().getFragmentManager().popBackStack();
                                //getActivity().finish();
                                FragmentTransaction fragmentTransaction = getActivity()
                                        .getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.fragment_container, new Dashboard());
                                fragmentTransaction.commit();

                            }


                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "Datele sunt gresite", Toast.LENGTH_LONG).show();
                        }
                    }
                });


            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Completati toate campurile", Toast.LENGTH_LONG).show();
            }

        }


    }

}
