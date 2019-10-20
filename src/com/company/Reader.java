package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Reader {

    public void save(ArrayList<Task> tasks){
        try {
            FileOutputStream f = new FileOutputStream(new File("myTasks.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(tasks);

            o.close();
            f.close();

            FileInputStream fi = new FileInputStream(new File("myTasks.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            Task t = (Task) oi.readObject();

            System.out.println(t.toString());

            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}