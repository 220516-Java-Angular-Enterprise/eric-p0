package com.revature.phuflix.ui;

import com.revature.phuflix.models.User;

import java.util.Scanner;

public class AdminMenu extends IMenu {

    private final User user;

    public AdminMenu(User user){
        this.user = user;
    }

    @Override
    public void start() {
        adminWelcome();
        while (select1(options())){
            System.out.println("Try Again");
        }

    }

    private void adminWelcome(){
        System.out.println("          ___       __          _     \n" +
                           "         /   | ____/ /___ ___  (_)___ \n" +
                        "        / /| |/ __  / __ `__ \\/ / __ \\\n" +
                        "       / ___ / /_/ / / / / / / / / / /\n" +
                        "      /_/  |_\\__,_/_/ /_/ /_/_/_/ /_/ \n" +
                        "                                ");
        displayLine();
        System.out.println("Hello " + user.getUsername() + "!");
    }

    private String options(){
        Scanner scan =new Scanner(System.in);
        String input;
        displayLine();
        System.out.println("                     [1] Add Phubox.");
        System.out.println("                     [2] Add Inventory.");
        System.out.println("                     [x] Sign out.");
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
                System.out.println("Goodbye.");
                newPage();
                return false;
            default:
                System.out.println("Invalid input.");
                return true;
        }
    }
}
