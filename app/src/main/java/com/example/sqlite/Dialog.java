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

public class Dialog extends DialogFragment {
    private addNewTaskCallback addNewTaskCallback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        addNewTaskCallback = (Dialog.addNewTaskCallback) context;
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View v = LayoutInflater.from(getContext()).inflate(R.layout.add_task_dg,null,false);
        TextInputLayout textInputLayout = v.findViewById(R.id.text_input_l);
        TextInputEditText textInputEditText = v.findViewById(R.id.add_task_Et);
        MaterialButton materialButton = v.findViewById(R.id.btn_add_task);

         materialButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (textInputEditText.length()>0){
                 TaskModel taskModel = new TaskModel();
                 taskModel.setTitle(textInputEditText.getText().toString());
                 taskModel.setCompleted(false);
                 addNewTaskCallback.newTask(taskModel);
                 dismiss();
                 }else {
                     textInputLayout.setError("The title should not be empty");
                 }
             }
         });

        builder.setView(v);
        return builder.create();
    }
    public interface addNewTaskCallback{
         void newTask(TaskModel taskModel);
    }
}
