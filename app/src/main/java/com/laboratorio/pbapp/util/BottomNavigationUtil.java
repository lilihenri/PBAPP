package com.laboratorio.pbapp.util;

import android.app.Activity;
import android.content.Intent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.view.ClientesActivity;
import com.laboratorio.pbapp.view.EstoqueActivity;
import com.laboratorio.pbapp.view.RelatorioActivity;
import com.laboratorio.pbapp.view.VendasActivity;

public class BottomNavigationUtil {

    public static void configurarBottomNavigation(BottomNavigationView bottomNavigationView, int itemSelecionado, Activity activity) {

        bottomNavigationView.setSelectedItemId(itemSelecionado);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_clientes && !(activity instanceof ClientesActivity)) {
                activity.startActivity(new Intent(activity, ClientesActivity.class));
                return true;
            } else if (id == R.id.nav_estoque && !(activity instanceof EstoqueActivity)) {
                activity.startActivity(new Intent(activity, EstoqueActivity.class));
                return true;
            } else if (id == R.id.nav_relatorio && !(activity instanceof RelatorioActivity)) {
                activity.startActivity(new Intent(activity, RelatorioActivity.class));
                return true;
            } else if (id == R.id.nav_vendas && !(activity instanceof VendasActivity)) {
                activity.startActivity(new Intent(activity, VendasActivity.class));
                return true;
            }

            return false;
        });
    }
}
