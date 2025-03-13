package com.laboratorio.pbapp.model;

public class ClienteModel {
    private int id;
    private String nomeCliente;
    private String cpfCnpj;
    private String endereco;
    private String telefone;

    public ClienteModel(int id, String nomeCliente, String cpfCnpj, String endereco, String telefone) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.cpfCnpj = cpfCnpj;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    // Construtor sem ID para inserções
    public ClienteModel(String nomeCliente, String cpfCnpj, String endereco, String telefone) {
        this.nomeCliente = nomeCliente;
        this.cpfCnpj = cpfCnpj;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
