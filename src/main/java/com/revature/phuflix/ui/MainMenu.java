package com.revature.phuflix.ui;

import com.revature.phuflix.models.User;

import java.util.Scanner;

public class MainMenu extends IMenu {

    private final User user;

    public MainMenu(User user){
        this.user = user;
    }

    @Override
    public void start() {

        welcomeMsg();
        displayTextBanner("Welcome " + user.getUsername());
        while(select1(options())){
            welcomeMsg();
            displayTextBanner("Main Menu");
        }

    }

    private void welcomeMsg(){
        System.out.println("           __  ___      _          __  ___                \n" +
                           "          /  |/  /___ _(_)___     /  |/  /__  ____  __  __\n" +
                           "         / /|_/ / __ `/ / __ \\   / /|_/ / _ \\/ __ \\/ / / /\n" +
                           "        / /  / / /_/ / / / / /  / /  / /  __/ / / / /_/ / \n" +
                           "       /_/  /_/\\__,_/_/_/ /_/  /_/  /_/\\___/_/ /_/\\__,_/  \n");

    }

    private String options(){
        Scanner scan =new Scanner(System.in);
        String input;
        System.out.println(" ");
        displayTextMiddle("[1] Find A Movie");
        displayTextMiddle("[2] Leave A Review");
        displayTextMiddle("[3]  View Shopping Cart");
        displayTextMiddle("[4]  View Order History");
        displayTextMiddle("[x] Sign out.");
        System.out.println(" ");
        displayLine();

        System.out.println("Enter: ");
        input = scan.nextLine();
        return input;
    }

    private boolean select1(String input){

        switch (input){
            case "1":

                return false;
            case "2":

                return false;
            case "x":
                newPage();
                System.out.println( user.getUsername() + "has Signed Out");
                return false;

            default:
                newPage();
                System.out.println("Invalid input.");
                return true;
        }
    }

}
