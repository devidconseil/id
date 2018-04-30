package com.example.hp.madose;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by HP on 28/12/2017.
 */

public class MyApplication extends Application {



    public static String mGlobalVarValue;
    public static int CoutTotBes;
    public static boolean done=false;
    public static boolean check=false;
    public static boolean fetch=true;
    public static boolean verif=false;
    public static boolean fait=false;
    public static boolean newAccount=false;
    public static String verif1;
    public static String categorie;
    public static String mail;
    public static int id;
    public static boolean textView;
    public static String employe;
    public static FirebaseAuth mAuth=FirebaseAuth.getInstance();
    public static DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference();
    public static List<String> notifications=new ArrayList<>();
    public static int old,nouv=0;


    public static String getCategorie() {
        return categorie;
    }

    public static boolean isNewAccount() {
        return newAccount;
    }

    public static void setNewAccount(boolean newAccount) {
        MyApplication.newAccount = newAccount;
    }

    public static void setCategorie(String categorie) {
        MyApplication.categorie = categorie;
    }

    public static boolean isFetch() {
        return fetch;
    }

    public static boolean isFait() {
        return fait;
    }

    public static List<String> getNotifications() {
        return notifications;
    }

    public static void setNotifications(List<String> notifications) {
        MyApplication.notifications = notifications;
    }

    public static String getMail() {
        return mail;
    }

    public static void setMail(String mail) {
        MyApplication.mail = mail;
    }

    public static void setFait(boolean fait) {
        MyApplication.fait = fait;
    }

    public static boolean isTextView() {
        return textView;
    }

    public static void setTextView(boolean textView) {
        MyApplication.textView = textView;
    }

    public static int getId() {
        return id;
    }

    public static String getEmploye() {
        return employe;
    }

    public static void setEmploye(String employe) {
        MyApplication.employe = employe;
    }

    public static void setId(int id) {
        MyApplication.id = id;
    }

    public static void setFetch(boolean fetch) {
        MyApplication.fetch = fetch;
    }

    public static boolean isCheck() {
        return check;
    }

    public static void setCheck(boolean check) {
        MyApplication.check = check;
    }

    public static boolean isDone() {
        return done;
    }

    public static void setDone(boolean done) {
        MyApplication.done = done;
    }

    public static int getCoutTotBes() {
        return CoutTotBes;
    }

    public static boolean isVerif() {
        return verif;
    }

    public static void setVerif(boolean verif) {
        MyApplication.verif = verif;
    }

    public static void setCoutTotBes(int coutTotBes) {
        CoutTotBes = coutTotBes;
    }

    public static String getGlobalVarValue() {
        return mGlobalVarValue;
    }

    public static void setGlobalVarValue(String globalVarValue) {
        mGlobalVarValue = globalVarValue;
    }

    public static FirebaseAuth getmAuth() {
        return mAuth;
    }

    public static void setmAuth(FirebaseAuth mAuth) {
        MyApplication.mAuth = mAuth;
    }

    public static DatabaseReference getmDatabase() {
        return mDatabase;
    }

