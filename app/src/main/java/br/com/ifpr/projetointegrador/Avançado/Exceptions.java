package br.com.ifpr.projetointegrador.Avançado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.ifpr.projetointegrador.R;

public class Exceptions extends AppCompatActivity {

    Button btn, btnSair;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exceptions);

        btn = findViewById(R.id.btnProx);
        btnSair = findViewById(R.id.btnVoltar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(Exceptions.this, Array.class);
                startActivity(in);
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(Exceptions.this, AssuntosAv.class);
                startActivity(in);
            }
        });
    }
}
