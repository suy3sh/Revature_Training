package com.p0_arcade.util;

import java.util.Scanner;

public class InputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static Integer getIntInput(String prompt){
        while (true){
            System.out.print(prompt);
            try{
                return Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Invalid Input. Please enter a number.");
            }
        }
    }

    public static String getStringInput(String prompt){
        while (true){
            System.out.print(prompt);
            try{
                return scanner.nextLine();
            }catch(RuntimeException e){
                System.out.println("Invalid Input. Please enter a string.");
            }
        }
    }

    public static char getCharInput(String prompt){
        while (true){
            System.out.print(prompt);
            String input = scanner.nextLine();

            if (input.length() == 1) {
                return input.charAt(0);
            }

            System.out.println("Invalid input. Please enter exactly one character.");
        }
    }
}