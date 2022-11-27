package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseClasses extends SQLiteOpenHelper {

    public static final String CLASSES_TABLE = "CLASSES_TABLE"; // uml check
    public static final String COURSECODE = "COURSECODE";// uml check
    public static final String COURSENAME = "COURSENAME";// uml check
    public static final String COURSEDAYS = "COURSEDAYS";// uml check
    public static final String COURSEHOURS = "COURSEHOURS";// uml check
    public static final String CAPACITY = "CAPACITY";// uml check
    public static final String INSTRUCTOR = "INSTRUCTOR";// uml check
    public static final String ENROLLEDSTUDENTS = "ENROLLEDSTUDENTS";// uml check

    public DatabaseClasses(@Nullable Context context) {
        super(context, "Database_Classes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {// uml check
        //method creates databse
        String createTableStatement = "CREATE TABLE "+CLASSES_TABLE+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+COURSECODE+" TEXT, "+COURSENAME+" TEXT, "+COURSEDAYS+" TEXT, "+COURSEHOURS+" TEXT, "+CAPACITY+" INTEGER, "+INSTRUCTOR+" TEXT, "+ENROLLEDSTUDENTS+" TEXT )";
        sqLiteDatabase.execSQL(createTableStatement);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {// uml check
        //method is there in case of databse upgrade i.e. more/less columns

    }
    public void changeClassName(String currentClassName,String newClassName){// uml check
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COURSENAME,newClassName);
        db.update(CLASSES_TABLE,cv,COURSENAME+" = '"+currentClassName+"'", null);
    }
    public void changeInstructor(String currentClassID, String instructorNew ){ //uml check
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(INSTRUCTOR,instructorNew);
        db.update(CLASSES_TABLE,cv,COURSECODE+" = '"+currentClassID+"'", null);
    }
    public void changeCourseDays(String currentClassID, String courseDaysNew){ //uml checkc
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COURSEDAYS,courseDaysNew);
        db.update(CLASSES_TABLE,cv,COURSECODE+" = '"+currentClassID+"'", null);
    }
    public void changeCourseHours(String currentClassID, String courseHoursNew){ //uml check
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COURSEHOURS,courseHoursNew);
        db.update(CLASSES_TABLE,cv,COURSECODE+" = '"+currentClassID+"'", null);
    }
    public void changeCourseCapacity(String currentClassID, String courseCapacityNew){ //uml check
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CAPACITY,courseCapacityNew);
        db.update(CLASSES_TABLE,cv,COURSECODE+" = '"+currentClassID+"'", null);

    }
    public void updateCourseStudentList(String currentClassID, String newList){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //find the course and pull the student list
        cv.put(ENROLLEDSTUDENTS,newList);
        db.update(CLASSES_TABLE,cv,COURSECODE+" = '"+currentClassID+"'", null);


    }

    public void changeClassID(String currentClassID,String newClassID){ // uml check
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COURSECODE,newClassID);
        Log.d("180","189 - changing class id: in database Class");
        db.update(CLASSES_TABLE,cv,COURSECODE+" = '"+currentClassID+"'", null);
        Log.d("180","190 - changing class id: in database Class");


    }

    public boolean delete(String classID){ // uml check
        Log.d("50","44. starting Delete class code in databseclass");
        SQLiteDatabase db =  this.getWritableDatabase();
        Log.d("50","46. Delete class code in databseclass");
        String deleteFromTableStatement = "DELETE FROM "+CLASSES_TABLE+" WHERE "+COURSECODE+"='"+classID+"'";
        Log.d("50","47. querry statement: "+deleteFromTableStatement+"| charat 0: "+classID.charAt(0));
        Cursor cursor = db.rawQuery(deleteFromTableStatement,null);
        Log.d("50","48. Delete class code in databseclass");
        if(cursor.moveToFirst()){
            //delete sucess

            Log.d("50","Delete class code success");
            return true;
        }else{
            Log.d("50","Delete class code success");
            return false;
        }
    }


    public boolean add(Class classNew){ // uml check



        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COURSECODE,classNew.getCourseCode());
        Log.d("50","Added COURSECODE");
        cv.put(COURSENAME,classNew.getClassName());
        Log.d("50","Added COURSENAME");
        cv.put(COURSEDAYS,"");
        Log.d("50","Added COURSEDAYS");
        cv.put(COURSEHOURS,"");
        Log.d("50","Added COURSEHOURS");
        cv.put(CAPACITY,0);
        Log.d("50","Added CAPACITY");
        cv.put(INSTRUCTOR,"");
        cv.put(ENROLLEDSTUDENTS,"");
        Log.d("55","Added all parameters for class object");


        long admin1 = db.insert(CLASSES_TABLE,  null, cv);
        Log.d("59","Added all parameters to table");
        //db.close();
        if(admin1>0){
            Log.d("44","Sign Up Success");
            return true;
        }else{
            Log.d("47","Sign Up Failed");
            return false;
        }

    }
    public List<Class> getAllClasses(){ // uml check
        List<Class> out = new ArrayList<>();
        //create sql query string
        String sqlQuery = "SELECT * FROM "+CLASSES_TABLE;


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sqlQuery,null);
        Log.d("65","testing cursor");
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            Log.d("65","there are results");
            //int id = cursor.getInt(0);
            //Log.d("69",Integer.toString(id));
            String courseCode = cursor.getString(1);
            //Log.d("70",userN);
            String courseName = cursor.getString(2);
            String courseDays = cursor.getString(3);
            String courseHours = cursor.getString(4);
            // check below for integer
            String capacity = cursor.getString(5);
            //
            String instructor = cursor.getString(6);
            //String enrolledStudents = cursor.getString(7);



            //Log.d("71",passW);
            cursor.moveToNext();
            Class curr = new Class(courseCode,courseName);

            curr.setCourseDays(courseDays);
            curr.setCourseHours(courseHours);
            curr.setCapacity(capacity);
            curr.setInstructor(instructor);





            out.add(curr);
        }
        Log.d("72","done");



        //String username = cursor.getString(1);

        /*
        Log.d("58","testing cursor");

        String pass = cursor.getString(2);
        Log.d("64",username);
        Log.d("65",pass);
        */

        /*
        if(cursor.moveToFirst()){
            //there are results

            Log.d("62","there are results");




            do{
                //get username
                String username = cursor.getString(1);
                String pass = cursor.getString(2);
                Log.d("64",username);
                Log.d("65",pass);

                Admin curr = new Admin(username,pass);
                //out.add(curr);
            }while(cursor.moveToFirst());


        }else{
            //no results

        }
        */

        return out;
    }
    public List<Class> getAllClasses2(){ // uml check
        List<Class> out = new ArrayList<>();
        //create sql query string
        String sqlQuery = "SELECT * FROM "+CLASSES_TABLE;


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sqlQuery,null);
        Log.d("65","testing cursor");
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            Log.d("65","there are results");
            //int id = cursor.getInt(0);
            //Log.d("69",Integer.toString(id));
            String courseCode = cursor.getString(1);
            //Log.d("70",userN);
            String courseName = cursor.getString(2);
            String courseDays = cursor.getString(3);
            String courseHours = cursor.getString(4);
            // check below for integer
            String capacity = cursor.getString(5);
            //
            String instructor = cursor.getString(6);
            String enrolledStudents = cursor.getString(7);
            String studList [] = enrolledStudents.split(",");
            ArrayList<String> studentsEnrolledCurr = new ArrayList<>();
            for(String currStudent: studList){
                studentsEnrolledCurr.add(currStudent);
            }



            //Log.d("71",passW);
            cursor.moveToNext();
            Class curr = new Class(courseCode,courseName);

            curr.setCourseDays(courseDays);
            curr.setCourseHours(courseHours);
            curr.setCapacity(capacity);
            curr.setInstructor(instructor);
            curr.setStudentsEnrolled(studentsEnrolledCurr);






            out.add(curr);
        }
        Log.d("72","done");



        //String username = cursor.getString(1);

        /*
        Log.d("58","testing cursor");

        String pass = cursor.getString(2);
        Log.d("64",username);
        Log.d("65",pass);
        */

        /*
        if(cursor.moveToFirst()){
            //there are results

            Log.d("62","there are results");




            do{
                //get username
                String username = cursor.getString(1);
                String pass = cursor.getString(2);
                Log.d("64",username);
                Log.d("65",pass);

                Admin curr = new Admin(username,pass);
                //out.add(curr);
            }while(cursor.moveToFirst());


        }else{
            //no results

        }
        */

        return out;
    }
}


//DatabaseClasses