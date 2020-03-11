package com.example.timeboxer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.timeboxer.fragments.AddEditTaskDialogFragment;
import com.example.timeboxer.fragments.HomeFragment;
import com.example.timeboxer.room.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements AddEditTaskDialogFragment.OnDialogClickedListener
{
    public static TaskViewModel viewModel;
    private HomeFragment.OnFragmentEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        TaskViewModelFactory factory = new TaskViewModelFactory(this);
        viewModel = ViewModelProviders.of(this, factory).get(TaskViewModel.class);

        final NavController navController = Navigation.findNavController(this, R.id.homeFragment);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottomNav, navController);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                NavigationUI.onNavDestinationSelected(item, navController);
                return false;
            }
        });
//        HomeFragment fragment = HomeFragment.newInstance();
//        fragment.setOnFragmentEventListener(new HomeFragment.OnFragmentEventListener()
//        {
//            @Override
//            public void onEditTask(Task task)
//            {
//                editTask(task);
//            }
//
//            @Override
//            public void onAllTasksCompleted()
//            {
//                CongratulationsDialogFragment fragment1 = CongratulationsDialogFragment.newInstance();
//                fragment1.show(getSupportFragmentManager(), "CongratulationsDialogFragment");
//            }
//        });
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container, fragment)
//                .commitNow();
    }

    public void addTask(View view)
    {
        AddEditTaskDialogFragment fragment = AddEditTaskDialogFragment.newInstance(null);
        fragment.show(getSupportFragmentManager(), "AddEditTaskFragment");
    }

    public void editTask(Task task)
    {
        AddEditTaskDialogFragment fragment = AddEditTaskDialogFragment.newInstance(task);
        fragment.show(getSupportFragmentManager(), "AddEditTaskFragment");
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
