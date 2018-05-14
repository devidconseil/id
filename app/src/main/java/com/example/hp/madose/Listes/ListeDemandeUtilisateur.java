package com.example.hp.madose.Listes;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.madose.Acceuil;
import com.example.hp.madose.BaseDeDonne;
import com.example.hp.madose.Demande;
import com.example.hp.madose.DemandeC;
import com.example.hp.madose.MyApplication;
import com.example.hp.madose.R;
import com.example.hp.madose.Welcome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;


public class ListeDemandeUtilisateur extends AppCompatActivity {
    List<String> liste=new ArrayList<>();
    BaseDeDonne bd;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    ProgressDialog mProgressDialog;
    TableLayout tableLayout;
    String ancien,nouveau,confirmation,profile;
    View vieww;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_demande_utilisateur);
        bd=new BaseDeDonne(this);
        TextView nomUser=(TextView)findViewById(R.id.nomUtil);
        tableLayout=(TableLayout)findViewById(R.id.useraffiche);
        tableLayout.setPadding(12,16,12,16);
         profile=bd.retrieveUserProfile(FirebaseAuth.getInstance().getCurrentUser().getEmail());
//         vieww = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_liste_demande_utilisateur, null);

        TableRow tl=new TableRow(this);
        tl.setBackgroundColor(Color.parseColor("#17631E"));
        tl.setPadding(12,16,12,16);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tl.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }
        TextView num=new TextView(this);
        num.setTypeface(null, Typeface.BOLD);
        num.setTextColor(Color.parseColor("#FFFFFF"));
        num.setText("Num.");
        num.setPadding(15,15,15,15);
        //tl.addView(num);

        TextView date=new TextView(this);
        date.setTypeface(null, Typeface.BOLD);
        date.setTextColor(Color.parseColor("#FFFFFF"));
        date.setText("DATE");
        date.setPadding(15,15,15,15);
        tl.addView(date);

        TextView mat=new TextView(this);
        mat.setText("MATERIELS");
        mat.setTextColor(Color.parseColor("#FFFFFF"));
        mat.setTypeface(null, Typeface.BOLD);
        mat.setPadding(15,15,15,15);
        tl.addView(mat);

        TextView prent=new TextView(this);
        prent.setTypeface(null, Typeface.BOLD);
        prent.setTextColor(Color.parseColor("#FFFFFF"));
        prent.setText("QUANTITE");
        prent.setPadding(15,15,15,15);
        tl.addView(prent);

        TextView autre=new TextView(this);
        autre.setTypeface(null, Typeface.BOLD);
        autre.setTextColor(Color.parseColor("#FFFFFF"));
        autre.setText("DEMANDE PAR");
        autre.setPadding(15,15,15,15);
       // tl.addView(autre);

        TextView depart=new TextView(getBaseContext());
        depart.setTypeface(null, Typeface.BOLD);
        depart.setTextColor(Color.parseColor("#FFFFFF"));
        depart.setText("DEPARTEMENT");
        depart.setPadding(15,15,15,15);
        //tl.addView(depart);

        TextView etat=new TextView(getBaseContext());
        depart.setTypeface(null, Typeface.BOLD);
        depart.setTextColor(Color.parseColor("#FFFFFF"));
        depart.setText("STATUT");
        depart.setPadding(15,15,15,15);

        tableLayout.addView(tl,new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        List<DemandeC> affF = bd.afficheDemandeUser(MyApplication.getmAuth().getCurrentUser().getEmail());
        int count = 0;
        String nom = null;
        for (DemandeC emp : affF) {

            TableRow tr = new TableRow(this);
            tr.setPadding(12, 16, 12, 16);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tr.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }
            if (count % 2 != 0) tr.setBackgroundColor(Color.parseColor("#d1d2d2"));

            TextView item1 = new TextView(this);
            item1.setPadding(15, 15, 15, 15);
            item1.setTextColor(Color.parseColor("#000000"));
            item1.setText(String.valueOf(emp.toStringNum()));
            //tr.addView(item1);

            TextView item2 = new TextView(this);
            item2.setPadding(15, 15, 15, 15);
            item2.setTextColor(Color.parseColor("#000000"));
            item2.setText(String.valueOf(emp.toStringDate()));
            tr.addView(item2);

            TextView item3 = new TextView(this);
            item3.setPadding(15, 15, 15, 15);
            item3.setTextColor(Color.parseColor("#000000"));
            item3.setText(emp.toStringLib());
            tr.addView(item3);

            TextView item4 = new TextView(this);
            item4.setPadding(15, 15, 15, 15);
            item4.setTextColor(Color.parseColor("#000000"));
            item4.setText(String.valueOf(emp.toStringQt()));
            tr.addView(item4);

            TextView item5 = new TextView(this);
            item5.setPadding(15, 15, 15, 15);
            item5.setTextColor(Color.parseColor("#000000"));
            item5.setText(emp.toStringNomEmp());
            nom=emp.toStringNomEmp();
            //tr.addView(item5);

            TextView item6 = new TextView(this);
            item6.setPadding(15, 15, 15, 15);
            item6.setTextColor(Color.parseColor("#000000"));
            item6.setText(emp.toStringDepa());
            //tr.addView(item6);

            TextView item7 = new TextView(this);
            item6.setPadding(15, 15, 15, 15);
            item6.setTextColor(Color.parseColor("#000000"));
            item6.setText(emp.toStringEtat());
            tr.addView(item7);

            tableLayout.addView(tr, new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            count++;
        }
        nomUser.setText(nom);
        MyApplication.setNomUser(nom);
        FloatingActionButton ajout=(FloatingActionButton)findViewById(R.id.floatAjout);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListeDemandeUtilisateur.this,Demande.class);
                intent.putExtra("code","utilisateur");
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_affiche2, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.deconnexion:
                MyApplication.getmAuth().signOut();
                finish();
                startActivity(new Intent(this, Welcome.class));
                break;
            case R.id.password:
                final AlertDialog.Builder builder = new AlertDialog.Builder(ListeDemandeUtilisateur.this, 0x00000005);
                final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_input1, null);
                final EditText input1 = (EditText) view.findViewById(R.id.input1);
                final EditText input2 = (EditText) view.findViewById(R.id.input2);
                final EditText input3 = (EditText) view.findViewById(R.id.input3);


                builder.setView(view);
                builder.setTitle("Changer de mot de passe");
                builder.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ancien = input1.getText().toString();
                        nouveau = input2.getText().toString();
                        confirmation = input3.getText().toString();

                        if (nouveau.equals(confirmation)) {
                            AuthCredential credential = EmailAuthProvider.getCredential(MyApplication.getmAuth().getCurrentUser().getEmail(), ancien);
                            MyApplication.getmAuth().getCurrentUser().reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        MyApplication.getmAuth().getCurrentUser().updatePassword(nouveau).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.i("Résultat", "success");
                                                   if(!profile.equals("USER")){
                                                    Snackbar.make(getCurrentFocus(), "Mot de passe changé avec succès!!", Snackbar.LENGTH_LONG)
                                                            .setAction("Action", null).show();
                                                   }
                                                   else {
                                                       Toast.makeText(getApplicationContext(),"Mot de passe changé avec succès!!",Toast.LENGTH_LONG).show();
                                                   }
                                                } else {
                                                    Log.i("Résultat", "failed");
                                                }

                                            }
                                        });
                                    } else {
                                        Log.i("résultat", "not good");
                                    }
                                }
                            });
                        } else {
                            if (profile.equals("USER")) {
                                Snackbar.make(view, "Changement de mot passe a échoué.\nVeuillez recommencer!!!", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Changement de mot passe a échoué.\nVeuillez recommencer!!!",Toast.LENGTH_LONG).show();
                            }
                        }
                        dialog.dismiss();


                    }
                });
                builder.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


                builder.create();
                builder.show();

                break;
        }


                return super.onOptionsItemSelected(item);
        }

}
