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

        inicializaCampos();


    }

    private void inicializaCampos() {
        configuraCampoNomeCompleto();
        configuraCampoCPF();
        configuraCampoTelefoneComDDD();
        configuraCampoEmail();
        configuraCampoSenha();
    }

    private void configuraCampoSenha() {
        TextInputLayout textInputSenha = findViewById(R.id.form_cadastro_campo_senha);
        adicionaValidacaoPadrao(textInputSenha);
    }

    private void configuraCampoEmail() {
        TextInputLayout textInputEmail = findViewById(R.id.form_cadastro_campo_email);
        adicionaValidacaoPadrao(textInputEmail);
    }

    private void configuraCampoTelefoneComDDD() {
        TextInputLayout textInputTelefone = findViewById(R.id.form_cadastro_campo_telefone);
        adicionaValidacaoPadrao(textInputTelefone);
    }

    private void configuraCampoCPF() {
        TextInputLayout textInputCPF = findViewById(R.id.form_cadastro_campo_cpf);
        adicionaValidacaoPadrao(textInputCPF);
    }

    private void configuraCampoNomeCompleto() {
        TextInputLayout textInputNomeCompleto = findViewById(R.id.form_cadastro_campo_nome_completo);
        adicionaValidacaoPadrao(textInputNomeCompleto);
    }

    private void adicionaValidacaoPadrao(TextInputLayout textInputCampo){
        EditText campo = textInputCampo.getEditText();
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String texto = campo.getText().toString();
                if(!hasFocus){
                    validaCampoObrigatorio(texto, textInputCampo);
                }
            }
        });
    }

    private void validaCampoObrigatorio(String texto, TextInputLayout textInputCampo) {
        if(texto.isEmpty()){
            textInputCampo.setError("Campo Obrigatório");
        }
        else { textInputCampo.setError(null);
        textInputCampo.setErrorEnabled(false);}
    }
}