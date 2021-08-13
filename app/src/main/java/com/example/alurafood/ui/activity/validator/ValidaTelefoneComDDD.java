package com.example.alurafood.ui.activity.validator;

import android.util.Log;
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
        String telefoneComDdd = getTelefoneComDDD();
        if(!validaEntreDezOuOnzeDigitos(telefoneComDdd)) return false;
        adicionaFormatacao(telefoneComDdd);
        return true;
    }

    private String getTelefoneComDDD() {
        return campoTelefoneComDdd.getText().toString();
    }

    private void adicionaFormatacao(String telefoneComDdd) {

        StringBuilder sb = new StringBuilder();
        int digitos = telefoneComDdd.length();
        for (int i = 0; i < digitos; i++) {
            if (i == 0) {
                sb.append("(");
            }

            char digito = telefoneComDdd.charAt(i);
            sb.append(digito);
            if (i == 1) {
                sb.append(") ");
            }
            if (digitos == 10 && i == 5 || digitos == 11 && i == 6) {
                sb.append("-");
            }
            String telefoneComDddFormatado = sb.toString();
            campoTelefoneComDdd.setText(telefoneComDddFormatado);
        }
    }
}
