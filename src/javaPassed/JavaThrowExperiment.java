/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaPassed;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author morrejo_sd2023
 */
public class JavaThrowExperiment {

    public static void main(String args[]) throws IOException, PasswordException {
        AccountInterface ai = new AccountInterface();
        PersonalInterface pi = new PersonalInterface();
        CourseInterface ci = new CourseInterface();
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);
        Scanner input4 = new Scanner(System.in);
        Scanner input5 = new Scanner(System.in);
        boolean exit = false;

        while (exit == false) {

            System.out.println("\t\t=========MENU=========");
            System.out.println("\tPress 1 to Create\n\tPress 2 to Update\n\tPress 3 to Delete\n\tPress 4 to Save\n\tPress 5 to Search\n\tPress 6 to Exit");
            System.out.print("Enter Choice : ");
            String choice1 = input1.next();
            switch (choice1) {
                case "1":
                    ai.create();
                    System.out.print("Do you want to add personal information (y/n) ? ");
                    String choice4 = input2.next();
                    switch (choice4) {
                        case "y":
                            pi.create();
                            while (true) {
                                System.out.print("Do you want to add courses (y/n) ? ");
                                String choice5 = input3.next();
                                if ("y".equals(choice5)) {
                                    ci.create();
                                } else {
                                    break;
                                }
                            }
                        case "n":
                            break;
                    }
                    break;
                case "2":
                    ai.retrieve();
                    pi.retrieve();
                    ci.retrieve();
                    break;
                case "3":
                    try {
                        System.out.println("\n--- UPDATE ---");
                        System.out.print("Account ID :");
                        int choice6 = input2.nextInt();
                        pi.update(choice6);
                        while (true) {
                            System.out.print("Do you want to update courses (y/n) ? ");
                            String choice8 = input5.next();
                            if ("y".equals(choice8)) {
                                ci.update(choice6);
                            } else {
                                break;
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Mismatch input.");
                    }
                    break;
                case "4":
                    try {
                        System.out.println("\n--- DELETE ---");
                        System.out.print("Account ID :");
                        int choice2 = input4.nextInt();
                        pi.delete(choice2);
                        ci.delete(choice2);
                    } catch (InputMismatchException e) {
                        System.out.println("Mismatch input.");
                    }
                    break;
                case "5":
                    System.out.println("\n--- SAVE ---");
                    ai.save();
                    pi.save();
                    ci.save();
                    break;
                case "6":
                    try {
                        System.out.println("\n--- SEARCH ---");
                        System.out.print("Account ID :");
                        int choice3 = input3.nextInt();
                        ai.search(choice3);
                        pi.search(choice3);
                        ci.search(choice3);
                    } catch (InputMismatchException e) {
                        System.out.println("Mismatch input.");
                    }
                    break;
                case "e":
                    exit = true;
                    break;
            }

        }
    }

}
