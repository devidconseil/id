package com.example.hp.madose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Listedepartement extends AppCompatActivity {

    String[] departement={"INFORMATIQUE","RECHERCHE","DIRECTION","SECRETARIAT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listedepartement);


        ListAdapter departe=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, departement);
        ListView affiche=(ListView)findViewById(R.id.listeview);
       // affiche.setAdapter(departe);

        final ListeDepart listeDepart=new ListeDepart();
        affiche.setAdapter(listeDepart);

        Intent intent=getIntent();
        final String var2=intent.getStringExtra("employer");
        final String var1=intent.getStringExtra("employerr");

        affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String depart= String.valueOf(parent.getItemIdAtPosition(position));
                int var=Integer.parseInt(depart);
                // Toast.makeText(getBaseContext(),departement[var],Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(Listedepartement.this,Employe.class);
                intent.putExtra("departement",departement[var]);
                intent.putExtra("employer",var2);
                intent.putExtra("employerr",var1);
                startActivity(intent);
            }
        });


    }

        class ListeDepart extends BaseAdapter
        {

            @Override
            public int getCount() {
                return departement.length;
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

                textView.setText(departement[position]);

                return convertView;
            }
        }
}
