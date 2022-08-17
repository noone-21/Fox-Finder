package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.PatternSyntaxException;

public class signup extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText password;
    EditText confirmPassword;
    EditText phone;
    Button signUp;
    Button signIn;
    database DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmpass);
        phone = (EditText) findViewById(R.id.phone);
        signUp = (Button) findViewById(R.id.signup);
        signIn = (Button) findViewById(R.id.login);
        DB = new database(this);



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String e = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String cpass = confirmPassword.getText().toString().trim();
                String p = phone.getText().toString();

                if (n.isEmpty() || e.isEmpty() || pass.isEmpty() || cpass.isEmpty() || p.isEmpty())
                    Toast.makeText(signup.this, "Fill all the fields!", Toast.LENGTH_SHORT).show();
                else {
                   if (Patterns.EMAIL_ADDRESS.matcher(e).matches()) {
                        if (pass.equals(cpass)) {
                            Boolean check = DB.checkEmail(e);
                            if (!check) {
                                Boolean insert = DB.insertData(n, e, pass, p);
                                if (insert) {
                                    Toast.makeText(signup.this, "Registered Successfully!:)", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), dashboard.class);
                                    startActivity(intent);
                                    finish();
                                } else
                                    Toast.makeText(signup.this, "Registration Failed!:(", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(signup.this, "Email already exist.", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(signup.this, "Password not Matching!", Toast.LENGTH_SHORT).show();

                   } else
                       Toast.makeText(signup.this, "Enter a Valid Email!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this,login.class);
                startActivity(intent);
            }
        });

    }

}