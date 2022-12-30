package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView task_rec = findViewById(R.id.rec_task);

        View view = findViewById(R.id.add_item_fab);

        TaskAdapter taskAdapter = new TaskAdapter();

        task_rec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        task_rec.setAdapter(taskAdapter);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Dialog dialog = new Dialog();
                 dialog.show(getSupportFragmentManager(),null);
            }
        });
    }
}