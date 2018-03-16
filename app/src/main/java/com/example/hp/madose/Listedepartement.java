package com.example.hp.madose;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Listedepartement extends AppCompatActivity {
    BaseDeDonne bd=new BaseDeDonne(this);
    List<String> liste=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listedepartement);
        BaseDeDonne bd=new BaseDeDonne(this);


        final List<DepartementC> departem= bd.afficheDepart();
        for (DepartementC departementC : departem){
            liste.add(departementC.toString().substring(14));
        }
        ListAdapter departe=new ArrayAdapter<DepartementC>(this, android.R.layout.simple_list_item_1, departem);
        ListView affiche=(ListView)findViewById(R.id.listeview);


        final ListeDepart listeDepart1=new ListeDepart(this,liste);
        affiche.setAdapter(listeDepart1);

        if (getIntent().getStringExtra("code").equals("sortie1"))
        {
            Intent intent = getIntent();
            final String varDate = intent.getStringExtra("bringDate");
            final String var1 = intent.getStringExtra("bringDemande");
            final String var2 = intent.getStringExtra("bringEmp");
            final String var3 = intent.getStringExtra("bringBesoin");
            final String var4 = intent.getStringExtra("bringMarque");
            final String var5 = intent.getStringExtra("bringQuantité");
            final String var6 = intent.getStringExtra("bringAutre");
            final Boolean var7=intent.getBooleanExtra("bringRadDep",true);
            final Boolean var8=intent.getBooleanExtra("bringRadEmp",false);
            final int var9=intent.getIntExtra("bringEmpVis",View.INVISIBLE);
            final int var10=intent.getIntExtra("bringDepVis",View.VISIBLE);

            affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String depart= String.valueOf(parent.getItemIdAtPosition(position));
                    int var=Integer.parseInt(depart);


                    Intent intent = new Intent(Listedepartement.this, BringOut.class);
                    String variable = liste.get(position);
                    // intent.putExtra("code","utilisateur");
                    intent.putExtra("bringDe", variable);
                    intent.putExtra("bringD", varDate);
                    intent.putExtra("bringDem", var1);
                    intent.putExtra("bringEmp", var2);
                    intent.putExtra("bringB", var3);
                    intent.putExtra("bringM", var4);
                    intent.putExtra("bringQ", var5);
                    intent.putExtra("bringA", var6);
                    intent.putExtra("etat1",var7);
                    intent.putExtra("etat2",var8);
                    intent.putExtra("etat3",var9);
                    intent.putExtra("etat4",var10);
                    intent.putExtra("code", "listeD");
                    startActivity(intent);
                    finish();
                }
            });

        }
        else if (getIntent().getStringExtra("code").equals("utilisateur")){
            Intent intent = getIntent();
            final String var2 = intent.getStringExtra("employer");
            final String var1 = intent.getStringExtra("employerr");
            final String var3 = intent.getStringExtra("employerrr");
            final String var4 = intent.getStringExtra("employerrrr");
            final String var5 = intent.getStringExtra("employerrrrr");


            affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String depart = String.valueOf(parent.getItemIdAtPosition(position));
                    int var = Integer.parseInt(depart);


                    Intent intent = new Intent(Listedepartement.this, Utilisateur.class);
                    String variable = liste.get(position);
                    intent.putExtra("departement", variable);
                    intent.putExtra("employer", var2);
                    intent.putExtra("employerr", var1);
                    intent.putExtra("employerrr", var3);
                    intent.putExtra("employerrrr", var4);
                    intent.putExtra("employerrrrr", var5);
                    startActivity(intent);
                    finish();
                }
            });
        }
        else  if (getIntent().getStringExtra("code").equals("demande")) {
            final String varDate = getIntent().getStringExtra("demDate");
            final String var1 = getIntent().getStringExtra("demBesoin");
            final String var2 = getIntent().getStringExtra("demQuantité");
            final Boolean var7 = getIntent().getBooleanExtra("bringRadDep", true);
            final Boolean var8 = getIntent().getBooleanExtra("bringRadEmp", false);
            final int var9 = getIntent().getIntExtra("bringEmpVis", View.INVISIBLE);
            final int var10 = getIntent().getIntExtra("bringDepVis", View.VISIBLE);


            affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String depart = String.valueOf(parent.getItemIdAtPosition(position));
                    int varr = Integer.parseInt(depart);


                    Intent intent = new Intent(Listedepartement.this, Demande.class);
                    String variable = liste.get(position);
                    // intent.putExtra("code","utilisateur");
                    intent.putExtra("demDe", variable);
                    intent.putExtra("demD", varDate);
                    intent.putExtra("demB", var1);
                    intent.putExtra("demQ", var2);
                    intent.putExtra("etat1", var7);
                    intent.putExtra("etat2", var8);
                    intent.putExtra("etat3", var9);
                    intent.putExtra("etat4", var10);
                    intent.putExtra("code", "listeDD");
                    startActivity(intent);
                    finish();
                }
            });


        }

    }

        class ListeDepart extends BaseAdapter
        {
            Context context;
            List<String> listee=new ArrayList<>();

            public ListeDepart(Context context,List<String> listee) {
               this.context=context;
                this.listee = listee;
            }

            @Override
            public int getCount() {
                return listee.size();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                convertView=getLayoutInflater().inflate(R.layout.activity_listedepartementimage,null);

                TextView textView=(TextView)convertView.findViewById(R.id.textView6);

                textView.setText(listee.get(position));

                return convertView;
            }
        }
}
