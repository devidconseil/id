package com.example.hp.madose;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Demande extends AppCompatActivity {



    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande);
        final BaseDeDonne bd=new BaseDeDonne(this);

        final EditText date=(EditText)findViewById(R.id.dateDemande);
        final Button ajouter=(Button)findViewById(R.id.ajout);
        final AutoCompleteTextView employe=(AutoCompleteTextView)findViewById(R.id.autoCompEmp);
        final AutoCompleteTextView depart=(AutoCompleteTextView)findViewById(R.id.autoCompDep);
        final AutoCompleteTextView bes=(AutoCompleteTextView)findViewById(R.id.autoCompBes);
        final EditText quant=(EditText)findViewById(R.id.editqt);
        final RadioButton radioButton_emp= findViewById(R.id.radioButton_emp);
        final RadioButton radioButton_dep= findViewById(R.id.radioButton_dep);
        final RadioGroup radioGroup= findViewById(R.id.radio_group);
        mDatabase= FirebaseDatabase.getInstance().getReference();


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
                        bd.insertEmp(user.getNomEmp(),user.getPrenEmp(),user.getMailEmp(),user.getTelEmp(),s,user.getProEmp());

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
            depart.setText(intent.getStringExtra("demDe"));
            bes.setText(intent.getStringExtra("demB"));
            quant.setText(intent.getStringExtra("demQ"));
            radioButton_dep.setChecked(intent.getBooleanExtra("etat1",radioButton_dep.isChecked()));
            radioButton_emp.setChecked(intent.getBooleanExtra("etat2",radioButton_emp.isChecked()));
            employe.setVisibility(intent.getIntExtra("etat3",employe.getVisibility()));
            depart.setVisibility(intent.getIntExtra("etat4",depart.getVisibility()));
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
            else
            {
                date.requestFocus();
            }

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
        depart.setOnClickListener(new View.OnClickListener() {
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
        });

        bes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioButton_emp.isChecked()){
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
                }
                else if (radioButton_dep.isChecked()){
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
                }
            }
        });

        //AutoTextComplete
        /*ArrayList<String> nd=bd.affiNE();
        ArrayAdapter<String> dep=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nd);
        employe.setAdapter(dep);

        ArrayList<String> em=bd.affiNDE();
        ArrayAdapter<String> emp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,em);
        depart.setAdapter(emp);

        ArrayList<String> nb=bd.affiNB();
        ArrayAdapter<String>nombes=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nb);
        bes.setAdapter(nombes);*/
        //FIN



        //DialogDatePicker
        final Calendar calendar;
        calendar=Calendar.getInstance();
        final int jour=calendar.get(Calendar.DAY_OF_MONTH);
        final int mois=calendar.get(Calendar.MONTH);
        final int annee=calendar.get(Calendar.YEAR);




        radioButton_emp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        });


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
                else if (employe.getText().toString().equals("") && radioButton_emp.isChecked())
                {
                    employe.requestFocus();
                    employe.setError("Veuillez saisir le nom de l'employé ou du département SVP!");
                }
                else if (depart.getText().toString().equals("") && radioButton_dep.isChecked() )
                {
                    depart.requestFocus();
                    depart.setError("Veuillez saisir le nom de l'employé ou du département SVP!");
                }
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
                    if (! date.getText().toString().matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {

                        a = date.getText().toString().substring(0, 2);
                        b = date.getText().toString().substring(3, 5);
                        c = date.getText().toString().substring(6, 10);
                        date.setText(c + "-" + b + "-" + a);
                    }

                    if (! bd.checkIfDemandeBesoinExist(employe.getText().toString(),bd.selectHeureDem(),bes.getText().toString(),date.getText().toString())) {


                        int var1, var2;

                        int var3 = Integer.parseInt(bd.selectIdBes(bes.getText().toString()));
                        int var4 = Integer.parseInt(quant.getText().toString());


                        if (! MyApplication.isFait()) {
                            if (radioButton_emp.isChecked()) {
                                var1 = Integer.parseInt(bd.selectEmpId(employe.getText().toString()));
                                departe = bd.DepartEmp(var1);
                                var2 = Integer.parseInt(bd.selectDep(departe));
                                bd.insertDemande(date.getText().toString(), var1, var2, "", true);
                            }

                            if (radioButton_dep.isChecked()) {
                                String recup = bd.selectDep(depart.getText().toString());
                                bd.insertDemande1(date.getText().toString(), Integer.parseInt(recup), "", true);
                            }
                        }


                        int dernierEnr = Integer.parseInt(bd.selectIdDem());
                        bd.insertDemandeBesoin(dernierEnr, var3, var4);

                        writeNewDemande(employe.getText().toString(), depart.getText().toString(), bes.getText().toString(), date.getText().toString(), Integer.parseInt(quant.getText().toString()), bd.selectHeureDem());
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
                else if (employe.getText().toString().equals("") && radioButton_emp.isChecked())
                {
                    employe.requestFocus();
                    employe.setError("Veuillez saisir le nom de l'employé ou du département SVP!");
                }
                else if (depart.getText().toString().equals("") && radioButton_dep.isChecked() )
                {
                    depart.requestFocus();
                    depart.setError("Veuillez saisir le nom de l'employé ou du département SVP!");
                }
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

                    if (! bd.checkIfDemandeBesoinExist(employe.getText().toString(),bd.selectHeureDem(),bes.getText().toString(),date.getText().toString())){
                    if (!MyApplication.isFait()) {
                   /*     String a, b, c;
                        a = date.getText().toString().substring(0, 2);
                        b = date.getText().toString().substring(3, 5);
                        c = date.getText().toString().substring(6, 10);
                        date.setText(c + "-" + b + "-" + a);  */
                    }
                    int var1, var2;
                    int var3 = Integer.parseInt(bd.selectIdBes(bes.getText().toString()));
                    int var4 = Integer.parseInt(quant.getText().toString());
                    if (!MyApplication.isFait()) {
                        if (radioButton_emp.isChecked()) {
                            //Toast.makeText(getBaseContext(),employe.getText().toString()+"coucou",Toast.LENGTH_LONG).show();
                            var1 = Integer.parseInt(bd.selectEmpId(employe.getText().toString()));
                            String departe = bd.DepartEmp(var1);
                            var2 = Integer.parseInt(bd.selectDep(departe));
                            bd.insertDemande(date.getText().toString(), var1, var2, "", true);
                        }

                        if (radioButton_dep.isChecked()) {
                            String recup = bd.selectDep(depart.getText().toString());
                            bd.insertDemande1(date.getText().toString(), Integer.parseInt(recup), "", true);
                        }
                    }
                    int dernierEnr = Integer.parseInt(bd.selectIdDem());
                    if (MyApplication.isFait()) {
                       /* String a, b, c, d;
                        a = date.getText().toString().substring(0, 2);
                        b = date.getText().toString().substring(3, 5);
                        c = date.getText().toString().substring(6, 10);
                        date.setText(c + "-" + b + "-" + a);  */
                    }
                    bd.insertDemandeBesoin(dernierEnr, var3, var4);
                    writeNewDemande(employe.getText().toString(), depart.getText().toString(), bes.getText().toString(), date.getText().toString(), Integer.parseInt(quant.getText().toString()), bd.selectHeureDem());
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
    public void writeNewDemande(String nomEmp,String libDpe, String libBes,String dateDem, int qte, String heureDem){
        BaseDeDonne bd=new BaseDeDonne(getApplicationContext());
        String now=bd.selectCurrentDate();
        String code=nomEmp+"-"+libDpe+"-"+libBes+"-"+now;
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

        DemandeC cat=new DemandeC(nomEmp,libDpe,libBes,dateDem,qte,heureDem);
        mDatabase.child("Demande").child(code).setValue(cat);
    }
}
