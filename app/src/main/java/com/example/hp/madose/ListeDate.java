package com.example.hp.madose;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListeDate extends AppCompatActivity {
    BaseDeDonne bd=new BaseDeDonne(this);
    List<String> liste=new ArrayList<>();
    ArrayList<String> au=new ArrayList<>();
    String var;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_date);

        BaseDeDonne bd=new BaseDeDonne(this);
        AutoCompleteTextView nameUser= findViewById(R.id.autoEmp);

        if (getIntent().getStringExtra("bringO").equals("sortie2")) {

            Intent intent = getIntent();

            final String varDate = intent.getStringExtra("bringDate");
            final String var1 = intent.getStringExtra("bringEmploye");
            final String var2 = intent.getStringExtra("bringDepartement");
            final String var3 = intent.getStringExtra("bringBesoin");
            final String var4 = intent.getStringExtra("bringMarque");
            final String var5 = intent.getStringExtra("bringQuantité");
            final String var6 = intent.getStringExtra("bringAutre");
            final Boolean var7=intent.getBooleanExtra("bringRadDep",false);
            final Boolean var8=intent.getBooleanExtra("bringRadEmp",true);
            final int var9=intent.getIntExtra("bringEmpVis",View.VISIBLE);
            final int var10=intent.getIntExtra("bringDepVis",View.INVISIBLE);


            String idUser= bd.selectIdEmp(MyApplication.getEmploye());
            final List<String> dateDemUser = bd.affiNumDem(Integer.parseInt(idUser));
            for (String caractere : dateDemUser) {
                liste.add(caractere);
            }
            ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dateDemUser);
            ListView affiche = (ListView) findViewById(R.id.listD);

            final ListeDat listeDate = new ListeDat(this, liste);
            affiche.setAdapter(listAdapter);

            affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String depart = String.valueOf(parent.getItemIdAtPosition(position));
                    int varr = Integer.parseInt(depart);


                    Intent intent = new Intent(ListeDate.this, BringOut.class);
                    String variable = liste.get(position);
                    // intent.putExtra("code","utilisateur");
                    intent.putExtra("bringDem", variable);
                    intent.putExtra("bringD", varDate);
                    intent.putExtra("bringE", var1);
                    intent.putExtra("bringDe", var2);
                    intent.putExtra("bringB", var3);
                    intent.putExtra("bringM", var4);
                    intent.putExtra("bringQ", var5);
                    intent.putExtra("bringA", var6);
                    intent.putExtra("etat1",var7);
                    intent.putExtra("etat2",var8);
                    intent.putExtra("etat3",var9);
                    intent.putExtra("etat4",var10);
                    intent.putExtra("code", "listeDa");
                    startActivity(intent);
                    finish();
                }
            });
        }
        if (getIntent().getStringExtra("bringO").equals("sortie1")) {

            Intent intent = getIntent();

            final String varDate = intent.getStringExtra("bringDate");
            final String var1 = intent.getStringExtra("bringEmploye");
            final String var2 = intent.getStringExtra("bringDepartement");
            final String var3 = intent.getStringExtra("bringBesoin");
            final String var4 = intent.getStringExtra("bringMarque");
            final String var5 = intent.getStringExtra("bringQuantité");
            final String var6 = intent.getStringExtra("bringAutre");
            final Boolean var7=intent.getBooleanExtra("bringRadDep",false);
            final Boolean var8=intent.getBooleanExtra("bringRadEmp",true);
            final int var9=intent.getIntExtra("bringEmpVis",View.INVISIBLE);
            final int var10=intent.getIntExtra("bringDepVis",View.VISIBLE);


            String idDep= bd.selectDep(var2);
            final List<String> dateDemUser = bd.affiNumDem11(Integer.parseInt(idDep));
            for (String caractere : dateDemUser) {
                liste.add(caractere);
            }
            ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dateDemUser);
            ListView affiche = (ListView) findViewById(R.id.listD);

            final ListeDat listeDate = new ListeDat(this, liste);
            affiche.setAdapter(listAdapter);

            affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String depart = String.valueOf(parent.getItemIdAtPosition(position));
                    int varr = Integer.parseInt(depart);


                    Intent intent = new Intent(ListeDate.this, BringOut.class);
                    String variable = liste.get(position);
                    // intent.putExtra("code","utilisateur");
                    intent.putExtra("bringDem", variable);
                    intent.putExtra("bringD", varDate);
                    intent.putExtra("bringE", var1);
                    intent.putExtra("bringDe", var2);
                    intent.putExtra("bringB", var3);
                    intent.putExtra("bringM", var4);
                    intent.putExtra("bringQ", var5);
                    intent.putExtra("bringA", var6);
                    intent.putExtra("etat1",var7);
                    intent.putExtra("etat2",var8);
                    intent.putExtra("etat3",var9);
                    intent.putExtra("etat4",var10);
                    intent.putExtra("code", "listeDa");
                    startActivity(intent);
                    finish();
                }
            });
        }




    }
    class ListeDat extends BaseAdapter
    {
        Context context;
        List<String> listee=new ArrayList<>();

        public ListeDat(Context context,List<String> listee) {
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

            convertView=getLayoutInflater().inflate(R.layout.activity_liste_utilisateurimage,null);

            TextView textView=(TextView)convertView.findViewById(R.id.textUser);

            textView.setText(listee.get(position));

            return convertView;
        }
    }

}
