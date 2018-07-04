package com.example.hp.madose.Listes;

import android.app.Notification;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hp.madose.Acceuil;
import com.example.hp.madose.BaseDeDonne;
import com.example.hp.madose.MyAdapter.MyAdapterUser;
import com.example.hp.madose.R;
import com.example.hp.madose.Utilisateur;
import com.example.hp.madose.UtilisateurC;
import com.example.hp.madose.model.Item;
import com.example.hp.madose.model.ItemU;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurListe extends AppCompatActivity {
    RecyclerView list;
    TextView textView;
    RecyclerView.LayoutManager layoutManager;
    List<ItemU>items =new ArrayList<>();
    List<ItemU>ite =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilisateur_liste);
        RecyclerView list=(RecyclerView)findViewById(R.id.recycle);
        list.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);


        setDataU();
        findViewById(R.id.float1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UtilisateurListe.this, Utilisateur.class);
                intent.putExtra("status","new user creating");
                startActivity(intent);
            }
        });
        findViewById(R.id.float2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UtilisateurListe.this, Acceuil.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void setDataU()
    {

        BaseDeDonne bd=new BaseDeDonne(this);
        List<UtilisateurC> affC = bd.afficheE();
        RecyclerView list=(RecyclerView)findViewById(R.id.recycle);
        for (UtilisateurC emp : affC)
        {       //ce gar la n'aime pas les int
            ItemU item=new ItemU(emp.toIntId(),emp.toStringNomPre(),emp.toStringProf(), emp.toStringMail(),emp.toStringTel(),emp.toStringDepart(),true);

            items.add(item);

        }
        MyAdapterUser adapter=new MyAdapterUser(items);
        list.setAdapter(adapter);
    }
}
