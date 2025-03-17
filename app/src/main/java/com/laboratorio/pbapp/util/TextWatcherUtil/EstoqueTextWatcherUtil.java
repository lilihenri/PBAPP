package com.laboratorio.pbapp.util.TextWatcherUtil;

import android.text.Editable;
import android.text.TextWatcher;
import com.laboratorio.pbapp.adapter.ProdutoAdapter;
import com.laboratorio.pbapp.model.ProdutoModel;
import java.util.ArrayList;
import java.util.List;

public class EstoqueTextWatcherUtil implements TextWatcher {

    // Declara variáveis de Classe
    private final ProdutoAdapter produtoAdapter;
    private final List<ProdutoModel> listaCompletaProduto;

    public EstoqueTextWatcherUtil(ProdutoAdapter produtoAdapter, List<ProdutoModel> listaCompletaProduto) {
        this.produtoAdapter = produtoAdapter;
        this.listaCompletaProduto = new ArrayList<>(listaCompletaProduto);
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
        for (ProdutoModel produto : listaCompletaProduto) {
            if (produto.getNomeProduto().toLowerCase().contains(texto.toLowerCase())) {
                produtosFiltrados.add(produto);
            }
        }
        produtoAdapter.atualizarLista(produtosFiltrados);
    }
}
