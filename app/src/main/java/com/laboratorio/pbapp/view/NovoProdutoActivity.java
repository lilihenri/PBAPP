package com.laboratorio.pbapp.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.controller.ProdutoController;
import com.laboratorio.pbapp.util.BottomNavigationUtil;
import com.laboratorio.pbapp.util.ProdutoTextWatcherUtil;

public class NovoProdutoActivity extends AppCompatActivity {

    private EditText etNomeDoProduto, etDescricao, etCustoUnitario, etQuantidadeEmEstoque, etValorDeVenda, etMargemDeLucro, etLucro;
    private Button btnSalvarNovoProduto;
    private ProdutoController produtoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novoproduto);

        produtoController = new ProdutoController(this);

        etNomeDoProduto = findViewById(R.id.etNomeDoProduto);
        etDescricao = findViewById(R.id.etDescricao);
        etCustoUnitario = findViewById(R.id.etCustoUnitario);
        etQuantidadeEmEstoque = findViewById(R.id.etQuantidadeEmEstoque);
        etValorDeVenda = findViewById(R.id.etValorDeVenda);
        etMargemDeLucro = findViewById(R.id.etMargemDeLucro);
        etLucro = findViewById(R.id.etLucro);
        btnSalvarNovoProduto = findViewById(R.id.btnSalvarNovoProduto);

        btnSalvarNovoProduto.setOnClickListener(v -> salvarProduto());

        ProdutoTextWatcherUtil watcher = new ProdutoTextWatcherUtil(etCustoUnitario, etValorDeVenda, etLucro, etMargemDeLucro);
        etCustoUnitario.addTextChangedListener(watcher);
        etValorDeVenda.addTextChangedListener(watcher);

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationUtil.configurarBottomNavigation(bottomNavigationView, R.id.nav_estoque, this);

    }

    private void salvarProduto() {
        String strNomeDoProduto = etNomeDoProduto.getText().toString().trim();
        String strDescricao = etDescricao.getText().toString().trim();
        String strCustoUnitario = etCustoUnitario.getText().toString().trim().replace(",", ".");
        String strQuantidadeEmEstoque = etQuantidadeEmEstoque.getText().toString().trim();
        String strValorDeVenda = etValorDeVenda.getText().toString().trim().replace(",", ".");

        if (strNomeDoProduto.isEmpty() || strCustoUnitario.isEmpty() || strQuantidadeEmEstoque.isEmpty() || strValorDeVenda.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios!", Toast.LENGTH_SHORT).show();
            return;
        }

        double doubleCustoUnitario;
        int intQuantidadeEmEstoque;
        double doubleValorDeVenda;

        try {
            doubleCustoUnitario = Double.parseDouble(strCustoUnitario);
            intQuantidadeEmEstoque = Integer.parseInt(strQuantidadeEmEstoque);
            doubleValorDeVenda = Double.parseDouble(strValorDeVenda);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valores numéricos inválidos!", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean sucesso = produtoController.adicionarProduto(strNomeDoProduto, strDescricao, doubleCustoUnitario, intQuantidadeEmEstoque, doubleValorDeVenda);

        if (sucesso) {
            Toast.makeText(this, "Produto salvo com sucesso!", Toast.LENGTH_LONG).show();
            limparCampos();
        } else {
            Toast.makeText(this, "Erro ao salvar o produto.", Toast.LENGTH_LONG).show();
        }
    }

    private void limparCampos() {
        etNomeDoProduto.setText("");
        etDescricao.setText("");
        etCustoUnitario.setText("");
        etQuantidadeEmEstoque.setText("");
        etValorDeVenda.setText("");
        etLucro.setText("");
        etMargemDeLucro.setText("");
    }

}
