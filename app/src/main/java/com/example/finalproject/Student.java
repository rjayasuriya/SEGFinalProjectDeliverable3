package com.example.finalproject;

import android.util.Log;

import java.util.ArrayList;

public class Student {
    /*
- firstName: String
- lastName: String
- studentID: int
- enrolledCourses: arrayList[Class]
- creditedCourses: arrayList[Class]
*/
    public String password; // uml check
    public String studentID; // uml check
    public ArrayList<String> enrolledCourses; // uml check

    public Student(String user, String pass){
        studentID=user;
        password=pass;
    }
    public String getPassword(){
        return password;
    } // uml check
    public String getstudentID(){
        return studentID;
    } // uml check
    public void setEnrolledCourses(String in){
        Log.d("Running: setEnrolledCourses","Input of current list of courses: "+in);
        String [] curArray = in.split(",");
        ArrayList<String> inList = new ArrayList<>();
        for(String currStud: curArray){
            inList.add(currStud);
            Log.d("Running: setEnrolledCourses","Adding to list of courses: "+currStud);
        }
        enrolledCourses=inList;
    }
    public ArrayList<String> getEnrolledCourses(){
        return enrolledCourses;
    }
}
