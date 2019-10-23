package com.company.entities;

import com.company.constants.DateFormats;
import com.company.enums.Status;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable {
    private final LocalDate dueDate;
    private final String project;
    private final String title;
    private Status status;

    public Task(String title, String dueDate, String project) {
        this.title = title;
        this.status = Status.NotStarted;
        this.dueDate = parseDate(dueDate);
        this.project = project;
    }

    private LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DateFormats.DateShortFormat));
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    private String getDueDateAsString() {
        return dueDate.toString();
    }

    public String getProject() {
        return project;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Title: %s. Due date: %s. Status: %s. Project: %s.", title, getDueDateAsString(), status, project);
    }
}