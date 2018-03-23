package com.example.hp.madose.Listes;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.hp.madose.BaseDeDonne;
import com.example.hp.madose.R;
import com.example.hp.madose.RuptureC;

import java.util.ArrayList;
import java.util.List;

public class StockSup extends AppCompatActivity {
    List<String> liste=new ArrayList<>();
    TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_sup);
        BaseDeDonne bd=new BaseDeDonne(this);
        tableLayout=(TableLayout)findViewById(R.id.tableauS);
        tableLayout.setPadding(12,16,12,16);
        TableRow tl=new TableRow(this);
        tl.setBackgroundColor(Color.parseColor("#17631E"));
        tl.setPadding(12,16,12,16);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tl.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }
        TextView nomt=new TextView(this);
        nomt.setText("BESOIN");
        nomt.setTextColor(Color.parseColor("#FFFFFF"));
        nomt.setTypeface(null, Typeface.BOLD);
        nomt.setPadding(15,15,15,15);
        tl.addView(nomt);
        TextView prent=new TextView(this);
        prent.setTypeface(null, Typeface.BOLD);
        prent.setTextColor(Color.parseColor("#FFFFFF"));
        prent.setText("SEUIL");
        prent.setPadding(15,15,15,15);
        tl.addView(prent);
        TextView mailt=new TextView(this);
        mailt.setTypeface(null, Typeface.BOLD);
        mailt.setTextColor(Color.parseColor("#FFFFFF"));
        mailt.setText("STOCK");
        mailt.setPadding(15,15,15,15);
        tl.addView(mailt);
        tableLayout.addView(tl,new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        List<RuptureC> affF = bd.StockSup();
        int count=0;
        for (RuptureC emp : affF) {




            TableRow tr=new TableRow(this);
            tr.setPadding(12,16,12,16);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tr.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }
            if (count %2!=0) tr.setBackgroundColor(Color.parseColor("#d1d2d2"));
            TextView nomBes=new TextView(this);
            nomBes.setPadding(15,15,15,15);
            nomBes.setTextColor(Color.parseColor("#000000"));
            nomBes.setText(emp.toStringBesoin());
            tr.addView(nomBes);
            TextView seuilBes =new TextView(this);
            seuilBes.setTextColor(Color.parseColor("#000000"));
            seuilBes.setPadding(15,15,15,15);
            seuilBes.setText(String.valueOf(emp.toStringSeuil()));
            tr.addView(seuilBes );
            TextView stockBes =new TextView(this);
            stockBes.setTextColor(Color.parseColor("#17631E"));
            stockBes.setPadding(15,15,15,15);
            stockBes.setText(String.valueOf(emp.toStringStock()));
            tr.addView(stockBes);
            tableLayout.addView(tr,new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            count++;
        }

    }
}
