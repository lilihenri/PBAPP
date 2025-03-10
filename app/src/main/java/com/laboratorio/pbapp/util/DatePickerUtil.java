package com.laboratorio.pbapp.util;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.Button;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatePickerUtil {

    public static void selecionarData(Context context, Calendar calendar, Button button) {
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(context, (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            // Formatar e exibir a data no botão
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            button.setText(dateFormat.format(calendar.getTime()));

        }, ano, mes, dia);
        dialog.show();
    }
}
