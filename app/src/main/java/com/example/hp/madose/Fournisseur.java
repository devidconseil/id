package com.example.hp.madose;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Fournisseur extends AppCompatActivity {
DatabaseReference mDatabase;
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
        mDatabase= FirebaseDatabase.getInstance().getReference();

        fbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fnom.getText().toString().equals(""))
                {
                    fnom.requestFocus();
                    fnom.setError("Veuillez saisir le nom du fournisseur SVP!!");
                }
                else if (fadr.getText().toString().equals(""))
                {
                    fadr.requestFocus();
                    fadr.setError("Veuillez saisir l'adresse du fournisseur SVP!!");
                }
                else if (fcont.getText().toString().equals(""))
                {
                    fcont.requestFocus();
                    fcont.setError("Veuillez saisir le contact du fournisseur SVP!!");
                }
                else {
                    if (! bd.checkIfFournisseurExist(fnom.getText().toString())){
                    int x = Integer.parseInt(fcont.getText().toString());
                    bd.insertFour(fnom.getText().toString(), fadr.getText().toString(), fcont.getText().toString());
                    bd.close();
                    writeNewFournisseur(fnom.getText().toString(), fadr.getText().toString(), fcont.getText().toString());
                    Toast.makeText(getApplicationContext(), "Fournisseur enregistré avec succès", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Fournisseur.this, Affichage.class);
                    intent.putExtra("passage", "fournisseur");
                    fnom.setText("");
                    fadr.setText("");
                    fcont.setText("");
                    startActivity(intent);
                    finish();
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Fournisseur.this,0x00000005 );
                        builder.setMessage("Ce fournisseur ne peut être créé car il existe déjà");
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

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Fournisseur.this,Acceuil.class);
                fnom.setText("");
                fadr.setText("");
                fcont.setText("");
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Fournisseur.this,0x00000005 );
        builder.setMessage("Voulez-vous abandonner l'enregistrement?");
        builder.setTitle("Attention!");
        builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Fournisseur.this,Acceuil.class);
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
    public void writeNewFournisseur(String nomFour,String adrFour,String telFour){
        String code=nomFour;
        if (nomFour.contains(" ")){
            code=nomFour.replace(" ","-");
        }
        if (nomFour.contains("'")){
            code=code.replace("'","-");
        }

        FournisseurC cat=new FournisseurC(nomFour,adrFour,telFour);
        mDatabase.child("Fournisseur").child(code).setValue(cat);
    }
}
