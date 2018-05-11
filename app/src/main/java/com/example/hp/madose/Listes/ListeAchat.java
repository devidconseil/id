package com.example.hp.madose.Listes;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.hp.madose.BaseDeDonne;
import com.example.hp.madose.Frag_accueil_listes;
import com.example.hp.madose.ListAchatC;
import com.example.hp.madose.R;

import java.util.ArrayList;
import java.util.List;

public class ListeAchat extends AppCompatActivity {

    List<String> liste=new ArrayList<>();
    TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_achat);

        BaseDeDonne bd=new BaseDeDonne(this);
        tableLayout=(TableLayout)findViewById(R.id.tableauA);
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
        num.setText("Besoin");
        num.setPadding(15,15,15,15);
        tl.addView(num);

        TextView date=new TextView(this);
        date.setTypeface(null, Typeface.BOLD);
        date.setTextColor(Color.parseColor("#FFFFFF"));
        date.setText("Quantité");
        date.setPadding(15,15,15,15);
        tl.addView(date);
        tableLayout.addView(tl,new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        List<ListAchatC> affF = bd.ListAchat();
        int count=0;
        for (ListAchatC emp : affF) {

            TableRow tr = new TableRow(this);
            tr.setPadding(12, 16, 12, 16);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tr.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }
            if (count % 2 != 0) tr.setBackgroundColor(Color.parseColor("#d1d2d2"));

            String a,b,c;
            a=bd.selectStockBes(String.valueOf(emp.toStringMat()));
            b=bd.selectStockBesDemande(String.valueOf(emp.toStringMat()));
            c=bd.selectStockBesSortie(String.valueOf(emp.toStringMat()));
            if (b==null){
                b="0";
            }
            if (c==null){
                c="0";
            }
            int d=Integer.parseInt(b)-Integer.parseInt(a)-Integer.parseInt(c);

            TextView item8 = new TextView(this);
            item8.setPadding(15, 15, 15, 15);
            item8.setTextColor(Color.parseColor("#000000"));
            item8.setText(String.valueOf(emp.toStringMat()));
            //tr.addView(item8);

            TextView item7 = new TextView(this);
            item7.setPadding(15, 15, 15, 15);
            item7.setTextColor(Color.parseColor("#000000"));
            item7.setText(String.valueOf(d));
           // item7.setText(String.valueOf(emp.toStringQt()));
            if (d>0){
                tr.addView(item8);
                tr.addView(item7);
                count++;
            }


            tableLayout.addView(tr,new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
           // count++;
        }
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
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
