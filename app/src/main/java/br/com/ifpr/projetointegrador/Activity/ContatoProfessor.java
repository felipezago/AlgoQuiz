package br.com.ifpr.projetointegrador.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.ifpr.projetointegrador.Firebase.UsuarioFirebase;
import br.com.ifpr.projetointegrador.Model.Usuario;
import br.com.ifpr.projetointegrador.R;

public class ContatoProfessor extends AppCompatActivity {

    Button btn, btnEnviar;
    Usuario u = UsuarioFirebase.getDadosUsuarioLogado();
    EditText titulo, descric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_professor);

        btn = findViewById(R.id.btnVoltar);
        btnEnviar = findViewById(R.id.btnEnviar);
        titulo = findViewById(R.id.edtTitulo);
        descric = findViewById(R.id.edtDesc);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ContatoProfessor.this, MenuPrincipal.class);
                startActivity(in);
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email(v);
            }
        });

    }

    public void email(View view){
        String[] emails = new String[]{
                "bruno.guaringue@gmail.com"
        };

        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, emails);
        intent.putExtra(Intent.EXTRA_SUBJECT, titulo.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, descric.getText().toString());

        startActivity(intent);



    }
}
