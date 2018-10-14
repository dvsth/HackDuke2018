package com.tsquad.hackduke18.fixmymorning;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskActivity extends AppCompatActivity {

    private TableLayout tblLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        tblLayout = (TableLayout) findViewById(R.id.tblLayout);
        tblLayout.setBackgroundColor(Color.LTGRAY);

        //List<Task> taskList = new ArrayList<Task>();
        //for (Task t : taskList){

          //  TableRow row = new TableRow(this);

            //TextView taskLabels = new TextView(this);
            //taskLabels.setText(t.getName());

        List<String> taskList = new ArrayList<>(Arrays.asList("a", "b", "c"));
        for (String t : taskList){

        TableRow row = new TableRow(this);

        TextView taskLabels = new TextView(this);
        taskLabels.setText(t);
        row.addView(taskLabels);

        tblLayout.addView(row);


        }

    }
}
