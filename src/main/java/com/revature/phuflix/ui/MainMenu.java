package com.revature.phuflix.ui;

import com.revature.phuflix.models.*;
import com.revature.phuflix.services.*;
import com.revature.phuflix.util.custom_exception.UserInputException;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

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
        Scanner scan = new Scanner(System.in);

        String page = "";
        page += welcomeMsg1();
        // line 5
        page += displayTextBanner1("Welcome " + user.getUsername());
        // line 8
        page += options1();
        // line 20
        System.out.println(page);
        String input = scan.nextLine();


        while(select1(input)){
            System.out.println(page);
            input = scan.nextLine();
        }

    }

    private String welcomeMsg1(){
        String anw = "";
        anw += displayTextMiddle1("    __  ___      _          __  ___              ");
        anw += displayTextMiddle1("     /  |/  /___ _(_)___     /  |/  /__  ____  __  __");
        anw += displayTextMiddle1("    / /|_/ / __ `/ / __ \\   / /|_/ / _ \\/ __ \\/ / / /");
        anw += displayTextMiddle1("  / /  / / /_/ / / / / /  / /  / /  __/ / / / /_/ /" );
        anw += displayTextMiddle1("/_/  /_/\\__,_/_/_/ /_/  /_/  /_/\\___/_/ /_/\\__,_/");
        return anw;
    }


    private String options(){
        Scanner scan =new Scanner(System.in);
        String input;
        System.out.println(" ");
        displayTextMiddle("[1] Order A Movie");
        displayTextMiddle("[2] Leave A Review");
        displayTextMiddle("[3] Shopping Cart");
        displayTextMiddle("[4] View Order History");
        displayTextMiddle("[x] Sign out.");
        System.out.println(" ");
        displayLine();

        System.out.println("Enter: ");
        input = scan.nextLine();
        return input;
    }

    private String options1(){

        String page = "";
        page  += displayBlankLine1(2);
        // line 2
        page  +=displayTextMiddle1("[1] Order A Movie");
        // line 3
        page  +=displayTextMiddle1("[2] Leave A Review");
        // line 4
        page  +=displayTextMiddle1("[3] Shopping Cart");
        // line 5
        page  +=displayTextMiddle1("[4] View Order History");
        // line 6
        page  +=displayTextMiddle1("[x] Sign out.");
        // line 7
        page  += displayBlankLine1(3);
        // line 10
        page  +=displayLine1();
        // line 11
        page  +=displayTextMiddle1("Enter: ");
        // line 12
        return page;
    }

    private boolean select1(String input){


        switch (input){
            case "1":
                try {
                    orderMovie(inventoryService.getInventoryByPhuboxID(selectPhubox().getId()));
                } catch (Exception e){
                    System.out.println("Back to Main menu");
                }
                return true;
            case "2":
                try {
                    reviewMovie(selectMovie());
                }catch (Exception e){
                    System.out.println("Failied getting movie.");
                }
                return true;
            case "3":
                // shopping cart
                Scanner scan = new Scanner(System.in);
                String page = "";
                page += shopingCartPage();
                System.out.println(page);
                String inputShop = scan.nextLine();


                while (shoppingCartSelect(inputShop)){
                    System.out.println(page);
                    inputShop = scan.nextLine();

                }



                //while(shoppingCartSelect(shoppingCartOptions()));
                return true;

            case "4":
                // order history
                viewOrderHistory();
                return true;

            case "x":
                newPage();
                System.out.println( user.getUsername() + " has Signed Out");
                return false;

            default:
                newPage();
                System.out.println("Invalid input.");
                return true;
        }
    }

    // need to check if user already has title in cart
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

        //---------------------


        // display list of all phuboxes and then return the one the user selects

        Scanner scan = new Scanner(System.in);


        List<String> pages = new ArrayList<>();
        String tempPage;
        int counter = 1;
        int lines = 3;
        int displayBoxes = 0;
        int displayBoxesPerPage = 1;
        int displayBoxesSize = 0;

        tempPage = displayTextBanner1("This is a list of");
        // line 3

        // we will break out once we get our last page

        for (int i = 0; i < inventories.size(); i++) {

            //display box
            List<String> l = new ArrayList<>();
            //-------- Add elemnts to display box

            l.add(("$"+ (movieService.getByID(inventories.get(i).getMovie_id()).getPrice()) / 100.00) + " QTY: " + inventories.get(i).getQty());
            // movie = movieService.getByID(inventories.get(i).getMovie_id())
            l.add(movieService.getByID(inventories.get(i).getMovie_id()).getMovie_name());
            String s = scoreToStars(reviewService.getAverageScore(movieService.getByID(inventories.get(i).getMovie_id()).getId()));
            l.add(s);

            // -------- end elements display box

            displayBoxes = l.size();
            displayBoxesSize = displayBoxes + 2;
            displayBoxesPerPage++;

            tempPage += displayBox1(l, (i % (15 / displayBoxesSize) + 1));
            lines += displayBoxes + 2;

            if ((i + 1) % (15 / displayBoxesSize) == 0 || i == inventories.size() - 1) {
                if (lines > 0 && lines < 18) {
                    tempPage += displayBlankLine1(18 - lines);
                }

                tempPage += displayTextLine1(counter + "/" + (int) Math.ceil(inventories.size() / ((15 / displayBoxesSize) * 1.0)));
                // line 19

                tempPage += displayTextMiddle1("Text input opions");
                // line 20

                pages.add(tempPage);
                tempPage = displayTextBanner1("This is a list of");
                lines = 3;
                counter++;

            }
        }

        // we have pages now we can iterate through them
        int place = 0;// this will kepp track of which page we are on

        String userInput = "";

        String currentPage = pages.get(place);
        int items = 15/displayBoxesSize;

        exit:
        {

            while (true) {
                System.out.print(currentPage);
                userInput = scan.nextLine();
                page:
                {

                    try {
                        if (userInput.matches("[1-" + items + "]")) {
                            Inventory inv = inventories.get(items * place + (Integer.parseInt(userInput) - 1));

                            String movieID = inv.getMovie_id();
                            int qty = 0;
                            // check if user already requested title

                            // will throw and error if cart is empty

                            try {
                                List<Orders> shopingCart = orderService.getShoppingCart(user.getId());
                                for (Orders o : shopingCart) {
                                    if (o.getMovie_id().equals(movieID)) {
                                        System.out.println("Cannot add movie that is in your cart");
                                        break page;
                                    }
                                }
                            } catch (IndexOutOfBoundsException e) {
                                // do nothing
                            }

                            Orders orders = new Orders(UUID.randomUUID().toString(), user.getId(), inv.getPhubox_id(), inv.getMovie_id(), 1);

                            orderService.save(orders);

                            System.out.println(orders.getMovie_id());
                            break exit;
                        } else if (userInput.equals("n")) {
                            currentPage = pages.get(place + 1);
                            place++;
                        } else if (userInput.equals("p")) {
                            currentPage = pages.get(place - 1);
                            place--;
                        } else if (userInput.equals("x")) {
                            break exit;
                        } else {
                            System.out.println("invalid input");
                        }

                    } catch (Exception e) {
                        System.out.println("Invalid input");
                    }
                }

            }
        }
        return;

    }

    private String  shoppingCartOptions(){

        String anw ="";
        anw += displayTextMiddle1("[1] Check out shopping cart");
        //line1
        anw += displayTextMiddle1("[2] View shopping cart");
        //line2
        anw += displayTextMiddle1("[3] Delete item from shopping cart");
        //line3
        anw += displayTextMiddle1("[x] Back to Main Menu");
        //line4
        return anw;
    }

    private String shopingCartPage(){
        String page ="";

        page += displayTextBanner1("Shopping Cart");
        // line 3
        page += displayBlankLine1(6);
        // line 9
        page += shoppingCartOptions();
        // line 13
        page += displayBlankLine1(5);
        // line 18
        page += displayLine1();
        // line 19
        page += displayTextMiddle1("Enter: ");
        //line 20
        return page;
    }



    private Movies selectMovie(){
        // display list of all phuboxes and then return the one the user selects
        Movies movie = new Movies();
        Scanner scan = new Scanner(System.in);
        List<Movies> movies = movieService.getAllMovies();

        List<String> pages = new ArrayList<>();
        String tempPage;
        int counter = 1;
        int lines = 3;
        int displayBoxes = 0;
        int displayBoxesPerPage = 1;
        int displayBoxesSize = 0;

        tempPage = displayTextBanner1("This is a list of");
        // line 3

        // we will break out once we get our last page

        for (int i = 0; i < movies.size(); i++) {

            //display box
            List<String> l = new ArrayList<>();
            //-------- Add elemnts to display box

            l.add(movies.get(i).getMovie_name());

            // -------- end elements display box

            displayBoxes = l.size();
            displayBoxesSize = displayBoxes + 2;
            displayBoxesPerPage++;

            tempPage += displayBox1(l, (i % (15 / displayBoxesSize) + 1));
            lines += displayBoxes + 2;

            if ((i + 1) % (15 / displayBoxesSize) == 0 || i == movies.size() - 1) {
                if (lines > 0 && lines < 18) {
                    tempPage += displayBlankLine1(18 - lines);
                }

                tempPage += displayTextLine1(counter + "/" + (int) Math.ceil(movies.size() / ((15 / displayBoxesSize) * 1.0)));
                // line 19

                tempPage += displayTextMiddle1("Text input opions");

                pages.add(tempPage);
                tempPage = displayTextBanner1("This is a list of");
                lines = 3;
                counter++;

            }
        }

        // we have pages now we can iterate through them
        int place = 0;// this will kepp track of which page we are on

        String userInput = "";

        String currentPage = pages.get(place);
        int items = 15/displayBoxesSize;

        exit:
        {

            while (true) {
                System.out.println(currentPage);
                userInput = scan.nextLine();

                try {
                    if (userInput.matches("[1-" + items + "]")){
                        movie = movies.get(items*place + (Integer.parseInt(userInput) - 1));
                        break exit;
                    }
                    else if(userInput.equals("n")) {
                        currentPage = pages.get(place+1);
                        place++;
                    }else if(userInput.equals("p")){
                        currentPage = pages.get(place-1);
                        place--;
                    } else if(userInput.equals("x")) {
                        break exit;
                    }

                    else{
                        System.out.println("invalid input");
                    }

                } catch (Exception e) {
                    System.out.println("Invalid input");
                }

            }
        }
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

        List<String> pages = new ArrayList<>();
        String tempPage;
        int counter = 1;
        int lines = 3;
        int displayBoxes = 0;
        int displayBoxesPerPage = 1;
        int displayBoxesSize = 0;

        tempPage = displayTextBanner1("This is a list of");
        // line 3

        // we will break out once we get our last page

        for (int i = 0; i < boxes.size(); i++) {

            //display box
            List<String> l = new ArrayList<>();
            //-------- Add elemnts to display box

            l.add("Address: " + boxes.get(i).getAddress());
            l.add("City: " + boxes.get(i).getCity());
            l.add("State: " + boxes.get(i).getState());

            // -------- end elements display box

            displayBoxes = l.size();
            displayBoxesSize = displayBoxes + 2;
            displayBoxesPerPage++;

            tempPage += displayBox1(l, (i % (15 / displayBoxesSize) + 1));
            lines += displayBoxes + 2;

            if ((i + 1) % (15 / displayBoxesSize) == 0 || i == boxes.size() - 1) {
                if (lines > 0 && lines < 18) {
                    tempPage += displayBlankLine1(18 - lines);
                }

                tempPage += displayTextLine1(counter + "/" + (int) Math.ceil(boxes.size() / ((15 / displayBoxesSize) * 1.0)));
                // line 19

                tempPage += displayTextMiddle1("Text input opions");

                pages.add(tempPage);
                tempPage = displayTextBanner1("This is a list of");
                lines = 3;
                counter++;

            }
        }

        // we have pages now we can iterate through them
        int place = 0;// this will kepp track of which page we are on

        String userInput = "";

        String currentPage = pages.get(place);
        int items = 15/displayBoxesSize;

        exit:
        {

            while (true) {
                System.out.println(currentPage);
                userInput = scan.nextLine();

                try {
                    if (userInput.matches("[1-" + items + "]")){
                        box = boxes.get(items*place + (Integer.parseInt(userInput) - 1));
                        break exit;
                    }
                    else if(userInput.equals("n")) {
                        currentPage = pages.get(place+1);
                        place++;
                    }else if(userInput.equals("p")){
                        currentPage = pages.get(place-1);
                        place--;
                    } else if(userInput.equals("x")) {
                        break exit;
                    }

                    else{
                        System.out.println("invalid input");
                    }

                } catch (Exception e) {
                    System.out.println("Invalid input");
                }

            }
        }
        return box;

    }

    // ------------------

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

    // ------------------

    // need to handle exception
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
                                // order id shoppingCart.get((counter-1)*displayBoxes + (Integer.parseInt(input) - 1)).getId()
                                String order_id = shoppingCart.get((counter-1)*displayBoxes + (Integer.parseInt(input) - 1)).getId();

                                displayTextMiddle("Are you sure you want to delete");
                                input = scan.nextLine();
                                if(input.equals("y")) {
                                    orderService.deleteByID(order_id);
                                    displayTextMiddle("Successful");
                                    break exit;
                                }
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

    // need to handle exception
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

    private String checkOutPage(){

        String page = "";

        page += displayTextBanner1("Shopping cart");
        // line 3
        page += displayBlankLine1(6);
        // line 9
        page += displayTextMiddle1("Shoping cart total");
        // line 10
        page += displayTextMiddle1(String.valueOf("$" + orderService.getShoppingCartSum(user.getId())/100.0));
        // line 11
        page += displayTextMiddle1("Would you like to check out?");
        // line 12
        page += displayBlankLine1(6);
        // line 18
        page += displayLine1();
        // line 19
        page += displayTextMiddle1("Enter y to confirm purchase (x to exit)");
        // line 20
        return page;
    }

    // need to handle exception
    private void checkOut(){
        // displays total of cart
        // promts users if they want to check out
        // iterates throug shopping cart and set each item with a timestamp
        Scanner scan = new Scanner(System.in);
        List<Orders> shoppingCart = orderService.getShoppingCart(user.getId());


        String input;

        while (true) {

            System.out.println(checkOutPage());
            input = scan.nextLine();

            if(input.equals("x")){return;}

            if (input.equals("y")) {
                for (Orders order : shoppingCart) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    ;
                    order.setTimestamp(timestamp);
                    // have to subtract 1 from inventory

                    // chech if invory id greater than one

                    if (inventoryService.getQty(order.getMovie_id(), order.getPhubox_id()) == 0) {
                        System.out.println("Item no longer in stock. Removed from cart");
                        // removes item from cart
                        orderService.deleteByID(order.getId());
                        return;
                    }
                    inventoryService.substract(order.getMovie_id(), order.getPhubox_id());
                    orderService.update(order);
                    return;
                }
            }

        }
    }



    private boolean shoppingCartSelect(String input){
        switch(input) {
            case "1":
                checkOut();
                return true;
            case "2":
                viewShoppingCart();
                return true;
            case "3":
                deleteItemShoppingCart();
                return true;
            case "x":
                System.out.println("Back to main menu");
                return false;
            default:
                System.out.println("Invalid input.");
                return true;
        }
    }

    // need to fix strings
    private void viewOrderHistory(){
        // need to validate
        Scanner scan = new Scanner(System.in);
        List<String> pages = new ArrayList<>();
        List<Orders> orderHistory = new ArrayList<>();

        try{
            orderHistory = orderService.getSOrderHistory(user.getId());
            orderHistory.get(0);
        }
        catch (Exception e){
            System.out.println("No Inventory");
            return;
        }


        String tempPage;
        int counter = 1;
        int lines = 3;
        int displayBoxes = 0;
        int displayBoxesPerPage = 1;
        int displayBoxesSize = 0;

        tempPage = displayTextBanner1("This is a list of");
        // line 3

        // we will break out once we get our last page

        for (int i = 0; i < orderHistory.size(); i++) {

            //display box
            List<String> l = new ArrayList<>();
            //-------- Add elemnts to display box

            //first line date excuted
            String date = orderHistory.get(i).getTimestamp().toString();
            l.add(date);

            // second line
            l.add(movieService.getByID(orderHistory.get(i).getMovie_id()).getMovie_name());

            // third line price
            String price = "$"+ movieService.getByID(orderHistory.get(i).getMovie_id()).getPrice() / 100.0;
            l.add(price);

            // -------- end elements display box

            displayBoxes = l.size();
            displayBoxesSize = displayBoxes + 2;
            displayBoxesPerPage++;

            tempPage += displayBox1(l, (i % (15 / displayBoxesSize) + 1));
            lines += displayBoxes + 2;

            if ((i + 1) % (15 / displayBoxesSize) == 0 || i == orderHistory.size() - 1) {
                if (lines > 0 && lines < 18) {
                    tempPage += displayBlankLine1(18 - lines);
                }

                tempPage += displayTextLine1(counter + "/" + (int) Math.ceil(orderHistory.size() / ((15 / displayBoxesSize) * 1.0)));
                // line 19

                tempPage += displayTextMiddle1("Text input opions");

                pages.add(tempPage);
                tempPage = displayTextBanner1("This is a list of");
                lines = 3;
                counter++;

            }
        }

        // we have pages now we can iterate through them
        int place = 0;// this will kepp track of which page we are on

        String userInput = "";

        String currentPage = pages.get(place);
        int items = 15/displayBoxesSize;

        exit:
        {

            while (true) {
                System.out.println(currentPage);
                userInput = scan.nextLine();

                try {
                    if (userInput.matches("[1-" + items + "]")){
                        // items
                        break exit;
                    }
                    else if(userInput.equals("n")) {
                        currentPage = pages.get(place+1);
                        place++;
                    }else if(userInput.equals("p")){
                        currentPage = pages.get(place-1);
                        place--;
                    } else if(userInput.equals("x")) {
                        break exit;
                    }

                    else{
                        System.out.println("invalid input");
                    }

                } catch (Exception e) {
                    System.out.println("Invalid input");
                }

            }
        }
        return;

    }

    // fixed view menu

    private void viewListObjects() {
        Scanner scan = new Scanner(System.in);
        List<Object> objects = new ArrayList<>();
        List<String> pages = new ArrayList<>();
        String tempPage;
        int counter = 1;
        int lines = 3;
        int displayBoxes = 0;
        int displayBoxesPerPage = 1;
        int displayBoxesSize = 0;

        tempPage = displayTextBanner1("This is a list of");
        // line 3

        // we will break out once we get our last page

        for (int i = 0; i < objects.size(); i++) {

            //display box
            List<String> l = new ArrayList<>();
            //-------- Add elemnts to display box

            l.add("Test");

            // -------- end elements display box

            displayBoxes = l.size();
            displayBoxesSize = displayBoxes + 2;
            displayBoxesPerPage++;

            tempPage += displayBox1(l, (i % (15 / displayBoxesSize) + 1));
            lines += displayBoxes + 2;

            if ((i + 1) % (15 / displayBoxesSize) == 0 || i == objects.size() - 1) {
                if (lines > 0 && lines < 18) {
                    tempPage += displayBlankLine1(18 - lines);
                }

                tempPage += displayTextLine1(counter + "/" + (int) Math.ceil(objects.size() / ((15 / displayBoxesSize) * 1.0)));
                // line 19

                tempPage += displayTextMiddle1("Text input opions");

                pages.add(tempPage);
                tempPage = displayTextBanner1("This is a list of");
                lines = 3;
                counter++;

            }
        }

        // we have pages now we can iterate through them
        int place = 1;// this will kepp track of which page we are on
        ListIterator<String> pn = pages.listIterator();

        String userInput = "";

        String currentPage = pn.next();

        while (true){
            System.out.println(currentPage);
            userInput = scan.nextLine();

            try{
                switch(userInput){
                    case "n":
                        currentPage = pn.next();
                        place ++;
                    case "p":
                        currentPage = pn.previous();
                        place --;
                    case "x":
                        break;
                }

            }catch (Exception e){
                System.out.println("Invalid input");
            }

        }


    }

        // while end

}
