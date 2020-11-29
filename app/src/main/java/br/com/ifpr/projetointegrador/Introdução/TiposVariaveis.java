package br.com.ifpr.projetointegrador.Introdução;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.ifpr.projetointegrador.R;

public class TiposVariaveis extends AppCompatActivity{

    Button btnVoltar, btnVolta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos_variaveis);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnVolta = findViewById(R.id.btnProx);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte= new Intent(TiposVariaveis.this, IntroVariaveis.class);
                startActivity(inte);
            }
        });


        btnVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TiposVariaveis.this, Operadores.class);
                startActivity(intent);
            }
        });


    }

}
