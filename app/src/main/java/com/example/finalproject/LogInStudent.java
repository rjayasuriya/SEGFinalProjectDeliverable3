package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class LogInStudent extends AppCompatActivity {
    Button btn0; // uml check
    TextView display; // uml check

    EditText editTextTextID; // uml check
    EditText editTextPass; // uml check

    @Override
    protected void onCreate(Bundle savedInstanceState) { // uml check
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_student);
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
    public void updateID(){ // uml check
        String user = editTextTextID.getText().toString();
        String pass = editTextPass.getText().toString();

        DatabaseStudent dbAdmin1 = new DatabaseStudent(LogInStudent.this);
        List<Student> adminList = dbAdmin1.getAllStudents();
        Log.d("60","Testing: User already in system-student-login");
        while (adminList.isEmpty()==false){
            Log.d("61","Testing: User already in system");
            Student curr = adminList.remove(0);
            if(curr.getstudentID().equals(user)){
                if(curr.getPassword().equals(pass)){
                    display.setText("Log In Success");
                    editTextTextID.setText("");
                    editTextPass.setText("");
                    display.setText("");
                    Intent intent = new Intent(this,LogInStudentSuccess.class);
                    intent.putExtra("user",user);
                    intent.putExtra("pass",pass);
                    startActivity(intent);
                    return;
                }else{
                    display.setText("Log In Fail");
                    return;
                }
                //user already in system



            }
        }
        display.setText("Log in Failed");
        editTextTextID.setText("");
        editTextPass.setText("");
    }
    public void openLogInStudentSuccess(){ // uml check
        Intent intent = new Intent(this,LogInStudentSuccess.class);
        startActivity(intent);
    }
}