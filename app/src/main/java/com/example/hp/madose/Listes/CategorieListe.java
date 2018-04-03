package com.example.hp.madose.Listes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hp.madose.Acceuil;
import com.example.hp.madose.BaseDeDonne;
import com.example.hp.madose.Categorie;
import com.example.hp.madose.CategorieC;
import com.example.hp.madose.MyAdapter.MyAdapterCat;
import com.example.hp.madose.R;
import com.example.hp.madose.model.ItemD;

import java.util.ArrayList;
import java.util.List;

public class CategorieListe extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ItemD> listItem =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie_liste);

        recyclerView=(RecyclerView)findViewById(R.id.listedesCat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setDataCat();
        findViewById(R.id.floatiRet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategorieListe.this,Acceuil.class);
                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.floatiAj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CategorieListe.this, Categorie.class);
                startActivity(intent);

            }
        });
    }
    private void setDataCat()
    {

        BaseDeDonne bd=new BaseDeDonne(this);
        List<CategorieC> affC = bd.afficheCat();
        // textView=(TextView)findViewById(R.id.libS);
        recyclerView=(RecyclerView)findViewById(R.id.listedesCat);
        for (CategorieC emp : affC)
        {
            ItemD itemF=new ItemD(emp.nomcat());
            listItem.add(itemF);

        }
        MyAdapterCat adapter=new MyAdapterCat(listItem,this);
        recyclerView.setAdapter(adapter);
    }
}
