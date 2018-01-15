package com.example.hp.madose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Authentification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        final EditText identifiant=(EditText)findViewById(R.id.iden);
        final EditText motpass=(EditText)findViewById(R.id.pass);

        Button connect=(Button) findViewById(R.id.connexion);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (identifiant.getText().toString().equals("admin") && motpass.getText().toString().equals("adminpass")) {
                    Intent intent = new Intent(Authentification.this, Acceuil.class);
                   // identifiant.setText("");
                  //  motpass.setText("");
                   // identifiant.requestFocus();
                    startActivity(intent);
               // }
               // else {
                 //   Toast.makeText(getBaseContext(),"Identifiant ou mot de passe incorrect",Toast.LENGTH_LONG).show();
               // }

            }
        });
    }
}
