package com.laboratorio.pbapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.util.NavigationUtil;

public class ClientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        // Referência e configura o botão de adicionar cliente
        ImageButton buttonAdicionarCliente = findViewById(R.id.buttonAdicionarClientes);
        NavigationUtil.configurarBotaoNavegacao(this, buttonAdicionarCliente, NovoClienteActivity.class);

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_clientes);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_clientes) {
                return true;
            } else if (id == R.id.nav_estoque) {
                startActivity(new Intent(ClientesActivity.this, EstoqueActivity.class));
                return true;
            } else if (id == R.id.nav_relatorio) {
                startActivity(new Intent(ClientesActivity.this, RelatorioActivity.class));
                return true;
            } else if (id == R.id.nav_vendas) {
                startActivity(new Intent(ClientesActivity.this, VendasActivity.class));
                return true;
            }

            return false;
        });
    }
}