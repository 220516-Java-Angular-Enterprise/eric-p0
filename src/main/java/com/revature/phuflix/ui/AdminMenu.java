package com.revature.phuflix.ui;

import com.revature.phuflix.daos.PhuboxDAO;
import com.revature.phuflix.models.Phubox;
import com.revature.phuflix.models.User;
import com.revature.phuflix.services.PhuboxService;
import com.revature.phuflix.util.custom_exception.UserInputException;

import java.util.Scanner;
import java.util.UUID;

public class AdminMenu extends IMenu {

    private final User user;
    private final PhuboxService phuboxService;

    public AdminMenu(User user, PhuboxService phuboxService){

        this.user = user;
        this.phuboxService = phuboxService;
    }

    @Override
    public void start() {
        adminWelcome();
        while (select1(options())){
            adminWelcome();
        }

    }

    private void adminWelcome(){
        System.out.println("              ___       __          _     \n" +
                           "             /   | ____/ /___ ___  (_)___ \n" +
                           "            / /| |/ __  / __ `__ \\/ / __ \\\n" +
                           "           / ___ / /_/ / / / / / / / / / /\n" +
                           "          /_/  |_\\__,_/_/ /_/ /_/_/_/ /_/ \n" +
                           "                                ");
        displayLine();
        System.out.println("                    Hello " + user.getUsername() + "!");
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
                newPage();
                newPhubox();
                return true;
            case "2":

                return true;
            case "x":
                newPage();
                System.out.println("Signed Out.");
                return false;
            default:
                System.out.println("Invalid input.");
                return true;
        }
    }

    private void newPhubox(){
        Scanner scan = new Scanner(System.in);
        String state;

        exit:
        {
            while (true) {
                System.out.println("Creating new PhuBox...");
                System.out.println("Input city");
                String city = scan.nextLine();

                while (true) {

                    System.out.println("Input city");
                    state = scan.nextLine();
                    try {
                        phuboxService.isValidST(state);
                        break;
                    } catch (UserInputException e) {
                        newPage();
                        System.out.println(e.getMessage());
                    }

                }

                System.out.println("Please confirm info");
                System.out.println("City: " + city);
                System.out.println("State: " + state);
                System.out.println(("Type y/n"));
                String input = scan.nextLine();
                if (input.equals("y")) {
                    phuboxService.newBox(new Phubox(UUID.randomUUID().toString(), city, state));
                    newPage();
                    System.out.println("Box Saved into database");
                    break exit;
                }
            }
        }

    }
}
