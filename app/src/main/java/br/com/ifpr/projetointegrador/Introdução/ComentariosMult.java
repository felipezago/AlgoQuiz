package br.com.ifpr.projetointegrador.Introdução;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.ifpr.projetointegrador.R;

public class ComentariosMult extends AppCompatActivity {

    Button btnAvanca, btnVolta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios_mult);

        btnAvanca = findViewById(R.id.btnProx);
        btnVolta = findViewById(R.id.btnVoltar);

        btnAvanca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ComentariosMult.this, IntroVariaveis.class);
                startActivity(i);
            }
        });

        btnVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ComentariosMult.this, AssuntosIntro.class);
                startActivity(i);
            }
        });

    }
}
