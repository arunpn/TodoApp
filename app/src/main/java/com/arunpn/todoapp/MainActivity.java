package com.arunpn.todoapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {
    TodoDataSource todoDataSource;
    TodoCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoDataSource = new TodoDataSource(this);

        ListView listView = (ListView) findViewById(R.id.listView);
        cursorAdapter = new TodoCursorAdapter(this, todoDataSource.getAllTasksaSCursor());
        listView.setAdapter(cursorAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add_item:
                addTask();
                return true;
            default:
                return true;
        }

    }

    private void addTask() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create a Task").setMessage("Please Enter a Task");
        final EditText inputField = new EditText(this);
        builder.setView(inputField).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                todoDataSource.insertTask(inputField.getText().toString());
                refreshCursor();
                dialogInterface.dismiss();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();

    }

    void refreshCursor() {
        cursorAdapter.swapCursor(todoDataSource.getAllTasksaSCursor());
    }
}
