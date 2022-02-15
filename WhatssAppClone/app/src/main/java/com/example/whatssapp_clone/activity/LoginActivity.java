package com.example.whatssapp_clone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.whatssapp_clone.R;
import com.example.whatssapp_clone.config.ConfiguracaoFirebase;
import com.example.whatssapp_clone.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail,campoSenha;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();

        campoEmail = findViewById(R.id.editEmailLogin);
        campoSenha = findViewById(R.id.editSenhaLogin);

    }

    public void abrirTelaCadastro(View view){
        startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
    }

    public void logar(Usuario usuario){

        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    abrirTelaPrincipal();
                }else{
                    if( task.isSuccessful() ){
                        Toast.makeText(LoginActivity.this,
                                "Sucesso ao cadastrar",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }else{

                        String excecao = "";
                        try {
                            throw task.getException();
                        }catch (FirebaseAuthInvalidUserException e){
                            excecao = "Usuario nao cadastrado";
                        }catch (FirebaseAuthInvalidCredentialsException e){
                            excecao = "Email ou Senha incorretos";
                        }catch (Exception e){
                            excecao = "Erro ao cadastrar usuario" + e.getMessage();
                            e.printStackTrace();
                        }

                        Toast.makeText(LoginActivity.this,
                                excecao,
                                Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual = autenticacao.getCurrentUser();
        if(usuarioAtual != null){
            abrirTelaPrincipal();
        }
    }

    public void validarLogin(View view){
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if( !email.isEmpty() ) {
            if(!senha.isEmpty()){

                Usuario usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setSenha(senha);

                logar( usuario );

            }
            else{
                Toast.makeText(LoginActivity.this,
                        "Digite sua Senha",
                        Toast.LENGTH_SHORT).show();

            }
        }
        else{
            Toast.makeText(LoginActivity.this,
                    "Digite seu Email",
                    Toast.LENGTH_SHORT).show();

        }
    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
    }

}