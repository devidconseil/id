package com.example.hp.madose;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.madose.Listes.CategorieListe;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Categorie extends AppCompatActivity {


  DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);

        final EditText codeT=(EditText) findViewById(R.id.editCat);
        final Button codeB=(Button) findViewById(R.id.valCat);
        final BaseDeDonne bd=new BaseDeDonne(this);
        mDatabase= FirebaseDatabase.getInstance().getReference();

        codeB.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if (codeT.getText().toString().equals(""))
        {
            codeT.requestFocus();
            Toast.makeText(getBaseContext(),"Veuillez saisir la catégorie SVP!!",Toast.LENGTH_LONG).show();
        }
        else {
            if (! bd.checkIfCategorieExist(codeT.getText().toString())) {
                String label = codeT.getText().toString();
                if (codeT.getText().toString().contains("'")) {
                    label = codeT.getText().toString().replace("'", "''");
                }
                bd.insertCat(label);
                writeNewCategory(codeT.getText().toString());
                bd.close();
                Toast.makeText(getApplicationContext(), "Catégorie enregistrée avec succès", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Categorie.this, CategorieListe.class);
                intent.putExtra("passage", "categorie");
                codeT.setText("");
                startActivity(intent);
                finish();
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(Categorie.this,0x00000005 );
                builder.setMessage("Cette catégorie ne peut être crééé car elle existe déjà");
                builder.setTitle("Echec");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create();
                builder.show();
            }
        }
    }
});

        final Button quitt =(Button)findViewById(R.id.buttonAn);
        quitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Categorie.this,Acceuil.class);
                codeT.setText("");
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Categorie.this,0x00000005 );
        builder.setMessage("Voulez-vous abandonner l'enregistrement?");
        builder.setTitle("Attention!");
        builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Categorie.this,Acceuil.class);
                startActivity(intent);
                finish();
                MyApplication.setFait(false);
            }
        });
        builder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }
    public void writeNewCategory(String libCat){
        String code=libCat;
        if (libCat.contains(" ")){
            code=libCat.replace(" ","-");
        }
        if (libCat.contains("'")){
            code=code.replace("'","-");
        }

        CategorieC cat=new CategorieC(libCat);
        mDatabase.child("Categorie").child(code).setValue(cat);
    }
}
