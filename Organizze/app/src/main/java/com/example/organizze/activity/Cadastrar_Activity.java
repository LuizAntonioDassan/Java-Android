package com.example.organizze.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.organizze.R;
import com.example.organizze.config.ConfiguracaoFirebase;
import com.example.organizze.helper.Base64Custom;
import com.example.organizze.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class Cadastrar_Activity extends AppCompatActivity {

    private EditText campoNome,campoEmail,campoSenha;
    private Button btnCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        getSupportActionBar().setTitle("Cadastro");

        campoEmail  = findViewById(R.id.emailCadastro);
        campoNome   = findViewById(R.id.nomeCadastro);
        campoSenha  = findViewById(R.id.senhaCadastro);
        btnCadastrar= findViewById(R.id.btnCadastro);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoNome    = campoNome.getText().toString();
                String textoEmail   = campoEmail.getText().toString();
                String textoSenha   = campoSenha.getText().toString();

                if( !textoNome.isEmpty()){
                    if (!textoEmail.isEmpty()) {
                        if (!textoSenha.isEmpty()) {

                            usuario = new Usuario();
                            usuario.setNome(textoNome);
                            usuario.setEmail(textoEmail);
                            usuario.setSenha(textoSenha);
                            cadastrarUsuario();

                        }
                        else{
                            Toast.makeText(Cadastrar_Activity.this,
                                        "Preencha a Senha",
                                        Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Cadastrar_Activity.this,
                                "Preencha o Email",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Cadastrar_Activity.this,
                            "Preencha o Nome",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setIdUsuario(idUsuario);
                    usuario.salvar();
                    finish();

                }else{

                    String excecao = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        if(usuario.getSenha().length() < 6){
                            excecao = "A senha precisa ter pelo menos 6 digitos";
                        }else{
                            excecao = "Digite uma senha mais forte";
                        }
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Digite um Email V??lido";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Essa conta ja foi cadastrada";
                    }catch(Exception e){
                        excecao = "Erro ao cadastrar Usu??rio: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(Cadastrar_Activity.this,
                            excecao,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}