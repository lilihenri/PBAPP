package com.laboratorio.pbapp.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.adapter.interfaces.OnItemClickListener;
import com.laboratorio.pbapp.model.ProdutoModel;
import java.util.Locale;

public class ProdutoViewHolder extends RecyclerView.ViewHolder {

    // Declara variáveis de Classe
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

    public void bind(ProdutoModel produtoModel, OnItemClickListener listener) {
        double lucro = produtoModel.getValorVenda() - produtoModel.getCustoUnitario();
        double margem = produtoModel.getValorVenda() > 0 ? (lucro / produtoModel.getValorVenda()) * 100 : 0;

        tvNomeProduto.setText(produtoModel.getNomeProduto());
        tvDescricaoProduto.setText(produtoModel.getDescricaoProduto());
        tvQuantidadeEstoque.setText(String.valueOf(produtoModel.getQuantidadeEstoque()));
        tvPrecoVenda.setText(String.format(Locale.getDefault(), "R$ %.2f", produtoModel.getValorVenda()));
        tvCusto.setText(String.format(Locale.getDefault(), "R$ %.2f", produtoModel.getCustoUnitario()));
        tvLucroProduto.setText(String.format(Locale.getDefault(), "R$ %.2f (%.2f%%)", lucro, margem));

        itemView.setOnClickListener(v -> listener.onItemClick(produtoModel));
    }
}
