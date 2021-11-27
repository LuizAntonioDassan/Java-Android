package com.example.calculagorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText Valor;
    private TextView textGorjeta, textGasto, textPorcentagem;
    private SeekBar seekBarGorjeta;

    private double porcentagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Valor               = findViewById(R.id.valor);
        textGasto           = findViewById(R.id.textGasto);
        textGorjeta         = findViewById(R.id.textGorjeta);
        textPorcentagem     = findViewById(R.id.textPorcentagem);
        seekBarGorjeta      = findViewById(R.id.seekBarGorjeta);

        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                porcentagem = progress;
                textPorcentagem.setText(Math.round(porcentagem) + "%");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void calcular(){

        String valorRecuperado = Valor.getText().toString();
        if( valorRecuperado == null || valorRecuperado.equals("")){
            Toast.makeText(
                    getApplicationContext(),"Digite um valor", Toast.LENGTH_LONG
            ).show();
        }else{
            double valorGorjeta,valorDigitado,valorTotal;
            //recupera o valor digitado String -> Double
            valorDigitado = Double.parseDouble(valorRecuperado);
            //Aplica o valor digitado
            valorGorjeta = valorDigitado * (porcentagem/100);
            valorTotal = valorGorjeta + valorDigitado;
            //Mostra a gorjeta
            textGorjeta.setText("R$" + valorGorjeta);
            textGasto.setText("R$" + valorTotal);

        }

    }


}