package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostandfound.ui.profile.ProfileFragment;

public class editProfile extends AppCompatActivity {

    EditText name,password,phone;
    TextView email;
    Button update;
    database DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.email);
        update = (Button) findViewById(R.id.edit);

        DB = new database(this);
        Intent intent = getIntent();
        String e = intent.getStringExtra("email");
        Cursor cursor = DB.getData(e);

        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();

        while(cursor.moveToNext()){
            stringBuilder.append(cursor.getString(0));
            stringBuilder1.append(cursor.getString(1));
            stringBuilder2.append(cursor.getString(2));
            stringBuilder3.append(cursor.getString(3));
            name.setHint(stringBuilder);
            email.setText(stringBuilder1);
            password.setHint(stringBuilder2);
            phone.setHint(stringBuilder3);
        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String pass = password.getText().toString();
                String p = phone.getText().toString();
                Boolean updateData = DB.updateData(n,e,pass,p);
                if(updateData){
                    Toast.makeText(editProfile.this,"Update Successfully",Toast.LENGTH_SHORT).show();
                    Fragment profileFragment = new ProfileFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.editPage,profileFragment);

// Add operations here

                    transaction.commit();

                }else
                    Toast.makeText(editProfile.this,"Try Again!:(",Toast.LENGTH_SHORT).show();

            }
        });

    }
}