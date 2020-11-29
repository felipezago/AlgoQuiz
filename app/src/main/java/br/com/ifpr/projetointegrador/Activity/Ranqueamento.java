package br.com.ifpr.projetointegrador.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.ifpr.projetointegrador.Firebase.UsuarioFirebase;
import br.com.ifpr.projetointegrador.Model.Conquista;
import br.com.ifpr.projetointegrador.Model.RecyclerAdapter;
import br.com.ifpr.projetointegrador.Model.Usuario;
import br.com.ifpr.projetointegrador.R;

public class Ranqueamento extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    List<Usuario> lista;
    RecyclerAdapter adapter;
    Button btn;

    Usuario usu= UsuarioFirebase.getDadosUsuarioLogado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranqueamento);

        btn = findViewById(R.id.btnVoltar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lista= new ArrayList<Usuario>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        ref= firebaseDatabase.getReference("Usuario");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(lista.size() ==0){
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        Usuario u = dataSnapshot1.getValue(Usuario.class);
                        lista.add(u);
                    }
                }

                //Conquista q= dataSnapshot.getValue(Conquista.class);

                Comparator<Usuario> c = new Comparator<Usuario>() {
                    @Override
                    public int compare(Usuario o1, Usuario o2) {
                        return o1.getTotal() - o2.getTotal();
                    }
                };

                Collections.sort(lista, c);
                Collections.reverse(lista);

                    adapter = new RecyclerAdapter(Ranqueamento.this, lista);
                    recyclerView.setAdapter(adapter);

                if(usu.getId().equals(lista.get(0).getId())){
                    AlertDialog.Builder alerta = new AlertDialog.Builder(Ranqueamento.this);

                    Log.e("teste", "passou aq");
                    ref.child(usu.getId()).child("Conquista").child("pegouPrimeiroLugar").setValue(true);

                    alerta.setTitle("Parabéns");
                    alerta.setMessage("Você está em primeiro lugar e já desbloqueou a conquista: 'Campeão'");
                    alerta.show();
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Ranqueamento.this, "Erro", Toast.LENGTH_SHORT).show();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Ranqueamento.this, MenuPrincipal.class);
                startActivity(i);
            }
        });

    }


}
