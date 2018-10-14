package com.tsquad.hackduke18.fixmymorning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import static android.content.ContentValues.TAG;


/*
public class DayManager extends SQLiteOpenHelper {


    //database and table info
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Schedule";
    private static final String TABLE_DAY = "Day";
    //identifier - primary key
    private static final String KEY_ID = "id";

    //demographics
    private static final String DAY = "Day";
    private static final String START = "Start";
    private static final String END = "End";



    //create a reference to the database we are handling
    private static SQLiteDatabase database;
    private static int counter;
    private static String[] QUESTION_ARRAY;
    private String timestamp;


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {


        //set reference to database we are handling
        database = db;

        //starting the primary key field from 0;
        counter = 0;

        //create the table with relevant columns
        String SCHEDULE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_DAY + "("
                        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DAY + " TEXT,"
                        + START + " NUMERIC, " + END + " NUMERIC, "  + ")";
        database.execSQL(SCHEDULE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAY);

        // Create tables again
        onCreate(db);
    }


    public Day[] getDay(SQLiteDatabase db) {

        Cursor cursor = db.query(TABLE_DAY, null, null, null, null, null, null);
        int i = 0;
        Day[] array = new String[cursor.getCount()];
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            array[i] = new Day(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            i++;

        }

        return array;

    }

    public void addDayResponse(String[] inputResponses) {

        //initialize array containing all questions
        QUESTION_ARRAY = new String[]{DAY, START, END};

        ContentValues values = new ContentValues();
        for (int i = 0; i < 3; i++) {
            values.put(QUESTION_ARRAY[i], inputResponses[i]);

        }

        getWritableDatabase().insert(TABLE_DAY, null, values);

        getWritableDatabase().close();

    }

    public int updateTask(Day day) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DAY, Day.getDay());
        values.put(START, Day.getStart());
        values.put(END, Day.getEnd());

        // updating row
        return db.update(TABLE_DAY, values, KEY_ID + " = ?",
                new String[]{String.valueOf(Day.getID())});


    }

    public void deleteNote(Day day) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DAY, KEY_ID + " = ?",
                new String[]{String.valueOf(DAY.getId())});
        db.close();
    }




}
*/