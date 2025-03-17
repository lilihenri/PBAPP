package com.laboratorio.pbapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.controller.ClienteController;
import com.laboratorio.pbapp.util.BottomNavigationUtil;
import com.laboratorio.pbapp.util.CpfCnpjUtil;
import com.laboratorio.pbapp.util.TelefoneUtil;


public class NovoClienteActivity extends AppCompatActivity {

    // Declara variáveis de Classe
    private Button btnSalvarNovoCliente;
    private ClienteController clienteController;
    private EditText etNome, etCPFCNPJ, etEndereço, etTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novocliente);

        clienteController = new ClienteController(this);

        etNome = findViewById(R.id.etNomeCliente);
        etCPFCNPJ = findViewById(R.id.etCPFCNPJ);
        etEndereço = findViewById(R.id.etEndereço);
        etTelefone = findViewById(R.id.etTelefone);
        btnSalvarNovoCliente = findViewById(R.id.btnSalvarNovoCliente);

        btnSalvarNovoCliente.setOnClickListener(v -> salvarCliente());

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationUtil.configurarBottomNavigation(bottomNavigationView, R.id.nav_clientes, this);

    }

    private void salvarCliente() {
        String nome = etNome.getText().toString().trim();
        String cpfCnpj = etCPFCNPJ.getText().toString().trim();
        String endereco = etEndereço.getText().toString().trim();
        String telefone = TelefoneUtil.removerNaoNumericos(etTelefone.getText().toString().trim());

        if (nome.isEmpty()) {
            Toast.makeText(this, "O campo Nome é obrigatório!", Toast.LENGTH_SHORT).show();
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

        boolean sucesso = clienteController.adicionarCliente(
                nome,
                cpfCnpj,
                endereco.isEmpty() ? null : endereco,
                telefone
        );

        if (sucesso) {
            Toast.makeText(this, "Cliente salvo com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ClientesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Erro ao salvar o cliente.", Toast.LENGTH_SHORT).show();
        }

    }

}
