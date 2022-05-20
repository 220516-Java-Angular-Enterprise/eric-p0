package com.revature.phuflix.ui;

import com.revature.phuflix.models.User;
import com.revature.phuflix.services.UserService;
import com.revature.phuflix.util.custom_exception.UserInputException;
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
        String username;
        String password;
        System.out.println("Logging in...");
        System.out.println("Please enter Username: \n");
        username = scan.nextLine();
        System.out.println("Please enter Password: \n");
        password = scan.nextLine();
        try {
            User user = userService.login(username,password);
            new MainMenu(user).start();
        }catch (UserInputException e){
            System.out.println(e.getMessage());
            System.out.println("Try again? (y)");
            String input = scan.nextLine();
            if ("y".equals(input)) {
                login();
            }
        }

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

                while (true) {
                    try {
                        if (userService.isValidUsername(username)){
                            if (userService.isUniqueUsername(username)) {
                                break;
                            }
                        }
                    } catch (UserInputException e) {System.out.println(e.getMessage());}
                    System.out.println("Username: ");
                    username = scan.nextLine();
                }

                while (true){

                    System.out.println("Password: ");
                    password = scan.nextLine();
                    try{
                        if (userService.isValidPassword(password)){
                            System.out.print("\nRe enter password again: ");
                            confirm = scan.nextLine();
                            if(password.equals(confirm)){break;}
                        }
                    }catch (UserInputException e){System.out.println("Invalid input. Try Again");}

                }

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
                            userService.register(user);

                            new MainMenu(user).start();
                            // communicates with daos to save in database
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
