package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {
    Button btn0,btn1,btn2;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        display=findViewById(R.id.display);
        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSignUPAdmin();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUPStudent();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUPInstructor();
            }
        });
    }
    public void openSignUPAdmin(){
        Intent intent = new Intent(this,SignUpAdmin.class);
        startActivity(intent);
    }
    public void openSignUPStudent(){
        Intent intent = new Intent(this,SignUpStudent.class);
        startActivity(intent);
    }
    public void openSignUPInstructor(){
        Intent intent = new Intent(this,SignUpInstructor.class);
        startActivity(intent);
    }
}