package com.example.hp.madose;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;

/**
 * Created by HP on 05/12/2017.
 */

public class BaseDeDonne extends SQLiteOpenHelper {

    private static final String MABASE = "gestion.db";


    private static final String TABLE_BESOIN_SORTIE = "CREATE TABLE Besoins_Sortie ( `NumSor` INTEGER, `NumBes` INTEGER, `qte` INTEGER NOT NULL, `marqueBes` TEXT, `Autre précision` TEXT," +
            "`HeureSor` TEXT, `User` TEXT, PRIMARY KEY(NumSor,NumBes), FOREIGN KEY(NumSor) REFERENCES Sortie(NumSor), FOREIGN KEY(NumBes) REFERENCES Besoin(NumBes) );";
    private static final String TABLE_CATEGORIE = "CREATE TABLE Categorie ( `IdCat` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `LibCat` TEXT NOT NULL );";
    private static final String TABLE_DEMANDE = "CREATE TABLE Demande ( `numDem` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `DateDem` INTEGER NOT NULL, `IdEmp` INTEGER, `IdDep` INTEGER, " +
            "`HeureDem` TEXT, FOREIGN KEY(`IdEmp`) REFERENCES `Utilisateur`(`IdEmp`), FOREIGN KEY(`IdDep`) REFERENCES `Departement`(`IdDep`) );";
    private static final String TABLE_DEMANDE_BESOIN = "CREATE TABLE Demande_Besoins ( `numDem` INTEGER, `NumBes` INTEGER, `qte` INTEGER NOT NULL, FOREIGN KEY(`NumBes`) REFERENCES `Besoin`(`NumBes`), PRIMARY KEY(`numDem`,`NumBes`), FOREIGN KEY(`numDem`) REFERENCES `Demande`(`numDem`) );";
    private static final String TABLE_DEPARTEMENT = "CREATE TABLE Departement ( `IdDep` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `libDep` TEXT NOT NULL );";
    private static final String TABLE_USER = "CREATE TABLE Utilisateur ( `IdEmp` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `nomEmp` TEXT NOT NULL,`PrenEmp` TEXT,`MailEmp` TEXT NOT NULL,`TelEmp` TEXT NOT NULL,`IdDep` INTEGER, `ProEmp` TEXT, FOREIGN KEY(`IdDep`) REFERENCES `Departement`(`IdDep`) );";
    private static final String TABLE_BESOIN = "CREATE TABLE Besoin ( NumBes INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, libBes TEXT NOT NULL, typeBes TEXT NOT NULL, IdCat INTEGER," +
            " SeuilBes INTEGER, AmorBes INTEGER,`StockBes` INTEGER, `Image` INTEGER, FOREIGN KEY(IdCat) REFERENCES Categorie(IdCat) );";
    private static final String TABLE_BESOIN_ENTREE = "CREATE TABLE Besoins_Entree ( NumBes INTEGER, numEnt INTEGER, PU INTEGER, qte INTEGER NOT NULL, marqueBes TEXT, autrePrecision TEXT," +
            " PRIMARY KEY(NumBes,numEnt), FOREIGN KEY(numEnt) REFERENCES Entree(numEnt), FOREIGN KEY(NumBes) REFERENCES Besoin(NumBes) );";
    private static final String TABLE_ENTREE = "CREATE TABLE Entree ( `numEnt` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `DateEnt` INTEGER NOT NULL, `IdFour` INTEGER,`HeureEnt` INTEGER NOT NULL,`User` TEXT NOT NULL, FOREIGN KEY(`IdFour`) REFERENCES `Fournisseur`(`IdFour`));";
    private static final String TABLE_FOURNISSEUR = "CREATE TABLE Fournisseur ( `IdFour` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `NomFour` TEXT NOT NULL, `AdrFour` TEXT, `TelFour` TEXT);";
    private static final String TABLE_SORTIE = "CREATE TABLE Sortie ( `NumSor` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `DateSor` INTEGER NOT NULL, `numDem` INTEGER,`HeureSor` TEXT,`User` TEXT, FOREIGN KEY(`numDem`) REFERENCES `Demande`(`numDem`));";
    //   nomEmp,libDep,ProEmp,IdDep
    //DateSor numDem** NumSor NumBes qte marqueBes Autre précision
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
        String entre = "insert into Departement ( libDep )values('" + libelle + "');";
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
        String entre = "insert into Utilisateur ( nomEmp, PrenEmp, MailEmp, TelEmp, IdDep, ProEmp )values('" + nom + "','" + prenom + "','" + mail + "','" + tel + "',"+ dep +",'" +profil + "');";
        this.getWritableDatabase().execSQL(entre);
        Log.i("DATABASE", "insert employe");
    }

    //Display
    public List<UtilisateurC> afficheE() {

        List<UtilisateurC> affE = new ArrayList<>();
        String req = "select nomEmp,PrenEmp,MailEmp,TelEmp,libDep,ProEmp from Utilisateur,Departement where Departement.IdDep=Utilisateur.IdDep;";
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
        String req="select nomEmp,PrenEmp,MailEmp,TelEmp,libDep,ProEmp from Utilisateur,Departement where Utilisateur.IdDep=Departement.IdDep and MailEmp='"+user.getMailEmp()+"';";
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
    public Boolean checkIfBesoinEntreeExist(String name,String name1,String name2,String name3){
        String req="select Besoin.NumBes,Entree.NumEnt from Besoins_Entree,Besoin,Entree where Besoins_Entree.NumBes=Besoin.NumBes and Besoins_Entree.NumEnt=Entree.NumEnt and libBes='"+name+"' and DateEnt=strftime('%s','"+name1+"') and HeureEnt='"+name2+"' and Entree.User='"+name3+"' ;";
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
    public Boolean checkIfDemandeExist(String name,String name1,String name2){

        String req="select Demande.numDem,DateDem,Utilisateur.IdEmp,Departement.IdDep from Demande,Utilisateur,Departement where Demande.IdDep=Departement.IdDep and ((Demande.IdEmp=Utilisateur.IdEmp and Utilisateur.nomEmp || ' ' || Utilisateur.PrenEmp='"+name+"' and HeureDem='"+name1+"') or (Demande.IdEmp is null and Departement.libDep='"+name2+"' and HeureDem='"+name1+"'));";
        Log.i("VERIF",req);
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
 /*   public Boolean checkIfDemandeExist(int id,String name1){

        String req="select Demande.numDem,DateDem,Utilisateur.IdEmp,Departement.IdDep from Demande,Utilisateur,Departement,Demande_Besoins where Demande.IdDep=Departement.IdDep  and Demande.numDem=Demande_Besoins.numDem and Demande.IdEmp is null and Utilisateur.nomEmp || ' ' || Utilisateur.PrenEmp='"+name+"' and HeureDem='"+name1+"';";
        Log.i("VERIF",req);
        Cursor cursor=this.getReadableDatabase().rawQuery(req,null);
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }  */
    public Boolean checkIfDemandeBesoinExist(String name,String name1,String name2,String name3,String name4){
        String req="select Demande.numDem,DateDem,Utilisateur.IdEmp,Departement.IdDep,libBes from Demande,Utilisateur,Departement,Demande_Besoins,Besoin where Demande_Besoins.NumBes=Besoin.NumBes and Demande.numDem=Demande_Besoins.numDem and ((Demande.IdEmp=Utilisateur.IdEmp and Utilisateur.IdDep=Departement.IdDep and Utilisateur.nomEmp || ' ' || Utilisateur.PrenEmp='"+name+"' and HeureDem='"+name1+"' and libBes='"+name2+"' and Demande.DateDem=strftime('%s','"+name3+"')) or (Demande.IdDep=Departement.IdDep and Demande.IdEmp is null and Departement.libDep='"+name4+"' and HeureDem='"+name1+"' and libBes='"+name2+"' and Demande.DateDem=strftime('%s','"+name3+"')))  ;";
        Log.i("A FLE",req);
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
    public Boolean checkIfDemandeBesoinExist(String name,String name1){
        String req="select Demande_Besoins.numDem,Demande_Besoins.NumBes from Besoin,Demande_Besoins,Demande where Demande.numDem=Demande_Besoins.numDem and Demande_Besoins.numBes=Besoin.NumBes and HeureDem='"+name+"' and libBes='"+name1+"' ;";
        Log.i("ALE",req);
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
    public Boolean checkIfSortieExist(String name,String name1,String depart){
        String req="select Sortie.numDem from Sortie,Utilisateur,Demande,Departement where Sortie.numDem=Demande.numDem and HeureSor='"+name1+"' and (( Demande.IdEmp=Utilisateur.IdEmp and nomEmp||' '||PrenEmp='"+name+"') or ( Departement.IdDep=Demande.IdDep and libDep='"+depart+"' and Demande.IdEmp is null)) ;";
        //or (Demande.IdDep=Departement.IdDep and Departement.libDep='RECHERCHE' and Demande.IdEmp is null)

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
    public Boolean checkIfSortieEntreeExist(String name,String name1,String name2,String name3,String name4){
        String req="select Sortie.numDem,DateDem,Demande.IdEmp,Demande.IdDep,libBes from Besoin,Sortie,Demande,Utilisateur,Departement,Besoins_Sortie where Besoin.NumBes=Besoins_Sortie.NumBes and Besoins_Sortie.NumSor=Sortie.NumSor and Demande.numDem=Sortie.numDem and(( Demande.IdEmp=Utilisateur.IdEmp and Utilisateur.IdDep=Departement.IdDep and nomEmp || ' ' || prenEmp='"+name+"') or(libDep='"+name4+"' and Demande.IdEmp is null and Demande.IdDep=Departement.IdDep)) and Sortie.HeureSor='"+name1+"' and libBes='"+name2+"' and Sortie.DateSor=strftime('%s','"+name3+"') ;";
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
        String req="select * from Besoin where libBes='"+name+"';";
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
    public Boolean checkIfEntreeExist(String name,String name1,String user){
        String req="select NumEnt,DateEnt,Entree.IdFour from Entree,Fournisseur where HeureEnt='"+name1+"' and NomFour='"+name+"' and Entree.IdFour=Fournisseur.IdFour and User='"+user+"';";
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
        String req="select * from Departement where libDep='"+name+"';";
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
        String req="select nomEmp,PrenEmp,MailEmp,TelEmp,libDep,ProEmp from Utilisateur,Departement where Utilisateur.IdDep=Departement.IdDep and MailEmp='"+mail  +"';";
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
    public String selectHeureEnt()
    {
        String req="select HeureEnt from Entree order by HeureEnt desc limit 1;";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectUserEnt(String name)
    {

        String req="select User from Entree where HeureEnt='"+name+"' ;";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectHeureDem()
    {
        String req="select HeureDem from Demande order by HeureDem desc limit 1;";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectHeureSor()
    {
        String req="select HeureSor from Sortie order by HeureSor desc limit 1;";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectCurrentDate()
    {
        String req="select strftime('%s','now');";
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
        String entre="insert into Besoin(libBes, typeBes, IdCat, SeuilBes, AmorBes,StockBes,Image)values('"+nom+"','"+type+"',"+categorie+","+seuil+",strftime('%s','"+amort+"'),"+stockBes+","+image+");";
        this.getWritableDatabase().execSQL(entre);
        Log.i("DATABASE","insert Besoin");
    }

    public List<BesoinC> afficheB()
    {
        List<BesoinC>affB=new ArrayList<>();
        //String req="select NumBes,libBes,typeBes,Idcat,SeuilBes,date(AmorBes,'unixepoch') from Besoin where Amorbes BETWEEN strftime('%s','2010-05-04') AND strftime('%s','2060-12-31') ;";

        String req="select libBes,typeBes,LibCat,SeuilBes,date(AmorBes,'unixepoch'),StockBes,Image from Besoin,Categorie where Besoin.Idcat=Categorie.IdCat ;";
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

        String req="select libBes from Besoin;";
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
        String req="select libBes from Besoin;";
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
        String req="select NumBes from Besoin where libBes='"+nomB+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

//----Insert
    public void insertEntr(String date, int idfour,String heure,String user,Boolean local)
    {
        if (local){
            String entre="insert into Entree( DateEnt, IdFour,HeureEnt,User)values(strftime('%s','"+date+"'),"+idfour+",strftime('%s','now'),'"+user+"') ;";
            Log.i("DATABASE","insert Entree");
            this.getWritableDatabase().execSQL(entre);
        } else {
            String entre = "insert into Entree( DateEnt, IdFour,HeureEnt,User)values(strftime('%s','" + date + "')," + idfour + ",'"+heure+"','"+user+"');";
            Log.i("DATABASE", "insert Entree");
            this.getWritableDatabase().execSQL(entre);
        }
    }

    public void insertEntrBes(int numB,int numE, int pu, int qte, String marque, String autr)
    {
        String entre="insert into Besoins_Entree( NumBes, numEnt, PU, qte, marqueBes, autrePrecision )values(" + numB +","+numE+","+pu+","+qte+",'"+marque+"','"+autr+"');";
        Log.i("DATABASE","insert Entree besoin");
        this.getWritableDatabase().execSQL(entre);
    }


    public String selectIdEnt()
    {
        String requete="select MAX(numEnt) from Entree;";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(requete,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectIdEnt(String date)
    {
        String requete="select NumEnt from Entree where DateEnt=strftime('%s','"+date+"');";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(requete,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectIdEnt(String time,String user)
    {
        String requete="select NumEnt from Entree where HeureEnt='"+time+"' and User='"+user+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(requete,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectIdEnt(int time)
    {
        String requete="select NumEnt from Entree where HeureEnt="+time+" ;";
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


        String req="select Demande.numDem,nomEmp||' '||prenEmp,libBes,qte,date(dateDem,'unixepoch'),libDep from Demande,Demande_Besoins,Utilisateur,Besoin,Departement where Besoin.NumBes=Demande_Besoins.NumBes and Demande_Besoins.numDem=Demande.numDem and Utilisateur.IdDep=Departement.IdDep and Utilisateur.IdEmp=Demande.IdEmp;";
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


        String req="select Demande.numDem,nomEmp,libBes,qte,date(dateDem,'unixepoch'),libDep from Demande,Demande_Besoins,Utilisateur,Besoin,Departement where Besoin.NumBes=Demande_Besoins.NumBes and Demande_Besoins.numDem=Demande.numDem and Demande.IdDep=Departement.IdDep and Utilisateur.IdEmp=Demande.IdEmp and Utilisateur.MailEmp='"+mail+"';";
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


        String req="select Demande.numDem,Departement.libDep,Besoin.libBes,Demande_Besoins.qte,date(Demande.dateDem,'unixepoch') from Demande,Demande_Besoins,Departement,Besoin where Demande.numDem=Demande_Besoins.numDem and Demande_Besoins.NumBes=Besoin.NumBes and Demande.IdDep=Departement.IdDep and Demande.IdEmp is null ;";
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


    public List<UtilisateurC> listeUser()
    {
        List<UtilisateurC> listU=new ArrayList<>();


        String req="select nomEmp,prenEmp from Utilisateur;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {

            UtilisateurC disp=new UtilisateurC(cursor.getString(0),cursor.getString(1));
            listU.add(disp);
            cursor.moveToNext();

        }

        cursor.close();
        return listU;
    }



    public List<FournisseurC> listeF()
    {
        List<FournisseurC> listF=new ArrayList<>();


        String req="select nomFour from Fournisseur;";
        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {

            FournisseurC disp=new FournisseurC(cursor.getString(0));
            listF.add(disp);
            cursor.moveToNext();

        }

        cursor.close();
        return listF;
    }























    public boolean checkIfTableHasData(String tableName){
    Cursor c = getReadableDatabase().rawQuery("SELECT * FROM " + tableName,null);
    return c.moveToFirst();
    }

    public String DepartEmp(int id_nom){
        String req="select Departement.libDep from Utilisateur,Departement where Utilisateur.IdDep=Departement.IdDep and Utilisateur.IdEmp='"+id_nom+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public void insertDemande1(String date , int dep, String heure, Boolean local)
    {
        if (local) {
            String entre = "insert into Demande( DateDem,IdDep,HeureDem )values(strftime('%s','" + date + "')," + dep + ",strftime('%s','now'));";
            Log.i("DATABASE", "insert Demande");
            this.getWritableDatabase().execSQL(entre);
        }
        else{
            String entre = "insert into Demande( DateDem,IdDep,HeureDem )values(strftime('%s','" + date + "')," + dep + ",'" + heure + "');";
            Log.i("DATABASE", "insert Demande");
            this.getWritableDatabase().execSQL(entre);
        }
    }


        public void insertDemande(String date ,int emp, int dep,String heure, Boolean local)
    {
        if (local) {
            String entre = "insert into Demande( DateDem, IdEmp, IdDep ,HeureDem)values(strftime('%s','" + date + "')," + emp + "," + dep + ",strftime('%s','now'));";
            Log.i("DATABASE", "insert Demande");
            this.getWritableDatabase().execSQL(entre);
        }
        else {
            String entre = "insert into Demande( DateDem, IdEmp, IdDep,HeureDem )values(strftime('%s','" + date + "')," + emp + "," + dep + ",'" + heure + "');";
            Log.i("DATABASE", "insert Demande");
            this.getWritableDatabase().execSQL(entre);
        }
    }
         public void insertDemandeBesoin(int dem, int besoin ,int qte)
    {
        String entre="insert into Demande_Besoins(numDem, NumBes, qte )values("+dem+"," + besoin +","+qte+");";
        Log.i("DATABASE","insert Demande besoin");
        this.getWritableDatabase().execSQL(entre);
    }

   /*public String selectEmpl(String nomF)
    {
        String req="select nomEmp from Utilisateur where nomEmp='"+nomF+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }*/
    public String selectEmpId(String nomB)
    {
        String req="select IdEmp from Utilisateur where nomEmp || ' ' || prenEmp ='"+nomB+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectIdEmp(String nomB)
    {
        String req="select IdEmp from Utilisateur where nomEmp || ' ' || prenEmp='"+nomB+"';";
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
        String req="select nomEmp from Utilisateur;";
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
        String req="select libDep from Departement;";
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
        String req="select IdDep from Departement where libDep='"+nomD+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectDepfromUser(String nomD)
    {
        int idUser=Integer.parseInt(selectEmpId(nomD));
        String req="select IdDep from Departement,Utilisateur where Departement.IdDep=Utilisateur.IdDep and nomEmp || ' ' || PrenEmp='"+nomD+"' ;";
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
        String requete="select MAX(numDem) from Demande;";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(requete,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectIdDem(String nomEmp,String date)
    {
        String requete="select numDem from Demande,Utilisateur where Demande.IdEmp=Utilisateur.IdEmp and nomEmp||' '||prenEmp='"+nomEmp+"' and DateDem=strftime('%s','"+date+"');";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(requete,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectIdDem1(String libDep,String date)
    {
        String requete="select numDem from Demande,Departement where Demande.IdDep=Departement.IdDep and libDep='"+libDep+"' and DateDem=strftime('%s','"+date+"');";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(requete,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectIdDem(String heure)
    {
        String requete="select numDem from Demande where HeureDem='"+heure+"';";
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
    String req="select date(DateDem,'unixepoch'),datetime(HeureDem,'unixepoch') as paco from Demande where IdEmp='"+idemp+"' order by HeureDem desc limit 3;";
    Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
    cursor.moveToFirst();

    while (!cursor.isAfterLast())
    {
        nd.add(cursor.getString(1));
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
    public ArrayList<String> affiNumDem11(int iddep)
    {
        ArrayList<String>nd=new ArrayList<>();
        String req="select date(DateDem,'unixepoch') as paco,datetime(HeureDem,'unixepoch')  from Demande where IdDep='"+iddep+"' and IdEmp is null order by HeureDem desc limit 3 ;";

        Cursor cursor=this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            nd.add(cursor.getString(1));
            cursor.moveToNext();
        }

        cursor.close();
        return nd;
    }
    //TextautocompleteMarque
    public ArrayList<String> affiMarque()
    {
        ArrayList<String>nd=new ArrayList<>();
        String req="select marqueBes from Besoins_Entree;";
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
        String req="select numDem from Demande where DateDem='"+date+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(req,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }


    public void insertSortie(String date, String demande, String heure, String user, Boolean local)
    {
        if (local) {

            //String paco="select numDem from Sortie,Demande,Utilisateur where Sortie.numDem=Demande.numDem and Demande.IdEmp=Emp.IdEmp and Demande.DateDem=strftime('%s','"+demande+"') and Emp.nomEmp='"+employe+"';";
            String entre = "insert into Sortie(DateSor,numDem,HeureSor,User)values(strftime('%s','" + date + "'),'" + demande + "',strftime('%s','now'),'"+user+"');";
            Log.i("DATABASE", "insert Sortie");
            this.getWritableDatabase().execSQL(entre);
        }
        else {
            String entre = "insert into Sortie(DateSor,numDem,HeureSor,User)values(strftime('%s','" + date + "'),'" + demande + "','"+heure+"','"+user+"');";
            Log.i("DATABASE", "insert Sortie");
            this.getWritableDatabase().execSQL(entre);
        }

    }

    public void insertSortieBesoin(int sortie, int besoin, int qte, String marque, String autr)
    {
        String entre="insert into Besoins_Sortie (NumSor, NumBes, qte, marqueBes, `Autre précision`)values("+sortie+","+besoin+","+qte+",'"+marque+"','"+autr+"');";
        Log.i("DATABASE","insert SortieBesoin");
        this.getWritableDatabase().execSQL(entre);
    }
    public String selectNumeDem(String demande,String employe)
    {

        String paco="select Demande.numDem from Demande,Utilisateur where Demande.IdEmp=Utilisateur.IdEmp and Demande.DateDem=strftime('%s','"+demande+"') and Utilisateur.nomEmp||' '||Utilisateur.prenEmp='"+employe+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(paco,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectNumeDem(String demande,String employe,String depart)
    {

        String paco="select Demande.numDem from Demande,Utilisateur,Departement where Demande.HeureDem=strftime('%s','"+demande+"') and ((Demande.IdEmp=Utilisateur.IdEmp and Utilisateur.nomEmp||' '||Utilisateur.prenEmp='"+employe+"') or ( Demande.IdDep=Departement.IdDep and libDep='"+depart+"' and Demande.IdEmp is null));";
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

        String paco="select Demande.numDem from Demande,Departement where Demande.IdDep=Departement.IdDep and Demande.DateDem=strftime('%s','"+demande+"') and Departement.libDep='"+depart+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(paco,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectNumDem1(String demande,String depart)
    {

        String paco="select Demande.numDem from Demande,Departement where Demande.IdDep=Departement.IdDep and Demande.HeureDem=strftime('%s','"+demande+"') and Departement.libDep='"+depart+"';";
        Cursor cursor = null;
        try {

            cursor = this.getReadableDatabase().rawQuery(paco,null );
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String selectNumDem2(String demande,String employe)
    {

        String paco="select Demande.numDem from Demande,Utilisateur where Demande.IdEmp=Utilisateur.IdEmp and Demande.HeureDem=strftime('%s','"+demande+"') and Utilisateur.nomEmp || ' ' || Utilisateur.prenEmp='"+employe+"';";
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
        String req = "select libBes,typeBes,PU,qte,date(Entree.DateEnt,'unixepoch') FROM Besoin,Besoins_Entree,Entree WHERE Besoin.NumBes=Besoins_Entree.NumBes and Entree.numEnt=Besoins_Entree.numEnt;";
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
        String req = "select Sortie.NumSor,Besoin.libBes,Besoin.typeBes,qte,nomEmp || ' ' || PrenEmp,date(DateSor,'unixepoch'),Sortie.HeureSor FROM Besoin,Besoins_Sortie, Demande,Utilisateur,Sortie WHERE Besoin.NumBes=Besoins_Sortie.NumBes and Sortie.numDem=Demande.numDem and Utilisateur.IdEmp=Demande.IdEmp and Besoins_Sortie.NumSor=Sortie.NumSor;";
        Cursor cursor = this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Stock2 disp = new Stock2(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
            affStok2.add(disp);
            cursor.moveToNext();
        }
        cursor.close();
        return affStok2;
    }

    public List<Stock2> afficheStock3() {

        List<Stock2> affStok3 = new ArrayList<>();
        String req = "select libBes,typeBes,libDep,qte,date(DateSor,'unixepoch') FROM Besoin,Besoins_Sortie, Demande,Departement,Sortie WHERE Besoin.NumBes=Besoins_Sortie.NumBes and Sortie.numDem=Demande.numDem and Departement.IdDep=Demande.IdDep and Besoins_Sortie.NumSor=Sortie.NumSor and Demande.IdEmp is null ;";
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
        String req="update Besoin set StockBes="+quant+" where libBes='"+nomB+"';";
        this.getWritableDatabase().execSQL(req);
        Log.i("DATABASE","mise a jour de la table stock");
    }


    public String selectStockBes(String LibBes){
        String requete="select StockBes from Besoin where libBes='"+LibBes+"';";
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


        String req="select libBes,typeBes,SeuilBes,StockBes,Image from Besoin;";
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


        String req="select libBes,typeBes,date(AmorBes,'unixepoch'),Image from Besoin;";
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


        String req="select Entree.numEnt,Besoin.libBes,Besoins_Entree.PU,Besoins_Entree.qte from Entree,Besoin,Besoins_Entree where Entree.numEnt=Besoins_Entree.numEnt and Besoin.NumBes=Besoins_Entree.NumBes and Besoin.libBes='"+besoin+"';";
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


        String req="select Besoin.libBes,sum(qte) as qte from Besoin,Demande_Besoins,Demande where Besoin.NumBes=Demande_Besoins.NumBes and Demande_Besoins.numDem=Demande.numDem  group by libBes;";
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

        String req = "select libBes,SeuilBes,StockBes from Besoin where SeuilBes>StockBes and TypeBes='NON AMORTISSABLE';";
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

    public List<RuptureC> StockSup() {
        List<RuptureC> affS = new ArrayList<>();

        String req = "select libBes,SeuilBes,StockBes from Besoin where SeuilBes<StockBes and TypeBes='NON AMORTISSABLE';";
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
    public List<RuptureC> StockEg() {
        List<RuptureC> affS = new ArrayList<>();

        String req = "select libBes,SeuilBes,StockBes from Besoin where SeuilBes=StockBes and TypeBes='NON AMORTISSABLE';";
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
    public List<RuptureC> CountRup() {
        List<RuptureC> affS = new ArrayList<>();

        String req = "select COUNT(libBes) from Besoin where StockBes<SeuilBes and TypeBes='NON AMORTISSABLE';";
        Cursor cursor = this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        if (!(!checkIfTableHasData("Besoins_Sortie") && !checkIfTableHasData("Categorie") && !checkIfTableHasData("Demande") && !checkIfTableHasData("Demande_Besoins") && !checkIfTableHasData("Departement") && !checkIfTableHasData("Utilisateur") && !checkIfTableHasData("Besoin") && !checkIfTableHasData("Besoins_Entree") && !checkIfTableHasData("Entree") && !checkIfTableHasData("Fournisseur") && !checkIfTableHasData("Sortie"))) {

            while (!cursor.isAfterLast()) {

                RuptureC disp = new RuptureC(cursor.getString(0));

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
    public List<RuptureC> CountSup() {
        List<RuptureC> affS = new ArrayList<>();

        String req = "select COUNT(libBes) from Besoin where StockBes>SeuilBes and TypeBes='NON AMORTISSABLE';";
        Cursor cursor = this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        if (!(!checkIfTableHasData("Besoins_Sortie") && !checkIfTableHasData("Categorie") && !checkIfTableHasData("Demande") && !checkIfTableHasData("Demande_Besoins") && !checkIfTableHasData("Departement") && !checkIfTableHasData("Utilisateur") && !checkIfTableHasData("Besoin") && !checkIfTableHasData("Besoins_Entree") && !checkIfTableHasData("Entree") && !checkIfTableHasData("Fournisseur") && !checkIfTableHasData("Sortie"))) {

            while (!cursor.isAfterLast()) {

                RuptureC disp = new RuptureC(cursor.getString(0));

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
    public List<RuptureC> CountEg() {
        List<RuptureC> affS = new ArrayList<>();

        String req = "select COUNT(libBes) from Besoin where StockBes=SeuilBes and TypeBes='NON AMORTISSABLE';";
        Cursor cursor = this.getReadableDatabase().rawQuery(req, null);
        cursor.moveToFirst();

        if (!(!checkIfTableHasData("Besoins_Sortie") && !checkIfTableHasData("Categorie") && !checkIfTableHasData("Demande") && !checkIfTableHasData("Demande_Besoins") && !checkIfTableHasData("Departement") && !checkIfTableHasData("Utilisateur") && !checkIfTableHasData("Besoin") && !checkIfTableHasData("Besoins_Entree") && !checkIfTableHasData("Entree") && !checkIfTableHasData("Fournisseur") && !checkIfTableHasData("Sortie"))) {

            while (!cursor.isAfterLast()) {

                RuptureC disp = new RuptureC(cursor.getString(0));

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











