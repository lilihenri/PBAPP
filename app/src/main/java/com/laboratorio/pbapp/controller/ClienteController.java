package com.laboratorio.pbapp.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.laboratorio.pbapp.database.DatabaseHelper;
import com.laboratorio.pbapp.model.ClienteModel;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private final DatabaseHelper dbHelper;

    public ClienteController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Adicionar Cliente
    public boolean adicionarCliente(String nome,
                                    String cpfCnpj,
                                    String endereco,
                                    String telefone) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.CLIENTE_NOME, nome);
        values.put(DatabaseHelper.CLIENTE_CPF_CNPJ, (cpfCnpj == null || cpfCnpj.trim().isEmpty()) ? null : cpfCnpj.trim());
        values.put(DatabaseHelper.CLIENTE_ENDERECO, (endereco == null || endereco.trim().isEmpty()) ? null : endereco.trim());
        values.put(DatabaseHelper.CLIENTE_TELEFONE, (telefone == null || telefone.trim().isEmpty()) ? null : telefone.trim());

        long resultado = db.insert(DatabaseHelper.TABLE_CLIENTE, null, values);
        db.close();
        return resultado != -1;
    }

    // Excluir Cliente
    public boolean excluirCliente(int clienteId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int resultado = db.delete(DatabaseHelper.TABLE_CLIENTE, DatabaseHelper.CLIENTE_ID + " = ?", new String[]{String.valueOf(clienteId)});
        db.close();
        return resultado > 0;
    }

    // Atualizar Cliente
    public boolean atualizarCliente(int clienteId,
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

        int resultado = db.update(DatabaseHelper.TABLE_CLIENTE, values, DatabaseHelper.CLIENTE_ID + " = ?", new String[]{String.valueOf(clienteId)});
        db.close();
        return resultado > 0;
    }

    // Buscar Cliente por ID
    public ClienteModel buscarClientePorId(int clienteId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_CLIENTE,
                null,
                DatabaseHelper.CLIENTE_ID + " = ?",
                new String[]{String.valueOf(clienteId)},
                null, null, null);

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

    // Buscar todos os clientes
    public List<ClienteModel> buscarTodosClientes() {
        List<ClienteModel> listaClientes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_CLIENTE, null);

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
