package br.com.ifpr.projetointegrador.Intermediário;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.ifpr.projetointegrador.Activity.MenuPrincipal;
import br.com.ifpr.projetointegrador.R;

public class AssuntosMedio extends AppCompatActivity {

    Button btnDecisao, btnDecComp, btnRep, btnVolta;
    TextView decisao, composta, rep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assuntos_medio);

        btnDecisao = findViewById(R.id.btnEst);
        btnDecComp= findViewById(R.id.btnEstComp);
        btnRep= findViewById(R.id.btnRep);
        btnVolta= findViewById(R.id.btnVoltar);
        decisao = findViewById(R.id.txEstrutura);
        composta = findViewById(R.id.txEstComp);
        rep = findViewById(R.id.txRep);

        decisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(AssuntosMedio.this, EstruturaDecisao.class);
                startActivity(in);
            }
        });

        composta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(AssuntosMedio.this, EstruturaDecisaoComposta.class);
                startActivity(in);
            }
        });

        rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(AssuntosMedio.this, EstruturaRepeticao.class);
                startActivity(in);
            }
        });

        btnDecisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(AssuntosMedio.this, EstruturaDecisao.class);
                startActivity(in);
            }
        });

        btnDecComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(AssuntosMedio.this, EstruturaDecisaoComposta.class);
                startActivity(in);
            }
        });

        btnRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(AssuntosMedio.this, EstruturaRepeticao.class);
                startActivity(in);
            }
        });

        btnVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(AssuntosMedio.this, MenuPrincipal.class);
                startActivity(in);
            }
        });

    }
}
