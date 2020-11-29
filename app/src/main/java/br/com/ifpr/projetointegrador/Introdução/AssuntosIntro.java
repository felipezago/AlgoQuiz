package br.com.ifpr.projetointegrador.Introdução;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.ifpr.projetointegrador.Activity.MenuPrincipal;
import br.com.ifpr.projetointegrador.R;

public class AssuntosIntro extends AppCompatActivity {

    Button btnVar, btnJavaIntro, btnVolta, btnEstrutura, btnComent;
    TextView java, var, op, coment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assuntos_intro);

        btnVar= findViewById(R.id.btnVariaveis);
        btnJavaIntro= findViewById(R.id.btnJava);
        btnVolta = findViewById(R.id.btnVoltar);
        btnEstrutura = findViewById(R.id.btnOp);
        btnComent = findViewById(R.id.btnComent);
        java = findViewById(R.id.txJava);
        var= findViewById(R.id.txVar);
        op = findViewById(R.id.txOp);
        coment= findViewById(R.id.txComent);

        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntroJava intro= new IntroJava();
                avanca(intro.getClass());
            }
        });

        var.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntroVariaveis at= new IntroVariaveis();
                avanca(at.getClass());
            }
        });

        op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operadores op= new Operadores();
                avanca(op.getClass());
            }
        });

        coment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComentariosSimples cm = new ComentariosSimples();
                avanca(cm.getClass());
            }
        });

        btnVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuPrincipal mp= new MenuPrincipal();
                avanca(mp.getClass());
            }
        });

        btnVar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntroVariaveis at= new IntroVariaveis();
                avanca(at.getClass());
            }
        });

        btnJavaIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntroJava intro= new IntroJava();
                avanca(intro.getClass());
            }
        });

        btnEstrutura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operadores op= new Operadores();
                avanca(op.getClass());
            }
        });

        btnComent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComentariosSimples cm = new ComentariosSimples();
                avanca(cm.getClass());
            }
        });
    }

    public void avanca(Class classe){
        Intent intent= new Intent(this, classe);
        startActivity(intent);
    }
}
