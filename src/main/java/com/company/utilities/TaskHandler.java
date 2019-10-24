package com.company.utilities;

import com.company.entities.Task;
import com.company.enums.Status;

import java.util.ArrayList;

public class TaskHandler {
    private ArrayList<Task> taskList;
    private FileHelper fileHelper;
    private TaskSorter taskSorter;

    public TaskHandler(FileHelper fileHelper, TaskSorter taskSorter, ArrayList<Task> taskList){
        this.fileHelper = fileHelper;
        this.taskSorter = taskSorter;
        this.taskList = getTaskList(taskList);
    }

    public void saveTasks(){
        fileHelper.saveTasksToFile(taskList);
    }

    public void sortTasksByDueDate(){
        taskList = taskSorter.sortByDueDate(this.taskList);
    }

    public void sortTasksByProjectName(){
        taskList = taskSorter.sortByProject(this.taskList);
    }

    public ArrayList<Task> getTasks(){
        return taskList;
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    public void updateTask(int index, Task task){
        taskList.set(index, task);
    }

    public void setTaskStatus(int index, Status status){
        taskList.get(index).setStatus(status);
    }

    public boolean taskListIsNotEmpty(){
        return TaskValidator.taskListIsNotEmpty(taskList);
    }

    public int numberOfCompletedTasks() {
        return (int) taskList.stream().filter(x -> x.getStatus() == Status.Finished).count();
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public int numberOfTasks() {
        return taskList.size();
    }

    private ArrayList<Task> getTaskList(ArrayList<Task> taskList){
        if(TaskValidator.taskListIsNotEmpty(taskList)){
            return taskList;
        } else {
            return fileHelper.getTasksFromFile();
        }
    }
}
