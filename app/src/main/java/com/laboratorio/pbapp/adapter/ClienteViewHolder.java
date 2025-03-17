package com.laboratorio.pbapp.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.adapter.interfaces.OnClienteClickListener;
import com.laboratorio.pbapp.model.ClienteModel;

public class ClienteViewHolder extends RecyclerView.ViewHolder {

    // Declara variáveis de Classe
    private final TextView tvNomeCliente;

    public ClienteViewHolder(View itemView) {
        super(itemView);
        tvNomeCliente = itemView.findViewById(R.id.tvNomeCliente);
    }

    public void bind(ClienteModel clienteModel, OnClienteClickListener listener) {
        tvNomeCliente.setText(clienteModel.getNomeCliente());

        itemView.setOnClickListener(v -> listener.onClienteClick(clienteModel));
    }

}
