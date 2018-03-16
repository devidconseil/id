package com.example.hp.madose;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TableListe extends AppCompatActivity {

    BaseDeDonne bd=new BaseDeDonne(this);
    List<String> liste=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_liste);

        BaseDeDonne bd=new BaseDeDonne(this);


        final List<UtilisateurC> departem= bd.afficheE();
        for (UtilisateurC utilisateurC : departem){
            liste.add(utilisateurC.toStringNom());
            liste.add(utilisateurC.toStringPren());
            liste.add(utilisateurC.toStringMail());
            liste.add(utilisateurC.toStringTel());
            liste.add(utilisateurC.toStringDepart());
            liste.add(utilisateurC.toStringProf());

        }
        ListAdapter departe=new ArrayAdapter<UtilisateurC>(this, android.R.layout.simple_list_item_1, departem);
        ListView affiche=(ListView)findViewById(R.id.maliste);


        final ListUtilisateur listeUti=new ListUtilisateur(this,liste);
        affiche.setAdapter(listeUti);

    }

    class ListUtilisateur extends BaseAdapter
    {

        Context context;
        List<String> listeU=new ArrayList<>();

        public ListUtilisateur(Context context, List<String> listeU) {
            this.context = context;
            this.listeU = listeU;
        }

        @Override
        public int getCount() {
            return listeU.size();
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

            convertView=getLayoutInflater().inflate(R.layout.activity_table_liste_image,null);
            LinearLayout linearLayout=(LinearLayout)findViewById(R.id.listUtilisateur);
            TextView textView1=(TextView)convertView.findViewById(R.id.nro1);
            TextView textView2=(TextView)convertView.findViewById(R.id.nro2);
            TextView textView3=(TextView)convertView.findViewById(R.id.nro3);
            TextView textView4=(TextView)convertView.findViewById(R.id.nro4);
            TextView textView5=(TextView)convertView.findViewById(R.id.nro5);
            TextView textView6=(TextView)convertView.findViewById(R.id.nro6);
           if (position==0)
           {

                textView1.setText("Nom");
                textView2.setText("Prénom");
                textView3.setText("E-mail");
                textView4.setText("Contact");
                textView5.setText("Departement");
                textView6.setText("Profil");
            }
            else
            {
                textView1.setText(listeU.get(position));
                textView2.setText(listeU.get(position));
                textView3.setText(listeU.get(position));
                textView4.setText(listeU.get(position));
                textView5.setText(listeU.get(position));
                textView6.setText(listeU.get(position));
            }
            return convertView;
        }
    }
}
