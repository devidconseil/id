package com.example.hp.madose;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by HP on 18/04/2018.
 */

public class Welcome extends AppCompatActivity {
      BaseDeDonne bd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button connect= findViewById(R.id.button2);
        Button account= findViewById(R.id.button4);
        bd=new BaseDeDonne(this);
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



            bd.insertEntr("2018-01-02", 1,"","padejinle@idconsulting.ie",true);
            bd.insertEntr("2018-01-02", 4,"","padejinle@idconsulting.ie",true);


            bd.insertEntrBes(1, 1, 150, 5, "Bic", "Bleu");
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
            bd.insertDemandeBesoin(1, 6, 1,"VALIDE");
            MyApplication.setFetch(false);
        }

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this,Authentification.class));
                finish();
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Welcome.this,Utilisateur.class);
                intent.putExtra("status","new user request");
                startActivity(intent);
                MyApplication.setNewAccount(true);
                finish();
            }
        });

        if (getIntent().getExtras() != null && getIntent().getExtras().getBoolean("EXIT", false)) {
            finish();
        }
    }

    @Override
    public void onStart(){
        super.onStart();

        FirebaseUser currentUser = MyApplication.mAuth.getCurrentUser();
        if (MyApplication.getmAuth().getCurrentUser() !=null && ! MyApplication.getmAuth().getCurrentUser().getEmail().toString().equals("test@idconsulting.ie")){
            onAuthSuccess(MyApplication.getmAuth().getCurrentUser());
            Log.i("UNO",MyApplication.getmAuth().getCurrentUser().getEmail());
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed(){
        finishAndRemoveTask();
    }

    private void onAuthSuccess(FirebaseUser user){
        String username = usernameFromEmail(user.getEmail());

        startActivity(new Intent(Welcome.this, Acceuil.class));


    }
    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }
}
