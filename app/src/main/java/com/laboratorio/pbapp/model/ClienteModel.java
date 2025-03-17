package com.laboratorio.pbapp.model;

public class ClienteModel {

    // Declara variáveis de Classe
    private int idCliente;
    private String nomeCliente;
    private String cpfCnpj;
    private String endereco;
    private String telefone;

    public ClienteModel(int idCliente,
                        String nomeCliente,
                        String cpfCnpj,
                        String endereco,
                        String telefone) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.cpfCnpj = cpfCnpj;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    // Getters e Setters
    public int getIdCliente() {return idCliente;}
    public String getNomeCliente() {return nomeCliente;}
    public String getCpfCnpj() {return cpfCnpj;}
    public String getEndereco() {return endereco;}
    public String getTelefone() {return telefone;}
}
