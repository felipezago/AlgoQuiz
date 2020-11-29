package br.com.ifpr.projetointegrador.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.ifpr.projetointegrador.R;

public class Splash extends AppCompatActivity {

    ImageView img;
    Animation upToDown;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (FirebaseApp.getApps(this).isEmpty()) {
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
            firebaseDatabase.setPersistenceCacheSizeBytes(104857600);
            databaseReference = firebaseDatabase.getReference();
            databaseReference.keepSynced(true);
        }

        img= findViewById(R.id.img);

        upToDown= AnimationUtils.loadAnimation(this, R.anim.uptodown);

        img.setAnimation(upToDown);

        Handler hand= new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                view();
            }
        }, 2000);

    }

    public void view(){
        Intent in= new Intent(Splash.this, MainActivity.class);
        startActivity(in);
        finish();
    }
}
