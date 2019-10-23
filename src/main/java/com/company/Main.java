package com.company;

import com.company.utilities.FileHelper;
import com.company.utilities.IOHandler;
import com.company.utilities.TaskSorter;

public class Main {
    public static void main(String[] args) {
        startProgram();
    }

    private static void startProgram(){
        TaskSorter taskSorter = new TaskSorter();
        IOHandler ioHandler = new IOHandler();
        FileHelper fileHelper = new FileHelper();

        UserInterface userInterface = new UserInterface(fileHelper, taskSorter, ioHandler);
        userInterface.showMainMenu();
    }
}
