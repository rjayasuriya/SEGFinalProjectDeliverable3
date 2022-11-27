package com.example.finalproject;

import java.util.ArrayList;

public class Class {
    /*
+ className: String
+ courseCode: int
+ courseDays: String
+ courseHours: String
+ capacity: int
+ studentsEnrolled: arrayList[Student]
*/
    public String className; // uml check
    public String courseCode; // uml check
    public String courseDays; // uml check
    public String courseHours; // uml check
    public int capacity; // uml check
    //public ArrayList<Student> studentsEnrolled; // uml check
    public ArrayList<String> studentsEnrolled; // uml check
    public String teachingInstructor; // uml check

    public Class(String className, String courseCode){
        this.className=className;
        this.courseCode=courseCode;
        courseDays="";
        courseHours="";
        capacity=0;
        studentsEnrolled=new ArrayList<>();
        teachingInstructor=null;
    }
    public String getClassName(){
        return className;
    } //uml check

    public String getCourseCode(){
        return courseCode;
    } //uml check

    public String getInstructor(){
        return teachingInstructor;
    }//uml check

    public String getCourseDays(){
        return courseDays;
    }//uml check
    public String getCourseHours(){
        return courseHours;
    }//uml check

    public ArrayList<String> getStudentsEnrolled(){return studentsEnrolled;}

    public int getCapacity(){
        return capacity;
    } //uml check

    public void setCourseDays(String in){
        courseDays=in;
    }
    public void setCourseHours(String in){
        courseHours=in;
    }
    public void setCapacity(String in){
        capacity=Integer.valueOf(in);
    }
    public void setInstructor(String in){
        teachingInstructor = in;
    }

    public void setStudentsEnrolled(ArrayList<String> in){
        studentsEnrolled=in;
    }

    /*
    public String getCourseDays(){
        return courseDays;
    }
    public void setClassName(String in){
        className=in;
    }
    public void setCourseCode(String in){
        courseCode = in;
    }

    public void setCourseDays(String in){
        courseDays=in;
    }
    public String getCourseHours(){
        return  courseHours;
    }
    public void setCourseHours(String in){
        courseHours=in;
    }
    public int getCapacity(){
        return capacity;
    }

    public ArrayList<Student> getStudentsEnrolled(){
        return studentsEnrolled;
    }
    public void addStudentToClass(Student in){
        studentsEnrolled.add(in);
    }
    public Instructor getTeachingInstructor(){
        return teachingInstructor;
    }
    public void setTeachingInstructor(Instructor in){

        teachingInstructor=in;
    }
    */


}

