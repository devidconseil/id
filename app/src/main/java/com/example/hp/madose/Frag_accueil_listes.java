package com.example.hp.madose;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hp.madose.Listes.BesoinListe;
import com.example.hp.madose.Listes.FournisseurListe;
import com.example.hp.madose.Listes.ListeAchat;
import com.example.hp.madose.Listes.ListeDesDemandes;
import com.example.hp.madose.Listes.ListeDesEntrees;
import com.example.hp.madose.Listes.ListeDesSorties;

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
       // Button stock= rootView.findViewById(R.id.stock);
        Button demand= rootView.findViewById(R.id.listDe);
        Button liste_achats= rootView.findViewById(R.id.textView10);
        //Button rupture= rootView.findViewById(R.id.rupture);



        entrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ListeDesEntrees.class);
                intent.putExtra("sortie","listeE");
                startActivity(intent);
            }
        });

        demande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CoutTotalBesoin.class);
                intent.putExtra("recherche","cout");
                startActivity(intent);
            }
        });

        sortie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ListeDesSorties.class);
                intent.putExtra("sortie","listeS");
                startActivity(intent);
            }
        });


        demand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ListeDesDemandes.class);
                intent.putExtra("sortie","listeD");
                startActivity(intent);
            }
        });
        liste_achats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ListeAchat.class);
                intent.putExtra("passage","Liste_achats");
                startActivity(intent);
            }
        });
          /* stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Affichage.class);
                intent.putExtra("passage","stock");
                startActivity(intent);
            }
        });*/

       /* rupture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Affichage.class);
                intent.putExtra("passage","Rupture");
                startActivity(intent);
            }
        });*/



        return rootView;
    }
}
