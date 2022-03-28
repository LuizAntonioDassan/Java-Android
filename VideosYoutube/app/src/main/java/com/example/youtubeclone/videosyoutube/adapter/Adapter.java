package com.example.youtubeclone.videosyoutube.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeclone.videosyoutube.R;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista,parent,false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.filme.setText("Filme de teste");
        holder.ano.setText("2018");
        holder.genero.setText("Comédia");

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView filme;
        TextView genero;
        TextView ano;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            filme = itemView.findViewById(R.id.titulo);
            genero = itemView.findViewById(R.id.genero);
            ano = itemView.findViewById(R.id.ano);


        }
    }

}
