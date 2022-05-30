package com.revature.phuflix.ui;

import com.revature.phuflix.daos.*;
import com.revature.phuflix.models.User;
import com.revature.phuflix.services.*;
import com.revature.phuflix.util.custom_exception.UserInputException;
import sun.java2d.pipe.hw.AccelDeviceEventListener;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
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

        displayTextBanner("Start Menu");

        // user input
        while(select1(displayOptions())){
            displayWelcomeMsg();
            displayTextBanner("Start Menu");
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

    private String displayOptions(){
        String input;
        System.out.println("                     ");
        displayTextMiddle("[1] Login");
        displayTextMiddle("[2] Sign Up");
        displayTextMiddle("[x] Exit");
        System.out.println(" ");
        displayLine();

        displayTextMiddle("Enter:");
        input = scan.nextLine();
        return input;
    }

    private void login(){
        String username;
        String password;
        displayTextMiddle("Logging in...");
        // line 1
        System.out.println(" ");
        //line 2
        displayTextBanner("Please enter Username:");
        // line 5
        displayBlankLine(13);
        // line 18

        displayLine();
        // line 19
        displayTextMiddle("Enter:");
        // line 20

        username = scan.nextLine();
        newPage();

        displayTextBanner("Please enter Password:");
        // line 3
        displayBlankLine(7);
        // line 10
        displayTextMiddle("Username: " + username);
        // line 11
        displayBlankLine(7);
        // line 18
        displayLine();
        // line 19
        displayTextMiddle("Enter");
        // line 20
        password = scan.nextLine();
        try {
            User user = userService.login(username,password);
            // Admin login
            System.out.println(user.getRole().equals("ADMIN"));
            newPage();
            if (user.getRole().equals("ADMIN")){
                new AdminMenu(user, new PhuboxService(new PhuboxDAO()), new InventoryService(new InventoryDAO()),
                        new MovieService(new MovieDAO())).start();
            }
            // normal login
            else{new MainMenu(user, new MovieService(new MovieDAO()),
                    new ReviewService(new ReviewDAO()), new PhuboxService(new PhuboxDAO()),
                    new InventoryService(new InventoryDAO()), new OrderService(new OrderDAO())  ).start();}
        }catch (UserInputException e){
            newPage();
            displayTextBanner(e.getMessage());
            displayBlankLine(15);
            displayLine();
            displayTextMiddle("Try again? (y/n)");
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

                                new MainMenu(user,new MovieService(new MovieDAO()),
                                        new ReviewService(new ReviewDAO()), new PhuboxService(new PhuboxDAO()),
                                new InventoryService(new InventoryDAO()), new OrderService(new OrderDAO())    ).start();
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
