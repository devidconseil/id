package com.example.hp.madose.Listes;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.hp.madose.BaseDeDonne;
import com.example.hp.madose.DemandeC;
import com.example.hp.madose.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;


public class ListeDemandeUtilisateur extends AppCompatActivity {
    List<String> liste=new ArrayList<>();
    BaseDeDonne bd;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    ProgressDialog mProgressDialog;
    TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_demande_utilisateur);
        bd=new BaseDeDonne(this);
        tableLayout=(TableLayout)findViewById(R.id.useraffiche);
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
        //tl.addView(num);

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
        prent.setText("QUANTITE");
        prent.setPadding(15,15,15,15);
        tl.addView(prent);

        TextView autre=new TextView(this);
        autre.setTypeface(null, Typeface.BOLD);
        autre.setTextColor(Color.parseColor("#FFFFFF"));
        autre.setText("DEMANDE PAR");
        autre.setPadding(15,15,15,15);
        tl.addView(autre);

        TextView depart=new TextView(getBaseContext());
        depart.setTypeface(null, Typeface.BOLD);
        depart.setTextColor(Color.parseColor("#FFFFFF"));
        depart.setText("DEPARTEMENT");
        depart.setPadding(15,15,15,15);
        //tl.addView(depart);

        tableLayout.addView(tl,new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

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
            //tr.addView(item1);

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
            item5.setText(emp.toStringDepa());
            tr.addView(item5);

            TextView item6 = new TextView(this);
            item6.setPadding(15, 15, 15, 15);
            item6.setTextColor(Color.parseColor("#000000"));
            item6.setText(emp.toStringDepa());
            //tr.addView(item6);

            tableLayout.addView(tr, new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            count++;
        }
    }
}
