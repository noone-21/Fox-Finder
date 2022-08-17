package com.example.lostandfound;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.internal.StringResourceValueReader;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter <myViewHolder> {

    ArrayList<Model>data;
    public myAdapter(ArrayList<Model>data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_singleitem, parent, false);
        return new myViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
          holder.itemN.setText(data.get(position).getHeader());
          holder.name.setText(data.get(position).getName());
          holder.image.setImageBitmap(utils.getImage(data.get(position).getImgName()));
          holder.description.setText(data.get(position).getDesc());
          holder.phone.setText(data.get(position).getPhoneNum());

    }

    @Override
    public int getItemCount() {

        return data.size();
    }


    }