package com.example.hp.madose.Listes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hp.madose.Acceuil;
import com.example.hp.madose.BaseDeDonne;
import com.example.hp.madose.Departement;
import com.example.hp.madose.DepartementC;
import com.example.hp.madose.MyAdapter.MyAdapterDep;
import com.example.hp.madose.R;
import com.example.hp.madose.model.ItemD;

import java.util.ArrayList;
import java.util.List;

public class DepartementListe extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ItemD> listItem =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departement_liste);

        recyclerView=(RecyclerView)findViewById(R.id.listedesD);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setDataDeparte();
        findViewById(R.id.floatRet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DepartementListe.this,Acceuil.class);
                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.floatAj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DepartementListe.this, Departement.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setDataDeparte()
    {

        BaseDeDonne bd=new BaseDeDonne(this);
        List<DepartementC> affC = bd.afficheDepart();
        // textView=(TextView)findViewById(R.id.libS);
        recyclerView=(RecyclerView)findViewById(R.id.listedesD);
        for (DepartementC emp : affC)
        {
            ItemD itemF=new ItemD(emp.libDep());
            listItem.add(itemF);

        }
        MyAdapterDep adapter=new MyAdapterDep(listItem,this);
        recyclerView.setAdapter(adapter);
    }
}
