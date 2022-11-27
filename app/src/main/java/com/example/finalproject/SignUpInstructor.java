package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class SignUpInstructor extends AppCompatActivity {
    Button btn0;
    TextView display;

    EditText editTextTextID;
    EditText editTextPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_instructor);

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
       // display.setText("ID: "+editTextTextID.getText()+"; Password: "+editTextPass.getText());
        //editTextTextID.setText("");
        //editTextPass.setText("");
        String user = editTextTextID.getText().toString();
        String pass = editTextPass.getText().toString();
        // First check if username already in use
        DatabaseInstructors dbAdmin1 = new DatabaseInstructors(SignUpInstructor.this);
        List<Instructor> adminList = dbAdmin1.getAllInstructors();
        Log.d("60","Testing: User already in system-instructor");
        while (adminList.isEmpty()==false){
            Log.d("61","Testing: User already in system");
            String curr = adminList.remove(0).getinstructorID();
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
            Instructor aCurrent = new Instructor(user,pass);

            DatabaseInstructors dbAdmin = new DatabaseInstructors(SignUpInstructor.this);
            boolean success = dbAdmin.add(aCurrent);
            if(success==true){
                editTextTextID.setText("");
                editTextPass.setText("");
                display.setText("New Instructor Created: Username: "+user+"; Password: "+pass);
            }else{
                //editTextTextID.setText("");
                editTextPass.setText("");
                display.setText("Sign Up Failed");
            }
        }
    }
}