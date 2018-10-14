package com.tsquad.hackduke18.fixmymorning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        // Input fields
        final EditText edit_description = (EditText)findViewById(R.id.edit_description);
        final EditText edit_min_time = (EditText)findViewById(R.id.edit_min_time);
        final EditText edit_max_time = (EditText)findViewById(R.id.edit_max_time);
        final Spinner edit_priority = (Spinner)findViewById(R.id.edit_priority);

        // Retrieve bundle
        Bundle bundle = getIntent().getExtras();
        final int id = bundle.getInt("id");
        Task task = new DBHandler(this).GetTask(id);
        edit_description.setText(task.getName());
        edit_min_time.setText(String.format("%i", task.getLower()));
        edit_max_time.setText(String.format("%i", task.getUpper()));
        edit_priority.setSelection(task.getPriority() - 1);

        Button button_save = (Button)findViewById(R.id.edit_done);
        button_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DBHandler handler = new DBHandler(view.getContext());
                Task task_to_update = new Task(
                        id,
                        edit_description.getText().toString(),
                        Integer.parseInt(edit_min_time.getText().toString()),
                        Integer.parseInt(edit_max_time.getText().toString()),
                        0,
                        edit_priority.getSelectedItemPosition() - 1,
                        ""
                        );
                handler.updateTask(task_to_update);
                finish();
            }
        });
    }
}
