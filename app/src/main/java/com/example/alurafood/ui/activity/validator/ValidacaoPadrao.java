package com.example.alurafood.ui.activity.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class ValidacaoPadrao {

    private final TextInputLayout textInputCampo;
    private final EditText campo;
    public ValidacaoPadrao(TextInputLayout textinputCampo) {
        this.textInputCampo = textinputCampo;
        this.campo = this.textInputCampo.getEditText();
    }

    private boolean validaCampoObrigatorio() {
        String texto = campo.getText().toString();
        if(texto.isEmpty()){
            textInputCampo.setError("Campo Obrigatório");
            return false;
        }
        return true;
    }

    public boolean estaValido() {
        if(!validaCampoObrigatorio()) return false;
        removeErro();
        return true;

    }

    private void removeErro() {
        textInputCampo.setError(null);
        textInputCampo.setErrorEnabled(false);
    }
}
