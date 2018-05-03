package com.example.hp.madose.Listes;

import android.Manifest;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.madose.AddBEC;
import com.example.hp.madose.AddEC;
import com.example.hp.madose.Authentification;
import com.example.hp.madose.BaseDeDonne;
import com.example.hp.madose.CoutTotalBesoin;
import com.example.hp.madose.Frag_accueil_listes;
import com.example.hp.madose.R;
import com.example.hp.madose.Stock1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.hp.madose.MyApplication.mDatabase;

public class ListeDesEntrees extends AppCompatActivity {
    List<String> liste=new ArrayList<>();
    TableLayout tableLayout;
    private String a="";
    private String b="";
    private Boolean count=false;
    private Boolean count1=false;
    private Boolean count2=false;
    private Boolean count3=false;
    private Boolean count4=false;
    private Boolean count5=false;
    private Boolean count6=false;
    private Boolean count7=false;
    private FirebaseAuth mAuth;
    ProgressDialog mProgressDialog;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_des_entrees);

        final BaseDeDonne bd=new BaseDeDonne(this);


        //base de données distant
        showProgressDialog();
        mDatabase.child("Entree").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotEnt:dataSnapshot.getChildren()){
                    AddEC cat= dataSnapshotEnt.getValue(AddEC.class);


                    if (!bd.checkIfEntreeExist(cat.getLibFour(),cat.getHeureEnt(),cat.getUser())){
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

                        int var2 = Integer.parseInt(bd.selectStockBes(cat.getLibBes()));
                        int var3 = var2 + cat.getQte();
                        bd.upDate(var3, cat.getLibBes());
                        count=true;


                    }
                }
                if (count){
                    finish();
                    showProgressDialog();
                    Intent intent=new Intent(ListeDesEntrees.this,ListeDesEntrees.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    //intent.putExtra("passage","affichage2");
                    intent.putExtra("sortie","listeE");
                    startActivity(intent);
                    hideProgressDialog();
                    count=false;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        hideProgressDialog();
//fin base de donnée distant


        tableLayout=(TableLayout)findViewById(R.id.tableauLE);
        ImageView set=(ImageView)findViewById(R.id.colonne);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogueB();
                tableLayout.removeAllViews();
            }
        });
        tableLayout.setPadding(12,16,12,16);
        TableRow tl=new TableRow(this);
        tl.setBackgroundColor(Color.parseColor("#17631E"));
        tl.setPadding(12,16,12,16);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tl.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }
        TextView date=new TextView(this);
        date.setTypeface(null, Typeface.BOLD);
        date.setTextColor(Color.parseColor("#FFFFFF"));
        date.setText("DATE");
        date.setPadding(15,15,15,15);
        tl.addView(date);

        TextView mat=new TextView(this);
        mat.setText("MATERIELS");
        mat.setTextColor(Color.parseColor("#FFFFFF"));
        mat.setTypeface(null, Typeface.BOLD);
        mat.setPadding(15,15,15,15);
        tl.addView(mat);



        TextView prent=new TextView(this);
        prent.setTypeface(null, Typeface.BOLD);
        prent.setTextColor(Color.parseColor("#FFFFFF"));
        prent.setText("TYPES");
        prent.setPadding(15,15,15,15);
      //  tl.addView(prent);


        TextView PU=new TextView(this);
        PU.setTypeface(null, Typeface.BOLD);
        PU.setTextColor(Color.parseColor("#FFFFFF"));
        PU.setText("P.U");
        PU.setPadding(15,15,15,15);
        tl.addView(PU);

        TextView qt=new TextView(this);
        qt.setTypeface(null, Typeface.BOLD);
        qt.setTextColor(Color.parseColor("#FFFFFF"));
        qt.setText("QUANTITE");
        qt.setPadding(15,15,15,15);
        tl.addView(qt);

        TextView marq=new TextView(this);
        marq.setTypeface(null, Typeface.BOLD);
        marq.setTextColor(Color.parseColor("#FFFFFF"));
        marq.setText("MARQUES");
        marq.setPadding(15,15,15,15);

        tl.addView(marq);

        TextView autre=new TextView(this);
        autre.setTypeface(null, Typeface.BOLD);
        autre.setTextColor(Color.parseColor("#FFFFFF"));
        autre.setText("PRECISIONS");
        autre.setPadding(15,15,15,15);
       // tl.addView(autre);
        tableLayout.addView(tl,new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

         List<Stock1> affF =null;
        if(getIntent().getStringExtra("sortie").equals("libelle"))
        {
            affF = bd.afficheLibEntree(getIntent().getStringExtra("libelle"));
        }
        else if (getIntent().getStringExtra("sortie").equals("listeE"))
        {
            affF =bd.afficheStock1();
        }
       // List<Stock1> affF = bd.afficheStock1();
        int count=0;
        for (Stock1 emp : affF) {


            TableRow tr=new TableRow(this);
            tr.setPadding(12,16,12,16);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tr.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }
            if (count %2!=0) tr.setBackgroundColor(Color.parseColor("#d1d2d2"));
            TextView item7=new TextView(this);
            item7.setPadding(15,15,15,15);
            item7.setTextColor(Color.parseColor("#000000"));
            item7.setText(emp.toStringDate());
            tr.addView(item7);

            TextView item1=new TextView(this);
            item1.setPadding(15,15,15,15);
            item1.setTextColor(Color.parseColor("#000000"));
            item1.setText(emp.toStringNom());
            tr.addView(item1);

            TextView item2=new TextView(this);
            item2.setPadding(15,15,15,15);
            item2.setTextColor(Color.parseColor("#000000"));
            if (emp.toStringType().equals("AMORTISSABLE"))
            {
                item2.setText("A.");
            }
            else if (emp.toStringType().equals("NON AMORTISSABLE"))
            {
                item2.setText("N.A.");
            }

            //tr.addView(item2);

            TextView item3=new TextView(this);
            item3.setPadding(15,15,15,15);
            item3.setTextColor(Color.parseColor("#000000"));
            item3.setText(String.valueOf(emp.toStringPU()));
            tr.addView(item3);

            TextView item4=new TextView(this);
            item4.setPadding(15,15,15,15);
            item4.setTextColor(Color.parseColor("#000000"));
            item4.setText(String.valueOf(emp.toStringQuantite()));
            tr.addView(item4);

            TextView item5=new TextView(this);
            item5.setPadding(15,15,15,15);
            item5.setTextColor(Color.parseColor("#000000"));
            item5.setText(emp.toStringMarque());
           // tr.addView(item5);

            TextView item6=new TextView(this);
            item6.setPadding(15,15,15,15);
            item6.setTextColor(Color.parseColor("#000000"));
            item6.setText(emp.toStringAutre());
           // tr.addView(item6);



            tableLayout.addView(tr,new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            count++;
        }
        TextView nbligne=(TextView)findViewById(R.id.ligneEn);
        nbligne.setText(""+count+" ligne(s)");
    }
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Mise à jour...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_affiche, menu);
        return true;
    }
    @Override
    public void onBackPressed(){
super.onBackPressed();
       Frag_accueil_listes fragment = new Frag_accueil_listes();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(fragment,"fragment");
        transaction.commit();

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
            case R.id.rechercher:
                //startActivity(new Intent(this, CoutTotalBesoin.class));
                //Pour aller a la page precedente
                //ListeDesEntrees.this.finish();
                Intent intent=new Intent(ListeDesEntrees.this,CoutTotalBesoin.class);
                intent.putExtra("recherche","Rentree");
                startActivity(intent);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void dialogueB()
    {
        final BaseDeDonne bd=new BaseDeDonne(this);
        final LayoutInflater maboite=this.getLayoutInflater();
        final View boitededialogue=maboite.inflate(R.layout.boite,null);
        final AlertDialog.Builder bdd=new AlertDialog.Builder(this);
        bdd.setView(boitededialogue);
        bdd.setTitle("Cochez les colonnes à afficher SVP!!");



        bdd.setPositiveButton("Valider", new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int which) {
                CheckBox datee=(CheckBox)boitededialogue.findViewById(R.id.checkBox);
                CheckBox mater=(CheckBox)boitededialogue.findViewById(R.id.checkBox2);
                CheckBox type=(CheckBox)boitededialogue.findViewById(R.id.checkBox3);
                CheckBox pu=(CheckBox)boitededialogue.findViewById(R.id.checkBox4);
                CheckBox qte=(CheckBox)boitededialogue.findViewById(R.id.checkBox5);
                CheckBox marqq=(CheckBox)boitededialogue.findViewById(R.id.checkBox6);
                CheckBox autr=(CheckBox)boitededialogue.findViewById(R.id.checkBox7);


                tableLayout=(TableLayout)findViewById(R.id.tableauLE);
                tableLayout.setPadding(12,16,12,16);
                TableRow tl=new TableRow(getBaseContext());
                tl.setBackgroundColor(Color.parseColor("#17631E"));
                tl.setPadding(12,16,12,16);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tl.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                }
                TextView date=new TextView(getBaseContext());
                date.setTypeface(null, Typeface.BOLD);
                date.setTextColor(Color.parseColor("#FFFFFF"));
                date.setText("DATE");
                date.setPadding(15,15,15,15);
                if (datee.isChecked())
                {
                    tl.addView(date);
                }
                else
                {

                }
                TextView mat=new TextView(getBaseContext());
                mat.setText("MATERIELS");
                mat.setTextColor(Color.parseColor("#FFFFFF"));
                mat.setTypeface(null, Typeface.BOLD);
                mat.setPadding(15,15,15,15);

                if (mater.isChecked())
                {
                    tl.addView(mat);
                }
                else
                {

                }

                TextView prent=new TextView(getBaseContext());
                prent.setTypeface(null, Typeface.BOLD);
                prent.setTextColor(Color.parseColor("#FFFFFF"));
                prent.setText("TYPES");
                prent.setPadding(15,15,15,15);
                if (type.isChecked())
                {
                    tl.addView(prent);
                }
                else
                {

                }

                TextView PU=new TextView(getBaseContext());
                PU.setTypeface(null, Typeface.BOLD);
                PU.setTextColor(Color.parseColor("#FFFFFF"));
                PU.setText("P.U");
                PU.setPadding(15,15,15,15);
                if (pu.isChecked())
                {
                    tl.addView(PU);
                }
                else
                {

                }


                TextView qt=new TextView(getBaseContext());
                qt.setTypeface(null, Typeface.BOLD);
                qt.setTextColor(Color.parseColor("#FFFFFF"));
                qt.setText("QUANTITE");
                qt.setPadding(15,15,15,15);

                if (qte.isChecked())
                {
                    tl.addView(qt);
                }
                else
                {

                }
                TextView marq=new TextView(getBaseContext());
                marq.setTypeface(null, Typeface.BOLD);
                marq.setTextColor(Color.parseColor("#FFFFFF"));
                marq.setText("MARQUES");
                marq.setPadding(15,15,15,15);
                if (marqq.isChecked())
                {
                    tl.addView(marq);
                }
                else
                {

                }

                TextView autre=new TextView(getBaseContext());
                autre.setTypeface(null, Typeface.BOLD);
                autre.setTextColor(Color.parseColor("#FFFFFF"));
                autre.setText("PRECISIONS");
                autre.setPadding(15,15,15,15);

                if (autr.isChecked())
                {
                    tl.addView(autre);
                }
                else
                {

                }
                tableLayout.addView(tl,new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                List<Stock1> affF =null;
                if(getIntent().getStringExtra("sortie").equals("libelle"))
                {
                    affF = bd.afficheLibEntree(getIntent().getStringExtra("libelle"));
                }
                else if (getIntent().getStringExtra("sortie").equals("listeE"))
                {
                    affF =bd.afficheStock1();
                }
                // List<Stock1> affF = bd.afficheStock1();
                int count=0;
                for (Stock1 emp : affF) {


                    TableRow tr=new TableRow(getBaseContext());
                    tr.setPadding(12,16,12,16);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        tr.setLayoutParams(new ActionMenuView.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    }
                    if (count %2!=0) tr.setBackgroundColor(Color.parseColor("#d1d2d2"));
                    TextView item7=new TextView(getBaseContext());
                    item7.setPadding(15,15,15,15);
                    item7.setTextColor(Color.parseColor("#000000"));
                    item7.setText(emp.toStringDate());
                    if (datee.isChecked())
                    {
                        tr.addView(item7);
                    }
                    else
                    {

                    }


                    TextView item1=new TextView(getBaseContext());
                    item1.setPadding(15,15,15,15);
                    item1.setTextColor(Color.parseColor("#000000"));
                    item1.setText(emp.toStringNom());
                    if (mater.isChecked())
                    {
                        tr.addView(item1);
                    }
                    else
                    {

                    }


                    TextView item2=new TextView(getBaseContext());
                    item2.setPadding(15,15,15,15);
                    item2.setTextColor(Color.parseColor("#000000"));
                    if (emp.toStringType().equals("AMORTISSABLE"))
                    {
                        item2.setText("A.");
                    }
                    else if (emp.toStringType().equals("NON AMORTISSABLE"))
                    {
                        item2.setText("N.A.");
                    }


                    if (type.isChecked())
                    {
                        tr.addView(item2);
                    }
                    else
                    {

                    }

                    TextView item3=new TextView(getBaseContext());
                    item3.setPadding(15,15,15,15);
                    item3.setTextColor(Color.parseColor("#000000"));
                    item3.setText(String.valueOf(emp.toStringPU()));

                    if (pu.isChecked())
                    {
                        tr.addView(item3);
                    }
                    else
                    {

                    }

                    TextView item4=new TextView(getBaseContext());
                    item4.setPadding(15,15,15,15);
                    item4.setTextColor(Color.parseColor("#000000"));
                    item4.setText(String.valueOf(emp.toStringQuantite()));
                    if (qte.isChecked())
                    {
                        tr.addView(item4);
                    }
                    else
                    {

                    }

                    TextView item5=new TextView(getBaseContext());
                    item5.setPadding(15,15,15,15);
                    item5.setTextColor(Color.parseColor("#000000"));
                    item5.setText(emp.toStringMarque());
                    if (marqq.isChecked())
                    {
                        tr.addView(item5);
                    }
                    else
                    {

                    }

                    TextView item6=new TextView(getBaseContext());
                    item6.setPadding(15,15,15,15);
                    item6.setTextColor(Color.parseColor("#000000"));
                    item6.setText(emp.toStringAutre());
                    if (autr.isChecked())
                    {
                        tr.addView(item6);
                    }
                    else
                    {

                    }



                    tableLayout.addView(tr,new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    count++;
                }
                TextView nbligne=(TextView)findViewById(R.id.ligneEn);
                nbligne.setText(""+count+" ligne(s)");

            } });

      /*  bdd.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            } });*/
        bdd.show();
    }

}
