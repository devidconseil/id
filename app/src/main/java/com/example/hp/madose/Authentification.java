package com.example.hp.madose;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentification extends AppCompatActivity {

    FirebaseAuth mAuth;
    final String TAG="alerte";
    ProgressBar progressBar;
    TextInputEditText identifiant;
    EditText motpass;

// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        identifiant= findViewById(R.id.iden);
        motpass=(EditText)findViewById(R.id.pass);

        mAuth = FirebaseAuth.getInstance();
        progressBar= findViewById(R.id.progressBar3);




        Button connect= findViewById(R.id.connexion);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*  if (identifiant.getText().toString().equals("admin") && motpass.getText().toString().equals("adminpass")) {
                    Intent intent = new Intent(Authentification.this, Acceuil.class);
                    identifiant.setText("");
                    motpass.setText("");
                    identifiant.requestFocus();
                    startActivity(intent);
                }
                else {
                    identifiant.setError("Identifiant ou mot de passe incorrect");
                    identifiant.requestFocus();
                }*/

            updateUI(null);
            signIn(identifiant.getText().toString(),motpass.getText().toString());


            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

    }

    public void updateUI(FirebaseUser user) {
        if (user ==null){
            progressBar.setProgress(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);


        }

    }

    public void signIn(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    //updateUI(user);
                    Intent intent = new Intent(Authentification.this, Acceuil.class);
                    Log.d(TAG, "signInWithEmail:success");
                    startActivity(intent);
                    progressBar.setVisibility(View.GONE);
                } else {
                    // If sign in fails, display a message to the user.
                    progressBar.setVisibility(View.GONE);
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                   identifiant.setError("Identifiant ou mot de passe incorrect");
                   identifiant.requestFocus();
                    //updateUI(null);
                }

                // ...
            }
        });
    }
}
