package com.example.timeboxer.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "tasks")
public class Task
{
    @PrimaryKey(autoGenerate = true)
    private int uuid;

    private boolean isDone;
    private String taskName = "";
    private String taskDescription;
    private int timeToComplete;
    private String timeFormat;
    //TODO: Add startTime for task. This is set from the calendar view. In the calendar view you'll create tasks for certain days and you'll need to know when the task starts. In the home screen, you'll automatically load the tasks for your current date.

    public boolean isExpanded;

    public Task(boolean isDone, String taskName, String taskDescription, int timeToComplete, String timeFormat)
    {
        this.isDone = isDone;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.timeToComplete = timeToComplete;
        this.timeFormat = timeFormat;
        this.isExpanded = false;
    }

    public String getTaskName()
    {
        return taskName;
    }
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }
    public String getTaskDescription()
    {
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription)
    {
        this.taskDescription = taskDescription;
    }
    public int getTimeToComplete()
    {
        return timeToComplete;
    }
    public void setTimeToComplete(int timeToComplete)
    {
        this.timeToComplete = timeToComplete;
    }
    public String getTimeFormat()
    {
        return timeFormat;
    }
    public void setTimeFormat(String timeFormat)
    {
        this.timeFormat = timeFormat;
    }
    public boolean isDone()
    {
        return isDone;
    }
    public void setDone(boolean done)
    {
        isDone = done;
    }
    public int getUuid()
    {
        return uuid;
    }
    public void setUuid(int uuid)
    {
        this.uuid = uuid;
    }
    public boolean isExpanded()
    {
        return isExpanded;
    }
    public void setExpanded(boolean expanded)
    {
        isExpanded = expanded;
    }
}
