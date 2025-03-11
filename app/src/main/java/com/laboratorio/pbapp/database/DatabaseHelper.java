package com.laboratorio.pbapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "estoque.db";
    private static final int DB_VERSION = 2;

    public static final String TABLE_PRODUTO = "produto";
    public static final String TABLE_CLIENTE = "cliente";

    // Colunas tabela Produto
    public static final String PRODUTO_ID = "id";
    public static final String PRODUTO_NOME_DO_PRODUTO = "nome_do_produto";
    public static final String PRODUTO_DESCRICAO = "descricao";
    public static final String PRODUTO_CUSTO_UNITARIO = "custo_unitario";
    public static final String PRODUTO_QUANTIDADE_EM_ESTOQUE = "quantidade_em_estoque";
    public static final String PRODUTO_VALOR_DE_VENDA = "valor_de_venda";

    // Colunas tabela Cliente
    public static final String CLIENTE_ID = "id";
    public static final String CLIENTE_NOME = "nome";
    public static final String CLIENTE_CPF_CNPJ = "cpf_cnpj";
    public static final String CLIENTE_ENDERECO = "endereco";
    public static final String CLIENTE_TELEFONE = "telefone";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        criarTabelaProduto(db);
        criarTabelaCliente(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (!isTableExists(db, TABLE_PRODUTO)) {
            criarTabelaProduto(db);
        }
        if (!isTableExists(db, TABLE_CLIENTE)) {
            criarTabelaCliente(db);
        }
    }

    private void criarTabelaProduto(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_PRODUTO + " (" +
                PRODUTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRODUTO_NOME_DO_PRODUTO + " TEXT NOT NULL, " +
                PRODUTO_DESCRICAO + " TEXT, " +
                PRODUTO_CUSTO_UNITARIO + " REAL NOT NULL, " +
                PRODUTO_QUANTIDADE_EM_ESTOQUE + " INTEGER NOT NULL, " +
                PRODUTO_VALOR_DE_VENDA + " REAL NOT NULL);";
        db.execSQL(sql);
    }

    private void criarTabelaCliente(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_CLIENTE + " (" +
                CLIENTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CLIENTE_NOME + " TEXT NOT NULL, " +
                CLIENTE_CPF_CNPJ + " TEXT, " +
                CLIENTE_ENDERECO + " TEXT, " +
                CLIENTE_TELEFONE + " TEXT);";
        db.execSQL(sql);
    }

    private boolean isTableExists(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name=?",
                new String[]{tableName});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

}
