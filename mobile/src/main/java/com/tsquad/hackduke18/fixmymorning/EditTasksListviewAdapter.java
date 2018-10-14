package com.tsquad.hackduke18.fixmymorning;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditTasksListviewAdapter extends BaseAdapter {

    DBHandler handler;

    private LayoutInflater inflater;

    public EditTasksListviewAdapter(Context context) {
        this.handler = new DBHandler(context);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        Task[] tasks = handler.GetTasks();
        return tasks.length;
    }

    @Override
    public Task getItem(int position) {
        Task[] tasks = handler.GetTasks();
        return tasks[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listitem_layout_edit_task, null);
            holder = new ViewHolder();
            holder.text_description = (TextView)convertView.findViewById(R.id.description);
            holder.text_time = (TextView)convertView.findViewById(R.id.time);
            holder.text_priority = (TextView)convertView.findViewById(R.id.priority);

            holder.button_edit = (ImageButton)convertView.findViewById(R.id.edit);
            holder.button_delete = (ImageButton)convertView.findViewById(R.id.delete);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        Task[] tasks = handler.GetTasks();
        Task task = tasks[position];
        final Task task_to_edit = task;

        holder.text_description.setText(task.getName());
        holder.text_time.setText("0:12-\n0:20");
        holder.text_priority.setText("3");

        holder.button_edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // Parameters to pass to activity
                Bundle bundle = new Bundle();
                bundle.putInt("id", task_to_edit.getId());

                Intent intent = new Intent(view.getContext(), EditTaskActivity.class);
                intent.putExtras(bundle);

                view.getContext().startActivity(intent);
            }
        });

        final BaseAdapter adapter = this;

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                handler.deleteTask(task_to_edit);
                adapter.notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        public TextView text_description;
        public TextView text_time;
        public TextView text_priority;

        public ImageButton button_edit;
        public ImageButton button_delete;
    }
}
