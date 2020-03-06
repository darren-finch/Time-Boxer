package com.example.timeboxer.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.timeboxer.R;
import com.example.timeboxer.room.Task;

import java.util.Objects;

public class AddEditTaskDialogFragment extends DialogFragment
{
    private OnDialogClickedListener listener;
    private EditText taskNameEditText;
    private EditText taskDescriptionEditText;
    private EditText timeToCompleteEditText;
    private String curTimeFormat = "";
    private Task taskData = null;

    public static AddEditTaskDialogFragment newInstance(Task taskData)
    {
        AddEditTaskDialogFragment fragment = new AddEditTaskDialogFragment();
        fragment.taskData = taskData;
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        try
        {
            listener = (OnDialogClickedListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnDialogClickedListener");
        }
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = inflater.inflate(R.layout.add_edit_task_dialog_fragment, null, false);
        taskNameEditText = dialogView.findViewById(R.id.taskNameEditText);
        taskDescriptionEditText = dialogView.findViewById(R.id.taskDescriptionEditText);
        timeToCompleteEditText = dialogView.findViewById(R.id.timeToCompleteEditText);
        Spinner timeFormatSpinner = dialogView.findViewById(R.id.timeFormatSpinner);

        //If editing a task, not creating a task...
        if(taskData != null)
        {
            taskNameEditText.setText(taskData.getTaskName());
            taskDescriptionEditText.setText(taskData.getTaskDescription());
            timeToCompleteEditText.setText(String.valueOf(taskData.getTimeToComplete()));
            //TODO: Make sure the spinner is set up with the task's current time unit
        }

        timeFormatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                curTimeFormat = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        builder.setMessage(taskData == null ? "Edit Task" : "Create Task")
                .setView(dialogView)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if(taskNameEditText.getText() == null || taskDescriptionEditText.getText() == null || timeToCompleteEditText.getText() == null || timeToCompleteEditText.getText().toString().isEmpty() || curTimeFormat == null)
                            return;

                        Task newTask = new Task(taskNameEditText.getText().toString(), taskDescriptionEditText.getText().toString(), Integer.parseInt(timeToCompleteEditText.getText().toString()), curTimeFormat);
                        listener.onPositiveButtonClicked(AddEditTaskDialogFragment.this, newTask, taskData != null);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        listener.onNegativeButtonClicked(AddEditTaskDialogFragment.this);
                        Objects.requireNonNull(getDialog()).cancel();
                    }
                });
        return builder.create();
    }

    public interface OnDialogClickedListener
    {
        void onPositiveButtonClicked(DialogFragment dialog, Task task, boolean updated);
        void onNegativeButtonClicked(DialogFragment dialog);
    }
}
