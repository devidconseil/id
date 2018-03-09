package com.example.hp.madose;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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
        ArrayList<String> nd=bd.affiNE();
        ArrayAdapter<String> dep=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nd);
        employe.setAdapter(dep);

        ArrayList<String> nd1=bd.affiNDE();
        ArrayAdapter<String> dep1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nd1);
        departement.setAdapter(dep1);

        ArrayList<String> nb=bd.affiNB();
        ArrayAdapter<String>nombes=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nb);
        besoin.setAdapter(nombes);

        ArrayList<String> m1=bd.affiMarque();
        ArrayAdapter<String>mar=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,m1);
        marq.setAdapter(mar);




        Intent intent=getIntent();
        if (intent != null)
        {

            date.setText(intent.getStringExtra("bringD"));
            employe.setText(intent.getStringExtra("employe"));
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
            departement.requestFocus();
        }



        employe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BringOut.this,ListeUtilisateur.class);
                intent.putExtra("bringDate",date.getText().toString());
                intent.putExtra("bringO","sortie");
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
            }
        });
        departement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BringOut.this,Listedepartement.class);
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
        });
        besoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BringOut.this,ListeBesoin.class);
                intent.putExtra("bringDate",date.getText().toString());
                intent.putExtra("bringO","sortie1");
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

        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        });
        //fin diadlogueDatepiker
       /* demande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BringOut.this,ListeDate.class);
                intent.putExtra("datededemande",employe.getText().toString());
                startActivity(intent);
            }
        });*/

       demande.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               ArrayList<String> au=new ArrayList<>();
               String var;
               if (employe.isEnabled()) {
                    var = bd.selectEmpId(employe.getText().toString());
                    if (var !=null){
                        au = bd.affiNumDem(Integer.parseInt(var));
                    }

               }
               if (departement.isEnabled()) {
                   var = bd.selectDep(departement.getText().toString());
                   if (var !=null){
                       au = bd.affiNumDem1(Integer.parseInt(var));
                   }

               }
               ArrayAdapter<String>aut=new ArrayAdapter<String>(BringOut.this,android.R.layout.simple_list_item_1,au);
               demande.setAdapter(aut);
           }
       });


        passage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(date.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"Veuillez saisir la date de sortie SVP!!",Toast.LENGTH_LONG).show();
                    date.requestFocus();
                }
                else if (demande.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"Veuillez saisir la date de la demande SVP!!",Toast.LENGTH_LONG).show();
                    demande.requestFocus();
                }
                else if (besoin.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"Veuillez saisir le nom du besoin SVP!!",Toast.LENGTH_LONG).show();
                    besoin.requestFocus();
                }
                else if (qut.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"Veuillez saisir le prix unitaire SVP!!",Toast.LENGTH_LONG).show();
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
                    bd.insertSortie(date.getText().toString(), num);
                    dernierEnr = Integer.parseInt(bd.selectIdSortie());
                    //NumSor` INTEGER, `NumBes` INTEGER, `qte` INTEGER NOT NULL, `marqueBes` TEXT, `Autre précision`
                    int var = Integer.parseInt(bd.selectIdBes(besoin.getText().toString()));
                    int var1 = Integer.parseInt(qut.getText().toString());
                    bd.insertSortieBesoin(dernierEnr, var, var1, marq.getText().toString(), autr.getText().toString());
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
                    Toast.makeText(getBaseContext(),"Veuillez saisir la date de sortie SVP!!",Toast.LENGTH_LONG).show();
                    date.requestFocus();
                }
                else if (demande.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"Veuillez saisir la date de la demande SVP!!",Toast.LENGTH_LONG).show();
                    demande.requestFocus();
                }
                else if (besoin.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"Veuillez saisir le nom du besoin SVP!!",Toast.LENGTH_LONG).show();
                    besoin.requestFocus();
                }
                else if (qut.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"Veuillez saisir le prix unitaire SVP!!",Toast.LENGTH_LONG).show();
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
                        bd.insertSortie(date.getText().toString(), num);
                    }


                    dernierEnr = Integer.parseInt(bd.selectIdSortie());
                    //NumSor` INTEGER, `NumBes` INTEGER, `qte` INTEGER NOT NULL, `marqueBes` TEXT, `Autre précision`
                    int var = Integer.parseInt(bd.selectIdBes(besoin.getText().toString()));
                    int var1 = Integer.parseInt(qut.getText().toString());
                    bd.insertSortieBesoin(dernierEnr, var, var1, marq.getText().toString(), autr.getText().toString());
                    //update debut
                    int var2 = Integer.parseInt(bd.selectStockBes(besoin.getText().toString()));
                    int var3 = var2 - var1;
                    bd.upDate(var3, besoin.getText().toString());
                    //update fin

                    bd.close();
                    Toast.makeText(getBaseContext(), "Sortie enregistrée avec succès !!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(BringOut.this, Acceuil.class);
                    startActivity(intent);
                }
            }
        });

        Button quitter=(Button)findViewById(R.id.quitte);
        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BringOut.this,Acceuil.class);
                startActivity(intent);

            }
        });
    }
}
