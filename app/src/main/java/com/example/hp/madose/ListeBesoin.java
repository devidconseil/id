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

public class ListeBesoin extends AppCompatActivity {
    BaseDeDonne bd=new BaseDeDonne(this);
    List<String> liste=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_besoin);

        BaseDeDonne bd=new BaseDeDonne(this);
        final List<BesoinC> besoin= bd.afficheLB();
        for (BesoinC besoinC: besoin){
            liste.add(besoinC.libelleBesoin());
        }
        ListAdapter departe=new ArrayAdapter<BesoinC>(this, android.R.layout.simple_list_item_1, besoin);
        ListView affiche=(ListView)findViewById(R.id.listB);


        final ListeBes listeBes=new ListeBes(this,liste);
        affiche.setAdapter(listeBes);

        Intent intent = getIntent();
        final String varDate = intent.getStringExtra("bringDate");
        final String var1 = intent.getStringExtra("bringDemande");
        final String var2 = intent.getStringExtra("bringDepartement");
        final String var3 = intent.getStringExtra("bringEmp");
        final String var4 = intent.getStringExtra("bringMarque");
        final String var5 = intent.getStringExtra("bringQuantité");
        final String var6 = intent.getStringExtra("bringAutre");
        final Boolean var7=intent.getBooleanExtra("bringRadDep",false);
        final Boolean var8=intent.getBooleanExtra("bringRadEmp",true);
        final int var9=intent.getIntExtra("bringEmpVis",View.VISIBLE);
        final int var10=intent.getIntExtra("bringDepVis",View.INVISIBLE);


        affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String depart = String.valueOf(parent.getItemIdAtPosition(position));
                int varr = Integer.parseInt(depart);


                Intent intent = new Intent(ListeBesoin.this, BringOut.class);
                String variable = liste.get(position);
                // intent.putExtra("code","utilisateur");
                intent.putExtra("employe", variable);
                intent.putExtra("bringD", varDate);
                intent.putExtra("bringDem", var1);
                intent.putExtra("bringDe", var2);
                intent.putExtra("bringB", var3);
                intent.putExtra("bringM", var4);
                intent.putExtra("bringQ", var5);
                intent.putExtra("bringA", var6);
                intent.putExtra("etat1",var7);
                intent.putExtra("etat2",var8);
                intent.putExtra("etat3",var9);
                intent.putExtra("etat4",var10);
                intent.putExtra("code", "listeU");
                startActivity(intent);
                finish();
            }
        });

    }


    class ListeBes extends BaseAdapter
    {
        Context context;
        List<String> listee=new ArrayList<>();

        public ListeBes(Context context,List<String> listee) {
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
