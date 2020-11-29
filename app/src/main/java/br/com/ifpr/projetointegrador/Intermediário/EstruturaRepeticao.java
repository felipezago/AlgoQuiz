package br.com.ifpr.projetointegrador.Intermediário;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.ifpr.projetointegrador.Introdução.AssuntosIntro;
import br.com.ifpr.projetointegrador.R;

public class EstruturaRepeticao extends AppCompatActivity {

    Button btn, btnVolta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estrutura_repeticao);

        btn= findViewById(R.id.btnProx);
        btnVolta = findViewById(R.id.btnVoltar);

        btnVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte= new Intent(EstruturaRepeticao.this, AssuntosIntro.class);
                startActivity(inte);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte= new Intent(EstruturaRepeticao.this, For.class);
                startActivity(inte);
            }
        });
        
    }
}
