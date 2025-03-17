package com.laboratorio.pbapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.adapter.ClienteAdapter;
import com.laboratorio.pbapp.controller.ClienteController;
import com.laboratorio.pbapp.model.ClienteModel;
import com.laboratorio.pbapp.util.BottomNavigationUtil;
import com.laboratorio.pbapp.util.TextWatcherUtil.ClientesTextWatcherUtil;
import com.laboratorio.pbapp.util.NavigationUtil;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClientesActivity extends AppCompatActivity {

    // Declara variáveis de Classe
    private ClienteAdapter clienteAdapter;
    private ClienteController clienteController;
    private RecyclerView clienteRecyclerView;
    private List<ClienteModel> clienteModels;
    private EditText etBuscaCliente;
    private TextView tvMensagemSemClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        clienteController = new ClienteController(this);
        clienteRecyclerView = findViewById(R.id.recyclerViewClientes);
        tvMensagemSemClientes = findViewById(R.id.tvMensagemSemClientes);
        clienteRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Carrega os clientes e exibe em ordem alfabética
        carregarClientes();

        // Adicionar TextWatcher para pesquisa dinâmica
        etBuscaCliente = findViewById(R.id.etBuscaClientes);
        etBuscaCliente.addTextChangedListener(new ClientesTextWatcherUtil(clienteAdapter, clienteModels));

        // Botão de adicionar cliente
        ImageButton buttonAdicionarCliente = findViewById(R.id.btnAdicionarClientes);
        NavigationUtil.configurarBotaoNavegacao(this, buttonAdicionarCliente, NovoClienteActivity.class);

        // Configuração do menu inferior
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationUtil.configurarBottomNavigation(bottomNavigationView, R.id.nav_clientes, this);
    }

    private void carregarClientes() {
        clienteModels = clienteController.buscarTodosClientes();

        Collections.sort(clienteModels, Comparator.comparing(ClienteModel::getNomeCliente));

        if (clienteModels.isEmpty()) {
            tvMensagemSemClientes.setVisibility(View.VISIBLE);
            clienteRecyclerView.setVisibility(View.GONE);
        } else {
            tvMensagemSemClientes.setVisibility(View.GONE);
            clienteRecyclerView.setVisibility(View.VISIBLE);
            clienteAdapter = new ClienteAdapter(this, clienteModels, this::abrirDetalhesCliente);
            clienteRecyclerView.setAdapter(clienteAdapter);
        }
    }

    private void abrirDetalhesCliente(ClienteModel clienteModel) {
        Intent intent = new Intent(this, ClienteActivity.class);
        intent.putExtra("cliente_id", clienteModel.getIdCliente());
        startActivity(intent);
    }

}
