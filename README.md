# Instagram API

## Frameworks and language
* Framework : Spring,SpringBoot
* Language : java (Version 11)
***

## Data Flow

>Controller
  * The controller package has two classes, first ***UserController*** and second ***PostController***
    * **_UserController_** :- User can do **CURD** operation in UserController
    * **_PostController_** :- When User is login then he can do **CURD** operation from the PostController
    
>Service
* The service package has two classes, first ***UserService*** and second ***PostService***
  * **_UserService_** :- The logic of all the card operations of the user controller is written here.
  * **_PostService_** :- The logic of all the card operations of the post controller is written here.
    
>dao

* There are two **interfaces** in the dao layer which extend the **JPARepository** and inside it, we fetch the data from the database by sending all the sql commands.

## Data Structure used in project

* Here we are using hasMap and List data structure.

## Project Summary

* We are waiting for the user of this project.  We are also creating posts along with the user.
* Lazy-loading is also applied in this.


## Postman 
 * If you want to use this project first clone this project and pate the given postman link in postman . In this way you can use the project abd run it to see how the output will come.
>https://api.postman.com/collections/24994942-741c8260-e2f8-44d3-b869-9ac500cd08a7?access_key=PMAT-01GTS6MQQW232YGXV7RR9RXHT0






