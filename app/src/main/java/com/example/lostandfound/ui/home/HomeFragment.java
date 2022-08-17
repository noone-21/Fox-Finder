package com.example.lostandfound.ui.home;
import com.example.lostandfound.lost;
import com.example.lostandfound.newPost;
import com.example.lostandfound.database;
import com.example.lostandfound.found;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lostandfound.R;
import com.example.lostandfound.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Activity context;
    FloatingActionButton fab;
    database DB;
    String name,ph;
    Button lost,found;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;

    }
    public void onStart(){
        super.onStart();
        lost=(Button) getView().findViewById(R.id.lost);
        found=(Button) getView().findViewById(R.id.found);

        DB = new database(context);
        Intent intent = context.getIntent();
        String e = intent.getStringExtra("str");
        Cursor cursor=DB.getData("e");
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();

        while(cursor.moveToNext()){
            stringBuilder.append(cursor.getString(0));
            stringBuilder1.append(cursor.getString(3));
            name = stringBuilder.toString();
            ph = stringBuilder1.toString();
        }

        lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,lost.class);
                startActivity(intent);
            }
        });
        found.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,found.class);
                startActivity(intent);
            }
        });
        fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context,newPost.class);
                intent.putExtra("email",e);
                startActivity(intent);
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}