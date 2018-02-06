package com.example.hp.madose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Employe extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe);


        final EditText codeT=(EditText) findViewById(R.id.nomEmp);
        final EditText codeP=(EditText) findViewById(R.id.profil);
        final EditText codeD=(EditText) findViewById(R.id.autoCompDep);
        final Button codeB=(Button) findViewById(R.id.valEmp);
        final Button quitter=(Button) findViewById(R.id.button9);
        final BaseDeDonne bd=new BaseDeDonne(this);

        //fonction autotexcomplet



        //fonction liste view
        codeD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                Intent intent=new Intent(Employe.this,Listedepartement.class);
                intent.putExtra("employer",codeT.getText().toString());
                intent.putExtra("employerr",codeP.getText().toString());
                startActivity(intent);
            }
        });

        Intent intent=getIntent();
        if (intent !=null)
        {
            codeD.setText(intent.getStringExtra("departement"));
            codeT.setText(intent.getStringExtra("employer"));
            codeP.setText(intent.getStringExtra("employerr"));
        }


        codeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText codeT=(EditText) findViewById(R.id.nomEmp);
                final EditText codeP=(EditText) findViewById(R.id.profil);
               // Toast.makeText(getBaseContext(),"Veuillez saisir le nom de l'emplyé SVP!",Toast.LENGTH_LONG).show();

                if (codeT.getText().toString().equals(""))
                {
                    codeT.requestFocus();
                    Toast.makeText(getBaseContext(),"Veuillez saisir le nom de l'emplyé SVP!",Toast.LENGTH_LONG).show();
                }
                else if (codeP.getText().toString().equals(""))
                {
                    codeP.requestFocus();
                    Toast.makeText(getBaseContext(),"Veuillez saisir le profil de l'emplyé SVP!",Toast.LENGTH_LONG).show();
                }
                else if (codeD.getText().toString().equals(""))
                {
                    codeD.requestFocus();
                    Toast.makeText(getBaseContext(),"Veuillez saisir le departement de l'emplyé SVP!",Toast.LENGTH_LONG).show();
                }
                else {
                    int x = Integer.parseInt(bd.selectDep(codeD.getText().toString()));
                    bd.insertEmp(codeT.getText().toString(), x, codeP.getText().toString());
                    bd.close();

                    Toast.makeText(getApplicationContext(), "Employé enregistré avec succès", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Employe.this, Affichage.class);
                    intent.putExtra("passage", "employe");
                    codeT.setText("");
                    codeP.setText("");
                    codeD.setText("");
                    startActivity(intent);
               }
            }
        });

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Employe.this,Acceuil.class);
                startActivity(intent);
            }
        });
    }

}
