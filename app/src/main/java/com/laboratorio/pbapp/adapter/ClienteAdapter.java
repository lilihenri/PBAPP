package com.laboratorio.pbapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.adapter.interfaces.OnClienteClickListener;
import com.laboratorio.pbapp.model.ClienteModel;
import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteViewHolder> {

    // Declara variáveis de Classe
    private final Context context;
    private List<ClienteModel> clienteModel;
    private final OnClienteClickListener listener;

    public ClienteAdapter(Context context, List<ClienteModel> clientes, OnClienteClickListener listener) {
        this.context = context;
        this.clienteModel = clientes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente, parent, false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        ClienteModel clienteModel = this.clienteModel.get(position);
        holder.bind(clienteModel, listener);
    }

    public void atualizarLista(List<ClienteModel> novaLista) {
        this.clienteModel.clear();
        this.clienteModel.addAll(novaLista);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return clienteModel.size();
    }

}