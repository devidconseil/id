package com.example.hp.madose;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BesoinAdapter extends BaseAdapter {

    List<String> strings;
    Context context;
    List<Integer> imagesId;
    private static LayoutInflater layoutInflater=null;

    public BesoinAdapter(Context context, List<String> strings, List<Integer> imagesId) {
        this.strings = strings;
        this.context = context;
        this.imagesId = imagesId;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int i) {
        return imagesId.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class Holder{
        TextView textView;
        ImageView imageView;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final Holder holder=new Holder();

        view=layoutInflater.inflate(R.layout.besoin_list_item,null);


        holder.textView= view.findViewById(R.id.textView8);
        holder.imageView= view.findViewById(R.id.imageView);

        holder.textView.setText(strings.get(i));
        Picasso.with(context).load(imagesId.get(i)).into(holder.imageView);

        if (MyApplication.isTextView()) {
            holder.textView.setVisibility(View.INVISIBLE);
        }

        if (!MyApplication.isTextView()) {
            holder.textView.setVisibility(View.VISIBLE);
        }




       holder.imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.verif1=holder.textView.getText().toString();
                MyApplication.id=context.getResources().getIdentifier(MyApplication.verif1,"drawable",context.getPackageName());



                Intent intent=new Intent(context,Besoin.class);
                intent.putExtra("Image","Besoin");
                context.startActivity(intent);

                MyApplication.setVerif(true);


            //  Toast.makeText(context,holder.textView.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
