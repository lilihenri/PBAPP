package com.laboratorio.pbapp.util.TextWatcherUtil;

import android.text.Editable;
import android.text.TextWatcher;
import com.laboratorio.pbapp.adapter.ClienteAdapter;
import com.laboratorio.pbapp.model.ClienteModel;
import java.util.ArrayList;
import java.util.List;

public class ClientesTextWatcherUtil implements TextWatcher {

    // Declara variáveis de Classe
    private final ClienteAdapter clienteAdapter;
    private final List<ClienteModel> listaCompletaClientes;

    public ClientesTextWatcherUtil(ClienteAdapter clienteAdapter, List<ClienteModel> listaCompletaClientes) {
        this.clienteAdapter = clienteAdapter;
        this.listaCompletaClientes = new ArrayList<>(listaCompletaClientes);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) { filtrarClientes(s.toString()); }

    private void filtrarClientes(String texto) {
        List<ClienteModel> clientesFiltrados = new ArrayList<>();
        for (ClienteModel cliente : listaCompletaClientes) {
            if (cliente.getNomeCliente().toLowerCase().contains(texto.toLowerCase())) {
                clientesFiltrados.add(cliente);
            }
        }
        clienteAdapter.atualizarLista(clientesFiltrados);
    }
}
