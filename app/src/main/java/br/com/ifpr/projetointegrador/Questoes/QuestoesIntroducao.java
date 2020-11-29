package br.com.ifpr.projetointegrador.Questoes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import java.util.UUID;

import br.com.ifpr.projetointegrador.Firebase.UsuarioFirebase;
import br.com.ifpr.projetointegrador.Introdução.AssuntosIntro;
import br.com.ifpr.projetointegrador.Activity.MenuPrincipal;
import br.com.ifpr.projetointegrador.R;
import br.com.ifpr.projetointegrador.Model.Usuario;

public class QuestoesIntroducao extends AppCompatActivity {

    int pontos= 0, respostaCerta;
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
            add(new Questao("Qual foi a empresa  criadora da linguagem de programação Java?",
                    R.id.rdResposta4, "IBM", "Oracle", "Netbeans", "Sun Microsystems"));
            //Questão 2
            add(new Questao("Quantas versões do Java são necessárias para distribuir sua aplicação\n" +
                    "em diferentes plataformas?",
                    R.id.rdResposta3, "Uma para cada plataforma", "Três", "Somente uma", "Duas"));
            //Questão 3
            add(new Questao("Comentário de uma linha são criados utilizando:",
                    R.id.rdResposta2, "\"//\" no final da linha", "\"//\" no começo da linha", "** no começo da linha", "*/ no começo da linha"));
            //Questão 4
            add(new Questao("Comentário de multiplas linhas são criados utilizando:",
                        R.id.rdResposta4, "\"//\" no começo e final da linha", "\"//\" no começo e final da linha", "*/ no começo e /* no final da linha", "/* no começo e */ no final da linha"));
            //Questão 5
            add(new Questao("Qual o tipo de variável deve ser utilizado para armazenar o nome de uma cidade?",
                    R.id.rdResposta2, "char", "String", "int", "float"));
            //Questão 6
            add(new Questao("Qual o tipo de variável deve ser utilizado para armazenar um número real?",
                    R.id.rdResposta4, "char", "String", "int", "float"));
            //Questão 7
            add(new Questao("Qual destes operadores é utilizado para incrementar o valor da variável em 1?",
                    R.id.rdResposta3, "+", "-", "++", "*"));
            //Questão 8
            add(new Questao("Qual deste operadores é o operador de atribuição?",
                    R.id.rdResposta1, "=", "+", "++", "*"));
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questoes);

        iniciaVariaveis();

        initFirebase();

        carregaQuestoes();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarQuestoes();
                carregaQuestoes();
                iniciaVariaveis();

                limparRb();
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
        FirebaseApp.initializeApp(QuestoesIntroducao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void salvar(final int x){

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuario").child(u.getId());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Usuario us= dataSnapshot.getValue(Usuario.class);

                if(x > us.getPontos()){
                    us.setPontos(pontos);
                    databaseReference.child("pontos").setValue(us.getPontos());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
                List<String> resposta = q.getRespostas();
                desc.setText(q.getPergunta());
                respostaCerta = q.getRespostaCerta();
                resp1.setText(resposta.get(0));
                resp2.setText(resposta.get(1));
                resp3.setText(resposta.get(2));
                resp4.setText(resposta.get(3));
                rg.setSelected(false);
                limparRb();
            }
            else{ //acabaram as questões
                salvar(pontos);
                Intent intent = new Intent(this, MenuPrincipal.class);
                intent.putExtra("intro", pontos);
                startActivity(intent);
                finish();
            }

    }

    public void validarQuestoes(){

        if(rg.getCheckedRadioButtonId() == respostaCerta) {
            pontos++;
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
                pontos= 0;
                Intent in= new Intent(QuestoesIntroducao.this, AssuntosIntro.class);
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
