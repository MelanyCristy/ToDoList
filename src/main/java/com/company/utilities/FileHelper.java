package com.company.utilities;

import com.company.constants.Paths;
import com.company.entities.Task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper {


    public ArrayList<Task> getTasksFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream(Paths.PathToTaskFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Task> tasks = (ArrayList<Task>) objectInputStream.readObject();
            objectInputStream.close();

            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Paths.PathToTaskFile);
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(tasks);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}