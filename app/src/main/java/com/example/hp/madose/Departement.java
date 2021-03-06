package com.example.hp.madose;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.madose.Listes.DepartementListe;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Departement extends AppCompatActivity {


   DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departement);

       final Button valider=(Button)findViewById(R.id.validez);
       final Button annuler=(Button)findViewById(R.id.annulerDep);
       final EditText edite=(EditText)findViewById(R.id.libdep);
       final BaseDeDonne gest=new BaseDeDonne(this);
       mDatabase= FirebaseDatabase.getInstance().getReference();

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (edite.getText().toString().equals(""))
                {
                    edite.requestFocus();
                    Toast.makeText(getBaseContext(),"Veuillez saisir le nom du departement SVP!!",Toast.LENGTH_LONG).show();
                }
                else {
                    if (! gest.checkIfDepartmentExist(edite.getText().toString())){
                        if (edite.getText().toString().contains("'")){
                            edite.setText(edite.getText().toString().replace("'","''"));
                        }
                        edite.setText(edite.getText().toString().toUpperCase());
                    gest.insert(edite.getText().toString());
                    gest.close();
                        if (edite.getText().toString().contains("''")){
                            edite.setText(edite.getText().toString().replace("''","'"));
                        }
                    writeNewDepartment(edite.getText().toString());
                        updateConnectivity(MyApplication.getmAuth().getCurrentUser().getEmail());

                    Toast.makeText(getApplicationContext(), "Departement enregistré avec succès", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Departement.this, DepartementListe.class);
                    intent.putExtra("passage", "departement");
                    edite.setText("");
                    startActivity(intent);
                    finish();
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Departement.this,0x00000005 );
                        builder.setMessage("Ce departement ne peut être créé car il existe déjà");
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

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Departement.this,Acceuil.class);
                edite.setText("");
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Departement.this,0x00000005 );
        builder.setMessage("Voulez-vous abandonner l'enregistrement?");
        builder.setTitle("Attention!");
        builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Departement.this,Acceuil.class);
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
    public void writeNewDepartment(String libDep){
        String code=libDep;
        if (libDep.contains(" ")){
            code=libDep.replace(" ","-");
        }
        if (libDep.contains("'")){
            code=code.replace("'","-");
        }

        DepartementC cat=new DepartementC(libDep);
        mDatabase.child("Departement").child(code).setValue(cat);
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
