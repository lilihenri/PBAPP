package com.laboratorio.pbapp.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.controller.ProdutoController;
import com.laboratorio.pbapp.util.BottomNavigationUtil;

public class ProdutoActivity extends AppCompatActivity {

    private ProdutoController produtoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        produtoController = new ProdutoController(this);

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationUtil.configurarBottomNavigation(bottomNavigationView, R.id.nav_estoque, this);

    }
}
