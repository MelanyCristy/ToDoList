package com.company;
import java.io.Serializable;


class Task implements Serializable {

     private static final long serialVersionUID = 1L;
     private String description;
     private String title;

     Task(String description,String title){
         this.description = description;
         this.title = title;
     }

     public String getDescription() {
         return description;
     }
     public String getTitle() {
         return title;
     }

     @Override
     public String toString()
     {
         return title + " --- " + description ;

        //return "Description:" + description  + "\nTitle: " + title;
    }

}
