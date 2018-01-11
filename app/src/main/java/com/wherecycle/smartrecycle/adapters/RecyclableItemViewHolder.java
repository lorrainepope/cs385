package com.wherecycle.smartrecycle.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wherecycle.smartrecycle.BinCompaniesActivity;
import com.wherecycle.smartrecycle.R;
import com.wherecycle.smartrecycle.RecyclingSymbolsActivity;

/*
* created by Lorraine
* This class sets up the view holder for our recyclerView by getting the imageView and textView*/
public class RecyclableItemViewHolder extends RecyclerView.ViewHolder {

    private final ImageView imageView;
    private final TextView textView;

    public RecyclableItemViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);//finds the imageview
        textView = (TextView) itemView.findViewById(R.id.titleText);//finds the textview
        };


    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }




    }


