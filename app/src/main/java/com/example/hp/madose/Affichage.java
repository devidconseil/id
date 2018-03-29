package com.example.hp.madose;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Affichage extends AppCompatActivity {
    private BaseDeDonne bd;
    private TextView categorie;
    private TextView fournisseur;
    private TextView departement;
    private TextView employe;
    private TextView stock;
    private TextView stock1;
    private TextView stocke;
    private TextView cout,demande;
    private ListView listView;
    private GridView gridView;
    private ScrollView scrollview;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String a="";
    private String b="";
    private Boolean count=false;
    private Boolean count1=false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);

        bd = new BaseDeDonne(this);
        List<String> maliste=new ArrayList<>();
        TableLayout tableLayout;
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();

       /* listView= findViewById(R.id.listeview);
        listView.setVisibility(View.INVISIBLE);  */


        // bd.insertCat("mobilier");

/*
        textView=(TextView)findViewById(R.id.textView4);
        List<AddEC>affE=bd.afficheEntre();
        for(AddEC emp:affE)
        {
            textView.append(emp.toString()+"\n\n");
        }
*/




/*
        textView=(TextView)findViewById(R.id.textView4);
        affichage=(TextView)findViewById(R.id.textView2);
        ArrayList<String> nc=bd.selectCat("mobilier");


        for(String emp:nc)
        {
            textView.append(emp.toString());
        }
**/
//debut ooooooooooooooooooiiiiiiiiiiiiiiiiiiiiiiiiiooooooooooooooooooooooooooooooooo

       if (getIntent().getStringExtra("passage").equals("fournisseur"))
        {

            fournisseur = (TextView) findViewById(R.id.textView4);
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
            List<FournisseurC> affF = bd.afficheF();
            for (FournisseurC emp : affF) {
                fournisseur.append(emp.toString() + "\n\n");
            }
            //Tableau affichage fournisseur
           /* tableLayout=(TableLayout)findViewById(R.id.tabAff);
            tableLayout.setPadding(12,16,12,16);
            TableRow tl=new TableRow(this);
            tl.setBackgroundColor(Color.parseColor("#17631E"));
            tl.setPadding(12,16,12,16);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                tl.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                TextView nomt = new TextView(this);
                nomt.setText("NOM");
                nomt.setTextColor(Color.parseColor("#FFFFFF"));
                nomt.setTypeface(null, Typeface.BOLD);
                nomt.setPadding(20, 15, 20, 15);
                tl.addView(nomt);
                TextView prent = new TextView(this);
                prent.setTypeface(null, Typeface.BOLD);
                prent.setTextColor(Color.parseColor("#FFFFFF"));
                prent.setText("ADRESSE");
                prent.setPadding(20, 15, 20, 15);
                tl.addView(prent);
                TextView mailt = new TextView(this);
                mailt.setTypeface(null, Typeface.BOLD);
                mailt.setTextColor(Color.parseColor("#FFFFFF"));
                mailt.setText("CONTACT");
                mailt.setPadding(20, 15, 20, 15);
                tl.addView(mailt);
            }
            else{

                TextView nomt = new TextView(this);
                nomt.setText("NOM");
                nomt.setTextColor(Color.parseColor("#FFFFFF"));
                nomt.setTypeface(null, Typeface.BOLD);
                nomt.setPadding(5, 10, 5, 10);
                tl.addView(nomt);
                TextView prent = new TextView(this);
                prent.setTypeface(null, Typeface.BOLD);
                prent.setTextColor(Color.parseColor("#FFFFFF"));
                prent.setText("ADRESSE");
                prent.setPadding(5, 10, 5, 10);
                tl.addView(prent);
                TextView mailt = new TextView(this);
                mailt.setTypeface(null, Typeface.BOLD);
                mailt.setTextColor(Color.parseColor("#FFFFFF"));
                mailt.setText("CONTACT");
                mailt.setPadding(5, 10, 5, 10);
                tl.addView(mailt);
            }
            tableLayout.addView(tl,new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            List<FournisseurC> affF = bd.afficheF();
            int count=0;
            for (FournisseurC emp : affF) {
                //fournisseur.append(emp.toString() + "\n\n");
                TableRow tr = new TableRow(this);
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                    tr.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    tr.setPadding(20, 16, 20, 16);
                if (count % 2 != 0) tr.setBackgroundColor(Color.parseColor("#d1d2d2"));
                TextView nomF = new TextView(this);
                nomF.setPadding(20, 15, 20, 15);
                nomF.setTextColor(Color.parseColor("#000000"));
                nomF.setText(emp.toStringNomFour());
                nomF.setTypeface(null, Typeface.BOLD);
                tr.addView(nomF);
                TextView adressF = new TextView(this);
                adressF.setTextColor(Color.parseColor("#000000"));
                adressF.setPadding(20, 15, 20, 15);
                adressF.setText(String.valueOf(emp.toStringAdressFour()));
                tr.addView(adressF);
                TextView contactF = new TextView(this);
                contactF.setTextColor(Color.parseColor("#000000"));
                contactF.setPadding(20, 15, 20, 15);
                contactF.setText(String.valueOf(emp.toStringContactFour()));
                tr.addView(contactF);
            }
            else{
                    tr.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    tr.setPadding(5, 5, 5, 5);
                    if (count % 2 != 0) tr.setBackgroundColor(Color.parseColor("#d1d2d2"));
                    TextView nomF = new TextView(this);
                    nomF.setPadding(5, 10, 5, 10);
                    nomF.setTextColor(Color.parseColor("#000000"));
                    nomF.setText(emp.toStringNomFour());
                    nomF.setTypeface(null, Typeface.BOLD);
                    tr.addView(nomF);
                    TextView adressF = new TextView(this);
                    adressF.setTextColor(Color.parseColor("#000000"));
                    adressF.setPadding(5, 10, 5, 10);
                    adressF.setText(String.valueOf(emp.toStringAdressFour()));
                    tr.addView(adressF);
                    TextView contactF = new TextView(this);
                    contactF.setTextColor(Color.parseColor("#000000"));
                    contactF.setPadding(5, 10, 5, 10);
                    contactF.setText(String.valueOf(emp.toStringContactFour()));
                    tr.addView(contactF);
                }
                tableLayout.addView(tr,new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
                count++;
            }*/

        }

        else if (getIntent().getStringExtra("passage").equals("departement"))
        {
            departement = (TextView) findViewById(R.id.textView4);
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

            List<DepartementC> affF = bd.afficheDepart();
            for (DepartementC emp : affF) {
                departement.append(emp.toString() + "\n\n");
            }

        }

        if (getIntent().getStringExtra("passage").equals("employe"))
        {
            employe = (TextView) findViewById(R.id.textView4);

            mDatabase.child("users").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshotUser:dataSnapshot.getChildren()){
                        UtilisateurC user=dataSnapshotUser.getValue(UtilisateurC.class);
                        Toast.makeText(getApplicationContext(),user.getMailEmp(),Toast.LENGTH_SHORT);
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


            List<UtilisateurC> affE = bd.afficheE();
            for (UtilisateurC emp : affE) {
                employe.append(emp.toString() + "\n\n");
            }

        }
