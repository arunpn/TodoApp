package com.arunpn.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by a1nagar on 9/28/15.
 */
public class TodoDataSource {
    TodoDbHelper todoDbHelper;
    Context context;
    SQLiteDatabase db;

    public TodoDataSource(Context context) {
        this.todoDbHelper = new TodoDbHelper(context);
        this.context = context;
    }

    public static final String TODO_TABLE = "todo";
    public static final String TODO_CREATE = "CREATE TABLE todo ( " +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "notes TEXT, " +
            "priority TEXT, " +
            "status TEXT )";

    public void insertTask(String todo) {
        db = todoDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", todo);
        db.insert(TODO_TABLE, null, values);
    }

    public ArrayList<String> getAllTasks() {
        db = todoDbHelper.getReadableDatabase();
        String sql = "select _id,name from todo";
        Cursor cursor = db.rawQuery(sql, null);

        ArrayList<String> todoItems = new ArrayList<>();
        while (cursor.moveToNext()) {
            String todo = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            todoItems.add(todo);
        }
        return todoItems;
    }

    public String getTask(int id) {
        db = todoDbHelper.getReadableDatabase();
        String sql = "select _id,name from todo where _id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        String todo = "";
        while (cursor.moveToNext()) {
            todo = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        }


        return todo;
    }

    public Cursor getAllTasksaSCursor() {
        db = todoDbHelper.getReadableDatabase();
        String sql = "select _id,name from todo";
        Cursor cursor = db.rawQuery(sql, null);

        return cursor;
    }

    public void deleteTask(int id) {
        db = todoDbHelper.getWritableDatabase();

        String whereClause = "_id" + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.delete(TODO_TABLE, whereClause, whereArgs);
    }

    public void updateTask(int id, String task) {
        db = todoDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", task);

        String whereClause = "_id" + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.update(TODO_TABLE, contentValues, whereClause, whereArgs);
    }

}
