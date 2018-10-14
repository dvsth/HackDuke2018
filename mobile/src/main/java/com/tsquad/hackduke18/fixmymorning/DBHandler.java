package com.tsquad.hackduke18.fixmymorning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public static class Task {
        public static final String TABLE_NAME = "Tasks";
        public static final String COLUMN_NAME_ID = "Id";
        public static final String COLUMN_NAME_DESCRIPTION = "Description";
        public static final String COLUMN_NAME_MIN_TIME = "MinTime";
        public static final String COLUMN_NAME_MAX_TIME = "MaxTime";
        public static final String COLUMN_NAME_PRIORITY = "Priority";
        public static final String COLUMN_NAME_ORDER = "OOrder";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_DESCRIPTION + " TEXT," +
                COLUMN_NAME_MIN_TIME + " DOUBLE," +
                COLUMN_NAME_MAX_TIME + " DOUBLE," +
                COLUMN_NAME_PRIORITY + " INTEGER,`" +
                COLUMN_NAME_ORDER + "` DOUBLE)";

        public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        private long id;
        private String description;
        private double min_time;
        private double max_time;
        private int priority;
        private double order;

        public Task(long id, String description, double min_time, double max_time, int priority, double order) {
            this.id = id;
            this.description = description;
            this.min_time = min_time;
            this.max_time = max_time;
            this.priority = priority;
            this.order = order;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getMin_time() {
            return min_time;
        }

        public void setMin_time(double min_time) {
            this.min_time = min_time;
        }

        public double getMax_time() {
            return max_time;
        }

        public void setMax_time(double max_time) {
            this.max_time = max_time;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public double getOrder() {
            return order;
        }

        public void setOrder(double order) {
            this.order = order;
        }
    }

    public static class Day {
        public static final String TABLE_NAME = "Days";
        public static final String COLUMN_NAME_DAY = "Day";
        public static final String COLUMN_NAME_START_TIME = "StartTime";
        public static final String COLUMN_NAME_END_TIME = "EndTime";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                "Id INTEGER PRIMARY KEY," +
                COLUMN_NAME_DAY + " INTEGER," +
                COLUMN_NAME_START_TIME + " DOUBLE," +
                COLUMN_NAME_END_TIME + " DOUBLE)";

        public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        private int day;
        private double start_time;
        private double end_time;

        public Day(int day, double start_time, double end_time) {
            this.day = day;
            this.start_time = start_time;
            this.end_time = end_time;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public double getStart_time() {
            return start_time;
        }

        public void setStart_time(double start_time) {
            this.start_time = start_time;
        }

        public double getEnd_time() {
            return end_time;
        }

        public void setEnd_time(double end_time) {
            this.end_time = end_time;
        }
    }

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "FixMyMorning.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Task.CREATE_TABLE);
        db.execSQL(Day.CREATE_TABLE);
        for (int i = 0; i < 7; i++) {
            ContentValues values = new ContentValues();
            values.put(Day.COLUMN_NAME_DAY, i);
            values.put(Day.COLUMN_NAME_START_TIME, 14400.0); // 4:00 am
            values.put(Day.COLUMN_NAME_END_TIME, 28800.0); // 8:00 am

            db.insert(Day.TABLE_NAME, null, values);
        }
    }

    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
        db.execSQL(Task.DROP_TABLE);
        db.execSQL(Day.DROP_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int old_version, int new_version) {
        onUpgrade(db, old_version, new_version);
    }

    public long InsertTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Task.COLUMN_NAME_DESCRIPTION, task.getDescription());
        values.put(Task.COLUMN_NAME_MIN_TIME, task.getMin_time());
        values.put(Task.COLUMN_NAME_MAX_TIME, task.getMax_time());
        values.put(Task.COLUMN_NAME_PRIORITY, task.getPriority());
        values.put(Task.COLUMN_NAME_ORDER, task.getOrder());

        long new_row_id = db.insert(
                Task.TABLE_NAME,
                null,
                values);
        return new_row_id;
    }

    public List<Task> GetTasks() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Task.TABLE_NAME,
                null, null, null, null, null, null, null);

        List<Task> tasks = new ArrayList<Task>();
        while (cursor.moveToNext()) {
            Task t = new Task(
                    cursor.getLong(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_DESCRIPTION)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_MIN_TIME)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_MAX_TIME)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_PRIORITY)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME_ORDER))
                    );
            tasks.add(t);
        }
        cursor.close();

        return tasks;
    }

    public void DeleteTask(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = Task.COLUMN_NAME_ID + " = ?";
        String[] selection_args = { String.format("%d", id) };
        db.delete(Task.TABLE_NAME, selection, selection_args);
    }

    public void UpdateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Task.COLUMN_NAME_DESCRIPTION, task.getDescription());
        values.put(Task.COLUMN_NAME_MIN_TIME, task.getMin_time());
        values.put(Task.COLUMN_NAME_MAX_TIME, task.getMax_time());
        values.put(Task.COLUMN_NAME_PRIORITY, task.getPriority());
        values.put(Task.COLUMN_NAME_ORDER, task.getOrder());

        String selection = Task.COLUMN_NAME_ID + " = ?";
        String[] selection_args = { String.format("%d", task.id) };

        int count = db.update(
                Task.TABLE_NAME,
                values,
                selection,
                selection_args
        );
    }
}
