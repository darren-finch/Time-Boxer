package com.example.timeboxer;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

public class TaskViewModelFactory implements ViewModelProvider.Factory
{
    private Context context;
    public TaskViewModelFactory(Context context)
    {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
    {
        try
        {
//            Log.i("TaskViewModelFactory", "WHYYYYY");
            return modelClass.getConstructor(Context.class).newInstance(context);
        }
        catch (Exception e)
        {
            Log.i("TaskViewModelFactory", "WHYYYYY");
            e.printStackTrace();
        }
        return null;
    }
}
