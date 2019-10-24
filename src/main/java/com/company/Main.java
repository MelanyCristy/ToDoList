package com.company;

import com.company.utilities.FileHelper;
import com.company.utilities.IOHandler;
import com.company.utilities.TaskHandler;
import com.company.utilities.TaskSorter;

public class Main {
    public static void main(String[] args) {
        startProgram();
    }

    private static void startProgram(){
        FileHelper fileHelper = new FileHelper();
        TaskSorter taskSorter = new TaskSorter();
        IOHandler ioHandler = new IOHandler();
        TaskHandler taskHandler = new TaskHandler(fileHelper, taskSorter, null);

        UserInterface userInterface = new UserInterface(ioHandler, taskHandler);
        userInterface.showMainMenu();
    }
}
