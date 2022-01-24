package com.example.registro.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registro.R;
import com.example.registro.model.gasto;

import java.util.List;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.MyViewHolder> {
    private List<gasto> listaDeGastos;

    public ListaAdapter(List<gasto> gastos) {
        this.listaDeGastos = gastos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.lista_gasto_adapter,parent,false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        gasto gasto = listaDeGastos.get(position);
        holder.gasto.setText(gasto.getNomeGasto());

    }


    @Override
    public int getItemCount() {
        return this.listaDeGastos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView gasto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            gasto = itemView.findViewById(R.id.textGasto);

        }
    }

}
