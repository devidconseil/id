package com.example.hp.madose;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class StockAffichAdapter extends BaseAdapter {

    List<String> strings;
    Context context;
    List<Integer> imagesId;
    private static LayoutInflater layoutInflater=null;

    public StockAffichAdapter(Context context, List<String> strings, List<Integer> imagesId) {
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
        Holder holder=new Holder();
        View vue;
        vue=layoutInflater.inflate(R.layout.stock_affich_list_item,null);

        holder.textView= vue.findViewById(R.id.textView8);
        holder.imageView= vue.findViewById(R.id.imageView);

        holder.textView.setText(strings.get(i));
        Picasso.with(context).load(imagesId.get(i)).into(holder.imageView);

        vue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        return vue;
    }
}
