package com.example.timeboxer.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "tasks")
public class Task
{
    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String taskName = "";
    private String taskDescription;
    private int timeToComplete;
    private String timeFormat;

    public boolean isExpanded;

    public Task(String taskName, String taskDescription, int timeToComplete, String timeFormat)
    {
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
    public int getUid()
    {
        return uid;
    }
    public void setUid(int uid)
    {
        this.uid = uid;
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
