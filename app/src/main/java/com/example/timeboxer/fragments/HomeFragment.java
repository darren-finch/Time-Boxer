package com.example.timeboxer.fragments;

import androidx.lifecycle.Observer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timeboxer.MainViewModel;
import com.example.timeboxer.R;
import com.example.timeboxer.recyclerviews.MarginItemDecoration;
import com.example.timeboxer.recyclerviews.TasksRecyclerAdapter;
import com.example.timeboxer.room.Task;

import java.util.List;

public class HomeFragment extends Fragment
{
    private RecyclerView recyclerView;
    private TasksRecyclerAdapter adapter;

    private MainViewModel viewModel;
    private OnFragmentEventListener listener;

    public static HomeFragment newInstance()
    {
        return new HomeFragment();
    }

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
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        adapter = new TasksRecyclerAdapter(viewModel.getAllTasks().getValue());
        adapter.setOnTaskMenuItemSelectedListener(new TasksRecyclerAdapter.OnTaskMenuItemSelectedListener()
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
        });
        viewModel.getAllTasks().observe(getActivity(), new Observer<List<Task>>()
        {
            @Override
            public void onChanged(List<Task> tasks)
            {
                adapter.updateTasks(tasks);
            }
        });

        recyclerView = getView().findViewById(R.id.tasksRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MarginItemDecoration(16));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    public interface OnFragmentEventListener
    {
        void onEditTask(Task task);
    }

    public void setOnFragmentEventListener(OnFragmentEventListener listener)
    {
        this.listener = listener;
    }
}
