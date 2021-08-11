package com.example.alurafood.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.alurafood.R;
import com.google.android.material.textfield.TextInputLayout;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

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
        EditText campoCPF = textInputCPF.getEditText();
        CPFFormatter cpfFormatter = new CPFFormatter();
        campoCPF.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String cpf = campoCPF.getText().toString();
                if(!hasFocus){
                    if(!validaCampoObrigatorio(cpf, textInputCPF)) return;
                    if(!validaCampoCom11Digitos(cpf, textInputCPF)) return;
                    if(!validaCalculoCPF(cpf, textInputCPF)) return;

                    removeErro(textInputCPF);


                    String cpfFormatado = cpfFormatter.format(cpf);
                    campoCPF.setText(cpfFormatado);
                } else {
                    try {
                        String cpfSemFormato = cpfFormatter.unformat(cpf);
                        campoCPF.setText(cpfSemFormato);
                    }catch (IllegalArgumentException e){
                        Log.e("erro formatação cpf", e.getMessage());
                    }

                }

            }
        });
    }

    private boolean validaCalculoCPF(String cpf, TextInputLayout textInputCPF) {
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);

        } catch (InvalidStateException e) {
            textInputCPF.setError("CPF Inválido poxa!");
            return false;
        }
        return true;
    }

    private void removeErro(TextInputLayout textInputCPF) {
        textInputCPF.setError(null);
        textInputCPF.setErrorEnabled(false);
    }

    private boolean validaCampoCom11Digitos(String cpf, TextInputLayout textInputCPF) {
        if(cpf.length() !=11){
            textInputCPF.setError("O CPF precisa ter 11 dígitos!");
            return false;}
            return true;

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
                    if(!validaCampoObrigatorio(texto, textInputCampo)) return;
                    removeErro(textInputCampo);
                }
            }
        });
    }

    private boolean validaCampoObrigatorio(String texto, TextInputLayout textInputCampo) {
        if(texto.isEmpty()){
            textInputCampo.setError("Campo Obrigatório");
            return false;
        }
         return true;}

}