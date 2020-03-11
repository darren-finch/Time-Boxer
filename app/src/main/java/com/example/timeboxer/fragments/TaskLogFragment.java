package com.example.timeboxer.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timeboxer.R;

public class TaskLogFragment extends Fragment
{
    public static TaskLogFragment newInstance()
    {
        return new TaskLogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.task_log_fragment, container, false);
    }
}
