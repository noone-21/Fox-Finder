package com.example.lostandfound;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myViewHolder extends RecyclerView.ViewHolder {

    TextView name,itemN,description,phone;
    ImageView image;

    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        itemN = (TextView) itemView.findViewById(R.id.itemName);
        description = (TextView) itemView.findViewById(R.id.description);
        phone = (TextView) itemView.findViewById(R.id.contact);
        image = (ImageView) itemView.findViewById(R.id.image);
    }
}
