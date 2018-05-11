package com.example.hp.madose.Listes;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.madose.BaseDeDonne;
import com.example.hp.madose.CoutC;
import com.example.hp.madose.Frag_accueil_listes;
import com.example.hp.madose.MyApplication;
import com.example.hp.madose.R;

import java.util.ArrayList;
import java.util.List;

public class CoutTotalB extends AppCompatActivity {

    List<String> liste=new ArrayList<>();
    TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cout_total_b);

        BaseDeDonne bd=new BaseDeDonne(this);
        tableLayout=(TableLayout)findViewById(R.id.total);
        tableLayout.setPadding(12,16,12,16);
        TableRow tl=new TableRow(this);
        tl.setBackgroundColor(Color.parseColor("#17631E"));
        tl.setPadding(12,16,12,16);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tl.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }
        TextView nomt=new TextView(this);
        nomt.setText("Nro ENTREE");
        nomt.setTextColor(Color.parseColor("#FFFFFF"));
        nomt.setTypeface(null, Typeface.BOLD);
        nomt.setPadding(15,15,15,15);
        tl.addView(nomt);
        TextView prent=new TextView(this);
        prent.setTypeface(null, Typeface.BOLD);
        prent.setTextColor(Color.parseColor("#FFFFFF"));
        prent.setText("MATERIELS");
        prent.setPadding(15,15,15,15);
        tl.addView(prent);
        TextView mailt=new TextView(this);
        mailt.setTypeface(null, Typeface.BOLD);
        mailt.setTextColor(Color.parseColor("#FFFFFF"));
        mailt.setText("COUT");
        mailt.setPadding(15,15,15,15);
        tl.addView(mailt);
        TextView qt=new TextView(this);
        qt.setTypeface(null, Typeface.BOLD);
        qt.setTextColor(Color.parseColor("#FFFFFF"));
        qt.setText("QUANTITE");
        qt.setPadding(15,15,15,15);
        tl.addView(qt);
        tableLayout.addView(tl,new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        List<CoutC> affF = bd.CoutBesoin(MyApplication.getGlobalVarValue());
        int count=0,x=0,y=0,z=0;

        for (CoutC emp : affF) {

            TableRow tr=new TableRow(this);
            tr.setPadding(12,16,12,16);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tr.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }
            if (count %2!=0) tr.setBackgroundColor(Color.parseColor("#d1d2d2"));
            TextView nro=new TextView(this);
            nro.setPadding(15,15,15,15);
            nro.setTextColor(Color.parseColor("#000000"));
            nro.setText(String.valueOf(emp.toStringNro()));
            tr.addView(nro);
            TextView lib =new TextView(this);
            lib.setTextColor(Color.parseColor("#000000"));
            lib.setPadding(15,15,15,15);
            lib.setText(emp.toStringBes());
            tr.addView(lib );
            TextView cout =new TextView(this);
            cout.setTextColor(Color.parseColor("#17631E"));
            cout.setPadding(15,15,15,15);
            cout.setText(separateurDemollier(String.valueOf(emp.toStringCout())));
         //   Toast.makeText(getBaseContext(),String.valueOf(emp.toStringCout()),Toast.LENGTH_LONG).show();
            tr.addView(cout);
            TextView qte =new TextView(this);
            qte.setTextColor(Color.parseColor("#17631E"));
            qte.setPadding(15,15,15,15);
            qte.setText(String.valueOf(emp.toStringqt()));
            tr.addView(qte);
            tableLayout.addView(tr,new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            count++;
        }

        TextView total=(TextView)findViewById(R.id.totall);
        total.setText("Montant total: "+separateurDemollier(String.valueOf(MyApplication.getCoutTotBes())));

        @SuppressLint("ResourceType") AlertDialog.Builder builder = new AlertDialog.Builder(CoutTotalB.this,0x00000005);
        builder.setMessage("Le coût total en approvisionnement du besoin "+MyApplication.getGlobalVarValue()+" est "+separateurDemollier(String.valueOf(MyApplication.getCoutTotBes()))+" FCFA.");
        builder.setTitle(R.string.dialog_title);
        builder.setPositiveButton("Voir plus", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


            }
        });
        builder.create();
        builder.show();
    }
    public String separateurDemollier(String chiffre)
    {
        String a=null,b=null,x=null,y=null;
        String z = null;

       if (chiffre.toString().length()==4)
        {
            x=chiffre.substring(0,1);
            y=chiffre.substring(1,4);
            z=String.valueOf(x+" "+y);
        }
        else if (chiffre.length()==5)
        {
            x=chiffre.substring(0,2);
            y=chiffre.substring(2,5);
            z=String.valueOf(x+" "+y);

        }
        else if (chiffre.length()==6)
        {
            x=chiffre.substring(0,3);
            y=chiffre.substring(3,6);
            z=String.valueOf(x+" "+y);
      }
        else if (chiffre.length()==7)
       {
           a=chiffre.substring(0,1);
           x=chiffre.substring(1,4);
           y=chiffre.substring(4,7);
           z=String.valueOf(a+" "+x+" "+y);

       }
       else if (chiffre.length()==8)
       {

           a=chiffre.substring(0,2);
           x=chiffre.substring(2,5);
           y=chiffre.substring(5,8);
           z=String.valueOf(a+" "+x+" "+y);

       }
       else if (chiffre.length()>=3)
       {
           z=String.valueOf(chiffre);
       }

        return z;
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Frag_accueil_listes fragment = new Frag_accueil_listes();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(fragment,"fragment");
        transaction.commit();

    }
}
