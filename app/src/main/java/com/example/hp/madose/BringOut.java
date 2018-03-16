package com.example.hp.madose;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class BringOut extends AppCompatActivity {

    boolean fait=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bring_out);

        final BaseDeDonne bd=new BaseDeDonne(this);
        final Button passage=(Button)findViewById(R.id.sortiVal);
        final AutoCompleteTextView employe=(AutoCompleteTextView)findViewById(R.id.autoEmp);
        final AutoCompleteTextView departement= findViewById(R.id.autoDep);
        final AutoCompleteTextView besoin=(AutoCompleteTextView)findViewById(R.id.autoBesoin);
        final EditText qut=(EditText)findViewById(R.id.sortieQt);
        final AutoCompleteTextView marq=(AutoCompleteTextView)findViewById(R.id.autoCompMark);
        final AutoCompleteTextView autr=(AutoCompleteTextView)findViewById(R.id.autoCompAutre);
        //final Spinner spinner=(Spinner)findViewById(R.id.spinner);
        final AutoCompleteTextView demande=(AutoCompleteTextView)findViewById(R.id.autoDemande);
        final RadioButton radioButton_emp= findViewById(R.id.radioButtonEmp);
        final RadioButton radioButton_dep= findViewById(R.id.radioButtonDep);

        final EditText date=(EditText)findViewById(R.id.editDate);

        //AutoTextComplete
       /* ArrayList<String> nd=bd.affiNE();
        ArrayAdapter<String> dep=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nd);
        employe.setAdapter(dep);

        ArrayList<String> nd1=bd.affiNDE();
        ArrayAdapter<String> dep1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nd1);
        departement.setAdapter(dep1);

        ArrayList<String> nb=bd.affiNB();
        ArrayAdapter<String>nombes=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nb);
        besoin.setAdapter(nombes);*/

        ArrayList<String> m1=bd.affiMarque();
        ArrayAdapter<String>mar=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,m1);
        marq.setAdapter(mar);




        Intent intent=getIntent();
        if (intent != null)
        {

            date.setText(intent.getStringExtra("bringD"));
            employe.setText(intent.getStringExtra("bringE"));
            departement.setText(intent.getStringExtra("bringDe"));
            besoin.setText(intent.getStringExtra("bringB"));
            demande.setText(intent.getStringExtra("bringDem"));
            marq.setText(intent.getStringExtra("bringM"));
            qut.setText(intent.getStringExtra("bringQ"));
            autr.setText(intent.getStringExtra("bringA"));
            radioButton_dep.setChecked(intent.getBooleanExtra("etat1",radioButton_dep.isChecked()));
            radioButton_emp.setChecked(intent.getBooleanExtra("etat2",radioButton_emp.isChecked()));
            employe.setVisibility(intent.getIntExtra("etat3",employe.getVisibility()));
            departement.setVisibility(intent.getIntExtra("etat4",departement.getVisibility()));
            if (intent.getStringExtra("code").equals("listeD")) {

            }
            else if (intent.getStringExtra("code").equals("listeU"))
            {
                employe.requestFocus();

            }
            else if (intent.getStringExtra("code").equals("listeB"))
            {
                besoin.requestFocus();
            }
            else if (intent.getStringExtra("code").equals("listeDa"))
            {
                demande.requestFocus();

            }
            else
            {
                date.requestFocus();

            }
        }



        employe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BringOut.this,ListeUtilisateur.class);
                intent.putExtra("bringDate",date.getText().toString());
                intent.putExtra("code","sortie");
                intent.putExtra("bringDemande",demande.getText().toString());
                intent.putExtra("bringDepartement",departement.getText().toString());
                intent.putExtra("bringBesoin",besoin.getText().toString());
                intent.putExtra("bringMarque",marq.getText().toString());
                intent.putExtra("bringQuantité",qut.getText().toString());
                intent.putExtra("bringAutre",autr.getText().toString());
                intent.putExtra("etat1",false);
                intent.putExtra("etat2",true);
                intent.putExtra("etat3",View.VISIBLE);
                intent.putExtra("etat4",View.INVISIBLE);
                startActivity(intent);
                finish();
            }
        });
        departement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent = new Intent(BringOut.this, Listedepartement.class);
                intent.putExtra("bringDate", date.getText().toString());
                intent.putExtra("code", "sortie1");
                intent.putExtra("bringDemande", demande.getText().toString());
                intent.putExtra("bringEmp", employe.getText().toString());
                intent.putExtra("bringBesoin", besoin.getText().toString());
                intent.putExtra("bringMarque", marq.getText().toString());
                intent.putExtra("bringQuantité", qut.getText().toString());
                intent.putExtra("bringAutre", autr.getText().toString());
                intent.putExtra("etat1", true);
                intent.putExtra("etat2", false);
                intent.putExtra("etat3", View.INVISIBLE);
                intent.putExtra("etat4", View.VISIBLE);
                startActivity(intent);
                finish();
            }
        });
        besoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioButton_emp.isChecked()){
                Intent intent=new Intent(BringOut.this,ListeBesoin.class);
                intent.putExtra("bringDate",date.getText().toString());
                intent.putExtra("code","besoinEmploye");
                intent.putExtra("bringDemande",demande.getText().toString());
                intent.putExtra("bringEmp",employe.getText().toString());
                intent.putExtra("bringBesoin",departement.getText().toString());
                intent.putExtra("bringMarque",marq.getText().toString());
                intent.putExtra("bringQuantité",qut.getText().toString());
                intent.putExtra("bringAutre",autr.getText().toString());
                intent.putExtra("etat1",true);
                intent.putExtra("etat2",false);
                intent.putExtra("etat3",View.INVISIBLE);
                intent.putExtra("etat4",View.VISIBLE);
                startActivity(intent);
                finish();
                }
                else if (radioButton_dep.isChecked()){
                    Intent intent=new Intent(BringOut.this,ListeBesoin.class);
                    intent.putExtra("bringDate",date.getText().toString());
                    intent.putExtra("code","besoinDepartement");
                    intent.putExtra("bringDemande",demande.getText().toString());
                    intent.putExtra("bringEmp",employe.getText().toString());
                    intent.putExtra("bringDepartement",departement.getText().toString());
                    intent.putExtra("bringMarque",marq.getText().toString());
                    intent.putExtra("bringQuantité",qut.getText().toString());
                    intent.putExtra("bringAutre",autr.getText().toString());
                    intent.putExtra("etat1",false);
                    intent.putExtra("etat2",true);
                    intent.putExtra("etat3",View.VISIBLE);
                    intent.putExtra("etat4",View.INVISIBLE);
                    startActivity(intent);
                    finish();
                }
            }
        });






        /*ArrayList<String> au=bd.affiAutre();
        ArrayAdapter<String>aut=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,au);
        autr.setAdapter(aut);*/
        //finAutoTextComplete

        //DialogDatePicker
        final Calendar calendar;
        calendar=Calendar.getInstance();
        final int jour=calendar.get(Calendar.DAY_OF_MONTH);
        final int mois=calendar.get(Calendar.MONTH);
        final int annee=calendar.get(Calendar.YEAR);


        radioButton_dep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    employe.setVisibility(View.INVISIBLE);
                    employe.setEnabled(false);
                    departement.setVisibility(View.VISIBLE);
                    departement.setEnabled(true);
                    Toast.makeText(getApplicationContext(), "Le bénéficiaire de la demande est un departement", Toast.LENGTH_SHORT).show();
                }
            }
        });
        radioButton_emp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    employe.setVisibility(View.VISIBLE);
                    employe.setEnabled(true);
                    departement.setVisibility(View.INVISIBLE);
                    departement.setEnabled(false);
                    Toast.makeText(getApplicationContext(), "Le bénéficiaire de la demande est un employé", Toast.LENGTH_SHORT).show();
                }
            }
        });
  /*      radioButton_dep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employe.setVisibility(View.INVISIBLE);
                employe.setEnabled(false);
                departement.setVisibility(View.VISIBLE);
                departement.setEnabled(true);
                Toast.makeText(getApplicationContext(),"Le bénéficiaire de la demande est un departement",Toast.LENGTH_SHORT).show();
            }
        });
        radioButton_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employe.setVisibility(View.VISIBLE);
                employe.setEnabled(true);
                departement.setVisibility(View.INVISIBLE);
                departement.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Le bénéficiaire de la demande est un employé",Toast.LENGTH_SHORT).show();
            }
        });  */

       /* date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (b){

                    DatePickerDialog datePickerDialog=new DatePickerDialog(BringOut.this, new DatePickerDialog.OnDateSetListener() {
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

                    date.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            date.setError(null);

                        }
                    });


                }


            }
        });*/
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                DatePickerDialog datePickerDialog=new DatePickerDialog(BringOut.this, new DatePickerDialog.OnDateSetListener() {
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
        demande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> au = new ArrayList<>();
                String var;
                if (employe.getVisibility() == View.VISIBLE) {
                    if (employe.getText().toString().equals("")) {
                        employe.setError("Veuillez saisir le nom du departement ou de l'employé SVP!!");
                    } else {
                        var = bd.selectIdEmp(employe.getText().toString());
                        if (var == null)
                        {
                            Toast.makeText(getBaseContext(),"Ce employé n'a pas éffectué de demande",Toast.LENGTH_LONG).show();
                        }
                        //Toast.makeText(getApplicationContext(), var, Toast.LENGTH_LONG);
                        // Log.i("CONSTAT",var);
                        else if (var != "") {
                            MyApplication.setEmploye(employe.getText().toString());
                            au = bd.affiNumDem(Integer.parseInt(var));
                            Intent intent = new Intent(BringOut.this, ListeDate.class);
                            intent.putExtra("bringDate", date.getText().toString());
                            intent.putExtra("bringO", "sortie2");
                            intent.putExtra("bringEmploye", employe.getText().toString());
                            intent.putExtra("bringDepartement", departement.getText().toString());
                            intent.putExtra("bringBesoin", besoin.getText().toString());
                            intent.putExtra("bringMarque", marq.getText().toString());
                            intent.putExtra("bringQuantité", qut.getText().toString());
                            intent.putExtra("bringAutre", autr.getText().toString());
                            intent.putExtra("bringRadDep", false);
                            intent.putExtra("bringRadEmp", true);
                            intent.putExtra("bringEmpVis", View.VISIBLE);
                            intent.putExtra("bringDepVis", View.INVISIBLE);
                            startActivity(intent);
                            finish();
                        }
                    }

                }
                if (departement.getVisibility() == View.VISIBLE) {
                    if (departement.getText().toString().equals("")) {
                        departement.setError("Veuillez saisir le nom du departement ou de l'employé SVP!!");
                    } else {
                        var = bd.selectDep(departement.getText().toString());
                        if (var == null)
                        {
                            Toast.makeText(getBaseContext(),"Ce departement n'a pas effectué de demande",Toast.LENGTH_LONG).show();
                        }
                        else if (var != "") {
                            au = bd.affiNumDem1(Integer.parseInt(var));
                            Intent intent = new Intent(BringOut.this, ListeDate.class);
                            intent.putExtra("bringDate", date.getText().toString());
                            intent.putExtra("bringO", "sortie1");
                            intent.putExtra("bringDepartement", departement.getText().toString());
                            intent.putExtra("bringEmploye", employe.getText().toString());
                            intent.putExtra("bringBesoin", besoin.getText().toString());
                            intent.putExtra("bringMarque", marq.getText().toString());
                            intent.putExtra("bringQuantité", qut.getText().toString());
                            intent.putExtra("bringAutre", autr.getText().toString());
                            intent.putExtra("bringRadDep", false);
                            intent.putExtra("bringRadEmp", true);
                            intent.putExtra("bringEmpVis", View.VISIBLE);
                            intent.putExtra("bringDepVis", View.INVISIBLE);
                            startActivity(intent);
                            finish();
                        }
                    }
                    }
                    ArrayAdapter<String> aut = new ArrayAdapter<String>(BringOut.this, android.R.layout.simple_list_item_1, au);
                    demande.setAdapter(aut);
                }

        });
