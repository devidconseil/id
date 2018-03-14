package com.example.hp.madose;

import android.app.Application;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    public static String verif1;
    public static int id;
    public static boolean textView;
    public static String employe;

    public static boolean isFetch() {
        return fetch;
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
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
       DatabaseReference userRef= FirebaseDatabase.getInstance().getReference("users");
       userRef.keepSynced(true);
    }

}
