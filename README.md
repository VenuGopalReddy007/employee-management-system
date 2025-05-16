# Employee Management System

The Employee Management System is a secure, role-based web application built using Spring Boot. It provides functionalities for managing employee data, with features like secure authentication, role-based access control, email notifications for login alerts, and API documentation via Swagger UI.

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Project Overview
The Employee Management System is designed to manage employee-related operations securely. It uses Spring Security for authentication and authorization, ensuring that only users with appropriate roles (`ADMIN` or `MANAGER`) can access specific functionalities. The system includes email notifications to alert users of successful logins, integrates an H2 in-memory database for development, and provides API documentation via Swagger UI.

## Features
- **Secure Authentication**: Form-based login with custom success handling.
- **Role-Based Access Control**: 
  - `ADMIN` role: Access to `/api/users/**`.
  - `ADMIN` and `MANAGER` roles: Access to `/api/employees/sendMail/**`.
- **Email Notifications**: Sends login alerts via email using Spring Mail.
- **H2 Database**: In-memory database for development, initialized with `data.sql`.
- **Swagger UI**: API documentation accessible at `/swagger-ui/**`.
- **Session Management**: Configured to create sessions only when required.
- **RESTful APIs**: Managed by `EmployeeController` and `UserController`.
- **Data Persistence**: Handled by `EmployeeRepository` and `UserRepository`.

## Technologies Used
- **Java**: Programming language.
- **Spring Boot**: Framework for building the application.
- **Spring Security**: For authentication and authorization.
- **Spring Data JPA**: For database operations.
- **Spring Mail**: For sending email notifications.
- **H2 Database**: In-memory database for development.
- **Swagger**: For API documentation.
- **Maven**: Dependency management.

## Project Structure

Employee-Management-System/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── Employee_Management_System/
│   │   │               ├── controller/
│   │   │               │   ├── EmployeeController.java
│   │   │               │   └── UserController.java
│   │   │               ├── model/
│   │   │               │   ├── Employee.java
│   │   │               │   ├── UserPrincipal.java
│   │   │               │   └── Users.java
│   │   │               ├── repository/
│   │   │               │   ├── EmployeeRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               ├── security/
│   │   │               │   ├── CustomAuthenticationSuccessHandler.java
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── service/
│   │   │               │   ├── EmailService.java
│   │   │               │   ├── EmployeeService.java
│   │   │               │   ├── MyUserDetailsService.java
│   │   │               │   └── UserService.java
│   │   │               └── EmployeeManagementSystemApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       │   └── (static assets like CSS, JS, images)
│   │       ├── templates/
│   │       │   └── (Thymeleaf or other templates for web views)
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/
│       └── (test classes and resources)
|_pom.xml
│ 
└── README.md





- **`controller/`**: REST controllers for handling API requests (`EmployeeController`, `UserController`).
- **`model/`**: Domain models (`Employee`, `Users`, `UserPrincipal`).
- **`repository/`**: JPA repositories for database operations (`EmployeeRepository`, `UserRepository`).
- **`security/`**: Security configurations and custom handlers (`SecurityConfig`, `CustomAuthenticationSuccessHandler`).
- **`service/`**: Business logic and email services (`EmployeeService`, `UserService`, `MyUserDetailsService`, `EmailService`).
- **`resources/`**: Configuration files (`application.properties`), initial data (`data.sql`), and static/templates for web assets.

## Prerequisites
- **Java 11 or higher**: Ensure Java is installed.
- **Maven**: For building and managing dependencies.
- **SMTP Server Access**: For email notifications (e.g., Gmail SMTP).
- **IDE**: IntelliJ IDEA, Eclipse, or any Java IDE.

## Setup Instructions
1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd Employee-Management-System

##
1.  Configure Application Properties:
	•  Open src/main/resources/application.properties.
	•  Update the email credentials for spring.mail.username and spring.mail.password with your SMTP server details (e.g., Gmail).
	•  Modify the H2 database settings if needed (default username: sa, password: empty).

The application will start on http://localhost:8080.


2.  Access the H2 Console (optional):
	•  URL: http://localhost:8080/h2-console
	•  JDBC URL: jdbc:h2:mem:empdb
	•  Username: sa
	•  Password: (leave empty)
3.  Access Swagger UI (optional):
	•  URL: http://localhost:8080/swagger-ui/
Configuration
The application.properties file contains key configurations:
•  Server Port: server.port=8080
•  H2 Database:
	•  spring.datasource.url=jdbc:h2:mem:empdb
	•  spring.datasource.username=sa
	•  spring.datasource.password=
	•  spring.h2.console.enabled=true
	•  spring.h2.console.path=/h2-console
•  Email Settings:
	•  spring.mail.host=smtp.gmail.com
	•  spring.mail.port=587
	•  spring.mail.username=<your-email>@example.com
	•  spring.mail.password=<your-password>
	•  Enable spring.mail.properties.mail.smtp.auth and spring.mail.properties.mail.smtp.starttls.enable for secure email communication.
•  JPA Settings:
	•  spring.jpa.hibernate.ddl-auto=update
	•  spring.jpa.defer-datasource-initialization=true
	•  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect


Usage
1.  Login:
	•  Navigate to http://localhost:8080/login.
	•  Use credentials configured in your UserDetailsService implementation (loaded via MyUserDetailsService).
	•  On successful login, you’ll receive an email notification and a custom response message.
2.  Access Protected Endpoints:
	•  /api/users/**: Requires ADMIN role.
	•  /api/employees/sendMail/**: Requires ADMIN and MANAGER roles.
	•  /api/employees/get: Accessible after login to retrieve employee details.
3.  Logout:
	•  Navigate to http://localhost:8080/logout.



API Endpoints
•  GET /api/employees/get: Retrieve employee details (requires authentication).
•  POST /api/employees/sendMail/: Send emails (requires ADMIN and MANAGER roles).
•  GET /api/users/: Manage users (requires ADMIN role).
•  GET /swagger-ui/: Access API documentation.
•  POST /login: Authenticate users.
•  GET /logout: Log out users.



Contributing
Contributions are welcome! Please follow these steps:
1.  Fork the repository.
2.  Create a new branch (git checkout -b feature-branch).
3.  Make your changes and commit (git commit -m "Add feature").
4.  Push to the branch (git push origin feature-branch).
5.  Create a pull request.


License
This project is licensed under the MIT License. See the LICENSE file for details.

---

### Additional Notes
- The project structure indicates a typical Spring Boot application with a clear separation of concerns: controllers for handling HTTP requests, models for domain entities, repositories for database access, services for business logic, and a dedicated security package for authentication and authorization.
- The `data.sql` file in `resources` suggests that the H2 database is pre-populated with initial data (e.g., users or employees) on startup.
- The `static` and `templates` directories suggest the application might include a web interface (possibly using Thymeleaf for templates), though the focus seems to be on REST APIs given the controller and security setup.
- If you’d like to add more details (e.g., specific API examples, deployment instructions, or additional features), let me know, and I can expand the README further!
 
