package com.example.timeboxer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.timeboxer.room.Task;
import com.example.timeboxer.room.TaskDao;
import com.example.timeboxer.room.TaskDatabase;

import java.util.List;

public class TaskRepository
{
    private TaskDao dao;
    TaskRepository(Context context)
    {
        dao = TaskDatabase.getInstance(context).getDao();
    }
    public LiveData<List<Task>> getAllTasks()
    {
        try
        {
            GetAllTasksAsyncTask getAllTasksAsyncTask = new GetAllTasksAsyncTask(dao);
            return getAllTasksAsyncTask.execute().get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public void insertTask(Task task)
    {
        new InsertTaskAsyncTask(dao).execute(task);
    }
    public void updateTask(Task task)
    {
        new UpdateTaskAsyncTask(dao).execute(task);
    }
    public void deleteTask(Task task)
    {
        new DeleteTaskAsyncTask(dao).execute(task);
    }

    public static class GetAllTasksAsyncTask extends AsyncTask<Void, Void, LiveData<List<Task>>>
    {
        private TaskDao dao;
        GetAllTasksAsyncTask(TaskDao dao) { this.dao = dao; }
        @Override
        protected LiveData<List<Task>> doInBackground(Void... voids)
        {
            return dao.getAllTasks();
        }
    }
    public static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void>
    {
        private TaskDao dao;
        InsertTaskAsyncTask(TaskDao dao) { this.dao = dao; }
        @Override
        protected Void doInBackground(Task... tasks)
        {
            dao.insertTask(tasks[0]);
            return null;
        }
    }
    public static class UpdateTaskAsyncTask extends AsyncTask<Task, Void, Void>
    {
        private TaskDao dao;
        UpdateTaskAsyncTask(TaskDao dao) { this.dao = dao; }
        @Override
        protected Void doInBackground(Task... tasks)
        {
            Log.i("TaskRepository", tasks[0].isDone() ? "THE TASK IS DONE!" : "");
            dao.updateTask(tasks[0]);
            return null;
        }
    }
    public static class DeleteTaskAsyncTask extends AsyncTask<Task, Void, Void>
    {
        private TaskDao dao;
        DeleteTaskAsyncTask(TaskDao dao) { this.dao = dao; }
        @Override
        protected Void doInBackground(Task... tasks)
        {
            dao.deleteTask(tasks[0]);
            return null;
        }
    }
}
