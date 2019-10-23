package com.company;

import com.company.constants.MenuOptions;
import com.company.constants.Output;
import com.company.entities.Task;
import com.company.enums.Status;
import com.company.utilities.FileHelper;
import com.company.utilities.IOHandler;
import com.company.utilities.TaskSorter;
import com.company.utilities.TaskValidator;

import java.util.ArrayList;

public class UserInterface {
    private final FileHelper fileHelper;
    private final IOHandler ioHandler;
    private final TaskSorter taskSorter;
    private ArrayList<Task> taskList;

    public UserInterface(FileHelper fileHelper, TaskSorter taskSorter, IOHandler ioHandler) {
        this.fileHelper = fileHelper;
        this.taskSorter = taskSorter;
        this.ioHandler = ioHandler;
        taskList = fileHelper.getTasksFromFile();
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
                    fileHelper.saveTasksToFile(taskList);
                    isRunning = false;
                    break;
                default:
                    ioHandler.output(Output.Errors.InvalidOption);
            }
        }
    }

    private void showTaskList() {
        printTasks();
        if (!TaskValidator.taskListIsNotEmpty(taskList)) return;

        ioHandler.output(Output.Info.SortByDateOrProject);
        boolean isRunning = true;
        while (isRunning) {
            String selectedValue = ioHandler.getInput().toUpperCase();
            switch (selectedValue.toUpperCase()) {
                case MenuOptions.OptionDate:
                    this.taskList = taskSorter.sortByDueDate(this.taskList);
                    showTaskList();
                    isRunning = false;
                    break;
                case MenuOptions.OptionProject:
                    this.taskList = taskSorter.sortByProject(this.taskList);
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
        if (!TaskValidator.taskListIsNotEmpty(taskList)) {
            ioHandler.output(Output.Errors.NoTasksWarning);
            return;
        }

        printTasks();
        ioHandler.output(Output.Info.ChooseTaskToEdit);
        String selectedTask = ioHandler.getInput();
        if(TaskValidator.indexIsIntegerAndWithinBoundsOfArray(selectedTask, taskList.size())){
            startEditMode(Integer.parseInt(selectedTask));
        } else {
            ioHandler.output(Output.Errors.InvalidOption);
        }
    }

    private void addTask() {
        taskList.add(createTaskFromInput());
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

    private int numberOfCompletedTasks() {
        return (int) taskList.stream().filter(x -> x.getStatus() == Status.Finished).count();
    }

    private int numberOfTasks() {
        return taskList.size();
    }

    private void startEditMode(int selectedTaskAsNumber) {
        ioHandler.output(Output.Info.EditOptions);
        String selectedValue = ioHandler.getInput().toUpperCase();
        switch (selectedValue) {
            case MenuOptions.OptionUpdate:
                updateTask(selectedTaskAsNumber);
                break;
            case MenuOptions.OptionMarkDone:
                taskList.get(selectedTaskAsNumber).setStatus(Status.Finished);
                break;
            case MenuOptions.OptionRemove:
                taskList.remove(selectedTaskAsNumber);
                break;
            default:
                ioHandler.output(Output.Errors.InvalidOption);
                editTask();
                break;
        }
    }

    private void updateTask(int selectedIndex) {
        Task editedTask = createTaskFromInput();
        taskList.set(selectedIndex, editedTask);
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
        ioHandler.output(String.format("You have %s tasks to do and %s tasks are done", numberOfTasks(), numberOfCompletedTasks()));
        ioHandler.output("Pick an option: ");
        ioHandler.output(">> (1) Show Task List ");
        ioHandler.output(">> (2) Add New Task");
        ioHandler.output(">> (3) Edit Task (update, mark as done, remove)");
        ioHandler.output(">> (4) Save and Quit");
    }
}