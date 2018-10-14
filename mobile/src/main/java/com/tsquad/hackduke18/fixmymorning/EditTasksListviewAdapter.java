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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EditTasksListviewAdapter extends BaseAdapter {

    DBHandler handler;

    private LayoutInflater inflater;

    public EditTasksListviewAdapter(Context context) {
        this.handler = new DBHandler(context);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        List<DBHandler.Task> tasks = handler.GetTasks();
        return tasks.size();
    }

    @Override
    public DBHandler.Task getItem(int position) {
        List<DBHandler.Task> tasks = handler.GetTasks();
        /*
        Collections.sort(tasks, new Comparator<DBHandler.Task>() {
            @Override
            public int compare(DBHandler.Task o1, DBHandler.Task o2) {
                return o1.getOrder() - o2.getOrder();
            }
        });
        */
        return tasks.get(position);
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

        List<DBHandler.Task> tasks = handler.GetTasks();
        DBHandler.Task task = tasks.get(position);
        final DBHandler.Task task_to_edit = task;

        holder.text_description.setText(task.getDescription());
        holder.text_time.setText(String.format("%2d:%02d-\n%2d:%02d",
                (int)(task.getMin_time() / 3600), (int)(task.getMin_time() / 60),
                (int)(task.getMax_time() / 3600), (int)(task.getMax_time() / 60)));
        holder.text_priority.setText(Integer.toString(task.getPriority()));

        final BaseAdapter adapter = this;

        holder.button_edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // Parameters to pass to activity
                Bundle bundle = new Bundle();
                bundle.putLong("id", task_to_edit.getId());

                Intent intent = new Intent(view.getContext(), EditTaskActivity.class);
                intent.putExtras(bundle);

                view.getContext().startActivity(intent);
                adapter.notifyDataSetChanged();
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                handler.DeleteTask(task_to_edit.getId());
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
