package com.example.hp.madose.MyAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.madose.R;
import com.example.hp.madose.model.ItemD;

import java.util.List;

/**
 * Created by erick on 03/04/2018.
 */

public class MyAdapterCat extends RecyclerView.Adapter<MyAdapterCat.ViewHolder> {
    private List<ItemD> listItems;
    private Context context;

    public MyAdapterCat(List<ItemD> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public MyAdapterCat.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.departement_liste_item,parent,false);
        return new MyAdapterCat.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemD listItem=listItems.get(position);
        holder.textnom.setText(listItem.getNom());
    }



    @Override
    public int getItemCount() {
        return listItems.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textnom;

        public ViewHolder(View itemView) {
            super(itemView);
            textnom=(TextView)itemView.findViewById(R.id.depNom);
        }
    }
}
