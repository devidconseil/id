package com.example.hp.madose;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.ArrayList;

public class Utilisateur extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseReference mDatabase;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe);


        final EditText codeT=(EditText) findViewById(R.id.nomEmp);
        final EditText codeD=(EditText) findViewById(R.id.autoCompDep);
        final Button codeB=(Button) findViewById(R.id.valEmp);
        final EditText prenE= findViewById(R.id.prenEmp);
        final EditText mailE= findViewById(R.id.email);
        final EditText telE= findViewById(R.id.contact);
        final Button quitter=(Button) findViewById(R.id.button9);
        final BaseDeDonne bd=new BaseDeDonne(this);

        mDatabase= FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase.getInstance().getReference("users").keepSynced(true);
        mAuth = FirebaseAuth.getInstance();

        final Spinner spinner=(Spinner) findViewById(R.id.profil);
        final String []utilisateur={"Choisir le profil","USER","ADMIN","SUPER ADMIN"};
        spinner.setOnItemSelectedListener(this);
       ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,utilisateur);
       arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
       spinner.setAdapter(arrayAdapter);

        mDatabase.child("Departement").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotDepart:dataSnapshot.getChildren()){
                    DepartementC depart=dataSnapshotDepart.getValue(DepartementC.class);
                    if (!bd.checkIfDepartmentExist(depart.getLibDep())){
                        bd.insert(depart.getLibDep());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotUser:dataSnapshot.getChildren()){
                    UtilisateurC user=dataSnapshotUser.getValue(UtilisateurC.class);
                    Log.i("CHAQUE USER",user.getMailEmp());
                    if (!bd.checkIfUserExist(user)){
                        int s=Integer.parseInt(bd.selectDep(user.getLibDep()));
                        bd.insertEmp(user.getNomEmp(),user.getPrenEmp(),user.getMailEmp(),user.getTelEmp(),s,user.getProEmp());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        //fonction liste view
        codeD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Utilisateur.this,Listedepartement.class);
                intent.putExtra("code","utilisateur");
                intent.putExtra("employer",codeT.getText().toString());
                intent.putExtra("bringO","utilisateur");
                // intent.putExtra("employerr",codeP.getT().toString());
                intent.putExtra("employerrr",prenE.getText().toString());
                intent.putExtra("employerrrr",mailE.getText().toString());
                intent.putExtra("employerrrrr",telE.getText().toString());
                startActivity(intent);
            }
        });
        codeD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


            }
        });

        Intent intent=getIntent();
        if (intent !=null)
        {
            codeD.setText(intent.getStringExtra("departement"));
            codeT.setText(intent.getStringExtra("employer"));
           // codeP.setText(intent.getStringExtra("employerr"));
            prenE.setText(intent.getStringExtra("employerrr"));
            mailE.setText(intent.getStringExtra("employerrrr"));
            telE.setText(intent.getStringExtra("employerrrrr"));
        }


        codeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText codeT=(EditText) findViewById(R.id.nomEmp);
                final Spinner codeP=(Spinner) findViewById(R.id.profil);
               // Toast.makeText(getBaseContext(),"Veuillez saisir le nom de l'emplyé SVP!",Toast.LENGTH_LONG).show();

                if (codeT.getText().toString().equals(""))
                {
                    codeT.requestFocus();
                    codeT.setError("Veuillez saisir le nom de l'utilisateur SVP!");;
                }
                else if (mailE.getText().toString().equals(""))
                {
                    mailE.requestFocus();
                    mailE.setError("Veuillez saisir l'adresse mail de l'utilisateur SVP!");
                }
                else if (telE.getText().toString().equals(""))
                {
                    telE.requestFocus();
                    telE.setError("Veuillez saisir le numéro de téléphone de l'utilisateur SVP!");
                }
                else if (spinner.getSelectedItem().toString().equals("Choisir le profil"))
                {
                    spinner.requestFocus();
                    Toast.makeText(getBaseContext(),"Veuillez saisir le profil de l'utilisateur SVP!",Toast.LENGTH_LONG).show();
                }
                else if (codeD.getText().toString().equals(""))
                {
                    codeD.requestFocus();
                    Toast.makeText(getBaseContext(),"Veuillez saisir le département de l'utilisateur SVP!",Toast.LENGTH_LONG).show();
                }
                else {
                    if (codeT.getText().toString().contains("'")){
                        codeT.getText().toString().replace("'","''");
                    }
                    if (prenE.getText().toString().contains("'")){
                        prenE.getText().toString().replace("'","''");
                    }
                    int x = Integer.parseInt(bd.selectDep(codeD.getText().toString()));
                    if (!bd.checkMailExist(mailE.getText().toString())) {
                       bd.insertEmp(codeT.getText().toString(), prenE.getText().toString(), mailE.getText().toString(), telE.getText().toString(), x, spinner.getSelectedItem().toString());
                        bd.close();
                        String username=mailE.getText().toString().split("@")[0];

                        writeNewUser(username+"-"+codeT.getText().toString(),codeT.getText().toString(),prenE.getText().toString(),mailE.getText().toString(),telE.getText().toString(),codeD.getText().toString(),spinner.getSelectedItem().toString());

                        Toast.makeText(getApplicationContext(), "Utilisateur enregistré avec succès", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Utilisateur.this, Affichage.class);
                        intent.putExtra("passage", "employe");
                        codeT.setText("");
                       // codeP.setText("");
                        codeD.setText("");
                        mailE.setText("");
                        prenE.setText("");
                        telE.setText("");
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), spinner.getOnItemSelectedListener().toString(), Toast.LENGTH_LONG).show();
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Utilisateur.this,0x00000005 );
                        builder.setMessage("Cet utilisateur ne peut être créé car il existe déjà");
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
                Intent intent=new Intent(Utilisateur.this,Acceuil.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Utilisateur.this,0x00000005 );
        builder.setMessage("Voulez-vous abandonner l'enregistrement?");
        builder.setTitle("Attention!");
        builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Utilisateur.this,Acceuil.class);
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
    private void writeNewUser(String userId, String name, String surname, String email, String tel, String department, String profile) {
        UtilisateurC user = new UtilisateurC(name, surname, email, tel, department, profile);
        mDatabase.child("users").child(userId).setValue(user);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
