package br.com.ifpr.projetointegrador.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.ifpr.projetointegrador.Firebase.ConfiguracoesFirebase;
import br.com.ifpr.projetointegrador.Firebase.UsuarioFirebase;
import br.com.ifpr.projetointegrador.Model.Usuario;
import br.com.ifpr.projetointegrador.R;


public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText edtEmail, edtSenha;
    TextView txRegistro;

    private FirebaseAuth autenticacao;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFirebase();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if(user != null){
            Intent in= new Intent(this, MenuPrincipal.class);
            startActivity(in);
            finish();
        }

        btn= findViewById(R.id.btn);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        txRegistro = findViewById(R.id.txRegistro);

        txRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, CadastroUsuario.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarLoginUsuario(v);

            }
        });

    }

    public void initFirebase(){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void validarLoginUsuario(View view){

        String txemail = edtEmail.getText().toString();
        String txsenha = edtSenha.getText().toString();

        if( !txemail.isEmpty() ){
            if( !txsenha.isEmpty() ){

                Usuario usuario = new Usuario();
                usuario.setEmail(txemail);
                usuario.setSenha(txsenha);
                logarUsuario(usuario);

            }else{
                Toast.makeText(MainActivity.this,
                        "Preencha a senha!", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(MainActivity.this,
                    "Preencha o e-mail!", Toast.LENGTH_SHORT).show();
        }

    }


    public void logarUsuario(final Usuario usuario){
        autenticacao = ConfiguracoesFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    AsyncTask<Void, Void, Void> demoLoad = new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            try {
                                Thread.sleep(500);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    };
                    demoLoad.execute();
                    UsuarioFirebase.redirecionaUsuarioLogado(MainActivity.this);
                }else{
                    try{
                        String excecao = "";
                        try {
                            throw task.getException();
                        }catch ( FirebaseAuthInvalidUserException e){
                            excecao = "Usuário não cadastrado!";
                            e.printStackTrace();

                        }catch ( FirebaseAuthInvalidCredentialsException e){

                            excecao = "E-mail e senha não correspondem a nenhum cadastro";
                        }catch ( Exception e){

                            excecao = "Erro ao cadastrar usuario: " +e.getMessage();
                            e.printStackTrace();
                        }

                        Toast.makeText(MainActivity.this,
                                excecao, Toast.LENGTH_SHORT).show();


                    }catch (Exception e){
                        e.printStackTrace();

                    }

                }

            }
        });

    }



}





