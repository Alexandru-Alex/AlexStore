package com.example.alexstore;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Lista_CuloriAdapter  extends RecyclerView.Adapter<Lista_CuloriAdapter.ViewHolder> {

    private final ArrayList<Culoare_filtru> culori;
    Context context;


    public Lista_CuloriAdapter(ArrayList<Culoare_filtru> culori) {
        this.culori = culori;
    }


    @NonNull
    @Override
    public Lista_CuloriAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.culoare_filtru_layout, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Lista_CuloriAdapter.ViewHolder holder, int position) {

        Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.button_selector);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(culori.get(position).getCuloare_code()));
        holder.culoare.setBackground(wrappedDrawable);

        holder.culoare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.culoare.setSelected(true);
                holder.culoare.setActivated(true);

               BDComunicare bdComunicare=new BDComunicare();

                bdComunicare.getFirestore().collection("Colectie_1").document("Haine").collection("Bluze").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(task.isSuccessful()) {
                            ArrayList<Produs> produse=new ArrayList<>();
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                Produs produs= documentSnapshot.toObject(Produs.class);
                                if(produs.getCuloare().equals(culori.get(position).getNume()))
                                produse.add(produs);
                            }

                           Lista_Produse lista_produse=new Lista_Produse();
                            lista_produse.produsFiltrat(produse);
                        }
                    }
                });

            }
        });


    }

    @Override
    public int getItemCount() {
        return culori.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        Button culoare;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            culoare=itemView.findViewById(R.id.culoare_background_filtru);

        }
    }
}
