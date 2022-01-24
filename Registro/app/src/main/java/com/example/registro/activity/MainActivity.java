package com.example.registro.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.registro.R;
import com.example.registro.adapter.ListaAdapter;
import com.example.registro.helper.RecyclerItemClickListener;
import com.example.registro.helper.RegistroDAO;
import com.example.registro.helper.dbhelper;
import com.example.registro.model.gasto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton adicionar;
    private RecyclerView lista;
    private ListaAdapter listaAdapter;
    private List<gasto> listaDeGastos = new ArrayList<>();
    private dbhelper db = new dbhelper(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        adicionar   = findViewById(R.id.fab);
        lista       = findViewById(R.id.lista);
        //evento de clique
        lista.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        lista,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Log.i()
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                //Log.i()
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Log.i()
                            }
                        }
                )


        );

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdicionarGasto.class);
                startActivity(intent);
            }
        });


    }
    public void carregarListaDeGastos(){
        //lista
        RegistroDAO registroDAO = new RegistroDAO(getApplicationContext());
        listaDeGastos = registroDAO.gastar();
        // exibe a lista
        System.out.println(listaDeGastos);
        //configura um adapter
        listaAdapter = new ListaAdapter(listaDeGastos);
        //configurar recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext());
        lista.setLayoutManager( layoutManager);
        lista.setHasFixedSize(true);
        lista.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        lista.setAdapter(listaAdapter);

    }

    @Override
    protected void onStart() {
        carregarListaDeGastos();
        super.onStart();
    }
}