    public static void setmDatabase(DatabaseReference mDatabase) {
        MyApplication.mDatabase = mDatabase;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        final BaseDeDonne bd=new BaseDeDonne(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
       DatabaseReference userRef= FirebaseDatabase.getInstance().getReference("users");
       userRef.keepSynced(true);


        mDatabase.child("Fournisseur").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotFour:dataSnapshot.getChildren()){
                    FournisseurC four=dataSnapshotFour.getValue(FournisseurC.class);
                    if (!bd.checkIfFournisseurExist(four.getNomFour())){
                        bd.insertFour(four.getNomFour(),four.getAdrFour(),four.getTelFour());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("Departement").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotDepart:dataSnapshot.getChildren()){
                    DepartementC depart=dataSnapshotDepart.getValue(DepartementC.class);
                    if (!bd.checkIfDepartmentExist(depart.getLibDep())){
                        bd.insert(depart.getLibDep());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("Categorie").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotCat:dataSnapshot.getChildren()){
                    CategorieC cat= dataSnapshotCat.getValue(CategorieC.class);
                    if (!bd.checkIfCategorieExist(cat.getLibCat())){
                        bd.insertCat(cat.getLibCat());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotUser:dataSnapshot.getChildren()){
                    UtilisateurC user=dataSnapshotUser.getValue(UtilisateurC.class);
                    Log.i("CHAQUE USER",user.getMailEmp());
                    if (!bd.checkIfUserExist(user)){
                        int s=Integer.parseInt(bd.selectDep(user.getLibDep()));
                        bd.insertEmp(user.getNomEmp(),user.getPrenEmp(),user.getMailEmp(),user.getTelEmp(),s,user.getProEmp(),user.getValEmp());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("Besoin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotBes:dataSnapshot.getChildren()){
                    BesoinC cat= dataSnapshotBes.getValue(BesoinC.class);
                    if (!bd.checkIfBesoinExist(cat.getLibBes())){
                        int ss=Integer.parseInt(bd.selectCat(cat.getLibCat()));
                        bd.insertBesoin(cat.getLibBes(),cat.getTypeBes(),ss,cat.getSeuilBes(),cat.getAmorBes(),cat.getStockBes(),cat.getImageBes());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("Entree").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotEnt:dataSnapshot.getChildren()){
                    AddEC cat= dataSnapshotEnt.getValue(AddEC.class);


                    if (!bd.checkIfEntreeExist(cat.getLibFour(),cat.getHeureEnt(),cat.user)){
                        Log.i("SHOW-ME",cat.getLibFour());
                        int ss=Integer.parseInt(bd.selectFour(cat.getLibFour()));
                        bd.insertEntr(cat.getDatEnt(),ss,cat.getHeureEnt(),cat.getUser(),false);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("Besoins-Entree").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotBesEnt:dataSnapshot.getChildren()){
                    AddBEC cat= dataSnapshotBesEnt.getValue(AddBEC.class);

                    if (!bd.checkIfBesoinEntreeExist(cat.getLibBes(),cat.getDatEnt(),bd.selectHeureEnt(),bd.selectUserEnt(bd.selectHeureEnt()))){
                        int ss=Integer.parseInt(bd.selectIdBes(cat.getLibBes()));
                        int sss=Integer.parseInt(bd.selectIdEnt(cat.getDatEnt()));
                        bd.insertEntrBes(ss,sss,cat.getPU(),cat.getQte(),cat.getMarqueBes(),cat.getAutrePrécision());
                        int var2 = Integer.parseInt(bd.selectStockBes(cat.getLibBes()));
                        int var3 = var2 + cat.getQte();
                        bd.upDate(var3, cat.getLibBes());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("Demande").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotDem:dataSnapshot.getChildren()){
                    DemandeC cat= dataSnapshotDem.getValue(DemandeC.class);
                    Log.i("I MISS YOU",cat.getDateDem()+" "+cat.getNomEmp());

                    if (! bd.checkIfDemandeBesoinExist(cat.getHeureDem(),cat.getLibBes())){

                        int ssss=Integer.parseInt(bd.selectIdBes(cat.getLibBes()));
                        if (cat.getLibDpe().equals("")){
                            int ss=Integer.parseInt(bd.selectEmpId(cat.getNomEmp()));
                            int sss=Integer.parseInt(bd.selectDep(bd.DepartEmp(ss)));
                            if (! bd.checkIfDemandeExist(cat.getNomEmp(),cat.getHeureDem(),cat.getLibDpe())) {
                                bd.insertDemande(cat.getDateDem(), ss, sss, cat.getHeureDem(), false);
                            }
                            bd.insertDemandeBesoin(Integer.parseInt(bd.selectIdDem(cat.getHeureDem())),ssss,cat.getQte());
                            //   Toast.makeText(getApplicationContext(),cat.toString(),Toast.LENGTH_LONG).show();
                        }
                        if (cat.getNomEmp().equals("")) {
                            int ss=0;
                            int sss=Integer.parseInt(bd.selectDep(cat.getLibDpe()));
                            bd.insertDemande1(cat.getDateDem(),sss,cat.getHeureDem(),false);
                            bd.insertDemandeBesoin(Integer.parseInt(bd.selectIdDem1(cat.getLibDpe(),cat.getDateDem())),ssss,cat.getQte());
                        }

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        MyApplication.getmDatabase().child("Sorties").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotSor:dataSnapshot.getChildren()){
                    Stock2 cat=dataSnapshotSor.getValue(Stock2.class);
                    Log.i("VOILA SORTIE",cat.getNomEmp()+" "+cat.getDateDem());
                    if (!bd.checkIfSortieEntreeExist(cat.getHeureSor(),cat.getLibBes())){
                        if (! bd.checkIfSortieExist(cat.getNomEmp(),cat.getHeureSor(),cat.getLibDep())){
                            String numDem=bd.selectNumeDem(cat.getDateDem(),cat.getNomEmp());
                            bd.insertSortie(cat.getDate(),numDem,cat.getHeureSor(),cat.getNomEmp(),false);
                        }
                        int var1=Integer.parseInt(bd.selectIdSortie());
                        int var2=Integer.parseInt(bd.selectIdBes(cat.getLibBes()));
                        bd.insertSortieBesoin(var1,var2,cat.getQte(),cat.getMarqBes(),cat.getAutreP());
                        int varP = Integer.parseInt(bd.selectStockBes(cat.getLibBes()));
                        int var3 = varP - cat.getQte();
                        bd.upDate(var3, cat.getLibBes());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

}
