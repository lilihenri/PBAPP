package com.laboratorio.pbapp.controller;

import android.content.Context;
import com.laboratorio.pbapp.dao.ClienteDAO;
import com.laboratorio.pbapp.model.ClienteModel;
import java.util.List;

public class ClienteController {

    // Declara variáveis de Classe
    private final ClienteDAO clienteDAO;

    public ClienteController(Context context) {
        this.clienteDAO = new ClienteDAO(context);
    }

    public boolean adicionarCliente(String nomeCliente, String cpfCnpj, String endereco, String telefone) {
        return clienteDAO.adicionarCliente(nomeCliente, cpfCnpj, endereco, telefone);
    }

    public boolean excluirCliente(int clienteId) {
        return clienteDAO.excluirCliente(clienteId);
    }

    public boolean atualizarCliente(int idCliente, String nomeCliente, String cpfCnpj, String endereco, String telefone) {
        return clienteDAO.atualizarCliente(idCliente, nomeCliente, cpfCnpj, endereco, telefone);
    }

    public ClienteModel buscarClientePorId(int idCliente) {
        return clienteDAO.buscarClientePorId(idCliente);
    }

    public List<ClienteModel> buscarTodosClientes() {
        return clienteDAO.buscarTodosClientes();
    }

}
