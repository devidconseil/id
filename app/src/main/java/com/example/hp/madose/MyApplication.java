package com.example.hp.madose;

import android.app.Application;

/**
 * Created by HP on 28/12/2017.
 */

public class MyApplication extends Application {



    public static String mGlobalVarValue;
    public static int CoutTotBes;
    public static boolean done=false;
    public static boolean check=false;
    public static boolean fetch=true;

    public static boolean isFetch() {
        return fetch;
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



    public static void setCoutTotBes(int coutTotBes) {
        CoutTotBes = coutTotBes;
    }

    public static String getGlobalVarValue() {
        return mGlobalVarValue;
    }

    public static void setGlobalVarValue(String globalVarValue) {
        mGlobalVarValue = globalVarValue;
    }

}
