
# Spring Boot Bookstore Project

This Spring Boot project manages book information with CRUD operations. It provides RESTful API endpoints for managing books and integrates Swagger for API documentation.

## Software Requirements

- Java 17
- Gradle 7.6.3 (Binary)
- IntelliJ Community Edition

## Instructions

### Setup Project

1. Create a new Spring Boot project using IntelliJ Community Edition.
2. Configure the project to use Java 17.
3. Use Gradle 7.6.3 (Binary) for project build and dependency management.

### Book Entity

1. Create a Book entity with attributes: `id`, `title`, `author`, `isbn`, and `price`.

### Repository

1. Create a `BookRepository` interface that extends `JpaRepository` to handle database operations for the `Book` entity.

### Controller

1. Create a `BookController` class with endpoints for:
   - Listing all books
   - Retrieving a book by ID
   - Searching by book title (ignore case)
   - Adding a new book
   - Updating an existing book
   - Deleting a book by ID

### Service

1. Create a `BookService` class to encapsulate the business logic. The service should interact with the `BookRepository`.

### Database Configuration

1. Configure an in-memory H2 database for storing book information.

### Swagger Documentation

1. Integrate Swagger for API documentation. Use Swagger annotations in the `BookController` to document the API endpoints.

### Running the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/gaurav-chavan-tss/bookstoreapp.git

