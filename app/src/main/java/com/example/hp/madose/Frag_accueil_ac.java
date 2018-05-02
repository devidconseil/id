package com.example.hp.madose;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.madose.Acceuil;

import com.example.hp.madose.Listes.ListeDemandeUtilisateur;
import com.example.hp.madose.Listes.ListeRupture;
import com.example.hp.madose.Listes.StockEgale;
import com.example.hp.madose.Listes.StockSup;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by erick on 20/03/2018.
 */

public class Frag_accueil_ac extends Fragment {



ConnexionDetector connexionDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_accueil, container, false);

        BaseDeDonne baseDeDonne=new BaseDeDonne(getContext());

       /* connexionDetector=new ConnexionDetector(getContext());
        int eric=0;
        if (connexionDetector.isConnected())
        {
            for (eric=0;eric<=9;eric++)
            {
                Toast.makeText(getContext(),String.valueOf(eric),Toast.LENGTH_SHORT).show();
            }
        }*/

        TextView tsup=(TextView)rootView.findViewById(R.id.sup);
        TextView tegale=(TextView)rootView.findViewById(R.id.egale);
        TextView tinf=(TextView)rootView.findViewById(R.id.inferieur);
        TextView ts=(TextView)rootView.findViewById(R.id.ts);
        TextView te=(TextView)rootView.findViewById(R.id.te);
        TextView ti=(TextView)rootView.findViewById(R.id.ti);

        Button sup =(Button)rootView.findViewById(R.id.butsup);
        Button egale =(Button)rootView.findViewById(R.id.butegale);
        Button inf =(Button)rootView.findViewById(R.id.butinfe);

        List<RuptureC> x=baseDeDonne.CountRup();
        for (RuptureC emp : x) {
            ti.append(emp.toStringCount());
        }
        inf.setText(ti.getText());

        List<RuptureC> y=baseDeDonne.CountSup();
        for (RuptureC emp : y) {
            ts.append(emp.toStringCount());
        }
        sup.setText(ts.getText());

        List<RuptureC> z=baseDeDonne.CountEg();
        for (RuptureC emp : z) {
            te.append(emp.toStringCount());
        }
        egale.setText(te.getText());

        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),StockSup.class);
                startActivity(intent);

            }
        });
        tsup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),StockSup.class);
                startActivity(intent);
            }
        });
        egale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),StockEgale.class);
                startActivity(intent);
            }
        });
        tegale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),StockEgale.class);
                startActivity(intent);
            }
        });
        inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ListeDemandeUtilisateur.class);
                startActivity(intent);
            }
        });
        tinf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ListeRupture.class);
                startActivity(intent);
            }
        });

        int total=Integer.parseInt(ts.getText().toString())+Integer.parseInt(te.getText().toString())+Integer.parseInt(ti.getText().toString());
        int supe=Integer.parseInt(ts.getText().toString())*100/total;
        int eg=Integer.parseInt(te.getText().toString())*100/total;
        int in=Integer.parseInt(ti.getText().toString())*100/total;
        int rainfall[]={Integer.parseInt(ts.getText().toString()),Integer.parseInt(te.getText().toString()),Integer.parseInt(ti.getText().toString())};
        String mois[]={supe+"%",eg+"%",in+"%"};
        List<PieEntry> pieEntries=new ArrayList<>();
        for (int i=0;i<rainfall.length;i++)
        {
            pieEntries.add(new PieEntry(rainfall[i],mois[i]));
        }
        PieDataSet dataSet=new PieDataSet(pieEntries,"");
        dataSet.setValueTextSize(0);
        dataSet.setValueLineColor(Color.parseColor("#ffffff"));
        //dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setColors(new int[] { Color.parseColor("#369936"), Color.parseColor("#FF9801"), Color.parseColor("#E10F0C")});
        PieData data=new PieData(dataSet);



        // to get chart
        PieChart chart=rootView.findViewById(R.id.chart);
        chart.setData(data);

        chart.animateY(1000);
        chart.invalidate();

        return rootView;
    }


}
