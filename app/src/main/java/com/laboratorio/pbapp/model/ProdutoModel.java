package com.laboratorio.pbapp.model;

public class ProdutoModel {
    private int id;
    private String nomeProduto;
    private String descricaoProduto;
    private double custoUnitario;
    private int quantidadeEstoque;
    private double valorVenda;

    public ProdutoModel(int id, String nomeProduto, String descricaoProduto, double custoUnitario, int quantidadeEstoque, double valorVenda) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.custoUnitario = custoUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
        this.valorVenda = valorVenda;
    }

    public int getId() {
        return id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public double getCustoUnitario() {
        return custoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public double calcularLucro() {
        return valorVenda - custoUnitario;
    }

    public double calcularMargemLucro() {
        return (valorVenda > 0) ? (calcularLucro() / valorVenda) * 100 : 0;
    }
}