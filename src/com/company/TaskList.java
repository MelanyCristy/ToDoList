package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    Scanner scan = new Scanner(System.in);
    ArrayList<Task> tasks = new ArrayList();

    public void run() {
        System.out.println("Welcome to the to do list application");
        System.out.println("Pick an option: ");
        System.out.println(">> (1) Show Task List ");
        System.out.println(">> (2) Add New Task");
        System.out.println(">> (3) Edit Task (update, mark as done, remove)");
        System.out.println(">> (4) Save and Quit");

        {
            // scan.nextLine() = means read the next line that the user entered
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
                    break;

                default:
                    System.out.println("Invalid option");

            }
        }
    }

    private void saveAndQuit() {
    }

    private void showTaskList() {

    }

    private void editTask() {
    }


    private void addTask() {
        {
            System.out.println("write the description of the task:");
            String desc = scan.nextLine();

            System.out.println("write the title of the task:");
            String tit = scan.nextLine();
            System.out.println(taskAddedSuccessfully() + );
            int i = 0;
            while (i > 1) {
                System.out.println(">>Do you want to add one task more? Press Y for yes and N for no");
                String select = scan.nextLine();
                switch (select) {
                    case "Y":
                        addTask();
                        break;

                    case "N":
                        taskAddedSuccessfully();
                        break;

                    default:
                        System.out.println("Invalid option");
                }

            }
            i++;
            Task taskOne = new Task(desc, tit);

            tasks.add(taskOne);
            System.out.println("Here is your description " + taskOne.getDescription());
            System.out.println("Here is the title of your task " + taskOne.getTitle());

        }

    }

    private void taskAddedSuccessfully() {
        System.out.println("Your task has been added");
    }
};

