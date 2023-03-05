# TODO API

## Frameworks and language
* Framework : Spring,SpringBoot
* Language : java (Version 11)
***

## Data Flow

>Controller
  * The controller package has One classes, ***TodoController***.
    * **_TodoController_** :- User can do **CURD** operation in TodoController
    
>Service
* The service package has one classes and one Interface, first ***TodoService*** and second ***ITodoService***
  * **_TodoService_** :- The logic of all the card operations of the user controller is written here.
  * **_ITodoService_** :- some logic witten here.
    
>dao

* There are two **interfaces** in the dao layer which extend the **JPARepository** and inside it, we fetch the data from the database by sending all the sql commands.

## Data Structure used in project

* Here we are using hasMap and List data structure.

## Project Summary








