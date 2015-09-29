package com.arunpn.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class DetailScreen extends ActionBarActivity {

    TodoDataSource todoDataSource;
    EditText todoTask;
    int todoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        Intent intent = getIntent();
        todoId = intent.getIntExtra("id", 0);
        todoTask = (EditText) findViewById(R.id.editTask);
        todoDataSource = new TodoDataSource(this);
        todoTask.setText(todoDataSource.getTask(todoId));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            saveTask();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveTask() {

        todoDataSource.updateTask(todoId, todoTask.getText().toString());
        finish();
        Intent intent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
