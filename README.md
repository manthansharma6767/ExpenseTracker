# Student Expense Tracker

A simple student-focused expense tracking application built with **Spring Boot, JPA/Hibernate, MySQL, HTML, CSS, and JavaScript**.

The project is designed to practice real-world backend development concepts without adding unnecessary authentication/security complexity.

## Features

- Add and manage students
- Add expenses for students
- View expenses
- Associate each expense with a student
- Categorize expenses using an enum
- Expense categories:
  - Food
  - Travel
  - Entertainment
  - Books
  - Shopping
  - Health
  - Rent
  - Other
- REST APIs with Spring Boot
- JPA/Hibernate database mapping
- MySQL persistence
- Plain HTML/CSS/JavaScript frontend
- Frontend-to-backend integration using `fetch()`

## Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok

### Frontend
- HTML5
- CSS3
- Vanilla JavaScript
- Fetch API

## Architecture

```text
                Frontend
        HTML + CSS + JavaScript
                    |
                 fetch()
                    |
                    v
          Spring Boot REST API
                    |
             Controller Layer
                    |
              Service Layer
                    |
            Repository Layer
                    |
              JPA / Hibernate
                    |
                    v
                  MySQL
```

## Data Model

The application uses two main entities:

```text
Student
  |
  | 1
  |
  |--------< Expense
             *
```

### Student

Typical fields:

- `studentId`
- `name`
- `email`

### Expense

Typical fields:

- `expenseId`
- `amount`
- `category`
- `description`
- `date`
- `student`

`category` is represented as an enum because the category is a fixed set of values and does not need its own entity/table.

## Project Structure

```text
ExpenseTracker/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ...
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── frontend/
│   ├── index.html
│   ├── index.css
│   └── index.js
│
├── pom.xml
├── .gitignore
└── README.md
```

> Adjust the frontend folder name in this section if your actual files are kept at the project root.

## Running the Backend

### 1. Clone the repository

```bash
git clone https://github.com/manthansharma6767/ExpenseTracker.git
cd ExpenseTracker
```

### 2. Configure MySQL

Create a MySQL database and update your local configuration.

Do **not** commit database passwords or other secrets to GitHub.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Run the Spring Boot application

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Or, if Maven is installed:

```bash
mvn spring-boot:run
```

The backend runs by default at:

```text
http://localhost:8080
```

## Running the Frontend

Open the frontend using a local development server.

For example, with VS Code Live Server, open:

```text
index.html
```

The frontend JavaScript should point to the local backend:

```javascript
const BASE_URL = "http://localhost:8080";
```

## API Examples

### Students

```http
GET /students
POST /students
GET /students/{id}
PUT /students/{id}
DELETE /students/{id}
```

### Expenses

```http
GET /expenses
POST /expenses
GET /expenses/{id}
PUT /expenses/{id}
DELETE /expenses/{id}
```

> Exact endpoints depend on the controller mappings in the current source code.

## Example Expense Request

```json
{
  "amount": 500,
  "category": "FOOD",
  "description": "Lunch",
  "date": "2026-08-13",
  "studentId": 1
}
```

## Why Category Is an Enum

Expense categories are modeled as an enum instead of separate entities because they are a small, fixed set of values and do not have an independent lifecycle.

This provides:

- Type safety
- Cleaner code
- Consistent category values
- Simpler database design
- No unnecessary category table or joins

## Development Notes

This project intentionally does not include JWT authentication or Spring Security. The goal is to focus on:

- REST API development
- Spring Boot fundamentals
- JPA/Hibernate relationships
- DTO and layered architecture
- Database operations
- Frontend/backend integration

Security can be introduced later as an extension rather than making it part of the core project.

## Future Improvements

Possible future enhancements:

- Monthly expense analytics
- Category-wise spending summary
- Budget limits
- Expense search and filtering
- Pagination and sorting
- Charts and visual reports
- User authentication
- Cloud deployment

## Author

**Manthan Sharma**

GitHub: https://github.com/manthansharma6767

## License

This project is licensed under the MIT License. See `LICENSE` for details.
