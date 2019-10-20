package com.company;

import java.util.Scanner;

 class UserInterface {
    TaskHandler taskHandler = new TaskHandler();
    Scanner scan = new Scanner(System.in);
    boolean isRunning = true;

    public void printMenu() {
        System.out.println("Welcome to the to do list application");
        System.out.println("Pick an option: ");
        System.out.println(">> (1) Show Task List ");
        System.out.println(">> (2) Add New Task");
        System.out.println(">> (3) Edit Task (update, mark as done, remove)");
        System.out.println(">> (4) Save and Quit");

        while (isRunning) {
            String option = scan.nextLine();
            switch (option) {
                case "1":
                    taskHandler.showTaskList();
                    break;
                case "2":
                    taskHandler.addTask();
                    break;
                case "3":
                    taskHandler.editTask(1,new Task("",""));
                    break;
                case "4":
                    taskHandler.saveAndQuit();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}