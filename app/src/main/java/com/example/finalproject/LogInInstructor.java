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

public class LogInInstructor extends AppCompatActivity {
    Button btn0; // uml check
    TextView display; // uml check

    EditText editTextTextID; // uml check
    EditText editTextPass;// uml check

    @Override
    protected void onCreate(Bundle savedInstanceState) { // uml check
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_instructor);
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

        DatabaseInstructors dbAdmin1 = new DatabaseInstructors(LogInInstructor.this);
        List<Instructor> adminList = dbAdmin1.getAllInstructors();
        Log.d("60","Testing: User already in system-log in instructor");
        while (adminList.isEmpty()==false){
            Log.d("61","Testing: User already in system");
            Instructor curr = adminList.remove(0);
            if(curr.getinstructorID().equals(user)){
                if(curr.getPassword().equals(pass)){
                    display.setText("Log In Success");
                    editTextTextID.setText("");
                    editTextPass.setText("");
                    display.setText("");
                    Intent intent = new Intent(this,LogInInstructorSuccess.class);
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
    public void openLogInInstructorSuccess(){
        Intent intent = new Intent(this,LogInInstructorSuccess.class);
        startActivity(intent);
    }
}