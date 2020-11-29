package br.com.ifpr.projetointegrador.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import br.com.ifpr.projetointegrador.Firebase.ConfiguracoesFirebase;
import br.com.ifpr.projetointegrador.Firebase.UsuarioFirebase;
import br.com.ifpr.projetointegrador.Model.Conquista;
import br.com.ifpr.projetointegrador.Model.Usuario;
import br.com.ifpr.projetointegrador.R;

public class CadastroUsuario extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnVoltar, btnSalvar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText edtEmail, edtSenha, edtNick;
    Spinner sp;
    String sexo;

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    private FirebaseAuth autenticacao = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        btnVoltar= findViewById(R.id.btnVoltar);
        btnSalvar= findViewById(R.id.btnSalvar);
        edtEmail= findViewById(R.id.edtEmail);
        edtSenha= findViewById(R.id.edtSenha);
        edtNick= findViewById(R.id.edtNick);
        sp= findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,
                R.array.sexo, android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);


        initFirebase();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CadastroUsuario.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastra(v);
            }
        });

    }

    public void initFirebase(){
        FirebaseApp.initializeApp(CadastroUsuario.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void cadastrarUsuario(final Usuario user) {

        autenticacao = ConfiguracoesFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                user.getEmail(),
                user.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    try {
                        String idUsuario = task.getResult().getUser().getUid();
                        user.setId(idUsuario);
                        user.salvar();
                        Log.i("tag", "SUCESSO");
                        UsuarioFirebase.AtualizaNome(user.getNick());
                        adicionarConquistas(idUsuario);
                        limpaCampos();
                        redirecionaLogin();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {

                    String excecao = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {

                        excecao = "Digite uma senha mais forte!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {

                        excecao = "Por favor, digite um e-mail válido";
                    } catch (FirebaseAuthUserCollisionException e) {

                        excecao = "Esta conta já está cadastrada";
                    } catch (Exception e) {

                        excecao = "Erro ao cadastrar usuario: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroUsuario.this,
                            excecao, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    public void limpaCampos(){
        edtNick.setText("");
        edtEmail.setText("");
        edtSenha.setText("");
    }

    public void redirecionaLogin(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void cadastra(View v){
        String nick = edtNick.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();
        String fotoMenino = "https://firebasestorage.googleapis.com/v0/b/projetointegrador-e2d4b.appspot.com/o/ImagensPerfil%2Fboy.png?alt=media&token=0af24514-9942-4ba8-bbc1-f4cbe715af9b";
        String fotoMenina = "https://firebasestorage.googleapis.com/v0/b/projetointegrador-e2d4b.appspot.com/o/ImagensPerfil%2Fgirl.png?alt=media&token=f607898f-f5f4-4526-9b2a-8071ca2e159d";

        if(!nick.isEmpty()){
            if(!email.isEmpty()){
                    if(!senha.isEmpty()){

                        Usuario user = new Usuario();

                        user.setNick(nick);
                        user.setEmail(email);
                        user.setSenha(senha);
                        user.setSexo(sexo);

                        if(sexo.equals("Masculino")){
                            user.setFotoPerfil(fotoMenino);
                        }else{
                            user.setFotoPerfil(fotoMenina);
                        }

                        cadastrarUsuario(user);

                    }else{
                        Toast.makeText(CadastroUsuario.this,
                                "Preencha a senha!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CadastroUsuario.this,
                            "Preencha o e-mail!", Toast.LENGTH_SHORT).show();
                }

        }else{
            Toast.makeText(CadastroUsuario.this,
                    "Preencha o nick!", Toast.LENGTH_SHORT).show();
        }

    }

    //CADASTRAR CONQUISTAS
    public void adicionarConquistas(String id){
        Conquista c= new Conquista();
        c.setCompletou(false);
        c.setCompletouIntro(false);
        c.setCompletouMedio(false);
        c.setMaisdevinte(false);
        c.setPegouPrimeiroLugar(false);
        databaseReference.child("Usuario").child(id).child("Conquista").setValue(c);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sexo = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Por favor, selecione um item!", Toast.LENGTH_SHORT).show();
    }
}



