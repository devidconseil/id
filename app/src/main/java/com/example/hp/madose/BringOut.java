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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class BringOut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bring_out);

        final BaseDeDonne bd=new BaseDeDonne(this);
        final Button passage=(Button)findViewById(R.id.sortiVal);
        final AutoCompleteTextView employe=(AutoCompleteTextView)findViewById(R.id.autoEmp);
        final AutoCompleteTextView besoin=(AutoCompleteTextView)findViewById(R.id.autoBesoin);
        final EditText qut=(EditText)findViewById(R.id.sortieQt);
        final AutoCompleteTextView marq=(AutoCompleteTextView)findViewById(R.id.autoCompMark);
        final AutoCompleteTextView autr=(AutoCompleteTextView)findViewById(R.id.autoCompAutre);
        //final Spinner spinner=(Spinner)findViewById(R.id.spinner);
        final AutoCompleteTextView demande=(AutoCompleteTextView)findViewById(R.id.autoDemande);

        final EditText date=(EditText)findViewById(R.id.editDate);
        //AutoTextComplete
        ArrayList<String> nd=bd.affiNE();
        ArrayAdapter<String> dep=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nd);
        employe.setAdapter(dep);

        ArrayList<String> nb=bd.affiNB();
        ArrayAdapter<String>nombes=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nb);
        besoin.setAdapter(nombes);

        ArrayList<String> m1=bd.affiMarque();
        ArrayAdapter<String>mar=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,m1);
        marq.setAdapter(mar);




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
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(BringOut.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        date.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },annee,mois,jour);
                datePickerDialog.show();
            }
        });
        //fin diadlogueDatepiker

       demande.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               String var=bd.selectEmpId(employe.getText().toString());
               ArrayList<String> au=bd.affiNumDem(Integer.parseInt(var));
               ArrayAdapter<String>aut=new ArrayAdapter<String>(BringOut.this,android.R.layout.simple_list_item_1,au);
               demande.setAdapter(aut);
           }
       });


        passage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num= bd.selectNumeDem(demande.getText().toString(),employe.getText().toString());
                int dernierEnr;
               // bd.insertSortie(date.getText().toString(),num);
               // bd.close();
                String a,b,c;
                a=date.getText().toString().substring(0,2);
                b=date.getText().toString().substring(3,5);
                c=date.getText().toString().substring(6,10);
                date.setText(c+"-"+b+"-"+a);
                bd.insertSortie(date.getText().toString(),num);
                dernierEnr=Integer.parseInt(bd.selectIdSortie());
                //NumSor` INTEGER, `NumBes` INTEGER, `Qte` INTEGER NOT NULL, `MarqueBes` TEXT, `Autre précision`
                int var=Integer.parseInt(bd.selectIdBes(besoin.getText().toString()));
                int var1=Integer.parseInt(qut.getText().toString());
                bd.insertSortieBesoin(dernierEnr,var,var1,marq.getText().toString(),autr.getText().toString());
                //update debut
                int var2=Integer.parseInt(bd.selectStockBes(besoin.getText().toString()));
                int var3=var2-var1;
                bd.upDate(var3,besoin.getText().toString());
                //update fin

                besoin.setText("");
                qut.setText("");
                marq.setText("");
                autr.setText("");
                bd.close();

                Toast.makeText(getBaseContext(),"Sortie enregistrée avec succès !!",Toast.LENGTH_LONG).show();
            }
        });
        Button passa=(Button)findViewById(R.id.enreg);
        passa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num= bd.selectNumeDem(demande.getText().toString(),employe.getText().toString());
                int dernierEnr;
                // bd.insertSortie(date.getText().toString(),num);
                // bd.close();
                String a,b,c;
                a=date.getText().toString().substring(0,2);
                b=date.getText().toString().substring(3,5);
                c=date.getText().toString().substring(6,10);
                date.setText(c+"-"+b+"-"+a);
                bd.insertSortie(date.getText().toString(),num);
                dernierEnr=Integer.parseInt(bd.selectIdSortie());
                //NumSor` INTEGER, `NumBes` INTEGER, `Qte` INTEGER NOT NULL, `MarqueBes` TEXT, `Autre précision`
                int var=Integer.parseInt(bd.selectIdBes(besoin.getText().toString()));
                int var1=Integer.parseInt(qut.getText().toString());
                bd.insertSortieBesoin(dernierEnr,var,var1,marq.getText().toString(),autr.getText().toString());
                //update debut
                int var2=Integer.parseInt(bd.selectStockBes(besoin.getText().toString()));
                int var3=var2-var1;
                bd.upDate(var3,besoin.getText().toString());
                //update fin

                bd.close();
                Toast.makeText(getBaseContext(),"Sortie enregistrée avec succès !!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(BringOut.this,Acceuil.class);
                startActivity(intent);
            }
        });


    }
}
