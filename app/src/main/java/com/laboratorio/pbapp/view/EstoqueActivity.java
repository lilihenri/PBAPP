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
import com.laboratorio.pbapp.adapter.ProdutoAdapter;
import com.laboratorio.pbapp.controller.ProdutoController;
import com.laboratorio.pbapp.model.ProdutoModel;
import com.laboratorio.pbapp.util.BottomNavigationUtil;
import com.laboratorio.pbapp.util.TextWatcherUtil.EstoqueTextWatcherUtil;
import com.laboratorio.pbapp.util.NavigationUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EstoqueActivity extends AppCompatActivity {

    // Declara variáveis de Classe
    private RecyclerView produtoRecyclerView;
    private ProdutoAdapter produtoAdapter;
    private ProdutoController produtoController;
    private List<ProdutoModel> produtoModels;
    private EditText etBuscaEstoque;
    private TextView tvMensagemSemProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        produtoController = new ProdutoController(this);
        produtoRecyclerView = findViewById(R.id.recyclerViewProdutos);
        tvMensagemSemProdutos = findViewById(R.id.tvMensagemSemProdutos);
        produtoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Carrega os produtos e exibe em ordem alfabética
        carregarProdutos();

        // Configura a barra de busca
        etBuscaEstoque = findViewById(R.id.etBuscaEstoque);
        etBuscaEstoque.addTextChangedListener(new EstoqueTextWatcherUtil(produtoAdapter, produtoModels));

        // Configura botão para adicionar novos produtos
        ImageButton buttonAdicionarEstoque = findViewById(R.id.btnAdicionarEstoque);
        NavigationUtil.configurarBotaoNavegacao(this, buttonAdicionarEstoque, NovoProdutoActivity.class);

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationUtil.configurarBottomNavigation(bottomNavigationView, R.id.nav_estoque, this);
    }

    private void carregarProdutos() {
        produtoModels = produtoController.buscarTodosProdutos();

        Collections.sort(produtoModels, Comparator.comparing(ProdutoModel::getNomeProduto));

        if (produtoModels.isEmpty()) {
            tvMensagemSemProdutos.setVisibility(View.VISIBLE);
            produtoRecyclerView.setVisibility(View.GONE);
        } else {
            tvMensagemSemProdutos.setVisibility(View.GONE);
            produtoRecyclerView.setVisibility(View.VISIBLE);
            produtoAdapter = new ProdutoAdapter(this, produtoModels, this::abrirDetalhesProduto);
            produtoRecyclerView.setAdapter(produtoAdapter);
        }
    }

    private void abrirDetalhesProduto(ProdutoModel produtoModel) {
        Intent intent = new Intent(this, ProdutoActivity.class);
        intent.putExtra("produto_id", produtoModel.getIdProduto());
        startActivity(intent);
    }

}
