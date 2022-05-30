package com.revature.phuflix.ui;

import com.revature.phuflix.models.*;
import com.revature.phuflix.services.*;
import com.revature.phuflix.util.custom_exception.UserInputException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MainMenu extends IMenu {

    private final User user;
    private final MovieService movieService;
    private final ReviewService reviewService;
    private final PhuboxService phuboxService;
    private final InventoryService inventoryService;
    private final OrderService orderService;

    public MainMenu(User user, MovieService movieService, ReviewService reviewService,
                    PhuboxService phuboxService,InventoryService inventoryService,OrderService orderService){

        this.user = user;
        this.movieService = movieService;
        this.reviewService = reviewService;
        this.phuboxService = phuboxService;
        this.inventoryService = inventoryService;
        this.orderService  =orderService;
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
        displayTextMiddle("[1] Order A Movie");
        displayTextMiddle("[2] Leave A Review");
        displayTextMiddle("[3]  View Shopping Cart");
        displayTextMiddle("[4]  View Order History / get average score");
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
                orderMovie(inventoryService.getInventoryByPhuboxID(selectPhubox().getId()));
                return true;
            case "2":
                try {
                    reviewMovie(selectMovie());
                }catch (Exception e){
                    System.out.println("Failied getting movie.");
                }
                return true;
            case "3":
                viewShoppingCart();
                return true;

            case "4":
                //System.out.println(scoreToStars(reviewService.getAverageScore(//movie)));
                return true;

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

    private void orderMovie(List<Inventory> inventories){
        // 1. displays boxes
        // 2. return movie: displays inventory of those boxes
        // 3. Displays movie info and asks users if they want to rent movie
        //  4a. if yes add to cart.
                // create new order object
        //  4b. if no go back to movie select screen

        for (Inventory inv: inventories){
            System.out.println(inv.getMovie_id());
        }


        Movies movie = new Movies();
        Scanner scan = new Scanner(System.in);
        int counter = 1;
        int lines= 3;
        int displayBoxes = 0; // change if boxes change

        displayTextBanner("List of Movies");
        // line = 3

        exit:
        {
            for (int i = 0; i < inventories.size(); i++) {

                // display three boxes per page
                List<String> l = new ArrayList<>();
                l.add("$"+ (movieService.getByID(inventories.get(i).getMovie_id()).getPrice()) / 100.00);
                // movie = movieService.getByID(inventories.get(i).getMovie_id())
                l.add(movieService.getByID(inventories.get(i).getMovie_id()).getMovie_name());
                String s = scoreToStars(reviewService.getAverageScore(movieService.getByID(inventories.get(i).getMovie_id()).getId()));
                l.add(s);

                displayBoxes = l.size();
                displayBox(l, (i % displayBoxes) + 1);
                lines += displayBoxes +2;
                next:
                {
                    if ((i + 1) % displayBoxes == 0 || i == inventories.size() - 1) { // change if size change
                        if (lines > 0 && lines < 18) {
                            displayBlankLine(18 - lines);
                        }
                        displayTextLine(counter + "/" + (int) Math.ceil(inventories.size() / (displayBoxes*1.0)));
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
                                Inventory selected = inventories.get((counter-1)*displayBoxes + (Integer.parseInt(input) - 1));
                                movie = movieService.getByID(inventories.get((counter-1)*displayBoxes + (Integer.parseInt(input) - 1)).getMovie_id());

                                // ask user for input amount

                                int qty = 0;

                                Orders orders = new Orders(UUID.randomUUID().toString(), user.getId(), selected.getPhubox_id(), selected.getMovie_id(), 1);

                                orderService.save(orders);

                                System.out.println(orders.getMovie_id());
                                break exit;
                            }
                        } catch (UserInputException e) {
                            displayTextMiddle(e.getMessage());
                            break;
                        }
                        return; // break out if invalid
                    }
                }

            }
        }
        System.out.println(movie.getMovie_name());
        return;


    }

    private void d(){
        // 1. displays all movies in database
        // 2. user selects movie and then display screen with reviews
        // 3. if user selects leave a review user can leave a review of the movie
    }


    private Movies selectMovie(){
        // display list of all phuboxes and then return the one the user selects
        Movies movie = new Movies();
        Scanner scan = new Scanner(System.in);
        List<Movies> movies = movieService.getAllMovies();
        int counter = 1;
        int lines= 3;
        int displayBoxes = 0; // change if boxes change

        displayTextBanner("List of Movies");
        // line = 3

        exit:
        {
            for (int i = 0; i < movies.size(); i++) {

                // display three boxes per page
                List<String> l = new ArrayList<>();
                l.add("$"+ movies.get(i).getPrice() / 100.00);
                l.add(movies.get(i).getMovie_name());
                String s = scoreToStars(reviewService.getAverageScore(movies.get(i).getId()));
                l.add(s);

                displayBoxes = l.size();
                displayBox(l, (i % displayBoxes) + 1);
                lines += displayBoxes +2;
                next:
                {
                    if ((i + 1) % displayBoxes == 0 || i == movies.size() - 1) { // change if size change
                        if (lines > 0 && lines < 18) {
                            displayBlankLine(18 - lines);
                        }
                        displayTextLine(counter + "/" + (int) Math.ceil(movies.size() / (displayBoxes*1.0)));
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
                                movie = movies.get((counter-1)*displayBoxes + (Integer.parseInt(input) - 1));
                                break exit;
                            }
                        } catch (UserInputException e) {
                            displayTextMiddle(e.getMessage());
                            break;
                        }
                        return null; // break out if invalid
                    }
                }

            }
        }
        System.out.println(movie.getMovie_name());
        return movie;


    }

    private void reviewMovie(Movies movie){
        String input;
        String inputMessage;
        String confirm;
        Scanner scan = new Scanner(System.in);

        System.out.println("Successful!");
        //line 1
        while (true) {
            displayTextBanner("Reviewing...");
            //line 4
            displayBlankLine(5);
            // line 9
            displayTextMiddle(movie.getMovie_name());
            // line 10
            System.out.println(" ");
            // line 11
            displayTextMiddle("What would you like to rate this movie?");
            // line 12
            displayTextMiddle("Please enter number between 1-5");
            //line 13
            displayBlankLine(5);
            //line 18
            displayLine();
            // line 19
            displayTextMiddle("Enter");

            input = scan.nextLine();

            if(input.equals("x")){
                System.out.println("Back to main menu");
                return;
            }
            else if(input.matches("[1-5]")){
                break;
            }else{
                System.out.println("Invalid input");
            }
        }
        System.out.println(input);

        System.out.println("Successful!");
        //line 1
        while (true) {
            displayTextBanner("Reviewing...");
            //line 4
            displayBlankLine(5);
            // line 9
            displayTextMiddle(movie.getMovie_name());
            // line 10
            System.out.println(" ");
            // line 11
            displayTextMiddle("What would you like to leave a message?");
            // line 12
            displayTextMiddle("Messages must be less than 55 characters");
            //line 13
            displayBlankLine(5);
            //line 18
            displayLine();
            // line 19
            displayTextMiddle("Enter");

            inputMessage = scan.nextLine();

            if(inputMessage.equals("x")){
                System.out.println("Back to main menu");
                return;
            }
            else if(inputMessage.matches(".{0,55}")){
                break;
            }else{
                System.out.println("Invalid input");
            }
        }

        System.out.println("Successful!");
        //line 1
        while (true) {
            displayTextBanner("Reviewing...");
            //line 4
            displayBlankLine(5);
            // line 9
            displayTextMiddle(movie.getMovie_name());
            // line 10
            System.out.println(" ");
            // line 11
            displayTextMiddle(user.getUsername() + ":");
            // line 12
            System.out.println(" ");
            // line 13
            displayTextMiddle(inputMessage);
            //line 14
            displayTextMiddle(input);
            displayBlankLine(4);
            //line 18
            displayLine();
            // line 19
            displayTextMiddle("Enter");

            confirm = scan.nextLine();

            if(confirm.equals("x")){
                System.out.println("Back to main menu");
                return;
            }
            else if(confirm.equals("y")){
                // implement saveing new review

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                Review review = new Review(UUID.randomUUID().toString(),movie.getId(),user.getId(),Integer.parseInt(input),inputMessage,timestamp);
                reviewService.save(review);
                System.out.println("Successful");
                break;
            }else{
                System.out.println("Invalid input");
            }
        }



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

    private String scoreToStars(double d){
        int i = (int)Math.round(d);

        if(i ==0){
            return "No Reviews";
        }else if(i == 1){
            return "⭑⭒⭒⭒⭒";
        }else if(i == 2){
            return "⭑⭑⭒⭒⭒";
        }else if(i == 3){
            return "⭑⭑⭑⭒⭒";
        }else if(i == 4){
            return "⭑⭑⭑⭑⭒";
        }else{
            return "⭑⭑⭑⭑⭑";
        }

    }

    private void deleteItemShoppingCart(){
        List<Orders> shoppingCart = orderService.getShoppingCart(user.getId());
        Scanner scan = new Scanner(System.in);

        Movies movie = new Movies();

        int counter = 1;
        int lines= 3;
        int displayBoxes = 0; // change if boxes change

        displayTextBanner("List of Movies");
        // line = 3

        exit:
        {
            for (int i = 0; i < shoppingCart.size(); i++) {

                // display three boxes per page
                List<String> l = new ArrayList<>();

                //first line
                l.add( phuboxService.getPhuboxByID(shoppingCart.get(i).getPhubox_id()).getAddress());

                // second line
                l.add(movieService.getByID(shoppingCart.get(i).getMovie_id()).getMovie_name());

                // third line
                String price = "$"+ String.valueOf( movieService.getByID(shoppingCart.get(i).getMovie_id()).getPrice()/100.0);
                l.add(price);

                displayBoxes = l.size();
                displayBox(l, (i % displayBoxes) + 1);
                lines += displayBoxes +2;
                next:
                {
                    if ((i + 1) % displayBoxes == 0 || i == shoppingCart.size() - 1) { // change if size change
                        if (lines > 0 && lines < 18) {
                            displayBlankLine(18 - lines);
                        }
                        displayTextLine(counter + "/" + (int) Math.ceil(shoppingCart.size() / (displayBoxes*1.0)));
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
                                // remove item from cart
                                //movie = shoppingCart.get((counter-1)*displayBoxes + (Integer.parseInt(input) - 1));
                                break exit;
                            }
                        } catch (UserInputException e) {
                            displayTextMiddle(e.getMessage());
                            break;
                        }
                        return; // break out if invalid
                    }
                }

            }
        }
        System.out.println(movie.getMovie_name());
        return;

    }

    private void viewShoppingCart(){
        List<Orders> shoppingCart = orderService.getShoppingCart(user.getId());
        Scanner scan = new Scanner(System.in);

        Movies movie = new Movies();

        int counter = 1;
        int lines= 3;
        int displayBoxes = 0; // change if boxes change

        displayTextBanner("List of Movies");
        // line = 3

        exit:
        {
            for (int i = 0; i < shoppingCart.size(); i++) {

                // display three boxes per page
                List<String> l = new ArrayList<>();

                //first line
                //l.add( phuboxService.getPhuboxByID(shoppingCart.get(i).getPhubox_id()).getAddress());

                // second line
                l.add(movieService.getByID(shoppingCart.get(i).getMovie_id()).getMovie_name());

                // third line
                //String price = "$"+ String.valueOf( movieService.getByID(shoppingCart.get(i).getMovie_id()).getPrice()/100.0);
                //l.add(price);

                displayBoxes = l.size();
                displayBox(l, (i % displayBoxes) + 1);
                lines += displayBoxes +2;
                next:
                {
                    if ((i + 1) % (15/displayBoxes) == 0 || i == shoppingCart.size() - 1) { // change if size change
                        if (lines > 0 && lines < 18) {
                            displayBlankLine(18 - lines);
                        }
                        displayTextLine(counter + "/" + (int) Math.ceil(shoppingCart.size() / ((15/displayBoxes)*1.0)));
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
                                // remove item from cart
                                //movie = shoppingCart.get((counter-1)*displayBoxes + (Integer.parseInt(input) - 1));
                                break exit;
                            }
                        } catch (UserInputException e) {
                            displayTextMiddle(e.getMessage());
                            break;
                        }
                        return; // break out if invalid
                    }
                }

            }
        }
        return;

    }

    public void  shoppingCartOptions(){
        displayTextMiddle("[1] View shopping cart");
        displayTextMiddle("[2] Delete item from shopping cart");
        displayTextMiddle("[3] Check out shopping cart");
    }

}
