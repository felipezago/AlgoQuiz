package br.com.ifpr.projetointegrador.Intermediário;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.ifpr.projetointegrador.Introdução.AssuntosIntro;
import br.com.ifpr.projetointegrador.R;

public class SwitchCase extends AppCompatActivity {

    Button btn, btnVolta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_case);

        btn = findViewById(R.id.btnProx);
        btnVolta = findViewById(R.id.btnVoltar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte= new Intent(SwitchCase.this, EstruturaRepeticao.class);
                startActivity(inte);
            }
        });

        btnVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte= new Intent(SwitchCase.this, AssuntosIntro.class);
                startActivity(inte);
            }
        });

    }
}
