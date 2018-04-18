package com.example.hp.madose.Listes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.hp.madose.Affichage;
import com.example.hp.madose.Authentification;
import com.example.hp.madose.BaseDeDonne;
import com.example.hp.madose.CoutTotalBesoin;
import com.example.hp.madose.DemandeC;
import com.example.hp.madose.R;
import com.example.hp.madose.Stock2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.hp.madose.MyApplication.mDatabase;

public class ListeDesSorties extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_des_sorties);

        final BaseDeDonne bd=new BaseDeDonne(this);


        //base de données distant
        showProgressDialog();
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
                        count7=true;

                    }
                    if (count7){
                        finish();
                        showProgressDialog();
                        Intent intent=new Intent(ListeDesSorties.this,ListeDesSorties.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //intent.putExtra("passage","affichage2");
                        intent.putExtra("passage","sortie");
                        startActivity(intent);
                        hideProgressDialog();
                        count7=false;
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
                    if (! bd.checkIfSortieEntreeExist(cat.getHeureSor(),cat.getLibBes())){
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

                        int varP = Integer.parseInt(bd.selectStockBes(cat.getLibBes()));
                        int var3 = varP - cat.getQte();
                        bd.upDate(var3, cat.getLibBes());
                        count1=true;

                    }
                }
                if (count1){
                    finish();
                    Intent intent=new Intent(ListeDesSorties.this,ListeDesSorties.class);
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
        hideProgressDialog();





        tableLayout=(TableLayout)findViewById(R.id.tableauLS);
        tableLayout.setPadding(12,16,12,16);
        TableRow tl=new TableRow(this);
        tl.setBackgroundColor(Color.parseColor("#17631E"));
        tl.setPadding(12,16,12,16);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tl.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }
        TextView num=new TextView(this);
        num.setTypeface(null, Typeface.BOLD);
        num.setTextColor(Color.parseColor("#FFFFFF"));
        num.setText("Num.Sort.");
        num.setPadding(15,15,15,15);
        tl.addView(num);

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
        tl.addView(prent);

        TextView autre=new TextView(this);
        autre.setTypeface(null, Typeface.BOLD);
        autre.setTextColor(Color.parseColor("#FFFFFF"));
        autre.setText("AUTRES PRECISIONS");
        autre.setPadding(15,15,15,15);
        tl.addView(autre);

        TextView qt=new TextView(this);
        qt.setTypeface(null, Typeface.BOLD);
        qt.setTextColor(Color.parseColor("#FFFFFF"));
        qt.setText("QUANTITE");
        qt.setPadding(15,15,15,15);
        tl.addView(qt);

        TextView marq=new TextView(this);
        marq.setTypeface(null, Typeface.BOLD);
        marq.setTextColor(Color.parseColor("#FFFFFF"));
        marq.setText("RECU PAR");
        marq.setPadding(15,15,15,15);
        tl.addView(marq);


        tableLayout.addView(tl,new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

      //  List<Stock2> affF = bd.afficheStock22();
      //  List<Stock2> affF = bd.afficheStockLib("MARKER");

        List<Stock2> affF =null;
        if(getIntent().getStringExtra("sortie").equals("libelle"))
        {
            affF = bd.afficheStockLib(getIntent().getStringExtra("libelle"));
        }
        else if (getIntent().getStringExtra("sortie").equals("listeS"))
        {
            affF =bd.afficheStock22();
        }

        int count=0;
        for (Stock2 emp : affF) {

            TableRow tr=new TableRow(this);
            tr.setPadding(12,16,12,16);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tr.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }
            if (count %2!=0) tr.setBackgroundColor(Color.parseColor("#d1d2d2"));
            TextView item8=new TextView(this);
            item8.setPadding(15,15,15,15);
            item8.setTextColor(Color.parseColor("#000000"));
            item8.setText(String.valueOf(emp.toStringNumSortie()));
            tr.addView(item8);

            TextView item7=new TextView(this);
            item7.setPadding(15,15,15,15);
            item7.setTextColor(Color.parseColor("#000000"));
            item7.setText(emp.toStringDate());
            tr.addView(item7);

            TextView item1=new TextView(this);
            item1.setPadding(15,15,15,15);
            item1.setTextColor(Color.parseColor("#000000"));
            item1.setText(emp.toStringBesoin());
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

            tr.addView(item2);

            TextView item3=new TextView(this);
            item3.setPadding(15,15,15,15);
            item3.setTextColor(Color.parseColor("#000000"));
            item3.setText(String.valueOf(emp.toStringAutreP()));
            tr.addView(item3);

            TextView item4=new TextView(this);
            item4.setPadding(15,15,15,15);
            item4.setTextColor(Color.parseColor("#000000"));
            item4.setText(String.valueOf(emp.toStringQt()));
            tr.addView(item4);

            TextView item5=new TextView(this);
            item5.setPadding(15,15,15,15);
            item5.setTextColor(Color.parseColor("#000000"));
            item5.setText(emp.toStringNomEm());
            tr.addView(item5);




            tableLayout.addView(tr,new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            count++;
        }
        TextView nbligne=(TextView)findViewById(R.id.ligneSor);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.deconnexion:
                mAuth.signOut();
                finish();
                startActivity(new Intent(this, Authentification.class));
                return true;
            case R.id.rechercher:
               // startActivity(new Intent(this, CoutTotalBesoin.class));
               Intent intent=new Intent(ListeDesSorties.this,CoutTotalBesoin.class);
               intent.putExtra("recherche","Rsortie");
               startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}