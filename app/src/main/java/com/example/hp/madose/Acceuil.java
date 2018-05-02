package com.example.hp.madose;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.madose.Listes.BesoinListe;
import com.example.hp.madose.Listes.CategorieListe;
import com.example.hp.madose.Listes.DepartementListe;
import com.example.hp.madose.Listes.FournisseurListe;
import com.example.hp.madose.Listes.ListeDemandeUtilisateur;
import com.example.hp.madose.Listes.ListeDesDemandes;
import com.example.hp.madose.Listes.ListeDesEntrees;
import com.example.hp.madose.Listes.ListeDesSorties;
import com.example.hp.madose.Listes.UtilisateurListe;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;


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
    DatabaseReference nDatabase;
    DatabaseReference vDatabase;
    FirebaseAuth mAuth;
    ProgressDialog mProgressDialog;
    ConnexionDetector connexionDetector;
    String temps;
    String ancien,nouveau,confirmation;
    int statut;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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

        MyApplication.nouv=bd.countAccountNotValidate();

        if (MyApplication.old!=MyApplication.nouv){
            if (MyApplication.nouv!=0 && !profile.equals("USER")) {
                //Notification debut
                NotificationCompat.Builder notification = new NotificationCompat.Builder(Acceuil.this);
                notification.setSmallIcon(R.drawable.ic_monlogo);
                notification.setContentText(MyApplication.nouv + " compte(s) en attente de validation.");
                notification.setContentTitle("RecapApp");
                //  MyApplication.notifications.add("Nouvel approvisionnement.\nEffectué le " + cat.getDatEnt() + " à" + cat.getHeureEnt());

                Intent entree = new Intent(getBaseContext(), NotificationArea.class);
                //entree.putExtra("sortie","listeE");
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getBaseContext());
                stackBuilder.addParentStack(Acceuil.class);
                stackBuilder.addNextIntent(entree);

                notification.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

                PendingIntent resultIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(resultIntent);
                notification.setAutoCancel(true);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Random random = new Random();
                notificationManager.notify(random.nextInt(130000), notification.build());
                //Notification fin
            }
            MyApplication.old=MyApplication.nouv;

        }

        showProgressDialog();
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
        mDatabase.child("Entree").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotEnt:dataSnapshot.getChildren()){
                    AddEC cat= dataSnapshotEnt.getValue(AddEC.class);


                    if (!bd.checkIfEntreeExist(cat.getLibFour(),cat.getHeureEnt(),cat.user)){
                        Log.i("SHOW-ME",cat.getLibFour()+" "+cat.getHeureEnt());
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

                    if (!bd.checkIfBesoinEntreeExist(cat.getLibBes(),cat.getDatEnt(),cat.getHeureEnt(),bd.selectUserEnt(cat.getHeureEnt()))){
                        Log.i("MONTRE-MOI",cat.getLibBes()+" "+cat.getDatEnt()+" "+cat.getHeureEnt()+" "+bd.selectUserEnt(cat.getHeureEnt()));
                        int ss=Integer.parseInt(bd.selectIdBes(cat.getLibBes()));
                        Log.i("REAL",""+cat.getHeureEnt());
                        int sss=Integer.parseInt(bd.selectIdEnt(Integer.parseInt(cat.getHeureEnt())));

                        bd.insertEntrBes(ss,sss,cat.getPU(),cat.getQte(),cat.getMarqueBes(),cat.getAutrePrécision());

                        int var2 = Integer.parseInt(bd.selectStockBes(cat.getLibBes()));
                        int var3 = var2 + cat.getQte();
                        bd.upDate(var3, cat.getLibBes());

                        //Notification debut
                        NotificationCompat.Builder notification=new NotificationCompat.Builder(Acceuil.this);
                        notification.setSmallIcon(R.drawable.ic_monlogo);
                        notification.setContentText("Nouvel approvisionnement");
                        notification.setContentTitle("RecapApp");
                      //  MyApplication.notifications.add("Nouvel approvisionnement.\nEffectué le " + cat.getDatEnt() + " à" + cat.getHeureEnt());

                        Intent entree=new Intent(getBaseContext(),ListeDesEntrees.class);
                        entree.putExtra("sortie","listeE");
                        TaskStackBuilder stackBuilder=TaskStackBuilder.create(getBaseContext());
                        stackBuilder.addParentStack(Acceuil.class);
                        stackBuilder.addNextIntent(entree);

                        notification.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

                        PendingIntent resultIntent= stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                        notification.setContentIntent(resultIntent);
                        notification.setAutoCancel(true);
                        NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        Random random=new Random();
                        notificationManager.notify(random.nextInt(130000),notification.build());
                        //Notification fin

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
                            bd.insertDemandeBesoin(Integer.parseInt(bd.selectIdDem(cat.getHeureDem())),ssss,cat.getQte());
                        }

                        //Notification debut
                       NotificationCompat.Builder notification=new NotificationCompat.Builder(getBaseContext());
                        notification.setSmallIcon(R.drawable.ic_monlogo);
                        notification.setContentText("Demande faite par "+cat.getNomEmp());
                        notification.setContentTitle("RecapApp");
                      //  MyApplication.notifications.add("Nouvelle demande.\nEffectuée le " + cat.getDateDem() + " à" + cat.getHeureDem());

                        Intent listedemande=new Intent(getBaseContext(),ListeDesDemandes.class);
                        listedemande.putExtra("sortie","listeD");
                        TaskStackBuilder stackBuilder=TaskStackBuilder.create(getBaseContext());
                        stackBuilder.addParentStack(Acceuil.class);
                        stackBuilder.addNextIntent(listedemande);

                        notification.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

                        PendingIntent resultIntent= stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                        notification.setContentIntent(resultIntent);
                        notification.setAutoCancel(true);
                        NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        Random random=new Random();
                        notificationManager.notify(random.nextInt(130000),notification.build());
                        //Notification fin

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase.child("Sorties").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int compter=0;
                for (DataSnapshot dataSnapshotSor:dataSnapshot.getChildren()){
                    Stock2 cat= dataSnapshotSor.getValue(Stock2.class);
                    Log.i("VOILA SORTIE",cat.getNomEmp()+" "+cat.getDateDem()+" "+cat.getLibDep());
                    if (! bd.checkIfSortieEntreeExist(cat.getHeureSor(),cat.getLibBes())){
                        Log.i("VOILAHEIN",cat.getNomEmp()+" "+cat.getHeureSor()+" "+cat.getLibBes()+" "+cat.getDate());
                        if (! bd.checkIfSortieExist(cat.getNomEmp(),cat.getHeureSor(),cat.getLibDep())){
                            Log.i("VOILATOUT",cat.getNomEmp()+" "+cat.getHeureSor());
                            String numDem=bd.selectNumeDem(cat.getDateDem(),cat.getNomEmp(),cat.getLibDep());
                            Log.i("TOUTDEH",""+numDem);
                            bd.insertSortie(cat.getDate(),numDem,cat.getHeureSor(),cat.getNomEmp(),false);
                            compter++;
                        }
                        int var1=Integer.parseInt(bd.selectIdSortie());
                        int var2=Integer.parseInt(bd.selectIdBes(cat.getLibBes()));
                        bd.insertSortieBesoin(var1,var2,cat.getQte(),cat.getMarqBes(),cat.getAutreP());

                        int varP = Integer.parseInt(bd.selectStockBes(cat.getLibBes()));
                        int var3 = varP - cat.getQte();
                        bd.upDate(var3, cat.getLibBes());

                        //Notification debut
                        NotificationCompat.Builder notification=new NotificationCompat.Builder(getBaseContext());
                        notification.setSmallIcon(R.drawable.ic_monlogo);
                        notification.setContentText("Nouvelle Sortie");
                        notification.setContentTitle("RecapApp");
                       // MyApplication.notifications.add("Nouvelle sortie.\nEnregistrée le " + cat.getDate() + " à" + cat.getHeureSor());

                        Intent sortie=new Intent(getBaseContext(),ListeDesSorties.class);
                        sortie.putExtra("sortie","listeS");
                        TaskStackBuilder stackBuilder=TaskStackBuilder.create(getBaseContext());
                        stackBuilder.addParentStack(Acceuil.class);
                        stackBuilder.addNextIntent(sortie);

                        notification.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);


                        PendingIntent resultIntent= stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                        notification.setContentIntent(resultIntent);
                        notification.setAutoCancel(true);
                        NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        Random random=new Random();
                        notificationManager.notify(random.nextInt(130000),notification.build());
                        //Notification fin
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        String username=usernameFromEmail(MyApplication.getmAuth().getCurrentUser().getEmail());
        String reste=MyApplication.getmAuth().getCurrentUser().getEmail().substring(username.length()+1,MyApplication.getmAuth().getCurrentUser().getEmail().length()-3);
        String rest=MyApplication.getmAuth().getCurrentUser().getEmail().substring(MyApplication.getmAuth().getCurrentUser().getEmail().length()-2,MyApplication.getmAuth().getCurrentUser().getEmail().length());
        String code=username+"-"+reste+"-"+rest;
        mDatabase.child("Connectivité").child(code).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String time=dataSnapshot.getValue(String.class);
                String a,b,c,d,e,f;
                a=time.substring(0,4);
                b=time.substring(4,6);
                c=time.substring(6,8);
                d=time.substring(9,11);
                e=time.substring(11,13);
                f=time.substring(13,15);
                temps=c+"-"+b+"-"+a+" "+d+":"+e+":"+f;


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


        hideProgressDialog();
      /*  AuthCredential credential= EmailAuthProvider.getCredential(MyApplication.getmAuth().getCurrentUser().getEmail(),"goodtop11");
        MyApplication.getmAuth().getCurrentUser().reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    MyApplication.getmAuth().getCurrentUser().updatePassword("adejinle114").addOnCompleteListener( new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Log.i("Résultat","success");
                            }
                            else {
                                Log.i("Résultat","failed");
                            }

                        }
                    });
                }
                else{
                    Log.i("résultat","not good");
                }
            }
        });  */

        if (profile.equals("USER")){
            Intent intent=new Intent(Acceuil.this,ListeDemandeUtilisateur.class);
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
            TextView connect= findViewById(R.id.textView15);
            status.setText("\nConnecté en tant que: " + MyApplication.getmAuth().getCurrentUser().getEmail());
            connect.setText("Dernière action sur le serveur: "+ bd.retrieveUserConnect(MyApplication.getmAuth().getCurrentUser().getEmail()));
            Log.i("ade","fait bien"+bd.retrieveUserConnect(MyApplication.getmAuth().getCurrentUser().getEmail()));
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
                Intent c = new Intent(Acceuil.this, UtilisateurListe.class);
                c.putExtra("passage", "employe");
                startActivity(c);
                break;
            case R.id.fournisseur:
                Intent g = new Intent(Acceuil.this, FournisseurListe.class);
                g.putExtra("passage", "fournisseur");
                startActivity(g);
                break;
            case R.id.departement:
                Intent p = new Intent(Acceuil.this, DepartementListe.class);
                p.putExtra("passage", "departement");
                startActivity(p);
                break;
            case R.id.categorie:
                Intent k = new Intent(Acceuil.this, CategorieListe.class);
                k.putExtra("passage", "categorie");
                startActivity(k);
                break;
            case R.id.besoin:
                Intent b = new Intent(Acceuil.this, BesoinListe.class);
                b.putExtra("passage", "besoin");
                startActivity(b);
                break;
            case R.id.notif_area:
                Intent adej=new Intent(Acceuil.this,NotificationArea.class);
                adej.putExtra("passage","notif");
                startActivity(adej);
                break;
            case R.id.password_change:

                final AlertDialog.Builder builder = new AlertDialog.Builder(Acceuil.this, 0x00000005);
                final View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_input1,null);
                final EditText input1 = (EditText) view.findViewById(R.id.input1);
                final EditText input2 = (EditText) view.findViewById(R.id.input2);
                final EditText input3 = (EditText) view.findViewById(R.id.input3);


                builder.setView(view);
                builder.setTitle("Changer de mot de passe");
                builder.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ancien=input1.getText().toString();
                        nouveau=input2.getText().toString();
                        confirmation=input3.getText().toString();
                        dialog.dismiss();
                        if (nouveau.equals(confirmation)){
                            AuthCredential credential= EmailAuthProvider.getCredential(MyApplication.getmAuth().getCurrentUser().getEmail(),ancien);
                            MyApplication.getmAuth().getCurrentUser().reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        MyApplication.getmAuth().getCurrentUser().updatePassword(nouveau).addOnCompleteListener( new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Log.i("Résultat","success");
                                                    Snackbar.make(getCurrentFocus(), "Mot de passe changé avec succès!!", Snackbar.LENGTH_LONG)
                                                            .setAction("Action", null).show();
                                                }
                                                else {
                                                    Log.i("Résultat","failed");
                                                }

                                            }
                                        });
                                    }
                                    else{
                                        Log.i("résultat","not good");
                                    }
                                }
                            });
                        }
                        else {
                            Snackbar.make(view, "Changement de mot passe a échoué.\nVeuillez recommencer!!!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }


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
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, Welcome.class));
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
    private void writeNewUser(String userId, String name, String surname, String email, String tel, String department, String profile, String validate) {
        UtilisateurC user = new UtilisateurC(name, surname, email, tel, department, profile,validate);
        mDatabase.child("users").child(userId).setValue(user);
    }
    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }


@Override
    public void onStart(){


    super.onStart();



        String profile=bd.retrieveUserProfile(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        if (profile.equals("USER")){
            Intent intent=new Intent(Acceuil.this,ListeDesDemandes.class);
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
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Mise à jour...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }



}
