package com.example.hp.madose;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class PageDeChargement extends AppCompatActivity {
ProgressDialog mProgressDialog;
    BaseDeDonne bd;
    String TAG="Status";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_de_chargement);
        bd=new BaseDeDonne(this);


        showProgressDialog();
        if(!bd.checkIfTableHasData("Besoins_Sortie") && !bd.checkIfTableHasData("Categorie") && !bd.checkIfTableHasData("Demande") && !bd.checkIfTableHasData("Demande_Besoins") && !bd.checkIfTableHasData("Departement") && !bd.checkIfTableHasData("Utilisateur") && !bd.checkIfTableHasData("Besoin") && !bd.checkIfTableHasData("Besoins_Entree") && !bd.checkIfTableHasData("Entree") && !bd.checkIfTableHasData("Fournisseur") && !bd.checkIfTableHasData("Sortie"))
        {
            bd.insertCat("MATERIEL DE BUREAU");
            bd.insertCat("OUTIL INFORMATIQUE");
            bd.insertCat("MATERIEL DE CUISINE");
            bd.insertCat("MATERIEL D ENTRETIEN");
            bd.insertCat("OUTIL PAUSE CAFE");

            bd.insert("INFORMATIQUE");
            bd.insert("RECHERCHE");
            bd.insert("SECRETARIAT");
            bd.insert("DIRECTION");

            bd.insertBesoin("STYLO", "NON AMORTISSABLE", 1, 3, "0", 2,R.drawable.b21);
            bd.insertBesoin("MARKER", "NON AMORTISSABLE", 1, 2, "0", 0,R.drawable.b22);
            bd.insertBesoin("CORBEILLE", "NON AMORTISSABLE", 4, 2, "0", 0,R.drawable.b10);
            bd.insertBesoin("STICKY NOTES", "NON AMORTISSABLE", 1, 3, "0", 2,R.drawable.b17);
            bd.insertBesoin("CLIMATISEUR", "AMORTISSABLE", 1, 0, "2020-03-25", 0,R.drawable.b9);
            bd.insertBesoin("ORDINATEUR", "AMORTISSABLE", 2, 0, "2020-03-25", 0,R.drawable.b26);
            bd.insertBesoin("IMPRIMANTE", "AMORTISSABLE", 2, 0, "2020-03-25", 0,R.drawable.b14);

            bd.insertFour("CASH CENTER", "01 bp 4236 Abidjan 01", "22445623");
            bd.insertFour("CASH IVOIRE", "01 bp 4036 Abidjan 02", "22441683");
            bd.insertFour("CDCI", "01 bp 1250 Abidjan 10", "22441182");
            bd.insertFour("SOCOCE", "01 bp 4036 Abidjan 28", "22441683");

            bd.insertEmp("KOUADJO","Eric","ekouadjio@idconsulting.ie","01020304", 1, "SUPER ADMIN","YES");
            bd.insertEmp("ADEJINLE","Patrick","padejinle@idconsulting.ie","01020304", 1, "SUPER ADMIN","YES");
            bd.insertEmp("KONE","Myriame","mnayele@idconsulting.ie","01020304",  2, "USER","YES");
            bd.insertEmp("KONE","Seydou","kseydou@idconsulting.ie","01020304", 2, "USER","YES");
            bd.insertEmp("LAGO","Yvon","ylago@idconsulting.ie","01020304", 2, "USER","YES");
            bd.insertEmp("ASSOH EPSE YAPI","Bénédicte","bassoh@idconsulting.ie","01020304", 3, "ADMIN","YES");
            bd.insertEmp("DROH","Agostino","adroh@idconsulting.ie","01020304", 4, "SUPER ADMIN","YES");



           /* bd.insertEntr("2018-01-02", 1,"","padejinle@idconsulting.ie",true);
            bd.insertEntr("2018-01-02", 4,"","padejinle@idconsulting.ie",true);*/


            /*bd.insertEntrBes(1, 1, 150, 5, "Bic", "Bleu");
            int var2 = Integer.parseInt(bd.selectStockBes("STYLO"));
            int var3 = var2 + 5;
            bd.upDate(var3, "STYLO");
            bd.insertEntrBes(2, 1, 250, 5, "Bic", "Noir, permanent");
            var2 = Integer.parseInt(bd.selectStockBes("MARKER"));
            var3 = var2 + 5;
            bd.upDate(var3, "MARKER");
            bd.insertEntrBes(6, 2, 450000, 5, "HP", "Noir, core i5, portable");
            var2 = Integer.parseInt(bd.selectStockBes("ORDINATEUR"));
            var3 = var2 + 5;
            bd.upDate(var3, "ORDINATEUR");
            bd.insertEntrBes(4, 2, 500, 5, "PRIVILEGE", "200 Sticks");
            var2 = Integer.parseInt(bd.selectStockBes("STICKY NOTES"));
            var3 = var2 + 5;
            bd.upDate(var3, "ORDINATEUR");
            bd.insertEntrBes(5, 1, 250000, 3, "SAMSUNG", "Blanc");
            var2 = Integer.parseInt(bd.selectStockBes("IMPRIMANTE"));
            var3 = var2 + 3;
            bd.upDate(var3, "IMPRIMANTE");

            bd.insertSortie("2018-01-10","1",bd.selectCurrentDate(),"adepatrickade@idconsulting.ie",true);

            bd.insertSortieBesoin(1,1,1, "Bic","Bleu");
            var2 = Integer.parseInt(bd.selectStockBes("STYLO"));
            var3 = var2 - 1;
            bd.upDate(var3, "STYLO");

            bd.insertDemande("2018-01-02", 1, 1,bd.selectCurrentDate(),true);
            bd.insertDemandeBesoin(1, 1, 1,"VALIDE");
            bd.insertDemandeBesoin(1, 6, 1,"VALIDE");*/
            MyApplication.setFetch(false);
        }
    /*    MyApplication.getmAuth().signInWithEmailAndPassword("test@idconsulting.ie","password").addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    MyApplication.getmDatabase().child("Categorie").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshotCat:dataSnapshot.getChildren()){
                                CategorieC cat= dataSnapshotCat.getValue(CategorieC.class);
                                if (! bd.checkIfCategorieExist(cat.getLibCat())){
                                    bd.insertCat(cat.getLibCat());
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    MyApplication.getmDatabase().child("Fournisseur").addValueEventListener(new ValueEventListener() {
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
                    MyApplication.getmDatabase().child("Departement").addValueEventListener(new ValueEventListener() {
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
                    MyApplication.getmDatabase().child("Besoin").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshotBes:dataSnapshot.getChildren()){
                                BesoinC cat= dataSnapshotBes.getValue(BesoinC.class);
                                if (!bd.checkIfBesoinExist(cat.getLibBes())){
                                    Log.i("TESTONS",cat.getLibCat());
                                    int ss=Integer.parseInt(bd.selectCat(cat.getLibCat()));
                                    bd.insertBesoin(cat.getLibBes(),cat.getTypeBes(),ss,cat.getSeuilBes(),cat.getAmorBes(),cat.getStockBes(),cat.getImageBes());
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    MyApplication.getmDatabase().child("users").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshotUser:dataSnapshot.getChildren()){
                                UtilisateurC user=dataSnapshotUser.getValue(UtilisateurC.class);
                                //  Toast.makeText(getApplicationContext(),user.getMailEmp(),Toast.LENGTH_SHORT);
                                if (!bd.checkIfUserExist(user)){
                                    Log.i("MONTRE-MOI",user.getLibDep());
                                    int s=Integer.parseInt(bd.selectDep(user.getLibDep()));
                                    bd.insertEmp(user.getNomEmp(),user.getPrenEmp(),user.getMailEmp(),user.getTelEmp(),s,user.getProEmp(),user.getValEmp());

                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                }
            }

        });


        FirebaseAuth.getInstance().signOut();

*/
        hideProgressDialog();
        Intent intent=new Intent(PageDeChargement.this,Welcome.class);
        startActivity(intent);

    }
    @Override
    public void onStart(){

        super.onStart();
        if (MyApplication.getmAuth().getCurrentUser() !=null && ! MyApplication.getmAuth().getCurrentUser().getEmail().toString().equals("test@idconsulting.ie")){
            onAuthSuccess(MyApplication.getmAuth().getCurrentUser());
            Log.i("UNO",MyApplication.getmAuth().getCurrentUser().getEmail());
        }

    }
    private void onAuthSuccess(FirebaseUser user){


        startActivity(new Intent(PageDeChargement.this, Acceuil.class));


    }
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Chargement des données...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
