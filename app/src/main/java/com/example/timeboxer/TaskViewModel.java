package com.example.timeboxer;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.timeboxer.room.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TaskViewModel extends ViewModel
{
    private TaskRepository repo;
    private LiveData<List<Task>> tasks;

    public TaskViewModel(Context context)
    {
        Log.i("MainViewModel", "CALLING TASKVIEWMODEL CONSTRUCTOR.");

        repo = new TaskRepository(context);
        tasks = repo.getAllTasks();
    }
    public LiveData<List<Task>> getAllTasks()
    {
        if(tasks == null)
            tasks = repo.getAllTasks();

        Log.i("MainViewModel", "Getting all tasks. The actual task list = " + tasks.getValue());

        return tasks;
    }
    public void insertTask(Task task)
    {
        repo.insertTask(task);
    }
    public void updateTask(Task task)
    {
        repo.updateTask(task);
    }
    public void deleteTask(Task task)
    {
        repo.deleteTask(task);
    }
}
