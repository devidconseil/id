package com.example.hp.madose;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 23/04/2018.
 */

public class NotificationArea extends AppCompatActivity {
    BaseDeDonne bd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_area);
        bd=new BaseDeDonne(this);
        List<String> notification=new ArrayList<>();
        for (String mail: bd.accountNotValidate()){
          notification.add("Un nouvel utilisateur enregistré est en attente de validation:"+mail);
        }
        ListAdapter arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,notification);
        final ListView listView= findViewById(R.id.listview_area);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int a=listView.getItemAtPosition(position).toString().length();
                MyApplication.mail=listView.getItemAtPosition(position).toString().substring(62,a);
                Intent intent=new Intent(NotificationArea.this,Utilisateur.class);
                intent.putExtra("status","new user validating");
                startActivity(intent);
               // Toast.makeText(getApplicationContext(), MyApplication.mail,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
