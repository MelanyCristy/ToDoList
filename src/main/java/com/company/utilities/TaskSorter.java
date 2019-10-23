package com.company.utilities;

import com.company.entities.Task;

import java.util.ArrayList;
import java.util.Comparator;

public class TaskSorter {
    public ArrayList<Task> sortByProject(ArrayList<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) return new ArrayList<>();

        tasks.sort((t1, t2) -> t1.getProject().compareToIgnoreCase(t2.getProject()));

        return tasks;
    }

    public ArrayList<Task> sortByDueDate(ArrayList<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) return new ArrayList<>();

        tasks.sort(Comparator.comparing(Task::getDueDate));

        return tasks;
    }
}