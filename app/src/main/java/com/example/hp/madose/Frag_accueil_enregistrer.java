package com.example.hp.madose;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by erick on 20/03/2018.
 */

public class Frag_accueil_enregistrer extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_enregistrer, container, false);

        CardView cardView1=rootView.findViewById(R.id.acBout1);
        CardView cardView2=rootView.findViewById(R.id.acBout2);
        CardView cardView3=rootView.findViewById(R.id.acBout3);


       cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Add.class);
                intent.putExtra("code","accueil");
                startActivity(intent);
            }
        });


        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), BringOut.class);
                intent.putExtra("code","accueil");
                startActivity(intent);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Demande.class);
                intent.putExtra("code","accueil");
                startActivity(intent);
            }
        });
        return rootView;
    }
}
