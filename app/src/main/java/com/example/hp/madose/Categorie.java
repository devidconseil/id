package com.example.hp.madose;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Categorie extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);

        final EditText codeT=(EditText) findViewById(R.id.editCat);
        final Button codeB=(Button) findViewById(R.id.valCat);
        final BaseDeDonne bd=new BaseDeDonne(this);

        codeB.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        bd.insertCat(codeT.getText().toString());
        bd.close();

        Toast.makeText(getApplicationContext(),"Catégorie enregistrée avec succès", Toast.LENGTH_LONG).show();
        Intent intent=new Intent(Categorie.this,Affichage.class);
        intent.putExtra("passage","categorie");
        codeT.setText("");
        startActivity(intent);
    }
});

        final Button quitt =(Button)findViewById(R.id.buttonAn);
        quitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Categorie.this,Acceuil.class);
                codeT.setText("");
                startActivity(intent);
            }
        });

    }
}