// Avec le listener Focus
    /*  demande.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               ArrayList<String> au=new ArrayList<>();
               String var;
               if (employe.getVisibility()==View.VISIBLE) {
                    var = bd.selectIdEmp(employe.getText().toString());
                    Toast.makeText(getApplicationContext(),var,Toast.LENGTH_LONG);
                  // Log.i("CONSTAT",var);
                    if (var !=""){
                        MyApplication.setEmploye(employe.getText().toString());
                        au = bd.affiNumDem(Integer.parseInt(var));
                        Intent intent=new Intent(BringOut.this,ListeDate.class);
                        intent.putExtra("bringDate",date.getText().toString());
                        intent.putExtra("bringO","sortie2");
                        intent.putExtra("bringEmploye",employe.getText().toString());
                        intent.putExtra("bringDepartement",departement.getText().toString());
                        intent.putExtra("bringBesoin",besoin.getText().toString());
                        intent.putExtra("bringMarque",marq.getText().toString());
                        intent.putExtra("bringQuantité",qut.getText().toString());
                        intent.putExtra("bringAutre",autr.getText().toString());
                        intent.putExtra("bringRadDep",false);
                        intent.putExtra("bringRadEmp",true);
                        intent.putExtra("bringEmpVis",View.VISIBLE);
                        intent.putExtra("bringDepVis",View.INVISIBLE);
                        startActivity(intent);
                    }

               }
               if (departement.getVisibility()==View.VISIBLE) {
                   var = bd.selectDep(departement.getText().toString());
                   if (var !=""){
                       au = bd.affiNumDem1(Integer.parseInt(var));
                       Intent intent=new Intent(BringOut.this,ListeDate.class);
                       intent.putExtra("bringDate",date.getText().toString());
                       intent.putExtra("bringO","sortie1");
                       intent.putExtra("bringDemande",demande.getText().toString());
                       intent.putExtra("bringEmp",employe.getText().toString());
                       intent.putExtra("bringBesoin",besoin.getText().toString());
                       intent.putExtra("bringMarque",marq.getText().toString());
                       intent.putExtra("bringQuantité",qut.getText().toString());
                       intent.putExtra("bringAutre",autr.getText().toString());
                       intent.putExtra("etat1",true);
                       intent.putExtra("etat2",false);
                       intent.putExtra("etat3",View.INVISIBLE);
                       intent.putExtra("etat4",View.VISIBLE);
                       startActivity(intent);
                   }

               }
               ArrayAdapter<String>aut=new ArrayAdapter<String>(BringOut.this,android.R.layout.simple_list_item_1,au);
               demande.setAdapter(aut);
           }
       });  */


        passage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(date.getText().toString().equals(""))
                {
                    date.setError("Veuillez saisir la date de sortie SVP!!");
                    date.requestFocus();
                }
                else if (demande.getText().toString().equals(""))
                {
                    demande.setError("Veuillez saisir la date de la demande SVP!!");
                    demande.requestFocus();
                }
                else if (besoin.getText().toString().equals(""))
                {
                    besoin.setError("Veuillez saisir le nom du besoin SVP!!");
                    besoin.requestFocus();
                }
                else if (qut.getText().toString().equals(""))
                {
                    qut.setError("Veuillez saisir le prix unitaire SVP!!");
                    qut.requestFocus();
                }
               else {


                    String num = new String();
                    if (employe.isEnabled()) {
                        num = bd.selectNumeDem(demande.getText().toString(), employe.getText().toString());
                    }
                    if (departement.isEnabled()) {
                        num = bd.selectNumeDem1(demande.getText().toString(), departement.getText().toString());
                    }
                    int dernierEnr;
                    // bd.insertSortie(date.getText().toString(),num);
                    // bd.close();
                    String a, b, c;
                    a = date.getText().toString().substring(0, 2);
                    b = date.getText().toString().substring(3, 5);
                    c = date.getText().toString().substring(6, 10);
                    date.setText(c + "-" + b + "-" + a);
                    bd.insertSortie(date.getText().toString(), num,"",MyApplication.mAuth.getCurrentUser().getEmail(),true);
                    dernierEnr = Integer.parseInt(bd.selectIdSortie());
                    //NumSor` INTEGER, `NumBes` INTEGER, `qte` INTEGER NOT NULL, `marqueBes` TEXT, `Autre précision`
                    int var = Integer.parseInt(bd.selectIdBes(besoin.getText().toString()));
                    int var1 = Integer.parseInt(qut.getText().toString());
                    bd.insertSortieBesoin(dernierEnr, var, var1, marq.getText().toString(), autr.getText().toString());
                    writeNewSortie(besoin.getText().toString(),marq.getText().toString(),autr.getText().toString(),demande.getText().toString(),employe.getText().toString(),date.getText().toString(),departement.getText().toString(),bd.selectHeureSor(),MyApplication.getmAuth().getCurrentUser().getEmail(),var1);
                    //update debut
                    int var2 = Integer.parseInt(bd.selectStockBes(besoin.getText().toString()));
                    int var3 = var2 - var1;
                    bd.upDate(var3, besoin.getText().toString());
                    //update fin

                    besoin.setText("");
                    qut.setText("");
                    marq.setText("");
                    autr.setText("");
                    bd.close();
                    a=date.getText().toString().substring(0,4);
                    b=date.getText().toString().substring(5,7);
                    c=date.getText().toString().substring(8,10);
                    date.setText(c+"/"+b+"/"+a);

                    Toast.makeText(getBaseContext(), "Sortie enregistrée avec succès !!", Toast.LENGTH_LONG).show();
                    fait = true;
                }
            }
        });
        Button passa=(Button)findViewById(R.id.enreg);
        passa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(date.getText().toString().equals(""))
                {
                    date.setError("Veuillez saisir la date de sortie SVP!!");
                    date.requestFocus();
                }
                else if (demande.getText().toString().equals(""))
                {
                    demande.setError("Veuillez saisir la date de la demande SVP!!");
                    demande.requestFocus();
                }
                else if (besoin.getText().toString().equals(""))
                {
                    besoin.setError("Veuillez saisir le nom du besoin SVP!!");
                    besoin.requestFocus();
                }
                else if (qut.getText().toString().equals(""))
                {
                    qut.setError("Veuillez saisir le prix unitaire SVP!!");
                    qut.requestFocus();
                }
                else {
                    String num = new String();
                    if (employe.isEnabled()) {
                        num = bd.selectNumeDem(demande.getText().toString(), employe.getText().toString());
                    }
                    if (departement.isEnabled()) {
                        num = bd.selectNumeDem1(demande.getText().toString(), departement.getText().toString());
                    }
                    int dernierEnr;
                    // bd.insertSortie(date.getText().toString(),num);
                    // bd.close();

                    if (fait == false) {
                        String a, b, c;
                        a = date.getText().toString().substring(0, 2);
                        b = date.getText().toString().substring(3, 5);
                        c = date.getText().toString().substring(6, 10);
                        date.setText(c + "-" + b + "-" + a);
                        bd.insertSortie(date.getText().toString(), num,"",MyApplication.getmAuth().getCurrentUser().getEmail(),true);

                    }


                    dernierEnr = Integer.parseInt(bd.selectIdSortie());
                    //NumSor` INTEGER, `NumBes` INTEGER, `qte` INTEGER NOT NULL, `marqueBes` TEXT, `Autre précision`
                    int var = Integer.parseInt(bd.selectIdBes(besoin.getText().toString()));
                    int var1 = Integer.parseInt(qut.getText().toString());
                    bd.insertSortieBesoin(dernierEnr, var, var1, marq.getText().toString(), autr.getText().toString());
                    writeNewSortie(besoin.getText().toString(),marq.getText().toString(),autr.getText().toString(),demande.getText().toString(),employe.getText().toString(),date.getText().toString(),departement.getText().toString(),bd.selectHeureSor(),MyApplication.getmAuth().getCurrentUser().getEmail(),var1);

                    //update debut
                    int var2 = Integer.parseInt(bd.selectStockBes(besoin.getText().toString()));
                    int var3 = var2 - var1;
                    bd.upDate(var3, besoin.getText().toString());
                    //update fin

                    bd.close();
                    Toast.makeText(getBaseContext(), "Sortie enregistrée avec succès !!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(BringOut.this, Acceuil.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        Button quitter=(Button)findViewById(R.id.quitte);
        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BringOut.this,Acceuil.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View v = getCurrentFocus();
        if (v != null)
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
    public void writeNewSortie(String libBes, String marqBes, String autreP, String dateDem, String nomEmp, String date, String libDep, String heureSor, String validationUser, int qte){
        BaseDeDonne bd=new BaseDeDonne(getApplicationContext());
        String now=bd.selectCurrentDate();
        String code=nomEmp+"-"+libDep+"-"+libBes+"-"+now;
        String cricri="";

        if (nomEmp.contains(" ")){
            cricri=nomEmp.replace(" ","-");
            code=cricri+"-"+libDep+"-"+libBes+"-"+now;
        }
  /*      if (dateDem.contains("/")){
            cris=dateDem.replace("/","-");
            code=nomEmp+"-"+libDpe+"-"+libBes+"-"+cris;
        }   */
        if (nomEmp.contains("'") || libDep.contains("'") || libBes.contains("'")){
            code=code.replace("'","-");
        }
        if (libDep.contains(" ") || libBes.contains(" ")){
            code=code.replace(" ","-");
        }

        Stock2 cat=new Stock2(libBes,marqBes,autreP,dateDem,nomEmp,date,libDep,heureSor,validationUser,qte);
        MyApplication.getmDatabase().child("Sorties").child(code).setValue(cat);
    }
}
