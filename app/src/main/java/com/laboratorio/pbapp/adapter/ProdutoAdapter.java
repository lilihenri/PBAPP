package com.laboratorio.pbapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.adapter.interfaces.OnItemClickListener;
import com.laboratorio.pbapp.model.ProdutoModel;
import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoViewHolder> {

    // Declara variáveis de Classe
    private final Context context;
    private final List<ProdutoModel> produtosModel;
    private final OnItemClickListener listener;

    public ProdutoAdapter(Context context, List<ProdutoModel> produtosModel, OnItemClickListener listener) {
        this.context = context;
        this.produtosModel = produtosModel;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        ProdutoModel produtoModel = produtosModel.get(position);
        holder.bind(produtoModel, listener);
    }

    public void atualizarLista(List<ProdutoModel> novaLista) {
        this.produtosModel.clear();
        this.produtosModel.addAll(novaLista);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return produtosModel.size();
    }

}
