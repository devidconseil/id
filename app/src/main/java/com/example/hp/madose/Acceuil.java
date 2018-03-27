package com.example.hp.madose;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import com.example.hp.madose.Listes.FournisseurListe;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class Acceuil extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private BaseDeDonne bd;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toolbar toolbar1=(Toolbar) findViewById(R.id.toolbarA);
        setSupportActionBar(toolbar1);
        if (getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //toolbar.setVisibility(View.INVISIBLE);
        }
        bd = new BaseDeDonne(this);

        mDatabase= FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        Toast.makeText(getApplicationContext(),today.toString(),Toast.LENGTH_LONG);
        String profile=bd.retrieveUserProfile(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        if (profile.equals("USER")){
            Intent intent=new Intent(Acceuil.this,Affichage.class);
            intent.putExtra("passage","demande");
            startActivity(intent);
            finish();
        }

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.getItem(3);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);


        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


/*if(!bd.checkIfTableHasData("Besoins_Sortie") && !bd.checkIfTableHasData("Categorie") && !bd.checkIfTableHasData("Demande") && !bd.checkIfTableHasData("Demande_Besoins") && !bd.checkIfTableHasData("Departement") && !bd.checkIfTableHasData("Utilisateur") && !bd.checkIfTableHasData("Besoin") && !bd.checkIfTableHasData("Besoins_Entree") && !bd.checkIfTableHasData("Entree") && !bd.checkIfTableHasData("Fournisseur") && !bd.checkIfTableHasData("Sortie"))
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

    bd.insertBesoin("TABLE", "AMORTISSABLE", 1, 0, "2020-03-25", 2,R.drawable.b5);
    bd.insertBesoin("CAHIER", "NON AMORTISSABLE", 1, 2, "0", 0,R.drawable.b6);
    bd.insertBesoin("CLIMATISEUR", "AMORTISSABLE", 4, 0, "2020-03-25", 0,R.drawable.b9);
    bd.insertBesoin("CISEAU", "NON AMORTISSABLE", 1, 3, "0", 2,R.drawable.b7);
    bd.insertBesoin("STYLO", "NON AMORTISSABLE", 1, 2, "0", 0,R.drawable.b21);
    bd.insertBesoin("ORDINATEUR", "AMORTISSABLE", 2, 0, "2020-03-25", 0,R.drawable.b26);
    bd.insertBesoin("IMPRIMANTE", "AMORTISSABLE", 2, 0, "2020-03-25", 0,R.drawable.b14);

    bd.insertFour("CASH CENTER", "01 bp 4236 Abidjan 01", 22445623);
    bd.insertFour("CASH IVOIRE", "01 bp 4036 Abidjan 02", 22441683);
    bd.insertFour("CDCI", "01 bp 1250 Abidjan 10", 22441182);
    bd.insertFour("SOCOCE", "01 bp 4036 Abidjan 28", 22441683);

    bd.insertEmp("KOUADJO","Eric","ekouadjio@idconsulting.ie","01020304", 1, "SUPER ADMIN");
    bd.insertEmp("ADEJINLE","Patrick","padejinle@idconsulting.ie","01020304", 1, "SUPER ADMIN");
    bd.insertEmp("KONE","Myriame","mnayele@idconsulting.ie","01020304",  2, "USER");
    bd.insertEmp("KONE","Seydou","kseydou@idconsulting.ie","01020304", 2, "USER");
    bd.insertEmp("LAGO","Yvon","ylago@idconsulting.ie","01020304", 2, "USER");
    bd.insertEmp("ASSOH EPSE YAPI","Bénédicte","bassoh@idconsulting.ie","01020304", 3, "ADMIN");



    bd.insertEntr("2018-01-02", 1);
    bd.insertEntr("2018-01-02", 4);


    bd.insertEntrBes(1, 1, 150, 5, "Bic", "bleu");
    bd.insertEntrBes(2, 1, 250, 5, "Bic", "noir, permanent");
    bd.insertEntrBes(6, 2, 450000, 5, "HP", "noir, core i5, portable");
    bd.insertEntrBes(4, 2, 250000, 5, "HP Laser", "blanc");
    bd.insertEntrBes(5, 1, 500, 3, "PRIVILEGE", "200 pages");

    bd.insertDemande("2018-01-02", 1, 1);
    bd.insertDemandeBesoin(1, 1, 1);
    bd.insertDemandeBesoin(1, 6, 1);
    MyApplication.setFetch(false);
}  */

        bd.RupureCheck();
