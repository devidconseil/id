package com.example.hp.madose;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by HP on 05/12/2017.
 */

public class BaseDeDonne extends SQLiteOpenHelper {

    private static final String MABASE = "gestion.db";


    private static final String TABLE_BESOIN_SORTIE = "CREATE TABLE Besoins_Sortie ( `NumSor` INTEGER, `NumBes` INTEGER, `Qte` INTEGER NOT NULL, `MarqueBes` TEXT, `Autre précision` TEXT," +
            " PRIMARY KEY(NumSor,NumBes), FOREIGN KEY(NumSor) REFERENCES Sortie(NumSor), FOREIGN KEY(NumBes) REFERENCES Besoin(NumBes) );";
    private static final String TABLE_CATEGORIE = "CREATE TABLE Categorie ( `IdCat` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `LibCat` TEXT NOT NULL );";
    private static final String TABLE_DEMANDE = "CREATE TABLE Demande ( `NumDem` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `DateDem` INTEGER NOT NULL, `IdEmp` INTEGER, `IdDep` INTEGER, " +
            "FOREIGN KEY(`IdEmp`) REFERENCES `Utilisateur`(`IdEmp`), FOREIGN KEY(`IdDep`) REFERENCES `Departement`(`IdDep`) );";
    private static final String TABLE_DEMANDE_BESOIN = "CREATE TABLE Demande_Besoins ( `NumDem` INTEGER, `NumBes` INTEGER, `Qte` INTEGER NOT NULL, FOREIGN KEY(`NumBes`) REFERENCES `Besoin`(`NumBes`), PRIMARY KEY(`NumDem`,`NumBes`), FOREIGN KEY(`NumDem`) REFERENCES `Demande`(`NumDem`) );";
    private static final String TABLE_DEPARTEMENT = "CREATE TABLE Departement ( `IdDep` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `LibDep` TEXT NOT NULL );";
    private static final String TABLE_USER = "CREATE TABLE Utilisateur ( `IdEmp` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `NomEmp` TEXT NOT NULL,`PrenEmp` TEXT,`MailEmp` TEXT NOT NULL,`TelEmp` TEXT NOT NULL,`IdDep` INTEGER, `ProEmp` TEXT, FOREIGN KEY(`IdDep`) REFERENCES `Departement`(`IdDep`) );";
    private static final String TABLE_BESOIN = "CREATE TABLE Besoin ( NumBes INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, LibBes TEXT NOT NULL, TypeBes TEXT NOT NULL, IdCat INTEGER," +
            " SeuilBes INTEGER, AmorBes INTEGER,`StockBes` INTEGER, `Image` INTEGER, FOREIGN KEY(IdCat) REFERENCES Categorie(IdCat) );";
    private static final String TABLE_BESOIN_ENTREE = "CREATE TABLE Besoins_Entree ( NumBes INTEGER, NumEnt INTEGER, PU INTEGER, Qte INTEGER NOT NULL, MarqueBes TEXT, Autre_Précision TEXT," +
            " PRIMARY KEY(NumBes,NumEnt), FOREIGN KEY(NumEnt) REFERENCES Entree(NumEnt), FOREIGN KEY(NumBes) REFERENCES Besoin(NumBes) );";
    private static final String TABLE_ENTREE = "CREATE TABLE Entree ( `NumEnt` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `DateEnt` INTEGER NOT NULL, `IdFour` INTEGER, FOREIGN KEY(`IdFour`) REFERENCES `Fournisseur`(`IdFour`));";
    private static final String TABLE_FOURNISSEUR = "CREATE TABLE Fournisseur ( `IdFour` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `NomFour` TEXT NOT NULL, `AdrFour` TEXT, `TelFour` TEXT);";
    private static final String TABLE_SORTIE = "CREATE TABLE Sortie ( `NumSor` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `DateSor` INTEGER NOT NULL, `NumDem` INTEGER, FOREIGN KEY(`NumDem`) REFERENCES `Demande`(`NumDem`));";
    //   NomEmp,LibDep,ProEmp,IdDep
    //DateSor NumDem** NumSor NumBes Qte MarqueBes Autre précision
    public BaseDeDonne(Context context) {
        super(context, MABASE, null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Fournisseur");
        db.execSQL("drop table if exists Sortie");
        db.execSQL("drop table if exists Entree");
        db.execSQL("drop table if exists Utilisateur");
        db.execSQL("drop table if exists Departement");
        db.execSQL("drop table if exists Demande_Besoins");
        db.execSQL("drop table if exists Demande");
        db.execSQL("drop table if exists Categorie");
        db.execSQL("drop table if exists Besoins_Sortie");
        db.execSQL("drop table if exists Besoins_Entree");
        db.execSQL("drop table if exists Besoin");

        onCreate(db);
        Log.i("DATABASE", "onUpgrade invoked");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_FOURNISSEUR);
        db.execSQL(TABLE_SORTIE);
        db.execSQL(TABLE_ENTREE);
        db.execSQL(TABLE_USER);
        db.execSQL(TABLE_DEMANDE);
        db.execSQL(TABLE_DEPARTEMENT);
        db.execSQL(TABLE_DEMANDE_BESOIN);
        db.execSQL(TABLE_CATEGORIE);
        db.execSQL(TABLE_BESOIN);
        db.execSQL(TABLE_BESOIN_ENTREE);
        db.execSQL(TABLE_BESOIN_SORTIE);

        Log.i("DATABASE", "onCreate invoked");

    }



    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    //<<--------------------------********Departement********------------------------------------->>
    //Insert
    public void insert(String libelle) {
        String entre = "insert into Departement ( LibDep )values('" + libelle + "');";
        this.getWritableDatabase().execSQL(entre);
        Log.i("DATABSE", "insert invoked");
    }

