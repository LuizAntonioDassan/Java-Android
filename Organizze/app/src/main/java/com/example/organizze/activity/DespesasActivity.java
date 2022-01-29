package com.example.organizze.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.organizze.R;
import com.example.organizze.config.ConfiguracaoFirebase;
import com.example.organizze.helper.Base64Custom;
import com.example.organizze.helper.DateCustom;
import com.example.organizze.model.Movimentacao;
import com.example.organizze.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText campoData,campoCategoria,campoDescricao;
    private EditText campoValor;
    private Movimentacao movimentacao;
    private DatabaseReference databaseRef   = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao       = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double despesaTotal,despesaAtualizada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        campoData       = findViewById(R.id.editDataDespesas);
        campoCategoria  = findViewById(R.id.editCategoriaDespesas);
        campoDescricao  = findViewById(R.id.editDescricaoDespesas);
        campoValor      = findViewById(R.id.editValorDespesas);

        campoData.setText(DateCustom.dataAtual());

        recuperarDespesaTotal();

    }


    public void salvarDespesas(View view){

        if( validarCampos()){
            String data = campoData.getText().toString();
            Double valorRecuperado = Double.parseDouble(campoValor.getText().toString());

            movimentacao = new Movimentacao();
            movimentacao.setValor(valorRecuperado);
            movimentacao.setCategoria( campoCategoria.getText().toString());
            movimentacao.setDescricao( campoDescricao.getText().toString());
            movimentacao.setData(data);
            movimentacao.setTipo("d");
            movimentacao.salvar(data);

            despesaAtualizada = despesaTotal + valorRecuperado;
            atualizarDespesas(despesaAtualizada);
            finish();


        }

    }

    public Boolean validarCampos(){

        String textoValor       = campoValor.getText().toString();
        String textoCategoria   = campoCategoria.getText().toString();
        String textoDescricao   = campoDescricao.getText().toString();
        String textoData        = campoData.getText().toString();

        if( !textoValor.isEmpty()){
            if( !textoData.isEmpty()){
                if( !textoCategoria.isEmpty()){
                    if( !textoDescricao.isEmpty()){

                        return true;

                    }else{
                        Toast.makeText(DespesasActivity.this,
                                "Descricao nao foi Preenchido!",
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }

                }else{
                    Toast.makeText(DespesasActivity.this,
                            "Categoria nao foi Preenchido!",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }

            }else{
                Toast.makeText(DespesasActivity.this,
                        "Data nao foi Preenchido!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }

        }else{
            Toast.makeText(DespesasActivity.this,
                    "Valor nao foi Preenchido!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

    }

   public void recuperarDespesaTotal(){

        String emailUsuario             = autenticacao.getCurrentUser().getEmail();
        String idUsuario                = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef    = databaseRef.child("usuarios").child( idUsuario );

       System.out.println(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue(Usuario.class);
                despesaTotal = usuario.getDespesaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void atualizarDespesas(Double despesa){

        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = databaseRef.child("usuarios").child( idUsuario );

        usuarioRef.child("despesaTotal").setValue(despesa);

    }

}