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

public class DatabaseInstructors extends SQLiteOpenHelper {

    public static final String ADMIN_TABLE = "INSTRUCTOR_TABLE"; //uml check
    public static final String USERNAME = "INSTRUCTORID"; //uml check
    public static final String PASSWORD = "PASSWORD"; //uml check

    public DatabaseInstructors(@Nullable Context context) {
        super(context, "Database_Instructors.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //uml check
        //method creates databse
        String createTableStatement = "CREATE TABLE "+ADMIN_TABLE+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+USERNAME+" TEXT, "+PASSWORD+" TEXT )";
        sqLiteDatabase.execSQL(createTableStatement);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { //uml check

    }

    public void delete(String instructorID){ //uml check
        SQLiteDatabase db =  this.getWritableDatabase();
        String deleteFromTableStatement = "DELETE FROM "+ADMIN_TABLE+" WHERE "+USERNAME+"='"+instructorID+"'";
        Cursor cursor = db.rawQuery(deleteFromTableStatement,null);

        if(cursor.moveToFirst()){
            Log.d("47","43 DELETE ADMIN success");
        }
        else{
            Log.d("47","43 DELETE ADMIN fail");
        }
    }

    public boolean add(Instructor admin){ //uml check



        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERNAME,admin.getinstructorID());
        cv.put(PASSWORD,admin.getPassword());

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
    public List<Instructor> getAllInstructors(){ //uml check
        List<Instructor> out = new ArrayList<>();
        //create sql query string
        String sqlQuery = "SELECT * FROM "+ADMIN_TABLE;


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sqlQuery,null);
        Log.d("65","testing cursor-instructor");
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            Log.d("65","there are results");
            int id = cursor.getInt(0);
            Log.d("69",Integer.toString(id));
            String userN = cursor.getString(1);
            Log.d("70",userN);
            String passW = cursor.getString(2);
            Log.d("71",passW);
            cursor.moveToNext();
            Instructor curr = new Instructor(userN,passW);
            out.add(curr);
        }
        Log.d("72","done:instructor while loop");



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
