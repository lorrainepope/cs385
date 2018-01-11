package com.wherecycle.smartrecycle.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wherecycle.smartrecycle.R;
import com.wherecycle.smartrecycle.model.RecycleableItem;
import com.wherecycle.smartrecycle.model.RecycleableType;

import java.util.List;
/*
* created by Lorraine
* This class creates the required viewholder and binds the viewholder to the data from the
* recyclableItem class*/
public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclableItemViewHolder> {

    private final Context _context;
    private final List<RecycleableItem> _items;

    public MyRecyclerAdapter(Context context, List<RecycleableItem> items){
        _context = context;
        _items = items;
    }
    //layout inflater turns the xml file into the view object
    @Override
    public RecyclableItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(_context);
        View view = inflater.inflate(R.layout.item_recyclable, parent, false);
        return new RecyclableItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclableItemViewHolder holder, int position) {
        RecycleableItem item = _items.get(position);
        int resourceId = 0;

        if (item.getType() == RecycleableType.Aluminium)
            resourceId = R.drawable.cans;
        else if (item.getType() == RecycleableType.Batteries)
            resourceId = R.drawable.batteries;
        else if (item.getType() == RecycleableType.Cardboard)
            resourceId = R.drawable.cardboard;
        else if (item.getType() == RecycleableType.Electronics)
            resourceId = R.drawable.electronics;
        else if (item.getType() == RecycleableType.Furniture)
            resourceId = R.drawable.furniture;
        else if (item.getType() == RecycleableType.Glass)
            resourceId = R.drawable.glassbottles;
        else if(item.getType() == RecycleableType.Plastics)
            resourceId = R.drawable.plastics;
        else if (item.getType() == RecycleableType.ScrapMetal)
            resourceId = R.drawable.scrapmetal;
        else if (item.getType() == RecycleableType.Textiles)
            resourceId = R.drawable.clothes;

        holder.getTextView().setText(item.getType().toString());
        Drawable d = _context.getResources().getDrawable(R.drawable.batteries);
        holder.getImageView().setImageDrawable(d);
        holder.getImageView().setImageResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return _items.size();
    }


}
