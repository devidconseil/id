package com.example.hp.madose.Listes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hp.madose.Acceuil;
import com.example.hp.madose.BaseDeDonne;
import com.example.hp.madose.Besoin;
import com.example.hp.madose.BesoinC;
import com.example.hp.madose.MyAdapter.MyAdapter;
import com.example.hp.madose.R;
import com.example.hp.madose.model.Item;

import java.util.ArrayList;
import java.util.List;

public class BesoinListe extends AppCompatActivity {

   RecyclerView list;
   TextView textView;
   RecyclerView.LayoutManager layoutManager;
   List<Item>items =new ArrayList<>();
   List<Item>ite =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_besoin_liste);
        RecyclerView list=(RecyclerView)findViewById(R.id.recycler);
        list.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);


        setData();
        findViewById(R.id.floatingAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BesoinListe.this, Besoin .class);
                startActivity(intent);
            }
        });
        findViewById(R.id.floatingBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BesoinListe.this, Acceuil.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void setData()
    {

        BaseDeDonne bd=new BaseDeDonne(this);
        List<BesoinC> affC = bd.afficheB();
        textView=(TextView)findViewById(R.id.libS);
        RecyclerView list=(RecyclerView)findViewById(R.id.recycler);
        for (BesoinC emp : affC)
        {       //ce gar la n'aime pas les int 
                Item item=new Item(emp.getLibBes(),emp.getTypeBes(), emp.getImageBes(),String.valueOf(emp.getStockBes()),emp.getLibCat(),true);
                items.add(item);

        }
        MyAdapter adapter=new MyAdapter(items);
        list.setAdapter(adapter);
    }
}
