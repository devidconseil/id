package com.example.hp.madose;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Demande extends AppCompatActivity {

    boolean fait=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande);
        final BaseDeDonne bd=new BaseDeDonne(this);

        final EditText date=(EditText)findViewById(R.id.dateDemande);
        final Button passage=(Button)findViewById(R.id.ajout);
        final AutoCompleteTextView employe=(AutoCompleteTextView)findViewById(R.id.autoCompEmp);
        final AutoCompleteTextView depart=(AutoCompleteTextView)findViewById(R.id.autoCompDep);
        final AutoCompleteTextView bes=(AutoCompleteTextView)findViewById(R.id.autoCompBes);
        final EditText quant=(EditText)findViewById(R.id.editqt);
        //AutoTextComplete
        ArrayList<String> nd=bd.affiNE();
        ArrayAdapter<String> dep=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nd);
        employe.setAdapter(dep);

        ArrayList<String> em=bd.affiNDE();
        ArrayAdapter<String> emp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,em);
        depart.setAdapter(emp);

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

        passage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  int var=Integer.parseInt(date.getText().toString());
                String a,b,c;
                a=date.getText().toString().substring(0,2);
                b=date.getText().toString().substring(3,5);
                c=date.getText().toString().substring(6,10);
                date.setText(c+"-"+b+"-"+a);
                int var1=Integer.parseInt(bd.selectEmpId(employe.getText().toString()));
                int var2=Integer.parseInt(bd.selectDep(depart.getText().toString()));
                int var3=Integer.parseInt(bd.selectIdBes(bes.getText().toString()));
                int var4=Integer.parseInt(quant.getText().toString());
                bd.insertDemande(date.getText().toString(),var1,var2);
                int dernierEnr=Integer.parseInt(bd.selectIdDem());
                bd.insertDemandeBesoin(dernierEnr,var3,var4);
                bes.setText("");
                quant.setText("");
                Toast.makeText(getBaseContext(),"Demande enregistrée avec succès !!",Toast.LENGTH_LONG).show();
                fait=true;
            }
        });

        Button enregistre=(Button)findViewById(R.id.annul);
        enregistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fait==false){
                    String a,b,c;
                    a=date.getText().toString().substring(0,2);
                    b=date.getText().toString().substring(3,5);
                    c=date.getText().toString().substring(6,10);
                    date.setText(c+"-"+b+"-"+a); }

                String a,b,c;
                a=date.getText().toString().substring(0,2);
                b=date.getText().toString().substring(3,5);
                c=date.getText().toString().substring(6,10);
                date.setText(c+"-"+b+"-"+a);
                int var1=Integer.parseInt(bd.selectEmpId(employe.getText().toString()));
                int var2=Integer.parseInt(bd.selectDep(depart.getText().toString()));
                int var3=Integer.parseInt(bd.selectIdBes(bes.getText().toString()));
                int var4=Integer.parseInt(quant.getText().toString());
                bd.insertDemande(date.getText().toString(),var1,var2);
                int dernierEnr=Integer.parseInt(bd.selectIdDem());
                bd.insertDemandeBesoin(dernierEnr,var3,var4);
                bd.close();
                Toast.makeText(getBaseContext(),"Sortie enregistrée avec succès !!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(Demande.this,Acceuil.class);
                startActivity(intent);

                Toast.makeText(getBaseContext(),"Demande enregistrée avec succès !!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
