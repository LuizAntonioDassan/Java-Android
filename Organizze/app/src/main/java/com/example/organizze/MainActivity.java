package com.example.organizze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.organizze.activity.Cadastrar_Activity;
import com.example.organizze.activity.LoginActivity;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class MainActivity extends IntroActivity {

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

    public void btEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));

    }
    public void btCadastrar(View view){
        startActivity(new Intent(this, Cadastrar_Activity.class));

    }

}