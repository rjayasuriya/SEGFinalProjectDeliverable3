package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class LogInInstructorSuccess extends AppCompatActivity {
    /*
    * Functionality to add:
    *   1) view all courses : DONE
    *   2) search for specific course using course code or course name: DONE
    *   3) if course has no instructor, the instructor can assign himself to the course: DONE
    *   4) if the current instructor is already assigned to course,
    *           5) they must be able to unassign themselves from it: DONE
    *           6) edit course days: DONE
    *           7) edit course hours: DONE
    *           8) edit course description
    *           9) edit capacity: DONE
    *
    *
    *
    * */

    Button btn0; // uml check
    Button btn4; // uml check
    Button btn5;// uml check
    Button btn6;// uml check
    Button btn7;// uml check
    Button btn8;// uml check
    Button btn9;// uml check


    TextView display;// uml check
    TextView display2;// uml check


    EditText ClassCodeInput;// uml check
    EditText ClassNameInput;// uml chec

    Bundle extras;
    String user;
    String pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) { // uml check
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_instructor_success);

        display=findViewById(R.id.display);
        display2=findViewById(R.id.display2);

        btn0=findViewById(R.id.btn0);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);


        ClassCodeInput = findViewById(R.id.ClassCodeInput);
        ClassNameInput = findViewById(R.id.ClassNameInput);

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
                //show all classes
                seeAllClasses();


            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //search  classes by name
                // if class code is filled search and return
                // if class name is filled search and return
                // on display2 set text to no class found
                if(ClassCodeInput.getText().toString().equals("") && ClassNameInput.getText().toString().equals("")){
                    display.setText("Please enter Class Code or Class Name to search");
                    display2.setText("");
                }
                if(!ClassCodeInput.getText().toString().equals("")){
                    searchClassByID(ClassCodeInput.getText().toString());
                    return;
                }
                if(!ClassNameInput.getText().toString().equals("")){
                    searchClassByName(ClassNameInput.getText().toString());
                    return;
                }



            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //un assign from class
                // if id field missing set display to say enter id
                if(ClassCodeInput.getText().toString().equals("")){
                    display.setText("No Class ID Entered");
                    display2.setText("");
                    return;
                }
                // do search for class
                // if search is succesfull > check if the current instructor is user
                // if so set instructor to ""
                unassignToClassByID(ClassCodeInput.getText().toString());



            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //assign to class
                // if id is empty display set to enter id
                if(ClassCodeInput.getText().toString().equals("")){
                    display.setText("No Class ID Entered");
                    return;
                }
                // else search class id and see if there is one, and if there is one see if no instructor
                assignToClassByID(ClassCodeInput.getText().toString());


            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set new class days
                // if id is empty display set to enter id
                //
                String inputDays = ClassNameInput.getText().toString();
                if(ClassCodeInput.getText().toString().equals("") ){
                    display.setText("No Class ID Entered,");
                    display2.setText("");
                    return;
                }
                // seperate string by comma
                String [] strArray = inputDays.split(",");
                for(String day:strArray){
                    Log.d("testing lower case days","String curr: "+day);
                    if(day.equals("monday") || day.equals("tuesday") || day.equals("wednesday") || day.equals("thursday") || day.equals("friday") || day.equals("saturday") || day.equals("sunday")){
                        Log.d("testing lower case days","testing to make sure course days are ok");
                    }else{
                        Log.d("testing lower case days","FAIL:testing to make sure course days are ok");
                        display.setText("Please enter days in lower case separated by a comma");
                        display2.setText("");
                        return;
                    }

                }


                setNewCourseDays(ClassCodeInput.getText().toString(), ClassNameInput.getText().toString());


            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set new course hours
                if(ClassCodeInput.getText().toString().equals("") ){
                    display.setText("No Class ID Entered");
                    display2.setText("");
                    return;
                }
                String inputDays = ClassNameInput.getText().toString();
                Log.d("testing setting class hours","Trying to input: "+inputDays);
                String [] strArray = inputDays.split("-");
                if(strArray.length!=2){
                    display.setText("Please enter hours in 24 hour format separated by a -; ex: 09:00-14:00");
                    display2.setText("");
                    Log.d("testing setting class hours","There is no ending time");
                    return;
                }
                if(strArray[0].equals(strArray[1])){
                    display.setText("Please enter hours in 24 hour format separated by a -; ex: 09:00-14:00");
                    display2.setText("");
                    Log.d("testing setting class hours","There is no ending time");
                    return;
                }
                for(String hour: strArray){
                    Log.d("testing setting class hours-splitted by -","Trying to String hour: "+hour);

                    String [] strArray2 = hour.split(":");
                    Log.d("testing setting class hours","There is no hour or minutes: length: "+strArray2.length);
                    if(strArray2.length!=2){
                        display.setText("Please enter hours in 24 hour format separated by a -; ex: 09:00-14:00");
                        display2.setText("");
                        Log.d("testing setting class hours","There is no ending time");
                        return;
                    }
                    for(String innerM: strArray2){
                        char [] innerB=innerM.toCharArray();
                        for(char inner:innerB){
                            Log.d("testing setting class hours-splitted by :","Trying to String inner: "+inner);
                            if(inner=='0'||inner=='1' ||inner=='2' ||inner=='3' ||inner=='4' ||inner=='5' ||inner=='6' ||inner=='7' ||inner=='8' ||inner=='9' ){

                            }else{
                                display.setText("Please enter hours in 24 hour format separated by a -; ex: 09:00-14:00");
                                display2.setText("");
                                return;
                            }
                        }

                    }
                }

                setNewCourseHours(ClassCodeInput.getText().toString(), ClassNameInput.getText().toString());

            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set new capacity
                if(ClassCodeInput.getText().toString().equals("") ){
                    display.setText("No Class ID Entered");
                    display2.setText("");
                    return;
                }
                setNewCapacity(ClassCodeInput.getText().toString(), ClassNameInput.getText().toString());

            }
        });


    }
    public void seeAllClasses(){ // uml check

        DatabaseClasses dbAdmin = new DatabaseClasses(LogInInstructorSuccess.this);
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
    public void searchClassByID(String idIn){ // uml check
        //look for class in database
        // if found delete
        // if not set display to say no such class

        Log.d("180","175. Testing: Deleting class ");
        String name = ClassNameInput.getText().toString();
        String id = idIn;
        //id is correct

        // First check if username already in use
        DatabaseClasses dbAdmin1 = new DatabaseClasses(LogInInstructorSuccess.this);
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
    public void searchClassByName(String idIn){ // uml check
        //look for class in database
        // if found delete
        // if not set display to say no such class

        Log.d("180","175. Testing: Deleting class ");
        String name = ClassNameInput.getText().toString();
        String id = idIn;
        //id is correct

        // First check if username already in use
        DatabaseClasses dbAdmin1 = new DatabaseClasses(LogInInstructorSuccess.this);
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
    public void setNewCapacity(String idIn, String newCapacity){ // uml check
        ///*
        try{
            int num = Integer.valueOf(newCapacity);
            if(num==0) {
                return;
            }
            if(num<0){
                display.setText("Enter valid number for capacity");
                display2.setText("");
                return;
            }
        }catch(Exception err){
            display.setText("Enter valid number for capacity");
            display2.setText("");
            return;
        }
        //*/

        String id = idIn;
        DatabaseClasses dbAdmin1 = new DatabaseClasses(LogInInstructorSuccess.this);
        List<Class> adminList = dbAdmin1.getAllClasses();
        while (adminList.isEmpty()==false){

            Class currClass = adminList.remove(0);
            String curr = currClass.getClassName();

            if(curr.equals(id)){
                //we have a class to delete
                String teacher=currClass.getInstructor();
                if(teacher.equals(user)){
                    // now we can set new class hours

                    //update in database
                    dbAdmin1.changeCourseCapacity(curr,newCapacity);
                    display.setText("Course ID: "+currClass.getClassName()+", Course Days: "+currClass.getCourseDays()+"\n"+"Course Hours: "+currClass.getCourseHours()+", Capacity: "+newCapacity+"\n"+"Instructor: "+currClass.getInstructor());
                    display2.setText("Class Found: Course Code: "+curr);
                    return;
                }else{
                    // cannot assign
                    display.setText("Class Found: Course Code: "+curr+", Instructor: "+teacher);
                    display2.setText("Cannot perform operation since you are not its instructor");
                    return;
                }

            }
        }
        display2.setText("");
        display.setText("No Class Found");
    }


    public void setNewCourseHours(String idIn, String newDays){//uml check
        String id = idIn;
        DatabaseClasses dbAdmin1 = new DatabaseClasses(LogInInstructorSuccess.this);
        List<Class> adminList = dbAdmin1.getAllClasses();
        while (adminList.isEmpty()==false){

            Class currClass = adminList.remove(0);
            String curr = currClass.getClassName();

            if(curr.equals(id)){
                //we have a class to delete
                String teacher=currClass.getInstructor();
                if(teacher.equals(user)){
                    // now we can set new class hours

                    //update in database
                    dbAdmin1.changeCourseHours(curr,newDays);
                    display.setText("Course ID: "+currClass.getClassName()+", Course Days: "+currClass.getCourseDays()+"\n"+"Course Hours: "+newDays+", Capacity: "+currClass.getCapacity()+"\n"+"Instructor: "+currClass.getInstructor());
                    display2.setText("Class Found: Course Code: "+curr);
                    return;
                }else{
                    // cannot assign
                    display.setText("Class Found: Course Code: "+curr+", Instructor: "+teacher);
                    display2.setText("Cannot perform operation since you are not its instructor");
                    return;
                }

            }
        }
        display2.setText("");
        display.setText("No Class Found");
    }

    public void setNewCourseDays(String idIn, String newDays){ //uml check
        String id = idIn;
        DatabaseClasses dbAdmin1 = new DatabaseClasses(LogInInstructorSuccess.this);
        List<Class> adminList = dbAdmin1.getAllClasses();
        while (adminList.isEmpty()==false){

            Class currClass = adminList.remove(0);
            String curr = currClass.getClassName();

            if(curr.equals(id)){
                //we have a class to delete
                String teacher=currClass.getInstructor();
                if(teacher.equals(user)){
                    // now we can set new class days

                    //update in database
                    dbAdmin1.changeCourseDays(curr,newDays);
                    display.setText("Course ID: "+currClass.getClassName()+", Course Days: "+newDays+"\n"+"Course Hours: "+currClass.getCourseHours()+", Capacity: "+currClass.getCapacity()+"\n"+"Instructor: "+currClass.getInstructor());
                    display2.setText("Class Found: Course Code: "+curr);
                    return;
                }else{
                    // cannot assign
                    display.setText("Class Found: Course Code: "+curr+", Instructor: "+teacher);
                    display2.setText("Cannot perform operation since you are not its instructor");
                    return;
                }

            }
        }
        display2.setText("");
        display.setText("No Class Found");
    }


    public void unassignToClassByID(String idIn){ //uml check
        String id = idIn;
        DatabaseClasses dbAdmin1 = new DatabaseClasses(LogInInstructorSuccess.this);
        List<Class> adminList = dbAdmin1.getAllClasses();
        while (adminList.isEmpty()==false){

            Class currClass = adminList.remove(0);
            String curr = currClass.getClassName();

            if(curr.equals(id)){
                //we have a class to delete
                String teacher=currClass.getInstructor();
                if(teacher.equals(user)){
                    Log.d("425","match for teacher");
                    // now we can unassign assign
                    teacher="";
                    //update in database
                    dbAdmin1.changeInstructor(curr,"");
                    dbAdmin1.changeCourseDays(curr,"");
                    dbAdmin1.changeCourseCapacity(curr,"");
                    dbAdmin1.changeCourseHours(curr,"");
                    display.setText("Class Found: Course Code: "+curr+", Instructor: "+teacher);
                    display2.setText("You are unassigned from class");
                    return;
                }else{
                    Log.d("425","no match for teacher");
                    // cannot assign
                    display.setText("Class Found: Course Code: "+curr+", Instructor: "+teacher);
                    display2.setText("Cannot unassign from class since you are not its instructor");
                    return;
                }

            }
        }
        display2.setText("");
        display.setText("No Class Found");
    }

    public void assignToClassByID(String idIn){ //uml check
        String id = idIn;
        //id is correct

        // First check if username already in use
        DatabaseClasses dbAdmin1 = new DatabaseClasses(LogInInstructorSuccess.this);
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
                    // now we can assign
                    teacher="No Instructor";
                    //update in database
                    dbAdmin1.changeInstructor(curr,user);
                    display.setText("Class Found: Course Code: "+curr+", Instructor: "+teacher);
                    display2.setText("You are assigned to class");
                    return;
                }else{
                    // cannot assign
                    display.setText("Class Found: Course Code: "+curr+", Instructor: "+teacher);
                    display2.setText("Instructor already assigned, cannot assign to this class");
                    return;
                }

            }
        }
        display2.setText("");
        display.setText("No Class Found");

    }

}