package com.example.hp.madose;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Authentification extends AppCompatActivity {


    final String TAG="alerte";
    ProgressBar progressBar;
    EditText identifiant;
    EditText motpass;
    TextView txtView;
    ProgressDialog mProgressDialog;
    BaseDeDonne bd;
    String test="";
    ConnexionDetector connexionDetector;


// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        bd=new BaseDeDonne(this);
        identifiant= findViewById(R.id.iden);
        motpass=(EditText)findViewById(R.id.pass);
        txtView= findViewById(R.id.textView16);
        String htmlString="<u>Mot de passe oublié?</u>";
        txtView.setText(Html.fromHtml(htmlString));
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Authentification");
        }


        txtView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Authentification.this, 0x00000005);
                builder.setView(R.id.iden);
                builder.setTitle("Veuillez entrer le mail!");
                builder.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar.make((View) dialog, "Email de réinitialisation envoyé!!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
                builder.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create();
                builder.show();
            }
        });
        connexionDetector=new ConnexionDetector(this);
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

             if(!connexionDetector.isConnected())
             {
                 Snackbar.make(v, "Connexion internet requise!!", Snackbar.LENGTH_LONG)
                         .setAction("Action", null).show();

             }
             else if (connexionDetector.isConnected())
             {
                 if (identifiant.getText().toString().equals("") && motpass.getText().toString().equals(""))
                 {
                     Snackbar.make(v, "Veuillez saisir votre identifiant et votre mot de passe SVP!!", Snackbar.LENGTH_LONG)
                             .setAction("Action", null).show();
                 }
                 else if(identifiant.getText().toString().equals(""))
                 {
                     identifiant.setError("Veuillez saisir votre identifiant SVP!!");
                 }
                 else if (motpass.getText().toString().equals(""))
                 {
                     motpass.setError("Veuillez saisir votre mot de passe SVP!!");
                 }
                 else
                 {
                     updateUI(null);
                     signIn(identifiant.getText().toString(), motpass.getText().toString());
                 }
             }



            }
        });
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(Authentification.this,Welcome.class));
    }
    @Override
    public void onStart(){
        super.onStart();

        FirebaseUser currentUser = MyApplication.mAuth.getCurrentUser();


   /*     if(!bd.checkIfTableHasData("Besoins_Sortie") && !bd.checkIfTableHasData("Categorie") && !bd.checkIfTableHasData("Demande") && !bd.checkIfTableHasData("Demande_Besoins") && !bd.checkIfTableHasData("Departement") && !bd.checkIfTableHasData("Utilisateur") && !bd.checkIfTableHasData("Besoin") && !bd.checkIfTableHasData("Besoins_Entree") && !bd.checkIfTableHasData("Entree") && !bd.checkIfTableHasData("Fournisseur") && !bd.checkIfTableHasData("Sortie"))
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

            bd.insertEmp("KOUADJO","Eric","ekouadjio@idconsulting.ie","01020304", 1, "SUPER ADMIN","YES");
            bd.insertEmp("ADEJINLE","Patrick","padejinle@idconsulting.ie","01020304", 1, "SUPER ADMIN","YES");
            bd.insertEmp("KONE","Myriame","mnayele@idconsulting.ie","01020304",  2, "USER","YES");
            bd.insertEmp("KONE","Seydou","kseydou@idconsulting.ie","01020304", 2, "USER","YES");
            bd.insertEmp("LAGO","Yvon","ylago@idconsulting.ie","01020304", 2, "USER","YES");
            bd.insertEmp("ASSOH EPSE YAPI","Bénédicte","bassoh@idconsulting.ie","01020304", 3, "ADMIN","YES");
            bd.insertEmp("DROH","Agostino","adroh@idconsulting.ie","01020304", 4, "SUPER ADMIN","YES");



            bd.insertEntr("2018-01-02", 1,"","padejinle@idconsulting.ie",true);
            bd.insertEntr("2018-01-02", 4,"","padejinle@idconsulting.ie",true);


            bd.insertEntrBes(1, 1, 150, 5, "Bic", "Bleu");
            int var2 = Integer.parseInt(bd.selectStockBes("STYLO"));
            int var3 = var2 + 5;
            bd.upDate(var3, "STYLO");
            bd.insertEntrBes(2, 1, 250, 5, "Bic", "Noir, permanent");
            var2 = Integer.parseInt(bd.selectStockBes("MARKER"));
            var3 = var2 + 5;
            bd.upDate(var3, "MARKER");
            bd.insertEntrBes(6, 2, 450000, 5, "HP", "Noir, core i5, portable");
            var2 = Integer.parseInt(bd.selectStockBes("ORDINATEUR"));
            var3 = var2 + 5;
            bd.upDate(var3, "ORDINATEUR");
            bd.insertEntrBes(4, 2, 500, 5, "PRIVILEGE", "200 Sticks");
            var2 = Integer.parseInt(bd.selectStockBes("STICKY NOTES"));
            var3 = var2 + 5;
            bd.upDate(var3, "ORDINATEUR");
            bd.insertEntrBes(5, 1, 250000, 3, "SAMSUNG", "Blanc");
            var2 = Integer.parseInt(bd.selectStockBes("IMPRIMANTE"));
            var3 = var2 + 3;
            bd.upDate(var3, "IMPRIMANTE");

            bd.insertSortie("2018-01-10","1",bd.selectCurrentDate(),"adepatrickade@idconsulting.ie",true);

            bd.insertSortieBesoin(1,1,1, "Bic","Bleu");
            var2 = Integer.parseInt(bd.selectStockBes("STYLO"));
            var3 = var2 - 1;
            bd.upDate(var3, "STYLO");

            bd.insertDemande("2018-01-02", 1, 1,bd.selectCurrentDate(),true);
            bd.insertDemandeBesoin(1, 1, 1);
            bd.insertDemandeBesoin(1, 6, 1);
            MyApplication.setFetch(false);
        }  */
        if (MyApplication.getmAuth().getCurrentUser() !=null && ! MyApplication.getmAuth().getCurrentUser().getEmail().toString().equals("test@idconsulting.ie")){
            onAuthSuccess(MyApplication.getmAuth().getCurrentUser());
            Log.i("UNO",MyApplication.getmAuth().getCurrentUser().getEmail());
        } else {

             MyApplication.getmAuth().signInWithEmailAndPassword("test@idconsulting.ie","password").addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){

                        MyApplication.getmDatabase().child("Categorie").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshotCat:dataSnapshot.getChildren()){
                                    CategorieC cat= dataSnapshotCat.getValue(CategorieC.class);
                                    if (! bd.checkIfCategorieExist(cat.getLibCat())){
                                        bd.insertCat(cat.getLibCat());
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        MyApplication.getmDatabase().child("Fournisseur").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshotFour:dataSnapshot.getChildren()){
                                    FournisseurC four=dataSnapshotFour.getValue(FournisseurC.class);
                                    if (!bd.checkIfFournisseurExist(four.getNomFour())){
                                        bd.insertFour(four.getNomFour(),four.getAdrFour(),four.getTelFour());
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        MyApplication.getmDatabase().child("Departement").addValueEventListener(new ValueEventListener() {
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
                        MyApplication.getmDatabase().child("Besoin").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshotBes:dataSnapshot.getChildren()){
                                    BesoinC cat= dataSnapshotBes.getValue(BesoinC.class);
                                    if (!bd.checkIfBesoinExist(cat.getLibBes())){
                                        Log.i("TESTONS",cat.getLibCat());
                                        int ss=Integer.parseInt(bd.selectCat(cat.getLibCat()));
                                        bd.insertBesoin(cat.getLibBes(),cat.getTypeBes(),ss,cat.getSeuilBes(),cat.getAmorBes(),cat.getStockBes(),cat.getImageBes());
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        MyApplication.getmDatabase().child("users").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshotUser:dataSnapshot.getChildren()){
                                    UtilisateurC user=dataSnapshotUser.getValue(UtilisateurC.class);
                                  //  Toast.makeText(getApplicationContext(),user.getMailEmp(),Toast.LENGTH_SHORT);
                                    if (!bd.checkIfUserExist(user)){
                                        Log.i("MONTRE-MOI",user.getLibDep());
                                        int s=Integer.parseInt(bd.selectDep(user.getLibDep()));
                                        bd.insertEmp(user.getNomEmp(),user.getPrenEmp(),user.getMailEmp(),user.getTelEmp(),s,user.getProEmp(),user.getValEmp());

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


FirebaseAuth.getInstance().signOut();

     }
    }

    private void onAuthSuccess(FirebaseUser user){
        String username = usernameFromEmail(user.getEmail());
       //  writeNewUser(user.getUid(), username, user.getEmail());
      //  finish();
       startActivity(new Intent(Authentification.this, Acceuil.class));


    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);
        MyApplication.getmDatabase().child("users").child(userId).setValue(user);


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

       MyApplication.getmAuth().signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    updateConnectivity(MyApplication.getmAuth().getCurrentUser().getEmail());
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = MyApplication.getmAuth().getCurrentUser();
                    //updateUI(user);
                    Intent intent = new Intent(Authentification.this, Acceuil.class);
                    Log.d(TAG, "signInWithEmail:success");
                    startActivity(intent);


                    finish();
                  hideProgressDialog();
                } else {
                    if (bd.checkMailExist(identifiant.getText().toString()) && bd.checkAccountValidate(identifiant.getText().toString()).equals("YES")) {

                        MyApplication.getmAuth().createUserWithEmailAndPassword(identifiant.getText().toString(), motpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    writeNewConnectivity(identifiant.getText().toString());
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
                        Toast.makeText(getApplicationContext(), "Authentication failed.\n"+bd.checkAccountValidate(identifiant.getText().toString()),
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
        MyApplication.getmDatabase().child("Besoin").child(code).setValue(cat);
    }
    private void writeNewUser(String userId, String name, String surname, String email, String tel, String department, String profile) {
        UtilisateurC user = new UtilisateurC(name, surname, email, tel, department, profile);
        MyApplication.getmDatabase().child("users").child(userId).setValue(user);

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
        MyApplication.getmDatabase().child("Fournisseur").child(code).setValue(cat);
    }
    public void writeNewConnectivity(String mail){
        String username=usernameFromEmail(mail);
        String reste=mail.substring(username.length()+1,mail.length()-3);
        String rest=mail.substring(mail.length()-2,mail.length());
        String code=username+"-"+reste+"-"+rest;
        Time time=new Time("GMT");
        time.setToNow();
        time.format("DD-MM-YYYY HH:MM:SS");
        Log.i("connect",code);
        MyApplication.getmDatabase().child("Connectivité").child(code).setValue(time.toString());

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

    }




}
