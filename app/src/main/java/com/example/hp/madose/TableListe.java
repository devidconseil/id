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
import android.widget.TableLayout;
import android.widget.TableRow;
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

        TextView textView1=(TextView)findViewById(R.id.n1);
        TextView textView2=(TextView)findViewById(R.id.n2);
        TextView textView3=(TextView)findViewById(R.id.n3);
        TextView textView4=(TextView)findViewById(R.id.n4);
        TextView textView5=(TextView)findViewById(R.id.n5);
        TextView textView6=(TextView)findViewById(R.id.n6);


        BaseDeDonne bd=new BaseDeDonne(this);


        final List<UtilisateurC> departem= bd.afficheE();
        for (UtilisateurC utilisateurC : departem){
                textView1.append(utilisateurC.toStringNom()+"\n\n");
                textView2.append(utilisateurC.toStringPren()+"\n\n");
                textView3.append(utilisateurC.toStringMail()+"\n\n");
                textView4.append(utilisateurC.toStringTel()+"\n\n");
                textView5.append(utilisateurC.toStringDepart()+"\n\n");
                textView6.append(utilisateurC.toStringProf()+"\n\n");

        }
        ListAdapter departe=new ArrayAdapter<UtilisateurC>(this, android.R.layout.simple_list_item_1, departem);
       ListView affiche=(ListView)findViewById(R.id.maliste);


       // final ListUtilisateur listeUti=new ListUtilisateur(this,liste);
        affiche.setAdapter(departe);

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
            TextView textView1=(TextView)convertView.findViewById(R.id.n1);
            TextView textView2=(TextView)convertView.findViewById(R.id.n2);
            TextView textView3=(TextView)convertView.findViewById(R.id.n3);
            TextView textView4=(TextView)convertView.findViewById(R.id.n4);
            TextView textView5=(TextView)convertView.findViewById(R.id.n5);
            TextView textView6=(TextView)convertView.findViewById(R.id.n6);


                textView1.setText(listeU.get(position));
                textView2.setText(listeU.get(position));
                textView3.setText(listeU.get(position));
                textView4.setText(listeU.get(position));
                textView5.setText(listeU.get(position));
                textView6.setText(listeU.get(position));

            return convertView;
        }
    }
}
