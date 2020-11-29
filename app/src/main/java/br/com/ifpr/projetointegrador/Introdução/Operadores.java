package br.com.ifpr.projetointegrador.Introdução;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.ifpr.projetointegrador.Questoes.QuestoesIntroducao;
import br.com.ifpr.projetointegrador.R;

public class Operadores extends AppCompatActivity {

    Button btn, btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operadores);

        btn = findViewById(R.id.btnProx);
        btnVoltar = findViewById(R.id.btnVoltar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta();
            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(Operadores.this, AssuntosIntro.class);
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
                Intent intent= new Intent(Operadores.this, QuestoesIntroducao.class);
                startActivity(intent);
            }
        });

        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent= new Intent(Operadores.this, AssuntosIntro.class);
                startActivity(intent);
            }
        });

        alerta.show();
    }

}
