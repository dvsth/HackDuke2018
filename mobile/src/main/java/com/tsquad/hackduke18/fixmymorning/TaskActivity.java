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

        List<Task> taskList = new ArrayList<Task>();
        for (Task t : taskList) {

            TableRow row = new TableRow(this);

            TextView taskLabels = new TextView(this);
            taskLabels.setText(t.getName());
            row.addView(taskLabels);

            TextView duration = new TextView(this);
            if (t.getDuration()!=0)
                duration.setText("" + t.getDuration());
            else
                duration.setText("X");


            tblLayout.addView(row);

        }

    }
}
