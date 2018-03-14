package com.example.hp.madose;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hp.madose.model.Item;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    DatabaseReference mDatabase;


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
        final ImageView imageView= findViewById(R.id.imageView3);
        final RadioButton radioNonAm= findViewById(R.id.radioNonAm);
        final RadioButton radioAm= findViewById(R.id.radioAm);
        int result = radioGroup.getCheckedRadioButtonId();
        radio = (RadioButton) findViewById(result);
        mDatabase= FirebaseDatabase.getInstance().getReference();

        radioNonAm.isChecked();
        edi1.setVisibility(View.VISIBLE);
        edi1.setEnabled(true);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Besoin.this,Affichage.class);
                intent.putExtra("passage","image");
                startActivity(intent);
            }
        });





        Intent intent=getIntent();
        if (intent != null)
        {
            editLib.setText(intent.getStringExtra("libCat"));
            edi1.setText(intent.getStringExtra("seuil"));
            edi2.setText(intent.getStringExtra("peremption"));
            edi3.setText(intent.getStringExtra("stock"));
            radioNonAm.setChecked(intent.getBooleanExtra("betat1",radioNonAm.isChecked()));
            radioAm.setChecked(intent.getBooleanExtra("betat2",radioAm.isChecked()));
            edi1.setVisibility(intent.getIntExtra("betat3",edi1.getVisibility()));
            edi2.setVisibility(intent.getIntExtra("betat4",edi2.getVisibility()));
            auto.setText(intent.getStringExtra("categorie"));
            if (MyApplication.id>0) {
                imageView.setImageResource(MyApplication.id);
            }
        }
        auto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if (radioNonAm.isChecked())
                    {
                        Intent intent=new Intent(Besoin.this,Listecategorie.class);
                        intent.putExtra("blibe",editLib.getText().toString());
                        intent.putExtra("bseuil",edi1.getText().toString());
                        intent.putExtra("bperemp",edi2.getText().toString());
                        intent.putExtra("bstock",edi3.getText().toString());
                        intent.putExtra("code","nonAm");
                        intent.putExtra("etat1",true);
                        intent.putExtra("etat2",false);
                        intent.putExtra("etat3",View.VISIBLE);
                        intent.putExtra("etat4",View.INVISIBLE);
                        startActivity(intent);
                        finish();
                    }
                    else if (radioAm.isChecked())
                    {
                        Intent intent=new Intent(Besoin.this,Listecategorie.class);
                        intent.putExtra("blibe",editLib.getText().toString());
                        intent.putExtra("bseuil",edi1.getText().toString());
                        intent.putExtra("bperemp",edi2.getText().toString());
                        intent.putExtra("bstock",edi3.getText().toString());
                        intent.putExtra("code","Am");
                        intent.putExtra("etat1",false);
                        intent.putExtra("etat2",true);
                        intent.putExtra("etat3",View.INVISIBLE);
                        intent.putExtra("etat4",View.VISIBLE);
                        startActivity(intent);
                        finish();
                    }
                    else {
                    Intent intent=new Intent(Besoin.this,Listecategorie.class);
                    intent.putExtra("code","none");
                    intent.putExtra("blibe",editLib.getText().toString());
                    intent.putExtra("bseuil",edi1.getText().toString());
                    intent.putExtra("bperemp",edi2.getText().toString());
                    intent.putExtra("bstock",edi3.getText().toString());
                    startActivity(intent);
                    finish();
                    }
                }
            }
        });



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
                finish();
            }
        });

   if (MyApplication.isVerif()){

       int resId=getResources().getIdentifier(MyApplication.verif1,"drawable",getPackageName());
       imageView.setImageResource(MyApplication.id);

       MyApplication.setVerif(false);
   }
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
                    editLib.setError("Veuillez saisir le libellé du besoin SVP!!");
                }
                else if (auto.getText().toString().equals(""))
                {
                    auto.requestFocus();
                    auto.setError("Veuillez saisir la catégorie du besoin SVP!!");
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


                if (radio.getText().toString().equals("AMORTISSABLE"))
                {
                    var1=edi2.getText().toString();
                    int resId=getResources().getIdentifier(MyApplication.verif1,"drawable",getPackageName());
                    bd.insertBesoin(editLib.getText().toString(), rb.getText().toString(), var3,0,var1,varo,resId);
                    writeNewBesoin(editLib.getText().toString(),rb.getText().toString(),auto.getText().toString(),0,var1,varo,resId);
                    Toast.makeText(Besoin.this, "Besoin enregistré avec succès", Toast.LENGTH_LONG).show();
                }

               else if (radio.getText().toString().equals("NON AMORTISSABLE"))
                {
                    var = Integer.parseInt(edi1.getText().toString());
                    int resId=getResources().getIdentifier(MyApplication.verif1,"drawable",getPackageName());
                    bd.insertBesoin(editLib.getText().toString(), rb.getText().toString(), var3,var,"0",varo,resId);
                    writeNewBesoin(editLib.getText().toString(), rb.getText().toString(), auto.getText().toString(),var,"0",varo,resId);
                    Toast.makeText(Besoin.this,"Besoin enregistré avec succès", Toast.LENGTH_LONG).show();
                }


                    edi1.setText("");
                    edi2.setText("");
                    editLib.setText("");
                    auto.setText("");
                    Intent intent = new Intent(Besoin.this, Affichage.class);
                    intent.putExtra("passage", "besoin");
                    startActivity(intent);
                    finish();
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
                finish();
            }
        });


    }

    public void radioBout(View v) {
        edi1 = (EditText) findViewById(R.id.seuil);
        edi2 = (EditText) findViewById(R.id.peremption);

        int resul = radioGroup.getCheckedRadioButtonId();
        radio = (RadioButton) findViewById(resul);


        if (radio.getText().toString().equals("AMORTISSABLE")) {

            edi2.setEnabled(true);
            edi1.setEnabled(false);
            edi1.setVisibility(View.INVISIBLE);
            edi2.setVisibility(View.VISIBLE);
            Toast.makeText(getBaseContext(), radio.getText().toString(), Toast.LENGTH_SHORT).show();
        } else

        if (radio.getText().toString().equals("NON AMORTISSABLE")) {

            edi1.setEnabled(true);
            edi2.setEnabled(false);
            edi1.setVisibility(View.VISIBLE);
            edi2.setVisibility(View.INVISIBLE);
            Toast.makeText(getBaseContext(), radio.getText().toString(), Toast.LENGTH_LONG).show();
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
