package com.laboratorio.pbapp.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class ProdutoTextWatcherUtil implements TextWatcher {

    private final EditText etCustoUnitario;
    private final EditText etValorDeVenda;
    private final EditText etLucro;
    private final EditText etMargemDeLucro;

    public ProdutoTextWatcherUtil(EditText etCustoUnitario, EditText etValorDeVenda, EditText etLucro, EditText etMargemDeLucro) {
        this.etCustoUnitario = etCustoUnitario;
        this.etValorDeVenda = etValorDeVenda;
        this.etLucro = etLucro;
        this.etMargemDeLucro = etMargemDeLucro;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        calcularLucroEMargem();
    }

    private void calcularLucroEMargem() {
        String strCusto = etCustoUnitario.getText().toString().trim().replace(",", ".");
        String strValorVenda = etValorDeVenda.getText().toString().trim().replace(",", ".");

        if (strCusto.isEmpty() || strValorVenda.isEmpty()) {
            etLucro.setText("");
            etMargemDeLucro.setText("");
            return;
        }

        try {
            double custo = Double.parseDouble(strCusto);
            double venda = Double.parseDouble(strValorVenda);

            double lucro = ProdutoUtil.calcularLucro(custo, venda);
            double margem = ProdutoUtil.calcularMargem(lucro, venda);

            etLucro.setText(ProdutoUtil.formatarDouble(lucro));
            etMargemDeLucro.setText(ProdutoUtil.formatarMargem(margem));

        } catch (NumberFormatException e) {
            etLucro.setText("");
            etMargemDeLucro.setText("");
        }
    }
}
