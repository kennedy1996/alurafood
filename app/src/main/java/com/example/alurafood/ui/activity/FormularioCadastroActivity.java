package com.example.alurafood.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.alurafood.R;
import com.google.android.material.textfield.TextInputLayout;

public class FormularioCadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);

        TextInputLayout textInputNomeCompleto = findViewById(R.id.form_cadastro_campo_nome_completo);
        EditText campoNomeCompleto = textInputNomeCompleto.getEditText();
        TextInputLayout textInputCPF = findViewById(R.id.form_cadastro_campo_cpf);
        EditText campoCPF = textInputCPF.getEditText();
        TextInputLayout textInputTelefone = findViewById(R.id.form_cadastro_campo_telefone);
        EditText campoTelefone = textInputTelefone.getEditText();
        TextInputLayout textInputEmail = findViewById(R.id.form_cadastro_campo_email);
        EditText campoEmail = textInputEmail.getEditText();
        TextInputLayout textInputSenha = findViewById(R.id.form_cadastro_campo_senha);
        EditText campoSenha = textInputSenha.getEditText();


        adicionaValidacaoPadrao(campoNomeCompleto);
        adicionaValidacaoPadrao(campoCPF);
        adicionaValidacaoPadrao(campoTelefone);
        adicionaValidacaoPadrao(campoEmail);
        adicionaValidacaoPadrao(campoSenha);


    }

    private void adicionaValidacaoPadrao(EditText campo) {
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String texto = campo.getText().toString();
                if(!hasFocus){
                    if(texto.isEmpty()){
                        campo.setError("Campo Obrigatório");
                    }
                }

            }
        });
    }
}