package com.revature.phuflix.ui;

import com.revature.phuflix.models.User;
import com.revature.phuflix.services.UserService;
import sun.java2d.pipe.hw.AccelDeviceEventListener;

import java.util.Scanner;
import java.util.UUID;

public class StartMenu implements IMenu {

    private final UserService userService;
    private final Scanner scan = new Scanner(System.in);

    public StartMenu(UserService userService){
        this.userService = userService;
    }

    @Override
    public void start() {
        // messages
        displayWelcomeMsg();
        displayOptions();

        // user input
        System.out.println("Enter: ");
        String input = scan.nextLine();

        while(select1(input)){
            System.out.println("Try again");
            displayOptions();
            System.out.println("Enter: ");
            input = scan.nextLine();
        }

    }

    private void displayWelcomeMsg(){
        System.out.println("\nWelcome to PhuFlix");
    }

    private void displayOptions(){
        System.out.println("\n[1] Login");
        System.out.println("[2] Sign Up");
        System.out.println("[x] Exit\n");
    }

    private void login(){
        // inplament
        System.out.println("Login");
    }

    private void signup() {
        String username;
        String password;
        String confirm;

        completeExit:
        {
            while (true) {
                System.out.println("Creating Account...");

                System.out.println("Username: ");
                username = scan.nextLine();

                while (!(userService.isValidUsername(username))) {
                    System.out.println("Invalid username. Username needs to be 8-20 characters long.");
                    System.out.println("Username: ");
                    username = scan.nextLine();
                }

                do {
                    System.out.println("Password: ");
                    password = scan.nextLine();

                    while (!(userService.isValidPassword(password))) {
                        System.out.println("Invalid password.");
                        System.out.println("Password: ");
                        password = scan.nextLine();
                    }

                    System.out.print("\nRe enter password again: ");
                    confirm = scan.nextLine();

                    if (!(password.equals(confirm))) {
                        System.out.println("Passwords do not match");
                    }
                } while (!(password.equals(confirm)));

                confirmExit:
                {
                    while (true) {
                    System.out.println("\nPlease confirm your credentials (y/n)");
                    System.out.println("\nUsername: " + username);
                    System.out.println("Password: " + password);

                    System.out.print("\nEnter: ");
                    String input = scan.nextLine();

                    switch(input) {
                        case "y":
                            User user = new User(UUID.randomUUID().toString(), username, password, "DEFAULT");

                            new MainMenu(user).start();
                            break completeExit;
                        case "n":
                            break confirmExit;
                        default:
                            System.out.println("Invalid Input");
                            break;
                    }
                    }
                }
            }
        }
    }

    private boolean select1(String input){

        switch (input){
            case "1":
                login();
                return false;
            case "2":
                signup();
                return false;
            case "x":
                System.out.println("Goodbye.");
                return false;
            default:
                System.out.println("Invalid input.");
                return true;
        }
    }
}
