package com.example.requisioapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.requisioapi.api.CEPService;
import com.example.requisioapi.api.DataService;
import com.example.requisioapi.model.CEP;
import com.example.requisioapi.model.Fotos;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button btnRecuperar;
    private TextView texto;
    private EditText cepText;
    private Retrofit retrofit;
    private List<Fotos> listaFotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnRecuperar = findViewById(R.id.btnRecuperar);
        texto = findViewById(R.id.textoDados);
        cepText = findViewById(R.id.editCep);

        //captura a URL e converte o JSON
        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Usando a biblioteca Retrofit
                //recuperarListaRetrofit();
                //Log.d("teste", "ok");


                if(!cepText.equals("") && cepText.length() == 8){
                    recuperarCepRetrofit();
                }else{
                    Toast.makeText(MainActivity.this, "Insira um Cep valido", Toast.LENGTH_SHORT).show();
                }


                //Recuperando API direto do JSON


                    /*if(!cep.equals("") && cep.length() == 8){
                        MyTasks myTasks = new MyTasks();
                        //String lugar = cep.getText().toString();
                        String urlApi = "https://blockchain.info/ticker";
                        String urlCep = "https://viacep.com.br/ws/"+cep.getText().toString()+"/json/";
                        myTasks.execute(urlApi);
                    }else{
                        Toast.makeText(MainActivity.this, "Insira um Cep valido", Toast.LENGTH_SHORT).show();
                    }*/
            }
        });
    }

    private void recuperarListaRetrofit() {

        DataService dataService = retrofit.create(DataService.class);
        Call<List<Fotos>> call = dataService.recuperarFotos();

        call.enqueue(new Callback<List<Fotos>>() {
            @Override
            public void onResponse(Call<List<Fotos>> call, Response<List<Fotos>> response) {
                if(response.isSuccessful()){
                    listaFotos = response.body();
                    texto.setText(listaFotos.size());
                    for(int i=0; i<listaFotos.size(); i++){
                        Fotos foto = listaFotos.get(i);
                        Log.d("resultado","resultado: " + foto.getId() + " / " + foto.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Fotos>> call, Throwable t) {

            }
        });

    }

        private void recuperarCepRetrofit(){
        CEPService cepService = retrofit.create(CEPService.class);
        Call<CEP> call = cepService.recuperarCep(cepText.getText().toString());

        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if(response.isSuccessful()){
                    CEP cep = response.body();
                    texto.setText(cep.getCep()+ " / "+ cep.getBairro());
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });

    }

    class MyTasks extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String stringUrl = strings[0];
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader reader = null;
            StringBuffer buffer = null;
            String erro = "Erro Desconhecido";

            try {
                URL url = new URL(stringUrl);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                //recupera os dados em Bytes
                inputStream = conexao.getInputStream();
                //Lê os dados em Bytes e decodifica para caracteres
                inputStreamReader = new InputStreamReader(inputStream);
                //Objeto utilizado para leitura dos caracteres do InputStreamReader
                reader = new BufferedReader(inputStreamReader);
                buffer = new StringBuffer();
                String linha = "";
                while( (linha = reader.readLine()) != null ){
                    buffer.append(linha);
                }


            } catch (MalformedURLException e) {
                Toast.makeText(MainActivity.this, erro, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, erro, Toast.LENGTH_SHORT).show();
            } catch (Exception e){
                e.printStackTrace();
            }

            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String bairro = null;
            String cep = null;
            String logradouro = null;
            String complemento = null;
            String localidade = null;
            String uf = null;

            /*String moeda = null;
            String valorMoeda = null;
            String simbolo = null;*/

            try {
                JSONObject jsonObject = new JSONObject(s);
                //  Objeto Simples
                bairro = jsonObject.getString("bairro");
                cep = jsonObject.getString("cep");
                logradouro = jsonObject.getString("logradouro");
                complemento = jsonObject.getString("complemento");
                localidade = jsonObject.getString("localidade");
                uf = jsonObject.getString("uf");


                /*//Objeto dentro de Objeto JSON
                moeda = jsonObject.getString("BRL");
                //ObjetoInterno
                JSONObject jsonObjectValor = new JSONObject(moeda);
                valorMoeda = jsonObjectValor.getString("last");
                simbolo = jsonObjectValor.getString("symbol");*/

            } catch (JSONException e) {
                e.printStackTrace();
            }

            texto.setText(bairro + " / " + cep + " / " + logradouro + " / " + complemento
                    + " / " + localidade + " / " + uf);

            //texto.setText(simbolo + " : " + valorMoeda);
        }
    }

}