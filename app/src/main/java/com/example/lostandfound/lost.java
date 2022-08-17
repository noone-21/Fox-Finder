package com.example.lostandfound;

import com.example.lostandfound.ui.home.HomeFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lostandfound.ui.home.HomeFragment;

import java.util.ArrayList;

public class lost extends AppCompatActivity {

    RecyclerView rcv;
    Button found;
    myAdapter adapter;
    databasePost DBP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        found = findViewById(R.id.found);

        found.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(lost.this,found.class);
                startActivity(intent);
                finish();
            }
        });
        rcv = findViewById(R.id.rclview2);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new myAdapter(dataqueue());
        rcv.setAdapter(adapter);
    }

    public ArrayList<Model> dataqueue(){
        ArrayList<Model> holder = new ArrayList<>();



        DBP = new databasePost(this);
        Cursor cursor=DBP.getData("Lost");

        while(cursor.moveToNext()){
            Model obj = new Model(cursor.getString(0),cursor.getString(1),cursor.getString(2)
            ,cursor.getString(4),cursor.getBlob(3));
            holder.add(obj);
        }

        return holder;
    }
}