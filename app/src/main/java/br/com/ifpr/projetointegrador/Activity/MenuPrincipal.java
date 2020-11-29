package br.com.ifpr.projetointegrador.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

import br.com.ifpr.projetointegrador.Avançado.AssuntosAv;
import br.com.ifpr.projetointegrador.Firebase.UsuarioFirebase;
import br.com.ifpr.projetointegrador.Intermediário.AssuntosMedio;
import br.com.ifpr.projetointegrador.Introdução.AssuntosIntro;
import br.com.ifpr.projetointegrador.Model.Conquista;
import br.com.ifpr.projetointegrador.Model.Usuario;
import br.com.ifpr.projetointegrador.Questoes.QuestoesIntroducao;
import br.com.ifpr.projetointegrador.R;
import de.hdodenhof.circleimageview.CircleImageView;

import static br.com.ifpr.projetointegrador.R.drawable.lock;

public class MenuPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnIntro, btnMedio, btnAv, moedaMedio, moedaAv;
    ImageView info;
    CircleImageView perfil;
    TextView txInfo, totalMoedas, txMoedasMedio, txMoedasAv, nick;
    TextView txIntro, txMedio, txAv;
    int total;

    Usuario u= UsuarioFirebase.getDadosUsuarioLogado();

    private DatabaseReference ref;
    private DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        instanciaVar();
        abrirGaveta();

        FirebaseApp.initializeApp(MenuPrincipal.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Usuario");

        ref = FirebaseDatabase.getInstance().getReference().child("Usuario").child(u.getId());
        ref.keepSynced(true);

        btnMedio.setEnabled(false);
        btnAv.setEnabled(false);
        validaAcesso();

        getDados(ref);

        Intent i= getIntent();
        if(i.hasExtra("intro")){
            int aux;
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            aux= (Integer) i.getSerializableExtra("intro");

            if(aux > 6){
                alerta.setTitle("Parabéns");
                alerta.setMessage("Você acertou mais de 75% das questões.");
                btnMedio.setEnabled(true);

                ref.child("Conquista").child("completouIntro").setValue(true);

            }else{
                alerta.setTitle(":(");
                alerta.setMessage("Você acertou "+aux+"/8 perguntas. Tente novamente!");
            }

            alerta.setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alerta.show();
        }

        if(i.hasExtra("medio")){
            int aux;
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            aux= (Integer) i.getSerializableExtra("medio");

            if(aux > 6){
                alerta.setTitle("Parabéns");
                alerta.setMessage("Você acertou "+aux+"/8 perguntas!");
            }else{
                alerta.setTitle(":(");
                alerta.setMessage("Você acertou "+aux+"/8 perguntas. Tente novamente!");
            }

            alerta.setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alerta.show();
        }

        if(i.hasExtra("av")){
            int aux;
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            aux= (Integer) i.getSerializableExtra("av");

            if(aux > 6){
                alerta.setTitle("Parabéns");
                alerta.setMessage("Você acertou "+aux+"/8 perguntas, e desbloqueou a conquista 'Programador III'");
                ref.child("Conquista").child("completou").setValue(true);

            }else{
                alerta.setTitle(":(");
                alerta.setMessage("Você acertou "+aux+"/8 perguntas, e não finalizou o Quizz." +
                        " Tente novamente!");
            }

            alerta.setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alerta.show();
        }

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta();
            }
        });

        txIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssuntosIntro as= new AssuntosIntro();
                avanca(as.getClass());
            }
        });

        txMedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssuntosMedio as= new AssuntosMedio();
                avanca(as.getClass());
            }
        });


        txAv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssuntosAv as= new AssuntosAv();
                avanca(as.getClass());
            }
        });


        txInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta();
            }
        });
        
        btnIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssuntosIntro as= new AssuntosIntro();
                avanca(as.getClass());
            }
        });

        btnMedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssuntosMedio as= new AssuntosMedio();
                avanca(as.getClass());
            }
        });

        btnAv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssuntosAv as= new AssuntosAv();
                avanca(as.getClass());
            }
        });



    }

    public void getDados(DatabaseReference reference){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final Usuario usua= dataSnapshot.getValue(Usuario.class);
                Conquista c= dataSnapshot.getValue(Conquista.class);
                total= usua.getTotal();
                String auxiliar= Integer.toString(total);
                totalMoedas.setText(auxiliar);

                if(usua.getTotal() >= 20){
                    if(c.isMaisdevinte() == false){
                        ref.child("Conquista").child("maisdevinte").setValue(true);
                    }

                }

                validaMoedaMedio(usua);
                validaMoedaAv(usua);
                validaBloqueio(usua);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("AD", "onCancelled", databaseError.toException());
                // ...
            }
        });
    }

    public void validaMoedaMedio(final Usuario us){
        final AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        moedaMedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total < 6){
                    Toast.makeText(MenuPrincipal.this, "Não foi possível comprar o próximo módulo," +
                            "você não tem moedas o suficiente!", Toast.LENGTH_SHORT).show();
                }else{
                    alerta.setTitle("Parabéns");
                    alerta.setMessage("Você liberou a conquista 'Programador I'");
                    alerta.show();
                    ref.child("Conquista").child("completouIntro").setValue(true);

                    us.setComprouMedio(true);
                    ref.child("comprouMedio").setValue(us.isComprouMedio());

                    btnMedio.setEnabled(true);
                    validaAcesso();
                    moedaMedio.setVisibility(View.GONE);
                    txMoedasMedio.setVisibility(View.GONE);

                }
            }
        });

    }

    public void validaMoedaAv(final Usuario us){
        final AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        moedaAv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total < 12){
                    Toast.makeText(MenuPrincipal.this, "Não foi possível comprar o próximo módulo," +
                            "você não tem moedas o suficiente!", Toast.LENGTH_SHORT).show();
                }else{
                    alerta.setTitle("Parabéns");
                    alerta.setMessage("Você liberou a conquista 'Programador II'");
                    alerta.show();

                    ref.child("Conquista").child("completouMedio").setValue(true);

                    us.setComprouAv(true);
                    ref.child("comprouAv").setValue(us.isComprouAv());
                    btnAv.setEnabled(true);
                    validaAcesso();
                    moedaAv.setVisibility(View.GONE);
                    txMoedasAv.setVisibility(View.GONE);
                }
            }
        });
    }

    public void validaBloqueio(final Usuario us){
        if(us.isComprouMedio()){
            btnMedio.setEnabled(true);
            validaAcesso();
            moedaMedio.setVisibility(View.GONE);
            txMoedasMedio.setVisibility(View.GONE);
        }

        if(us.isComprouAv()){
            btnAv.setEnabled(true);
            validaAcesso();
            moedaAv.setVisibility(View.GONE);
            txMoedasAv.setVisibility(View.GONE);
        }
    }

    public void instanciaVar(){

        txIntro = findViewById(R.id.txIntroducao);
        txMedio = findViewById(R.id.txMedio);
        txAv = findViewById(R.id.txAV);
        btnIntro= findViewById(R.id.btnIntro);
        btnMedio= findViewById(R.id.btnMedio);
        btnAv= findViewById(R.id.btnAv);
        info= findViewById(R.id.imgInfo);
        txInfo= findViewById(R.id.txInfo);
        moedaAv= findViewById(R.id.moedaAv);
        moedaMedio= findViewById(R.id.moedaMedio);
        totalMoedas= findViewById(R.id.totalMoedas);
        txMoedasAv= findViewById(R.id.txMoedaAv);
        txMoedasMedio= findViewById(R.id.txMoedaMeio);

    }

    public void abrirGaveta(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        perfil = findViewById(R.id.perfil);
        nick= findViewById(R.id.nick);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usua = dataSnapshot.getValue(Usuario.class);

                nick.setText(usua.getNick());

                Picasso.get().load(usua.getFotoPerfil()).into(perfil);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return true;
    }

    public void alerta(){

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(R.drawable.ic_alert);
        alerta.setTitle("Alerta");
        alerta.setMessage("Para liberar os módulos bloqueados deve ser " +
                "realizado o teste no final de cada módulo e acertar pelo menos 75% " +
                "das questões.");

        alerta.setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
        });

         alerta.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(this, Perfil.class);
            startActivity(i);
        } else if (id == R.id.nav_send) {
            Intent i = new Intent(this, ContatoProfessor.class);
            startActivity(i);
        } else if(id == R.id.rank){
            Intent i = new Intent(this, Ranqueamento.class);
            startActivity(i);
        } else if(id == R.id.conq){
            Intent i = new Intent(this, Conquistas.class);
            startActivity(i);
        }

        else if(id == R.id.navSair){
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void validaAcesso(){
        if(!btnMedio.isEnabled()) {
            btnMedio.setBackgroundResource(lock);
        }else{
            btnMedio.setBackgroundResource(R.drawable.loop);
        }

        if(!btnAv.isEnabled()) {
            btnAv.setBackgroundResource(lock);
        }else{
            btnAv.setBackgroundResource(R.drawable.terminal);
        }
    }

    public void avanca(Class classe){
        Intent intent= new Intent(this, classe);
        startActivity(intent);
    }

}
