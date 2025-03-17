package com.laboratorio.pbapp.model;

public class ProdutoModel {

    // Declara variáveis de Classe
    private int idProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private double custoUnitario;
    private int quantidadeEstoque;
    private double valorVenda;

    public ProdutoModel(int idProduto,
                        String nomeProduto,
                        String descricaoProduto,
                        double custoUnitario,
                        int quantidadeEstoque,
                        double valorVenda) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.custoUnitario = custoUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
        this.valorVenda = valorVenda;
    }

    public int getIdProduto() {return idProduto;}
    public String getNomeProduto() {return nomeProduto;}
    public String getDescricaoProduto() {return descricaoProduto;}
    public double getCustoUnitario() {return custoUnitario;}
    public int getQuantidadeEstoque() {return quantidadeEstoque;}
    public double getValorVenda() {return valorVenda;}

    public double calcularLucro() {
        return valorVenda - custoUnitario;
    }

    public double calcularMargemLucro() {
        return (valorVenda > 0) ? (calcularLucro() / valorVenda) * 100 : 0;
    }
}