package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LogIn extends AppCompatActivity {
    Button btn0,btn1,btn2; //uml check
    TextView display; //uml check

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) { //uml check
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        display=findViewById(R.id.display);
        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               openLogInAsAdmin();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogInAsStudent();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogInAsInstructor();
            }
        });
    }
    public void openLogInAsAdmin(){ //uml check
        Intent intent = new Intent(this,LogInAdmin.class);
        startActivity(intent);
    }
    public void openLogInAsStudent(){ //uml check
        Intent intent = new Intent(this,LogInStudent.class);
        startActivity(intent);
    }
    public void openLogInAsInstructor(){ //uml check
        Intent intent = new Intent(this,LogInInstructor.class);
        startActivity(intent);
    }
}