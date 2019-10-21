package com.company;

import com.company.constants.MenuConstants;
import com.company.constants.MessageConstants;
import com.company.utilities.TaskHandler;

import java.util.Scanner;

public class UserInterface {
    private final TaskHandler taskHandler;
    private final Scanner scanner;
    private boolean isRunning;

    UserInterface(){
        taskHandler = new TaskHandler();
        scanner = new Scanner(System.in);
        isRunning = true;
    }

    void startProgram() {
        while (isRunning) {
            printMainMenu();
            String selectedOption = scanner.nextLine();
            switch (selectedOption) {
                case MenuConstants.OptionOne:
                    taskHandler.showTaskList();
                    break;
                case MenuConstants.OptionTwo:
                    taskHandler.addTask();
                    break;
                case MenuConstants.OptionThree:
                    taskHandler.editTask();
                    break;
                case MenuConstants.OptionFour:
                    taskHandler.saveAndQuit();
                    isRunning = false;
                    break;
                default:
                    writeInfo(MessageConstants.Errors.InvalidOption);
            }
        }
    }

    private void printMainMenu() {
        writeInfo("Welcome to the to do list application");
        writeInfo(String.format("You have %s tasks to do and %s tasks are done", taskHandler.numberOfTasks(), taskHandler.numberOfCompletedTasks()));
        writeInfo("Pick an option: ");
        writeInfo(">> (1) Show Task List ");
        writeInfo(">> (2) Add New Task");
        writeInfo(">> (3) Edit Task (update, mark as done, remove)");
        writeInfo(">> (4) Save and Quit");
    }

    public static void writeInfo(String message) {
        System.out.println(message);
    }

    public static void writeError(String error){
        System.out.println("Error: " + error);
    }
}