package com.example.hp.madose;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Demande extends AppCompatActivity {

    boolean fait=false;
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
                else if (employe.getText().toString().equals(""))
                {
                    employe.requestFocus();
                    employe.setError("Veuillez saisir le nom de l'employé ou du département SVP!");
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
                    a = date.getText().toString().substring(0, 2);
                    b = date.getText().toString().substring(3, 5);
                    c = date.getText().toString().substring(6, 10);
                    date.setText(c + "-" + b + "-" + a);


                    int var1, var2;

                    int var3 = Integer.parseInt(bd.selectIdBes(bes.getText().toString()));
                    int var4 = Integer.parseInt(quant.getText().toString());

                    if (radioButton_emp.isChecked()) {
                        var1 = Integer.parseInt(bd.selectEmpId(employe.getText().toString()));
                        departe = bd.DepartEmp(var1);
                        var2 = Integer.parseInt(bd.selectDep(departe));
                        bd.insertDemande(date.getText().toString(), var1, var2);
                    }

                    if (radioButton_dep.isChecked()) {
                        String recup = bd.selectDep(depart.getText().toString());
                        bd.insertDemande1(date.getText().toString(), Integer.parseInt(recup));
                    }


                    int dernierEnr = Integer.parseInt(bd.selectIdDem());
                    bd.insertDemandeBesoin(dernierEnr, var3, var4);
                    writeNewDemande(employe.getText().toString(),depart.getText().toString(),bes.getText().toString(),date.getText().toString(),Integer.parseInt(quant.getText().toString()));
                    bes.setText("");
                    quant.setText("");
                    Toast.makeText(getBaseContext(), "Demande enregistrée avec succès !!", Toast.LENGTH_LONG).show();
                    fait = true;
                }
            }
        });

        Button enregistre=(Button)findViewById(R.id.annul);
        enregistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date.getText().toString().equals(""))
                {
                    date.requestFocus();
                    date.setError("Veuillez saisir la date SVP!");
                }
                else if (employe.getText().toString().equals(""))
                {
                    employe.requestFocus();
                    employe.setError("Veuillez saisir le nom de l'employé ou du département SVP!");
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
                    if (fait == false) {
                        String a, b, c;
                        a = date.getText().toString().substring(0, 2);
                        b = date.getText().toString().substring(3, 5);
                        c = date.getText().toString().substring(6, 10);
                        date.setText(c + "-" + b + "-" + a);
                    }
                    int var1, var2;
                    int var3 = Integer.parseInt(bd.selectIdBes(bes.getText().toString()));
                    int var4 = Integer.parseInt(quant.getText().toString());
                    if (fait == false) {
                        if (radioButton_emp.isChecked()) {
                            //Toast.makeText(getBaseContext(),employe.getText().toString()+"coucou",Toast.LENGTH_LONG).show();
                            var1 = Integer.parseInt(bd.selectEmpId(employe.getText().toString()));
                            String departe = bd.DepartEmp(var1);
                            var2 = Integer.parseInt(bd.selectDep(departe));
                            bd.insertDemande(date.getText().toString(), var1, var2);
                        }

                        if (radioButton_dep.isChecked()) {
                            String recup = bd.selectDep(depart.getText().toString());
                            bd.insertDemande1(date.getText().toString(), Integer.parseInt(recup));
                        }
                    }
                    int dernierEnr = Integer.parseInt(bd.selectIdDem());
                    bd.insertDemandeBesoin(dernierEnr, var3, var4);
                    writeNewDemande(employe.getText().toString(),depart.getText().toString(),bes.getText().toString(),date.getText().toString(),Integer.parseInt(quant.getText().toString()));
                    bd.close();
                    Toast.makeText(getBaseContext(), "Sortie enregistrée avec succès !!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Demande.this, Acceuil.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getBaseContext(), "Demande enregistrée avec succès !!", Toast.LENGTH_LONG).show();
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
            }
        });

    }
    public void writeNewDemande(String nomEmp,String libDpe, String libBes,String dateDem, int qte){
        String code=nomEmp+"-"+libDpe+"-"+libBes+"-"+dateDem;
        String cricri="";
        String cris="";
        String cristi="";
        String crissi="";
        if (nomEmp.contains(" ")){
            cricri=nomEmp.replace(" ","-");
            code=cricri+"-"+libDpe+"-"+libBes+"-"+dateDem;
        }
        if (dateDem.contains("/")){
            cris=dateDem.replace("/","-");
            code=nomEmp+"-"+libDpe+"-"+libBes+"-"+cris;
        }
        if (nomEmp.contains("'") || libDpe.contains("'") || libBes.contains("'")){
            code=code.replace("'","-");
        }
        if (libDpe.contains(" ") || libBes.contains(" ")){
            code=code.replace(" ","-");
        }

        DemandeC cat=new DemandeC(nomEmp,libDpe,libBes,dateDem,qte);
        mDatabase.child("Demande").child(code).setValue(cat);
    }
}
