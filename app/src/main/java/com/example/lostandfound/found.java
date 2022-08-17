package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class found extends AppCompatActivity {

    RecyclerView rcv;
    Button lost;
    myAdapter adapter;
    databasePost DBP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        lost = findViewById(R.id.btnLost);

        lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(found.this,lost.class);
                startActivity(intent);
                finish();
            }
        });
        rcv = findViewById(R.id.rclview);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new myAdapter(dataqueue());
        rcv.setAdapter(adapter);
    }
    public ArrayList<Model> dataqueue(){
        ArrayList<Model> holder = new ArrayList<>();



        DBP = new databasePost(this);
        Cursor cursor=DBP.getData("Found");

        while(cursor.moveToNext()){
            Model obj = new Model(cursor.getString(0),cursor.getString(1),cursor.getString(2)
                    ,cursor.getString(4),cursor.getBlob(3));
            holder.add(obj);
        }

        return holder;
    }
}