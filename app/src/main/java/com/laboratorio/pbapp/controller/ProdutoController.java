package com.laboratorio.pbapp.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.laboratorio.pbapp.database.DatabaseHelper;
import com.laboratorio.pbapp.model.ProdutoModel;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    private final DatabaseHelper dbHelper;

    public ProdutoController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Adicionar Produto
    public boolean adicionarProduto(String nomeDoProduto,
                                    String descricao,
                                    double custoUnitario,
                                    int quantidadeEmEstoque,
                                    double valorDeVenda) {
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

    // Excluir Produto
    public boolean excluirProduto(int produtoId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted = db.delete(DatabaseHelper.TABLE_PRODUTO, "id = ?", new String[]{String.valueOf(produtoId)});
        db.close();

        return rowsDeleted > 0;
    }

    // Atualizar Produto
    public boolean atualizarProduto(int id, String nome,
                                    String descricao,
                                    double custo,
                                    int quantidade,
                                    double venda) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(DatabaseHelper.PRODUTO_NOME_DO_PRODUTO, nome);
        valores.put(DatabaseHelper.PRODUTO_DESCRICAO, descricao);
        valores.put(DatabaseHelper.PRODUTO_CUSTO_UNITARIO, custo);
        valores.put(DatabaseHelper.PRODUTO_QUANTIDADE_EM_ESTOQUE, quantidade);
        valores.put(DatabaseHelper.PRODUTO_VALOR_DE_VENDA, venda);

        int linhasAfetadas = db.update(DatabaseHelper.TABLE_PRODUTO, valores, "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return linhasAfetadas > 0;
    }

    // Buscar Produto por ID
    public ProdutoModel buscarProdutoPorId(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM produto WHERE id = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            ProdutoModel produto = new ProdutoModel(
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUTO_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUTO_NOME_DO_PRODUTO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUTO_DESCRICAO)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUTO_CUSTO_UNITARIO)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUTO_QUANTIDADE_EM_ESTOQUE)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUTO_VALOR_DE_VENDA))
            );

            cursor.close();
            db.close();
            return produto;
        }

        cursor.close();
        db.close();
        return null;
    }

    // Buscar todos os Produtos
    public List<ProdutoModel> buscarTodosProdutos() {
        List<ProdutoModel> listaProdutoModels = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_PRODUTO, null, null, null, null, null, DatabaseHelper.PRODUTO_NOME_DO_PRODUTO + " ASC");
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PRODUTO_ID));
                String nome = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PRODUTO_NOME_DO_PRODUTO));
                String descricao = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PRODUTO_DESCRICAO));
                double custoUnitario = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.PRODUTO_CUSTO_UNITARIO));
                int quantidade = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PRODUTO_QUANTIDADE_EM_ESTOQUE));
                double valorVenda = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.PRODUTO_VALOR_DE_VENDA));

                listaProdutoModels.add(new ProdutoModel(id, nome, descricao, custoUnitario, quantidade, valorVenda));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listaProdutoModels;
    }

}
