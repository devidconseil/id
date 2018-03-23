package com.example.hp.madose;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TableListe extends AppCompatActivity {

    BaseDeDonne bd=new BaseDeDonne(this);
    List<String> liste=new ArrayList<>();
    TableLayout tableLayout;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_liste);

        tableLayout=(TableLayout)findViewById(R.id.tableau);
        tableLayout.setPadding(12,16,12,16);





       /* TextView textView1=(TextView)findViewById(R.id.n1);
        TextView textView2=(TextView)findViewById(R.id.n2);
        TextView textView3=(TextView)findViewById(R.id.n3);
        TextView textView4=(TextView)findViewById(R.id.n4);
        TextView textView5=(TextView)findViewById(R.id.n5);
        TextView textView6=(TextView)findViewById(R.id.n6);*/


        BaseDeDonne bd=new BaseDeDonne(this);
        TableRow tl=new TableRow(this);
        tl.setBackgroundColor(Color.parseColor("#17631E"));
        tl.setPadding(12,16,12,16);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tl.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }
        TextView nomt=new TextView(this);
        nomt.setText("Nom");
        nomt.setTextColor(Color.parseColor("#FFFFFF"));
        nomt.setTypeface(null, Typeface.BOLD);
        nomt.setPadding(15,15,15,15);
        tl.addView(nomt);
        TextView prent=new TextView(this);
        prent.setTypeface(null, Typeface.BOLD);
        prent.setTextColor(Color.parseColor("#FFFFFF"));
        prent.setText("Prénoms");
        prent.setPadding(15,15,15,15);
        tl.addView(prent);
        TextView mailt=new TextView(this);
        mailt.setTypeface(null, Typeface.BOLD);
        mailt.setTextColor(Color.parseColor("#FFFFFF"));
        mailt.setText("E-mail");
        mailt.setPadding(15,15,15,15);
        tl.addView(mailt);
        TextView contactt=new TextView(this);
        contactt.setTextColor(Color.parseColor("#FFFFFF"));
        contactt.setText("Contact");
        contactt.setTypeface(null, Typeface.BOLD);
        contactt.setPadding(15,15,15,15);
        tl.addView(contactt);
        TextView departementt=new TextView(this);
        departementt.setText("Departement");
        departementt.setTextColor(Color.parseColor("#FFFFFF"));
        departementt.setTypeface(null, Typeface.BOLD);
        departementt.setPadding(15,15,15,15);
        tl.addView(departementt);
        TextView profilt=new TextView(this);
        profilt.setText("Profil");
        profilt.setTextColor(Color.parseColor("#FFFFFF"));
        profilt.setTypeface(null, Typeface.BOLD);
        profilt.setPadding(15,15,15,15);
        tableLayout.addView(tl,new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        int count=0;
        final List<UtilisateurC> departem= bd.afficheE();
        for (UtilisateurC utilisateurC : departem){

            TableRow tr=new TableRow(this);
            tr.setPadding(12,16,12,16);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tr.setLayoutParams(new ActionMenuView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }
            if (count %2!=0) tr.setBackgroundColor(Color.parseColor("#d1d2d2"));
            TextView nom=new TextView(this);
            nom.setPadding(15,15,15,15);
            nom.setTextColor(Color.parseColor("#000000"));
            nom.setText(utilisateurC.toStringNom());
            tr.addView(nom);
            TextView pren=new TextView(this);
            pren.setTextColor(Color.parseColor("#000000"));
            pren.setPadding(15,15,15,15);
            pren.setText(utilisateurC.toStringPren());
            tr.addView(pren);
            TextView mail=new TextView(this);
            mail.setTextColor(Color.parseColor("#17631E"));
            mail.setPadding(15,15,15,15);
            mail.setText(utilisateurC.toStringMail());
            tr.addView(mail);
            TextView contact=new TextView(this);
            contact.setTextColor(Color.parseColor("#000000"));
            contact.setPadding(15,15,15,15);
            contact.setText(utilisateurC.toStringTel());
            tr.addView(contact);
            TextView departement=new TextView(this);
            departement.setPadding(15,15,15,15);
            departement.setTextColor(Color.parseColor("#2b5a83"));
            departement.setText(utilisateurC.toStringDepart());
            tr.addView(departement);
            TextView profil=new TextView(this);
            profil.setPadding(15,15,15,15);
            profil.setText(utilisateurC.toStringProf());
           //View view=new View(this);
          //  view.setMinimumHeight(5);
          //  view.setBackgroundColor(R.color.wallet_holo_blue_light);
            tableLayout.addView(tr,new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
                /*textView1.append(utilisateurC.toStringNom()+"\n\n");
                textView2.append(utilisateurC.toStringPren()+"\n\n");
                textView3.append(utilisateurC.toStringMail()+"\n\n");
                textView4.append(utilisateurC.toStringTel()+"\n\n");
                textView5.append(utilisateurC.toStringDepart()+"\n\n");
                textView6.append(utilisateurC.toStringProf()+"\n\n");*/
                count++;


        }
       // ListAdapter departe=new ArrayAdapter<UtilisateurC>(this, android.R.layout.simple_list_item_1, departem);
       //ListView affiche=(ListView)findViewById(R.id.maliste);


       // final ListUtilisateur listeUti=new ListUtilisateur(this,liste);
      //  affiche.setAdapter(departe);

    }

  /* class ListUtilisateur extends BaseAdapter
    {

        Context context;
        List<String> listeU=new ArrayList<>();

        public ListUtilisateur(Context context, List<String> listeU) {
            this.context = context;
            this.listeU = listeU;
        }

        @Override
        public int getCount() {
            return listeU.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView=getLayoutInflater().inflate(R.layout.activity_table_liste_image,null);
            TextView textView1=(TextView)convertView.findViewById(R.id.n1);
            TextView textView2=(TextView)convertView.findViewById(R.id.n2);
            TextView textView3=(TextView)convertView.findViewById(R.id.n3);
            TextView textView4=(TextView)convertView.findViewById(R.id.n4);
            TextView textView5=(TextView)convertView.findViewById(R.id.n5);
            TextView textView6=(TextView)convertView.findViewById(R.id.n6);


                textView1.setText(listeU.get(position));
                textView2.setText(listeU.get(position));
                textView3.setText(listeU.get(position));
                textView4.setText(listeU.get(position));
                textView5.setText(listeU.get(position));
                textView6.setText(listeU.get(position));

            return convertView;
        }
    }*/
}
