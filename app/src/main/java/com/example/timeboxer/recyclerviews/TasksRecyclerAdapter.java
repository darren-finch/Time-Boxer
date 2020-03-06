package com.example.timeboxer.recyclerviews;

import android.graphics.drawable.Icon;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeboxer.R;
import com.example.timeboxer.room.Task;

import java.util.ArrayList;
import java.util.List;

public class TasksRecyclerAdapter extends RecyclerView.Adapter<TasksRecyclerAdapter.ViewHolder>
{
    private List<Task> tasks;
    private Icon collapseIcon;
    private Icon expandIcon;
    private OnTaskMenuItemSelectedListener listener;

    public TasksRecyclerAdapter(List<Task> tasks)
    {
        this.tasks = tasks;
        collapseIcon = Icon.createWithResource("com.example.timeboxer", R.drawable.ic_collapse);
        expandIcon = Icon.createWithResource("com.example.timeboxer", R.drawable.ic_expand);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Task task = tasks.get(position);
        holder.taskNameTextView.setText(task.getTaskName());
        holder.taskDescriptionTextView.setText(task.getTaskDescription());
        holder.timeToCompleteTextView.setText(task.getTimeToComplete() + " " + task.getTimeFormat());
    }

    @Override
    public int getItemCount()
    {
        return tasks == null ? 0 : tasks.size();
    }

    public void updateTasks(List<Task> tasks)
    {
        if(this.tasks == null)
            this.tasks = new ArrayList<>();

        this.tasks.clear();
        this.tasks.addAll(tasks);
        notifyDataSetChanged();
    }

    public Task getTaskAtPosition(int pos)
    {
        return tasks.get(pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView taskNameTextView;
        private TextView taskDescriptionTextView;
        private TextView timeToCompleteTextView;
        private ImageButton expandCollapseButton;
        private ConstraintLayout expandableLayout;
        private ImageButton menuButton;
        private PopupMenu menu;

        ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            taskNameTextView = itemView.findViewById(R.id.taskName);
            taskDescriptionTextView = itemView.findViewById(R.id.taskDescription);
            timeToCompleteTextView = itemView.findViewById(R.id.timeToComplete);
            expandCollapseButton = itemView.findViewById(R.id.expandCollapseButton);
            expandCollapseButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Task task = tasks.get(getAdapterPosition());
                    task.isExpanded = !task.isExpanded;
                    setExpanded(task.isExpanded);
                    Log.i("TasksRecyclerViewAdapter", "Clicked!");
                }
            });
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            menuButton = itemView.findViewById(R.id.menuButton);
            if(menu != null)
                menu.dismiss();
            menuButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    menu = new PopupMenu(v.getContext(), v);
                    menu.inflate(R.menu.task_item_menu);
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                    {
                        @Override
                        public boolean onMenuItemClick(MenuItem item)
                        {
                            if(listener != null)
                                listener.onTaskMenuItemSelected(getAdapterPosition(), item.getItemId());
                            return false;
                        }
                    });
                    menu.setOnDismissListener(new PopupMenu.OnDismissListener()
                    {
                        @Override
                        public void onDismiss(PopupMenu menu)
                        {
                            menu = null;
                        }
                    });

                    menu.show();
                }
            });
            setExpanded(false);
        }

        private void setExpanded(boolean expanded)
        {
            if(expanded)
            {
                expandCollapseButton.setImageIcon(collapseIcon);
                expandableLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                expandCollapseButton.setImageIcon(expandIcon);
                expandableLayout.setVisibility(View.GONE);
            }
        }
    }

    public interface OnTaskMenuItemSelectedListener
    {
        void onTaskMenuItemSelected(int position, int itemId);
    }
    public void setOnTaskMenuItemSelectedListener(OnTaskMenuItemSelectedListener listener)
    {
        this.listener = listener;
    }
}
