package com.laboratorio.pbapp.util;

import android.text.Editable;
import android.text.TextWatcher;
import com.laboratorio.pbapp.adapter.ProdutoAdapter;
import com.laboratorio.pbapp.model.ProdutoModel;
import java.util.ArrayList;
import java.util.List;

public class EstoqueTextWatcherUtil implements TextWatcher {

    private final ProdutoAdapter produtoAdapter;
    private final List<ProdutoModel> listaCompleta;

    public EstoqueTextWatcherUtil(ProdutoAdapter produtoAdapter, List<ProdutoModel> listaCompleta) {
        this.produtoAdapter = produtoAdapter;
        this.listaCompleta = new ArrayList<>(listaCompleta);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        filtrarProdutos(s.toString());
    }

    private void filtrarProdutos(String texto) {
        List<ProdutoModel> produtosFiltrados = new ArrayList<>();
        for (ProdutoModel produto : listaCompleta) {
            if (produto.getNomeProduto().toLowerCase().contains(texto.toLowerCase())) {
                produtosFiltrados.add(produto);
            }
        }
        produtoAdapter.atualizarLista(produtosFiltrados);
    }
}
