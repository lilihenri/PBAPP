package com.laboratorio.pbapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.controller.ClienteController;
import com.laboratorio.pbapp.model.ClienteModel;
import com.laboratorio.pbapp.util.BottomNavigationUtil;
import com.laboratorio.pbapp.util.CpfCnpjUtil;
import com.laboratorio.pbapp.util.TelefoneUtil;

public class ClienteActivity extends AppCompatActivity {

    // Declara variáveis de Classe
    private ClienteController clienteController;
    private EditText etNomeCliente, etCPFCNPJ, etEndereço, etTelefone;
    private Button btnSalvarCliente, btnExcluirCluente;
    private int idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        // Recuperar ID do cliente da Intent
        idCliente = getIntent().getIntExtra("cliente_id", -1);
        if (idCliente == -1) {
            Toast.makeText(this, "Erro ao carregar cliente", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        etNomeCliente = findViewById(R.id.etNomeDoClienteEdição);
        etCPFCNPJ = findViewById(R.id.etCPFCNPJEdição);
        etEndereço = findViewById(R.id.etEndereçoEdição);
        etTelefone = findViewById(R.id.etTelefoneEdição);
        btnSalvarCliente = findViewById(R.id.btnSalvarClienteEdição);
        btnExcluirCluente = findViewById(R.id.btnExcluirClienteEdição);

        clienteController = new ClienteController(this);

        // Buscar os dados do cliente no banco e preencher os campos
        carregarCliente(idCliente);

        // Configurar botão salvar
        btnSalvarCliente.setOnClickListener(v -> atualizarCliente());

        // Configurar botão excluir
        btnExcluirCluente.setOnClickListener(v -> confirmarExclusao());

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationUtil.configurarBottomNavigation(bottomNavigationView, R.id.nav_clientes, this);

    }

    private void carregarCliente(int idCliente) {
        ClienteModel clienteModel = clienteController.buscarClientePorId(idCliente);
        if (clienteModel != null) {
            TextView tvClienteId = findViewById(R.id.tvClienteId);
            tvClienteId.setText("Cliente Nº #" + idCliente);

            etNomeCliente.setText(clienteModel.getNomeCliente());
            etCPFCNPJ.setText(clienteModel.getCpfCnpj());
            etEndereço.setText(clienteModel.getEndereco());
            etTelefone.setText(clienteModel.getTelefone());
        }
    }

    private void atualizarCliente() {
        String nomeCliente = etNomeCliente.getText().toString().trim();
        String cpfCnpj = etCPFCNPJ.getText().toString().trim();
        String endereco = etEndereço.getText().toString().trim();
        String telefone =  TelefoneUtil.removerNaoNumericos(etTelefone.getText().toString().trim());

        if (nomeCliente.isEmpty()) {
            Toast.makeText(this, "Nome do Cliente é obrigatórios!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!cpfCnpj.isEmpty()) {
            if (cpfCnpj.length() == 11) {
                if (!CpfCnpjUtil.isCPFouCNPJValido(cpfCnpj)) {
                    Toast.makeText(this, "CPF inválido!", Toast.LENGTH_SHORT).show();
                    etCPFCNPJ.setError("CPF inválido!");
                    return;
                }
                cpfCnpj = CpfCnpjUtil.formatarCpf(cpfCnpj);
            } else if (cpfCnpj.length() == 14) {
                if (!CpfCnpjUtil.isCPFouCNPJValido(cpfCnpj)) {
                    Toast.makeText(this, "CNPJ inválido!", Toast.LENGTH_SHORT).show();
                    etCPFCNPJ.setError("CNPJ inválido!");
                    return;
                }
                cpfCnpj = CpfCnpjUtil.formatarCnpj(cpfCnpj);
            } else {
                Toast.makeText(this, "CPF/CNPJ inválido!", Toast.LENGTH_SHORT).show();
                etCPFCNPJ.setError("CPF/CNPJ inválido!");
                return;
            }
        }

        if (!telefone.isEmpty()) {
            if (telefone.length() != 10 && telefone.length() != 11) {
                Toast.makeText(this, "Telefone inválido!", Toast.LENGTH_SHORT).show();
                etTelefone.setError("Telefone inválido!");
                return;
            }
            telefone = TelefoneUtil.formatarTelefone(telefone);
        }

        boolean sucesso = clienteController.atualizarCliente(idCliente, nomeCliente, cpfCnpj, endereco, telefone);

        if(sucesso) {
            Toast.makeText(this, "Cliente atualizado com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ClientesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Erro ao atualizar o cliente.", Toast.LENGTH_SHORT).show();
        }
    }

    private void confirmarExclusao() {AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar Exclusão");
        builder.setMessage("Tem certeza de que deseja excluir este cliente?");
        builder.setPositiveButton("Excluir", (dialog, which) -> excluirCliente());
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void excluirCliente() {
        boolean sucesso = clienteController.excluirCliente(idCliente);
        if(sucesso) {
            Toast.makeText(this, "Cliente excluído com sucesso!", Toast.LENGTH_SHORT).show();
            // Retorna para ClientesActivity e atualiza a lista
            Intent intent = new Intent(this, ClientesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

}
