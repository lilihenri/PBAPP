package com.laboratorio.pbapp.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.util.BottomNavigationUtil;
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
        Button buttonDataInicial = findViewById(R.id.btnDataInicial);
        Button buttonDataFinal = findViewById(R.id.btnDataFinal);

        // Configurar os cliques dos botões
        buttonDataInicial.setOnClickListener(v -> DatePickerUtil.selecionarData(this, calendarInicial, buttonDataInicial));
        buttonDataFinal.setOnClickListener(v -> DatePickerUtil.selecionarData(this, calendarFinal, buttonDataFinal));

        // Configuração do Spinner de Status da Venda
        Spinner spinnerStatusVenda = findViewById(R.id.spStatusVenda);
        SpinnerUtil.configurarSpinner(this, spinnerStatusVenda, new String[]{"Processada", "Cancelada"});

        // Configuração do Spinner de Cliente
        Spinner spinnerCliente = findViewById(R.id.spCliente);
        SpinnerUtil.configurarSpinner(this, spinnerCliente, new String[]{"Todos os clientes"});

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationUtil.configurarBottomNavigation(bottomNavigationView, R.id.nav_relatorio, this);

    }
}