package com.example.hp.madose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Fournisseur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fournisseur);

        final EditText fnom=(EditText)findViewById(R.id.nomF);
        final Button quitter=(Button)findViewById(R.id.button3);
        final EditText fadr=(EditText)findViewById(R.id.adrF);
        final EditText fcont=(EditText)findViewById(R.id.contF);
        final Button fbout=(Button)findViewById(R.id.valF);
        final BaseDeDonne bd=new BaseDeDonne(this);

        fbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int x=Integer.parseInt(fcont.getText().toString());
                bd.insertFour(fnom.getText().toString(),fadr.getText().toString(),x);
                bd.close();
                Toast.makeText(getApplicationContext(),"Fournisseur enregistré avec succès", Toast.LENGTH_LONG).show();

                Intent intent=new Intent(Fournisseur.this,Affichage.class);
                intent.putExtra("passage","fournisseur");
                fnom.setText("");
                fadr.setText("");
                fcont.setText("");
                startActivity(intent);
            }
        });

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Fournisseur.this,Acceuil.class);
                fnom.setText("");
                fadr.setText("");
                fcont.setText("");
                startActivity(intent);
            }
        });

    }
}
