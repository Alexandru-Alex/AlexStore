package com.example.alexstore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URL;
import java.util.ArrayList;


public class ProduseAdapter extends RecyclerView.Adapter<ProduseAdapter.ViewHolder> {


    private final ArrayList<Produs> lista_produse;
    Context context;


    public ProduseAdapter(ArrayList<Produs> lista_produse) {
        this.lista_produse = lista_produse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.produs_layout, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        try {


            URL url = new URL(lista_produse.get(position).getPoza());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.poza_produs.setImageBitmap(bmp);
            holder.descriere.setText(lista_produse.get(position).getDescriere());
            holder.pret.setText(lista_produse.get(position).getPret());

        } catch (Exception e) {
            System.out.println(" AM INTRAT IN EXCEPTIEEEE");
        }

        holder.poza_produs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(" AM APASAT PE "+lista_produse.get(position).getDescriere());
                Context context=v.getContext();
                Intent intent=new Intent(context,Detalii_produs.class);
                intent.putExtra("produs",lista_produse.get(position));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return lista_produse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poza_produs;
        TextView pret, descriere;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poza_produs = itemView.findViewById(R.id.poza_produs);
            pret = itemView.findViewById(R.id.pret);
            descriere = itemView.findViewById(R.id.descriere);
        }
    }


}
