package com.laboratorio.pbapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.util.NavigationUtil;

public class EstoqueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        // Referência e configura o botão de adicionar estoque
        ImageButton buttonAdicionarEstoque = findViewById(R.id.buttonAdicionarEstoque);
        NavigationUtil.configurarBotaoNavegacao(this, buttonAdicionarEstoque, NovoProdutoActivity.class);

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_estoque);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_clientes) {
                startActivity(new Intent(EstoqueActivity.this, ClientesActivity.class));
                return true;
            } else if (id == R.id.nav_estoque) {
                return true;
            } else if (id == R.id.nav_relatorio) {
                startActivity(new Intent(EstoqueActivity.this, RelatorioActivity.class));
                return true;
            } else if (id == R.id.nav_vendas) {
                startActivity(new Intent(EstoqueActivity.this, VendasActivity.class));
                return true;
            }

            return false;
        });
    }
}