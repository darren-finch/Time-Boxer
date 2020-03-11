package com.example.timeboxer.fragments;

import androidx.lifecycle.Observer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.timeboxer.MainActivity;
import com.example.timeboxer.TaskViewModel;
import com.example.timeboxer.R;
import com.example.timeboxer.recyclerviews.MarginItemDecoration;
import com.example.timeboxer.recyclerviews.TasksRecyclerAdapter;
import com.example.timeboxer.room.Task;

import java.util.List;

public class HomeFragment extends NavHostFragment
{
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TasksRecyclerAdapter adapter;

    private int overallProgress;
    private int overallTime;

    private TaskViewModel viewModel;

    private OnFragmentEventListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        viewModel = MainActivity.viewModel;

        adapter = new TasksRecyclerAdapter();
        Log.i("HomeFragment", "All tasks = " + viewModel.getAllTasks().getValue());
        viewModel.getAllTasks().observe(getViewLifecycleOwner(), new Observer<List<Task>>()
        {
            @Override
            public void onChanged(List<Task> tasks)
            {
                overallTime = 0;
                overallProgress = 0;
                for(Task task : tasks)
                {
                    overallTime++;
                    if(task.isDone())
                    {
                        overallProgress++;
                    }
                }
                updateTaskProgressBar();
                if(overallTime > 0 && overallProgress == overallTime)
                {
                    if(listener != null)
                        listener.onAllTasksCompleted();
                }
                adapter.updateTasks(tasks);
            }
        });
        adapter.setOnTasksAdapterEventListener(new TasksRecyclerAdapter.OnTasksAdapterEventListener()
        {
            @Override
            public void onTaskMenuItemSelected(int position, int itemId)
            {
                Task curTask = adapter.getTaskAtPosition(position);
                switch (itemId)
                {
                    case R.id.deleteTaskButton:
                        viewModel.deleteTask(curTask);
                        break;
                    case R.id.editTaskButton:
                        if(listener == null)
                            return;

                        listener.onEditTask(curTask);
                        break;
                }
            }

            @Override
            public void onIsDoneUpdated(int position, boolean isDone)
            {
                Task task = adapter.getTaskAtPosition(position);
                task.setDone(isDone);
                Log.i("HomeFragment", "The task's UUID is " + task.getUuid());

                viewModel.updateTask(task);
            }
        });

        recyclerView = getView().findViewById(R.id.futureTasksRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MarginItemDecoration(16));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        progressBar = getView().findViewById(R.id.taskProgressBar);
    }

    private void updateTaskProgressBar()
    {
        progressBar.setMax(overallTime);
        progressBar.setProgress(overallProgress);
    }

    public interface OnFragmentEventListener
    {
        void onEditTask(Task task);
        void onAllTasksCompleted();
    }

    public void setOnFragmentEventListener(OnFragmentEventListener listener)
    {
        this.listener = listener;
    }
}
