package com.example.registro.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.registro.R;
import com.example.registro.helper.RegistroDAO;
import com.example.registro.model.gasto;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarGasto extends AppCompatActivity {

    private TextInputEditText editGasto;
    private gasto gastoAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_gasto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemSalvar:
                RegistroDAO registroDAO = new RegistroDAO(getApplicationContext());
                String nomeGasto = editGasto.getText().toString();
                if(!nomeGasto.isEmpty()){
                    gasto gasto = new gasto();
                    gasto.setNomeGasto(nomeGasto);
                    registroDAO.salvar(gasto);
                    finish();
                }
        }

        return super.onOptionsItemSelected(item);
    }
}