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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Add extends AppCompatActivity {
    int jour,mois,annee;
    boolean fait=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        BaseDeDonne bd=new BaseDeDonne(this);
        //Création du form




        final AutoCompleteTextView four=(AutoCompleteTextView)findViewById(R.id.autoCompleteFour);
        final AutoCompleteTextView besoin=(AutoCompleteTextView)findViewById(R.id.autoCompleteBesoin);
        final EditText date=(EditText)findViewById(R.id.datepik);
        final EditText pu=(EditText)findViewById(R.id.PU);
        final EditText qte=(EditText)findViewById(R.id.QT);
        final EditText mark=(EditText)findViewById(R.id.marq);
        final EditText autre=(EditText)findViewById(R.id.autre);
        final ImageView button= findViewById(R.id.boutoun);



        //---------AutoTextComplete
        final ArrayList<String> nf=bd.affiNF();
        ArrayAdapter<String>nomfour=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nf);
        four.setAdapter(nomfour);

        ArrayList<String> nb=bd.affiNB();
        ArrayAdapter<String>nombes=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nb);
        besoin.setAdapter(nombes);

        //-------insertion dans la base de donnée

        //------DATEPCKERDIALOG
        //**final EditText date=(EditText)findViewById(R.id.datepik);
        final Calendar calendar;
        calendar=Calendar.getInstance();
        jour=calendar.get(Calendar.DAY_OF_MONTH);
        mois=calendar.get(Calendar.MONTH);
        annee=calendar.get(Calendar.YEAR);
        //date.setText(jour+"/"+mois+"/"+annee);

    /*    date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 DatePickerDialog datePickerDialog=new DatePickerDialog(Add.this, new DatePickerDialog.OnDateSetListener() {
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
        });  */



        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (b){

                    DatePickerDialog datePickerDialog=new DatePickerDialog(Add.this, new DatePickerDialog.OnDateSetListener() {
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


        final BaseDeDonne dd=new BaseDeDonne(this);
       // dd.upDate(0,"STYLO");


        final Button passage=(Button)findViewById(R.id.AjouterEn);
        passage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (four.getText().toString().equals(""))
                {
                   // Toast.makeText(getBaseContext(),"Veuillez saisir la date d'approvisionnement SVP!!",Toast.LENGTH_LONG).show();
                    date.setError("Veuillez saisir la date d'approvisionnement SVP!!");
                    date.requestFocus();
                }
                else if (!date.getText().toString().matches("[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]")){
                    date.setError("Votre date ne respecte pas le format JJ/MM/AAAA\nExemple: 01/01/1970");

                }

                else if (!nf.contains(four.getText().toString()) && !four.getText().toString().equals("")){
                    four.setError("Le fournisseur choisi ne fait pas partie de la liste des fournisseurs de l'entreprise.\n Veuillez donc l'ajouter à la liste préalablement en cliquant sur le bouton + \nsinon veuillez entrer un fournisseur faisant partie de la liste.");
                    button.setVisibility(View.VISIBLE);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(),Fournisseur.class));

                        }
                    });
                }
                else if (four.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"Veuillez saisir le nom du fournisseur de cette approvisionnement SVP!!",Toast.LENGTH_LONG).show();
                    four.setError("Veuillez saisir le nom du fournisseur de cette approvisionnement SVP!!");
                    four.requestFocus();
                }

                else if (besoin.getText().toString().equals(""))
                {
                    besoin.setError("Veuillez saisir le nom du besoin SVP!!");
                    besoin.requestFocus();
                }
                else if (pu.getText().toString().equals(""))
                {
                    pu.setError("Veuillez saisir le prix unitaire SVP!!");
                    pu.requestFocus();
                }
                else if (qte.getText().toString().equals(""))
                {
                    qte.setError("Veuillez saisir la quantité SVP!!");
                    qte.requestFocus();
                }
                else {
                String a,b,c;
                a=date.getText().toString().substring(0,2);
                b=date.getText().toString().substring(3,5);
                c=date.getText().toString().substring(6,10);
                date.setText(c+"-"+b+"-"+a);


                if (!fait) {
                    int var = Integer.parseInt(dd.selectFour(four.getText().toString()));
                    dd.insertEntr(date.getText().toString(), var);
                }

                int var1=Integer.parseInt(dd.selectIdBes(besoin.getText().toString()));

                int quantite=Integer.parseInt(qte.getText().toString());
                int prix=Integer.parseInt(pu.getText().toString());


                int dernierEnregistrem= Integer.parseInt(dd.selectIdEnt());
                dd.insertEntrBes(var1,dernierEnregistrem,prix,quantite,mark.getText().toString(),autre.getText().toString());

               //update debut
               int var2=Integer.parseInt(dd.selectStockBes(besoin.getText().toString()));
               int var3=var2+quantite;
               dd.upDate(var3,besoin.getText().toString());
                //update fin


                pu.setText("");
                qte.setText("");
                autre.setText("");
                mark.setText("");
                besoin.setText("");
                dd.close();
                Toast.makeText(getBaseContext(),"Approvisionnement enregistré avec succès !!",Toast.LENGTH_SHORT).show();
                fait=true;
                }

            }
        });

        Button passag=(Button)findViewById(R.id.EnregistrerEn);
        passag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(date.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"Veuillez saisir la date d'approvisionnement SVP!!",Toast.LENGTH_LONG).show();
                    date.requestFocus();
                }
                else if (four.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"Veuillez saisir le nom du fournisseur de cette approvisionnement SVP!!",Toast.LENGTH_LONG).show();
                    four.requestFocus();
                }

                else if (besoin.getText().toString().equals(""))
                {
                    besoin.setError("Veuillez saisir le nom du besoin SVP!!");
                    besoin.requestFocus();
                }
                else if (pu.getText().toString().equals(""))
                {
                   pu.setError("Veuillez saisir le prix unitaire SVP!!");
                    pu.requestFocus();
                }
                else if (qte.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"Veuillez saisir la quantité SVP!!",Toast.LENGTH_LONG).show();
                    qte.requestFocus();
                }

                else {
                    if (fait == false) {
                        String a, b, c;
                        a = date.getText().toString().substring(0, 2);
                        b = date.getText().toString().substring(3, 5);
                        c = date.getText().toString().substring(6, 10);
                        date.setText(c + "-" + b + "-" + a);
                        int var = Integer.parseInt(dd.selectFour(four.getText().toString()));
                        dd.insertEntr(date.getText().toString(), var);
                    }


                    int var1 = Integer.parseInt(dd.selectIdBes(besoin.getText().toString()));

                    int quantite = Integer.parseInt(qte.getText().toString());
                    int prix = Integer.parseInt(pu.getText().toString());


                    int dernierEnregistrem = Integer.parseInt(dd.selectIdEnt());
                    dd.insertEntrBes(var1, dernierEnregistrem, prix, quantite, mark.getText().toString(), autre.getText().toString());

                    //update debut
                    int var2 = Integer.parseInt(dd.selectStockBes(besoin.getText().toString()));
                    int var3 = var2 + quantite;
                    dd.upDate(var3, besoin.getText().toString());
                    //update fin

                    dd.close();
                    Toast.makeText(getBaseContext(), "Approvisionnement enregistré avec succès !!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Add.this, Acceuil.class);
                    startActivity(intent);
                }

            }
        });
        Button quitter=(Button)findViewById(R.id.quitter);
        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Add.this,Acceuil.class);
                startActivity(intent);

            }
        });

    }
}
