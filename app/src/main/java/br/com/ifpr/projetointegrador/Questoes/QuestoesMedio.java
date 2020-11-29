package br.com.ifpr.projetointegrador.Questoes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.ifpr.projetointegrador.Firebase.UsuarioFirebase;
import br.com.ifpr.projetointegrador.Intermediário.AssuntosMedio;
import br.com.ifpr.projetointegrador.Activity.MenuPrincipal;
import br.com.ifpr.projetointegrador.Model.Usuario;
import br.com.ifpr.projetointegrador.R;

public class QuestoesMedio extends AppCompatActivity {

    int pontosMedio= 0, respostaCerta;
    TextView desc;
    RadioButton resp1, resp2, resp3, resp4;
    RadioGroup rg;
    Button btn, btnSair;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Usuario u= UsuarioFirebase.getDadosUsuarioLogado();

    List<Questao> questoes = new ArrayList<Questao>(){
        {
            //Questão 1
            add(new Questao("Qual a estrutura de uma declaração if-else?",
                    R.id.rdResposta1, "Estrutura de decisão", "Estrutura de Repetição", "Estrutura de Dados", "Switch"));
            //Questão 2
            add(new Questao("Quantas opções são possíveis utilizando uma estrutura simples if-else?",
                    R.id.rdResposta2, "1", "2", "3", "Quantas for necessário"));
            //Questão 3
            add(new Questao("Quantas opções são possíveis utilizando uma estrutura composta if-else if?",
                    R.id.rdResposta4, "1", "2", "3", "Quantas for necessário"));
            //Questão 4
            add(new Questao("Quantas opções (case) são possíveis utilizando uma estrutura switch-case?",
                    R.id.rdResposta4, "1", "2", "3", "Quantas for necessário"));
            //Questão 5
            add(new Questao("Defina (verdadeiro ou falso) cada uma das seguintes expressoes:" +
                    "\n14 >= 14\n15 > 15\n16 != 16\n18 >= 19",
                    R.id.rdResposta2, "V,F,F,V", "V,F,F,F", "V,V,V,V", "F,F,F,F"));
            //Questão 6
            add(new Questao("A expressão '2+2 = 4' está correta?",
                    R.id.rdResposta2, "Sim", "Não", "As vezes", "Não sei"));
            //Questão 7
            add(new Questao("Quantas vezes vai ser exibida a mensagem 'Bem vindo ao Java': \nint count = 0; \nwhile (count < 10) { " +
                    "\n System.out.println('Bem vindo ao Java'); \n count++;\n}",
                    R.id.rdResposta3, "0", "8", "9", "10"));
            //Questão 8
            add(new Questao("Qual dos seguintes códigos imprimirá 'Bem vindo ao Java' 10 vezes?",
                    R.id.rdResposta4, "for (int count = 1; count <= 10; count++){\nSystem.out.println('Bem vindo ao Java');\n}",
                    "for (int count = 2; count < 10; count++) {\nSystem.out.println('Bem vindo ao Java');\n}",
                    "for (int count = 1; count < 10; count++) {\nSystem.out.println('Bem vindo ao Java');\n}",
                    "for (int count = 0; count <= 10; count++) {\nSystem.out.println('Bem vindo ao Java');\n}"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questoes_medio);

        iniciaVariaveis();
        initFirebase();
        carregaQuestoes();

        limparRb();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarQuestoes();
                carregaQuestoes();
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta();
            }
        });

    }

    public void initFirebase(){
        FirebaseApp.initializeApp(QuestoesMedio.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void iniciaVariaveis(){
        desc= findViewById(R.id.descricao);
        resp1= findViewById(R.id.rdResposta1);
        resp2= findViewById(R.id.rdResposta2);
        resp3= findViewById(R.id.rdResposta3);
        resp4= findViewById(R.id.rdResposta4);
        rg= findViewById(R.id.rdgrupo);
        btnSair= findViewById(R.id.btnVoltar);
        btn= findViewById(R.id.btnValida);
    }

    public void carregaQuestoes(){

        if(questoes.size() > 0) {
            Questao q = questoes.remove(0);
            desc.setText(q.getPergunta());
            List<String> resposta = q.getRespostas();
            resp1.setText(resposta.get(0));
            resp2.setText(resposta.get(1));
            resp3.setText(resposta.get(2));
            resp4.setText(resposta.get(3));
            respostaCerta = q.getRespostaCerta();
            rg.setSelected(false);
            limparRb();
        }
        else{ //acabaram as questões
            salvar(pontosMedio);
            Intent intent = new Intent(this, MenuPrincipal.class);
            intent.putExtra("medio", pontosMedio);
            startActivity(intent);
            finish();
        }

    }

    public void salvar(final int x){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuario").child(u.getId());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario us= dataSnapshot.getValue(Usuario.class);

                if(x > us.getPontosMedio()){
                    us.setPontosMedio(pontosMedio);
                    databaseReference.child("pontosMedio").setValue(us.getPontosMedio());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    public void validarQuestoes(){

        if(rg.getCheckedRadioButtonId() == respostaCerta) {
            pontosMedio++;
        }

    }

    public void limparRb(){
        resp1.setChecked(false);
        resp2.setChecked(false);
        resp3.setChecked(false);
        resp4.setChecked(false);
    }

    public void alerta(){

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(R.drawable.ic_alert);
        alerta.setTitle("Alerta");
        alerta.setMessage("Tem certeza que deseja sair? Você perderá seu progresso.");

        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pontosMedio= 0;
                Intent in= new Intent(QuestoesMedio.this, AssuntosMedio.class);
                startActivity(in);
            }
        });

        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alerta.show();
    }

}
