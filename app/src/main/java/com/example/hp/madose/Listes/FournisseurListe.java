package com.example.hp.madose.Listes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hp.madose.Acceuil;
import com.example.hp.madose.Affichage;
import com.example.hp.madose.BaseDeDonne;
import com.example.hp.madose.BesoinC;
import com.example.hp.madose.Fournisseur;
import com.example.hp.madose.FournisseurC;
import com.example.hp.madose.MyAdapter.MyAdapter;
import com.example.hp.madose.MyAdapter.MyAdapterFour;
import com.example.hp.madose.MyApplication;
import com.example.hp.madose.R;
import com.example.hp.madose.model.Item;
import com.example.hp.madose.model.ItemF;

import java.util.ArrayList;
import java.util.List;

public class FournisseurListe extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ItemF>listItem =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fournisseur_liste);

        recyclerView=(RecyclerView)findViewById(R.id.listedesF);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItem=new ArrayList<>();

        setDataFour();

        findViewById(R.id.floatingRet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FournisseurListe.this,Acceuil.class);
                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.floatingAj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(FournisseurListe.this, Fournisseur.class);
                    intent.putExtra("action","ajouter");
                    startActivity(intent);

            }
            });

    }

    private void setDataFour()
    {

        BaseDeDonne bd=new BaseDeDonne(this);
        List<FournisseurC> affC = bd.afficheFo();
       // textView=(TextView)findViewById(R.id.libS);
        recyclerView=(RecyclerView)findViewById(R.id.listedesF);
        for (FournisseurC emp : affC)
        {       //ce gar la n'aime pas les int
            ItemF itemF=new ItemF(emp.getIdFour(),emp.toStringNomFour(),emp.toStringContactFour(), emp.toStringAdressFour());
            listItem.add(itemF);

        }
        MyAdapterFour adapter=new MyAdapterFour(listItem,this);
        recyclerView.setAdapter(adapter);
    }


}
