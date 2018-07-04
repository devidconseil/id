package com.example.hp.madose;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hp.madose.Listes.UtilisateurListe;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utilisateur extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseReference mDatabase;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe);


         final EditText codeT=(EditText) findViewById(R.id.nomEmp);
        final AutoCompleteTextView codeD= findViewById(R.id.autoCompDep);
        final Button codeB=(Button) findViewById(R.id.valEmp);
        final EditText prenE= findViewById(R.id.prenEmp);
        final EditText mailE= findViewById(R.id.email);
        final EditText telE= findViewById(R.id.contact);
        final Spinner codeP=(Spinner) findViewById(R.id.profil);
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
        ArrayList<String> nb=bd.affiNDE();
        ArrayAdapter<String>nombes=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nb);
        codeD.setAdapter(nombes);
        if (getIntent().getStringExtra("status").equals("new user request")){
            spinner.setVisibility(View.INVISIBLE);
            MyApplication.getmAuth().signInWithEmailAndPassword("test@idconsulting.ie","password").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Log.i("ETAT","bon");
                    }
                    else {
                        Log.i("ETAT","mauvais");
                    }
                }
            });
        }
        else {
            spinner.setVisibility(View.VISIBLE);
        }


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
                        bd.insertEmp(user.getNomEmp(),user.getPrenEmp(),user.getMailEmp(),user.getTelEmp(),s,user.getProEmp(),user.getValEmp());

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
                if (spinner.getVisibility()==View.INVISIBLE){
                    intent.putExtra("status","new user request");
                }
                if (spinner.getVisibility()==View.VISIBLE){
                    intent.putExtra("status","new user creating");
                }
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
        if (getIntent().getStringExtra("status").equals("new user validating")){
            List<String> notification=new ArrayList<>();
            for (String item: bd.validatingAccount(MyApplication.mail)){
                notification.add(item);
            }
            Toast.makeText(getApplicationContext(), notification.get(1), Toast.LENGTH_SHORT).show();
            codeT.setText(notification.get(0));
            codeT.setEnabled(false);
            prenE.setText(notification.get(1));
            prenE.setEnabled(false);
            mailE.setText(notification.get(2));
            mailE.setEnabled(false);
            telE.setText(notification.get(3));
            telE.setEnabled(false);
            codeD.setText(notification.get(4));
            codeD.setEnabled(false);
            spinner.requestFocus();
        }
        else if (getIntent().getStringExtra("status").equals("modifier"))
        {
            codeT.setText(MyApplication.getModifNom());
            prenE.setText(MyApplication.getModifNom());
            mailE.setText(MyApplication.getModifEmail());
            codeD.setText(MyApplication.getModifDepart());
            telE.setText(MyApplication.getModifContact());


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
                else if (spinner.getSelectedItem().toString().equals("Choisir le profil") && getIntent().getStringExtra("status").equals("new user creating"))
                {
                    spinner.requestFocus();
                    Toast.makeText(getBaseContext(),"Veuillez saisir le profil de l'utilisateur SVP!",Toast.LENGTH_LONG).show();
                }
                else if (codeD.getText().toString().equals(""))
                {
                    codeD.requestFocus();
                    Toast.makeText(getBaseContext(),"Veuillez saisir le département de l'utilisateur SVP!",Toast.LENGTH_LONG).show();
                }
                else if (getIntent().getStringExtra("status").equals("modifier"))
                {
                    int x = Integer.parseInt(bd.selectDep(codeD.getText().toString()));
                    bd.updateUtilisateur(Integer.parseInt(MyApplication.getModifId()),codeT.getText().toString(),prenE.getText().toString(),x,mailE.getText().toString(),spinner.getSelectedItem().toString(),telE.getText().toString());
                    Toast.makeText(getBaseContext(),"Modification éffectuée avec succès",Toast.LENGTH_LONG).show();
                }
                else {


                    int x = Integer.parseInt(bd.selectDep(codeD.getText().toString()));
                    if (!bd.checkMailExist(mailE.getText().toString())) {
                       String name1=prenE.getText().toString().substring(0,1).toUpperCase();
                       prenE.setText(prenE.getText().toString().substring(1,prenE.getText().toString().length()).toLowerCase());
                       prenE.setText(name1+prenE.getText().toString());
                       codeT.setText(codeT.getText().toString().toUpperCase());
                        if (codeT.getText().toString().contains("'")){
                            codeT.setText(codeT.getText().toString().replace("'","''"));
                        }
                        if (prenE.getText().toString().contains("'")){
                            prenE.setText(prenE.getText().toString().replace("'","''"));
                        }
                        if (spinner.getSelectedItem().toString().equals("Choisir le profil")){
                            bd.insertEmp(codeT.getText().toString(), prenE.getText().toString(), mailE.getText().toString(), telE.getText().toString(), x, "","NO");

                            String username=mailE.getText().toString().split("@")[0];
                            writeNewUser(username+"-"+codeT.getText().toString(),codeT.getText().toString(),prenE.getText().toString(),mailE.getText().toString(),telE.getText().toString(),codeD.getText().toString(),"","NO");


                            MyApplication.getmAuth().signOut();


                        }
                        else {

                                bd.insertEmp(codeT.getText().toString(), prenE.getText().toString(), mailE.getText().toString(), telE.getText().toString(), x, spinner.getSelectedItem().toString(), "YES");
                            String username=mailE.getText().toString().split("@")[0];
                        if (codeT.getText().toString().contains("''")){
                            codeT.setText(codeT.getText().toString().replace("''","'"));
                        }
                        if (prenE.getText().toString().contains("''")){
                            prenE.setText(prenE.getText().toString().replace("''","'"));
                        }
                        writeNewUser(username+"-"+codeT.getText().toString(),codeT.getText().toString(),prenE.getText().toString(),mailE.getText().toString(),telE.getText().toString(),codeD.getText().toString(),spinner.getSelectedItem().toString(),"YES");

                        updateConnectivity(MyApplication.getmAuth().getCurrentUser().getEmail());
                        }

                        bd.close();
                     /*   if (MyApplication.isNewAccount()){
                            MyApplication.notifications.add("Un nouvel utilisateur enregistré est en attente de validation:"+mailE.getText().toString());
                        }  */
                        Toast.makeText(getApplicationContext(), "Utilisateur enregistré avec succès", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Utilisateur.this, UtilisateurListe.class);
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
                        if (MyApplication.isNewAccount()){
                          startActivity(new Intent(Utilisateur.this,Authentification.class));
                          MyApplication.getmAuth().signOut();
                          MyApplication.setNewAccount(false);
                        }
                    }
                    else if (bd.checkMailExist(mailE.getText().toString()) && bd.checkAccountValidate(mailE.getText().toString()).equals("NO")){
                        if (getIntent().getStringExtra("status").equals("new user validating")){
                            Toast.makeText(getApplicationContext(),spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT);
                            bd.upDateUserDetails(spinner.getSelectedItem().toString(),MyApplication.mail);
                            String username=mailE.getText().toString().split("@")[0];
                            if (codeT.getText().toString().contains("''")){
                                codeT.setText(codeT.getText().toString().replace("''","'"));
                            }
                            if (prenE.getText().toString().contains("''")){
                                prenE.setText(prenE.getText().toString().replace("''","'"));
                            }
                            updateNewUser(codeT.getText().toString(),mailE.getText().toString(),spinner.getSelectedItem().toString(),"YES");
                            updateConnectivity(MyApplication.getmAuth().getCurrentUser().getEmail());
                            Toast.makeText(getApplicationContext(), "Utilisateur validé avec succès", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Utilisateur.this, UtilisateurListe.class);
                            codeT.setText("");
                            // codeP.setText("");
                            codeD.setText("");
                            mailE.setText("");
                            prenE.setText("");
                            telE.setText("");
                            startActivity(intent);
                            finish();
                        }
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
                if (MyApplication.isNewAccount()){
                    startActivity(new Intent(Utilisateur.this,Welcome.class));
                    MyApplication.setNewAccount(false);
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        if (MyApplication.isNewAccount()){
            startActivity(new Intent(Utilisateur.this,Welcome.class));
            MyApplication.setNewAccount(false);
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(Utilisateur.this, 0x00000005);
            builder.setMessage("Voulez-vous abandonner l'enregistrement?");
            builder.setTitle("Attention!");
            builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Utilisateur.this, Acceuil.class);
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
    }
    private void writeNewUser(String userId, String name, String surname, String email, String tel, String department, String profile, String validate) {
        UtilisateurC user = new UtilisateurC(name, surname, email, tel, department, profile,validate);
        mDatabase.child("users").child(userId).setValue(user);

    }
    private void updateNewUser( String name, String email,  String profile, String validate) {

        String username,code;
        username=email.split("@")[0];
        code=username+"-"+name;
        Map<String,Object> childUpdates=new HashMap<>();
        childUpdates.put("/users/"+code+"/proEmp",profile);
        childUpdates.put("/users/"+code+"/valEmp",validate);
        MyApplication.getmDatabase().updateChildren(childUpdates);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
