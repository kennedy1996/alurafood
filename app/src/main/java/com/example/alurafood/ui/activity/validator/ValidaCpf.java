package com.example.alurafood.ui.activity.validator;

import android.util.Log;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidaCpf implements Validador {

    private static final String CAMPO_OBRIGATORIO = "CPF Inválido poxa!";
    public static final String ERRO_FORMATACAO_CPF = "erro formatação cpf";
    public static final String ERRO_CPF_ONZE_DIGITOS = "O CPF precisa ter 11 dígitos!";
    private final TextInputLayout textInputCPF;
    private final EditText campoCpf;
    private final ValidacaoPadrao validadorPadrao;
    private final CPFFormatter formatador = new CPFFormatter();

    public ValidaCpf(TextInputLayout textInputCpf) {
        this.textInputCPF = textInputCpf;
        this.campoCpf = textInputCpf.getEditText();
        this.validadorPadrao= new ValidacaoPadrao(textInputCPF);
    }
    private boolean validaCalculoCPF(String cpf) {
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException e) {
            textInputCPF.setError(CAMPO_OBRIGATORIO);
            return false;
        }
        return true;
    }
    @Override
    public boolean estaValido(){
        if(!validadorPadrao.estaValido()) return false;
        String cpf = getCpf();
        String cpfSemFormato = cpf;
        try {
            cpfSemFormato = formatador.unformat(cpf);
        }catch (IllegalArgumentException e){
            Log.e(ERRO_FORMATACAO_CPF, e.getMessage());
        }
        if(!validaCampoCom11Digitos(cpfSemFormato)) return false;
        if(!validaCalculoCPF(cpfSemFormato)) return false;

        adicionaFormatacao(cpfSemFormato);
        return true;
    }

    private void adicionaFormatacao(String cpf) {
        String cpfFormatado = formatador.format(cpf);
        campoCpf.setText(cpfFormatado);
    }

    private String getCpf() {
        return campoCpf.getText().toString();
    }
    private boolean validaCampoCom11Digitos(String cpf) {
        if(cpf.length() !=11){
            textInputCPF.setError(ERRO_CPF_ONZE_DIGITOS);
            return false;}
        return true;

    }

}