    //Display
    public List<DepartementC> afficheDepart() {
        List<DepartementC> aff = new ArrayList<>();
        String req = "select * from Departement;";
        Cursor cursor = this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            DepartementC affi = new DepartementC(cursor.getInt(0), cursor.getString(1));
            aff.add(affi);
            cursor.moveToNext();
        }
        cursor.close();
        return aff;
    }


    //<<---------------------------********Categorie********-------------------------------------->>
    //Insert
    public void insertCat(String libelle) {
        String entre = "insert into Categorie ( LibCat )values('" + libelle + "');";
        Log.i("DATABSE", "insert categorie");
        this.getWritableDatabase().execSQL(entre);
    }

    //Display
    public List<CategorieC> afficheCat() {
        List<CategorieC> affC = new ArrayList<>();
        String req = "select * from Categorie;";
        Cursor cursor = this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            CategorieC disp = new CategorieC(cursor.getInt(0), cursor.getString(1));
            affC.add(disp);
            cursor.moveToNext();
        }
        cursor.close();
        return affC;
    }
    //Textautocomplete
    public ArrayList<String> affiCC()
    {
        ArrayList<String>nc=new ArrayList<>();
        String req="select LibCat from Categorie;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            nc.add(cursor.getString(0));
            cursor.moveToNext();
        }

        cursor.close();
        return nc;
    }

    //<<----------------------------********Employé********--------------------------------------->>
    //Insert
    public void insertEmp(String nom, String prenom, String mail, String tel, int dep, String profil)
    {
        String entre = "insert into Utilisateur ( NomEmp, PrenEmp, MailEmp, TelEmp, IdDep, ProEmp )values('" + nom + "','" + prenom + "','" + mail + "','" + tel + "',"+ dep +",'" +profil + "');";
        this.getWritableDatabase().execSQL(entre);
        Log.i("DATABASE", "insert employe");
    }

    //Display
    public List<UtilisateurC> afficheE() {

        List<UtilisateurC> affE = new ArrayList<>();
        String req = "select NomEmp,PrenEmp,MailEmp,TelEmp,LibDep,ProEmp from Utilisateur,Departement where Departement.IdDep=Utilisateur.IdDep;";
        Cursor cursor = this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            UtilisateurC disp = new UtilisateurC(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            affE.add(disp);
            cursor.moveToNext();
        }
        cursor.close();
        return affE;
    }
    //<<---------------------------********Fournisseur********------------------------------------>>
    //Insert
    public void insertFour(String nom, String adr, String tel)
    {
        String entre="insert into Fournisseur( NomFour, AdrFour, TelFour)values('" + nom +"','"+adr+"',"+tel+");";
        this.getWritableDatabase().execSQL(entre);
        Log.i("DATABASE","insert fournisseur");
    }
    public Boolean checkIfUserExist(UtilisateurC user){
        String req="select NomEmp,PrenEmp,MailEmp,TelEmp,LibDep,ProEmp from Utilisateur,Departement where Utilisateur.IdDep=Departement.IdDep and MailEmp='"+user.getMailEmp()+"';";
        Cursor cursor=this.getReadableDatabase().rawQuery(req,null);
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }
    public Boolean checkIfBesoinExist(String name){
        String req="select * from Besoin where LibBes='"+name+"';";
        Cursor cursor=this.getReadableDatabase().rawQuery(req,null);
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }
    public Boolean checkIfCategorieExist(String name){
        String req="select * from Categorie where LibCat='"+name+"';";
        Cursor cursor=this.getReadableDatabase().rawQuery(req,null);
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }
    public Boolean checkIfDepartmentExist(String name){
        String req="select * from Departement where LibDep='"+name+"';";
        Cursor cursor=this.getReadableDatabase().rawQuery(req,null);
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }
    public Boolean checkIfFournisseurExist(String name){
        String req="select * from Fournisseur where NomFour='"+name+"';";
        Cursor cursor=this.getReadableDatabase().rawQuery(req,null);
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }
    public Boolean checkMailExist(String mail){
        String req="select NomEmp,PrenEmp,MailEmp,TelEmp,LibDep,ProEmp from Utilisateur,Departement where Utilisateur.IdDep=Departement.IdDep and MailEmp='"+mail  +"';";
        Cursor cursor=this.getReadableDatabase().rawQuery(req,null);
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }
    //Display
    public List<FournisseurC> afficheF()
    {
        List<FournisseurC>affF=new ArrayList<>();
        String req="select * from Fournisseur;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            FournisseurC disp=new FournisseurC(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            affF.add(disp);
            cursor.moveToNext();
        }

         cursor.close();
         return affF;
    }
    public String retrieveUserProfile(String mail){
        String req="select ProEmp from Utilisateur where MailEmp='"+mail+"';";
        Cursor cursor = null;
        try {
            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    //Textautocomplete
    public ArrayList<String> affiNF()
    {
        ArrayList<String>nf=new ArrayList<>();
        String req="select NomFour from Fournisseur;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            nf.add(cursor.getString(0));
            cursor.moveToNext();
        }

        cursor.close();
        return nf;
    }


    //<<---------------------------********Besoin********------------------------------------>>
//Insert


    public String selectCat(String nomCat)
    {
        String req="select IdCat from Categorie where LibCat='"+nomCat+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public void insertBesoin(String nom,String type,int categorie,int seuil,String amort,int stockBes,int image)
    {
        String entre="insert into Besoin(LibBes, TypeBes, IdCat, SeuilBes, AmorBes,StockBes,Image)values('"+nom+"','"+type+"',"+categorie+","+seuil+",strftime('%s','"+amort+"'),"+stockBes+","+image+");";
        this.getWritableDatabase().execSQL(entre);
        Log.i("DATABASE","insert Besoin");
    }

    public List<BesoinC> afficheB()
    {
        List<BesoinC>affB=new ArrayList<>();
        //String req="select NumBes,LibBes,TypeBes,Idcat,SeuilBes,date(AmorBes,'unixepoch') from Besoin where Amorbes BETWEEN strftime('%s','2010-05-04') AND strftime('%s','2060-12-31') ;";

        String req="select LibBes,TypeBes,LibCat,SeuilBes,date(AmorBes,'unixepoch'),StockBes,Image from Besoin,Categorie where Besoin.Idcat=Categorie.IdCat ;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        String amort1,amort2,amort3,amort="2018-12-31";
     /*   amort=cursor.getString(5);
        amort1=amort.substring(0,4);
        amort2=amort.substring(5,7);
        amort3=amort.substring(8,10);
        amort=amort3+"/"+amort2+"/"+amort1;  */

        while (!cursor.isAfterLast())
        {


            BesoinC disp=new BesoinC(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6));
            affB.add(disp);
            cursor.moveToNext();

        }

        cursor.close();
        return affB;
    }


    public List<BesoinC> afficheLB()
    {
        List<BesoinC>affB=new ArrayList<>();

        String req="select LibBes from Besoin;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            BesoinC disp=new BesoinC(cursor.getString(0));
            affB.add(disp);
            cursor.moveToNext();
        }

        cursor.close();
        return affB;
    }


    //Textautocomplete
    public ArrayList<String> affiNB()
    {
        ArrayList<String>nb=new ArrayList<>();
        String req="select LibBes from Besoin;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            nb.add(cursor.getString(0));
            cursor.moveToNext();
        }

        cursor.close();
        return nb;
    }

