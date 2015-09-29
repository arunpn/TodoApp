package com.arunpn.todoapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by a1nagar on 9/29/15.
 */
public class TodoCursorAdapter extends CursorAdapter {

    TodoDataSource todoDataSource;

    public TodoCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        todoDataSource = new TodoDataSource(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_task, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView todoBody = (TextView) view.findViewById(R.id.todoName);
        Button deleteButton = (Button) view.findViewById(R.id.deleteButton);

        int todoId = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        todoBody.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
        deleteButton.setOnClickListener(new DeleteEntryOnClicklistener(todoId));
        todoBody.setOnClickListener(new TextEntryOnClickListener(todoId, context));
    }

    public class DeleteEntryOnClicklistener implements View.OnClickListener {
        int id;

        public DeleteEntryOnClicklistener(int id) {
            this.id = id;
        }

        @Override
        public void onClick(View v) {
            todoDataSource.deleteTask(id);
            TodoCursorAdapter.this.swapCursor(todoDataSource.getAllTasksaSCursor());
        }
    }

    public class TextEntryOnClickListener implements View.OnClickListener {
        int id;
        Context context;

        public TextEntryOnClickListener(int id, Context context) {
            this.id = id;
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DetailScreen.class);
            intent.putExtra("id", id);
            context.startActivity(intent);
        }
    }
}

