package com.example.jokenpo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SelecionaPedra(View view){
        this.ObterResultado("pedra");
    }
    public void SelecionaPapel(View view){
        this.ObterResultado("papel");
    }
    public void SelecionaTesoura(View view){
        this.ObterResultado("tesoura");
    }


    public void ObterResultado(String ObterResultado){

        ImageView imagemResultado = findViewById(R.id.resultadoApp);
        TextView textoResultado = findViewById(R.id.resultadoTexto);
        TextView vitorias = findViewById(R.id.vitorias);
        TextView derrotas = findViewById(R.id.derrotas);
        int vitoria = 0;
        int derrota = 0;
        int numero = new Random().nextInt(3);
        String pessoa[] = {"pedra","papel","tesoura"};
        String App = pessoa[numero];

        switch ( App ){
            case "pedra":
                imagemResultado.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imagemResultado.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imagemResultado.setImageResource(R.drawable.tesoura);
                break;
        }

        if(
                (App == "pedra" && ObterResultado == "tesoura") ||
                (App == "tesoura" && ObterResultado == "papel") ||
                (App == "papel" && ObterResultado == "pedra")
        ){ // App ganha
            textoResultado.setText("Voce Perdeu :v");

        }else if(
                (ObterResultado == "pedra" && App == "tesoura") ||
                (ObterResultado == "tesoura" && App == "papel") ||
                (ObterResultado == "papel" && App == "pedra")
        ){ // Pessoa ganha
            textoResultado.setText("Voce Ganhou :)");

        }else{ // Empate
            textoResultado.setText("## EMPATE ##");
        }
        System.out.println(" Item Selecionado foi " + numero);

    }

}