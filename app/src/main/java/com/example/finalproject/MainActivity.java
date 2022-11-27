package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn0,btn1;
    TextView display;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display=findViewById(R.id.display);
        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUP();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });



    }
    public void openSignUP(){
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }
    public void openLogin(){
        Intent intent = new Intent(this,LogIn.class);
        startActivity(intent);
    }
}
/*
 * to make a button open another activity
 * 1. right click package (root folder of mainactivity file) > new activity
 * 2. for the button u want to press that would start a new activity put a method in the onclick method
 * 3. in the class method scope implement the method in step 2
 * https://www.youtube.com/watch?v=bgIUdb-7Rqo
 * */