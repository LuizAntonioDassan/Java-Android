package com.example.caraoucoroa;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class resultado extends AppCompatActivity {

    private ImageView imageResultado,imageVoltar;
    private View resultadoSnack;
    private MediaPlayer coinFlip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        imageResultado  = findViewById(R.id.imageResultado);
        imageVoltar     = findViewById(R.id.imageVoltar);
        resultadoSnack  = findViewById(R.id.resultadoLayout);
        coinFlip        = MediaPlayer.create(getApplicationContext(), R.raw.coinflip);

        if( coinFlip != null){
            coinFlip.start();
        }


        int numero = new Random().nextInt(2);

        if(numero == 0){ //cara
            imageResultado.setImageResource(R.drawable.moeda_cara);
            Snackbar.make(
                    resultadoSnack,
                    "Cara",
                    Snackbar.LENGTH_LONG
            ).setAction( "Confirmar", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( coinFlip.isPlaying()){
                        coinFlip.pause();
                    }
                    finish();
                }
            }).show();
        }else{ //coroa
            imageResultado.setImageResource(R.drawable.moeda_coroa);
            Snackbar.make(
                    resultadoSnack,
                    "Coroa",
                    Snackbar.LENGTH_LONG
            ).setAction( "Confirmar",
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if( coinFlip.isPlaying()){
                                coinFlip.pause();
                            }
                            finish();
                        }
                    }).show();
        }

        imageVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( coinFlip.isPlaying()){
                    coinFlip.pause();
                }
                finish();
            }
        });

    }
}