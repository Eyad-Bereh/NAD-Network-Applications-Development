# E-Commerce Website

A simple E-commerce website developed using Spring MVC framework and Hibernate ORM.

**Important:** to successfully run the application you must have Java 11 or above installed, notice that JRE is enough if all you want to do is to run the application, but if you want to make modifications then you must have JDK installed. This application was developed using **OpenJDK v11.0.7**.
Also, you must have MySQL server installed, or you can use **phpMyAdmin** to import the database. Personally, i've used **XAMPP** MySQL service for this purpose.

## Installation

1. Start **XAMPP** or **WAMP** (it's possible to use **mysql** from command line, but i haven't tested this method, if you know how to do this please open a pull request and edit this document, thank you :) ).
2. Open **phpMyAdmin**, usually it can be found at [http://localhost/phpmyadmin](http://localhost/phpmyadmin)
3. From the upper bar, click **Import**
4. Choose the database file **e_commerce_website.sql** from the folder of this project
5. Click **Go**

**And that's pretty much it !!!**

## Running the application

- Navigate to project folder and run the command 
```
java -jar target/finalproject-0.0.1-SNAPSHOT.jar
```
- Open [http://localhost:8080/](http://localhost:8080/)
- Check the report for some functionalities description