package com.laboratorio.pbapp.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;

public class NovoProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novoproduto);

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_estoque);

        // Configura o listener para a navegação
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_clientes) {
                startActivity(new Intent(NovoProdutoActivity.this, ClientesActivity.class));
                return true;
            } else if (id == R.id.nav_estoque) {
                startActivity(new Intent(NovoProdutoActivity.this, EstoqueActivity.class));
                return true;
            } else if (id == R.id.nav_relatorio) {
                startActivity(new Intent(NovoProdutoActivity.this, RelatorioActivity.class));
                return true;
            } else if (id == R.id.nav_vendas) {
                startActivity(new Intent(NovoProdutoActivity.this, VendasActivity.class));
                return true;
            }

            return false;
        });

    }
}
