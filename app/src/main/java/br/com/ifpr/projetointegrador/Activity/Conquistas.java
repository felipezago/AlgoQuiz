package br.com.ifpr.projetointegrador.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.ifpr.projetointegrador.Firebase.UsuarioFirebase;
import br.com.ifpr.projetointegrador.Model.Conquista;
import br.com.ifpr.projetointegrador.Model.Usuario;
import br.com.ifpr.projetointegrador.R;

import static br.com.ifpr.projetointegrador.R.drawable.lock;

public class Conquistas extends AppCompatActivity {

    Button btnVoltar, completouIntro, completouMedio, completou, maisdevinte, primeiroLugar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Usuario u= UsuarioFirebase.getDadosUsuarioLogado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conquistas);

        btnVoltar = findViewById(R.id.btnVoltar);
        completou = findViewById(R.id.completouQuizz);
        completouIntro= findViewById(R.id.completouIntro);
        completouMedio = findViewById(R.id.completouMedio);
        maisdevinte= findViewById(R.id.maisdevinte);
        primeiroLugar = findViewById(R.id.primeiroLugar);
        initFirebase();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuario").child(u.getId()).child("Conquista");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final AlertDialog.Builder alerta = new AlertDialog.Builder(Conquistas.this);
                final Conquista c= dataSnapshot.getValue(Conquista.class);

                validaConquistas(c);

                completouMedio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!c.isCompletouMedio()) {
                            alerta.setTitle("Conquista não liberada!");
                            alerta.setMessage("Para liberar esta conquista, é necessário completar o módulo 'Intermediário'.");
                            alerta.show();
                        }else{
                            alerta.setTitle("Programador II");
                            alerta.setMessage("Parabéns, você completou o módulo 'Intermediário', e recebeu o titulo 'Programador II' .");
                            alerta.show();
                        }
                    }
                });

                completouIntro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(c.isCompletouIntro()) {
                            alerta.setTitle("Programador I");
                            alerta.setMessage("Parabéns, você completou o módulo 'Introdução', e recebeu o titulo 'Programador I' .");
                            alerta.show();

                        }else{
                            alerta.setTitle("Conquista não liberada!");
                            alerta.setMessage("Para liberar esta conquista, é necessário completar o módulo 'Introdução'.");
                            alerta.show();
                        }
                    }
                });

                completou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!c.isCompletou()) {
                            alerta.setTitle("Conquista não liberada!");
                            alerta.setMessage("Para liberar esta conquista, é necessário completar o módulo 'Avançado'.");
                            alerta.show();
                        }else{
                            alerta.setTitle("Programador III");
                            alerta.setMessage("Parabéns, você completou o módulo 'Avançado', e recebeu o titulo 'Programador III' .");
                            alerta.show();
                        }
                    }
                });

                primeiroLugar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!c.isPegouPrimeiroLugar()) {
                            alerta.setTitle("Conquista não liberada!");
                            alerta.setMessage("Para liberar esta conquista, é necessário ficar em primeiro lugar no Ranking ao menos uma vez.");
                            alerta.show();
                        }else{
                            alerta.setTitle("Campeão");
                            alerta.setMessage("Parabéns, você já ficou em primeiro lugar no Ranking, e recebeu o titulo 'Campeão' .");
                            alerta.show();
                        }
                    }
                });

                maisdevinte.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!c.isMaisdevinte()) {
                            alerta.setTitle("Conquista não liberada!");
                            alerta.setMessage("Para liberar esta conquista, é necessário possuir mais de 20 pontos.");
                            alerta.show();
                        }else{
                            alerta.setTitle("Estudioso");
                            alerta.setMessage("Parabéns, você possui mais de 20 pontos, e recebeu o titulo 'Estudioso' .");
                            alerta.show();
                        }
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Conquistas.this, MenuPrincipal.class);
                startActivity(i);
            }
        });


    }

    public void initFirebase(){
        FirebaseApp.initializeApp(Conquistas.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    //VALIDAR SE CONQUISTAS FORAM COMPLETAS
    public void validaConquistas(Conquista c){

        if(!c.isCompletouMedio()) {
            completouMedio.setBackgroundResource(lock);
        }else{
            completouMedio.setBackgroundResource(R.drawable.medal);
        }

        if(!c.isCompletou()) {
            completou.setBackgroundResource(lock);
        }else{
            completou.setBackgroundResource(R.drawable.diploma);
        }

        if(!c.isPegouPrimeiroLugar()) {
            primeiroLugar.setBackgroundResource(lock);
        }else{
            primeiroLugar.setBackgroundResource(R.drawable.medalha);
        }

        if(!c.isMaisdevinte()) {
            maisdevinte.setBackgroundResource(lock);
        }else{
            maisdevinte.setBackgroundResource(R.drawable.trofeu);
        }

        if(!c.isCompletouIntro()) {
            completouIntro.setBackgroundResource(lock);
        }else{
            completouIntro.setBackgroundResource(R.drawable.trophy);
        }

    }

}
