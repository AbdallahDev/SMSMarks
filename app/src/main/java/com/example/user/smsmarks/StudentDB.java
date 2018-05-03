package com.example.user.smsmarks;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDB extends SQLiteOpenHelper{
    public StudentDB(Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table student(mobile varchar(10),marks number(3))");
        db.execSQL("insert into student values('0771234567',85)");
        db.execSQL("insert into student values('0781234567',80)");
        db.execSQL("insert into student values('0791234567',90)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
