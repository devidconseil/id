package com.example.hp.madose.Listes;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListeDesDemandes extends AppCompatActivity {
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
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    ProgressDialog mProgressDialog;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_des_demandes);
        final BaseDeDonne bd=new BaseDeDonne(this);
    //**********relier avec la base de données distante
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mAuth= FirebaseAuth.getInstance();
        showProgressDialog();

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

                            NotificationCompat.Builder notification=new NotificationCompat.Builder(getBaseContext());
                            notification.setSmallIcon(R.mipmap.logorecap);
                            notification.setContentText("Demande(s) ajoutées");
                            notification.setContentTitle("Demande");

                            Intent listedemande=new Intent(getBaseContext(),ListeDesDemandes.class);
                            TaskStackBuilder stackBuilder=TaskStackBuilder.create(getBaseContext());
                            stackBuilder.addParentStack(ListeDesDemandes.class);
                            stackBuilder.addNextIntent(listedemande);
                            PendingIntent resultIntent= stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                            notification.setContentIntent(resultIntent);
                            notification.setAutoCancel(true);
                            NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                            Random random=new Random();
                            notificationManager.notify(random.nextInt(130000),notification.build());

                        }
                        if (cat.getNomEmp().equals("")) {
                            int ss=0;
                            int sss=Integer.parseInt(bd.selectDep(cat.getLibDpe()));
                            bd.insertDemande1(cat.getDateDem(),sss,cat.getHeureDem(),false);
                            bd.insertDemandeBesoin(Integer.parseInt(bd.selectIdDem(cat.getHeureDem())),ssss,cat.getQte());


                        }
                        count7=true;

                    }
                }
                if (count7){
                    finish();
                    showProgressDialog();
                    Intent intent=new Intent(ListeDesDemandes.this,ListeDesDemandes.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    //intent.putExtra("passage","affichage2");
                    intent.putExtra("sortie","listeD");
                    startActivity(intent);
                    hideProgressDialog();
                    count7=false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        hideProgressDialog();
        //fin 000000000000000000000000000000000000000000000000000000000000000000000000000


        tableLayout=(TableLayout)findViewById(R.id.tableauLD);
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
        num.setText("Num.");
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
        prent.setText("Qt");
        prent.setPadding(15,15,15,15);
        tl.addView(prent);

        TextView autre=new TextView(this);
        autre.setTypeface(null, Typeface.BOLD);
        autre.setTextColor(Color.parseColor("#FFFFFF"));
        autre.setText("DEMANDE PAR");
        autre.setPadding(15,15,15,15);
        tl.addView(autre);

       /* TextView qt=new TextView(this);
        qt.setTypeface(null, Typeface.BOLD);
        qt.setTextColor(Color.parseColor("#FFFFFF"));
        qt.setText("DEPARTEMENT");
        qt.setPadding(15,15,15,15);
        tl.addView(qt);*/

        tableLayout.addView(tl,new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));



        String profile=bd.retrieveUserProfile(mAuth.getCurrentUser().getEmail());

        if (profile.equals("SUPER ADMIN")) {

            List<DemandeC> affF =null;
            if(getIntent().getStringExtra("sortie").equals("libelle"))
            {
                //pour recherche
                affF = bd.afficheDemandeR(getIntent().getStringExtra("libelle"));
            }
            else if (getIntent().getStringExtra("sortie").equals("listeD"))
            {
                affF =bd.afficheDemande();
            }


            //List<DemandeC> affF = bd.afficheDemande();
            int count = 0;
            for (DemandeC emp : affF) {

                TableRow tr = new TableRow(this);
                tr.setPadding(12, 16, 12, 16);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tr.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                }
                if (count % 2 != 0) tr.setBackgroundColor(Color.parseColor("#d1d2d2"));

                TextView item1 = new TextView(this);
                item1.setPadding(15, 15, 15, 15);
                item1.setTextColor(Color.parseColor("#000000"));
                item1.setText(String.valueOf(emp.toStringNum()));
                tr.addView(item1);

                TextView item2 = new TextView(this);
                item2.setPadding(15, 15, 15, 15);
                item2.setTextColor(Color.parseColor("#000000"));
                item2.setText(String.valueOf(emp.toStringDate()));
                tr.addView(item2);

                TextView item3 = new TextView(this);
                item3.setPadding(15, 15, 15, 15);
                item3.setTextColor(Color.parseColor("#000000"));
                item3.setText(emp.toStringLib());
                tr.addView(item3);

                TextView item4 = new TextView(this);
                item4.setPadding(15, 15, 15, 15);
                item4.setTextColor(Color.parseColor("#000000"));
                item4.setText(String.valueOf(emp.toStringQt()));
                tr.addView(item4);

                TextView item5 = new TextView(this);
                item5.setPadding(15, 15, 15, 15);
                item5.setTextColor(Color.parseColor("#000000"));
                item5.setText(emp.toStringNomEmp());
                tr.addView(item5);

               /* TextView item6 = new TextView(this);
                item6.setPadding(15, 15, 15, 15);
                item6.setTextColor(Color.parseColor("#000000"));
                item6.setText(emp.toStringDepa());
                tr.addView(item6);*/


                tableLayout.addView(tr, new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                count++;
            }
            TextView nbligne=(TextView)findViewById(R.id.ligneEn);
            nbligne.setText(""+count+" ligne(s)");
        }
            if (profile.equals("USER")) {

               /* List<DemandeC> affFF =null;
              if(getIntent().getStringExtra("sortie").equals("libelle"))
        {
            affFF = bd.afficheDemandeUserL(mAuth.getCurrentUser().getEmail(),getIntent().getStringExtra("libelle"));
        }
        else
        {
            affF =bd.afficheDemandeUser(mAuth.getCurrentUser().getEmail());
        }*/
                List<DemandeC> affF = bd.afficheDemandeUser(mAuth.getCurrentUser().getEmail());
                int count = 0;
                for (DemandeC emp : affF) {

                    TableRow tr = new TableRow(this);
                    tr.setPadding(12, 16, 12, 16);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        tr.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    }
                    if (count % 2 != 0) tr.setBackgroundColor(Color.parseColor("#d1d2d2"));

                    TextView item1 = new TextView(this);
                    item1.setPadding(15, 15, 15, 15);
                    item1.setTextColor(Color.parseColor("#000000"));
                    item1.setText(String.valueOf(emp.toStringNum()));
                    tr.addView(item1);

                    TextView item2 = new TextView(this);
                    item2.setPadding(15, 15, 15, 15);
                    item2.setTextColor(Color.parseColor("#000000"));
                    item2.setText(String.valueOf(emp.toStringDate()));
                    tr.addView(item2);

                    TextView item3 = new TextView(this);
                    item3.setPadding(15, 15, 15, 15);
                    item3.setTextColor(Color.parseColor("#000000"));
                    item3.setText(emp.toStringLib());
                    tr.addView(item3);

                    TextView item4 = new TextView(this);
                    item4.setPadding(15, 15, 15, 15);
                    item4.setTextColor(Color.parseColor("#000000"));
                    item4.setText(String.valueOf(emp.toStringQt()));
                    tr.addView(item4);

                    TextView item5 = new TextView(this);
                    item5.setPadding(15, 15, 15, 15);
                    item5.setTextColor(Color.parseColor("#000000"));
                    item5.setText(emp.toStringNomEmp());
                    tr.addView(item5);

                    TextView item6 = new TextView(this);
                    item6.setPadding(15, 15, 15, 15);
                    item6.setTextColor(Color.parseColor("#000000"));
                    item6.setText(emp.toStringDepa());
                    tr.addView(item6);


                    tableLayout.addView(tr, new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    count++;
                }
            }

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
                //startActivity(new Intent(this, CoutTotalBesoin.class));
                Intent intent=new Intent(ListeDesDemandes.this,CoutTotalBesoin.class);
                intent.putExtra("recherche","Rdemande");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
