package com.laboratorio.pbapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.model.Produto;
import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {
    private final List<Produto> produtos;
    private final OnItemClickListener listener;
    private final Context context;

    public interface OnItemClickListener {
        void onItemClick(Produto produto);
    }

    public ProdutoAdapter(Context context, List<Produto> produtos, OnItemClickListener listener) {
        this.context = context;
        this.produtos = produtos;
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
        Produto produto = produtos.get(position);
        holder.tvNomeProduto.setText(produto.getNomeProduto());
        holder.tvDescricaoProduto.setText(produto.getDescricaoProduto());
        holder.tvQuantidadeEstoque.setText("Estoque: " + produto.getQuantidadeEstoque());
        holder.tvPrecoVenda.setText("Preço: R$ " + String.format("%.2f", produto.getValorVenda()));
        holder.tvCusto.setText("Preço: R$ " + String.format("%.2f", produto.getCustoUnitario()));
        holder.tvLucroProduto.setText("Lucro: R$ " + String.format("%.2f", produto.calcularLucro()));

        holder.itemView.setOnClickListener(v -> listener.onItemClick(produto));
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNomeProduto, tvDescricaoProduto, tvQuantidadeEstoque, tvPrecoVenda, tvCusto, tvLucroProduto;

        public ProdutoViewHolder(View itemView) {
            super(itemView);
            tvNomeProduto = itemView.findViewById(R.id.tvNomeProduto);
            tvDescricaoProduto = itemView.findViewById(R.id.tvDescricaoProduto);
            tvQuantidadeEstoque = itemView.findViewById(R.id.tvQuantidadeEstoque);
            tvPrecoVenda = itemView.findViewById(R.id.tvPrecoVenda);
            tvCusto = itemView.findViewById(R.id.tvCusto);
            tvLucroProduto = itemView.findViewById(R.id.tvLucroProduto);
        }

        public void bind(Produto produto, OnItemClickListener listener) {
            tvNomeProduto.setText(produto.getNomeProduto());
            tvDescricaoProduto.setText(produto.getDescricaoProduto());
            tvQuantidadeEstoque.setText(produto.getQuantidadeEstoque());
            tvPrecoVenda.setText(String.format("R$ %.2f", produto.getValorVenda()));
            tvCusto.setText(String.format("R$ %.2f", produto.getCustoUnitario()));
            double lucro = produto.getValorVenda() - produto.getCustoUnitario();
            double margem = produto.getValorVenda() > 0 ? (lucro / produto.getValorVenda()) * 100 : 0;
            tvLucroProduto.setText(String.format("R$ %.2f (%.2f%%)", lucro, margem));

            itemView.setOnClickListener(v -> listener.onItemClick(produto));
        }
    }
}
