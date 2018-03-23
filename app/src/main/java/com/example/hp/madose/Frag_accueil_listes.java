package com.example.hp.madose;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by erick on 20/03/2018.
 */

public class Frag_accueil_listes extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_listes, container, false);

        Button entrees= rootView.findViewById(R.id.listeE);
        Button demande= rootView.findViewById(R.id.listeD);
        Button sortie= rootView.findViewById(R.id.listeS);
        Button stock= rootView.findViewById(R.id.stock);
        Button demand= rootView.findViewById(R.id.textView9);
        Button liste_achats= rootView.findViewById(R.id.textView10);
        Button rupture= rootView.findViewById(R.id.rupture);
        Button test=(Button)rootView.findViewById(R.id.boutontest);
        Button test2=(Button)rootView.findViewById(R.id.button4);


        entrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Affichage.class);
                intent.putExtra("passage","entree");
                startActivity(intent);
            }
        });

        demande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CoutTotalBesoin.class);
                intent.putExtra("passage","demande");
                startActivity(intent);
            }
        });

        sortie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Affichage.class);
                intent.putExtra("passage","sortie");
                startActivity(intent);
            }
        });

        stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Affichage.class);
                intent.putExtra("passage","stock");
                startActivity(intent);
            }
        });

        demand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Affichage.class);
                intent.putExtra("passage","demande");
                startActivity(intent);
            }
        });



        rupture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Affichage.class);
                intent.putExtra("passage","Rupture");
                startActivity(intent);
            }
        });

        liste_achats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),Affichage.class);
                intent.putExtra("passage","Liste_achats");
                startActivity(intent);
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Display.class);
                startActivity(intent);
            }
        });
        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), TableListe.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
