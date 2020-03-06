package com.example.timeboxer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.timeboxer.fragments.AddEditTaskDialogFragment;
import com.example.timeboxer.fragments.HomeFragment;
import com.example.timeboxer.room.Task;

public class MainActivity extends AppCompatActivity implements AddEditTaskDialogFragment.OnDialogClickedListener
{
    private MainViewModel viewModel;
    private HomeFragment.OnFragmentEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.init(this);

        HomeFragment fragment = HomeFragment.newInstance();
        fragment.setOnFragmentEventListener(new HomeFragment.OnFragmentEventListener()
        {
            @Override
            public void onEditTask(Task task)
            {
                editTask(task);
            }
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow();
    }

    public void addTask(View view)
    {
        AddEditTaskDialogFragment fragment = AddEditTaskDialogFragment.newInstance(null);
        fragment.show(getSupportFragmentManager(), "AddTaskFragment");
    }

    public void editTask(Task task)
    {
        AddEditTaskDialogFragment fragment = AddEditTaskDialogFragment.newInstance(task);
        fragment.show(getSupportFragmentManager(), "AddTaskFragment");
    }

    @Override
    public void onPositiveButtonClicked(DialogFragment dialog, Task task, boolean updated)
    {
        if(updated)
        {
            Toast.makeText(this, "Updated Task!", Toast.LENGTH_SHORT).show();
            viewModel.updateTask(task);
        }
        else
        {
            viewModel.insertTask(task);
        }
    }

    @Override
    public void onNegativeButtonClicked(DialogFragment dialog)
    {

    }
}
