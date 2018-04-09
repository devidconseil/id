package com.example.hp.madose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;

import com.example.hp.madose.Listes.BesoinListe;

public class Variation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variation);

        Button entrees= findViewById(R.id.listeE);
        Button demande= findViewById(R.id.listeD);
        Button sortie= findViewById(R.id.listeS);
        Button demand= findViewById(R.id.listDe);
        Button liste_achats= findViewById(R.id.textView10);


        entrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Variation.this,Affichage.class);
                intent.putExtra("passage","entree");
                startActivity(intent);
            }
        });

        demande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Variation.this,CoutTotalBesoin.class);
                intent.putExtra("passage","demande");
                startActivity(intent);
            }
        });

        sortie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Variation.this,Affichage.class);
                intent.putExtra("passage","sortie");
                startActivity(intent);
            }
        });

       /* stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Variation.this,Affichage.class);
                intent.putExtra("passage","stock");
                startActivity(intent);
            }
        });*/

        demand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Variation.this,Affichage.class);
                intent.putExtra("passage","demande");
                startActivity(intent);
            }
        });
        Button rupture= findViewById(R.id.rupture);


        rupture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Variation.this,Affichage.class);
                intent.putExtra("passage","Rupture");
                startActivity(intent);
            }
        });

        liste_achats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Variation.this,Affichage.class);
                intent.putExtra("passage","Liste_achats");
                startActivity(intent);
            }
        });
       /* test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Variation.this, BesoinListe.class);
                startActivity(intent);
            }
        });
     /*   test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Variation.this, TableListe.class);
                startActivity(intent);
            }
        });*/
    }
}
