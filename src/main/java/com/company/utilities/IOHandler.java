package com.company.utilities;

import java.util.Scanner;

public class IOHandler {
    private final Scanner scanner;

    public IOHandler(){
        scanner = new Scanner(System.in);
    }

    public String getInput(){
        return scanner.nextLine();
    }

    public void output(String message) {
        System.out.println(message);
    }
}
