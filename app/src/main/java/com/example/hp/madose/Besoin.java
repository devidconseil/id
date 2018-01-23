package com.example.hp.madose;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;


import java.util.ArrayList;
import java.util.Calendar;

public class Besoin extends AppCompatActivity {
    RadioButton radio;
    Button enregistrer;
    Button quitte;
    RadioGroup radioGroup;
    RadioButton rb;
    EditText edi1;
    EditText edi2;
    EditText edi3;
    EditText editLib;
    BaseDeDonne bd;
    int jour,mois,annee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_besoin);




        //autocomplète
        bd = new BaseDeDonne(this);
        final AutoCompleteTextView auto = (AutoCompleteTextView) findViewById(R.id.autoComplCat);
        ArrayList<String> nc = bd.affiCC();
        ArrayAdapter<String> nomCat = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nc);
        auto.setAdapter(nomCat);
        //autocomplète

        final EditText editLib = (EditText) findViewById(R.id.libelle);
        final EditText edi1 = (EditText) findViewById(R.id.seuil);
        final EditText edi2 = (EditText) findViewById(R.id.peremption);
        final EditText edi3=(EditText)findViewById(R.id.stock);
        radioGroup = (RadioGroup) findViewById(R.id.groupeRadio);
        final Button enregistrer = (Button) findViewById(R.id.enregistre);


        final Calendar calendar;
        calendar=Calendar.getInstance();
        jour=calendar.get(Calendar.DAY_OF_MONTH);
        mois=calendar.get(Calendar.MONTH);
        annee=calendar.get(Calendar.YEAR);
        //date.setText(jour+"/"+mois+"/"+annee);

        quitte=(Button)findViewById(R.id.quitterB);
        quitte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Besoin.this,Acceuil.class);
                edi1.setText("");
                edi2.setText("");
                editLib.setText("");
                auto.setText("");
                startActivity(intent);

            }
        });

        edi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(Besoin.this, new DatePickerDialog.OnDateSetListener() {
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
                        edi2.setText(jour+"/"+mois+"/"+year);

                       // edi2.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },annee,mois,jour);
                datePickerDialog.show();
            }
        });

        enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editLib.getText().toString().equals(""))
                {
                    editLib.requestFocus();
                    Toast.makeText(getBaseContext(),"Veuillez saisir le libellé du besoin SVP!!",Toast.LENGTH_LONG).show();
                }
                else if (auto.getText().toString().equals(""))
                {
                    auto.requestFocus();
                    Toast.makeText(getBaseContext(),"Veuillez saisir la catégorie du besoin SVP!!",Toast.LENGTH_LONG).show();
                }
                else {
                    int resul = radioGroup.getCheckedRadioButtonId();
                    rb = (RadioButton) findViewById(resul);

                    int var;
                    String var1;
                    int var3 = Integer.parseInt(bd.selectCat(auto.getText().toString()));

                    String amort1, amort2, amort3;
                    if (edi2.getText().toString().matches(".*/.*/.*")) {
                        amort1 = edi2.getText().toString().substring(0, 2);
                        amort2 = edi2.getText().toString().substring(3, 5);
                        amort3 = edi2.getText().toString().substring(6, 10);
                        if (Integer.parseInt(amort1) <= 31 && Integer.parseInt(amort2) <= 12 && Integer.parseInt(amort3) >= 1970) {
                            edi2.setText(amort3 + "-" + amort2 + "-" + amort1);

                        } else {
                            Toast.makeText(getBaseContext(), "Erreur", Toast.LENGTH_LONG).show();
                        }

                    }


                    int varo = Integer.parseInt(edi3.getText().toString());
                    int result = radioGroup.getCheckedRadioButtonId();
                    radio = (RadioButton) findViewById(result);

                    if (radio.getText().toString().equals("amortissable")) {
                        var1 = edi2.getText().toString();
                        bd.insertBesoin(editLib.getText().toString(), rb.getText().toString(), var3, 0, var1, varo);
                        Toast.makeText(Besoin.this, "Besoin enregistré avec succès", Toast.LENGTH_LONG).show();
                    } else if (radio.getText().toString().equals("non amortissable")) {
                        var = Integer.parseInt(edi1.getText().toString());
                        bd.insertBesoin(editLib.getText().toString(), rb.getText().toString(), var3, var, "0", varo);
                        Toast.makeText(Besoin.this, "Besoin enregistré avec succès", Toast.LENGTH_LONG).show();
                    }


                    edi1.setText("");
                    edi2.setText("");
                    editLib.setText("");
                    auto.setText("");
                    Intent intent = new Intent(Besoin.this, Affichage.class);
                    intent.putExtra("passage", "besoin");
                    startActivity(intent);
                }

            }
        });
        quitte=(Button)findViewById(R.id.quitterB);
        quitte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Besoin.this,Acceuil.class);
                edi1.setText("");
                edi2.setText("");
                editLib.setText("");
                auto.setText("");
                startActivity(intent);

            }
        });


    }

    public void radioBout(View v) {
        edi1 = (EditText) findViewById(R.id.seuil);
        edi2 = (EditText) findViewById(R.id.peremption);

        int resul = radioGroup.getCheckedRadioButtonId();
        radio = (RadioButton) findViewById(resul);


        if (radio.getText().toString().equals("amortissable")) {

            edi2.setEnabled(true);
            edi1.setEnabled(false);
            Toast.makeText(getBaseContext(), radio.getText().toString(), Toast.LENGTH_SHORT).show();
        } else

        if (radio.getText().toString().equals("non amortissable")) {

            edi1.setEnabled(true);
            edi2.setEnabled(false);
            Toast.makeText(getBaseContext(), radio.getText().toString(), Toast.LENGTH_LONG).show();
        }

    }


}
