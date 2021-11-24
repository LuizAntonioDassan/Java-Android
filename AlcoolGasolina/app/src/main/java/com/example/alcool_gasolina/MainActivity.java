package com.example.alcool_gasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText valorAlcool, valorGasolina;
    private TextView textoResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valorAlcool     = findViewById(R.id.textoAlcool);
        valorGasolina   = findViewById(R.id.textoGasolina);
        textoResultado  = findViewById(R.id.resultado);
    }

    public void calcular(View view){
        // recuperando o valor digitado no input
        String precoAlcool      = valorAlcool.getText().toString();
        String precoGasolina    = valorGasolina.getText().toString();

        // fazendo a verificação do preço digitado
        Boolean validar = validarCampo(precoAlcool,precoGasolina);
        if(validar){ //validando as entradas, sera efetuado o calculo
            Double valorAlcool = Double.parseDouble(precoAlcool);
            Double valorGasolina = Double.parseDouble(precoGasolina);
            /*
                Calculo utilizado -> (valorAlcool/valorGasolina) >= 0.7 melhor usar gasolina
             */
            Double resultado = valorAlcool/valorGasolina;
            if(resultado >= 0.7){
                textoResultado.setText("Melhor usar Gasolina");
            }else{
                textoResultado.setText("Melhor usar Álcool");
            }
        }else{
            textoResultado.setText("Preencha todos os campos corretamente");
        }

    }

    public Boolean validarCampo(String alcool, String gasolina){
        Boolean campo = true;
        //compara se as caixas de texto estão vazia, ou por algum motivo não existe
        if( alcool == null || alcool.equals("")){
            campo = false;
        }else if( gasolina == null || gasolina.equals("")){
            campo = false;
        }

        return campo;
    }

}