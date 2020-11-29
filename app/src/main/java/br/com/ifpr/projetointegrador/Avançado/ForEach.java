package br.com.ifpr.projetointegrador.Avançado;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.ifpr.projetointegrador.Intermediário.For;
import br.com.ifpr.projetointegrador.Introdução.AssuntosIntro;
import br.com.ifpr.projetointegrador.Introdução.Operadores;
import br.com.ifpr.projetointegrador.Questoes.QuestoesAv;
import br.com.ifpr.projetointegrador.Questoes.QuestoesIntroducao;
import br.com.ifpr.projetointegrador.R;

public class ForEach extends AppCompatActivity {

    Button btn, btnSair;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_each);

        btn = findViewById(R.id.btnProx);
        btnSair = findViewById(R.id.btnVoltar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               alerta();
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(ForEach.this, AssuntosAv.class);
                startActivity(in);
            }
        });

    }

    public void alerta(){

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(R.drawable.ic_info);
        alerta.setTitle("Confirmação");
        alerta.setMessage("Você deseja realizar o teste agora?");

        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent= new Intent(ForEach.this, QuestoesAv.class);
                startActivity(intent);
            }
        });

        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent= new Intent(ForEach.this, AssuntosAv.class);
                startActivity(intent);
            }
        });

        alerta.show();
    }
}
