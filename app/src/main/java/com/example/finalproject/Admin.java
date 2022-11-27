package com.example.finalproject;

public class Admin {
    public String userName; // uml check
    public String passWord;// uml check


    public Admin(String userName, String passWord){
        this.userName=userName;
        this.passWord=passWord;

    }

    private Class createClass(String className, String courseCode){
        Class newClass = new Class(className,courseCode);
        return newClass;
    }

    public String getUserName(){
        return userName;
    } // uml check
    public String getPassWord(){
        return passWord;
    } // uml check
    public String toString(){ // uml check
        String out = "Username: "+userName+", Password: "+passWord;
        return out;
    }


    /*
    private void deleteClass(Class classToDelete){
        classToDelete=null;
    }
    private void deleteStudent(Student studentToDelete){
        studentToDelete=null;
    }
    private void deleteInstructor(Instructor instructorToDelete){
        instructorToDelete=null;
    }
    */





}
