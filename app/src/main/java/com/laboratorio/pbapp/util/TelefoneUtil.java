package com.laboratorio.pbapp.util;

public class TelefoneUtil {

    public static String formatarTelefone(String telefone) {
        if (telefone == null || telefone.isEmpty()) return "";

        telefone = removerNaoNumericos(telefone);

        if (telefone.startsWith("0")) {
            telefone = telefone.substring(1);
        }

        if (telefone.length() == 10) { // Telefone fixo
            return String.format("(%s)%s-%s",
                    telefone.substring(0, 2),
                    telefone.substring(2, 6),
                    telefone.substring(6, 10));
        } else if (telefone.length() == 11) { // Celular
            return String.format("(%s)%s-%s",
                    telefone.substring(0, 2),
                    telefone.substring(2, 7),
                    telefone.substring(7, 11));
        } else {
            return "";
        }
    }

    public static String removerNaoNumericos(String telefone) {
        if (telefone == null) return "";
        return telefone.replaceAll("[^\\d]", "");
    }

}
