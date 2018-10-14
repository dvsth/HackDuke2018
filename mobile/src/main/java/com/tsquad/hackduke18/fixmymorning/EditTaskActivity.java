package com.tsquad.hackduke18.fixmymorning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

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
        final long id = bundle.getLong("id");
        System.out.println("Task ID: " + id);
        DBHandler handler = new DBHandler(this);
        List<DBHandler.Task> tasks = handler.GetTasks();
        DBHandler.Task t = tasks.get(0);
        for (DBHandler.Task task : tasks) {
            if (task.getId() == id) {
                t = task;
                break;
            }
        }
        edit_description.setText(t.getDescription());
        edit_min_time.setText(Integer.toString((int)t.getMin_time()));
        edit_max_time.setText(Integer.toString((int)t.getMax_time()));
        edit_priority.setSelection(t.getPriority() - 1);
        final double order = t.getOrder() == 0 ? id : t.getOrder();

        Button button_save = (Button)findViewById(R.id.edit_done);
        button_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DBHandler handler = new DBHandler(view.getContext());
                DBHandler.Task task_to_update = new DBHandler.Task(
                        id,
                        edit_description.getText().toString(),
                        Integer.parseInt(edit_min_time.getText().toString()) * 60,
                        Integer.parseInt(edit_max_time.getText().toString()) * 60,
                        edit_priority.getSelectedItemPosition() + 1,
                        order
                        );
                handler.DeleteTask(id);
                handler.InsertTask(task_to_update);
                finish();
            }
        });
    }
}
