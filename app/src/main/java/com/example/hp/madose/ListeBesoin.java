package com.example.hp.madose;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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



        Intent intent = getIntent();
        if (intent.getStringExtra("code").equals("besoinSortie"))
        {

            BaseDeDonne bd=new BaseDeDonne(this);
            final List<BesoinC> besoin= bd.affichNumDemanBesoin(MyApplication.getIdDemande());

            Log.i("EriCo DecostA"," "+MyApplication.getIdBesoin()+" "+ MyApplication.getIdDemande());
            for (BesoinC besoinC: besoin){
                liste.add(besoinC.libelleBesoin());
            }
            ListAdapter departe=new ArrayAdapter<BesoinC>(this, android.R.layout.simple_list_item_1, besoin);
            ListView affiche=(ListView)findViewById(R.id.listB);

            final ListeBes listeBes=new ListeBes(this,liste);
            affiche.setAdapter(listeBes);

            final String varDate = intent.getStringExtra("bringDate");
            final String var1 = intent.getStringExtra("bringDemande");
           // final String var2 = intent.getStringExtra("bringDepartement");
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

                        intent.putExtra("bringB", variable);
                        intent.putExtra("bringD", varDate);
                        intent.putExtra("bringDem", var1);
                       // intent.putExtra("bringDe", var2);
                        intent.putExtra("bringE", var3);
                        intent.putExtra("bringM", var4);
                        intent.putExtra("bringQ", var5);
                        intent.putExtra("bringA", var6);
                        intent.putExtra("etat1", var7);
                        intent.putExtra("etat2", var8);
                        intent.putExtra("etat3", var9);
                        intent.putExtra("etat4", var10);
                        intent.putExtra("code", "listeB");
                        startActivity(intent);
                        finish();


                }
            });
        }
        else if (intent.getStringExtra("code").equals("besoinDepartement")){

            BaseDeDonne bd=new BaseDeDonne(this);
            final List<BesoinC> besoin= bd.afficheLB();
            for (BesoinC besoinC: besoin){
                liste.add(besoinC.libelleBesoin());
            }
            ListAdapter departe=new ArrayAdapter<BesoinC>(this, android.R.layout.simple_list_item_1, besoin);
            ListView affiche=(ListView)findViewById(R.id.listB);

            final ListeBes listeBes=new ListeBes(this,liste);
            affiche.setAdapter(listeBes);

            final String varDate = intent.getStringExtra("bringDate");
            final String var1 = intent.getStringExtra("bringDemande");
            final String var2 = intent.getStringExtra("bringDepartement");
            //final String var3 = intent.getStringExtra("bringEmp");
            final String var4 = intent.getStringExtra("bringMarque");
            final String var5 = intent.getStringExtra("bringQuantité");
            final String var6 = intent.getStringExtra("bringAutre");

            final Boolean var77 = intent.getBooleanExtra("bringRadDep", true);
            final Boolean var88 = intent.getBooleanExtra("bringRadEmp", false);
            final int var99 = intent.getIntExtra("bringEmpVis", View.INVISIBLE);
            final int var11 = intent.getIntExtra("bringDepVis", View.VISIBLE);


            affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String depart = String.valueOf(parent.getItemIdAtPosition(position));
                    int varr = Integer.parseInt(depart);


                    Intent intent = new Intent(ListeBesoin.this, BringOut.class);
                    String variable = liste.get(position);
                    // intent.putExtra("code","utilisateur");


                        intent.putExtra("bringB", variable);
                        intent.putExtra("bringD", varDate);
                        intent.putExtra("bringDem", var1);
                        intent.putExtra("bringDe", var2);
                        //intent.putExtra("bringE", var3);
                        intent.putExtra("bringM", var4);
                        intent.putExtra("bringQ", var5);
                        intent.putExtra("bringA", var6);
                        intent.putExtra("etat1", var77);
                        intent.putExtra("etat2", var88);
                        intent.putExtra("etat3", var99);
                        intent.putExtra("etat4", var11);
                        intent.putExtra("code", "listeB");
                        startActivity(intent);
                        finish();



                }
            });
        }
        else if (intent.getStringExtra("code").equals("add")){

            BaseDeDonne bd=new BaseDeDonne(this);
            final List<BesoinC> besoin= bd.afficheLB();
            for (BesoinC besoinC: besoin){
                liste.add(besoinC.libelleBesoin());
            }
            ListAdapter departe=new ArrayAdapter<BesoinC>(this, android.R.layout.simple_list_item_1, besoin);
            ListView affiche=(ListView)findViewById(R.id.listB);

            final ListeBes listeBes=new ListeBes(this,liste);
            affiche.setAdapter(listeBes);

            final String varDate = intent.getStringExtra("addDate");
            final String var1 = intent.getStringExtra("addFourni");
            final String var2 = intent.getStringExtra("addPrixU");
            final String var4 = intent.getStringExtra("addQt");
            final String var5 = intent.getStringExtra("addMark");
            final String var6 = intent.getStringExtra("addAutre");


            affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String depart = String.valueOf(parent.getItemIdAtPosition(position));
                    int varr = Integer.parseInt(depart);


                    Intent intent = new Intent(ListeBesoin.this, Add.class);
                    String variable = liste.get(position);
                    // intent.putExtra("code","utilisateur");


                    intent.putExtra("addB", variable);
                    intent.putExtra("addD", varDate);
                    intent.putExtra("addFournisseur", var1);
                    intent.putExtra("addP", var2);
                    intent.putExtra("addQ", var4);
                    intent.putExtra("addM", var5);
                    intent.putExtra("addA", var6);
                    intent.putExtra("code", "addBB");
                    startActivity(intent);
                    finish();



                }
            });
        }
        else if (intent.getStringExtra("code").equals("besoinDepartementD")){

            BaseDeDonne bd=new BaseDeDonne(this);
            final List<BesoinC> besoin= bd.afficheLB();
            for (BesoinC besoinC: besoin){
                liste.add(besoinC.libelleBesoin());
            }
            ListAdapter departe=new ArrayAdapter<BesoinC>(this, android.R.layout.simple_list_item_1, besoin);
            ListView affiche=(ListView)findViewById(R.id.listB);

            final ListeBes listeBes=new ListeBes(this,liste);
            affiche.setAdapter(listeBes);

            final String varDate = getIntent().getStringExtra("demDate");
            final String var1 = getIntent().getStringExtra("demDepartement");
            final String var2 = getIntent().getStringExtra("demQuantité");
            final Boolean var7 = getIntent().getBooleanExtra("bringRadDep", true);
            final Boolean var8 = getIntent().getBooleanExtra("bringRadEmp", false);
            final int var9 = getIntent().getIntExtra("bringEmpVis", View.INVISIBLE);
            final int var10 = getIntent().getIntExtra("bringDepVis", View.VISIBLE);


            affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String depart = String.valueOf(parent.getItemIdAtPosition(position));
                    int varr = Integer.parseInt(depart);


                    Intent intent = new Intent(ListeBesoin.this, Demande.class);
                    String variable = liste.get(position);
                    // intent.putExtra("code","utilisateur");
                    intent.putExtra("demB", variable);
                    intent.putExtra("demD", varDate);
                    intent.putExtra("demDe", var1);
                    intent.putExtra("demQ", var2);
                    intent.putExtra("etat1", var7);
                    intent.putExtra("etat2", var8);
                    intent.putExtra("etat3", var9);
                    intent.putExtra("etat4", var10);
                    intent.putExtra("code", "listeDD");
                    startActivity(intent);
                    finish();
                }
            });
        }
        else if (intent.getStringExtra("code").equals("besoinEmployeD")){
            BaseDeDonne bd=new BaseDeDonne(this);
            final List<BesoinC> besoin= bd.afficheLB();
            for (BesoinC besoinC: besoin){
                liste.add(besoinC.libelleBesoin());
            }
            ListAdapter departe=new ArrayAdapter<BesoinC>(this, android.R.layout.simple_list_item_1, besoin);
            ListView affiche=(ListView)findViewById(R.id.listB);

            final ListeBes listeBes=new ListeBes(this,liste);
            affiche.setAdapter(listeBes);

            final String varDate = getIntent().getStringExtra("demDate");
            final String var1 = getIntent().getStringExtra("demEmp");
            final String var2 = getIntent().getStringExtra("demQuantité");
            final Boolean var7 = getIntent().getBooleanExtra("bringRadDep", false);
            final Boolean var8 = getIntent().getBooleanExtra("bringRadEmp", true);
            final int var9 = getIntent().getIntExtra("bringEmpVis", View.VISIBLE);
            final int var10 = getIntent().getIntExtra("bringDepVis", View.INVISIBLE);


            affiche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String depart = String.valueOf(parent.getItemIdAtPosition(position));
                    int varr = Integer.parseInt(depart);


                    Intent intent = new Intent(ListeBesoin.this, Demande.class);
                    String variable = liste.get(position);
                    // intent.putExtra("code","utilisateur");
                    intent.putExtra("demB", variable);
                    intent.putExtra("demD", varDate);
                    intent.putExtra("demE", var1);
                    intent.putExtra("demQ", var2);
                    intent.putExtra("etat1", var7);
                    intent.putExtra("etat2", var8);
                    intent.putExtra("etat3", var9);
                    intent.putExtra("etat4", var10);
                    intent.putExtra("code", "listeDD");
                    startActivity(intent);
                    finish();
                }
            });
        }


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
