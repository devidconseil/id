package com.example.hp.madose;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;



public class Add extends AppCompatActivity {
    int jour,mois,annee;
    boolean aBoolean=false;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
     final BaseDeDonne bd=new BaseDeDonne(this);
        //Création du form




        final AutoCompleteTextView four=(AutoCompleteTextView)findViewById(R.id.autoCompleteFour);
        final AutoCompleteTextView besoin=(AutoCompleteTextView)findViewById(R.id.autoCompleteBesoin);
        final EditText date=(EditText)findViewById(R.id.datepik);
        final EditText pu=(EditText)findViewById(R.id.PU);
        final EditText qte=(EditText)findViewById(R.id.QT);
        final EditText mark=(EditText)findViewById(R.id.marq);
        final EditText autre=(EditText)findViewById(R.id.autre);
        mDatabase= FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Fournisseur").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotFour:dataSnapshot.getChildren()){
                    FournisseurC four=dataSnapshotFour.getValue(FournisseurC.class);
                    if (!bd.checkIfFournisseurExist(four.getNomFour())){
                        bd.insertFour(four.getNomFour(),four.getAdrFour(),four.getTelFour());
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

       date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
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
        });

        Intent intent=getIntent();
        if (intent != null)
        {

            date.setText(intent.getStringExtra("addD"));
            four.setText(intent.getStringExtra("addFournisseur"));
            besoin.setText(intent.getStringExtra("addB"));
            pu.setText(intent.getStringExtra("addP"));
            qte.setText(intent.getStringExtra("addQ"));
            mark.setText(intent.getStringExtra("addM"));
            autre.setText(intent.getStringExtra("addA"));
            if (intent.getStringExtra("code").equals("addFF")) {
                besoin.requestFocus();
            }
            else if (intent.getStringExtra("code").equals("addBB"))
            {
                pu.requestFocus();
            }

        }

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Add.this, ListeFournisseur.class);
                intent.putExtra("addDate",date.getText().toString());
                intent.putExtra("addBesoin",besoin.getText().toString());
                intent.putExtra("addPrixU",pu.getText().toString());
                intent.putExtra("addQt",qte.getText().toString());
                intent.putExtra("addMark",mark.getText().toString());
                intent.putExtra("addAutre",autre.getText().toString());
                startActivity(intent);
                finish();
            }
        });
        besoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Add.this, ListeBesoin.class);
                intent.putExtra("addDate",date.getText().toString());
                intent.putExtra("addFourni",four.getText().toString());
                intent.putExtra("addPrixU",pu.getText().toString());
                intent.putExtra("addQt",qte.getText().toString());
                intent.putExtra("addMark",mark.getText().toString());
                intent.putExtra("addAutre",autre.getText().toString());
                intent.putExtra("code","add");
                startActivity(intent);
                finish();
            }
        });
       /* date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        });*/


        final BaseDeDonne dd=new BaseDeDonne(this);
       // dd.upDate(0,"STYLO");


        final Button passage=(Button)findViewById(R.id.AjouterEn);
        passage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (date.getText().toString().equals(""))
                {

                    date.setError("Veuillez saisir la date d'approvisionnement SVP!!");
                    date.requestFocus();
                }
                else if (!date.getText().toString().matches("[0-3][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]")){

                    date.setError("Votre date ne respecte pas le format JJ/MM/AAAA\nExemple: 01/01/1970");
                    date.requestFocus();


                }

                else if (!nf.contains(four.getText().toString()) && !four.getText().toString().equals("")){
                    four.setError("Le fournisseur choisi ne fait pas partie de la liste des fournisseurs de l'entreprise." +
                            "\n Veuillez donc l'ajouter à la liste préalablement en cliquant sur le bouton + " +
                            "\nsinon veuillez entrer un fournisseur faisant partie de la liste.");

                }
                else if (four.getText().toString().equals(""))
                {
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
                    String a, b, c;
                    if (! date.getText().toString().matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {

                        a = date.getText().toString().substring(0, 2);
                        b = date.getText().toString().substring(3, 5);
                        c = date.getText().toString().substring(6, 10);
                        date.setText(c + "-" + b + "-" + a);
                    }
                    if (!MyApplication.isFait()) {
                        int var = Integer.parseInt(dd.selectFour(four.getText().toString()));
                        dd.insertEntr(date.getText().toString(), var, "", MyApplication.getmAuth().getCurrentUser().getEmail(), true);
                        writeNewEntree(four.getText().toString(), date.getText().toString(), bd.selectHeureEnt(), MyApplication.getmAuth().getCurrentUser().getEmail());
                    }

                    if (!bd.checkIfBesoinEntreeExist(besoin.getText().toString(), date.getText().toString(),bd.selectHeureEnt(),bd.selectUserEnt(bd.selectHeureEnt()))) {




                        int var1 = Integer.parseInt(dd.selectIdBes(besoin.getText().toString()));

                        int quantite = Integer.parseInt(qte.getText().toString());
                        int prix = Integer.parseInt(pu.getText().toString());


                        int dernierEnregistrem = Integer.parseInt(dd.selectIdEnt());
                        dd.insertEntrBes(var1, dernierEnregistrem, prix, quantite, mark.getText().toString(), autre.getText().toString());
                        writeNewAdd(bd.selectHeureEnt(),besoin.getText().toString(), date.getText().toString(), prix, quantite, mark.getText().toString(), autre.getText().toString());

                        //update debut
                        int var2 = Integer.parseInt(dd.selectStockBes(besoin.getText().toString()));
                        int var3 = var2 + quantite;
                        dd.upDate(var3, besoin.getText().toString());
                        //update fin


                        pu.setText("");
                        qte.setText("");
                        autre.setText("");
                        mark.setText("");
                        besoin.setText("");
                        dd.close();
                        if (date.getText().toString().matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
                            a = date.getText().toString().substring(0, 4);
                            b = date.getText().toString().substring(5, 7);
                            c = date.getText().toString().substring(8, 10);
                            date.setText(c + "/" + b + "/" + a);
                        }
                        Toast.makeText(getBaseContext(), "Approvisionnement enregistré avec succès !!", Toast.LENGTH_SHORT).show();
                        MyApplication.setFait(true);

                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Add.this,0x00000005 );
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

        Button passag=(Button)findViewById(R.id.EnregistrerEn);
        passag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (date.getText().toString().equals(""))
                {
                    date.setError("Veuillez saisir la date d'approvisionnement SVP!!");
                    date.requestFocus();
                }
                else if (!date.getText().toString().matches("[0-3][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]")){
                    date.setError("Votre date ne respecte pas le format JJ/MM/AAAA\nExemple: 01/01/1970");

                }

               else if (!nf.contains(four.getText().toString()) && !four.getText().toString().equals("")){
                    four.setError("Le fournisseur choisi ne fait pas partie de la liste des fournisseurs de l'entreprise." +
                            "\n Veuillez donc l'ajouter à la liste préalablement en cliquant sur le bouton + " +
                            "\nsinon veuillez entrer un fournisseur faisant partie de la liste.");

                }
                else if (four.getText().toString().equals(""))
                {
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

                    String a, b, c;
                    if (! date.getText().toString().matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
                        a = date.getText().toString().substring(0, 2);
                        b = date.getText().toString().substring(3, 5);
                        c = date.getText().toString().substring(6, 10);
                        date.setText(c + "-" + b + "-" + a);
                    }
                    if (!MyApplication.isFait()) {

                 /*       String a, b, c;
                        a = date.getText().toString().substring(0, 2);
                        b = date.getText().toString().substring(3, 5);
                        c = date.getText().toString().substring(6, 10);
                        date.setText(c + "-" + b + "-" + a);  */
                        int var = Integer.parseInt(dd.selectFour(four.getText().toString()));
                        dd.insertEntr(date.getText().toString(), var, "", MyApplication.getmAuth().getCurrentUser().getEmail(), true);
                        writeNewEntree(four.getText().toString(), date.getText().toString(), bd.selectHeureEnt(), MyApplication.getmAuth().getCurrentUser().getEmail());
                    }

                    if (! bd.checkIfBesoinEntreeExist(besoin.getText().toString(),date.getText().toString(),bd.selectHeureEnt(),bd.selectUserEnt(bd.selectHeureEnt()))) {

                        int var1 = Integer.parseInt(dd.selectIdBes(besoin.getText().toString()));

                        int quantite = Integer.parseInt(qte.getText().toString());
                        int prix = Integer.parseInt(pu.getText().toString());


                        int dernierEnregistrem = Integer.parseInt(dd.selectIdEnt());
                        if (MyApplication.isFait()) {
                     /*   String a, b, c, d;
                        a = date.getText().toString().substring(0, 2);
                        b = date.getText().toString().substring(3, 5);
                        c = date.getText().toString().substring(6, 10);
                        date.setText(c + "-" + b + "-" + a);  */
                        }

                        dd.insertEntrBes(var1, dernierEnregistrem, prix, quantite, mark.getText().toString(), autre.getText().toString());
                        writeNewAdd(bd.selectHeureEnt(),besoin.getText().toString(), date.getText().toString(), prix, quantite, mark.getText().toString(), autre.getText().toString());

                        //update debut
                        int var2 = Integer.parseInt(dd.selectStockBes(besoin.getText().toString()));
                        int var3 = var2 + quantite;
                        dd.upDate(var3, besoin.getText().toString());
                        //update fin

                        dd.close();
                        Toast.makeText(getBaseContext(), "Approvisionnement enregistré avec succès !!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Add.this, Acceuil.class);
                        startActivity(intent);
                        finish();

                        MyApplication.setFait(false);
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Add.this,0x00000005 );
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
        Button quitter=(Button)findViewById(R.id.quitter);
    //    quitter.requestFocus();
        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Add.this,Acceuil.class);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(Add.this,0x00000005 );
        builder.setMessage("Voulez-vous abandonner l'enregistrement?");
        builder.setTitle("Attention!");
        builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Add.this,Acceuil.class);
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


    public void writeNewAdd(String heureEnt,String libBes,String datEnt, int pU, int qte, String marqueBes, String autrePrécision){
        BaseDeDonne bd=new BaseDeDonne(getApplicationContext());
       String now=bd.selectCurrentDate();

        String code=libBes+"-"+datEnt+"-"+now;
        String cricri="";
        String cris="";
        if (libBes.contains(" ")){
            cricri=libBes.replace(" ","-");
            code=cricri+"-"+datEnt+"-"+now;
        }
        if (datEnt.contains("/")){
            cris=datEnt.replace("/","-");
            code=libBes+"-"+cris+"-"+now;
        }
        if (libBes.contains("'")){
            code=code.replace("'","-");
        }

        AddBEC cat=new AddBEC(heureEnt,libBes,datEnt,pU,qte, marqueBes, autrePrécision);
        mDatabase.child("Besoins-Entree").child(code).setValue(cat);
    }
    public void writeNewEntree(String libFour, String datEnt, String heureEnt, String user){
        BaseDeDonne bd=new BaseDeDonne(getApplicationContext());
        String now=bd.selectCurrentDate();
        String code=libFour+"-"+datEnt+"-"+now;
        String cricri="";
        String cris="";
        if (libFour.contains(" ")){
            cricri=libFour.replace(" ","-");
            code=cricri+"-"+datEnt+"-"+now;
        }
        if (datEnt.contains("/")){
            cris=datEnt.replace("/","-");
            code=libFour+"-"+cris+"-"+now;
        }
        if (libFour.contains("'")){
            code=code.replace("'","-");
        }

        AddEC cat=new AddEC(libFour,datEnt,heureEnt,user);
        mDatabase.child("Entree").child(code).setValue(cat);
    }
}
