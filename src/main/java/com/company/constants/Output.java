package com.company.constants;

public class Output {
    public static class Errors {
        public static final String NoTasksWarning = "You have no tasks in your task list";
        public static final String InvalidOption = "Invalid option.";
        public static final String CouldNotCreateTask = "Could not create task, please try again.";
    }

    public static class Info {
        public static final String AddMoreTasks = ">>Do you want to add one task more? Press Y for yes and N for no.";
        public static final String WriteTheTitleOfTask = ">>Write the title of the task:";
        public static final String WriteTheDueDateOfTask = ">>Write the due date of the task (yyyy-MM-dd):";
        public static final String WriteTheProjectNameOfTask = ">>Write the project name of the task";
        public static final String YourTaskHasBeenAdded = "Your task has been added.";
        public static final String ChooseTaskToEdit = "Choose task to edit (by index): ";
        public static final String SortByDateOrProject = "Sort by (d)ate or (p)roject, or (c)ancel?";
        public static final String EditOptions = "Do you want to (u)pdate, (m)ark as done or (r)emove the task";
    }
}
