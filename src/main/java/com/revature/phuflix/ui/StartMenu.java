package com.revature.phuflix.ui;

import com.revature.phuflix.daos.PhuboxDAO;
import com.revature.phuflix.models.User;
import com.revature.phuflix.services.PhuboxService;
import com.revature.phuflix.services.UserService;
import com.revature.phuflix.util.custom_exception.UserInputException;
import sun.java2d.pipe.hw.AccelDeviceEventListener;

import java.security.SecureRandom;
import java.util.Scanner;
import java.util.UUID;

public class StartMenu extends IMenu {

    private final UserService userService;
    private final Scanner scan = new Scanner(System.in);

    public StartMenu(UserService userService){
        this.userService = userService;
    }

    @Override
    public void start() {
        // messages
        displayWelcomeMsg();


        // user input
        while(select1(displayOptions())){
            displayWelcomeMsg();
        }

    }

    private void displayWelcomeMsg(){
        System.out.println("\n" +
                "    ██████╗ ██╗  ██╗██╗   ██╗███████╗██╗     ██╗██╗  ██╗          \n" +
                "    ██╔══██╗██║  ██║██║   ██║██╔════╝██║     ██║╚██╗██╔╝          \n" +
                "    ██████╔╝███████║██║   ██║█████╗  ██║     ██║ ╚███╔╝           \n" +
                "    ██╔═══╝ ██╔══██║██║   ██║██╔══╝  ██║     ██║ ██╔██╗           \n" +
                "    ██║     ██║  ██║╚██████╔╝██║     ███████╗██║██╔╝ ██╗          \n" +
                "    ╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚══════╝╚═╝╚═╝  ╚═╝       \n" +
                "                                                       ");
    }


    private void login(){
        String username;
        String password;
        System.out.println("Logging in...");
        System.out.println("Please enter Username: \n");
        username = scan.nextLine();
        newPage();
        System.out.println("Please enter Password: \n");
        password = scan.nextLine();
        try {
            User user = userService.login(username,password);
            // Admin login
            System.out.println(user.getRole().equals("ADMIN"));
            newPage();
            if (user.getRole().equals("ADMIN")){
                new AdminMenu(user, new PhuboxService(new PhuboxDAO())).start();
            }
            // normal login
            else{new MainMenu(user).start();}
        }catch (UserInputException e){
            newPage();
            System.out.println(e.getMessage());
            System.out.println("Try again? (y/n)");
            String input = scan.nextLine();
            if ("y".equals(input)) {
                newPage();
                login();
            }
            newPage();

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
                newPage();

                while (true) {
                    try {
                        if (userService.isValidUsername(username)){
                            if (userService.isUniqueUsername(username)) {
                                break;
                            }
                        }
                    } catch (UserInputException e) {
                        newPage();
                        System.out.println(e.getMessage());}
                    System.out.println("Username: ");
                    username = scan.nextLine();
                }
                newPage();
                while (true){
                    System.out.println("Password: ");
                    password = scan.nextLine();
                    try{
                        if (userService.isValidPassword(password)){
                            newPage();
                            System.out.print("Re enter password again: ");
                            confirm = scan.nextLine();
                            if(password.equals(confirm)){break;}
                        }
                    }catch (UserInputException e){
                        newPage();
                        System.out.println("Invalid input. Try Again");
                    }
                    newPage();
                    System.out.println("Passwords did not match try again.");
                }

                confirmExit:
                {
                    while (true) {
                        newPage();
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
                            newPage();
                            break confirmExit;
                        default:
                            newPage();
                            System.out.println("Invalid Input");
                            break;
                    }
                    }
                }
            }
        }
    }

    private String displayOptions(){
        String input;
        System.out.println("                     ");
        System.out.println("                     [1] Login");
        System.out.println("                     [2] Sign Up");
        System.out.println("                     [x] Exit\n");
        displayLine();

        System.out.println("Enter: ");
        input = scan.nextLine();
        return input;
    }

    private boolean select1(String input){

        switch (input){
            case "1":
                newPage();
                login();
                return true;
            case "2":
                newPage();
                signup();
                return true;
            case "x":
                newPage();
                System.out.println("Goodbye.");
                return false;
            default:
                newPage();
                System.out.println("Invalid input.");
                return true;
        }
    }
}
