package com.example.hp.madose;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class Authentification extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    final String TAG="alerte";
    ProgressBar progressBar;
    EditText identifiant;
    EditText motpass;
    ProgressDialog mProgressDialog;
    BaseDeDonne bd;
    String test="";

// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        bd=new BaseDeDonne(this);
        identifiant= findViewById(R.id.iden);
        motpass=(EditText)findViewById(R.id.pass);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        progressBar= findViewById(R.id.progressBar3);




        Button connect= findViewById(R.id.connexion);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*  if (identifiant.getText().toString().equals("admin") && motpass.getText().toString().equals("adminpass")) {
                    Intent intent = new Intent(Authentification.this, Acceuil.class);
                    identifiant.setText("");
                    motpass.setText("");
                    identifiant.requestFocus();
                    startActivity(intent);
                }
                else {
                    identifiant.setError("Identifiant ou mot de passe incorrect");
                    identifiant.requestFocus();
                }*/

            updateUI(null);
           signIn(identifiant.getText().toString(),motpass.getText().toString());



            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(!bd.checkIfTableHasData("Besoins_Sortie") && !bd.checkIfTableHasData("Categorie") && !bd.checkIfTableHasData("Demande") && !bd.checkIfTableHasData("Demande_Besoins") && !bd.checkIfTableHasData("Departement") && !bd.checkIfTableHasData("Utilisateur") && !bd.checkIfTableHasData("Besoin") && !bd.checkIfTableHasData("Besoins_Entree") && !bd.checkIfTableHasData("Entree") && !bd.checkIfTableHasData("Fournisseur") && !bd.checkIfTableHasData("Sortie"))
        {
            bd.insertCat("MATERIEL DE BUREAU");
            bd.insertCat("OUTIL INFORMATIQUE");
            bd.insertCat("MATERIEL DE CUISINE");
            bd.insertCat("MATERIEL D ENTRETIEN");
            bd.insertCat("OUTIL PAUSE CAFE");

            bd.insert("INFORMATIQUE");
            bd.insert("RECHERCHE");
            bd.insert("SECRETARIAT");
            bd.insert("DIRECTION");

            bd.insertBesoin("STYLO", "NON AMORTISSABLE", 1, 3, "0", 2,R.drawable.b21);
            bd.insertBesoin("MARKER", "NON AMORTISSABLE", 1, 2, "0", 0,R.drawable.b22);
            bd.insertBesoin("CORBEILLE", "NON AMORTISSABLE", 4, 2, "0", 0,R.drawable.b10);
            bd.insertBesoin("STICKY NOTES", "NON AMORTISSABLE", 1, 3, "0", 2,R.drawable.b17);
            bd.insertBesoin("CLIMATISEUR", "AMORTISSABLE", 1, 0, "2020-03-25", 0,R.drawable.b9);
            bd.insertBesoin("ORDINATEUR", "AMORTISSABLE", 2, 0, "2020-03-25", 0,R.drawable.b26);
            bd.insertBesoin("IMPRIMANTE", "AMORTISSABLE", 2, 0, "2020-03-25", 0,R.drawable.b14);

            bd.insertFour("CASH CENTER", "01 bp 4236 Abidjan 01", "22445623");
            bd.insertFour("CASH IVOIRE", "01 bp 4036 Abidjan 02", "22441683");
            bd.insertFour("CDCI", "01 bp 1250 Abidjan 10", "22441182");
            bd.insertFour("SOCOCE", "01 bp 4036 Abidjan 28", "22441683");

            bd.insertEmp("KOUADJO","Eric","ekouadjio@idconsulting.ie","01020304", 1, "SUPER ADMIN");
            bd.insertEmp("ADEJINLE","Patrick","padejinle@idconsulting.ie","01020304", 1, "SUPER ADMIN");
            bd.insertEmp("KONE","Myriame","mnayele@idconsulting.ie","01020304",  2, "USER");
            bd.insertEmp("KONE","Seydou","kseydou@idconsulting.ie","01020304", 2, "USER");
            bd.insertEmp("LAGO","Yvon","ylago@idconsulting.ie","01020304", 2, "USER");
            bd.insertEmp("ASSOH EPSE YAPI","Bénédicte","bassoh@idconsulting.ie","01020304", 3, "ADMIN");



            bd.insertEntr("2018-01-02", 1);
            bd.insertEntr("2018-01-02", 4);


            bd.insertEntrBes(1, 1, 150, 5, "Bic", "bleu");
            bd.insertEntrBes(2, 1, 250, 5, "Bic", "noir, permanent");
            bd.insertEntrBes(6, 2, 450000, 5, "HP", "noir, core i5, portable");
            bd.insertEntrBes(4, 2, 250000, 5, "SAMSUNG", "blanc");
            bd.insertEntrBes(5, 1, 500, 3, "PRIVILEGE", "200 sticks");

            bd.insertDemande("2018-01-02", 1, 1);
            bd.insertDemandeBesoin(1, 1, 1);
            bd.insertDemandeBesoin(1, 6, 1);
            MyApplication.setFetch(false);
        }
        if (currentUser !=null){
            mDatabase.child("users").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshotUser:dataSnapshot.getChildren()){
                        UtilisateurC user=dataSnapshotUser.getValue(UtilisateurC.class);
                        Toast.makeText(getApplicationContext(),user.getMailEmp(),Toast.LENGTH_SHORT);
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


            onAuthSuccess(mAuth.getCurrentUser());
        } else {
            mAuth.signInWithEmailAndPassword("test@idconsulting.ie","password").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshotUser:dataSnapshot.getChildren()){
                                    UtilisateurC user=dataSnapshotUser.getValue(UtilisateurC.class);
                                  //  Toast.makeText(getApplicationContext(),user.getMailEmp(),Toast.LENGTH_SHORT);
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
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                    }
                }
            });
            mAuth.signOut();
        }
    }

    private void onAuthSuccess(FirebaseUser user){
        String username = usernameFromEmail(user.getEmail());
       //  writeNewUser(user.getUid(), username, user.getEmail());
        startActivity(new Intent(Authentification.this, Acceuil.class));
        finish();

    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);
        mDatabase.child("users").child(userId).setValue(user);


    }
    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    public void updateUI(FirebaseUser user) {
        if (user ==null){
         showProgressDialog();


        }

    }

    public void signIn(String email, final String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    //updateUI(user);
                    Intent intent = new Intent(Authentification.this, Acceuil.class);
                    Log.d(TAG, "signInWithEmail:success");
                    startActivity(intent);
                    finish();
                  hideProgressDialog();
                } else {
                    if (bd.checkMailExist(identifiant.getText().toString())) {

                        mAuth.createUserWithEmailAndPassword(identifiant.getText().toString(), motpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(Authentification.this, Acceuil.class));
                                    finish();
                                } else {
                                    hideProgressDialog();
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    identifiant.setError("Identifiant ou mot de passe incorrect");
                                    identifiant.requestFocus();
                                }
                            }
                        });
                    } else
                    {
                        hideProgressDialog();
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        identifiant.setError("Identifiant ou mot de passe incorrect");
                        identifiant.requestFocus();
                    }
                }

            }
        });
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Chargement...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    public void writeNewBesoin(String libBes,String typBes,String libCat,int seuilBes, String amorBes,int stockBes,int imageBes){
        String code=libBes;
        if (libBes.contains(" ")){
            code=libBes.replace(" ","-");
        }
        if (libBes.contains("'")){
            code=code.replace("'","-");
        }

        BesoinC cat=new BesoinC(libBes,typBes,libCat,seuilBes,amorBes,stockBes,imageBes);
        mDatabase.child("Besoin").child(code).setValue(cat);
    }

}
