package com.example.alurafood.ui.activity.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class ValidaEmail implements Validador {

    private final TextInputLayout textInputEmail;
    private final EditText campoEmail;
    private final ValidacaoPadrao validadorPadrao;

    public ValidaEmail(TextInputLayout textInputEmail) {
        this.textInputEmail = textInputEmail;
        this.campoEmail = this.textInputEmail.getEditText();
        this.validadorPadrao= new ValidacaoPadrao(this.textInputEmail);
    }


    private boolean validaPadrao(String email){
        if(email.matches(".+@.+\\..+")){
            return true;
        }
        textInputEmail.setError("E-mail inválido!");
        return false;
    }
@Override
    public boolean estaValido(){
        if(!validadorPadrao.estaValido()) return false;
        String email = campoEmail.getText().toString();
        if(!validaPadrao(email)) return false;
        return true;
    }
}
