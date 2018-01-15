package com.example.hp.madose;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.ArrayList;

public class CoutTotalBesoin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cout_total_besoin);


        BaseDeDonne bd=new BaseDeDonne(this);
        final AutoCompleteTextView autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.autoRecherche);


        ArrayList<String> nb=bd.affiNB();
        ArrayAdapter<String> nombes=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nb);
        autoCompleteTextView.setAdapter(nombes);

        findViewById(R.id.recherch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.setGlobalVarValue(autoCompleteTextView.getText().toString());
                Intent intent=new Intent(CoutTotalBesoin.this,Affichage.class);
                intent.putExtra("passage","Cout");

                startActivity(intent);

            }
        });
    }

}
