package com.laboratorio.pbapp.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.laboratorio.pbapp.database.DatabaseHelper;
import com.laboratorio.pbapp.model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    private final DatabaseHelper dbHelper;

    public ProdutoController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean adicionarProduto(String nomeDoProduto, String descricao, double custoUnitario, int quantidadeEmEstoque, double valorDeVenda) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.PRODUTO_NOME_DO_PRODUTO, nomeDoProduto);
        valores.put(DatabaseHelper.PRODUTO_DESCRICAO, descricao);
        valores.put(DatabaseHelper.PRODUTO_CUSTO_UNITARIO, custoUnitario);
        valores.put(DatabaseHelper.PRODUTO_QUANTIDADE_EM_ESTOQUE, quantidadeEmEstoque);
        valores.put(DatabaseHelper.PRODUTO_VALOR_DE_VENDA, valorDeVenda);

        long resultado = db.insert(DatabaseHelper.TABLE_PRODUTO, null, valores);
        db.close();

        return resultado != -1;
    }

    public List<Produto> buscarTodosProdutos() {
        List<Produto> listaProdutos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_PRODUTO, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PRODUTO_ID));
                String nome = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PRODUTO_NOME_DO_PRODUTO));
                String descricao = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PRODUTO_DESCRICAO));
                double custoUnitario = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.PRODUTO_CUSTO_UNITARIO));
                int quantidade = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PRODUTO_QUANTIDADE_EM_ESTOQUE));
                double valorVenda = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.PRODUTO_VALOR_DE_VENDA));

                listaProdutos.add(new Produto(id, nome, descricao, custoUnitario, quantidade, valorVenda));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listaProdutos;
    }

}
