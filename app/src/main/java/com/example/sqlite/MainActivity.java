package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Dialog_add_task.addNewTaskCallback, TaskAdapter.onItemSelect, Dialog_update_task.EditTaskCallback {
    SqliteHelper sqliteHelper;
    TaskAdapter taskAdapter = new TaskAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView task_rec = findViewById(R.id.rec_task);
        View view = findViewById(R.id.add_item_fab);
        task_rec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        task_rec.setAdapter(taskAdapter);
        sqliteHelper = new SqliteHelper(this);
        List<TaskModel> tasks = sqliteHelper.get_tasks();
        taskAdapter.add_all(tasks);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_add_task dialog = new Dialog_add_task();
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

    @Override
    public void onDeleteButtonClicked(TaskModel taskModel) {
        int result = sqliteHelper.delete_task(taskModel);

        if (result > 0) {
            taskAdapter.deleteItem(taskModel);
        }
    }

    @Override
    public void onUpdateButtonClicked(TaskModel taskModel) {
    Dialog_update_task dialog_update_task = new Dialog_update_task();
    Bundle bundle = new Bundle();
    bundle.putParcelable("task", taskModel);
    dialog_update_task.setArguments(bundle);
    dialog_update_task.show(getSupportFragmentManager(),null);

    }

    @Override
    public void updateTask(TaskModel taskModel) {

        int result = sqliteHelper.update_task(taskModel);
        if (result > 0) {
         taskAdapter.updateItem(taskModel);
        }
    }
}