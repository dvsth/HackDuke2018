package com.tsquad.hackduke18.fixmymorning;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.tsquad.hackduke18.fixmymorning.DBHandler.Task;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class TaskActivity extends AppCompatActivity {

    private TableLayout tblLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        tblLayout = (TableLayout) findViewById(R.id.tblLayout);
        tblLayout.setBackgroundColor(Color.LTGRAY);
        DBHandler dbHandler = new DBHandler(this);

        //FOR TESTING
        //Task task1 = new Task(1, "Shower", 500, 1000,3, 2);
        //Task task2 = new Task(2, "Brushing", 120, 120,5, 1);
        //Task task3 = new Task(3, "Eating", 200, 400, 1, 3);
        //dbHandler.InsertTask(task1);
        //dbHandler.InsertTask(task2);
        //dbHandler.InsertTask(task3);

        List<Task> taskList = dbHandler.GetTasks();
        //System.out.println("TESTDEV: " + task1.getDescription() + " ||| " + task2.getDescription());
        Algorithm algo = new Algorithm();
        taskList = algo.executeSelection(taskList, 620);
        System.out.println("TESTDEV: " + taskList.get(0).getDescription());

        //End test Code

        for (Task t : taskList) {

            TableRow row = new TableRow(this);

            TextView taskLabels = new TextView(this);
            taskLabels.setText(t.getDescription());
            row.addView(taskLabels);

            TextView duration = new TextView(this);
            if (t.getDuration()!=0)
                duration.setText("" + t.getDuration());
            else
                duration.setText("X");
            row.addView(duration);

            tblLayout.addView(row);

        }

    }
}