package com.example.hp.madose;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.madose.Listes.FournisseurListe;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

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


        if(getIntent().getStringExtra("action").equals("modifier"))
        {
            fnom.setText(MyApplication.getModifFnom());
            fadr.setText(MyApplication.getModifFadre());
            fcont.setText(MyApplication.getModifFcont());
        }
        else if (getIntent().getStringExtra("action").equals("ajouter"))
        {
           /* Snackbar.make(fcont, "ajout d'un fournisseur!!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        }
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
                else if(getIntent().getStringExtra("action").equals("modifier"))
                {
                    bd.updatefournisseur(MyApplication.getModifFId(),fnom.getText().toString(),fadr.getText().toString(),fcont.getText().toString());
                    bd.close();
                    Intent intent=new Intent(getBaseContext(),FournisseurListe.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    if (! bd.checkIfFournisseurExist(fnom.getText().toString())){
//                    int x = Integer.parseInt(fcont.getText().toString());
                        fnom.setText(fnom.getText().toString().toUpperCase());
                        fadr.setText(fadr.getText().toString().toUpperCase());
                        if (fnom.getText().toString().contains("'")){
                            fnom.setText(fnom.getText().toString().replace("'","''"));
                        }
                    bd.insertFour(fnom.getText().toString(), fadr.getText().toString(), fcont.getText().toString());
                    bd.close();
                        if (fnom.getText().toString().contains("''")){
                            fnom.setText(fnom.getText().toString().replace("''","'"));
                        }
                    writeNewFournisseur(fnom.getText().toString(), fadr.getText().toString(), fcont.getText().toString());
                        updateConnectivity(MyApplication.getmAuth().getCurrentUser().getEmail());
                    Toast.makeText(getApplicationContext(), "Fournisseur enregistré avec succès", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Fournisseur.this, FournisseurListe.class);
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
    public void updateConnectivity(String mail){
        String username=usernameFromEmail(mail);
        String reste=mail.substring(username.length()+1,mail.length()-3);
        String rest=mail.substring(mail.length()-2,mail.length());
        String code=username+"-"+reste+"-"+rest;
        Time time=new Time("GMT");
        time.setToNow();
        time.format("DD-MM-YYYY HH:MM:SS");
        Log.i("connect",code);
        Map<String,Object> childUpdates=new HashMap<>();
        childUpdates.put("/Connectivité/"+code,time.toString());
        MyApplication.getmDatabase().updateChildren(childUpdates);
        String a,b,c,d,e,f,tiime,temps;
        BaseDeDonne bd=new BaseDeDonne(getApplicationContext());
        tiime=time.toString();
        a=tiime.substring(0,4);
        b=tiime.substring(4,6);
        c=tiime.substring(6,8);
        d=tiime.substring(9,11);
        e=tiime.substring(11,13);
        f=tiime.substring(13,15);
        temps=c+"-"+b+"-"+a+" "+d+":"+e+":"+f;
        bd.updateConnect(temps,mail);


    }
    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }
}
