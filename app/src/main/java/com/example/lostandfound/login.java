package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class login extends AppCompatActivity {

    EditText email,password;
    Button signIn;
    Button signUp;
    database DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.login);
        signUp = (Button) findViewById(R.id.signup);
        DB = new database(this);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,signup.class);
                startActivity(intent);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString().trim();
                String pass = password.getText().toString();
                if (e == "" || pass == "")
                    Toast.makeText(login.this, "Fill both the fields!", Toast.LENGTH_SHORT).show();
                else {
                    Boolean login = DB.loginCheck(e,pass);
                    if(login){
                        Toast.makeText(login.this,"Login Successfully!:)",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),dashboard.class);
                        intent.putExtra("str",e);

                        startActivity(intent);
                        finish();
                    }else
                        Toast.makeText(login.this,"Invalid Credentials!:(",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}