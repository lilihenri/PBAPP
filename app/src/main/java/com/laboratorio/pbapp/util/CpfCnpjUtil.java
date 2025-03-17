package com.laboratorio.pbapp.util;

public class CpfCnpjUtil {

    public static boolean isCpfValido(String CPF) {
        if (CPF == null || CPF.isEmpty()) return false;

        CPF = CPF.replaceAll("[^\\d]", "");
        if (CPF.length() != 11 || CPF.matches("(\\d)\\1{10}")) return false;

        int soma = 0, resto;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(CPF.charAt(i)) * (10 - i);
        }
        resto = 11 - (soma % 11);
        int digito1 = (resto >= 10) ? 0 : resto;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(CPF.charAt(i)) * (11 - i);
        }
        resto = 11 - (soma % 11);
        int digito2 = (resto >= 10) ? 0 : resto;

        return (digito1 == Character.getNumericValue(CPF.charAt(9)) &&
                digito2 == Character.getNumericValue(CPF.charAt(10)));
    }

    public static boolean isCnpjValido(String CNPJ) {
        if (CNPJ == null || CNPJ.isEmpty()) return false;

        CNPJ = CNPJ.replaceAll("[^\\d]", "");
        if (CNPJ.length() != 14 || CNPJ.matches("(\\d)\\1{13}")) return false;

        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma = 0, resto;
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(CNPJ.charAt(i)) * pesos1[i];
        }
        resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : 11 - resto;

        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(CNPJ.charAt(i)) * pesos2[i];
        }
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : 11 - resto;

        return (digito1 == Character.getNumericValue(CNPJ.charAt(12)) &&
                digito2 == Character.getNumericValue(CNPJ.charAt(13)));
    }

    public static String formatarCpf(String CPF) {
        if (CPF == null) return null;

        CPF = CPF.replaceAll("[^\\d]", "");

        if (CPF.length() == 11) {
            return String.format("%s.%s.%s-%s",
                    CPF.substring(0, 3),
                    CPF.substring(3, 6),
                    CPF.substring(6, 9),
                    CPF.substring(9, 11));
        }
        return "";
    }

    public static String formatarCnpj(String CNPJ) {
        if (CNPJ == null) return null;

        CNPJ = CNPJ.replaceAll("[^\\d]", "");

        if (CNPJ.length() == 14) {
            return String.format("%s.%s.%s/%s-%s",
                    CNPJ.substring(0, 2),
                    CNPJ.substring(2, 5),
                    CNPJ.substring(5, 8),
                    CNPJ.substring(8, 12),
                    CNPJ.substring(12, 14));
        }
        return "";
    }
    public static boolean isCPFouCNPJValido(String documento) {
        if (documento == null) return false;

        documento = documento.replaceAll("[^\\d]", "");

        if (documento.length() == 11) {
            return isCpfValido(documento);
        } else if (documento.length() == 14) {
            return isCnpjValido(documento);
        }
        return false;
    }

    public static String formatarCPFouCNPJ(String documento) {
        if (documento == null) return null;

        documento = documento.replaceAll("[^\\d]", "");

        if (documento.length() == 11) {
            return formatarCpf(documento);
        } else if (documento.length() == 14) {
            return formatarCnpj(documento);
        }
        return null;
    }

}