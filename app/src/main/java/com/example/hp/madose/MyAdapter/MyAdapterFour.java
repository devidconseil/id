package com.example.hp.madose.MyAdapter;

/**
 * Created by erick on 28/03/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.hp.madose.R;
import com.example.hp.madose.model.Item;
import com.example.hp.madose.model.ItemF;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;

import java.util.List;

public class MyAdapterFour extends RecyclerView.Adapter<MyAdapterFour.ViewHolder> {

private List<ItemF> listItems;
private Context context;

    public MyAdapterFour(List<ItemF> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public MyAdapterFour.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.fournisseur_liste_item,parent,false);
        return new ViewHolder(view );
    }

    @Override
    public void onBindViewHolder(MyAdapterFour.ViewHolder holder, int position) {
        ItemF listItem=listItems.get(position);
        holder.textnom.setText(listItem.getNom());
        holder.textcontact.setText(listItem.getContact());
        holder.textadress.setText(listItem.getAdresse());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textnom,textcontact,textadress;

        public ViewHolder(View itemView) {
            super(itemView);
            textnom=(TextView)itemView.findViewById(R.id.fourNom);
            textcontact=(TextView)itemView.findViewById(R.id.fourContact);
            textadress=(TextView)itemView.findViewById(R.id.fourAdres);
        }
    }
}
