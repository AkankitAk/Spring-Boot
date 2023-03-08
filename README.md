# Chat Application

## Framework and Language
* Framework :Spring,SpringBoot And Hibernate
* Language : Java(11)

## Data Flow
> Model
* The Model package has three classes:-
  * **ChatHistory**: It contains all the data related to chat history.
  * **Status**: It contains all the data related to the status.
  * **User**: It contains all the data related to the status.
> Controller
* The controller package has three classes:-
    * **UserController**: User can do CRUD operation in it.
    * **StatusControl**: In this the status will be active or inactive.
    * **ChatHistoryController**: In this we can send message to anyone . You can see your message or you can see all the messages.
>  Service 
* Whatever request is coming from the controller package classes , the logic of everything is written here.
> Dao
* This package also has three classes, But the logic of the SQL is written in all the classes.
> Util
* The util contains has only one class:-
  * **CommonUtils**: There is validation of everything in this.

## Data Structure used in this project
* Here we are using hasMap and List data structure.

## Project Summary
* In this project the user con contact any other user who is in the database.
* Use can delete his account and can see your messages, Who has been sent to whom?

## Postman
* If you want to use this project first clone this project and pate the given postman link in postman . In this way you can use the project and run it to see how the output will come.
    >https://api.postman.com/collections/24994942-c85f8b55-6340-47db-b7ba-fa8f72a85a35?access_key=PMAT-01GV0VM5R8CZXJJENRA296MBS9   

