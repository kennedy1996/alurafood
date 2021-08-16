package com.example.alurafood.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alurafood.R;
import com.example.alurafood.ui.activity.formatter.FormatatelefoneComDdd;
import com.example.alurafood.ui.activity.validator.ValidaCpf;
import com.example.alurafood.ui.activity.validator.ValidaTelefoneComDDD;
import com.example.alurafood.ui.activity.validator.ValidacaoPadrao;
import com.example.alurafood.ui.activity.validator.ValidaEmail;
import com.example.alurafood.ui.activity.validator.Validador;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.stella.format.CPFFormatter;

public class FormularioCadastroActivity extends AppCompatActivity {

    public static final String ERRO_FORMATACAO_CPF = "erro formatação cpf";
    private final List<Validador> validadores = new ArrayList<>();

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
        configuraBotaoCadastrar();
    }

    private void configuraBotaoCadastrar() {
        Button botaoCadastrar = findViewById(R.id.formulario_cadastro_botao_cadastrar);
        botaoCadastrar.setOnClickListener(v -> {
            boolean formularioEstaValido = validaTodosCampos();
            if (formularioEstaValido) {
                cadastroRealizado();
            }
        });
    }
    private void cadastroRealizado() {
        Toast.makeText(FormularioCadastroActivity.this,
                "Cadastro Relizado com Sucesso!",
                Toast.LENGTH_LONG).show();
    }
    private boolean validaTodosCampos() {
        boolean formularioEstaValido = true;
        for (Validador validador : validadores
             ) {
            if (!validador.estaValido()) {
                formularioEstaValido = false;
            }
        }
        return formularioEstaValido;
    }
    private void configuraCampoSenha() {
        TextInputLayout textInputSenha = findViewById(R.id.form_cadastro_campo_senha);
        adicionaValidacaoPadrao(textInputSenha);
    }
    private void configuraCampoEmail() {
        TextInputLayout textInputEmail = findViewById(R.id.form_cadastro_campo_email);
        EditText campoEmail = textInputEmail.getEditText();
        ValidaEmail validador = new ValidaEmail(textInputEmail);
        validadores.add(validador);
        campoEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validador.estaValido();
                }
            }
        });
    }
    private void configuraCampoTelefoneComDDD() {
        TextInputLayout textInputTelefone = findViewById(R.id.form_cadastro_campo_telefone);
        EditText campoTelefoneComDdd = textInputTelefone.getEditText();
        ValidaTelefoneComDDD validador = new ValidaTelefoneComDDD(textInputTelefone);
        final FormatatelefoneComDdd formatador = new FormatatelefoneComDdd();
        validadores.add(validador);
        campoTelefoneComDdd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String telefoneComDdd = campoTelefoneComDdd.getText().toString();
                if(hasFocus){
                    String telefoneComDddSemFormatacao = formatador.remove(telefoneComDdd);
                    campoTelefoneComDdd.setText(telefoneComDddSemFormatacao);
                } else {
                    validador.estaValido();
                }
            }
        });
    }
    private void configuraCampoCPF() {
        TextInputLayout textInputCPF = findViewById(R.id.form_cadastro_campo_cpf);
        EditText campoCPF = textInputCPF.getEditText();
        CPFFormatter formatador = new CPFFormatter();
        ValidaCpf validador = new ValidaCpf(textInputCPF);
        validadores.add(validador);
        campoCPF.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    removeFormatacao( formatador, campoCPF);
                } else {
                    validador.estaValido();
                }

            }
        });
    }
    private void removeFormatacao(CPFFormatter formatador, EditText campoCPF) {
        String cpf = campoCPF.getText().toString();
        try {
            String cpfSemFormato = formatador.unformat(cpf);
            campoCPF.setText(cpfSemFormato);
        }catch (IllegalArgumentException e){
            Log.e(ERRO_FORMATACAO_CPF, e.getMessage());
        }
    }
    private void configuraCampoNomeCompleto() {
        TextInputLayout textInputNomeCompleto = findViewById(R.id.form_cadastro_campo_nome_completo);
        adicionaValidacaoPadrao(textInputNomeCompleto);
    }
    private void adicionaValidacaoPadrao(TextInputLayout textInputCampo){
        EditText campo = textInputCampo.getEditText();
        ValidacaoPadrao validador = new ValidacaoPadrao(textInputCampo);
        validadores.add(validador);
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validador.estaValido();
                }
            }
        });
    }
}