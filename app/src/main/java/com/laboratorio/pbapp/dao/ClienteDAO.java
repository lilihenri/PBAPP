package com.laboratorio.pbapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.laboratorio.pbapp.database.DatabaseHelper;
import com.laboratorio.pbapp.model.ClienteModel;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Declara variáveis de Classe
    private final DatabaseHelper dbHelper;

    public ClienteDAO(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    public boolean adicionarCliente(String nomeCliente,
                                    String cpfCnpj,
                                    String endereco,
                                    String telefone) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.CLIENTE_NOME, nomeCliente);
        values.put(DatabaseHelper.CLIENTE_CPF_CNPJ, (cpfCnpj == null || cpfCnpj.trim().isEmpty()) ? null : cpfCnpj.trim());
        values.put(DatabaseHelper.CLIENTE_ENDERECO, (endereco == null || endereco.trim().isEmpty()) ? null : endereco.trim());
        values.put(DatabaseHelper.CLIENTE_TELEFONE, (telefone == null || telefone.trim().isEmpty()) ? null : telefone.trim());

        long result = db.insert(DatabaseHelper.TABLE_CLIENTE, null, values);
        db.close();
        return result != -1;
    }

    public boolean excluirCliente(int idCliente) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted = db.delete(DatabaseHelper.TABLE_CLIENTE, "id = ?", new String[]{String.valueOf(idCliente)});
        db.close();
        return rowsDeleted > 0;
    }

    public boolean atualizarCliente(int idCliente,
                                    String nome,
                                    String cpfCnpj,
                                    String endereco,
                                    String telefone) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.CLIENTE_NOME, nome);
        values.put(DatabaseHelper.CLIENTE_CPF_CNPJ, cpfCnpj);
        values.put(DatabaseHelper.CLIENTE_ENDERECO, endereco);
        values.put(DatabaseHelper.CLIENTE_TELEFONE, telefone);

        int result = db.update(DatabaseHelper.TABLE_CLIENTE, values, "id = ?", new String[]{String.valueOf(idCliente)});
        db.close();
        return result > 0;
    }

    public ClienteModel buscarClientePorId(int idCliente) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cliente WHERE id = ?", new String[]{String.valueOf(idCliente)});

        if (cursor != null && cursor.moveToFirst()) {
            ClienteModel cliente = new ClienteModel(
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.CLIENTE_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.CLIENTE_NOME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.CLIENTE_CPF_CNPJ)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.CLIENTE_ENDERECO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.CLIENTE_TELEFONE))
            );
            cursor.close();
            db.close();
            return cliente;
        }
        return null;
    }

    public List<ClienteModel> buscarTodosClientes() {
        List<ClienteModel> listaClientes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_CLIENTE,
                null, null, null, null, null,
                DatabaseHelper.CLIENTE_NOME + " ASC"
        );

        if (cursor.moveToFirst()) {
            do {
                ClienteModel cliente = new ClienteModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.CLIENTE_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.CLIENTE_NOME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.CLIENTE_CPF_CNPJ)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.CLIENTE_ENDERECO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.CLIENTE_TELEFONE))
                );
                listaClientes.add(cliente);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaClientes;
    }
}