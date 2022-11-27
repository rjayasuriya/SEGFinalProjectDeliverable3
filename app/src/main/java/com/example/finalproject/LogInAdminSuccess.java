package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LogInAdminSuccess extends AppCompatActivity {
    Button btn0; // uml check
    Button btn1;// uml check
    Button btn2;// uml check
    Button btn3;// uml check
    Button btn4;// uml check
    Button btn5;// uml check
    Button btn6;// uml check
    Button btn7;// uml check
    Button btn8;// uml check
    Button btn9;// uml check
    Button btn10;// uml check

    TextView display;// uml check
    TextView display2;// uml check

    EditText ClassCodeInput;// uml check
    EditText ClassNameInput;// uml check

    /*
     * Create Class: Done
     * Delete Class: Done
     * Change Class Name: Done
     * Change class code: done
     * Delete Admin: done
     * Delete Student:  done
     * Delete Instructor:  done

* * Functionality to add:
     *   1) view all courses
     *   2) search for specific course using course code or course name
     *   3) if course has no instructor, the instructor can assign himself to the course
     *   4) if the current instructor is already assigned to course,
     *           5) they must be able to unassign themselves from it
     *           6) edit course days
     *           7) edit course hours
     *           8) edit course description
     *           9) edit capacity
     *
    *
    * */

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_admin_success);

        display=findViewById(R.id.display);
        display2=findViewById(R.id.display2);
        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);
        btn10=findViewById(R.id.btn10);

        ClassCodeInput = findViewById(R.id.ClassCodeInput);
        ClassNameInput = findViewById(R.id.ClassNameInput);



        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show all list of all admins

                seeAllAdmin();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //views all classes
                seeAllStudents();


            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show all instructors
                seeAllInstructors();

             }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openSignUPAdmin();
                Log.d("69","86. clicking: view all classes");
                seeAllClasses();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make sure both fields are checked
                // create class
                //check if class already exists
                //if not add it
                // tell admin class already there
                if(ClassCodeInput.getText().toString().equals("")  ||ClassNameInput.getText().toString().equals("")){
                    Log.d("93","missing criteria");
                    display.setText("Missing criteria");
                    display2.setText("");

                }else{
                    Log.d("94","not missing criteria");
                    createClass();
                }

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // for changing class Name
                if(ClassCodeInput.getText().toString().equals("") || ClassNameInput.getText().toString().equals("")){
                    display.setText("Please enter a class Name and a new class Name");
                    display2.setText("");
                    return;
                }
                changeClassName();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change class code
                if(ClassCodeInput.getText().toString().equals("") || ClassNameInput.getText().toString().equals("")){
                    display.setText("Please enter a class ID and a new class ID");
                    display2.setText("");
                    return;
                }
                changeClassCode();
                //openSignUPAdmin();
                //seeAllClasses();
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClassCodeInput.getText().toString().equals("")){
                    display.setText("Please enter an Student username to delete");
                    display2.setText("");
                    return;
                }

                deleteStudent();
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("165","165 - starting to delete admin-dummy");
                if(ClassCodeInput.getText().toString().equals("") ){
                    display.setText("Please enter an Admin username to delete");
                    display2.setText("");
                    return;
                }

                //deleteAdmin();
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Delete class
                if(ClassCodeInput.getText().toString().equals("")){
                    display.setText("Please enter Class Code to Delete Class");
                    display2.setText("");
                }else{
                    deleteClass();
                }


            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("193","193 - starting to delete instructor");
                if(ClassCodeInput.getText().toString().equals("") ){
                    display.setText("Please enter an Instructor username to delete");
                    display2.setText("");
                    return;
                }
                deleteInstructor();

            }
        });

    }
    public void changeClassName(){ //uml check
        if(ClassNameInput.getText().toString().equals("") || ClassCodeInput.getText().toString().equals("")){
            //missing criteria
            display.setText("");
            display2.setText("Please enter a class ID, and a new ID");
        }else{
            String name = ClassNameInput.getText().toString();
            String id = ClassCodeInput.getText().toString();

            DatabaseClasses dbAdmin1 = new DatabaseClasses(LogInAdminSuccess.this);
            List<Class> adminList = dbAdmin1.getAllClasses();

            while (adminList.isEmpty()==false){
                Log.d("61","Testing: Class already in system");
                String curr = adminList.remove(0).getCourseCode();
                Log.d("180","186 - changing class string curr: "+curr);
                Log.d("180","187 - changing class id: "+id);
                if(curr.equals(name)){
                    //we have a class to delete
                    display.setText("Class Found");
                    display2.setText("");
                    // test this *********
                    Log.d("180","202 - changing class name: "+id);
                    dbAdmin1.changeClassName(curr,id);
                /*
                if(deleteSucess==true){
                    display.setText("Class Deleted");
                    display2.setText("");
                    return;
                }else{
                    display.setText("Class Could Not Be Deleted");
                    display2.setText("");
                    return;
                }
                */

                    //Log.d("65","User already in system");
                    return;
                }else{
                    Log.d("66","Testing: no match");
                }
            }
            display.setText("");
            display2.setText("No Class With Given Name Found");

        }
    }

    public void changeClassCode(){ //uml check
        if(ClassNameInput.getText().toString().equals("") || ClassCodeInput.getText().toString().equals("")){
            //missing criteria
            display.setText("");
            display2.setText("Please enter a class ID, and a new ID");



        }else{
            //check if the classCodeinput is in system
            String name = ClassNameInput.getText().toString();
            String id = ClassCodeInput.getText().toString();
            //id is correct

            // First check if username already in use
            DatabaseClasses dbAdmin1 = new DatabaseClasses(LogInAdminSuccess.this);
            List<Class> adminList = dbAdmin1.getAllClasses();
            while (adminList.isEmpty()==false){
                Log.d("61","Testing: Class already in system");
                String curr = adminList.remove(0).getClassName();
                Log.d("180","186 - changing class string curr: "+curr);
                Log.d("180","187 - changing class id: "+id);
                if(curr.equals(id)){
                    //we have a class to delete
                    display.setText("Class Found");
                    // test this *********
                    Log.d("180","188 - Deleting class id: "+id);
                    dbAdmin1.changeClassID(id,name);
                /*
                if(deleteSucess==true){
                    display.setText("Class Deleted");
                    display2.setText("");
                    return;
                }else{
                    display.setText("Class Could Not Be Deleted");
                    display2.setText("");
                    return;
                }
                */

                    //Log.d("65","User already in system");
                    display.setText("Class Changed");
                    display2.setText("");
                    return;
                }else{
                    Log.d("66","Testing: no match");
                }
            }
            display.setText("");
            display2.setText("No Class Found to Change");
        }
    }

    public void deleteInstructor(){ //uml check
        Log.d("305","305 - Starting Deleting instructor ");
        String name = ClassNameInput.getText().toString();
        String id = ClassCodeInput.getText().toString();

        DatabaseInstructors dbAdmin1 = new DatabaseInstructors(LogInAdminSuccess.this);
        List<Instructor> adminList = dbAdmin1.getAllInstructors();

        while (adminList.isEmpty()==false){
            Log.d("61","Testing: Class already in system");
            String curr = adminList.remove(0).getInstructorID();
            Log.d("180","186 - Deleting class string curr: "+curr);
            Log.d("180","186 - Deleting class id: "+id);
            if(curr.equals(id)){
                //we have a class to delete
                display.setText("Instructor Deleted");
                display2.setText("");

                dbAdmin1.delete(curr);
                /*
                if(deleteSucess==true){
                    display.setText("Class Deleted");
                    display2.setText("");
                    return;
                }else{
                    display.setText("Class Could Not Be Deleted");
                    display2.setText("");
                    return;
                }
                */

                //Log.d("65","User already in system");
                return;
            }else{
                Log.d("66","Testing: no match");
            }
        }
        display.setText("");
        display2.setText("No Instructor Found to Delete");

    }

    public void deleteStudent(){ //uml check
        String name = ClassNameInput.getText().toString();
        String id = ClassCodeInput.getText().toString();

        DatabaseStudent dbAdmin1 = new DatabaseStudent(LogInAdminSuccess.this);
        List<Student> adminList = dbAdmin1.getAllStudents();

        while (adminList.isEmpty()==false){
            Log.d("61","Testing: Class already in system");
            String curr = adminList.remove(0).getstudentID();
            Log.d("180","186 - Deleting class string curr: "+curr);
            Log.d("180","186 - Deleting class id: "+id);
            if(curr.equals(id)){
                //we have a class to delete
                display.setText("Student Deleted");
                display2.setText("");

                dbAdmin1.delete(curr);
                /*
                if(deleteSucess==true){
                    display.setText("Class Deleted");
                    display2.setText("");
                    return;
                }else{
                    display.setText("Class Could Not Be Deleted");
                    display2.setText("");
                    return;
                }
                */

                //Log.d("65","User already in system");
                return;
            }else{
                Log.d("66","Testing: no match");
            }
        }
        display.setText("");
        display2.setText("No Student Found to Delete");

    }

    public void deleteAdmin(){
        String name = ClassNameInput.getText().toString();
        String id = ClassCodeInput.getText().toString();

        DatabaseAdmin dbAdmin1 = new DatabaseAdmin(LogInAdminSuccess.this);
        List<Admin> adminList = dbAdmin1.getAllAdmins();

        while (adminList.isEmpty()==false){
            Log.d("61","Testing: Class already in system");
            String curr = adminList.remove(0).getUserName();
            Log.d("180","186 - Deleting class string curr: "+curr);
            Log.d("180","186 - Deleting class id: "+id);
            if(curr.equals(id)){
                //we have a class to delete
                display.setText("Admin Deleted");
                display2.setText("");

                 dbAdmin1.delete(curr);
                /*
                if(deleteSucess==true){
                    display.setText("Class Deleted");
                    display2.setText("");
                    return;
                }else{
                    display.setText("Class Could Not Be Deleted");
                    display2.setText("");
                    return;
                }
                */

                //Log.d("65","User already in system");
                return;
            }else{
                Log.d("66","Testing: no match");
            }
        }
        display.setText("");
        display2.setText("No Admin Found to Delete");
    }

    public void deleteClass(){ //uml check
        //look for class in database
        // if found delete
        // if not set display to say no such class

        Log.d("180","175. Testing: Deleting class ");
        String name = ClassNameInput.getText().toString();
        String id = ClassCodeInput.getText().toString();
        //id is correct

        // First check if username already in use
        DatabaseClasses dbAdmin1 = new DatabaseClasses(LogInAdminSuccess.this);
        List<Class> adminList = dbAdmin1.getAllClasses();
        Log.d("180","Testing: Class already in system - Deleting class");
        while (adminList.isEmpty()==false){
            Log.d("61","Testing: Class already in system");
            String curr = adminList.remove(0).getClassName();
            Log.d("180","186 - Deleting class string curr: "+curr);
            Log.d("180","186 - Deleting class id: "+id);
            if(curr.equals(id)){
                //we have a class to delete
                display.setText("Class Deleted");

                boolean deleteSucess=dbAdmin1.delete(id);
                /*
                if(deleteSucess==true){
                    display.setText("Class Deleted");
                    display2.setText("");
                    return;
                }else{
                    display.setText("Class Could Not Be Deleted");
                    display2.setText("");
                    return;
                }
                */

                //Log.d("65","User already in system");
                display.setText("Class Deleted");
                display2.setText("");
                return;
            }else{
                Log.d("66","Testing: no match");
            }
        }
        display.setText("");
        display2.setText("No Class Found to Delete");

    }

    public void seeAllAdmin(){ //uml check
        DatabaseAdmin dbAdmin = new DatabaseAdmin(LogInAdminSuccess.this);
        List<Admin> adminList = dbAdmin.getAllAdmins();
        Log.d("142","142");
        String newDisplay="";
        while (adminList.isEmpty()==false){
            String curr = adminList.remove(0).toString();
            newDisplay+=curr;
            newDisplay+="\n";
        }
        display.setText(newDisplay);
        display2.setText("Showing All Admins");
        /*
        if(adminList.size()>0){
            //display.setText(adminList.toString());
        }else{
            //display.setText("No Admins");
        }

         */


    }
    public void seeAllClasses(){ //uml check

        DatabaseClasses dbAdmin = new DatabaseClasses(LogInAdminSuccess.this);
        List<Class> adminList = dbAdmin.getAllClasses();
        String newDisplay="";
        while (adminList.isEmpty()==false){
            Class now = adminList.remove(0);
            String curr = "Course Name: "+now.getCourseCode()+", Course Code: "+ now.getClassName();
            newDisplay+=curr;
            newDisplay+="\n";
        }
        display.setText(newDisplay);
        display2.setText("Showing All Classes");
    }

    public void seeAllInstructors(){ //uml check
        DatabaseInstructors dbAdmin = new DatabaseInstructors(LogInAdminSuccess.this);
        List<Instructor> adminList = dbAdmin.getAllInstructors();
        String newDisplay="";
        while (adminList.isEmpty()==false){
            Instructor now = adminList.remove(0);
            String curr = "Instructor ID: "+now.getinstructorID()+", Pass: "+ now.getPassword();
            newDisplay+=curr;
            newDisplay+="\n";
        }
        display.setText(newDisplay);
        display2.setText("Showing All Instructors");
    }
    public void seeAllStudents(){ //uml check
        DatabaseStudent dbAdmin = new DatabaseStudent(LogInAdminSuccess.this);
        List<Student> adminList = dbAdmin.getAllStudents();
        String newDisplay="";
        while (adminList.isEmpty()==false){
            Student now = adminList.remove(0);
            String curr = "Student ID: "+now.getstudentID()+", Pass: "+ now.getPassword();
            newDisplay+=curr;
            newDisplay+="\n";
        }
        display.setText(newDisplay);
        display2.setText("Showing All Students");
    }


    public void createClass(){ // used when creating a new admin checks if username already in use
        String name = ClassNameInput.getText().toString();
        String id = ClassCodeInput.getText().toString();

        // First check if username already in use || checked and working
        DatabaseClasses dbAdmin1 = new DatabaseClasses(LogInAdminSuccess.this);
        List<Class> adminList = dbAdmin1.getAllClasses();
        Log.d("180","Testing: User already in system");
        while (adminList.isEmpty()==false){
            Log.d("61","Testing: User already in system");
            String curr = adminList.remove(0).getClassName();
            if(curr.equals(id)){
                //user already in system
                display.setText("Class already in system");
                display2.setText("");
                Log.d("65","User already in system| entered ID: "+id+"| course ID in system: "+curr);
                return;
            }else{
                Log.d("66","Testing: no match: enetered ID: "+id+"/ the courseID in system: "+curr);
            }
        }
        Log.d("194","after while loop");
        //

        ///


        //display.setText("ID: "+editTextTextID.getText()+"; Password: "+editTextPass.getText());



        //user can be put in the system
        boolean go = true;
        //go=false;

        if(go==true){
            Class aCurrent = new Class(name,id);

            DatabaseClasses dbAdmin = new DatabaseClasses(LogInAdminSuccess.this);
            Log.d("212","database object created");
            boolean success = dbAdmin.add(aCurrent);
            Log.d("214","added class success unknown");
            if(success==true){
                ClassNameInput.setText("");
                ClassCodeInput.setText("");
                display.setText("New Class Created: CourseCode: "+id+"; CourseName: "+name);
                Log.d("216","Could add class");
            }else{
                //editTextTextID.setText("");
                display.setText("Class Creation Failed");
                Log.d("219","Could not add class");

            }
        }

        display2.setText("");
    }
}