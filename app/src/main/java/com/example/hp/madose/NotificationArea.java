package com.example.hp.madose;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.madose.Listes.ListeDesDemandes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HP on 23/04/2018.
 */

public class NotificationArea extends AppCompatActivity {
    BaseDeDonne bd;
    String profile,nume,heuree;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_area);
        bd=new BaseDeDonne(this);
        List<String> notification=new ArrayList<>();
        profile=bd.retrieveUserProfile(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        if (!profile.equals("USER")) {
            for (String mail : bd.accountNotValidate()) {
                notification.add("Un nouvel utilisateur enregistré est en attente de validation:" + mail);
            }
            for (String num : bd.demandeNotValidate()) {
                nume=num;
                notification.add("Une nouvelle demande enregistrée est en attente de validation: demande numéro " + num);
            }
            ListAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notification);
            final ListView listView = findViewById(R.id.listview_area);
            listView.setAdapter(arrayAdapter);

            //listView.setBackgroundColor(Color.parseColor("#ffffff"));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (listView.getItemAtPosition(position).toString().contains("utilisateur")) {
                        int a = listView.getItemAtPosition(position).toString().length();
                        MyApplication.mail = listView.getItemAtPosition(position).toString().substring(62, a);
                        Intent intent = new Intent(NotificationArea.this, Utilisateur.class);
                        intent.putExtra("status", "new user validating");
                        startActivity(intent);
                        // Toast.makeText(getApplicationContext(), MyApplication.mail,Toast.LENGTH_SHORT).show();
                    }
                    if (listView.getItemAtPosition(position).toString().contains("demande")){
                        final AlertDialog.Builder builder = new AlertDialog.Builder(NotificationArea.this);
                        builder.setTitle("Voulez-vous valider cette demande?");
                        builder.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                bd.updateDemande(Integer.parseInt(nume), "VALIDE");
                                Intent intent=new Intent(NotificationArea.this,ListeDesDemandes.class);
                                intent.putExtra("sortie","listeD");
                                startActivity(intent);
                            }
                        });
                        builder.setNeutralButton("REFUSER", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                bd.updateDemande(Integer.parseInt(nume),"REFUSE");
                                Intent intent=new Intent(NotificationArea.this,ListeDesDemandes.class);
                                intent.putExtra("sortie","listeD");
                                startActivity(intent);
                            }
                        });
                        builder.create();
                        builder.show();
                    }
                }
            });
        }
        else {

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(NotificationArea.this,Acceuil.class));
    }
    private void updateNewDemande(  String email,  String numero,String etat) {

        String depart,code,heure,nom,pren;
        nom=bd.selectEmpNomFromMail(email);
        pren=bd.selectEmpPrenomFromMail(email);
        depart=bd.selectDepartFromUser(email);
       // heure=bd.selectHeureDemandeFromNumero(nume);
        MyApplication.getmDatabase().child("HeureDemande").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotHDem:dataSnapshot.getChildren()){
                    String chain=dataSnapshotHDem.getValue(String.class);
                    if (chain.contains(MyApplication.getmAuth().getCurrentUser().getEmail())){
                        heuree= String.valueOf(chain.split("-"+ MyApplication.getmAuth().getCurrentUser().getEmail(),0));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        code=nom+"-"+pren+"-"+depart+"-"+heuree;


        Map<String,Object> childUpdates=new HashMap<>();
        childUpdates.put("/Demande/"+code+"/etat",etat);
        MyApplication.getmDatabase().updateChildren(childUpdates);


    }
}