package com.example.hp.madose.MyAdapter;

/**
 * Created by erick on 28/03/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.madose.Fournisseur;
import com.example.hp.madose.MyApplication;
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
        final ItemF listItem=listItems.get(position);
        holder.textnom.setText(listItem.getNom());
        holder.textcontact.setText(listItem.getContact());
        holder.textadress.setText(listItem.getAdresse());
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MyApplication.setModifFadre(listItem.getAdresse());
                MyApplication.setModifFnom(listItem.getNom());
                MyApplication.setModifFcont(listItem.getContact());
                MyApplication.setModifFId(listItem.getId());
                Intent intent=new Intent(context.getApplicationContext(), Fournisseur.class);
                intent.putExtra("action","modifier");
                context.startActivity(intent);
                Toast.makeText(context.getApplicationContext()," "+listItem.getId(),Toast.LENGTH_LONG).show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textnom,textcontact,textadress;
        public CardView cardView;


        public ViewHolder(final View itemView) {
            super(itemView);
            textnom=(TextView)itemView.findViewById(R.id.fourNom);
            textcontact=(TextView)itemView.findViewById(R.id.fourContact);
            textadress=(TextView)itemView.findViewById(R.id.fourAdres);
            cardView=(CardView)itemView.findViewById(R.id.cardfour);

        }
    }
}
