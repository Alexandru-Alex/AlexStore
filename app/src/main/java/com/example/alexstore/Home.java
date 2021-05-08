package com.example.alexstore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Home extends Fragment implements View.OnClickListener {

    private View view;
    private BDComunicare comunicare;
    private ViewFlipper flipper;
    private ImageButton dreapta, stanga;
    private String[] poze;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.home_activity, container, false);
        flipper = (ViewFlipper) view.findViewById(R.id.flipper);
        dreapta = (ImageButton) view.findViewById(R.id.sageata_dreapta);
        stanga = (ImageButton) view.findViewById(R.id.sageata_stanga);
        comunicare = new BDComunicare();
        comunicare.getPoze_main();
        int index = 0;
        comunicare.getReferinta().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                poze = new String[(int) snapshot.getChildrenCount()];
                for (DataSnapshot ds : snapshot.getChildren()) {
                    poze[index] = ds.getValue(String.class);
                    setImageInFlipr(poze[index]);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        stanga.setOnClickListener(this);
        dreapta.setOnClickListener(this);
        flipper.setOnClickListener(this);


        return view;


    }


    @Override
    public void onClick(View v) {

        if(v==stanga)
        {
            flipper.showPrevious();
        }
        if(v==dreapta)
        {
            flipper.showNext();
        }
        if(v==flipper)
        {
            Fragment select = new Search();
            BottomNavigationView botNav=getActivity().findViewById(R.id.bottom_nav);
            botNav.setSelectedItemId(R.id.search);
            loadFragment(select);
        }


    }


    private void setImageInFlipr(String imgUrl) {

        ImageView image = new ImageView(getContext());
        Picasso.get().load(imgUrl).resize(800, 1200).into(image);
        flipper.addView(image);
    }
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


}
