package com.example.timeboxer.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = { Task.class }, version = 3, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase
{
    private static TaskDatabase instance;
    public static TaskDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context, TaskDatabase.class, "tasks")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public abstract TaskDao getDao();
}
