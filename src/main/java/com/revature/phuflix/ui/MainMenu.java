package com.revature.phuflix.ui;

import com.revature.phuflix.models.Movies;
import com.revature.phuflix.models.Review;
import com.revature.phuflix.models.User;
import com.revature.phuflix.services.MovieService;
import com.revature.phuflix.services.ReviewService;
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

    public MainMenu(User user, MovieService movieService, ReviewService reviewService){

        this.user = user;
        this.movieService = movieService;
        this.reviewService = reviewService;
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
                reviewMovie(selectMovie());
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

    private void orderMovie(){
        // 1. displays boxes
        // 2. return movie: displays inventory of those boxes
        // 3. Displays movie info and asks users if they want to rent movie
        //  4a. if yes add to cart.
        //  4b. if no go back to movie select screen
    }

    private void browseMovies(){
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
                String s = "⭑⭑⭑⭒⭒";
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
            displayTextMiddle("Messages must be over 20 and less than 55 characters");
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
            else if(inputMessage.matches(".{20,55}")){
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
                String time = timestamp.toString();

                Review review = new Review(UUID.randomUUID().toString(),user.getId(),movie.getId(),Integer.parseInt(input),inputMessage,time);


                System.out.println("Successful");
                break;
            }else{
                System.out.println("Invalid input");
            }
        }





    }

}
