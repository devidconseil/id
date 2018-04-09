package com.example.hp.madose.MyAdapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hp.madose.R;
import com.example.hp.madose.model.Item;
import com.example.hp.madose.model.ItemU;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.List;

/**
 * Created by erick on 07/03/2018.
 */

class MyViewHolderWithChildUser extends RecyclerView.ViewHolder {

    public TextView textnomUti,textproUti,textemailUti,textcontUti,textdepartUti;
    public RelativeLayout button;
    public ExpandableLinearLayout expandableLinearLayout;

    public MyViewHolderWithChildUser(View itemView) {
        super(itemView);
        textnomUti=(TextView)itemView.findViewById(R.id.nomUti);
        textcontUti=(TextView)itemView.findViewById(R.id.contUit);
        textproUti=(TextView)itemView.findViewById(R.id.proUti);
        textdepartUti=(TextView)itemView.findViewById(R.id.departUti);
        textemailUti=(TextView)itemView.findViewById(R.id.emailUti);
        button=(RelativeLayout) itemView.findViewById(R.id.buttonUti);
        expandableLinearLayout=(ExpandableLinearLayout)itemView.findViewById(R.id.expandableLayoutUti);

    }
}

public class MyAdapterUser extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<ItemU> items;
    Context context;
    SparseBooleanArray expandState=new SparseBooleanArray();


    public MyAdapterUser(List<ItemU> items) {
        this.items = items;
        for (int i=0;i<items.size();i++)
            expandState.append(i,false);

    }


    @Override
    public int getItemViewType(int position) {
        if (items.get(position).isDeroulante()) return 1;
        else return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        if(viewType==0)//whithout item
        {
            LayoutInflater inflater=LayoutInflater.from(context);
            View view=inflater.inflate(R.layout.layout_without_child,parent,false);
            return new MyViewHolderWithouChild(view);
        }
        else
        {
            LayoutInflater inflater=LayoutInflater.from(context);
            View view=inflater.inflate(R.layout.layout_without_child_utilisateur,parent,false);
            return new MyViewHolderWithChildUser(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType())
        {
            case 0: {
                MyViewHolderWithouChild viewHolder = (MyViewHolderWithouChild) holder;
                ItemU item = items.get(position);
                viewHolder.setIsRecyclable(false);

            }
            break;
            case 1:
            {

                final MyViewHolderWithChildUser viewHolder = (MyViewHolderWithChildUser) holder;
                ItemU item = items.get(position);
                viewHolder.setIsRecyclable(false);


                // ce gar la n'aime pas les int
                viewHolder.textnomUti.setText(item.getNom());
                viewHolder.textproUti.setText(item.getProfil());
                viewHolder.textemailUti.setText(item.getMail());
                viewHolder.textcontUti.setText(item.getContact());
                viewHolder.textdepartUti.setText(item.getDepartement());
                // Picasso.with(context).load(item.getImage()).into(viewHolder.imageView);


                viewHolder.expandableLinearLayout.setInRecyclerView(true);
                viewHolder.expandableLinearLayout.setExpanded(expandState.get(position));
                viewHolder.expandableLinearLayout.setListener(new ExpandableLayoutListenerAdapter() {


                    @Override
                    public void onPreOpen() {
                        changeRotate(viewHolder.button, 0f, 180f).start();
                        expandState.put(position, true);
                    }

                    @Override
                    public void onPreClose() {
                        changeRotate(viewHolder.button, 180f, 0f).start();
                        expandState.put(position, false);
                    }

                });

                viewHolder.button.setRotation(expandState.get(position) ? 180f : 0f);
                viewHolder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.expandableLinearLayout.toggle();
                    }
                });
                //viewHolder.textViewChild.setText(items.get(position).getSubtext());
            }
            //viewHolder.textStock.setText(items.get(position).getStock());

            break;
            default:
                break;
        }
    }

    private ObjectAnimator changeRotate(RelativeLayout button, float from, float to) {
        ObjectAnimator animator=ObjectAnimator.ofFloat(button,"rotation",from,to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return  animator;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}