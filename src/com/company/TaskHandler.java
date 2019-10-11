package com.company;

import java.util.ArrayList;
import java.util.Scanner;

class TaskHandler {
    private Scanner scan = new Scanner(System.in);
    private ArrayList<Task> taskList = new ArrayList<Task>();

    void run() {
        boolean isRunning = true;

        while(isRunning){
            printMenu();
            String option = scan.nextLine();
            switch (option) {
                case "1":
                    showTaskList();
                    break;
                case "2":
                    addTask();
                    break;
                case "3":
                    editTask();
                    break;
                case "4":
                    saveAndQuit();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void printMenu(){
        System.out.println("Welcome to the to do list application");
        System.out.println("Pick an option: ");
        System.out.println(">> (1) Show Task List ");
        System.out.println(">> (2) Add New Task");
        System.out.println(">> (3) Edit Task (update, mark as done, remove)");
        System.out.println(">> (4) Save and Quit");
    }

    private void saveAndQuit()
    {

    }

    private void showTaskList()
    {
        //System.out.println(taskList);
        for (Task task : taskList)
        {
            //System.out.println(task.toString()); // this is exactly similar to the next line
            System.out.println(task);
        }

    }

    private void editTask() {

    }

    private void addTask() {
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

    private void taskAddedSuccessfully() {
        System.out.println("Your task has been added");
    }
}