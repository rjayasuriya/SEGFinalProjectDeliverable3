package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignUpAdmin extends AppCompatActivity {
    Button btn0;
    TextView display;

    EditText editTextTextID;
    EditText editTextPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_admin);

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
                    //by default it should be updateID
                    updateID();

                    //toast below works
                    //seeAllAdmin();
                    Toast.makeText(SignUpAdmin.this,"Testing toast",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public void updateID(){ // used when creating a new admin checks if username already in use
        String user = editTextTextID.getText().toString();
        String pass = editTextPass.getText().toString();

        // First check if username already in use
        DatabaseAdmin dbAdmin1 = new DatabaseAdmin(SignUpAdmin.this);
        List<Admin> adminList = dbAdmin1.getAllAdmins();
        Log.d("60","Testing: User already in system-admin");
         while (adminList.isEmpty()==false){
             Log.d("61","Testing: User already in system");
            String curr = adminList.remove(0).getUserName();
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

        ///


        //display.setText("ID: "+editTextTextID.getText()+"; Password: "+editTextPass.getText());



        //user can be put in the system
        boolean go = true;
        go=true;

        if(go==true){
            Admin aCurrent = new Admin(user,pass);

            DatabaseAdmin dbAdmin = new DatabaseAdmin(SignUpAdmin.this);
            boolean success = dbAdmin.add(aCurrent);
            if(success==true){
                editTextTextID.setText("");
                editTextPass.setText("");
                display.setText("New Admin Created: Username: "+user+"; Password: "+pass);
            }else{
                //editTextTextID.setText("");
                editTextPass.setText("");
                display.setText("Sign Up Failed");
            }
        }


    }


}