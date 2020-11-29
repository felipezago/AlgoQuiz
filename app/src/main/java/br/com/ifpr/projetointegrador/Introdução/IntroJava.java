package br.com.ifpr.projetointegrador.Introdução;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.ifpr.projetointegrador.R;

public class IntroJava extends AppCompatActivity {

    Button btnVolta, btnProx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_java);

        btnVolta = findViewById(R.id.btnVoltar);
        btnProx= findViewById(R.id.btnProx);

        btnVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte= new Intent(IntroJava.this, AssuntosIntro.class);
                startActivity(inte);
            }
        });

        btnProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(IntroJava.this, ComentariosSimples.class);
                startActivity(in);
            }
        });
    }
}
