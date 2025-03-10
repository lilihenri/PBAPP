package com.laboratorio.pbapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.util.DatePickerUtil;
import com.laboratorio.pbapp.util.SpinnerUtil;
import java.util.Calendar;

public class RelatorioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        // Criar um Calendar para armazenar a data
        Calendar calendarInicial = Calendar.getInstance();
        Calendar calendarFinal = Calendar.getInstance();

        // Referência ao botão
        Button buttonDataInicial = findViewById(R.id.buttonDataInicial);
        Button buttonDataFinal = findViewById(R.id.buttonDataFinal);

        // Configurar os cliques dos botões
        buttonDataInicial.setOnClickListener(v -> DatePickerUtil.selecionarData(this, calendarInicial, buttonDataInicial));
        buttonDataFinal.setOnClickListener(v -> DatePickerUtil.selecionarData(this, calendarFinal, buttonDataFinal));

        // Configuração do Spinner de Status da Venda
        Spinner spinnerStatusVenda = findViewById(R.id.spinnerStatusVenda);
        SpinnerUtil.configurarSpinner(this, spinnerStatusVenda, new String[]{"Processada", "Cancelada"});

        // Configuração do Spinner de Cliente
        Spinner spinnerCliente = findViewById(R.id.spinnerCliente);
        SpinnerUtil.configurarSpinner(this, spinnerCliente, new String[]{"Todos os clientes"});

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_relatorio);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_clientes) {
                startActivity(new Intent(RelatorioActivity.this, ClientesActivity.class));
                return true;
            } else if (id == R.id.nav_estoque) {
                startActivity(new Intent(RelatorioActivity.this, EstoqueActivity.class));
                return true;
            } else if (id == R.id.nav_relatorio) {
                return true;
            } else if (id == R.id.nav_vendas) {
                startActivity(new Intent(RelatorioActivity.this, VendasActivity.class));
                return true;
            }

            return false;
        });
    }

}