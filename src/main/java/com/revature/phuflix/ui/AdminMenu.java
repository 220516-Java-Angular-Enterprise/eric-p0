package com.revature.phuflix.ui;

import com.revature.phuflix.daos.PhuboxDAO;
import com.revature.phuflix.models.Inventory;
import com.revature.phuflix.models.Movies;
import com.revature.phuflix.models.Phubox;
import com.revature.phuflix.models.User;
import com.revature.phuflix.services.InventoryService;
import com.revature.phuflix.services.MovieService;
import com.revature.phuflix.services.PhuboxService;
import com.revature.phuflix.util.custom_exception.UserInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AdminMenu extends IMenu {

    private final User user;
    private final PhuboxService phuboxService;
    private final InventoryService inventoryService;
    private final MovieService movieService;

    public AdminMenu(User user, PhuboxService phuboxService, InventoryService inventoryService,
                     MovieService movieService){

        this.user = user;
        this.phuboxService = phuboxService;
        this.inventoryService = inventoryService;
        this.movieService = movieService;
    }

    @Override
    public void start() {
        adminWelcome();
        while (select1(options())){
            adminWelcome();
        }

    }

    private void adminWelcome(){
        displayTextMiddle(" ___       __          _");
        displayTextMiddle("   /   | ____/ /___ ___  (_)___");
        displayTextMiddle("  / /| |/ __  / __ `__ \\/ / __");
        displayTextMiddle("  / ___ / /_/ / / / / / / / / / /");
        displayTextMiddle("/_/  |_\\__,_/_/ /_/ /_/_/_/ /_/");
        System.out.println("\n");

        displayTextBanner("Hello " + user.getUsername() + "!");
        // 8 lines
    }

    private String options(){
        Scanner scan =new Scanner(System.in);
        String input;
        displayTextMiddle("[1] Add Phubox");
        displayTextMiddle("[2] Add Inventory");
        displayTextMiddle("[3] Add New Movie");

        displayTextMiddle("[4] View Users");
        displayTextMiddle("[5] Delete Users");
        displayTextMiddle("[x] Sign Out");
        displayLine();

        System.out.println("Enter: ");
        input = scan.nextLine();
        return input;
        // 8 lines
    }

    private boolean select1(String input){

        switch (input){
            case "1":
                newPage();
                newPhubox();
                return true;
            case "2":
                addMovieInventory();
                return true;
            case "3":
                addMovie();
                return true;
            case "x":
                newPage();
                System.out.println(user.getUsername() + " has Signed Out");
                return false;
            default:
                displayLine();
                System.out.println("Invalid input.");
                return true;
        }
    }

    private void newPhubox(){
        Scanner scan = new Scanner(System.in);
        String state;
        String city;
        String streetAddress;
        String streetName;
        String streetType;
        String streetNumber;
        String input;

        exit:
        {
            while (true) {

                begin:
                {
                    while (true) {
                        displayTextMiddle("Creating new PhuBox...");
                        // line = 1
                        while (true) {
                            displayTextBanner("Enter House Number: ");
                            // line = 4

                            displayBlankLine(14);

                            displayLine();
                            // line =  19
                            displayTextMiddle("Enter: ");
                            //line = 20

                            streetNumber = scan.nextLine();
                            try {
                                if (phuboxService.isValidStreetNumber(streetNumber)) {
                                    break;
                                }
                            } catch (Exception e) {
                                displayTextMiddle("Invalid input. Please enter a number.");
                                // line 1
                            }
                        }
                        displayTextMiddle("Successful!");
                        // line 1
                        while (true) {
                            displayTextBanner("Enter Street Name");
                            // line 4

                            displayBlankLine(6);
                            displayTextMiddle("Street Nunmber: "+streetNumber);
                            // line 10

                            displayBlankLine(7);
                            // line 17
                            displayLine();
                            // line 18
                            displayTextMiddle("If incorrect type x");
                            // line 19
                            displayTextMiddle("Enter: ");
                            // line 20
                            streetName = scan.nextLine();

                            if (streetName.equals("x")){break begin;}

                            try {
                                if (phuboxService.isValidStreetName(streetName)) {
                                    break;
                                }
                            } catch (UserInputException e) {
                                displayTextMiddle(e.getMessage());
                            }
                        }
                        displayTextMiddle("Successful!");
                        // line 1
                        while (true) {
                            displayTextBanner("Enter Street Type");
                            // line 4
                            displayBlankLine(6);
                            displayTextMiddle("Street Nunmber: "+streetNumber);
                            // line 10
                            displayTextMiddle("Street Name: "+streetName);
                            // line 11

                            displayBlankLine(6);
                            // line 17
                            displayLine();
                            // line 18
                            displayTextMiddle("If incorrect type x");
                            // line 19
                            displayTextMiddle("Enter: ");
                            // line 20

                            streetType = scan.nextLine();
                            if (streetType.equals("x")){break begin;}
                            try {
                                if (phuboxService.isValidStreetType(streetType)) {
                                    break;
                                }
                            } catch (UserInputException e) {
                                displayTextMiddle(e.getMessage());
                                // line 1
                            }
                        }
                        confirm:
                        {
                            displayTextMiddle("Confirm Street Address");
                            // line 1
                            while (true) {

                                displayTextBanner("Is this the correct street info?");
                                // line 4

                                displayBlankLine(6);
                                // line 10
                                displayTextMiddle("Street Number: " + streetNumber);
                                // line 11
                                displayTextMiddle("Street Name: " + streetName);
                                // line 12
                                displayTextMiddle("Street Type: " + streetType);
                                // line 13

                                displayBlankLine(5);
                                //line 18
                                displayLine();
                                //line 19
                                displayTextMiddle("y to continue, x to go back to begining");
                                // line 20
                                input = scan.nextLine();

                                switch (input) {
                                    case "y":
                                        break confirm;
                                    case "x":
                                        break begin;
                                    default:
                                        displayTextMiddle("Invalid Input");
                                }
                            }
                        }

                        if (input.equals("y")) {
                            streetAddress = streetNumber + " " + streetName + " " + streetType;
                            System.out.println(streetAddress);
                            break;
                        }

                    }

                    displayTextMiddle("Successful");
                    // line 1
                    while (true) {

                        displayTextBanner("Input city");
                        // line 4
                        displayBlankLine(6);
                        // line 10
                        displayTextMiddle("Street Address: " + streetAddress);


                        displayBlankLine(7);
                        //line 17
                        displayLine();
                        //line 18
                        displayTextMiddle("Type x to go back to beginning");
                        displayTextMiddle("Enter City: ");
                        // line 20
                        city = scan.nextLine();
                        if(city.equals("x")){break begin;}

                        try {
                            phuboxService.isValidCity(city);
                            break;
                        } catch (UserInputException e) {
                            displayTextMiddle(e.getMessage());
                            // line 1
                        }

                    }
                    displayTextMiddle("Successful");
                    //line 1
                    while (true) {
                        displayTextBanner("Input State Abbreviation");
                        // line 4
                        displayBlankLine(6);
                        // line 10
                        displayTextMiddle("Street Address: " + streetAddress);
                        displayTextMiddle("City: " + city);

                        displayBlankLine(6);
                        //line 17
                        displayLine();
                        //line 18
                        displayTextMiddle("Type x to go back to beginning");
                        displayTextMiddle("Enter State Abbreviation (CT, FL, GA): ");
                        // line 20

                        state = scan.nextLine();
                        if(state.equals("x")){break begin;}
                        try {
                            phuboxService.isValidST(state);
                            break;
                        } catch (UserInputException e) {
                            displayTextMiddle(e.getMessage());
                        }

                    }

                    displayTextMiddle("Please confirm info");

                    confirm2: {
                        while (true){
                            displayTextBanner("Is this the correct address info?");
                            // line 4

                            displayBlankLine(6);
                            // line 10
                            displayTextMiddle("Street Address: " + streetAddress);
                            // line 11
                            displayTextMiddle("City: " + city);
                            // line 12
                            displayTextMiddle("State: " + state);
                            // line 13

                            displayBlankLine(5);
                            //line 18
                            displayLine();
                            //line 19
                            displayTextMiddle("y to continue, x to go back to begining");
                            // line 20
                            input = scan.nextLine();

                            switch (input) {
                                case "y":
                                case "x":
                                    break confirm2;
                                default:
                                    displayTextMiddle("Invalid Input");
                            }
                        }

                    }

                    if (input.equals("y")) {
                        phuboxService.newBox(new Phubox(UUID.randomUUID().toString(), streetAddress, city, state));
                        newPage();
                        System.out.println("Box Saved into database");
                        break exit;
                    }
                }
            } // begin
        } // exit

    }

    private Phubox selectPhubox(){
        // display list of all phuboxes and then return the one the user selects
        Phubox box = new Phubox();
        Scanner scan = new Scanner(System.in);
        List<Phubox> boxes = phuboxService.getAllPhuBoxes();
        int counter = 1;
        int lines= 3;

        displayTextBanner("List of phuboxes");
        // line = 3
        exit:
        {
            for (int i = 0; i < boxes.size(); i++) {

                // display three boxes per page
                List<String> l = new ArrayList<>();
                l.add("Address: " + boxes.get(i).getAddress());
                l.add("City: " + boxes.get(i).getCity());
                l.add("State: " + boxes.get(i).getState());
                displayBox(l, (i % 3) + 1);
                lines += 5;
                next:
                {
                    if ((i + 1) % 3 == 0 || i == boxes.size() - 1) {
                        if (lines > 0 && lines < 18) {
                            displayBlankLine(18 - lines);
                        }
                        displayTextLine(counter + "/" + (int) Math.ceil(boxes.size() / 3.0));
                        // line 19
                        displayTextMiddle("Select box. blank for next. x to exit");
                        // line 20
                        String input = scan.nextLine();
                        if (input.equals("x")) {
                            break;
                        }
                        if (input.equals("n")) {
                            displayTextBanner("List of phuboxes");
                            lines = 3;
                            counter++;
                            break next;
                        }
                        try {
                            if (phuboxService.isValidSelect(input)) {
                                box = boxes.get((counter-1)*3 + (Integer.valueOf(input) - 1));
                                break exit;
                            }
                        } catch (UserInputException e) {
                            displayTextMiddle(e.getMessage());
                            break;
                        }
                    }
                }

            }
        }
        return box;
    }

    // add new inventory
    // look at all movies in the database
    // looks at all boxes
    //

    private Movies selectMovie(){
        // display list of all phuboxes and then return the one the user selects
        Movies movie = new Movies();
        Scanner scan = new Scanner(System.in);
        List<Movies> movies = movieService.getAllMovies();
        int counter = 1;
        int lines= 3;

        displayTextBanner("List of Movies");
        // line = 3
        exit:
        {
            for (int i = 0; i < movies.size(); i++) {

                // display three boxes per page
                List<String> l = new ArrayList<>();
                l.add(movies.get(i).getMovie_name());
                //.add("$"+String.valueOf(movies.get(i).getPrice()/100.00));
                displayBox(l, (i % 5) + 1);
                lines += 3;
                next:
                {
                    if ((i + 1) % 5 == 0 || i == movies.size() - 1) { // change if size chabge
                        if (lines > 0 && lines < 18) {
                            displayBlankLine(18 - lines);
                        }
                        displayTextLine(counter + "/" + (int) Math.ceil(movies.size() / 5.0));
                        // line 19
                        displayTextMiddle("Select movie. blank for next. x to exit");
                        // line 20
                        String input = scan.nextLine();
                        if (input.equals("x")) {
                            break;
                        }
                        if (input.equals("n")) {
                            displayTextBanner("List of movies");
                            lines = 3;
                            counter++;
                            break next;
                        }
                        try {
                            if (input.matches("[1-5]")) {
                                movie = movies.get((counter-1)*5 + (Integer.valueOf(input) - 1));
                                break exit;
                            }
                        } catch (UserInputException e) {
                            displayTextMiddle(e.getMessage());
                            break;
                        }
                    }
                }

            }
        }
        System.out.println(movie.getMovie_name());
        return movie;


    }

    private void addMovieInventory(){
        Scanner scan = new Scanner(System.in);
        Phubox box = new Phubox();
        int qty =0;
        String qtyInput;

        Movies movie = selectMovie();
        try{
            if (movieService.isValidMovie(movie)){
                box = selectPhubox();
                phuboxService.isValidPhubox(box);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
        displayTextMiddle("Inventory");
        //line 1


            while (true) {
                displayTextBanner("Quantity");
                // line 4
                displayBlankLine(3);
                // line 7
                displayTextLine("Movie");
                //line 8
                displayTextMiddle(movie.getMovie_name());
                // line 9
                System.out.println(" ");
                // line 10
                displayTextLine("Phubox Address");
                // line 11
                displayTextMiddle(box.getAddress());
                // line 12
                displayTextMiddle(box.getCity() + "," + box.getState());
                // line 13

                displayBlankLine(5);
                // line 18
                displayLine();
                // line 19
                displayTextMiddle("Enter number of new Inventory");
                // line 20
                qtyInput = scan.nextLine();
                if(inventoryService.isValidQuanity(qtyInput)){
                    qty = Integer.parseInt(qtyInput);
                    break;
                }

        }
        try {
            inventoryService.save(new Inventory(movie.getId(), box.getId(), qty));
            } catch (Exception e){
            try{
                // get the quanity
                Inventory inv = inventoryService.getInventoryByIDs(movie.getId(), box.getId());
                inventoryService.update(new Inventory(movie.getId(), box.getId(), qty+inv.getQty()));
            } catch (Exception e1){
                System.out.println(e1.getMessage());
            }
        }

    }

    private void addMovie(){
        Scanner scan = new Scanner(System.in);
        String movieTitle;
        String priceInput;
        int price = 0;
        String confirm;

        completeExit:
        {
            while (true) {
                System.out.println("Adding Movie...");

                System.out.println("Movie Title: ");
                movieTitle = scan.nextLine();
                newPage();

                while (true) {
                    try {
                        if (movieService.isValidTitle(movieTitle)){
                            break;
                        }
                    } catch (UserInputException e) {
                        newPage();
                        System.out.println(e.getMessage());}
                    System.out.println("Movie Title: ");
                    movieTitle = scan.nextLine();
                }
                newPage();
                while (true){

                    try{
                        newPage();
                        System.out.println("Price : ");
                        priceInput = scan.nextLine();
                        if(movieService.isValidPrice(priceInput)){
                            double temp = Double.parseDouble(priceInput) *100.0;
                            price = (int)temp;
                            break;
                        }
                    }catch (Exception e){
                        newPage();
                        System.out.println("Invalid input. Try Again");
                    }

                }

                confirmExit:
                {
                    while (true) {
                        newPage();
                        System.out.println("\nPlease confirm your credentials (y/n)");
                        System.out.println("\nTitle: " + movieTitle);
                        System.out.println("Price: $" + price/100.0);

                        System.out.print("\nEnter: ");
                        String input = scan.nextLine();

                        switch(input) {
                            case "y":
                                Movies movie = new Movies(UUID.randomUUID().toString(), movieTitle, price);
                                try{
                                    movieService.save(movie);
                                }catch (UserInputException e){

                                }

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

}
