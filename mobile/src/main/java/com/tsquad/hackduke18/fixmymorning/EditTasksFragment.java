package com.tsquad.hackduke18.fixmymorning;

import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
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
