package com.laboratorio.pbapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.laboratorio.pbapp.database.DatabaseHelper;
import com.laboratorio.pbapp.model.ProdutoModel;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // Declara variáveis de Classe
    private final DatabaseHelper dbHelper;

    public ProdutoDAO(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    public boolean adicionarProduto(String nomeProduto,
                                    String descricaoProduto,
                                    double custoUnitario,
                                    int quantidade,
                                    double valorVenda) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.PRODUTO_NOME_DO_PRODUTO, nomeProduto);
        values.put(DatabaseHelper.PRODUTO_DESCRICAO, (descricaoProduto == null || descricaoProduto.trim().isEmpty()) ? null : descricaoProduto.trim());
        values.put(DatabaseHelper.PRODUTO_CUSTO_UNITARIO, custoUnitario);
        values.put(DatabaseHelper.PRODUTO_QUANTIDADE_EM_ESTOQUE, quantidade);
        values.put(DatabaseHelper.PRODUTO_VALOR_DE_VENDA, valorVenda);

        long result = db.insert(DatabaseHelper.TABLE_PRODUTO, null, values);
        db.close();
        return result != -1;
    }

    public boolean excluirProduto(int idProduto) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted = db.delete(DatabaseHelper.TABLE_PRODUTO, "id = ?", new String[]{String.valueOf(idProduto)});
        db.close();
        return rowsDeleted > 0;
    }

    public boolean atualizarProduto(int idProduto, String nomeProduto, String descricaoProduto, double custo, int quantidade, double venda) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.PRODUTO_NOME_DO_PRODUTO, nomeProduto);
        values.put(DatabaseHelper.PRODUTO_DESCRICAO, descricaoProduto);
        values.put(DatabaseHelper.PRODUTO_CUSTO_UNITARIO, custo);
        values.put(DatabaseHelper.PRODUTO_QUANTIDADE_EM_ESTOQUE, quantidade);
        values.put(DatabaseHelper.PRODUTO_VALOR_DE_VENDA, venda);

        int result = db.update(DatabaseHelper.TABLE_PRODUTO, values, "id = ?", new String[]{String.valueOf(idProduto)});
        db.close();
        return result > 0;
    }

    public ProdutoModel buscarProdutoPorId(int idProduto) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM produto WHERE id = ?", new String[]{String.valueOf(idProduto)});

        if (cursor.moveToFirst()) {
            ProdutoModel produtoModel = new ProdutoModel(
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUTO_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUTO_NOME_DO_PRODUTO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUTO_DESCRICAO)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUTO_CUSTO_UNITARIO)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUTO_QUANTIDADE_EM_ESTOQUE)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUTO_VALOR_DE_VENDA))
            );
            cursor.close();
            db.close();
            return produtoModel;
        }

        cursor.close();
        db.close();
        return null;
    }

    public List<ProdutoModel> buscarTodosProdutos() {
        List<ProdutoModel> listaProdutoModels = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_PRODUTO,
                null, null, null, null, null,
                DatabaseHelper.PRODUTO_NOME_DO_PRODUTO + " ASC"
        );
        if (cursor.moveToFirst()) {
            do {
                listaProdutoModels.add(new ProdutoModel(
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PRODUTO_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.PRODUTO_NOME_DO_PRODUTO)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.PRODUTO_DESCRICAO)),
                        cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.PRODUTO_CUSTO_UNITARIO)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PRODUTO_QUANTIDADE_EM_ESTOQUE)),
                        cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.PRODUTO_VALOR_DE_VENDA))
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaProdutoModels;
    }
}