package com.example.sqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Dialog_update_task extends DialogFragment {
    private EditTaskCallback editTaskCallback;
    private TaskModel taskModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        editTaskCallback = (EditTaskCallback) context;
        taskModel = getArguments().getParcelable("task");
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View v = LayoutInflater.from(getContext()).inflate(R.layout.update_task_dg,null,false);
        TextInputLayout textInputLayout = v.findViewById(R.id.text_input_l);
        TextInputEditText textInputEditText = v.findViewById(R.id.update_task_Et);
        MaterialButton materialButton = v.findViewById(R.id.btn_update_task);

         materialButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (textInputEditText.length()>0){
                 TaskModel taskModel = new TaskModel();
                 taskModel.setTitle(textInputEditText.getText().toString());
                 taskModel.setCompleted(false);
                 editTaskCallback.updateTask(taskModel);
                 dismiss();
                 }else {
                     textInputLayout.setError("The title should not be empty");
                 }
             }
         });

        builder.setView(v);
        return builder.create();
    }
    public interface EditTaskCallback{
         void updateTask(TaskModel taskModel);
    }
}
