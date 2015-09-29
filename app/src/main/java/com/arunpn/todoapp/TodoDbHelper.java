package com.arunpn.todoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a1nagar on 9/28/15.
 */
public class TodoDbHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "TODO";
    final static int DATABASE_VERSION = 1;

    public TodoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TodoDataSource.TODO_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
