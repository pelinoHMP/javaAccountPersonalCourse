/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaPassed;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Objects.hash;
import java.util.Scanner;
import static javaPassed.Account.account_id;

/**
 *
 * @author morrejo_sd2023
 */
public class AccountInterface {

    ArrayList<Account> a;
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    Scanner input3 = new Scanner(System.in);

    public AccountInterface() {
        a = new ArrayList();
    }

    public void retrieve() throws IOException {
        System.out.println("--- RETRIEVE ---");
        System.out.println("\n\t\t\t*** Accounts ***");
        a = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\account.txt"))) {
            String inside;
            while ((inside = reader.readLine()) != null) {
                System.out.println(inside);
                String[] partsA = inside.split("\t\t");
                a.add(new Account(Integer.parseInt(partsA[0]), partsA[1], partsA[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public void create() throws IOException, PasswordException {
        String username, password, confirm;
        System.out.print("\n--- CREATE ---\nEnter username : ");
        username = input1.next();
        while (!Check.isString(username)) {
            System.out.println("Username containing all number.");
            create();
        }
        while (true) {
            try {
                System.out.print("Enter password : ");
                password = input1.next();
                if (password.length() >= 8) {
                    break;
                } else {
                    throw new PasswordException("Password too short.");
                }
            } catch (PasswordException ex) {
                System.out.println(ex);
            }
        }
        while (true) {
            try {
                System.out.print("Re-enter password : ");
                confirm = input2.next();
                if (confirm == null ? password == null : confirm.equals(password)) {
                    break;
                } else {
                    throw new PasswordException("Password mismatch.");
                }
            } catch (PasswordException ex) {
                System.out.println(ex);
            }
        }
        if (a.isEmpty()) {
            a.add(new Account(1, username, password));
        }
        else {
            a.add(new Account(a.get(a.size()-1).getAcc_id()+1, username, password));
            account_id = a.get(a.size()-1).getAcc_id();
        }
    }

    public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\account.txt"))) {
            String str;
            writer.flush();
            for (int i = 0; i < a.size(); i++) {
                str = a.get(i).getAcc_id() + "\t\t" + a.get(i).getUsername() + "\t\t" + a.get(i).getPassword();
                writer.write(str);
                writer.newLine();
            }
        } catch (Exception ex) {
            System.out.println("File not found.");
        }
    }

    public void search(int acc_id) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getAcc_id() == acc_id) {
                System.out.println(a.get(i).getAcc_id() + "\t" + a.get(i).getUsername() + "\t" + a.get(i).getPassword());
            }
        }
    }

}
