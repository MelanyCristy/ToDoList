package com.company.utilities;

import com.company.UserInterface;
import com.company.constants.MenuConstants;
import com.company.constants.MessageConstants;
import com.company.constants.RegularExpressions;
import com.company.entities.Task;
import com.company.enums.Status;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskHandler {
    private final Scanner scanner;
    private final FileHelper fileHelper;
    private final TaskSorter taskSorter;
    private ArrayList<Task> taskList;

    public TaskHandler() {
        scanner = new Scanner(System.in);
        fileHelper = new FileHelper();
        taskSorter = new TaskSorter();
        taskList = fileHelper.getTasksFromFile();
    }

    public void saveAndQuit() {
        fileHelper.saveTasksToFile(taskList);
    }

    private void printTasks() {
        if (taskList == null || taskList.isEmpty()) {
            UserInterface.writeError(MessageConstants.Errors.NoTasksToBeShown);
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                UserInterface.writeInfo(i + ") " + taskList.get(i).toString());
            }
        }
    }

    public void showTaskList() {
        printTasks();
        UserInterface.writeInfo(MessageConstants.Info.SortByDateOrProject);
        boolean isRunning = true;
        while (isRunning) {
            String selectedValue = scanner.nextLine().toUpperCase();
            switch (selectedValue.toUpperCase()) {
                case MenuConstants.OptionDate:
                    this.taskList = taskSorter.sortByDueDate(this.taskList);
                    showTaskList();
                    isRunning = false;
                    break;
                case MenuConstants.OptionProject:
                    this.taskList = taskSorter.sortByProject(this.taskList);
                    showTaskList();
                    isRunning = false;
                    break;
                case MenuConstants.OptionCancel:
                    isRunning = false;
                    break;
                default:
                    UserInterface.writeError(MessageConstants.Errors.InvalidOption);
                    break;
            }
        }
    }

    public void editTask() {
        if (taskList == null || taskList.isEmpty()) {
            UserInterface.writeInfo(MessageConstants.Errors.NoTasksToBeEdited);
            return;
        }

        printTasks();
        UserInterface.writeInfo(MessageConstants.Info.ChooseTaskToEdit);
        String selectedTask = scanner.nextLine();
        int selectedTaskAsNumber = validateSelectedTask(selectedTask);

        startEditMode(selectedTaskAsNumber);
    }

    private int validateSelectedTask(String selectedTask) {
        if (!selectedTask.matches(RegularExpressions.RegexStringIsOfTypeInteger)) {
            UserInterface.writeError(MessageConstants.Errors.InvalidOption);
            editTask();
        }

        int selectedTaskAsNumber = Integer.parseInt(selectedTask);

        if (selectedTaskAsNumber < 0 || selectedTaskAsNumber > taskList.size()) {
            UserInterface.writeError(MessageConstants.Errors.InvalidOption);
            editTask();
        }

        return selectedTaskAsNumber;
    }

    private void startEditMode(int selectedTaskAsNumber) {
        UserInterface.writeInfo(MessageConstants.Info.EditOptions);
        String selectedValue = scanner.nextLine().toUpperCase();
        switch (selectedValue) {
            case MenuConstants.OptionUpdate:
                updateTask(selectedTaskAsNumber);
                break;
            case MenuConstants.OptionMarkDone:
                markTaskDone(selectedTaskAsNumber);
                break;
            case MenuConstants.OptionRemove:
                removeTask(selectedTaskAsNumber);
                break;
            default:
                UserInterface.writeError(MessageConstants.Errors.InvalidOption);
                editTask();
                break;
        }
    }

    private void updateTask(int selectedIndex) {
        Task editedTask = null;
        try {
            editedTask = createTaskFromInput();
        } catch (ParseException e) {
            UserInterface.writeError(MessageConstants.Errors.CouldNotCreateTask);
            editTask();
        }

        taskList.set(selectedIndex, editedTask);
        UserInterface.writeInfo(MessageConstants.Info.YourTaskHasBeenAdded);
    }

    private void markTaskDone(int selectedIndex) {
        taskList.get(selectedIndex).setStatus(Status.Finished);
    }

    private void removeTask(int selectedIndex) {
        taskList.remove(selectedIndex);
    }

    private Task createTaskFromInput() throws ParseException {
        UserInterface.writeInfo(MessageConstants.Info.WriteTheTitleOfTask);
        String title = scanner.nextLine();

        UserInterface.writeInfo(MessageConstants.Info.WriteTheDueDateOfTask);
        String dueDate = scanner.nextLine();

        UserInterface.writeInfo(MessageConstants.Info.WriteTheProjectNameOfTask);
        String projectName = scanner.nextLine();

        return new Task(title, dueDate, projectName);
    }

    public ArrayList<Task> addTask() {
        Task newTask = null;
        try {
            newTask = createTaskFromInput();
        } catch (ParseException e) {
            UserInterface.writeError(MessageConstants.Errors.CouldNotCreateTask);
            addTask();
        }

        taskList.add(newTask);
        UserInterface.writeInfo(MessageConstants.Info.YourTaskHasBeenAdded);

        boolean isRunning = true;
        while (isRunning) {
            UserInterface.writeInfo(MessageConstants.Info.AddMoreTasks);
            String selectedValue = scanner.nextLine().toUpperCase();
            switch (selectedValue) {
                case MenuConstants.OptionYes:
                    addTask();
                    break;
                case MenuConstants.OptionNo:
                    isRunning = false;
                    break;
                default:
                    UserInterface.writeError(MessageConstants.Errors.InvalidOption);
                    break;
            }
        }
        return null;
    }

    public int numberOfCompletedTasks() {
        return (int) taskList.stream().filter(x -> x.getStatus() == Status.Finished).count();
    }

    public int numberOfTasks() {
        return taskList.size();
    }
}