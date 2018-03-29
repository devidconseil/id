package com.example.hp.madose;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hp.madose.MyAdapter.MyAdapter;
import com.example.hp.madose.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Display extends AppCompatActivity {

   RecyclerView list;
   TextView textView;
   RecyclerView.LayoutManager layoutManager;
   List<Item>items =new ArrayList<>();
   List<Item>ite =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        RecyclerView list=(RecyclerView)findViewById(R.id.recycler);
        list.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);


        setData();
    }



    private void setData()
    {

        BaseDeDonne bd=new BaseDeDonne(this);
        List<BesoinC> affC = bd.afficheB();
        textView=(TextView)findViewById(R.id.libS);
        RecyclerView list=(RecyclerView)findViewById(R.id.recycler);
        for (BesoinC emp : affC)
        {       //ce gar la n'aime pas les int 
                Item item=new Item(emp.getLibBes(),emp.getTypeBes(), emp.getImageBes(),String.valueOf(emp.getStockBes()),emp.getLibCat(),true);
                items.add(item);

        }
        MyAdapter adapter=new MyAdapter(items);
        list.setAdapter(adapter);
    }
}
