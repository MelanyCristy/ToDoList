package com.company;

import com.company.constants.MenuOptions;
import com.company.constants.Output;
import com.company.entities.Task;
import com.company.enums.Status;
import com.company.utilities.*;

import java.util.ArrayList;

public class UserInterface {
    private final IOHandler ioHandler;
    private final TaskHandler taskHandler;

    public UserInterface(IOHandler ioHandler, TaskHandler taskHandler) {
        this.ioHandler = ioHandler;
        this.taskHandler = taskHandler;
    }

    public void showMainMenu() {
        boolean isRunning = true;

        while (isRunning) {
            printMainMenu();
            String selectedOption = ioHandler.getInput();
            switch (selectedOption) {
                case MenuOptions.OptionOne:
                    showTaskList();
                    break;
                case MenuOptions.OptionTwo:
                    addTask();
                    break;
                case MenuOptions.OptionThree:
                    editTask();
                    break;
                case MenuOptions.OptionFour:
                    taskHandler.saveTasks();
                    isRunning = false;
                    break;
                default:
                    ioHandler.output(Output.Errors.InvalidOption);
            }
        }
    }

    private void showTaskList() {
        printTasks();
        if (!taskHandler.taskListIsNotEmpty()) return;

        ioHandler.output(Output.Info.SortByDateOrProject);
        boolean isRunning = true;
        while (isRunning) {
            String selectedValue = ioHandler.getInput().toUpperCase();
            switch (selectedValue.toUpperCase()) {
                case MenuOptions.OptionDate:
                    taskHandler.sortTasksByDueDate();
                    showTaskList();
                    isRunning = false;
                    break;
                case MenuOptions.OptionProject:
                    taskHandler.sortTasksByProjectName();
                    showTaskList();
                    isRunning = false;
                    break;
                case MenuOptions.OptionCancel:
                    isRunning = false;
                    break;
                default:
                    ioHandler.output(Output.Errors.InvalidOption);
                    break;
            }
        }
    }

    private void editTask() {
        if (!taskHandler.taskListIsNotEmpty()) {
            ioHandler.output(Output.Errors.NoTasksWarning);
            return;
        }

        printTasks();
        ioHandler.output(Output.Info.ChooseTaskToEdit);
        String selectedTask = ioHandler.getInput();
        if(TaskValidator.indexIsIntegerAndWithinBoundsOfArray(selectedTask, taskHandler.numberOfTasks())){
            startEditMode(Integer.parseInt(selectedTask));
        } else {
            ioHandler.output(Output.Errors.InvalidOption);
        }
    }

    private void addTask() {
        taskHandler.addTask(createTaskFromInput());
        ioHandler.output(Output.Info.YourTaskHasBeenAdded);

        boolean isRunning = true;
        while (isRunning) {
            ioHandler.output(Output.Info.AddMoreTasks);
            String selectedValue = ioHandler.getInput().toUpperCase();
            switch (selectedValue) {
                case MenuOptions.OptionYes:
                    addTask();
                    break;
                case MenuOptions.OptionNo:
                    isRunning = false;
                    break;
                default:
                    ioHandler.output(Output.Errors.InvalidOption);
                    break;
            }
        }
    }

    private void startEditMode(int selectedTaskAsNumber) {
        ioHandler.output(Output.Info.EditOptions);
        String selectedValue = ioHandler.getInput().toUpperCase();
        switch (selectedValue) {
            case MenuOptions.OptionUpdate:
                updateTask(selectedTaskAsNumber);
                break;
            case MenuOptions.OptionMarkDone:
                taskHandler.setTaskStatus(selectedTaskAsNumber, Status.Finished);
                break;
            case MenuOptions.OptionRemove:
                taskHandler.removeTask(selectedTaskAsNumber);
                break;
            default:
                ioHandler.output(Output.Errors.InvalidOption);
                editTask();
                break;
        }
    }

    private void updateTask(int selectedIndex) {
        Task editedTask = createTaskFromInput();
        taskHandler.updateTask(selectedIndex, editedTask);
        ioHandler.output(Output.Info.YourTaskHasBeenAdded);
    }

    private Task createTaskFromInput() {
        ioHandler.output(Output.Info.WriteTheTitleOfTask);
        String title = ioHandler.getInput();

        ioHandler.output(Output.Info.WriteTheDueDateOfTask);
        String dueDate = ioHandler.getInput();

        ioHandler.output(Output.Info.WriteTheProjectNameOfTask);
        String projectName = ioHandler.getInput();

        return new Task(title, dueDate, projectName);
    }

    private void printTasks() {
        ArrayList<Task> taskList = taskHandler.getTasks();

        if (TaskValidator.taskListIsNotEmpty(taskList)){
            for (int i = 0; i < taskList.size(); i++) {
                ioHandler.output(i + ") " + taskList.get(i).toString());
            }
        } else {
            ioHandler.output(Output.Errors.NoTasksWarning);
        }
    }

    private void printMainMenu() {
        ioHandler.output("Welcome to the to do list application");
        ioHandler.output(String.format("You have %s tasks to do and %s tasks are done", taskHandler.numberOfTasks(), taskHandler.numberOfCompletedTasks()));
        ioHandler.output("Pick an option: ");
        ioHandler.output(">> (1) Show Task List ");
        ioHandler.output(">> (2) Add New Task");
        ioHandler.output(">> (3) Edit Task (update, mark as done, remove)");
        ioHandler.output(">> (4) Save and Quit");
    }
}