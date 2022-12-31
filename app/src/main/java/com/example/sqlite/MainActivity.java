package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Dialog.addNewTaskCallback {
    SqliteHelper sqliteHelper;
    TaskAdapter taskAdapter = new TaskAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView task_rec = findViewById(R.id.rec_task);
        sqliteHelper = new SqliteHelper(this);
        List<TaskModel> tasks = sqliteHelper.get_tasks();

        View view = findViewById(R.id.add_item_fab);


        task_rec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        task_rec.setAdapter(taskAdapter);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog();
                dialog.show(getSupportFragmentManager(), null);
            }
        });
    }

    @Override
    public void newTask(TaskModel taskModel) {

        long task_id = sqliteHelper.add_task(taskModel);
        if (task_id > 0) {
            taskModel.setId(task_id);
            taskAdapter.additem(taskModel);
        }
    }
}