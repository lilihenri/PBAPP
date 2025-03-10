package com.laboratorio.pbapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.util.NavigationUtil;

public class VendasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas);

        // Referência e configura o botão de adicionar cliente
        ImageButton buttonAdicionarVenda = findViewById(R.id.buttonAdicionarVenda);
        NavigationUtil.configurarBotaoNavegacao(this, buttonAdicionarVenda, NovaVendaActivity.class);

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_vendas);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_clientes) {
                startActivity(new Intent(VendasActivity.this, ClientesActivity.class));
                return true;
            } else if (id == R.id.nav_estoque) {
                startActivity(new Intent(VendasActivity.this, EstoqueActivity.class));
                return true;
            } else if (id == R.id.nav_relatorio) {
                startActivity(new Intent(VendasActivity.this, RelatorioActivity.class));
                return true;
            } else if (id == R.id.nav_vendas) {
                return true;
            }

            return false;
        });
    }
}