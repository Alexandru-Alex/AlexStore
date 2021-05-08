package com.example.alexstore;


import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class BDComunicare {

    private DatabaseReference reference;
    private FirebaseAuth autentifica;
    private FirebaseUser user;
    private FirebaseFirestore firestore;

    BDComunicare() {
        autentifica = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();
        firestore=FirebaseFirestore.getInstance();
    }

    public void inregistrare() {
        autentifica = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Utilizatori");
    }


    public void getPoze_main() {
        reference = FirebaseDatabase.getInstance().getReference().child("Poza_main");
    }
    public void getPoze_prezentare() {
        reference = FirebaseDatabase.getInstance().getReference().child("Poza_Prezentare");
    }
    public void getPoze_multiColor() {
        reference = FirebaseDatabase.getInstance().getReference().child("Poza_multiColor");
    }

    public boolean getUserUid() {

        if (autentifica.getCurrentUser() != null) {
            user = autentifica.getCurrentUser();

            return true;
        }
        return false;
    }

    public void setEmail_Informatii(TextView email) {
        autentifica = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Utilizatori").child(autentifica.getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                email.setText((String) snapshot.child("email").getValue());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void signout()
    {
        autentifica.signOut();
    }


    public FirebaseAuth getAutentifica() {
        return autentifica;
    }

    public DatabaseReference getReferinta() {
        return reference;
    }
    public FirebaseFirestore getFirestore() {return firestore;}


}