//<<--------------------------------*********Entrées*******-------------------------------------->>

    public String selectFour(String nomF)
    {
        String req="select IdFour from Fournisseur where NomFour='"+nomF+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public String selectIdBes(String nomB)
    {
        String req="select NumBes from Besoin where LibBes='"+nomB+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

//----Insert
    public void insertEntr(String date, int idfour)
    {
        String entre="insert into Entree( DateEnt, IdFour)values(strftime('%s','"+date+"'),"+idfour+");";
        Log.i("DATABASE","insert Entree");
        this.getWritableDatabase().execSQL(entre);
    }

    public void insertEntrBes(int numB,int numE, int pu, int qte, String marque, String autr)
    {
        String entre="insert into Besoins_Entree( NumBes, NumEnt, PU, Qte, MarqueBes, Autre_Précision )values(" + numB +","+numE+","+pu+","+qte+",'"+marque+"','"+autr+"');";
        Log.i("DATABASE","insert Entree besoin");
        this.getWritableDatabase().execSQL(entre);
    }


    public String selectIdEnt()
    {
        String requete="select MAX(NumEnt) from Entree;";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(requete,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    //------Display
    public List<AddEC> afficheEntre()
    {
        List<AddEC>affE=new ArrayList<>();


        String req="select * from Entree;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();


        while (!cursor.isAfterLast())
        {

            AddEC disp=new AddEC(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2));
            affE.add(disp);
            cursor.moveToNext();

        }

        cursor.close();
        return affE;
    }
    public List<AddBEC> afficheBesoinEntre()
    {
        List<AddBEC> affEB=new ArrayList<>();


        String req="select * from Besoins_Entree;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();


        while (!cursor.isAfterLast())
        {


            AddBEC disp=new AddBEC(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5));
            affEB.add(disp);
            cursor.moveToNext();

        }

        cursor.close();
        return affEB;
    }

//-------------------------------------*****Demande*****--------------------------------------

    public List<DemandeC> afficheDemande()
    {
        List<DemandeC> affD=new ArrayList<>();


        String req="select Demande.NumDem,NomEmp,LibBes,Qte,date(dateDem,'unixepoch'),libDep from Demande,Demande_Besoins,Utilisateur,Besoin,Departement where Besoin.NumBes=Demande_Besoins.NumBes and Demande_Besoins.NumDem=Demande.NumDem and Demande.IdDep=Departement.IdDep and Utilisateur.IdEmp=Demande.IdEmp;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();


        while (!cursor.isAfterLast())
        {


            DemandeC disp=new DemandeC(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5));
            affD.add(disp);
            cursor.moveToNext();

        }

        cursor.close();
        return affD;
    }
    public List<DemandeC> afficheDemandeUser(String mail)
    {
        List<DemandeC> affD=new ArrayList<>();


        String req="select Demande.NumDem,NomEmp,LibBes,Qte,date(dateDem,'unixepoch'),libDep from Demande,Demande_Besoins,Utilisateur,Besoin,Departement where Besoin.NumBes=Demande_Besoins.NumBes and Demande_Besoins.NumDem=Demande.NumDem and Demande.IdDep=Departement.IdDep and Utilisateur.IdEmp=Demande.IdEmp and Utilisateur.MailEmp='"+mail+"';";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();


        while (!cursor.isAfterLast())
        {


            DemandeC disp=new DemandeC(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5));
            affD.add(disp);
            cursor.moveToNext();

        }

        cursor.close();
        return affD;
    }

    public List<DemandeC> afficheDemande1()
    {
        List<DemandeC> affD=new ArrayList<>();


        String req="select Demande.NumDem,Departement.LibDep,Besoin.LibBes,Demande_Besoins.Qte,date(Demande.dateDem,'unixepoch') from Demande,Demande_Besoins,Departement,Besoin where Demande.NumDem=Demande_Besoins.NumDem and Demande_Besoins.NumBes=Besoin.NumBes and Demande.IdDep=Departement.IdDep and Demande.IdEmp is null ;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();


        while (!cursor.isAfterLast())
        {


            DemandeC disp=new DemandeC(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4));
            affD.add(disp);
            cursor.moveToNext();

        }

        cursor.close();
        return affD;
    }

    public String test()
    {
        String req="select * from Besoin where NumBes=1;";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
        }
    }
























    public boolean checkIfTableHasData(String tableName){
    Cursor c = getReadableDatabase().rawQuery("SELECT * FROM " + tableName,null);
    return c.moveToFirst();
    }

    public String DepartEmp(int id_nom){
        String req="select Departement.LibDep from Utilisateur,Departement where Utilisateur.IdDep=Departement.IdDep and Utilisateur.IdEmp='"+id_nom+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public void insertDemande1(String date , int dep)
    {
        String entre="insert into Demande( DateDem,IdDep )values(strftime('%s','"+date +"'),"+dep+");";
        Log.i("DATABASE","insert Demande");
        this.getWritableDatabase().execSQL(entre);
    }


        public void insertDemande(String date ,int emp, int dep)
    {
        String entre="insert into Demande( DateDem, IdEmp, IdDep )values(strftime('%s','"+date +"'),"+emp+","+dep+");";
        Log.i("DATABASE","insert Demande");
        this.getWritableDatabase().execSQL(entre);
    }
         public void insertDemandeBesoin(int dem, int besoin ,int qte)
    {
        String entre="insert into Demande_Besoins(NumDem, NumBes, Qte )values("+dem+"," + besoin +","+qte+");";
        Log.i("DATABASE","insert Demande besoin");
        this.getWritableDatabase().execSQL(entre);
    }

   public String selectEmpl(String nomF)
    {
        String req="select NomEmp from Utilisateur where NomEmp='"+nomF+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectEmpId(String nomB)
    {
        String req="select IdEmp from Utilisateur where NomEmp='"+nomB+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }


    //TextautocompleteEmploye
    public ArrayList<String> affiNE()
    {
        ArrayList<String>nd=new ArrayList<>();
        String req="select NomEmp from Utilisateur;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            nd.add(cursor.getString(0));
            cursor.moveToNext();
        }

        cursor.close();
        return nd;
    }
    //TextautocompleteDepartement
    public ArrayList<String> affiNDE()
    {
        ArrayList<String>nd=new ArrayList<>();
        String req="select LibDep from Departement;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            nd.add(cursor.getString(0));
            cursor.moveToNext();
        }

        cursor.close();
        return nd;
    }

    public String selectDep(String nomD)
    {
        String req="select IdDep from Departement where LibDep='"+nomD+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectIdDem()
    {
        String requete="select MAX(NumDem) from Demande;";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(requete,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
//-------------------------**********SORTIE(BringOut)***********-----------------------------------
//TextautocompleteNumDemand
public ArrayList<String> affiNumDem(int idemp)
{
    ArrayList<String>nd=new ArrayList<>();
    String req="select date(DateDem,'unixepoch') as paco from Demande where IdEmp='"+idemp+"' order by paco desc limit 3;";
    Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
    cursor.moveToFirst();

    while (!cursor.isAfterLast())
    {
        nd.add(cursor.getString(0));
        cursor.moveToNext();
    }

    cursor.close();
    return nd;
}

    public ArrayList<String> affiNumDem1(int iddep)
    {
        ArrayList<String>nd=new ArrayList<>();
        String req="select date(DateDem,'unixepoch') as paco from Demande where IdDep='"+iddep+"' and IdEmp is null order by paco desc limit 3;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            nd.add(cursor.getString(0));
            cursor.moveToNext();
        }

        cursor.close();
        return nd;
    }
    //TextautocompleteMarque
    public ArrayList<String> affiMarque()
    {
        ArrayList<String>nd=new ArrayList<>();
        String req="select MarqueBes from Besoins_Entree;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            nd.add(cursor.getString(0));
            cursor.moveToNext();
        }

        cursor.close();
        return nd;
    }
    //TextautocompleteAutre
    public ArrayList<String> affiAutre()
    {
        ArrayList<String>nd=new ArrayList<>();
        String req="select `Autre précision` from Besoins_Entree;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            nd.add(cursor.getString(0));
            cursor.moveToNext();
        }

        cursor.close();
        return nd;
    }

    public String selectNumDem(String date)
    {
        String req="select NumDem from Demande where DateDem='"+date+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public void insertSortie(String date, String demande)
    {

       //String paco="select NumDem from Sortie,Demande,Utilisateur where Sortie.NumDem=Demande.NumDem and Demande.IdEmp=Emp.IdEmp and Demande.DateDem=strftime('%s','"+demande+"') and Emp.NomEmp='"+employe+"';";
        String entre="insert into Sortie(DateSor,NumDem)values(strftime('%s','"+date+"'),'"+demande+"');";
        Log.i("DATABASE","insert Sortie");
        this.getWritableDatabase().execSQL(entre);

    }

    public void insertSortieBesoin(int sortie, int besoin, int qte, String marque, String autr)
    {
        String entre="insert into Besoins_Sortie (NumSor, NumBes, Qte, MarqueBes, `Autre précision`)values("+sortie+","+besoin+","+qte+",'"+marque+"','"+autr+"');";
        Log.i("DATABASE","insert SortieBesoin");
        this.getWritableDatabase().execSQL(entre);
    }
    public String selectNumeDem(String demande,String employe)
    {

        String paco="select Demande.NumDem from Demande,Utilisateur where Demande.IdEmp=Utilisateur.IdEmp and Demande.DateDem=strftime('%s','"+demande+"') and Utilisateur.NomEmp='"+employe+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(paco,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public String selectNumeDem1(String demande,String depart)
    {

        String paco="select Demande.NumDem from Demande,Departement where Demande.IdDep=Departement.IdDep and Demande.DateDem=strftime('%s','"+demande+"') and Departement.LibDep='"+depart+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(paco,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public String selectIdSortie()
    {
        String requete="select MAX(NumSor) from Sortie;";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(requete,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    //-----------------------------les requetes-------------------------------------------//

    //Display
    public List<Stock1> afficheStock1() {

        List<Stock1> affStok1 = new ArrayList<>();
        String req = "select LibBes,TypeBes,PU,Qte,date(Entree.DateEnt,'unixepoch') FROM Besoin,Besoins_Entree,Entree WHERE Besoin.NumBes=Besoins_Entree.NumBes and Entree.NumEnt=Besoins_Entree.NumEnt;";
        Cursor cursor = this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Stock1 disp = new Stock1(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3),cursor.getString(4));
            affStok1.add(disp);
            cursor.moveToNext();
        }
        cursor.close();
        return affStok1;
    }

    public List<Stock2> afficheStock2() {

        List<Stock2> affStok2 = new ArrayList<>();
        String req = "select Besoin.LibBes,Besoin.TypeBes,Qte,NomEmp,date(DateSor,'unixepoch') FROM Besoin,Besoins_Sortie, Demande,Utilisateur,Sortie WHERE Besoin.NumBes=Besoins_Sortie.NumBes and Sortie.NumDem=Demande.NumDem and Utilisateur.IdEmp=Demande.IdEmp and Besoins_Sortie.NumSor=Sortie.NumSor;";
        Cursor cursor = this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Stock2 disp = new Stock2(cursor.getString(0), cursor.getString(1), cursor.getInt(2),cursor.getString(3),cursor.getString(4));
            affStok2.add(disp);
            cursor.moveToNext();
        }
        cursor.close();
        return affStok2;
    }

    public List<Stock2> afficheStock3() {

        List<Stock2> affStok3 = new ArrayList<>();
        String req = "select LibBes,TypeBes,LibDep,Qte,date(DateSor,'unixepoch') FROM Besoin,Besoins_Sortie, Demande,Departement,Sortie WHERE Besoin.NumBes=Besoins_Sortie.NumBes and Sortie.NumDem=Demande.NumDem and Departement.IdDep=Demande.IdDep and Besoins_Sortie.NumSor=Sortie.NumSor and Demande.IdEmp is null ;";
        Cursor cursor = this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Stock2 disp = new Stock2(cursor.getString(0), cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4));
            affStok3.add(disp);
            cursor.moveToNext();
        }
        cursor.close();
        return affStok3;

    }

    //======********///*****//Creation de la table stock////*********************/////**/''"'''



    public void upDate(int quant, String nomB)
    {
        String req="update Besoin set StockBes="+quant+" where LibBes='"+nomB+"';";
        this.getWritableDatabase().execSQL(req);
        Log.i("DATABASE","mise a jour de la table stock");
    }


    public String selectStockBes(String LibBes){
        String requete="select StockBes from Besoin where LibBes='"+LibBes+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(requete,null );
           return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public List<StockC> afficheSt()
    {
        List<StockC>affS=new ArrayList<>();


        String req="select LibBes,TypeBes,SeuilBes,StockBes,Image from Besoin;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();


        while (!cursor.isAfterLast())
        {

            StockC disp=new StockC(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4));

            affS.add(disp);
            cursor.moveToNext();
        }

        cursor.close();
        return affS;
    }

    public List<StockC> afficheSt1()
    {
        List<StockC>affS=new ArrayList<>();


        String req="select LibBes,TypeBes,date(AmorBes,'unixepoch'),Image from Besoin;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();


        while (!cursor.isAfterLast())
        {

            StockC disp=new StockC(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));

            affS.add(disp);
            cursor.moveToNext();
        }

        cursor.close();
        return affS;
    }


    public List<CoutC> CoutBesoin(String besoin)
    {
        List<CoutC>affS=new ArrayList<>();


        String req="select Entree.NumEnt,Besoin.LibBes,Besoins_Entree.PU,Besoins_Entree.Qte from Entree,Besoin,Besoins_Entree where Entree.NumEnt=Besoins_Entree.NumEnt and Besoin.NumBes=Besoins_Entree.NumBes and Besoin.LibBes='"+besoin+"';";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        MyApplication.setCoutTotBes(0);
        while (!cursor.isAfterLast())
        {

            CoutC disp=new CoutC(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3));

            affS.add(disp);
            MyApplication.setCoutTotBes(MyApplication.getCoutTotBes()+(cursor.getInt(2)*cursor.getInt(3)));
            cursor.moveToNext();
        }

        cursor.close();
        return affS;
    }

    public List<ListAchatC> ListAchat()
    {
        List<ListAchatC>affS=new ArrayList<>();


        String req="select Besoin.LibBes,sum(Qte) as Qte from Besoin,Demande_Besoins,Demande where Besoin.NumBes=Demande_Besoins.NumBes and Demande_Besoins.NumDem=Demande.NumDem  group by LibBes;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {

            ListAchatC disp=new ListAchatC(cursor.getString(0),cursor.getInt(1));

            affS.add(disp);

            cursor.moveToNext();
        }

        cursor.close();
        return affS;
    }


    public List<RuptureC> RupureCheck() {
        List<RuptureC> affS = new ArrayList<>();


        String req = "select LibBes,SeuilBes,StockBes from Besoin where SeuilBes>=StockBes and TypeBes='NON AMORTISSABLE';";
        Cursor cursor = this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        if (!(!checkIfTableHasData("Besoins_Sortie") && !checkIfTableHasData("Categorie") && !checkIfTableHasData("Demande") && !checkIfTableHasData("Demande_Besoins") && !checkIfTableHasData("Departement") && !checkIfTableHasData("Utilisateur") && !checkIfTableHasData("Besoin") && !checkIfTableHasData("Besoins_Entree") && !checkIfTableHasData("Entree") && !checkIfTableHasData("Fournisseur") && !checkIfTableHasData("Sortie"))) {

                    while (!cursor.isAfterLast()) {

                        RuptureC disp = new RuptureC(cursor.getString(0), cursor.getInt(1), cursor.getInt(2));

                        affS.add(disp);
                        cursor.moveToNext();
                        // }

                    }
                    if (cursor.moveToFirst()) {
                        MyApplication.setDone(true);
                    } else {
                        MyApplication.setDone(false);
                    }


                cursor.close();
                return affS;
            }





        cursor.close();
        return affS;


    }
    public void deleteUser(String mail){
        String req="delete from Utilisateur where MailEmp='"+mail+"';";
        this.getWritableDatabase().execSQL(req);
        Log.i("DATABASE","Suppression de l'utilisateur '"+mail+"'.");
    }


}











