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

public class ListeUtilisateur extends AppCompatActivity {

    BaseDeDonne bd=new BaseDeDonne(this);
    List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_utilisateur);
        BaseDeDonne bd=new BaseDeDonne(this);


        final List<UtilisateurC> userliste= bd.listeUser();
        for (UtilisateurC utilisateurC : userliste){
            list.add(utilisateurC.nomEtprenom());
        }
        ListAdapter listAdapter=new ArrayAdapter<UtilisateurC>(this, android.R.layout.simple_list_item_1, userliste);
        ListView affiche=(ListView)findViewById(R.id.listeUser);

        final ListeUser listeUser=new ListeUser(this,list);
        affiche.setAdapter(listeUser);

        Intent intent=getIntent();
        final String varDate=intent.getStringExtra("bringDate");
        final String var1=intent.getStringExtra("bringPresonne");
        final String var2=intent.getStringExtra("bringDepartement");
        final String var3=intent.getStringExtra("bringBesoin");
        final String var4=intent.getStringExtra("bringMarque");
        final String var5=intent.getStringExtra("bringQuantité");
        final String var6=intent.getStringExtra("bringAutre");

        affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String depart= String.valueOf(parent.getItemIdAtPosition(position));
                int varr=Integer.parseInt(depart);


                Intent intent=new Intent(ListeUtilisateur.this,BringOut.class);
                String variable=list.get(position);
               // intent.putExtra("code","utilisateur");
                intent.putExtra("employe",variable);
                intent.putExtra("bringD",varDate);
                intent.putExtra("bringP",var1);
                intent.putExtra("bringDe",var2);
                intent.putExtra("bringB",var3);
                intent.putExtra("bringM",var4);
                intent.putExtra("bringQ",var5);
                intent.putExtra("bringA",var6);
                startActivity(intent);
                finish();
            }
        });

    }


    class ListeUser extends BaseAdapter
    {
        Context context;
        List<String> listee=new ArrayList<>();

        public ListeUser(Context context,List<String> listee) {
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
