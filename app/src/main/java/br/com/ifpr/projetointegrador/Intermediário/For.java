package br.com.ifpr.projetointegrador.Intermediário;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.ifpr.projetointegrador.Introdução.AssuntosIntro;
import br.com.ifpr.projetointegrador.Introdução.Operadores;
import br.com.ifpr.projetointegrador.Questoes.QuestoesIntroducao;
import br.com.ifpr.projetointegrador.Questoes.QuestoesMedio;
import br.com.ifpr.projetointegrador.R;

public class For extends AppCompatActivity {

    Button btn, btnVolta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for);

        btn = findViewById(R.id.btnProx);
        btnVolta = findViewById(R.id.btnVoltar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta();
            }
        });
        btnVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(For.this, AssuntosMedio.class);
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
                Intent intent= new Intent(For.this, QuestoesMedio.class);
                startActivity(intent);
            }
        });

        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent= new Intent(For.this, AssuntosMedio.class);
                startActivity(intent);
            }
        });

        alerta.show();
    }
}
