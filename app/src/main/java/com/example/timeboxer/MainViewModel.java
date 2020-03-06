package com.example.timeboxer;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.timeboxer.room.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainViewModel extends ViewModel
{
    private TaskRepository repo;
    public void init(Context context)
    {
        repo = new TaskRepository(context);
//        insertTask(new Task("Clean Room", "Hate this so much.", 45, TimeUnit.MINUTES.toString()));
//        insertTask(new Task("Code", "Love this so much! Just type away!", 60, TimeUnit.MINUTES.toString()));
//        insertTask(new Task("Debug", "Debugging sucks, however, I sometimes enjoy it.", 2, TimeUnit.HOURS.toString()));
    }
    public LiveData<List<Task>> getAllTasks()
    {
//        List<Task> allTasks = repo.getAllTasks().getValue();
//        Log.i("MainViewModel", "allTasks length is " + allTasks.size());
        return repo.getAllTasks();
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
