package com.example.lostandfound.ui.profile;

import com.example.lostandfound.MainActivity;
import com.example.lostandfound.database;
import com.example.lostandfound.editProfile;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostandfound.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    Activity context;
    TextView name,email,phone;
    database DB;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }
    public void onStart(){
        super.onStart();
        name = (TextView)getView().findViewById(R.id.name);
        email = (TextView)getView().findViewById(R.id.email);
        phone = (TextView)getView().findViewById(R.id.phone);
        Intent intent= context.getIntent();
        String e= intent.getStringExtra("str");
        DB = new database(context);

        Cursor cursor=DB.getData(e);
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();

        while(cursor.moveToNext()){
            stringBuilder.append(cursor.getString(0));
            stringBuilder1.append(cursor.getString(1));
            stringBuilder2.append(cursor.getString(3));
            name.setText(stringBuilder);
            email.setText(stringBuilder1);
            phone.setText(stringBuilder2);
        }

        Button editButton= (android.widget.Button) getView().findViewById(R.id.edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, editProfile.class);
                intent.putExtra("email",e);
                startActivity(intent);
            }
        });
        Button signOutButton= (android.widget.Button) getView().findViewById(R.id.signout);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}