
# Library Management System Application

An Library Management System Application using Spring Boot is designed to manage and store student and library information, books data, author data and issued book data, while providing secure and efficient API endpoints.


## Prerequisites

- JDK 11 or Higher
- Maven
- MySQL Database
- IntelliJ Idea 
##  Usage

A Spring Boot library management application facilitates the issue books. It manages Student, Book, Author, Library Card, and Transaction entities. Users can create and search for students, books, and authors, issue books to library cards, and track transactions. This application simplifies library management by offering a user-friendly interface for handling library resources and transactions efficiently.

### Issue Book
- MySQL create a new table for Transaction to store issue book records in the database.
- It takes LibraryCardID , BookID , and if both are valid it simply issue the book to a student.
- Return a message after issue a book like transactionID , book name , and transaction status.

### Operations to fetch data
- Implemented many API's to fetch the students record like get students by name, email etc.
- Same done with every entity so that user can get many functionalities.

### Features Implemented
- Implemented DTO's (Data Transfer Object).
- Implemented Cardinality Relationship.
- Implemented MVC Architechture.
- Implemented Exception Handling.
- Implemented 15+ API's.
- Used Swagger to showcase the List of API's.
- Used Java Mail Sender to recieve email for every order.



## Contributing

Contributions to the E-Commerce Backend Application are welcome. If you find any issues or have suggestions for improvement, please create a new issue or submit a pull request.

## Screenshot
![Swagger1](https://github.com/Amanhussain786/Library-Management-System/assets/78783889/0f6c7037-d116-4f39-a413-256abc111dc8)
![Swagger2](https://github.com/Amanhussain786/Library-Management-System/assets/78783889/1a2cc29d-c675-4239-a194-c7c19e9ce039)
![Schema](https://github.com/Amanhussain786/Library-Management-System/assets/78783889/683ad04b-a100-4bff-9f01-8d19a6f49a51)

