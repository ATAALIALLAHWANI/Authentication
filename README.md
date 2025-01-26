# Spring Boot Authentication and Authorization with JWT

This project demonstrates how to implement **authentication** and **authorization** using Spring Boot and **JSON Web Tokens (JWT)**. It includes the use of custom filters to manage security efficiently.

---

## Key Features:
- **Authentication**: Users can log in with their credentials and receive a JWT for future requests.
- **Authorization**: Secure endpoints with role-based access control.
- **Stateless Security**: Leveraging JWT ensures the application is completely stateless, eliminating the need for server-side session storage.

---

## Filter Chain Explained
https://www.google.com/url?sa=i&url=https%3A%2F%2Fdocs.spring.io%2Fspring-security%2Freference%2Fservlet%2Farchitecture.html&psig=AOvVaw3xd0SOmaqYRFuESk1T2PLa&ust=1738006272385000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCLi4qoaQlIsDFQAAAAAdAAAAABAJ

### `addFilterBefore()`  
The `addFilterBefore()` method is used to insert a custom filter (`ExceptionHandlerFilter`) into the filter chain **before** another specified filter (`AuthenticationFilter`). This ensures that exceptions are handled globally before authentication processing begins.  

- **Example Code**:
    ```java
    http.addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
        .addFilter(authenticationFilter)
        .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class);
    ```

---

## How to Use

1. Clone the repository:
    ```bash
    git clone (https://github.com/ATAALIALLAHWANI/Authentication.git)
    ```

2. Navigate to the project directory:
    ```bash
    cd Authentication
    ```

3. Run the application:
    ```bash
    ./mvnw spring-boot:run
    ```

4. Use a tool like Postman to test authentication:
   - **POST /login**: Send username and password to receive a JWT.
   - **GET /secure-endpoint**: Include the JWT in the `Authorization` header to access protected resources.

---

## Technologies Used
- **Spring Boot**: Backend framework
- **Spring Security**: Security configuration and filters
- **JWT**: Token-based authentication
- **Maven**: Build tool

---