/*        if (MyApplication.isDone() && !MyApplication.isCheck()){
            AlertDialog.Builder builder = new AlertDialog.Builder(Acceuil.this,0x00000005 );
            builder.setMessage("Certains de vos articles sont en rupture");
            builder.setTitle("Rupture");
            builder.setPositiveButton("Voir", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    MyApplication.setCheck(true);
                    Intent intent=new Intent(Acceuil.this,Affichage.class);
                    intent.putExtra("passage","Rupture_original");
                    startActivity(intent);
                }
            });
            builder.create();
            builder.show();
            builder.setCancelable(false);
        }   */

/*mmmmmmmmmmmmmmmmmmmmmmmmmmm zone de turbulence mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm*/

       /* cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Acceuil.this, Variation.class);
                intent.putExtra("code","accueil");
                startActivity(intent);
            }
        });*/

/*mmmmmmmmmmmmmmmmmmmmmmmmmm fin de turbulence mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


       // ATTENTION: This was auto-generated to implement the App Indexing API
       //See https://g.co/AppIndexing/AndroidStudio for more information.

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.acceuil, menu);
        if (! bd.retrieveUserProfile(MyApplication.getmAuth().getCurrentUser().getEmail()).equals("USER")) {
            TextView status = findViewById(R.id.textView13);
            status.setText("\nConnecté en tant que: " + MyApplication.getmAuth().getCurrentUser().getEmail());
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.employé:
                Intent c = new Intent(Acceuil.this, Affichage.class);
                c.putExtra("passage", "employe");
                startActivity(c);
                break;
            case R.id.fournisseur:
                Intent g = new Intent(Acceuil.this, Affichage.class);
                g.putExtra("passage", "fournisseur");
                startActivity(g);
                break;
            case R.id.departement:
                Intent p = new Intent(Acceuil.this, Affichage.class);
                p.putExtra("passage", "departement");
                startActivity(p);
                break;
            case R.id.categorie:
                Intent k = new Intent(Acceuil.this, Affichage.class);
                k.putExtra("passage", "categorie");
                startActivity(k);
                break;
            case R.id.besoin:
                Intent b = new Intent(Acceuil.this, Affichage.class);
                b.putExtra("passage", "besoin");
                startActivity(b);
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, Authentification.class));


                break;


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private void writeNewUser(String userId, String name, String surname, String email, String tel, String department, String profile) {
        UtilisateurC user = new UtilisateurC(name, surname, email, tel, department, profile);
        mDatabase.child("users").child(userId).setValue(user);
    }

@Override
    public void onStart(){


    super.onStart();



        String profile=bd.retrieveUserProfile(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        if (profile.equals("USER")){
            Intent intent=new Intent(Acceuil.this,Affichage.class);
            intent.putExtra("passage","demande");
            startActivity(intent);
            finish();
        }
    }

    public void writeNewAdd(String heureEnt,String libBes,String datEnt, int pU, int qte, String marqueBes, String autrePrécision){
        String code=libBes+"-"+datEnt;
        if (libBes.contains(" ")){
            libBes=libBes.replace(" ","-");
            code=libBes+"-"+datEnt;
        }
        if (datEnt.contains("/")){
            datEnt=datEnt.replace("/","-");
            code=libBes+"-"+datEnt;
        }
        if (libBes.contains("'")){
            code=code.replace("'","-");
        }

        AddBEC cat=new AddBEC(heureEnt,libBes,datEnt,pU,qte, marqueBes, autrePrécision);
        mDatabase.child("Besoins-Entree").child(code).setValue(cat);
    }
    public void writeNewEntree(String libFour, String datEnt){
        String code=libFour+"-"+datEnt;
        if (libFour.contains(" ")){
            libFour=libFour.replace(" ","-");
            code=libFour+"-"+datEnt;
        }
        if (datEnt.contains("/")){
            datEnt=datEnt.replace("/","-");
            code=libFour+"-"+datEnt;
        }
        if (libFour.contains("'")){
            code=code.replace("'","-");
        }

        AddEC cat=new AddEC(libFour,datEnt);
        mDatabase.child("Entree").child(code).setValue(cat);
    }



    /**
     * A placeholder fragment containing a simple view.
     */

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Frag_accueil_ac fra1=new Frag_accueil_ac();
                    return fra1;

                case 1:
                    Frag_accueil_enregistrer fra2=new Frag_accueil_enregistrer();
                    return fra2;
                case 2:
                    Frag_accueil_listes fra3=new Frag_accueil_listes();
                    return fra3;
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }



}
