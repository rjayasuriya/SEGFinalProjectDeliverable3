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

public class DatabaseStudent extends SQLiteOpenHelper {

    public static final String ADMIN_TABLE = "STUDENT_TABLE";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String ENROLLEDCOURSES = "ENROLLEDCOURSES";

    public DatabaseStudent(@Nullable Context context) {
        super(context, "Database_Student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //uml check
        String createTableStatement = "CREATE TABLE "+ADMIN_TABLE+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+USERNAME+" TEXT, "+PASSWORD+" TEXT, "+ENROLLEDCOURSES+" TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { //uml check

    }
    public void delete(String id){ //uml check
        SQLiteDatabase db =  this.getWritableDatabase();
        Log.d("47","42 DELETE student | starting | looking for ID: "+id);
        String deleteFromTableStatement = "DELETE FROM "+ADMIN_TABLE+" WHERE "+USERNAME+"='"+id+"'";
        Cursor cursor = db.rawQuery(deleteFromTableStatement,null);
        if(cursor.moveToFirst()){
            Log.d("47","42 DELETE student | success ");
        }else{
            Log.d("47","42 DELETE student | fail ");
        }

    }
    public void updateEnrolledCourses(String currentClassID, String newList){
        Log.d("296","Testing: updateCurrStudentEnrolledCourses");
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ENROLLEDCOURSES,newList);
        Log.d("297","Testing: updateCurrStudentEnrolledCourses");
        db.update(ADMIN_TABLE,cv,USERNAME+" = '"+currentClassID+"'", null);
        Log.d("298","Testing: updateCurrStudentEnrolledCourses");
    }

    public boolean add(Student admin){ //uml check



        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERNAME,admin.getstudentID());
        cv.put(PASSWORD,admin.getPassword());
        cv.put(ENROLLEDCOURSES,"");

        long admin1 = db.insert(ADMIN_TABLE,  null, cv);
        //db.close();
        if(admin1>0){
            Log.d("44","Sign Up Success");
            return true;
        }else{
            Log.d("47","Sign Up Failed");
            return false;
        }

    }
    public List<Student> getAllStudents() { //uml check
        List<Student> out = new ArrayList<>();
        //create sql query string
        String sqlQuery = "SELECT * FROM " + ADMIN_TABLE;


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sqlQuery, null);
        Log.d("65", "testing cursor");
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Log.d("65", "there are results");
            int id = cursor.getInt(0);
            Log.d("69", Integer.toString(id));
            String userN = cursor.getString(1);
            Log.d("70", userN);
            String passW = cursor.getString(2);
            Log.d("71", passW);
            String enrolledList = cursor.getString(3);
            cursor.moveToNext();
            Student curr = new Student(userN, passW);
            curr.setEnrolledCourses(enrolledList);
            out.add(curr);
        }
        Log.d("72", "done");
        return out;
    }

}

