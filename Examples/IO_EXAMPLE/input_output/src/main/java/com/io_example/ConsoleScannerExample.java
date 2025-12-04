package com.io_example;

import java.util.Scanner;

public class ConsoleScannerExample {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name");
        String name = scanner.nextLine();

        System.out.println("Enter your age:");
        int age = scanner.nextInt();

        System.out.println("Enter your phone_number: ");
        double number = scanner.nextDouble();

        System.out.println(name);
        System.out.println(age);
        System.out.println(number);

        scanner.nextLine();

        System.out.println("Enter your city:");
        String city = scanner.nextLine();

        System.out.println(city);

        scanner.close();
    }
}
