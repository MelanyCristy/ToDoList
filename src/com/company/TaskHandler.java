package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskHandler {
    private Scanner scan = new Scanner(System.in);
    private ArrayList<Task> taskList = new ArrayList<Task>();

    public TaskHandler() {
        loadTasks();
    }

    public void saveAndQuit()
    {
        Reader reader = new Reader();
        reader.save(taskList);
    }

    public void loadTasks() {
        //load list from file and add them to the taskList
    }

    public void showTaskList()
    {
        System.out.println(taskList);
        for (Task task : taskList)
        {
            System.out.println(task);
        }

    }

    public Task getTaskById(int index) {
        return taskList.get(index);
    }

    public void editTask(int index, Task task) {
        taskList.set(index - 1, task);
    }

    public void addTask() {
        {
            System.out.println("write the description of the task:");
            String desc = scan.nextLine();

            System.out.println("write the title of the task:");
            String tittle = scan.nextLine();
            Task task = new Task(desc, tittle);
            taskList.add(task);

            taskAddedSuccessfully();
            System.out.println();

            boolean isRunning = true;
            while (isRunning) {
                System.out.println(">>Do you want to add one task more? Press Y for yes and N for no");
                String select = scan.nextLine().toUpperCase();
                switch (select) {
                    case "Y":
                        addTask();
                        break;
                    case "N":
                        taskAddedSuccessfully();
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }
        }
    }

    public void taskAddedSuccessfully() {
        System.out.println("Your task has been added");
    }
}