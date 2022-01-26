package com.example.organizze.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.organizze.R;
import com.example.organizze.helper.DateCustom;
import com.example.organizze.model.Movimentacao;
import com.google.android.material.textfield.TextInputEditText;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText campoData,campoCategoria,campoDescricao;
    private EditText campoValor;
    private Movimentacao movimentacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        campoData       = findViewById(R.id.editData);
        campoCategoria  = findViewById(R.id.editaCategoria);
        campoDescricao  = findViewById(R.id.editDescricao);
        campoValor      = findViewById(R.id.editValor);

        campoData.setText(DateCustom.dataAtual());

    }

    public void salvarDespesas(View view){

        String data = campoData.getText().toString();

        movimentacao = new Movimentacao();
        movimentacao.setValor(Double.parseDouble(campoValor.getText().toString()));
        movimentacao.setCategoria( campoCategoria.getText().toString());
        movimentacao.setDescricao( campoDescricao.getText().toString());
        movimentacao.setData(data);
        movimentacao.setTipo("d");
        movimentacao.salvar(data);
        finish();

    }

}