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
        // build page
        String page = "";
        page += displayWelcomeMsg1();
        // line 7
        page += displayBlankLine1(1);
        //line 8

        page += displayTextBanner1("Start Menu");
        // line 11
        page += displayOptions1();
        //line 20

        System.out.println(page);

        String input;
        input = scan.nextLine();

        // user input
        while(select1(input)){
            System.out.println(page);
            input = scan.nextLine();
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

    private String displayWelcomeMsg1(){
        // line = 7
        String page="";
        page += displayBlankLine1(1);
        page += displayTextMiddle1("██████╗ ██╗  ██╗██╗   ██╗███████╗██╗     ██╗██╗  ██╗");
        page += displayTextMiddle1("██╔══██╗██║  ██║██║   ██║██╔════╝██║     ██║╚██╗██╔╝");
        page += displayTextMiddle1("██████╔╝███████║██║   ██║█████╗  ██║     ██║ ╚███╔  ");
        page += displayTextMiddle1("██╔═══╝ ██╔══██║██║   ██║██╔══╝  ██║     ██║ ██╔██╗ ");
        page += displayTextMiddle1("██║     ██║  ██║╚██████╔╝██║     ███████╗██║██╔╝ ██╗");
        page += displayTextMiddle1("╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚══════╝╚═╝╚═╝  ╚═╝");

        return page;

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

        String usernamePage = "";

        System.out.print("Logging in...");
        // line 1

        usernamePage += displayTextBanner1("Username");
        // line 4

        usernamePage += displayBlankLine1(7);
        //line 10

        usernamePage += displayTextMiddle1("Please Enter You Username: ");
        // line 11

        usernamePage += displayBlankLine1(7);
        // line 18

        usernamePage += displayLine1();
        // line 19
        usernamePage += displayTextMiddle1("Enter (type x to exit to start screen):");
        // line 20

        System.out.println(usernamePage);
        username = scan.nextLine();
        if (username.equals("x")){
            System.out.println("Back to start menu");
            return;
        }

        String passwordPage="";

        passwordPage += displayTextBanner1("Please enter Password:");
        // line 3
        passwordPage += displayBlankLine1(7);
        // line 10
        passwordPage += displayTextMiddle1("Username: " + username);
        // line 11
        passwordPage += displayBlankLine1(7);
        // line 18
        passwordPage += displayLine1();
        // line 19
        passwordPage += displayTextMiddle1("Enter (type x to exit to start screen)");
        // line 20
        System.out.println("Password");
        System.out.println(passwordPage);
        password = scan.nextLine();
        if (password.equals("x")){
            System.out.println("Back to start menu");
            return;
        }

        try {
            User user = userService.login(username,password);
            // Admin login
            System.out.println(user.getRole().equals("ADMIN"));
            newPage();
            if (user.getRole().equals("ADMIN")){
                new AdminMenu(user, new PhuboxService(new PhuboxDAO()), new InventoryService(new InventoryDAO()),
                        new MovieService(new MovieDAO()), new UserService(new UserDAO()), new OrderService(new OrderDAO())).start();
            }
            // normal login
            else{new MainMenu(user, new MovieService(new MovieDAO()),
                    new ReviewService(new ReviewDAO()), new PhuboxService(new PhuboxDAO()),
                    new InventoryService(new InventoryDAO()), new OrderService(new OrderDAO())  ).start();}
        }catch (UserInputException e){

            String retryPage="";


            retryPage += displayTextBanner1("FAILED");
            // line 3
            retryPage += displayBlankLine1(6);
            // line 19
            retryPage += displayTextMiddle1("INVALID INFO:");
            // line 10
            retryPage += displayTextMiddle1("Username: " + username);
            // line 11
            retryPage += displayTextMiddle1("Password: " + password);
            // line 12
            retryPage += displayBlankLine1(6);
            // line 18
            retryPage += displayLine1();
            // line 19
            retryPage += displayTextMiddle1("Try again? (y/n)");
            // line 20

            System.out.println("WRONG COMBO");


            while(true) {
                System.out.println(retryPage);
                String input = scan.nextLine();

                if ("y".equals(input)) {
                    newPage();
                    login();
                } else if(input.equals("n")){
                    System.out.println("Back to start menu");
                    break;
                }else{
                    System.out.println("Invalid Input");
                }
            }

        }

    }

    private String signUpPg1(){
        // line 19
        String userNamePage = "";

        userNamePage += displayTextBanner1("Creating Account...");
        // line 4
        userNamePage += displayBlankLine1(6);
        // line 10
        userNamePage += displayTextMiddle1("username is 8-20 characters long");
        // line 11
        userNamePage += displayTextMiddle1("no _ or . at the beginning or end");
        //line 12
        userNamePage += displayTextMiddle1("no __ or _. or ._ or .. inside");
        // line 13
        userNamePage += displayBlankLine1(6);
        // line 18
        userNamePage += displayLine1();
        // line 19
        userNamePage += displayTextMiddle1("Enter (type x to exit to start menu):");
        //line 20
        return  userNamePage;
    }

    private String signUpPg2(){
        String passwordPage = "";
        passwordPage += displayTextBanner1("Creating Account...");
        // line 4
        passwordPage += displayBlankLine1(6);
        // line 10
        passwordPage += displayTextMiddle1("Password minimum eight characters");
        // line 11
        passwordPage += displayTextMiddle1("at least one letter, one number");
        //line 12
        passwordPage += displayTextMiddle1("and one special character");
        // line 13
        passwordPage += displayBlankLine1(6);
        // line 18
        passwordPage += displayLine1();
        // line 19
        passwordPage += displayTextMiddle1("Enter (type x to exit to start menu):");
        //line 20
        return passwordPage;

    }

    private String signUpPg3(String username, String password){
        String passwordPage = "";
        passwordPage += displayTextBanner1("Creating Account...");
        // line 4
        passwordPage += displayBlankLine1(6);
        // line 10
        passwordPage += displayTextMiddle1("Please Confrim your password");
        // line 11
        passwordPage += displayTextMiddle1("Username: " + username);
        //line 12

        String hidden = "";

        for (char c: password.toCharArray()){
            hidden += "*";
        }

        passwordPage += displayTextMiddle1("Password: " + hidden);
        // line 13
        passwordPage += displayBlankLine1(6);
        // line 18
        passwordPage += displayLine1();
        // line 19
        passwordPage += displayTextMiddle1("Enter (type x to exit to start menu):");
        //line 20
        return passwordPage;

    }

    private String signUpPg4(String username, String password){
        String passwordPage = "";
        passwordPage += displayTextBanner1("Creating Account...");
        // line 4
        passwordPage += displayBlankLine1(6);
        // line 10
        passwordPage += displayTextMiddle1("Please Confrim your password");
        // line 11
        passwordPage += displayTextMiddle1("Username: " + username);
        //line 12

        passwordPage += displayTextMiddle1("Password: " + password);
        // line 13
        passwordPage += displayBlankLine1(6);
        // line 18
        passwordPage += displayLine1();
        // line 19
        passwordPage += displayTextMiddle1("Enter y to confirm (type x to exit to start menu):");
        //line 20
        return passwordPage;

    }

    private void signup() {
        String username;
        String password;
        String confirm;

        complete:
        {

            start:
            {
                System.out.print("Signing up");

                while (true) {

                    // first page
                    System.out.println(signUpPg1());

                    username = scan.nextLine();
                    if (username.equals("x")){break complete;}

                    try {
                        if (userService.isValidUsername(username)) {
                            if (userService.isUniqueUsername(username)) {
                                System.out.print("Successful!");
                                break;
                            }
                        }
                    } catch (UserInputException e) {
                        // either failed
                        newPage();
                        System.out.print(e.getMessage());
                    }

                }

                // here if you pass

                while (true) {
                    System.out.println(signUpPg2());
                    password = scan.nextLine();
                    if (password.equals("x")){break complete;}
                    try {
                        if (userService.isValidPassword(password)) {
                            System.out.println(signUpPg3(username, password));
                            confirm = scan.nextLine();
                            if (confirm.equals("x")){break complete;}
                            if (password.equals(confirm)) {
                                System.out.print("Successful!");
                                break;
                            }
                            System.out.print("Passwords did not match. Try Again");
                        }
                    } catch (UserInputException e) {
                        // either failed
                        System.out.print("Invalid input");
                    }

                }

                // might have to fix  ---------

                while (true) {

                    System.out.println(signUpPg4(username, password));
                    String input = scan.nextLine();
                    if (input.equals("x")){break complete;}

                    switch (input) {
                        case "y":
                            User user = new User(UUID.randomUUID().toString(), username, password, "DEFAULT");
                            userService.register(user);

                            new MainMenu(user, new MovieService(new MovieDAO()),
                                    new ReviewService(new ReviewDAO()), new PhuboxService(new PhuboxDAO()),
                                    new InventoryService(new InventoryDAO()), new OrderService(new OrderDAO())).start();
                            // communicates with daos to save in database
                            System.out.println(" ");
                            break complete;
                        case "n":
                            newPage();
                            System.out.print("Back to beginning");
                            break start;
                        default:
                            newPage();
                            System.out.print("Invalid Input");
                            break;
                    }
                }


            }// start

        }// complete




    }

             // first while true
    // complete exit



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

    private String displayOptions1(){
        // line = 7
        String input;
        String page= "";
        page += displayBlankLine1(2);
        //line 2
        page += displayTextMiddle1("[1] Login");
        //line 3
        page += displayTextMiddle1("[2] Sign Up");
        //line 4
        page += displayTextMiddle1("[x] Exit");
        //line 5
        page += displayBlankLine1(2);
        //line 7
        page += displayLine1();
        //line 8
        page += displayTextMiddle1("Enter:");
        //line 9
        return page;

    }

    private void testMessage(){
        String page;
        page = displayTextBanner1("This is a Test");
        //line 3
        page += displayBlankLine1(7);
        //line 10
        page += displayTextMiddle1("Hello Eric");
        // line 11
        page += displayBlankLine1(6);
        //line 18
        page += displayLine1();
        // line 19
        page += displayTextMiddle1("Enter: ");
        System.out.println(page);
    }
}
