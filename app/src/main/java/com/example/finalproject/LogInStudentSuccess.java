package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LogInStudentSuccess extends AppCompatActivity {

    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;

    EditText ClassCodeInput;// uml check
    EditText ClassNameInput;// uml check
    EditText ClassDayInput;// uml check

    Bundle extras;
    String user;
    String pass;

    TextView display;
    TextView display2;// uml check
    TextView ClassDay;// uml check
    TextView ClassName;// uml check
    TextView ClassCodeOrUsernameOrID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_student_success);

        display=findViewById(R.id.display);
        display2=findViewById(R.id.display2);

        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);

        //edit texts
        ClassCodeInput = findViewById(R.id.ClassCodeInput);
        ClassNameInput = findViewById(R.id.ClassNameInput);
        ClassDayInput = findViewById(R.id.ClassDayInput);

        //textview
        ClassDay = findViewById(R.id.ClassDay);
        ClassCodeOrUsernameOrID = findViewById(R.id.ClassCodeOrUsernameOrID);
        ClassName = findViewById(R.id.ClassName);

        extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("user");
            pass = extras.getString("pass");
            //The key argument here must match that used in the other activity
            display.setText("Signed in as Username: "+user+", Password: "+pass);
        }

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show all list of all courses
                seeAllClasses();

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //search by course name
                Log.d("1","Testing search by course name ");
                if(ClassCodeInput.getText().toString().equals("") && ClassNameInput.getText().toString().equals("")){
                    display.setText("Please enter Class Code or Class Name to search");
                    display2.setText("");
                    return;
                }

                /*
                if(!ClassCodeInput.getText().toString().equals("")){
                    searchClassByID(ClassCodeInput.getText().toString());
                    return;
                }
                */

                if(!ClassNameInput.getText().toString().equals("")){
                    searchClassByName(ClassNameInput.getText().toString());
                    return;
                }



            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //search by course code
                Log.d("1","Testing search by course code ");
                if(ClassCodeInput.getText().toString().equals("") && ClassNameInput.getText().toString().equals("")){
                    display.setText("Please enter Class Code or Class Name to search");
                    display2.setText("");
                    return;
                }
                if(!ClassCodeInput.getText().toString().equals("")){
                    searchClassByID(ClassCodeInput.getText().toString());
                    return;
                }

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //search by course day
                String classDay=ClassDayInput.getText().toString();


                if(classDay.equals("")|| classDay.equals("monday") || classDay.equals("tuesday") || classDay.equals("wednesday")|| classDay.equals("thursday")|| classDay.equals("friday")|| classDay.equals("saturday")|| classDay.equals("sunday")){

                }else{
                    display.setText("Please enter Class day in lower case and full name ex: monday");
                    display2.setText("");
                    return;
                }
                display.setText("");
                display2.setText("");
                searchClassByDay(classDay);



            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //enroll
                Log.d("Testing enroll","Testing enroll");
                if(ClassCodeInput.getText().toString().equals("")){
                    display.setText("Please enter Class Code to Enroll in course");
                    display2.setText("");
                    return;
                }
                // we need to update both the class data base and the student database
                //update class database
                setNewStudentList(ClassCodeInput.getText().toString()); // this will update in database class
                updateCurrStudentEnrolledCourses(ClassCodeInput.getText().toString()); // this will update in student database



            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show enrolled courses
                // we need to pull the string of enrolle courses
                // split it into a string array with split(",")
                //then run get all classes each time it matches add criteria into a string output

                Log.d("Testing view enrolled courses","checking button");
                viewEnrolledCourses();
                // method tested


            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show enrolled courses
                // we need to pull the string of enrolled courses
                // split it into a string array with split(",")
                //then run get all classes each time it matches add criteria into a string output

                Log.d("Testing drop course","checking button");
                if(ClassCodeInput.getText().toString().equals("")){
                    display.setText("Please enter Class Code to drop course");
                    display2.setText("");
                    return;
                }else{
                    display.setText("");
                    display2.setText("");
                }
                //first check if this course is in the enrolled list of courses
                // if course found, then for the student database pull the current enrolled list and adjust it
                // for the classes data base pull the list of students, then adjust it
                viewEnrolledCourses2(ClassCodeInput.getText().toString());



            }
        });
    }
    public void seeAllClasses(){ //uml check

        DatabaseClasses dbAdmin = new DatabaseClasses(LogInStudentSuccess.this);
        List<Class> adminList = dbAdmin.getAllClasses();
        String newDisplay="";
        while (adminList.isEmpty()==false){
            Class now = adminList.remove(0);
            String curr = "C_Name: "+now.getCourseCode()+", C_Code: "+ now.getClassName()+", Capacity: "+now.getCapacity();
            newDisplay+=curr;
            newDisplay+="\n";
        }
        display.setText(newDisplay);
        display2.setText("Showing All Classes");
    }

    public void searchClassByDay(String idIn){

        String id = idIn;
        DatabaseClasses dbAdmin1 = new DatabaseClasses(LogInStudentSuccess.this);
        List<Class> adminList = dbAdmin1.getAllClasses();
        String output="";
        while (adminList.isEmpty()==false){
            Log.d("61","Testing: Class already in system");
            Class currClass = adminList.remove(0);
            String currArray [] = currClass.getCourseDays().split(",");

            for(String cday:currArray){
                Log.d("searching by class day","Curr day: "+cday+", checking if matches with input: "+idIn);
                String curr=cday;
                if(curr.equals(id)){
                    //we have a class to delete

                    output+="Course Name: "+currClass.getCourseCode()+", Course Days: "+currClass.getCourseDays()+", Course Hours: "+currClass.getCourseHours()+", Capacity: "+currClass.getCapacity()+"Instructor: "+currClass.getInstructor()+"\n";
                    //display2.setText("Course Name: "+currClass.getCourseCode());

                }else{
                    Log.d("66","Testing: no match");
                }
                Log.d("searching by class day","Curr output: "+output);
            }
            display.setText(output);

        }
    }

    public void searchClassByName(String idIn){ // uml check
        //look for class in database
        // if found delete
        // if not set display to say no such class

        Log.d("180","175. Testing: Deleting class ");
        String name = ClassNameInput.getText().toString();
        String id = idIn;
        //id is correct

        // First check if username already in use
        DatabaseClasses dbAdmin1 = new DatabaseClasses(LogInStudentSuccess.this);
        List<Class> adminList = dbAdmin1.getAllClasses();
        Log.d("180","Testing: Class already in system - Deleting class");
        while (adminList.isEmpty()==false){
            Log.d("61","Testing: Class already in system");
            Class currClass = adminList.remove(0);
            String curr = currClass.getCourseCode();
            Log.d("180","186 - Deleting class string curr: "+curr);
            Log.d("180","186 - Deleting class id: "+id);
            if(curr.equals(id)){
                //we have a class to delete
                String teacher=currClass.getInstructor();
                if(teacher.equals("")){
                    teacher="No Instructor";
                }
                display.setText("Course Name: "+currClass.getCourseCode()+", Course Days: "+currClass.getCourseDays()+"\n"+"Course Hours: "+currClass.getCourseHours()+", Capacity: "+currClass.getCapacity()+"\n"+"Instructor: "+currClass.getInstructor());
                display2.setText("Course Name: "+currClass.getCourseCode());
                return;
            }else{
                Log.d("66","Testing: no match");
            }
        }
        display2.setText("");
        display.setText("No Class Found");

    }

    public void setNewStudentList(String idIn){
        String id = idIn;
        DatabaseClasses dbAdmin1 = new DatabaseClasses(LogInStudentSuccess.this);
        List<Class> adminList = dbAdmin1.getAllClasses2();
        while (adminList.isEmpty()==false) {

            Class currClass = adminList.remove(0);
            String curr = currClass.getClassName();
            if(curr.equals(id)){
                ArrayList<String> currStudentList=currClass.getStudentsEnrolled();
                String currentListString ="";
                for(String a:currStudentList){
                    Log.d("66","Testing: Enrolling: Testing current list-curr student: "+a+", curr user: "+user);
                    if(a.equals(user)){
                        display.setText("You Are Already Enrolled In This Course");
                        display2.setText("");
                        return;
                    }
                    currentListString+=a;
                    currentListString+=",";
                }
                currentListString+=user;
                Log.d("66","Testing: Enrolling: id: "+curr+", student list: "+currentListString);
                dbAdmin1.updateCourseStudentList(curr,currentListString);
                display.setText("You are Enrolled In Course");
                display2.setText("");
            }
        }
    }
    public void viewEnrolledCourses(){

        DatabaseStudent dbAdmin1 = new DatabaseStudent(LogInStudentSuccess.this);
        List<Student> adminList = dbAdmin1.getAllStudents();
        while (adminList.isEmpty()==false){
            Student currClass = adminList.remove(0);
            String curr = currClass.getstudentID();
            if(curr.equals(user)){
                ArrayList<String> currEnrolledList = currClass.getEnrolledCourses();
                String newList="";
                try{
                    if(currEnrolledList!=null){
                        for(String currCourse:currEnrolledList ){
                            //need to get details of the classes using its id
                            // pull all classes
                            DatabaseClasses dbAdmin2 = new DatabaseClasses(LogInStudentSuccess.this);
                            List<Class> adminList2 = dbAdmin2.getAllClasses();
                            while (adminList2.isEmpty()==false){
                                Class currClass2 = adminList2.remove(0);
                                String curr2 = currClass2.getClassName();
                                if(curr2.equals(currCourse)){//if the class we have enrolled in matches with total class list
                                    newList+="C_Code: "+curr2+", C_Name: "+currClass2.getCourseCode()+", Capacity: "+currClass2.getCapacity()+", Instructor: "+currClass2.getInstructor()+"\n";
                                }
                            }
                        }
                    }
                }catch(Exception err){

                }
                display.setText(newList);
                display2.setText("");
                return;
            }

        }

    }

    public void viewEnrolledCourses2(String classID){
        DatabaseStudent dbAdmin1 = new DatabaseStudent(LogInStudentSuccess.this);
        List<Student> adminList = dbAdmin1.getAllStudents();
        while (adminList.isEmpty()==false){
            Student currClass = adminList.remove(0);
            String curr = currClass.getstudentID();
            if(curr.equals(user)){
                // the student matches with current
                ArrayList<String> currEnrolledList = currClass.getEnrolledCourses();


                try{
                    if(currEnrolledList!=null){
                        for(String currCourse:currEnrolledList ){
                            //we need to check if this course id is same as the one indicated to drop
                            if(currCourse.equals(classID)){
                                String newList="";
                                // if so we need to update the databaseStudent with new class list
                                currEnrolledList.remove(currCourse);
                                for(String currCourse2:currEnrolledList ){
                                    newList+=currCourse2+",";
                                    //now we have string input ready to upload
                                }

                                // *************** uncomment below
                                dbAdmin1.updateEnrolledCourses(user,newList);


                                // if so we we need to update databaseClasses with student list
                                DatabaseClasses dbAdmin2 = new DatabaseClasses(LogInStudentSuccess.this);
                                List<Class> adminList2 = dbAdmin2.getAllClasses2();
                                while (adminList2.isEmpty()==false) {
                                    Log.d("Testing Drop course 1", "Testing: updateCurrStudentEnrolledCourses");
                                    Class classCurrent = adminList2.remove(0);
                                    if(classCurrent.getClassName().equals(classID)){
                                        Log.d("Testing Drop course 2","current in list: "+classCurrent.getClassName()+", course to drop: "+classID);
                                        //we have a class match
                                        ArrayList<String> listOfStudents = classCurrent.getStudentsEnrolled();
                                        String listofNewStudents="";
                                        for(String currStudentInList:listOfStudents){
                                            //find the user match

                                            if(currStudentInList.equals(user)){
                                                //user match
                                                //listOfStudents.remove(user);
                                            }else{
                                                listofNewStudents+=currStudentInList+",";
                                            }
                                            Log.d("Testing Drop course 3","current in list: "+currStudentInList+",listofNewStudents: "+listofNewStudents);
                                        }
                                        Log.d("Testing Drop course 4","listofNewStudents: "+listofNewStudents);
                                        //now we should update databaseClasses

                                        // *************** uncomment below
                                        dbAdmin2.updateCourseStudentList(classID,listofNewStudents);
                                        break;
                                    }
                                }
                                display.setText("Course Dropped");
                                display2.setText("");
                                return;

                            }


                        }
                        display.setText("Cannot Drop Class, Not Enrolled in Class");
                        display2.setText("");
                        return;
                    }else{
                        display.setText("Cannot Drop Class, No Enrolled Classes");
                        display2.setText("");
                    }
                }catch(Exception err){

                }
            }
        }

    }


    public void updateCurrStudentEnrolledCourses(String idIn){
        String id = idIn; // this is the course id
        DatabaseStudent dbAdmin1 = new DatabaseStudent(LogInStudentSuccess.this);
        List<Student> adminList = dbAdmin1.getAllStudents();
        while (adminList.isEmpty()==false){
            Log.d("285","Testing: updateCurrStudentEnrolledCourses");
            Student currClass = adminList.remove(0);
            Log.d("286","Testing: updateCurrStudentEnrolledCourses");
            String curr = currClass.getstudentID();
            Log.d("287","Testing: updateCurrStudentEnrolledCourses: curr: "+curr+", user: "+user);
            if(curr.equals(user)){
                //student found
                //we need to check the current enrolled classes for the student this will be a string we need to add the course id to it
                // also check if if the id already exists
                Log.d("288","Testing: updateCurrStudentEnrolledCourses");
                ArrayList<String> currEnrolledList = currClass.getEnrolledCourses();
                Log.d("289","Testing: updateCurrStudentEnrolledCourses");
                String newList="";
                try{
                    if(currEnrolledList!=null){
                        for(String currCourse:currEnrolledList ){
                            Log.d("290","Testing: updateCurrStudentEnrolledCourses");

                            if(currCourse.equals(id)){
                                Log.d("291","Testing: updateCurrStudentEnrolledCourses");
                                display.setText("Already Enrolled In Course");
                                display2.setText("");
                                return;
                            }
                            Log.d("292","Testing: updateCurrStudentEnrolledCourses");
                            newList+=currCourse+",";
                            Log.d("293","Testing: updateCurrStudentEnrolledCourses");
                        }
                        Log.d("294","Testing: updateCurrStudentEnrolledCourses");
                        newList+=id;
                        Log.d("295","Testing: updateCurrStudentEnrolledCourses");
                        dbAdmin1.updateEnrolledCourses(user,newList);
                    }else{
                        Log.d("290-ELSE","Testing: updateCurrStudentEnrolledCourses-FAIL");
                        newList+=id;
                        dbAdmin1.updateEnrolledCourses(user,newList);
                    }
                    return;
                }catch (Exception err){
                    Log.d("299-ERROR","Testing: updateCurrStudentEnrolledCourses-FAIL");
                }


            }

        }
    }


    public void searchClassByID(String idIn){ // uml check
        //look for class in database
        // if found delete
        // if not set display to say no such class

        Log.d("180","175. Testing: Deleting class ");
        String name = ClassNameInput.getText().toString();
        String id = idIn;
        //id is correct

        // First check if username already in use
        DatabaseClasses dbAdmin1 = new DatabaseClasses(LogInStudentSuccess.this);
        List<Class> adminList = dbAdmin1.getAllClasses();
        Log.d("180","Testing: Class already in system - Deleting class");
        while (adminList.isEmpty()==false){
            Log.d("61","Testing: Class already in system");
            Class currClass = adminList.remove(0);
            String curr = currClass.getClassName();
            Log.d("180","186 - Deleting class string curr: "+curr);
            Log.d("180","186 - Deleting class id: "+id);
            if(curr.equals(id)){
                //we have a class to delete
                String teacher=currClass.getInstructor();
                if(teacher.equals("")){
                    teacher="No Instructor";
                }
                display.setText("Course ID: "+currClass.getClassName()+", Course Days: "+currClass.getCourseDays()+"\n"+"Course Hours: "+currClass.getCourseHours()+", Capacity: "+currClass.getCapacity()+"\n"+"Instructor: "+currClass.getInstructor());
                display2.setText("Class Found: Course Code: "+curr);
                return;
            }else{
                Log.d("66","Testing: no match");
            }
        }
        display2.setText("");
        display.setText("No Class Found");

    }
}