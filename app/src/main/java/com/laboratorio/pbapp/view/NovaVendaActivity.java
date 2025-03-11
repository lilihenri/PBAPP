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

public class NovaVendaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novavenda);

        // Criar um Calendar para armazenar a data
        final Calendar calendar = Calendar.getInstance();

        // Referência ao botão
        Button buttonData = findViewById(R.id.buttonSelecionarData);

        // Configurar o clique no botão para abrir o DatePickerDialog
        buttonData.setOnClickListener(v -> DatePickerUtil.selecionarData(this, calendar, buttonData));

        // Configuração do Spinner de Cliente
        Spinner spinnerCliente = findViewById(R.id.spCliente);
        SpinnerUtil.configurarSpinner(this, spinnerCliente, new String[]{"Selecione um cliente"});

        // Configuração de produto
        Spinner spinnerProduto = findViewById(R.id.spinnerProduto);
        SpinnerUtil.configurarSpinner(this, spinnerProduto, new String[]{"Selecione um produto"});

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationUtil.configurarBottomNavigation(bottomNavigationView, R.id.nav_vendas, this);

    }
}
