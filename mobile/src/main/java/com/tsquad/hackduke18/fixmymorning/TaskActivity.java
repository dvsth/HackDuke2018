package com.tsquad.hackduke18.fixmymorning;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

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

        List<String> taskList = new ArrayList<String>();


    }
}
