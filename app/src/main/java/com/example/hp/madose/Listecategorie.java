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
            liste.add(categorieC.toString().substring(11));
        }
        ListAdapter departe=new ArrayAdapter<CategorieC>(this, android.R.layout.simple_list_item_1, departem);
        ListView affiche=(ListView)findViewById(R.id.listcategorie);


        final Listecategorie.ListeDepart listeDepart1=new Listecategorie.ListeDepart(this,liste);
        affiche.setAdapter(listeDepart1);

        Intent intent=getIntent();
        final String var2=intent.getStringExtra("categorie");




        affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String depart= String.valueOf(parent.getItemIdAtPosition(position));
                int var=Integer.parseInt(depart);

                Intent intent=new Intent(Listecategorie.this,Besoin.class);
                String variable=liste.get(position);
                intent.putExtra("categorie",var2);
                intent.putExtra("categoriec",variable);
                startActivity(intent);
            }
        });

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
