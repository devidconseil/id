package com.example.hp.madose;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    String profile,nume,heuree,besoin,numbes,mail, depart,code,nom,pren;;
    HeureDemandeC pac,chain;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_area);
        bd=new BaseDeDonne(this);
        List<String> notification=new ArrayList<>();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        profile=bd.retrieveUserProfile(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        pac=new HeureDemandeC();
        pac.setBesoin("");
        pac.setHeure("");
        pac.setHeure("");


        mDatabase.child("HeureDemande").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotHDem:dataSnapshot.getChildren()){
               chain = dataSnapshotHDem.getValue(HeureDemandeC.class);
                    Log.i("sawyer",dataSnapshotHDem.getValue(HeureDemandeC.class).getBesoin());

                    if (chain.getBesoin().equals(pac.getBesoin()) && chain .getHour().equals(pac.getHour())){
                        // heuree=chain.getHeure();
                        // besoin=chain.getBesoin();
                        Log.i("see it",pac.getHeure()+" "+pac.getBesoin()+"_"+pac.getBesoin()+"-"+pac.getHeure());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (!profile.equals("USER")) {
            for (String mail : bd.accountNotValidate()) {
                notification.add("Un nouvel utilisateur enregistré est en attente de validation:" + mail);
            }
            for (HeureDemandeC num : bd.demandeNotValidate1()) {

                notification.add("Une nouvelle demande de "+num.getPrenom()+" "+num.getNom()+" enregistrée est en attente de validation: " + num.getQte()+" "+num.getBesoin()+".");
                Log.i("vvv",num.getMail()+"-"+num.getNumero());
            }
            ListAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notification);
            final ListView listView = findViewById(R.id.listview_area);
            listView.setAdapter(arrayAdapter);

            //listView.setBackgroundColor(Color.parseColor("#ffffff"));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
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

                                for (final HeureDemandeC num : bd.demandeNotValidate1()) {
                                    pac=num;
                                    nume=num.getNumero();
                                    numbes=bd.selectIdBes(num.getBesoin());
                                    mail=num.getMail();
                                    Log.i("vvvv",num.getMail()+"-"+num.getNumero());

                                mDatabase.child("HeureDemande").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot dataSnapshotHDem:dataSnapshot.getChildren()){
                                            chain = dataSnapshotHDem.getValue(HeureDemandeC.class);
                                            Log.i("sawyer",dataSnapshotHDem.getValue(HeureDemandeC.class).getBesoin());
                                            Log.i("payton",num.getMail()+"-"+num.getNom()+" "+chain.getNom()+"/"+listView.getItemAtPosition(position).toString());
                                            Log.i("scott",chain.getBesoin()+"-"+num.getBesoin()+"/"+chain.getHour()+"-"+num.getHour());

                                            if (chain.getBesoin().equals(num.getBesoin()) && chain .getHour().equals(num.getHour()) && listView.getItemAtPosition(position).toString().contains(chain.getNom())&& listView.getItemAtPosition(position).toString().contains(chain.getPrenom()) && listView.getItemAtPosition(position).toString().contains(chain.getBesoin()) && listView.getItemAtPosition(position).toString().contains(String.valueOf(chain.getQte()))){
                                                // heuree=chain.getHeure();
                                                // besoin=chain.getBesoin();
                                                bd.updateDemande(Integer.parseInt(num.getNumero()),Integer.parseInt(bd.selectIdBes(num.getBesoin())), "VALIDE");
                                                updateNewDemande(num.getMail(),"VALIDE");
                                                Log.i("see_it",num.getHeure()+" "+num.getBesoin()+"_"+pac.getBesoin()+"-"+pac.getHeure());

                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                  });
                                }


                                Intent intent=new Intent(NotificationArea.this,ListeDesDemandes.class);
                                intent.putExtra("sortie","listeD");
                                startActivity(intent);
                            }
                        });
                        builder.setNeutralButton("REFUSER", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                bd.updateDemande(Integer.parseInt(nume),Integer.parseInt(numbes),"REFUSE");
                                updateNewDemande(mail,"REFUSE");
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
    private void updateNewDemande(  String email, String etat) {
        nom=bd.selectEmpNomFromMail(email);
        pren=bd.selectEmpPrenomFromMail(email);
        depart=bd.selectDepartFromMail(email);
        String cricri;
       // heure=bd.selectHeureDemandeFromNumero(nume);
        code=nom+"-"+pren+"-"+depart+"-"+chain.getBesoin()+"-"+chain.getHeure();
        if (nom.contains(" ")){
            cricri=nom.replace(" ","-");
            code=cricri+"-"+pren+"-"+depart+"-"+chain.getBesoin()+"-"+chain.getHeure();
        }
  /*      if (dateDem.contains("/")){
            cris=dateDem.replace("/","-");
            code=nomEmp+"-"+libDpe+"-"+libBes+"-"+cris;
        }   */
        if (nom.contains("'") || depart.contains("'") || chain.getBesoin().contains("'")){
            code=code.replace("'","-");
        }
        if (depart.contains(" ") || chain.getBesoin().contains(" ")){
            code=code.replace(" ","-");
        }

        Map<String,Object> childUpdates=new HashMap<>();
        childUpdates.put("/Demande/"+code+"/etat",etat);
        MyApplication.getmDatabase().updateChildren(childUpdates);


    }
}