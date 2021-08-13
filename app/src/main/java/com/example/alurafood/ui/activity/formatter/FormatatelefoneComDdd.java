package com.example.alurafood.ui.activity.formatter;

public class FormatatelefoneComDdd {

    public String formata(String telefoneComDdd) {
        String telefoneComDddFormatado = telefoneComDdd
                .replaceAll("([0-9]{2})([0-9]{4,5})([0-9]{4})",
                        "($1) $2- $3");
        return telefoneComDddFormatado;
    }

    public String remove(String telefoneComDdd) {
        String telefoneComDddSemFormatacao = telefoneComDdd
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("-", "");
        return telefoneComDddSemFormatacao;
    }
}
