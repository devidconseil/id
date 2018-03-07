package com.example.hp.madose.MyAdapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hp.madose.BaseDeDonne;
import com.example.hp.madose.BesoinC;
import com.example.hp.madose.UtilisateurC;
import com.example.hp.madose.R;
import com.example.hp.madose.model.Item;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by erick on 24/02/2018.
 */

class MyViewHolderWithouChild extends RecyclerView.ViewHolder{

    public TextView textView;

    public MyViewHolderWithouChild(View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.textp);
    }
}
class MyViewHolderWithChild extends RecyclerView.ViewHolder {

    public TextView textView,textViewChild,textStock;
    public ImageView imageView;
    public RelativeLayout button;
    public ExpandableLinearLayout expandableLinearLayout;

    public MyViewHolderWithChild(View itemView) {
        super(itemView);
        textView=(TextView)itemView.findViewById(R.id.textp);
        textViewChild=(TextView)itemView.findViewById(R.id.texten);
        textStock=(TextView)itemView.findViewById(R.id.texteo);
        imageView=(ImageView)itemView.findViewById(R.id.ima);
        button=(RelativeLayout) itemView.findViewById(R.id.buttonr);
        expandableLinearLayout=(ExpandableLinearLayout)itemView.findViewById(R.id.expandableLayout);

    }
}

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<Item>items;
    Context context;
    SparseBooleanArray expandState=new SparseBooleanArray();


    public MyAdapter(List<Item> items) {
        this.items = items;
        for (int i=0;i<items.size();i++)
            expandState.append(i,false);

    }


    @Override
    public int getItemViewType(int position) {
        if (items.get(position).isExpandable()) return 1;
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
            View view=inflater.inflate(R.layout.layout_with_child,parent,false);
            return new MyViewHolderWithChild(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType())
        {
            case 0: {
                MyViewHolderWithouChild viewHolder = (MyViewHolderWithouChild) holder;
                Item item = items.get(position);
                viewHolder.setIsRecyclable(false);
                viewHolder.textView.setText(item.getText());
            }
            break;
            case 1:
            {

                final MyViewHolderWithChild viewHolder = (MyViewHolderWithChild) holder;
                Item item = items.get(position);
                viewHolder.setIsRecyclable(false);

                // ce gar la n'aime pas les int
                viewHolder.imageView.setImageResource(item.getImage());
                viewHolder.textView.setText(item.getText());
                viewHolder.textStock.setText(item.getStock());
               // Picasso.with(context).load(item.getImage()).into(viewHolder.imageView);


                viewHolder.expandableLinearLayout.setInRecyclerView(true);
                viewHolder.expandableLinearLayout.setExpanded(expandState.get(position));
                viewHolder.expandableLinearLayout.setListener(new ExpandableLayoutListenerAdapter() {


                    @Override
                    public void onPreOpen() {
                                changeRotate(viewHolder.button,0f,180f).start();
                                expandState.put(position,true);
                    }

                    @Override
                    public void onPreClose() {
                        changeRotate(viewHolder.button,180f,0f).start();
                        expandState.put(position,false);
                    }

                });

                viewHolder.button.setRotation(expandState.get(position)?180f:0f);
                viewHolder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.expandableLinearLayout.toggle();
                    }
                });
                viewHolder.textViewChild.setText(items.get(position).getSubtext());
                //viewHolder.textStock.setText(items.get(position).getStock());
            }
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
