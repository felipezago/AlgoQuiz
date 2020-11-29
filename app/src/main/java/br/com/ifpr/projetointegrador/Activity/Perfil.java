package br.com.ifpr.projetointegrador.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import br.com.ifpr.projetointegrador.Firebase.UsuarioFirebase;
import br.com.ifpr.projetointegrador.Model.Usuario;
import br.com.ifpr.projetointegrador.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class Perfil extends AppCompatActivity {

    ImageView img;
    Button btnVolta;
    TextView email, nick, nick2, sexo;
    RelativeLayout layout;

    DatabaseReference databaseReference;
    StorageReference storageReference;

    private static final int FotoGaleria = 1;

    Usuario u = UsuarioFirebase.getDadosUsuarioLogado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        img = findViewById(R.id.fotoPerfil);
        btnVolta = findViewById(R.id.btnVoltar);
        email = findViewById(R.id.email);
        nick = findViewById(R.id.nick);
        nick2 = findViewById(R.id.nick2);
        sexo = findViewById(R.id.sexo);
        layout = findViewById(R.id.relativo);

        storageReference = FirebaseStorage.getInstance().getReference().child("ImagensPerfil");
        databaseReference = FirebaseDatabase.getInstance().getReference("Usuario").child(u.getId());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String foto = dataSnapshot.child("fotoPerfil").getValue().toString();
                Picasso.get().load(foto).into(img);

                Usuario us = dataSnapshot.getValue(Usuario.class);
                nick.setText(" "+us.getNick());
                nick2.setText(" "+us.getNick());
                email.setText(" "+us.getEmail());
                sexo.setText(" "+us.getSexo());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Perfil.this, MenuPrincipal.class);
                startActivity(i);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setAction(Intent.ACTION_GET_CONTENT);
                in.setType("image/*");
                startActivityForResult(in, FotoGaleria);
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FotoGaleria && resultCode == RESULT_OK && data != null) {
            Uri imgUri = data.getData();

            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .setMaxCropResultSize(550, 550)
                    .start(this);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {

                Uri resultUri = result.getUri();
                final StorageReference filePath = storageReference.child(u.getId()+".jpg");

                filePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                           @Override
                           public void onSuccess(Uri uri) {
                               final String downloadUrl = uri.toString();
                               databaseReference.child("fotoPerfil").setValue(downloadUrl)
                                       .addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {
                                               if(task.isSuccessful()){
                                                   Toast.makeText(Perfil.this, "Imagem de perfil armazenada com sucesso!",
                                                           Toast.LENGTH_SHORT).show();
                                               }else{
                                                   String message = task.getException().getMessage();
                                               }

                                           }
                                       });
                           }
                       });
                    }
                });
            }
        }

    }

}