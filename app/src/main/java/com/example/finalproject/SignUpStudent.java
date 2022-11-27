package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class SignUpStudent extends AppCompatActivity {
    Button btn0;
    TextView display;

    EditText editTextTextID;
    EditText editTextPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_student);

        display=findViewById(R.id.display);
        btn0=findViewById(R.id.btn0);

        editTextTextID = findViewById(R.id.editTextTextID);
        editTextPass = findViewById(R.id.editTextPass);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openSignUPAdmin();
                if(editTextTextID.getText().toString().matches("") || editTextPass.getText().toString().matches("")){
                    display.setText("Missing Criteria");
                }else{
                    updateID();
                }
            }
        });
    }
    public void updateID(){
        String user = editTextTextID.getText().toString();
        String pass = editTextPass.getText().toString();

        DatabaseStudent dbAdmin1 = new DatabaseStudent(SignUpStudent.this);
        List<Student> adminList = dbAdmin1.getAllStudents();
        Log.d("60","Testing: User already in system-student");
        while (adminList.isEmpty()==false){

            Log.d("61","Testing: User already in system");
            String curr = adminList.remove(0).getstudentID();
            if(curr.equals(user)){
                //user already in system
                display.setText("Username already in use");
                editTextTextID.setText("");
                editTextPass.setText("");
                Log.d("65","User already in system");

                return;
            }else{
                Log.d("66","Testing: no match");
            }
        }
        boolean go = true;
        go=true;

        if(go==true){
            Student aCurrent = new Student(user,pass);

            DatabaseStudent dbAdmin = new DatabaseStudent(SignUpStudent.this);
            boolean success = dbAdmin.add(aCurrent);
            if(success==true){
                editTextTextID.setText("");
                editTextPass.setText("");
                display.setText("New Student Created: Username: "+user+"; Password: "+pass);
            }else{
                //editTextTextID.setText("");
                editTextPass.setText("");
                display.setText("Sign Up Failed");
            }
        }
    }
}