package com.example.organizze.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.organizze.R;
import com.example.organizze.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setButtonNextVisible(false);
        setButtonBackVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_1)
                .canGoBackward(false)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_3)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_4)
                .build()

        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_cadastro)
                .canGoBackward(false)
                .canGoForward(false)
                .build()

        );




        //setContentView(R.layout.activity_main);

        /*
        setButtonNextVisible(false);
        setButtonBackVisible(false);

        addSlide(new SimpleSlide.Builder()
                                    .title("Titulo")
                                    .description("Descricao")
                                    .image(R.drawable.um)
                                    .background(android.R.color.holo_orange_dark)
                                    .build()

        );
        addSlide(new SimpleSlide.Builder()
                                    .title("Titulo2")
                                    .description("Descricao2")
                                    .image(R.drawable.dois)
                                    .background(android.R.color.holo_orange_dark)
                                    .build()

        );
        addSlide(new SimpleSlide.Builder()
                                    .title("Titulo3")
                                    .description("Descricao3")
                                    .image(R.drawable.tres)
                                    .background(android.R.color.holo_orange_dark)
                                    .build()

        );
        addSlide(new SimpleSlide.Builder()
                                    .title("Titulo4")
                                    .description("Descricao4")
                                    .image(R.drawable.quatro)
                                    .background(android.R.color.holo_orange_dark)
                                    .build()

        );
        */
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    public void btEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));

    }
    public void btCadastrar(View view){
        startActivity(new Intent(this, Cadastrar_Activity.class));

    }

    public void verificarUsuarioLogado(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        //autenticacao.signOut();
        if( autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();
        }

    }

    public  void abrirTelaPrincipal(){
        startActivity(new Intent( this, PrincipalActivity.class));
    }

}