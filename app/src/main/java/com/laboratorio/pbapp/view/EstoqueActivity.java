package com.laboratorio.pbapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.adapter.ProdutoAdapter;
import com.laboratorio.pbapp.controller.ProdutoController;
import com.laboratorio.pbapp.model.Produto;
import com.laboratorio.pbapp.util.BottomNavigationUtil;
import com.laboratorio.pbapp.util.NavigationUtil;
import java.util.List;

public class EstoqueActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProdutoAdapter produtoAdapter;
    private ProdutoController produtoController;
    private TextView tvMensagemSemProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        produtoController = new ProdutoController(this);
        recyclerView = findViewById(R.id.recyclerViewProdutos);
        tvMensagemSemProdutos = findViewById(R.id.tvMensagemSemProdutos);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        carregarProdutos();

        // Referência e configura o botão de adicionar estoque
        ImageButton buttonAdicionarEstoque = findViewById(R.id.btnAdicionarEstoque);
        NavigationUtil.configurarBotaoNavegacao(this, buttonAdicionarEstoque, NovoProdutoActivity.class);

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationUtil.configurarBottomNavigation(bottomNavigationView, R.id.nav_estoque, this);

    }

    private void carregarProdutos() {
        List<Produto> produtos = produtoController.buscarTodosProdutos();
        if (produtos.isEmpty()) {
            tvMensagemSemProdutos.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvMensagemSemProdutos.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            produtoAdapter = new ProdutoAdapter(this, produtos, produto -> abrirDetalhesProduto(produto));
            recyclerView.setAdapter(produtoAdapter);
        }
    }

    private void abrirDetalhesProduto(Produto produto) {
        Intent intent = new Intent(this, ProdutoActivity.class);
        intent.putExtra("produto_id", produto.getId());
        startActivity(intent);
    }

}