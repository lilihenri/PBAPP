package com.laboratorio.pbapp.util;

import java.util.Locale;

public class ProdutoUtil {

    public static double calcularLucro(double custoUnitario, double valorVenda) {
        return valorVenda - custoUnitario;
    }

    public static double calcularMargem(double lucro, double valorVenda) {
        if (valorVenda == 0) return 0;
        return (lucro / valorVenda) * 100;
    }

    public static String formatarDouble(double valor) {
        return String.format(Locale.getDefault(), "%.2f", valor);
    }

    public static String formatarMargem(double valor) {
        return String.format(Locale.getDefault(), "%.2f%%", valor);
    }

}
