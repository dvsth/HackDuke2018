package com.tsquad.hackduke18.fixmymorning;

import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditTasksFragment extends Fragment {

    public String[] tasks = {"Brush Teeth", "Take Shower", "Go to Marketpalce", "Ride the C1", "Do my Math 212 Homework", "Go to Brenda", "Do my Laundry", "Finish my Resume", "Print the History Reading"};

    public EditTasksFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_tasks, container, false);

        ListView lv = (ListView)view.findViewById(R.id.listview);
        lv.setAdapter(new EditTasksListviewAdapter(getActivity()));

        ImageButton button = (ImageButton)view.findViewById(R.id.new_task);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler.Task task_to_edit = new DBHandler.Task(
                        0, "New Task", 0, 0, 6,0);
                long task_id = new DBHandler(getContext()).InsertTask(task_to_edit);

                // Parameters to pass to activity
                Bundle bundle = new Bundle();
                bundle.putLong("id", task_id);

                Intent intent = new Intent(view.getContext(), EditTaskActivity.class);
                intent.putExtras(bundle);

                view.getContext().startActivity(intent);
            }
        });

        return view;
    }

    /*
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(savedInstanceState,
    }
    */
}
