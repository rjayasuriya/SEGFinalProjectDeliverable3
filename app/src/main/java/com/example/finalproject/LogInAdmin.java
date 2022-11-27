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

public class LogInAdmin extends AppCompatActivity {
    Button btn0;  // uml check
    TextView display;//uml check

    EditText editTextTextID;
    EditText editTextPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // uml check
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_admin);
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

        DatabaseAdmin dbAdmin1 = new DatabaseAdmin(LogInAdmin.this);
        List<Admin> adminList = dbAdmin1.getAllAdmins();
        Log.d("60","Testing: User already in system");
        while (adminList.isEmpty()==false){
            Log.d("61","Testing: User already in system");
            Admin curr = adminList.remove(0);
            if(curr.getUserName().equals(user)){
                if(curr.getPassWord().equals(pass)){
                    display.setText("Log In Success");
                    editTextTextID.setText("");
                    editTextPass.setText("");
                    display.setText("");
                    openLogInAdminSuccess();
                    return;
                }else{
                    display.setText("Log  In Fail");
                    return;
                }
                //user already in system



            }
        }

        display.setText("Log in Failed");
        editTextTextID.setText("");
        editTextPass.setText("");
    }
    public void openLogInAdminSuccess(){// uml check
        Intent intent = new Intent(this,LogInAdminSuccess.class);
        startActivity(intent);
    }
}