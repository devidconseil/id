package com.example.hp.madose;

/**
 * Created by HP on 27/12/2017.
 */

public class Stock2 {

        String LibBes,TypeBes,NomEmp,date;
        int  Qte;


    public String getLibBes() {
        return LibBes;
    }

    public String getNomEmp() {
        return NomEmp;
    }

    public int getQte() {
        return Qte;
    }

    public String getTypeBes() {
        return TypeBes;
    }


    public String getDate() {
        return date;
    }

    public Stock2(String libBes, String typeBes, int qte, String nomEmp,String Date) {
        LibBes = libBes;
        TypeBes = typeBes;
        Qte = qte;
        NomEmp = nomEmp;
        date=Date;
    }

    @Override
    public String toString() {
        return  "Besoin: " + LibBes  +
                "\nType: " + TypeBes +
                "\nQuantité: " + Qte +
                "\nReçu par: " + NomEmp+
                "\nLe:  "+date+"\n";
    }
    /* public Stock2(String libBes, String typeBes, int qte) {
            this.LibBes = libBes;
            this.TypeBes = typeBes;
            this.Qte = qte;
        }

        public String getLibBes() {
            return LibBes;
        }

        public String getTypeBes() {
            return TypeBes;
        }



        public int getQte() {
            return Qte;
        }

        @Override
        public String toString() {
            return  "Besoin=" + LibBes +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tType=" + TypeBes +
                    "\nQuantité=" + Qte;
        }*/


}
