package com.laboratorio.pbapp.util;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageButton;

public class NavigationUtil {

    public static void configurarBotaoNavegacao(Activity activity, ImageButton button, Class<?> destino) {
        button.setOnClickListener(v -> {
            Intent intent = new Intent(activity, destino);
            activity.startActivity(intent);
        });
    }
}
