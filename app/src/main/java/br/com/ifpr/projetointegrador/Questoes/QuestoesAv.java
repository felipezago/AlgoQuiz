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

import br.com.ifpr.projetointegrador.Avançado.AssuntosAv;
import br.com.ifpr.projetointegrador.Activity.MenuPrincipal;
import br.com.ifpr.projetointegrador.Firebase.UsuarioFirebase;
import br.com.ifpr.projetointegrador.Model.Usuario;
import br.com.ifpr.projetointegrador.R;

public class QuestoesAv extends AppCompatActivity {

    int pontosAv= 0, respostaCerta;
    TextView desc;
    RadioButton resp1, resp2, resp3, resp4;
    RadioGroup rg;
    Button btn, btnSair;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Usuario u= UsuarioFirebase.getDadosUsuarioLogado();


    List<Questao> questoesAv = new ArrayList<Questao>(){
        {
            //Questão 1
            add(new Questao("Qual o lugar onde um método NÃO pode ser chamado?",
                    R.id.rdResposta4, "Método Principal", "Dentro de outros métodos", "Método Construtor", "Fora do método principal"));
            //Questão 2
            add(new Questao("Qual é a utilidade do Try/Catch?",
                    R.id.rdResposta4, "Nos permite corrigir erros", "Nos permite tratar a exceção manualmente", "Previne a finalização da aplicação quando ocorrer uma exceção", "Todas as opções acima"));
            //Questão 3
            add(new Questao("Qual é a palavra chave utilizada para que o bloco de código seja examinado para exceções?",
                    R.id.rdResposta1, "Try", "Catch", "Throw", "Check"));
            //Questão 4
            add(new Questao("Qual é o número inicial de um array?",
                    R.id.rdResposta2, "1", "0", "Pode ser definido", "2"));
            //Questão 5
            add(new Questao("Qual o operador utilizado após o tipo da variável para definir que esta será um Array?",
                    R.id.rdResposta1, "[]", "{}", "()", "*"));
            //Questão 6
            add(new Questao("Qual a diferença entre um Array e um ArrayList?",
                    R.id.rdResposta3, "Nenhuma", "No Array é permitido adicionar e remover elementos.", "O tamanho do ArrayList é alocado automáticamente e pode ser modificado.", "O tamanho do ArrayList é alocado automáticamente e não pode ser modificado"));
            //Questão 7
            add(new Questao("Qual a utilidade de um For Each?",
                    R.id.rdResposta2, "Realizar verificações de condição.", "Percorrer toda a lista, elemento por elemento.", "Mesma função que o For", "Não sei."));
            //Questão 8
            add(new Questao("Qual a sintaxe correta de um For Each?",
                    R.id.rdResposta3, "for (tipo nomeVariavel ; array) \n" +
                    "{ \n" +
                    "    validações com a variável;\n" +
                    "}", "for (tipo : array) \n" +
                    "{ \n" +
                    "    validações com a variável;\n" +
                    "}", "for (tipo nomeVariavel: array) \n" +
                    "{ \n" +
                    "    validações com a variável;\n" +
                    "}", "for (array : tipo nomeVariavel) \n" +
                    "{ \n" +
                    "    validações com a variável;\n" +
                    "}"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questoes_av);

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
        FirebaseApp.initializeApp(QuestoesAv.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void salvar(final int x){

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuario").child(u.getId());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario us= dataSnapshot.getValue(Usuario.class);

                if(x > us.getPontosAv()){
                    us.setPontosAv(pontosAv);
                    databaseReference.child("pontosAv").setValue(us.getPontosAv());
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

        if(questoesAv.size() > 0) {
            Questao q = questoesAv.remove(0);
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
            Intent intent = new Intent(this, MenuPrincipal.class);
            salvar(pontosAv);
            intent.putExtra("av", pontosAv);
            startActivity(intent);
            finish();
        }

    }

    public void validarQuestoes(){

        if(rg.getCheckedRadioButtonId() == respostaCerta) {
            pontosAv++;
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
                pontosAv= 0;
                Intent in= new Intent(QuestoesAv.this, AssuntosAv.class);
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
