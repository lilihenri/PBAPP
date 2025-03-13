package com.laboratorio.pbapp.view;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laboratorio.pbapp.R;
import com.laboratorio.pbapp.controller.ClienteController;
import com.laboratorio.pbapp.util.BottomNavigationUtil;


public class NovoClienteActivity extends AppCompatActivity {

    private Button btnSalvarNovoCliente;
    private ClienteController clienteController;
    private EditText editTextNome, editTextCPFCNPJ, editTextEndereço, editTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novocliente);

        clienteController = new ClienteController(this);

        editTextNome = findViewById(R.id.editTextNome);
        editTextCPFCNPJ = findViewById(R.id.editTextCPFCNPJ);
        editTextEndereço = findViewById(R.id.editTextEndereço);
        editTelefone = findViewById(R.id.editTelefone);
        btnSalvarNovoCliente = findViewById(R.id.btnSalvarNovoCliente);

        btnSalvarNovoCliente.setOnClickListener(v -> salvarCliente());

        // Apresenta o Menu no canto inferior da página
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationUtil.configurarBottomNavigation(bottomNavigationView, R.id.nav_clientes, this);

    }

    private void salvarCliente() {
        String nome = editTextNome.getText().toString().trim();
        String cpfCnpj = editTextCPFCNPJ.getText().toString().trim();
        String endereco = editTextEndereço.getText().toString().trim();
        String telefone = editTelefone.getText().toString().trim();

        if (nome.isEmpty()) {
            Toast.makeText(this, "O campo Nome é obrigatório!", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean sucesso = clienteController.adicionarCliente(
                nome,
                cpfCnpj.isEmpty() ? null : cpfCnpj,
                endereco.isEmpty() ? null : endereco,
                telefone.isEmpty() ? null : telefone
        );

        if (sucesso) {
            Toast.makeText(this, "Cliente salvo com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Erro ao salvar o cliente.", Toast.LENGTH_SHORT).show();
        }
    }

}
