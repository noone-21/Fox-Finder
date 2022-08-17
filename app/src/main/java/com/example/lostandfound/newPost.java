package com.example.lostandfound;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.cast.framework.media.ImagePicker;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class newPost extends AppCompatActivity {

    ImageButton gallery,camera;
   CircularImageView iv_user;
   EditText itemName,description;
   RadioGroup radioGroup;
   RadioButton radioButton;
   database DB;
   databasePost DBP;
   Button edit;
   String name,ph;
   Uri u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        itemName = (EditText) findViewById(R.id.itemname);
        description = (EditText) findViewById(R.id.itemdesc);
        radioGroup = (RadioGroup) findViewById(R.id.radiotype);
        edit = (Button) findViewById(R.id.edit);

        DB = new database(this);
        DBP = new databasePost(this);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        Cursor cursor=DB.getData(email);
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();

        while(cursor.moveToNext()){
            stringBuilder.append(cursor.getString(0));
            stringBuilder1.append(cursor.getString(3));
            name = stringBuilder.toString();
            ph = stringBuilder1.toString();
        }


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
                String n = name;
                String des = description.getText().toString();
                String iname = itemName.getText().toString();
                String p = ph;
                String type = radioButton.getText().toString();

                if (n.isEmpty() || des.isEmpty() || iname.isEmpty()  || p.isEmpty())
                    Toast.makeText(newPost.this, "Fill all the fields!", Toast.LENGTH_SHORT).show();
                else {
                    try{
                        InputStream inputStream = getContentResolver().openInputStream(u);
                        byte[] img = utils.getBytes(inputStream);
                        Boolean insert = DBP.insertDataPost(n,iname,des,img,p,type);
                        if (insert) {
                            Toast.makeText(newPost.this, "Post Successfully!:)", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), lost.class);
                            startActivity(intent);
                            finish();
                        } else
                            Toast.makeText(newPost.this, "Post Failed!:(", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        Toast.makeText(newPost.this,"Error",Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

    }

       public void checkButton(View v){
          int radioID = radioGroup.getCheckedRadioButtonId();
          radioButton = findViewById(radioID);
         // Toast.makeText(this, radioButton.getText(),Toast.LENGTH_SHORT).show();
       }
       public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) newPost.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.selectitems, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(newPost.this);
                builder.setView(v);
                final AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();

                gallery = alertDialog.findViewById(R.id.gallery);
                gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, 0);
                        alertDialog.dismiss();
                    }
                });

                camera = alertDialog.findViewById(R.id.camera);
                camera.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View v) {

                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
                        } else {
                            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takePicture, 1);
                            alertDialog.dismiss();
                        }
                    }
                });
            }




       protected void onActivityResult(int reqCode, int resultCode,@Nullable Intent data) {
            super.onActivityResult(reqCode, resultCode, data);
            iv_user = (CircularImageView)findViewById(R.id.iv_users);
            Bitmap bitmap;
            switch (reqCode) {
                case 0:
                    if (resultCode == RESULT_OK) {
                        Uri uri = data.getData();
                        u = uri;
                        try {
                              bitmap = MediaStore.Images.Media.getBitmap(newPost.this.getContentResolver(), uri);
                        } catch (IOException e) {
                            e.printStackTrace();
                            bitmap = (Bitmap)(data.getExtras().get("data"));
                        }
                        iv_user.setImageBitmap(bitmap);
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK) {
                        Uri uri = data.getData();
                        u = data.getData();
                        Bundle extras = data.getExtras();
                        bitmap = (Bitmap) extras.get("data");
                        iv_user.setImageBitmap(bitmap);
                    }
                    break;
            }
        }
}
