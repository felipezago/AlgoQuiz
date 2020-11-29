package br.com.ifpr.projetointegrador.Avançado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.ifpr.projetointegrador.Activity.MenuPrincipal;
import br.com.ifpr.projetointegrador.R;

public class AssuntosAv extends AppCompatActivity {

    Button btnVolta, btnMetodo, btnExc, btnArr, btnForEach;
    Intent in;
    TextView excp, metodos, foreach, arrays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assuntos_av);

        btnVolta = findViewById(R.id.btnVoltar);
        btnMetodo= findViewById(R.id.btnMetodos);
        btnExc = findViewById(R.id.btnExcep);
        btnArr = findViewById(R.id.btnLista);
        btnForEach= findViewById(R.id.btnForEach);
        excp = findViewById(R.id.txExcep);
        metodos = findViewById(R.id.txMetodos);
        foreach = findViewById(R.id.txForEach);
        arrays = findViewById(R.id.txLista);

        excp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in= new Intent(AssuntosAv.this, Exceptions.class);
                startActivity(in);
            }
        });

        metodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in= new Intent(AssuntosAv.this, Metodos.class);
                startActivity(in);
            }
        });

        foreach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in= new Intent(AssuntosAv.this, ForEach.class);
                startActivity(in);
            }
        });

        arrays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in= new Intent(AssuntosAv.this, Array.class);
                startActivity(in);
            }
        });

        btnVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in= new Intent(AssuntosAv.this, MenuPrincipal.class);
                startActivity(in);
            }
        });

        btnMetodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in= new Intent(AssuntosAv.this, Metodos.class);
                startActivity(in);
            }
        });

        btnExc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in= new Intent(AssuntosAv.this, Exceptions.class);
                startActivity(in);
            }
        });

        btnArr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in= new Intent(AssuntosAv.this, Array.class);
                startActivity(in);
            }
        });

        btnForEach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in= new Intent(AssuntosAv.this, ForEach.class);
                startActivity(in);
            }
        });

    }
}
