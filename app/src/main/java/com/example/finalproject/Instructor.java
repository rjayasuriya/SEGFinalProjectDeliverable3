package com.example.finalproject;

import java.util.ArrayList;

public class Instructor {
    /*
    * - firstName: String
- lastName: String
- instructorID: int
- teachingCourses: arrayList[Class]
*
* Functionality to add:
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
*
*
*
*
*
 * */
    public String password; // uml check
    public String instructorID; // uml check
    public ArrayList<Class> teachingCourses; // uml check

    Instructor(String user, String pass){
        password=pass;
        instructorID = user;
    }

    public String getinstructorID(){
        return instructorID;
    } // uml check
    public String getPassword(){
        return password;
    } // uml check
    public String getInstructorID(){
        return instructorID;
    } // uml check




}
