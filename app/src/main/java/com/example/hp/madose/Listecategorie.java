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

public class Listecategorie extends AppCompatActivity {
    BaseDeDonne bd=new BaseDeDonne(this);
    List<String> liste=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listecategorie);

        BaseDeDonne bd=new BaseDeDonne(this);

        final List<CategorieC> departem= bd.afficheCat();
        for (CategorieC categorieC : departem){
            liste.add(categorieC.nomcat());
        }
        ListAdapter departe=new ArrayAdapter<CategorieC>(this, android.R.layout.simple_list_item_1, departem);
        ListView affiche=(ListView)findViewById(R.id.listcategorie);


        final Listecategorie.ListeDepart listeDepart1=new Listecategorie.ListeDepart(this,liste);
        affiche.setAdapter(listeDepart1);

        Intent intent=getIntent();
        if (intent.getStringExtra("code").equals("nonAm"))
        {
        final String var2=intent.getStringExtra("blibe");
        final String var3=intent.getStringExtra("bseuil");
        final String var4=intent.getStringExtra("bperemp");
        final String var44=intent.getStringExtra("bstock");
        final Boolean var5=intent.getBooleanExtra("etat1",true);
        final Boolean var6=intent.getBooleanExtra("etat2",false);
        final int var7=intent.getIntExtra("etat3",View.VISIBLE);
        final int var8=intent.getIntExtra("etat4",View.INVISIBLE);



        affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String depart= String.valueOf(parent.getItemIdAtPosition(position));
                int var=Integer.parseInt(depart);

                Intent intent=new Intent(Listecategorie.this,Besoin.class);
                String variable=liste.get(position);
                intent.putExtra("libCat",var2);
                intent.putExtra("seuil",var3);
                intent.putExtra("peremption",var4);
                intent.putExtra("stock",var44);
                intent.putExtra("betat1",var5);
                intent.putExtra("betat2",var6);
                intent.putExtra("betat3",var7);
                intent.putExtra("betat4",var8);
                intent.putExtra("categorie",variable);
                startActivity(intent);
            }
        });
        }
        else if (intent.getStringExtra("code").equals("Am"))
        {
            final String var2=intent.getStringExtra("blibe");
            final String var3=intent.getStringExtra("bseuil");
            final String var4=intent.getStringExtra("bperemp");
            final String var44=intent.getStringExtra("bstock");
            final Boolean var5=intent.getBooleanExtra("etat1",false);
            final Boolean var6=intent.getBooleanExtra("etat2",true);
            final int var7=intent.getIntExtra("etat3",View.INVISIBLE);
            final int var8=intent.getIntExtra("etat4",View.VISIBLE);



            affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String depart= String.valueOf(parent.getItemIdAtPosition(position));
                    int var=Integer.parseInt(depart);

                    Intent intent=new Intent(Listecategorie.this,Besoin.class);
                    String variable=liste.get(position);
                    intent.putExtra("libCat",var2);
                    intent.putExtra("seuil",var3);
                    intent.putExtra("peremption",var4);
                    intent.putExtra("stock",var44);
                    intent.putExtra("betat1",var5);
                    intent.putExtra("betat2",var6);
                    intent.putExtra("betat3",var7);
                    intent.putExtra("betat4",var8);
                    intent.putExtra("categorie",variable);
                    startActivity(intent);
                    finish();
                }
            });
        }

        else if (intent.getStringExtra("code").equals("none"))
        {
            final String var2=intent.getStringExtra("blibe");
            final String var3=intent.getStringExtra("bseuil");
            final String var4=intent.getStringExtra("bperemp");
            final String var44=intent.getStringExtra("bstock");
            /*final Boolean var5=intent.getBooleanExtra("etat1",false);
            final Boolean var6=intent.getBooleanExtra("etat2",true);
            final int var7=intent.getIntExtra("etat3",View.INVISIBLE);
            final int var8=intent.getIntExtra("etat4",View.VISIBLE);*/



            affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String depart= String.valueOf(parent.getItemIdAtPosition(position));
                    int var=Integer.parseInt(depart);

                    Intent intent=new Intent(Listecategorie.this,Besoin.class);
                    String variable=liste.get(position);
                    intent.putExtra("libCat",var2);
                    intent.putExtra("seuil",var3);
                    intent.putExtra("peremption",var4);
                    intent.putExtra("stock",var44);
                   /* intent.putExtra("betat1",var5);
                    intent.putExtra("betat2",var6);
                    intent.putExtra("betat3",var7);
                    intent.putExtra("betat4",var8);*/
                    intent.putExtra("categorie",variable);
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

            convertView=getLayoutInflater().inflate(R.layout.activity_listecategorieimage,null);

            TextView textView=(TextView)convertView.findViewById(R.id.textCate);

            textView.setText(listee.get(position));

            return convertView;
        }
    }
}
