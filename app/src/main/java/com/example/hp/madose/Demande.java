package com.example.hp.madose;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hp.madose.Listes.ListeDemandeUtilisateur;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Demande extends AppCompatActivity {

    private FirebaseAuth mAuth;

    DatabaseReference mDatabase;

    String ancien,nouveau,confirmation,profile;
    BaseDeDonne bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande);
       bd =new BaseDeDonne(this);
       mDatabase=FirebaseDatabase.getInstance().getReference();
        profile=bd.retrieveUserProfile(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        final EditText date=(EditText)findViewById(R.id.dateDemande);
        final Button ajouter=(Button)findViewById(R.id.ajout);
        final AutoCompleteTextView employe=(AutoCompleteTextView)findViewById(R.id.autoCompEmp);
  //      final AutoCompleteTextView depart=(AutoCompleteTextView)findViewById(R.id.autoCompDep);
        final AutoCompleteTextView bes=(AutoCompleteTextView)findViewById(R.id.autoCompBes);
        final EditText quant=(EditText)findViewById(R.id.editqt);
       /* final RadioButton radioButton_emp= findViewById(R.id.radioButton_emp);
        final RadioButton radioButton_dep= findViewById(R.id.radioButton_dep);
        final RadioGroup radioGroup= findViewById(R.id.radio_group);  */





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
        mDatabase.child("Categorie").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotCat:dataSnapshot.getChildren()){
                    CategorieC cat= dataSnapshotCat.getValue(CategorieC.class);
                    if (!bd.checkIfCategorieExist(cat.getLibCat())){
                        bd.insertCat(cat.getLibCat());
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
        mDatabase.child("Besoin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotBes:dataSnapshot.getChildren()){
                    BesoinC cat= dataSnapshotBes.getValue(BesoinC.class);
                    if (!bd.checkIfBesoinExist(cat.getLibBes())){
                        int ss=Integer.parseInt(bd.selectCat(cat.getLibCat()));
                        bd.insertBesoin(cat.getLibBes(),cat.getTypeBes(),ss,cat.getSeuilBes(),cat.getAmorBes(),cat.getStockBes(),cat.getImageBes());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Intent intent=getIntent();
        if (intent != null)
        {

            date.setText(intent.getStringExtra("demD"));
            employe.setText(intent.getStringExtra("demE"));
//            depart.setText(intent.getStringExtra("demDe"));
            bes.setText(intent.getStringExtra("demB"));
            quant.setText(intent.getStringExtra("demQ"));
          //  radioButton_dep.setChecked(intent.getBooleanExtra("etat1",radioButton_dep.isChecked()));
          //  radioButton_emp.setChecked(intent.getBooleanExtra("etat2",radioButton_emp.isChecked()));
            employe.setVisibility(intent.getIntExtra("etat3",employe.getVisibility()));
       //     depart.setVisibility(intent.getIntExtra("etat4",depart.getVisibility()));
            if (intent.getStringExtra("code").equals("listeD")) {
                bes.requestFocus();
            }
            else if (intent.getStringExtra("code").equals("listeU"))
            {
                bes.requestFocus();
            }
            else if (intent.getStringExtra("code").equals("listeB"))
            {
                quant.requestFocus();
            }
            else if (intent.getStringExtra("code").equals("utilisateur"))
            {
                date.requestFocus();
             //employe.setText(MyApplication.getNomUser());
            }
            else
            {
                date.requestFocus();
            }

        }
        mDatabase= FirebaseDatabase.getInstance().getReference();
        if (profile.equals("USER")){
            String name=bd.selectEmpNameFromMail(MyApplication.getmAuth().getCurrentUser().getEmail());
            Log.i("fff",name);
            employe.setText(name);
            employe.setEnabled(false);
        }
        employe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Demande.this,ListeUtilisateur.class);
                intent.putExtra("demDate",date.getText().toString());
                intent.putExtra("code","demande");
                intent.putExtra("demBesoin",bes.getText().toString());
                intent.putExtra("demQuantité",quant.getText().toString());
                intent.putExtra("etat1",false);
                intent.putExtra("etat2",true);
                intent.putExtra("etat3",View.VISIBLE);
                intent.putExtra("etat4",View.INVISIBLE);
                startActivity(intent);
                finish();
            }
        });
    /*    depart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Demande.this,Listedepartement.class);
                intent.putExtra("demDate",date.getText().toString());
                intent.putExtra("code","demande");
                intent.putExtra("demBesoin",bes.getText().toString());
                intent.putExtra("demQuantité",quant.getText().toString());
                intent.putExtra("etat1",true);
                intent.putExtra("etat2",false);
                intent.putExtra("etat3",View.INVISIBLE);
                intent.putExtra("etat4",View.VISIBLE);
                startActivity(intent);
                finish();
            }
        });  */

        bes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          //      if (radioButton_emp.isChecked()){
                    Intent intent=new Intent(Demande.this,ListeBesoin.class);
                    intent.putExtra("demDate",date.getText().toString());
                    intent.putExtra("code","besoinEmployeD");
                    intent.putExtra("demEmp",employe.getText().toString());
                    intent.putExtra("demQuantité",quant.getText().toString());
                    intent.putExtra("etat1",false);
                    intent.putExtra("etat2",true);
                    intent.putExtra("etat3",View.INVISIBLE);
                    intent.putExtra("etat4",View.VISIBLE);
                    startActivity(intent);
                    finish();
            //    }
          /*      else if (radioButton_dep.isChecked()){
                    Intent intent=new Intent(Demande.this,ListeBesoin.class);
                    intent.putExtra("demDate",date.getText().toString());
                    intent.putExtra("code","besoinDepartementD");
                    intent.putExtra("demDepartement",depart.getText().toString());
                    intent.putExtra("demQuantité",quant.getText().toString());
                    intent.putExtra("etat1",true);
                    intent.putExtra("etat2",false);
                    intent.putExtra("etat3",View.VISIBLE);
                    intent.putExtra("etat4",View.INVISIBLE);
                    startActivity(intent);
                    finish();
                }   */
            }
        });

        //AutoTextComplete
        ArrayList<String> nd=bd.affiNE();
        ArrayAdapter<String> dep=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nd);
        employe.setAdapter(dep);

        ArrayList<String> em=bd.affiNDE();
        ArrayAdapter<String> emp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,em);
    //    depart.setAdapter(emp);

        ArrayList<String> nb=bd.affiNB();
        ArrayAdapter<String>nombes=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nb);
        bes.setAdapter(nombes);
        //FIN

        //DialogDatePicker
        final Calendar calendar;
        calendar=Calendar.getInstance();
        final int jour=calendar.get(Calendar.DAY_OF_MONTH);
        final int mois=calendar.get(Calendar.MONTH);
        final int annee=calendar.get(Calendar.YEAR);




     /*   radioButton_emp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    employe.setVisibility(View.VISIBLE);
                    employe.setEnabled(true);
                    depart.setVisibility(View.INVISIBLE);
                    depart.setEnabled(false);
                    Toast.makeText(getApplicationContext(), "Le bénéficiaire de la demande est un employé", Toast.LENGTH_SHORT).show();
                }
            }
        });
        radioButton_dep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    employe.setVisibility(View.INVISIBLE);
                    employe.setEnabled(false);
                    depart.setVisibility(View.VISIBLE);
                    depart.setEnabled(true);
                    Toast.makeText(getApplicationContext(), "Le bénéficiaire de la demande est un departement", Toast.LENGTH_SHORT).show();
                }

            }
        });   */


         date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                DatePickerDialog datePickerDialog=new DatePickerDialog(Demande.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        String jour=String.valueOf(dayOfMonth);
                        String mois=String.valueOf(month);

                        if (month<10){
                            mois="0"+mois;
                        }
                        if (dayOfMonth<10){
                            jour="0"+jour;
                        }
                        date.setText(jour+"/"+mois+"/"+year);
                       // date.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },annee,mois,jour);
                datePickerDialog.show();
            }
        });
        //fin diadlogueDatepiker

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (date.getText().toString().equals(""))
                {
                    date.requestFocus();
                    date.setError("Veuillez saisir la date SVP!");
                }
                else if (employe.getText().toString().equals("") )
                {
                    employe.requestFocus();
                    employe.setError("Veuillez saisir le nom de l'employé  SVP!");
                }
             /*   else if (depart.getText().toString().equals("") && radioButton_dep.isChecked())
                {
                    depart.requestFocus();
                    depart.setError("Veuillez saisir le nom du département SVP!");
                }  */
                else if (bes.getText().toString().equals(""))
                {
                    bes.requestFocus();
                   bes.setError("Veuillez saisir le libellé du besoin SVP!!");
                }
                else if (quant.getText().toString().equals(""))
                {
                    quant.requestFocus();
                    quant.setError("Veuillez saisir la quantité SVP!!");
                }
                else {
                    String a, b, c, departe;
                    int var1,var2;
                    if (! date.getText().toString().matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {

                        a = date.getText().toString().substring(0, 2);
                        b = date.getText().toString().substring(3, 5);
                        c = date.getText().toString().substring(6, 10);
                        date.setText(c + "-" + b + "-" + a);
                    }
                    if (! MyApplication.isFait()) {
                   //     if (radioButton_emp.isChecked()) {
                            var1 = Integer.parseInt(bd.selectEmpId(employe.getText().toString()));
                            departe = bd.DepartEmp(var1);
                            var2 = Integer.parseInt(bd.selectDep(departe));
                            bd.insertDemande(date.getText().toString(), var1, var2, "", true);

                    //    }

                   /*     if (radioButton_dep.isChecked()) {
                            String recup = bd.selectDep(depart.getText().toString());
                            bd.insertDemande1(date.getText().toString(), Integer.parseInt(recup), "", true,"en attente");
                        }  */
                    }
                    if (! bd.checkIfDemandeBesoinExist(bd.selectHeureDem(),bes.getText().toString())) {


                        int var3 = Integer.parseInt(bd.selectIdBes(bes.getText().toString()));
                        int var4 = Integer.parseInt(quant.getText().toString());


                        int dernierEnr = Integer.parseInt(bd.selectIdDem());


                        if (!profile.equals("USER")) {
                            bd.insertDemandeBesoin(dernierEnr, var3, var4,"VALIDE");
                            writeNewDemande(employe.getText().toString(), bd.selectDepartFromUser(employe.getText().toString()), bes.getText().toString(), date.getText().toString(), Integer.parseInt(quant.getText().toString()), bd.selectHeureDem(), "VALIDE");
                        }
                        else {
                            bd.insertDemandeBesoin(dernierEnr, var3, var4,"EN ATTENTE");
                            writeNewDemande(employe.getText().toString(), bd.selectDepartFromUser(employe.getText().toString()), bes.getText().toString(), date.getText().toString(), Integer.parseInt(quant.getText().toString()), bd.selectHeureDem(), "EN ATTENTE");

                        }
                        updateConnectivity(MyApplication.getmAuth().getCurrentUser().getEmail());
                        bes.setText("");
                        quant.setText("");
                        if (date.getText().toString().matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
                            a = date.getText().toString().substring(0, 4);
                            b = date.getText().toString().substring(5, 7);
                            c = date.getText().toString().substring(8, 10);
                            date.setText(c + "/" + b + "/" + a);
                        }
                        Toast.makeText(getBaseContext(), "Demande enregistrée avec succès !!", Toast.LENGTH_LONG).show();

                        MyApplication.setFait(true);
                    }
                   else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Demande.this,0x00000005 );
                        builder.setMessage("Ce besoin a été déjà enregistré");
                        builder.setTitle("Echec");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create();
                        builder.show();
                    }
                    if (date.getText().toString().matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
                        a = date.getText().toString().substring(0, 4);
                        b = date.getText().toString().substring(5, 7);
                        c = date.getText().toString().substring(8, 10);
                        date.setText(c + "/" + b + "/" + a);
                    }

                }

            }
        });

        Button enregistre=(Button)findViewById(R.id.enregistre);
        enregistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date.getText().toString().equals(""))
                {
                    date.requestFocus();
                    date.setError("Veuillez saisir la date SVP!");
                }
                else if (employe.getText().toString().equals("") )
                {
                    employe.requestFocus();
                    employe.setError("Veuillez saisir le nom de l'employé SVP!");
                }
            /*    else if (depart.getText().toString().equals("") && radioButton_dep.isChecked())
                {
                    depart.requestFocus();
                    depart.setError("Veuillez saisir le nom du département SVP!");
                }   */
                else if (bes.getText().toString().equals(""))
                {
                    bes.requestFocus();
                    bes.setError("Veuillez saisir le libellé du besoin SVP!!");
                }
                else if (quant.getText().toString().equals(""))
                {
                    quant.requestFocus();
                    quant.setError("Veuillez saisir la quantité SVP!!");
                }
                else {

                    String a, b, c;
                    if (! date.getText().toString().matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {

                        a = date.getText().toString().substring(0, 2);
                        b = date.getText().toString().substring(3, 5);
                        c = date.getText().toString().substring(6, 10);
                        date.setText(c + "-" + b + "-" + a);
                    }
                    if (!MyApplication.isFait()) {
                   /*     String a, b, c;
                        a = date.getText().toString().substring(0, 2);
                        b = date.getText().toString().substring(3, 5);
                        c = date.getText().toString().substring(6, 10);
                        date.setText(c + "-" + b + "-" + a);  */
                        int var1, var2;
                        var1 = Integer.parseInt(bd.selectEmpId(employe.getText().toString()));
                        String departe = bd.DepartEmp(var1);
                        var2 = Integer.parseInt(bd.selectDep(departe));

                        bd.insertDemande(date.getText().toString(), var1, var2, "", true);
                    }
                    if (! bd.checkIfDemandeBesoinExist(bd.selectHeureDem(),bes.getText().toString())){


                    int var3 = Integer.parseInt(bd.selectIdBes(bes.getText().toString()));
                    int var4 = Integer.parseInt(quant.getText().toString());
                    if (!MyApplication.isFait()) {
                    //    if (radioButton_emp.isChecked()) {
                            //Toast.makeText(getBaseContext(),employe.getText().toString()+"coucou",Toast.LENGTH_LONG).show();


                     //   }

                 /*       if (radioButton_dep.isChecked()) {
                            String recup = bd.selectDep(depart.getText().toString());
                            bd.insertDemande1(date.getText().toString(), Integer.parseInt(recup), "", true,"en attente");
                        }  */
                    }
                    int dernierEnr = Integer.parseInt(bd.selectIdDem());
                    if (MyApplication.isFait()) {
                       /* String a, b, c, d;
                        a = date.getText().toString().substring(0, 2);
                        b = date.getText().toString().substring(3, 5);
                        c = date.getText().toString().substring(6, 10);
                        date.setText(c + "-" + b + "-" + a);  */
                    }

                    if (!profile.equals("USER")) {
                        bd.insertDemandeBesoin(dernierEnr, var3, var4,"VALIDE");
                        writeNewDemande(employe.getText().toString(), bd.selectDepartFromUser(employe.getText().toString()), bes.getText().toString(), date.getText().toString(), Integer.parseInt(quant.getText().toString()), bd.selectHeureDem(), "VALIDE");
                    }
                    else {
                        bd.insertDemandeBesoin(dernierEnr, var3, var4,"EN ATTENTE");
                        writeNewDemande(employe.getText().toString(), bd.selectDepartFromUser(employe.getText().toString()), bes.getText().toString(), date.getText().toString(), Integer.parseInt(quant.getText().toString()), bd.selectHeureDem(), "EN ATTENTE");

                    }
                    updateConnectivity(MyApplication.getmAuth().getCurrentUser().getEmail());
                    bd.close();
                    Toast.makeText(getBaseContext(), "Sortie enregistrée avec succès !!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Demande.this, Acceuil.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getBaseContext(), "Demande enregistrée avec succès !!", Toast.LENGTH_LONG).show();

                    MyApplication.setFait(false);
                   }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Demande.this,0x00000005 );
                    builder.setMessage("Ce besoin a été déjà enregistré");
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

        Button quitter=(Button)findViewById(R.id.dquitter);
        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Demande.this,Acceuil.class);
                startActivity(intent);
                finish();
                MyApplication.setFait(false);
            }
        });

    }
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View v = getCurrentFocus();
        if (v != null)
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Demande.this,0x00000005 );
        builder.setMessage("Voulez-vous abandonner l'enregistrement?");
        builder.setTitle("Attention!");
        builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Demande.this,Acceuil.class);
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
    public void writeNewDemande(String nomEmp,String libDpe, String libBes,String dateDem, int qte, String heureDem,String etat){
        BaseDeDonne bd=new BaseDeDonne(getApplicationContext());
        String now=bd.selectCurrentDate();
        String code=nomEmp+"-"+libDpe+"-"+libBes+"-"+now;
        writeNewHeureDemande(MyApplication.getmAuth().getCurrentUser().getEmail(),now,libBes,qte);
        String cricri="";
        String cris="";
        String cristi="";
        String crissi="";
        if (nomEmp.contains(" ")){
            cricri=nomEmp.replace(" ","-");
            code=cricri+"-"+libDpe+"-"+libBes+"-"+now;
        }
  /*      if (dateDem.contains("/")){
            cris=dateDem.replace("/","-");
            code=nomEmp+"-"+libDpe+"-"+libBes+"-"+cris;
        }   */
        if (nomEmp.contains("'") || libDpe.contains("'") || libBes.contains("'")){
            code=code.replace("'","-");
        }
        if (libDpe.contains(" ") || libBes.contains(" ")){
            code=code.replace(" ","-");
        }

        DemandeC cat=new DemandeC(nomEmp,libDpe,libBes,dateDem,qte,heureDem,etat);
        mDatabase.child("Demande").child(code).setValue(cat);
    }
    public void writeNewHeureDemande(String mail,String heure,String besoin,int qte){
        if (profile.equals("USER")){
        BaseDeDonne bd=new BaseDeDonne(getApplicationContext());
        String nom,pren,code,hour;
        nom=bd.selectEmpNomFromMail(mail);
        pren=bd.selectEmpPrenomFromMail(mail);
        hour=bd.selectHeureDem();
        code=nom+"-"+pren+"-"+besoin+"-"+hour;
        HeureDemandeC donnee=new HeureDemandeC(nom,pren,besoin,heure,hour,qte);

            mDatabase.child("HeureDemande").child(code).setValue(donnee);
            Log.i("fait",besoin+"-"+pren);
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        String profile=bd.retrieveUserProfile(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        if (profile.equals("USER")){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_affiche2, menu);
        return true;
        }
        else {
            return false;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.deconnexion:
                MyApplication.getmAuth().signOut();
                finish();
                startActivity(new Intent(this, Authentification.class));
                return true;
            case R.id.password:
                final AlertDialog.Builder builder = new AlertDialog.Builder(Demande.this, 0x00000005);
                final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_input1, null);
                final EditText input1 = (EditText) view.findViewById(R.id.input1);
                final EditText input2 = (EditText) view.findViewById(R.id.input2);
                final EditText input3 = (EditText) view.findViewById(R.id.input3);


                builder.setView(view);
                builder.setTitle("Changer de mot de passe");
                builder.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ancien = input1.getText().toString();
                        nouveau = input2.getText().toString();
                        confirmation = input3.getText().toString();
                        dialog.dismiss();
                        if (nouveau.equals(confirmation)) {
                            AuthCredential credential = EmailAuthProvider.getCredential(MyApplication.getmAuth().getCurrentUser().getEmail(), ancien);
                            MyApplication.getmAuth().getCurrentUser().reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        MyApplication.getmAuth().getCurrentUser().updatePassword(nouveau).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.i("Résultat", "success");
                                                    Snackbar.make(getCurrentFocus(), "Mot de passe changé avec succès!!", Snackbar.LENGTH_LONG)
                                                            .setAction("Action", null).show();
                                                } else {
                                                    Log.i("Résultat", "failed");
                                                }

                                            }
                                        });
                                    } else {
                                        Log.i("résultat", "not good");
                                    }
                                }
                            });
                        } else {
                            Snackbar.make(view, "Changement de mot passe a échoué.\nVeuillez recommencer!!!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }


                    }
                });
                builder.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


                builder.create();
                builder.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
