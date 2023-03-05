# Doctor API

## Frameworks and language
* Framework : Spring,SpringBoot
* Language : java (Version 11)
***

## Data Flow

>Controller
  * The controller package has two classes, first ***DoctorController*** and second ***PatientController***
    * **_DoctorController_** :- User can do **CURD** operation in DoctorController
    * **_PatientController_** :- When User is login then he can do **CURD** operation from the PatientController
    
>Service
* The service package has two classes, first ***DoctorService*** and second ***PatientService***
  * **_DoctorService_** :- The logic of all the card operations of the  Doctor controller is written here.
  * **_PatientService_** :- The logic of all the card operations of the Patient controller is written here.
    
>dao

* There are two **interfaces** in the dao layer which extend the **JPARepository** and inside it, we fetch the data from the database by sending all the sql commands.

# Data Structure used in project

* Here we are using hasMap and List data structure.

## Project Summary



## Postman 
 * If you want to use this project first clone this project and pate the given postman link in postman . In this way you can use the project abd run it to see how the output will come.
>https://api.postman.com/collections/24994942-6894e402-3952-460d-933e-1beca57441c1?access_key=PMAT-01GTS7F4XZ73MQXP61V33SK5SP






