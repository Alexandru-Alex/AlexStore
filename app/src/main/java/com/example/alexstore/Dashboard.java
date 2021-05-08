package com.example.alexstore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Dashboard extends Fragment implements View.OnClickListener {

    private View view;
    private TextView user_email;
    private Button logout,informatii_personale,cumparaturi,returnari,wallet,ajutor,distribuie;
    private BDComunicare bdComunicare;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dashboard_activity, container, false);
        user_email = view.findViewById(R.id.informatii_email);
        logout=view.findViewById(R.id.logout);

        bdComunicare = new BDComunicare();
        bdComunicare.setEmail_Informatii(user_email);
        logout.setOnClickListener(this);


        return view;


    }

    @Override
    public void onClick(View v) {

        if(v==logout)
        {
            bdComunicare.signout();
            FragmentTransaction fragmentTransaction = getActivity()
                    .getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new Login());
            fragmentTransaction.commit();
            Toast.makeText(getActivity(), "Deconectare cu succes", Toast.LENGTH_LONG).show();

        }

    }
}
