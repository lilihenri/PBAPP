package com.laboratorio.pbapp.controller;

import android.content.Context;
import com.laboratorio.pbapp.dao.ProdutoDAO;
import com.laboratorio.pbapp.model.ProdutoModel;
import java.util.List;

public class ProdutoController {

    // Declara variáveis de Classe
    private final ProdutoDAO produtoDAO;

    public ProdutoController(Context context) {
        this.produtoDAO = new ProdutoDAO(context);
    }

    public boolean adicionarProduto(String nomeProduto, String descricao, double custoUnitario, int quantidadeEmEstoque, double valorDeVenda) {
        return produtoDAO.adicionarProduto(nomeProduto, descricao, custoUnitario, quantidadeEmEstoque, valorDeVenda);
    }

    public boolean excluirProduto(int produtoId) {
        return produtoDAO.excluirProduto(produtoId);
    }

    public boolean atualizarProduto(int idProduto, String nomeProduto, String descricao, double custo, int quantidade, double venda) {
        return produtoDAO.atualizarProduto(idProduto, nomeProduto, descricao, custo, quantidade, venda);
    }

    public ProdutoModel buscarProdutoPorId(int idProduto) {
        return produtoDAO.buscarProdutoPorId(idProduto);
    }

    public List<ProdutoModel> buscarTodosProdutos() {
        return produtoDAO.buscarTodosProdutos();
    }
}
