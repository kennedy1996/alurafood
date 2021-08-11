package com.example.alurafood.ui.activity.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class ValidaTelefoneComDDD {

    public static final String DEVE_TER_10_OU_11_DIGITOS = "Telefone deve conter entre 10 e 11 digitos!";
    private final TextInputLayout textInputTelefoneComddd;
    private final ValidacaoPadrao validacaoPadrao;
    private final EditText campoTelefoneComDdd;

    public ValidaTelefoneComDDD(TextInputLayout textInputTelefoneComddd) {
        this.textInputTelefoneComddd = textInputTelefoneComddd;
        this.validacaoPadrao = new ValidacaoPadrao(textInputTelefoneComddd);
        this.campoTelefoneComDdd = textInputTelefoneComddd.getEditText();
    }

    private boolean validaEntreDezOuOnzeDigitos(String telefoneComDdd){
        int digitos = telefoneComDdd.length();
        if(digitos <10 || digitos >11){
            textInputTelefoneComddd.setError(DEVE_TER_10_OU_11_DIGITOS);
            return false;
        }
        return true;
    }
    public boolean estaValido(){
        if(!validacaoPadrao.estaValido()) return false;
        String telefoneComDdd = campoTelefoneComDdd.getText().toString();
        if(validaEntreDezOuOnzeDigitos(telefoneComDdd)) return false;
        return true;
    }
}
