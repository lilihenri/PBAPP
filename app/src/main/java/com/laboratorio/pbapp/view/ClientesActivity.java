package com.laboratorio.pbapp.view;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.util.BottomNavigationUtil;
import com.laboratorio.pbapp.util.NavigationUtil;

public class ClientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        // Referência e configura o botão de adicionar cliente
        ImageButton buttonAdicionarCliente = findViewById(R.id.btnAdicionarClientes);
        NavigationUtil.configurarBotaoNavegacao(this, buttonAdicionarCliente, NovoClienteActivity.class);

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationUtil.configurarBottomNavigation(bottomNavigationView, R.id.nav_clientes, this);

    }
}