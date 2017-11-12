package com.wherecycle.smartrecycle.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wherecycle.smartrecycle.R;

public class RecyclableItemViewHolder extends RecyclerView.ViewHolder {

    private final ImageView imageView;
    private final TextView textView;

    public RecyclableItemViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        textView = (TextView) itemView.findViewById(R.id.titleText);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }
}
