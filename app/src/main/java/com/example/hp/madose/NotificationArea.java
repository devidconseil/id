package com.example.hp.madose;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by HP on 23/04/2018.
 */

public class NotificationArea extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_area);
        ListAdapter arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,MyApplication.notifications);
        ListView listView= findViewById(R.id.listview_area);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
