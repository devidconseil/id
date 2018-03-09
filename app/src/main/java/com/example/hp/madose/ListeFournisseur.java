package com.example.hp.madose;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListeFournisseur extends AppCompatActivity {
    BaseDeDonne bd=new BaseDeDonne(this);
    List<String> liste=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_fournisseur);
        BaseDeDonne bd=new BaseDeDonne(this);

        final List<FournisseurC> departem= bd.listeF();
        for (FournisseurC fournisseurC : departem){
            liste.add(fournisseurC.listeFour());
        }
        ListAdapter departe=new ArrayAdapter<FournisseurC>(this, android.R.layout.simple_list_item_1, departem);
        ListView affiche=(ListView)findViewById(R.id.listFour);


        final ListeFour listeFour=new ListeFour(this,liste);
        affiche.setAdapter(listeFour);
    }


    class ListeFour extends BaseAdapter
    {
        Context context;
        List<String> listee=new ArrayList<>();

        public ListeFour(Context context,List<String> listee) {
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
