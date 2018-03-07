package com.example.hp.madose;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Utilisateur extends AppCompatActivity {

    DatabaseReference mDatabase;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe);


        final EditText codeT=(EditText) findViewById(R.id.nomEmp);
        final EditText codeP=(EditText) findViewById(R.id.profil);
        final EditText codeD=(EditText) findViewById(R.id.autoCompDep);
        final Button codeB=(Button) findViewById(R.id.valEmp);
        final EditText prenE= findViewById(R.id.prenEmp);
        final EditText mailE= findViewById(R.id.email);
        final EditText telE= findViewById(R.id.contact);
        final Button quitter=(Button) findViewById(R.id.button9);
        final BaseDeDonne bd=new BaseDeDonne(this);

        mDatabase= FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //fonction autotexcomplet



        //fonction liste view
        codeD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                Intent intent=new Intent(Utilisateur.this,Listedepartement.class);
                intent.putExtra("employer",codeT.getText().toString());
                intent.putExtra("employerr",codeP.getText().toString());
                intent.putExtra("employerrr",prenE.getText().toString());
                intent.putExtra("employerrrr",mailE.getText().toString());
                intent.putExtra("employerrrrr",telE.getText().toString());
                startActivity(intent);
            }
        });

        Intent intent=getIntent();
        if (intent !=null)
        {
            codeD.setText(intent.getStringExtra("departement"));
            codeT.setText(intent.getStringExtra("employer"));
            codeP.setText(intent.getStringExtra("employerr"));
            prenE.setText(intent.getStringExtra("employerrr"));
            mailE.setText(intent.getStringExtra("employerrrr"));
            telE.setText(intent.getStringExtra("employerrrrr"));
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
                    Toast.makeText(getBaseContext(),"Veuillez saisir le nom de l'utilisateur SVP!",Toast.LENGTH_LONG).show();
                }
                else if (mailE.getText().toString().equals(""))
                {
                    mailE.requestFocus();
                    Toast.makeText(getBaseContext(),"Veuillez saisir l'adresse mail de l'utilisateur SVP!",Toast.LENGTH_LONG).show();
                }
                else if (telE.getText().toString().equals(""))
                {
                    telE.requestFocus();
                    Toast.makeText(getBaseContext(),"Veuillez saisir le numéro de téléphone de l'utilisateur SVP!",Toast.LENGTH_LONG).show();
                }
                else if (codeP.getText().toString().equals(""))
                {
                    codeP.requestFocus();
                    Toast.makeText(getBaseContext(),"Veuillez saisir le profil de l'utilisateur SVP!",Toast.LENGTH_LONG).show();
                }
                else if (codeD.getText().toString().equals(""))
                {
                    codeD.requestFocus();
                    Toast.makeText(getBaseContext(),"Veuillez saisir le département de l'utilisateur SVP!",Toast.LENGTH_LONG).show();
                }
                else {
                    int x = Integer.parseInt(bd.selectDep(codeD.getText().toString()));
                    if (!bd.checkMailExist(mailE.getText().toString())) {
                        bd.insertEmp(codeT.getText().toString(), prenE.getText().toString(), mailE.getText().toString(), telE.getText().toString(), x, codeP.getText().toString());
                        bd.close();
                    }
                    String username=mailE.getText().toString().split("@")[0];

                    writeNewUser(username+"-"+codeT.getText().toString(),codeT.getText().toString(),prenE.getText().toString(),mailE.getText().toString(),telE.getText().toString(),codeD.getText().toString(),codeP.getText().toString());

                    Toast.makeText(getApplicationContext(), "Utilisateur enregistré avec succès", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Utilisateur.this, Affichage.class);
                    intent.putExtra("passage", "employe");
                    codeT.setText("");
                    codeP.setText("");
                    codeD.setText("");
                    mailE.setText("");
                    prenE.setText("");
                    telE.setText("");
                    startActivity(intent);
               }
            }
        });

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Utilisateur.this,Acceuil.class);
                startActivity(intent);
            }
        });
    }
    private void writeNewUser(String userId, String name, String surname, String email, String tel, String department, String profile) {
        UtilisateurC user = new UtilisateurC(name, surname, email, tel, department, profile);
        mDatabase.child("users").child(userId).setValue(user);

    }

}
