package com.laboratorio.pbapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.controller.ProdutoController;
import com.laboratorio.pbapp.model.ProdutoModel;
import com.laboratorio.pbapp.util.BottomNavigationUtil;
import com.laboratorio.pbapp.util.ProdutoUtil;
import com.laboratorio.pbapp.util.TextWatcherUtil.ProdutoTextWatcherUtil;

public class ProdutoActivity extends AppCompatActivity {

    // Declara variáveis de Classe
    private ProdutoController produtoController;
    private EditText etNomeProduto, etDescricao, etCustoUnitario, etQuantidadeEmEstoque, etValorDeVenda, etLucro, etMargemDeLucro;
    private Button btnSalvarProduto, btnExcluirProduto;
    private int idProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        // Recuperar ID do produto da Intent
        idProduto = getIntent().getIntExtra("produto_id", -1);
        if (idProduto == -1) {
            Toast.makeText(this, "Erro ao carregar produto", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Inicializar os campos de entrada
        etNomeProduto = findViewById(R.id.etNomeDoProdutoEdição);
        etDescricao = findViewById(R.id.etDescricaoEdição);
        etCustoUnitario = findViewById(R.id.etCustoUnitarioEdição);
        etQuantidadeEmEstoque = findViewById(R.id.etQuantidadeEmEstoqueEdição);
        etValorDeVenda = findViewById(R.id.etValorDeVendaEdição);
        etLucro = findViewById(R.id.etLucroEdição);
        etMargemDeLucro = findViewById(R.id.etMargemDeLucroEdição);
        btnSalvarProduto = findViewById(R.id.btnSalvarProdutoEdição);
        btnExcluirProduto = findViewById(R.id.btnExcluirProdutoEdição);

        produtoController = new ProdutoController(this);

        // Buscar os dados do produto no banco e preencher os campos
        carregarProduto(idProduto);

        // Adiciona o TextWatcher para atualizar lucro e margem automaticamente
        ProdutoTextWatcherUtil textWatcher = new ProdutoTextWatcherUtil(etCustoUnitario, etValorDeVenda, etLucro, etMargemDeLucro);
        etCustoUnitario.addTextChangedListener(textWatcher);
        etValorDeVenda.addTextChangedListener(textWatcher);

        // Configurar botão salvar
        btnSalvarProduto.setOnClickListener(v -> atualizarProduto());

        // Configurar botão excluir
        btnExcluirProduto.setOnClickListener(v -> confirmarExclusao());

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationUtil.configurarBottomNavigation(bottomNavigationView, R.id.nav_estoque, this);
    }

    private void carregarProduto(int idProduto) {
        ProdutoModel produtoModel = produtoController.buscarProdutoPorId(idProduto);
        if (produtoModel != null) {
            TextView tvProdutoId = findViewById(R.id.tvProdutoId);
            tvProdutoId.setText("Produto Nº #" + idProduto);

            etNomeProduto.setText(produtoModel.getNomeProduto());
            etDescricao.setText(produtoModel.getDescricaoProduto());
            etCustoUnitario.setText(String.valueOf(produtoModel.getCustoUnitario()));
            etQuantidadeEmEstoque.setText(String.valueOf(produtoModel.getQuantidadeEstoque()));
            etValorDeVenda.setText(String.valueOf(produtoModel.getValorVenda()));

            // Calcular Lucro e Margem automaticamente
            double lucro = ProdutoUtil.calcularLucro(produtoModel.getCustoUnitario(), produtoModel.getValorVenda());
            double margem = ProdutoUtil.calcularMargem(lucro, produtoModel.getValorVenda());

            etLucro.setText(ProdutoUtil.formatarDouble(lucro));
            etMargemDeLucro.setText(ProdutoUtil.formatarMargem(margem));
        }
    }

    private void atualizarProduto() {
        String nomeProduto = etNomeProduto.getText().toString().trim();
        String descricao = etDescricao.getText().toString().trim();
        String strCustoUnitario = etCustoUnitario.getText().toString().trim().replace(",", ".");
        String strQuantidadeEmEstoque = etQuantidadeEmEstoque.getText().toString().trim();
        String strValorDeVenda = etValorDeVenda.getText().toString().trim().replace(",", ".");

        if (nomeProduto.isEmpty() || strCustoUnitario.isEmpty() || strQuantidadeEmEstoque.isEmpty() || strValorDeVenda.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double custoUnitario = Double.parseDouble(strCustoUnitario);
            int quantidadeEmEstoque = Integer.parseInt(strQuantidadeEmEstoque);
            double valorDeVenda = Double.parseDouble(strValorDeVenda);

            boolean sucesso = produtoController.atualizarProduto(idProduto, nomeProduto, descricao, custoUnitario, quantidadeEmEstoque, valorDeVenda);

            if (sucesso) {
                Toast.makeText(this, "Produto atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, EstoqueActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Erro ao atualizar o produto.", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valores inválidos!", Toast.LENGTH_SHORT).show();
        }
    }

    private void confirmarExclusao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar Exclusão");
        builder.setMessage("Tem certeza de que deseja excluir este produto?");
        builder.setPositiveButton("Excluir", (dialog, which) -> excluirProduto());
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void excluirProduto() {
        boolean sucesso = produtoController.excluirProduto(idProduto);
        if (sucesso) {
            Toast.makeText(this, "Produto excluído com sucesso!", Toast.LENGTH_SHORT).show();
            // Retorna para EstoqueActivity e atualiza a lista
            Intent intent = new Intent(this, EstoqueActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Erro ao excluir o produto.", Toast.LENGTH_SHORT).show();
        }
    }
}