//fin iiiiiiiiiiiiiiiiiiiiiiiiiiiiioooooooooooooooooooooooooooooooooooooiiiiiiiiiiiiiiiiiiiiiiiiiii



      /*  affichage=(TextView)findViewById(R.id.textView2);

        List<AddBEC>affEb=bd.afficheBesoinEntre();
        for(AddBEC emp:affEb)
        {
            affichage.append(emp.toString()+"\n\n");
        }*/



        if (getIntent().getStringExtra("passage").equals("categorie"))

        {
            categorie = (TextView) findViewById(R.id.textView2);
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

            List<CategorieC> affC = bd.afficheCat();
            for (CategorieC emp : affC) {
                categorie.append(emp.toString() + "\n\n");
            }
        }

        if (getIntent().getStringExtra("passage").equals("besoin")) {
             employe = (TextView) findViewById(R.id.textView4);

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

             List<BesoinC> affC = bd.afficheB();
             for (BesoinC emp : affC) {
                 employe.append(emp.toString1() + "\n\n");
             }
         }

        if (getIntent().getStringExtra("passage").equals("stock")) {
            MyApplication.setTextView(false);
            stock = (TextView) findViewById(R.id.textView14);
            gridView= findViewById(R.id.gridview);
            scrollview= findViewById(R.id.scrollview);
            int liste=0;
            List<String> list=new ArrayList<>();
           List<Integer> list1=new ArrayList<>();
           stock.setText("LISTE DES BESOINS (MATERIELS) ET LEUR STOCK \n");
            List<StockC> affS = bd.afficheSt();
            List<StockC> affS1 = bd.afficheSt1();
           for (StockC emp : affS) {
                if (emp.getTypeBes().equals("NON AMORTISSABLE")) {
                   //stock.append(emp.toString() + "\n\n");
                   list.add(emp.toString());
                   list1.add(emp.getImageBes());



                }
            }
            for (StockC emp : affS1) {
                if (emp.getTypeBes().equals("AMORTISSABLE")) {
                  //  stock.append(emp.toString1() + "\n\n");
                    list.add(emp.toString1());
                    list1.add(emp.getImageBes());
                }

            }
       StockAffichAdapter arrayAdapter=new StockAffichAdapter(this,list,list1);
          gridView.setAdapter(arrayAdapter);
          gridView.setVisibility(View.VISIBLE);
          scrollview.setVisibility(View.INVISIBLE);
          stock.setVisibility(View.VISIBLE);


        }
          if (getIntent().getStringExtra("passage").equals("demande")) {
            demande = (TextView) findViewById(R.id.textView2);
            mDatabase.child("Demande").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshotDem:dataSnapshot.getChildren()){
                        DemandeC cat= dataSnapshotDem.getValue(DemandeC.class);
                        Log.i("I MISS YOU",cat.getDateDem()+" "+cat.getNomEmp());

                        if (! bd.checkIfDemandeBesoinExist(cat.getHeureDem(),cat.getLibBes())){

                            int ssss=Integer.parseInt(bd.selectIdBes(cat.getLibBes()));
                            if (cat.getLibDpe().equals("")){
                                int ss=Integer.parseInt(bd.selectEmpId(cat.getNomEmp()));
                                int sss=Integer.parseInt(bd.selectDep(bd.DepartEmp(ss)));
                                if (! bd.checkIfDemandeExist(cat.getNomEmp(),cat.getHeureDem(),cat.getLibDpe())) {
                                    bd.insertDemande(cat.getDateDem(), ss, sss, cat.getHeureDem(), false);
                                }
                                bd.insertDemandeBesoin(Integer.parseInt(bd.selectIdDem(cat.getHeureDem())),ssss,cat.getQte());
                                //   Toast.makeText(getApplicationContext(),cat.toString(),Toast.LENGTH_LONG).show();
                            }
                            if (cat.getNomEmp().equals("")) {
                                int ss=0;
                                int sss=Integer.parseInt(bd.selectDep(cat.getLibDpe()));
                                bd.insertDemande1(cat.getDateDem(),sss,cat.getHeureDem(),false);
                                bd.insertDemandeBesoin(Integer.parseInt(bd.selectIdDem(cat.getHeureDem())),ssss,cat.getQte());
                            }

                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            String profile=bd.retrieveUserProfile(mAuth.getCurrentUser().getEmail());
            if (profile.equals("SUPER ADMIN")) {
                demande.setText("LISTE DES DEMANDES DE BESOINS PAR PERSONNE \n\n\n");
                List<DemandeC> affF = bd.afficheDemande();
                for (DemandeC emp : affF) {
                    demande.append(emp.toString() + "\n\n");
                }
                List<DemandeC> affF1 = bd.afficheDemande1();
                for (DemandeC emp : affF1) {
                    demande.append(emp.toString1() + "\n\n");
                }
            }

            if (profile.equals("USER")){
                demande.setText("VOTRE LISTE DE DEMANDES \n\n\n");
                List<DemandeC> affF = bd.afficheDemandeUser(mAuth.getCurrentUser().getEmail());
                for (DemandeC emp : affF) {
                    demande.append(emp.toString() + "\n\n");
                }
            }
        }
        if (getIntent().getStringExtra("passage").equals("sortie")) {
            stock1 = (TextView) findViewById(R.id.textView2);
            mDatabase.child("Demande").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshotDem:dataSnapshot.getChildren()){
                        DemandeC cat= dataSnapshotDem.getValue(DemandeC.class);
                        Log.i("I MISS YOU",cat.getDateDem()+" "+cat.getNomEmp()+" "+bd.selectIdBes(cat.getLibBes()));

                        if (! bd.checkIfDemandeBesoinExist(cat.getHeureDem(),cat.getLibBes())){

                            int ssss=Integer.parseInt(bd.selectIdBes(cat.getLibBes()));
                            if (cat.getLibDpe().equals("")){
                                int ss=Integer.parseInt(bd.selectEmpId(cat.getNomEmp()));
                                int sss=Integer.parseInt(bd.selectDep(bd.DepartEmp(ss)));
                                if (! bd.checkIfDemandeExist(cat.getNomEmp(),cat.getHeureDem(),cat.getLibDpe())) {
                                    bd.insertDemande(cat.getDateDem(), ss, sss, cat.getHeureDem(), false);
                                }
                                Log.i("Je DOIS VOIR",bd.selectIdDem(cat.getHeureDem())+" "+ssss);
                                bd.insertDemandeBesoin(Integer.parseInt(bd.selectIdDem(cat.getHeureDem())),ssss,cat.getQte());
                                //   Toast.makeText(getApplicationContext(),cat.toString(),Toast.LENGTH_LONG).show();

                            }
                            if (cat.getNomEmp().equals("")) {
                                int ss=0;
                                int sss=Integer.parseInt(bd.selectDep(cat.getLibDpe()));
                                if (! bd.checkIfDemandeExist(cat.getNomEmp(),cat.getHeureDem(),cat.getLibDpe())) {
                                    bd.insertDemande1(cat.getDateDem(), sss, cat.getHeureDem(), false);
                                }
                               // bd.insertDemandeBesoin(Integer.parseInt(bd.selectIdDem1(cat.getLibDpe(),cat.getDateDem())),ssss,cat.getQte());
                                Log.i("ON DOIT VOIR",bd.selectIdDem(cat.getHeureDem())+" "+ssss);
                                bd.insertDemandeBesoin(Integer.parseInt(bd.selectIdDem(cat.getHeureDem())),ssss,cat.getQte());
                            }

                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            mDatabase.child("Sorties").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshotSor:dataSnapshot.getChildren()){
                        Stock2 cat=dataSnapshotSor.getValue(Stock2.class);
                        Log.i("VOILA SORTIE",cat.getNomEmp()+" "+cat.getDateDem()+" "+cat.getLibDep());
                        if (! bd.checkIfSortieEntreeExist(cat.getNomEmp(),cat.getHeureSor(),cat.getLibBes(),cat.getDate(),cat.getLibDep())){
                            Log.i("VOILAHEIN",cat.getNomEmp()+" "+cat.getHeureSor()+" "+cat.getLibBes()+" "+cat.getDate());
                            if (! bd.checkIfSortieExist(cat.getNomEmp(),cat.getHeureSor(),cat.getLibDep())){
                                Log.i("VOILATOUT",cat.getNomEmp()+" "+cat.getHeureSor());
                                String numDem=bd.selectNumeDem(cat.getDateDem(),cat.getNomEmp(),cat.getLibDep());
                                Log.i("TOUTDEH",""+numDem);
                                bd.insertSortie(cat.getDate(),numDem,cat.getHeureSor(),cat.getNomEmp(),false);
                            }
                                int var1=Integer.parseInt(bd.selectIdSortie());
                                int var2=Integer.parseInt(bd.selectIdBes(cat.getLibBes()));
                                bd.insertSortieBesoin(var1,var2,cat.getQte(),cat.getMarqBes(),cat.getAutreP());
                                count1=true;

                        }
                    }
                    if (count1){
                        finish();
                        Intent intent=new Intent(Affichage.this,Affichage.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //intent.putExtra("passage","affichage2");
                        intent.putExtra("passage","sortie");
                        startActivity(intent);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            stock1.setText("LISTE DES BESOINS (MATERIELS) LIVRES AUX EMPLOYES \n\n\n");
        List<Stock2> affF = bd.afficheStock2();
        List<Stock2> affF1=bd.afficheStock3();
        for (Stock2 emp : affF) {
            stock1.append(emp.toString() + "\n\n");
           }
       for (Stock2 emp : affF1) {
                stock1.append(emp.toString1() + "\n\n");
            }

        }

        if (getIntent().getStringExtra("passage").equals("entree")) {
            stocke = (TextView) findViewById(R.id.textView2);


            mDatabase.child("Entree").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshotEnt:dataSnapshot.getChildren()){
                        AddEC cat= dataSnapshotEnt.getValue(AddEC.class);


                        if (!bd.checkIfEntreeExist(cat.getLibFour(),cat.getHeureEnt(),cat.user)){
                            Log.i("SHOW-ME",cat.getLibFour()+" "+cat.getHeureEnt());
                          int ss=Integer.parseInt(bd.selectFour(cat.getLibFour()));
                            bd.insertEntr(cat.getDatEnt(),ss,cat.getHeureEnt(),cat.getUser(),false);

                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            mDatabase.child("Besoins-Entree").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshotBesEnt:dataSnapshot.getChildren()){
                        AddBEC cat= dataSnapshotBesEnt.getValue(AddBEC.class);

                        if (!bd.checkIfBesoinEntreeExist(cat.getLibBes(),cat.getDatEnt(),cat.getHeureEnt(),bd.selectUserEnt(cat.getHeureEnt()))){
                            Log.i("MONTRE-MOI",cat.getLibBes()+" "+cat.getDatEnt()+" "+cat.getHeureEnt()+" "+bd.selectUserEnt(cat.getHeureEnt()));
                            int ss=Integer.parseInt(bd.selectIdBes(cat.getLibBes()));
                            Log.i("REAL",""+cat.getHeureEnt());
                            int sss=Integer.parseInt(bd.selectIdEnt(Integer.parseInt(cat.getHeureEnt())));

                            bd.insertEntrBes(ss,sss,cat.getPU(),cat.getQte(),cat.getMarqueBes(),cat.getAutrePrécision());


                           count=true;


                        }

                    }
                    if (count){
                        finish();
                        Intent intent=new Intent(Affichage.this,Affichage.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //intent.putExtra("passage","affichage2");
                        intent.putExtra("passage","entree");
                        startActivity(intent);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            stocke.setText("LISTE DES BESOINS (MATERIELS) ENTRES \n\n\n");
            List<Stock1> affF = bd.afficheStock1();
            for (Stock1 emp : affF) {
                stocke.append(emp.toString() + "\n\n");
            }
        }
        if (getIntent().getStringExtra("passage").equals("Cout")){
            cout = (TextView) findViewById(R.id.textView2);
            cout.setText("LISTE DES BESOINS (MATERIELS) ENTRES \n\n\n");



            List<CoutC> affF = bd.CoutBesoin(MyApplication.getGlobalVarValue());
       //     Toast.makeText(getBaseContext(),getIntent().getStringExtra("passage"),Toast.LENGTH_LONG);
           for (CoutC emp : affF) {
               cout.append(emp.toString() + "\n\n");
           }
            AlertDialog.Builder builder = new AlertDialog.Builder(Affichage.this,0x00000005);
            builder.setMessage("Le coût total en approvisionnement du besoin "+MyApplication.getGlobalVarValue()+" est "+MyApplication.getCoutTotBes()+" FCFA.");
            builder.setTitle(R.string.dialog_title);
            builder.setPositiveButton("Voir plus", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {


                }
            });
            builder.create();
            builder.show();
        }



        if (getIntent().getStringExtra("passage").equals("Rupture")){
            cout = (TextView) findViewById(R.id.textView2);
            cout.setText("LISTE DES BESOINS (MATERIELS) EN RUPTURE \n\n\n");

            boolean a=true;

            List<RuptureC> affF = bd.RupureCheck();
            //     Toast.makeText(getBaseContext(),getIntent().getStringExtra("passage"),Toast.LENGTH_LONG);
            for (RuptureC emp : affF) {
                cout.append(emp.toString() + "\n");
            }
            if (MyApplication.isDone()){
                AlertDialog.Builder builder = new AlertDialog.Builder(Affichage.this,0x00000005);
                builder.setMessage("Certains de vos articles sont en rupture");
                builder.setTitle("Rupture");
                builder.setPositiveButton("Voir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.create();
                builder.show();

            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(Affichage.this,0x00000005);
                builder.setMessage("Aucun de vos articles n'est en rupture");
                builder.setTitle("Rupture Check");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent=new Intent(Affichage.this,Acceuil.class);
                        startActivity(intent);

                    }
                });
                builder.create();
                builder.show();
            }




        }

        if (getIntent().getStringExtra("passage").equals("Rupture_original")) {
            cout = (TextView) findViewById(R.id.textView2);
            cout.setText("LISTE DES BESOINS (MATERIELS) EN RUPTURE \n\n\n");

            boolean a = true;
            
            List<RuptureC> affF = bd.RupureCheck();
            //     Toast.makeText(getBaseContext(),getIntent().getStringExtra("passage"),Toast.LENGTH_LONG);
            for (RuptureC emp : affF) {
                cout.append(emp.toString()+"\n\n");
            }

        }

     if (getIntent().getStringExtra("passage").equals("image")){
            MyApplication.setTextView(true);
            List<String> list=new ArrayList<>();
            List<Integer> list1=new ArrayList<>();

            String string="";
       /*     for (String bes:besoin_nom){
                list.add(bes);
            }    */
            for (Integer i=1;i<=30;i++){
                string="b"+i;
                int id=getBaseContext().getResources().getIdentifier(string,"drawable",getBaseContext().getPackageName());
                list1.add(id);
                list.add(string);
            }
            BesoinAdapter besoinAdapter=new BesoinAdapter(this,list,list1);

            GridView gridView= findViewById(R.id.gridview);

            gridView.setAdapter(besoinAdapter);
            gridView.setVisibility(View.VISIBLE);


        }

     findViewById(R.id.floatingRetour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Affichage.this,Acceuil.class);
                startActivity(intent);
            }
        });
     findViewById(R.id.floatingAjout).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if (getIntent().getStringExtra("passage").equals("fournisseur"))
             {
                 Intent intent=new Intent(Affichage.this,Fournisseur.class);
                 startActivity(intent);

             }
             else if (getIntent().getStringExtra("passage").equals("departement"))
             {
                 Intent intent=new Intent(Affichage.this,Departement.class);
                 startActivity(intent);
             }
            else if (getIntent().getStringExtra("passage").equals("employe"))
             {
                 Intent intent=new Intent(Affichage.this,Utilisateur.class);
                 startActivity(intent);
             }
             else if (getIntent().getStringExtra("passage").equals("categorie"))
             {
                 Intent intent=new Intent(Affichage.this,Categorie.class);
                 startActivity(intent);
             }
             else  if (getIntent().getStringExtra("passage").equals("besoin"))
             {
                 Intent intent=new Intent(Affichage.this,Besoin.class);
                 startActivity(intent);
             }
             else if (getIntent().getStringExtra("passage").equals("sortie")) {

                 Intent intent=new Intent(Affichage.this,BringOut.class);
                 intent.putExtra("code","affichage");
                 startActivity(intent);
             }
             else if (getIntent().getStringExtra("passage").equals("entree"))
             {
                 Intent intent=new Intent(Affichage.this,Add.class);
                 intent.putExtra("code","affichage");
                 startActivity(intent);
             }
             else if (getIntent().getStringExtra("passage").equals("demande"))
             {
                 Intent intent=new Intent(Affichage.this,Demande.class);
                 intent.putExtra("code","affichage");
                 startActivity(intent);
             }
         }
     });
     if (getIntent().getStringExtra("passage").equals("Liste_achats")){
         cout = (TextView) findViewById(R.id.textView2);
         listView= findViewById(R.id.listeview);
         cout.setText("LISTE DES ARTICLES FAISANT L'OBJET D'ACHAT \n\n\n");

         List<ListAchatC> affF = bd.ListAchat();

         for (ListAchatC emp : affF) {
             cout.append(emp.toString()+"\n\n");
         }

     }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_affiche, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.deconnexion:
               mAuth.signOut();
                finish();
                startActivity(new Intent(this, Authentification.class));

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
