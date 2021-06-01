package com.example.alexstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MarimiAdapter  extends RecyclerView.Adapter<MarimiAdapter.ViewHolder> {


    private final ArrayList<String> marimi=new ArrayList<String>(Arrays.asList("32","34","37","39","40","XL", "L","M","S","XS","XXS"));
    Context context;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marimi_layout, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }



    @Override
    public int getItemCount() {
        return marimi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button marime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            marime=itemView.findViewById(R.id.marimea_button);

        }
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.marime.setText(marimi.get(position));
        holder.marime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"AI ALES MARIMEA: "+marimi.get(position),Toast.LENGTH_SHORT).show();

            }
        });







    }

}
