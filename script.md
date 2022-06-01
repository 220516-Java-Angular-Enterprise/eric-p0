## Script

Hello my name is Eric Phu. I am an enterprise Java Developer that works for Revature. My product is Phuflix.
Phuflix is a website that handles Movie orders and review for users.

Before I go through the product let me just show you my Junit test that validates that my methods for my services.
work. There are 10 simple test that test ... (list methods tested)

Ok.Next let me go through my er diagram so you can see Phuflix is able to hold and organize all this data. As you can 
see Phuflix follows 3N.


(Maybe go over diagram here)

Now let me walk through the product. First when a customer is introduced to the software they are greeted with by the
start page. Here we can select wheter to login or register. I will demostrate both but for now let me sign up.

(Select a wrong input).

As you can see the start menu as well as the other menu that we will go through has input validation that ensure
the software are getting the correct inputs from the customer.

(Select correct input)

(Sign up page populate)

When signing up Users will be introduced to the sign up pages. User will have to create a username that follows
the rules listed.

(Type a username that is taken)
(Type invalid input)

Also they have to avoid selecting a username that is already taken.

(Type username that is taken)

As you see the customer is given a different promt from the software to indicate that the username is taken.
This is done by creating a custom exception that is thron if a username is already in the database.

So lets create the username Catlover123

Now lets make a password which also has the same validation methods as Username with extra rules for security

(sign up correctly)

Welcome to the main menu of Phuflix.

Here the Customer are given 4 options to choose. 

1. Order a movie, 
2. Leave a review (which if we have time I can go over)
3. Shopping cart options
4. and view order history

Lets go thrugh an order.

How phuflix is set up is very similar to redboxes. There are boxes that are stationed in specific address. So how 
an order is done is by selecting a location and then selecting a movie. 

Let say Tacoman is from Kentucky so he is going to the phubox in that location. When he selects the box the inventory
of that box will show up. He will then select a movie.

One movie is not enough how bout he also likes ...

Ok thats enough movies now lets go to see his purchase

(Go to shopping cart options)

Here we can view all of our items in our shoping cart we can

1. Checkout
2. View items
3. delete an Item

(go to check out)

woah Tacoman didnt know that his total will be that much, Inflation am I right? so he is going to back out and remove
an item. Lets remove ....

we can view the cart to see if it is no longer there for validation

and now lets go check out

type y to check out and congrats we bought our moviews now we just got to pick it up

(sign out of taco man)

Now how does Phuflix handel its back end. Phuflix uses mysql as a back end and uses postgressql to communicate with
that database. But because we dont want to type sql query every time we want to add things such as inventory
we can do that stuff in the software through the admin page

Here is the admin page. Here we have 4 options
1. add a new phubox (which if we have time I can go over)
2. add inventoy
3. add a new movie to the database
4. view user info.

So I just noticed that phuflix has a new user and I wanted to see if what he ordered to indicate what kind of products
to buy.

(go to view users and then go to new user)

woah this guy likes... thats good to know.

Now lets go over how to add inventory first we select a movie that is in the database to add inventory.
Then we select the phubox location we want to add. And finally we add tha ammount good.

No lets say you are an existing Customer and you hear that Phuflix just added the movie to his phubox
.... and you want to buy it
first you want to check out your oder hisory because you have a budget of how much money you can spend on movies
(populate this user with psedo data) 

(log in)

We can no go to view order history.

(view order history)

And lets sort for the most recent order. Oh so Tom has only spent ... this month (I hard coded in postgres
some psudo data to display this funtion) so he has more than enough to buy it. So he goes to the box and adds it to
his cart.

That should be all the MVP. Now that I have extra time I can go through some other features of phuflix.

Lets say ... wants to leave a review. He will go to the leave a review tab...

(Go to leave a review)

...


