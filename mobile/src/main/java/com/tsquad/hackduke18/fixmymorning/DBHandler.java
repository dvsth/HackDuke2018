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



public class DBHandler extends SQLiteOpenHelper {


    //database and table info
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Schedule";
    private static final String TABLE_RESPONSES = "Schedule";
    //identifier - primary key
    private static final String KEY_ID = "id";

    //demographics
    private static final String NAME = "TASK_NAME";
    private static final String UPPER = "UL";
    private static final String LOWER = "LL";
    private static final String PRIORITY = "Priority";
    private static final String ORDER1 = "Order";
    private static final String DATE = "Date";


    //create a reference to the database we are handling
    private static SQLiteDatabase database;
    private static int counter;
    private static String[] QUESTION_ARRAY;

    //quelle heure est-il? wie viel uhr is es?
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
                "CREATE TABLE IF NOT EXISTS " + TABLE_RESPONSES + "("
                        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT,"
                        + UPPER + " NUMERIC, " + LOWER + " NUMERIC, " + PRIORITY + " INTEGER, " + ORDER1 + "NUMERIC," + DATE + " TEXT " + ")";
        database.execSQL(SCHEDULE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESPONSES);

        // Create tables again
        onCreate(db);
    }


    public Task[] getTask(SQLiteDatabase db) {

        Cursor cursor = db.query(TABLE_RESPONSES, null, null, null, null, null, null);
        int i = 0;
        Task[] array = new Task[cursor.getCount()];
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            array[i] = new Task(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getFloat(2),
                    cursor.getFloat(3),
                    cursor.getFloat(4),
                    cursor.getInt(5),
                    cursor.getString(6));
            i++;

        }

        return array;

    }

    public void addSurveyResponse(String[] inputResponses) {

        //initialize array containing all questions
        QUESTION_ARRAY = new String[]{NAME, UPPER, LOWER, PRIORITY, ORDER1, DATE};

        ContentValues values = new ContentValues();
        for (int i = 0; i < 6; i++) {
            values.put(QUESTION_ARRAY[i], inputResponses[i]);

        }

        getWritableDatabase().insert(TABLE_RESPONSES, null, values);

        getWritableDatabase().close();

    }

    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, task.getName());
        values.put(UPPER, task.getUpper());
        values.put(LOWER, task.getLower());
        values.put(PRIORITY, task.getPriority());
        values.put(ORDER1, task.getOrder());
        values.put(DATE, task.getDate());

        // updating row
        return db.update(TABLE_RESPONSES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(task.getId())});


    }

    public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESPONSES, KEY_ID + " = ?",
                new String[]{String.valueOf(task.getId())});
        db.close();
    }

    public Task[] GetTasks() {
        return getTask(database);
    }

    public Task GetTask(int id) {
        Task[] tasks = getTask(database);
        for (Task task : tasks) {
            if (task.getId() == id) return task;
        }
        return null;
    }

}