package com.example.sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<TaskModel> taskModelList = new ArrayList<>();
    private TaskAdapter.onItemSelect onItemSelect;

    public TaskAdapter(onItemSelect onItemSelect) {
        this.onItemSelect = onItemSelect;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind_task(taskModelList.get(position));
    }

    public void additem(TaskModel taskModel) {
        taskModelList.add(0, taskModel);
        notifyItemInserted(0);
    }

    public void updateItem(TaskModel taskModel) {
        for (int i = 0; i < taskModelList.size(); i++) {
            if (taskModel.getId() == taskModelList.get(i).getId()) {
                taskModelList.set(i,taskModel);
                notifyItemChanged(i);
            }
        }
    }

    @Override
    public int getItemCount() {
        return taskModelList.size();
    }

    public void add_all(List<TaskModel> taskModelList) {
        this.taskModelList.addAll(taskModelList);
        notifyDataSetChanged();
    }

    public void deleteItem(TaskModel taskModel) {
        for (int i = 0; i < taskModelList.size(); i++) {
            if (taskModelList.get(i).getId() == taskModel.getId()) {
                taskModelList.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        View delete, update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox_title);
            delete = itemView.findViewById(R.id.img_delete);
            update = itemView.findViewById(R.id.img_edit);
        }

        public void bind_task(TaskModel taskModel) {
            checkBox.setChecked(taskModel.isCompleted());
            checkBox.setText(taskModel.getTitle());
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemSelect.onDeleteButtonClicked(taskModel);
                }
            });
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemSelect.onUpdateButtonClicked(taskModel);
                }
            });


        }

    }

    public interface onItemSelect {
        public void onDeleteButtonClicked(TaskModel taskModel);
        public void onUpdateButtonClicked(TaskModel taskModel);
    }
}
