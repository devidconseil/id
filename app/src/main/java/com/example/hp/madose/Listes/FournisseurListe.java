package com.example.hp.madose.Listes;

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

import com.example.hp.madose.BaseDeDonne;
import com.example.hp.madose.FournisseurC;
import com.example.hp.madose.R;

import java.util.ArrayList;
import java.util.List;

public class FournisseurListe extends AppCompatActivity {
    BaseDeDonne bd=new BaseDeDonne(this);
    List<String> liste1=new ArrayList<>();
    List<String> liste2=new ArrayList<>();
    List<String> liste3=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fournisseur_liste);


        BaseDeDonne bd=new BaseDeDonne(this);
        final List<FournisseurC> four= bd.afficheF();
        for (FournisseurC fournisseurC: four){
            liste1.add(fournisseurC.toStringNomFour());
            liste2.add(fournisseurC.toStringContactFour());
            liste3.add(fournisseurC.toStringAdressFour());

        }
        ListAdapter departe=new ArrayAdapter<FournisseurC>(this, android.R.layout.simple_list_item_1, four);
        ListView affiche=(ListView)findViewById(R.id.listedesF);


        final ListeDesF1 listeDesF1=new ListeDesF1(this,liste1);
        final ListeDesF2 listeDesF2=new ListeDesF2(this,liste2);
        final ListeDesF3 listeDesF3=new ListeDesF3(this,liste3);
        affiche.setAdapter(listeDesF1);
        affiche.setAdapter(listeDesF2);
       // affiche.setAdapter(listeDesF3);
    }
    class ListeDesF1 extends BaseAdapter {
        Context context;
        List<String> listee = new ArrayList<>();

        public ListeDesF1(Context context, List<String> listee) {
            this.context = context;
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

            convertView = getLayoutInflater().inflate(R.layout.fournisseur_liste1, null);
            convertView = getLayoutInflater().inflate(R.layout.fournisseur_liste2, null);
            convertView = getLayoutInflater().inflate(R.layout.fournisseur_liste3, null);

            TextView textView0 = (TextView) convertView.findViewById(R.id.nomduF);

            textView0.setText(listee.get(position));

            return convertView;
        }
    }



        class ListeDesF2 extends BaseAdapter
        {
            Context context;
            List<String> listee=new ArrayList<>();

            public ListeDesF2(Context context,List<String> listee) {
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

                convertView = getLayoutInflater().inflate(R.layout.fournisseur_liste2, null);


                TextView textView1 = (TextView) convertView.findViewById(R.id.contDuF);

                textView1.setText(listee.get(position));

                return convertView;
            }
        }



        class ListeDesF3 extends BaseAdapter {
            Context context;
            List<String> listee = new ArrayList<>();

            public ListeDesF3(Context context, List<String> listee) {
                this.context = context;
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

                convertView = getLayoutInflater().inflate(R.layout.fournisseur_liste3, null);

                TextView textView2 = (TextView) convertView.findViewById(R.id.adresDuF);

                textView2.setText(listee.get(position));


                return convertView;
            }
        }




}
