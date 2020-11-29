package br.com.ifpr.projetointegrador.Firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ConfiguracoesFirebase {
    private static DatabaseReference database;
    private static FirebaseAuth auth;
    private static StorageReference storage;

    public static DatabaseReference getFirebaseDatabase(){

        if( database == null){

            database = FirebaseDatabase.getInstance().getReference();

        }
        return database;

    }

    public static FirebaseAuth getFirebaseAutenticacao(){

        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;

    }

    public static StorageReference getfirebasestorage(){

        if(storage == null){
            storage = FirebaseStorage.getInstance().getReference();
        }
        return storage;

    }


}
