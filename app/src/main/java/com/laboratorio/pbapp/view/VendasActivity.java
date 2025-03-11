package com.laboratorio.pbapp.view;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.util.BottomNavigationUtil;
import com.laboratorio.pbapp.util.NavigationUtil;

public class VendasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas);

        // Referência e configura o botão de adicionar cliente
        ImageButton buttonAdicionarVenda = findViewById(R.id.btnAdicionarVenda);
        NavigationUtil.configurarBotaoNavegacao(this, buttonAdicionarVenda, NovaVendaActivity.class);

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationUtil.configurarBottomNavigation(bottomNavigationView, R.id.nav_vendas, this);

    }
}