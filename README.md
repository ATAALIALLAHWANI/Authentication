# Spring Boot Authentication and Authorization with JWT

This project demonstrates how to implement **authentication** and **authorization** using Spring Boot and **JSON Web Tokens (JWT)**. It includes the use of custom filters to manage security efficiently.

---

## Key Features:
- **Authentication**: Users can log in with their credentials and receive a JWT for future requests.
- **Authorization**: Secure endpoints with role-based access control.
- **Stateless Security**: Leveraging JWT ensures the application is completely stateless, eliminating the need for server-side session storage.

---

## Filter Chain Explained

The image below illustrates how Spring Security filters work during the request lifecycle in this project:  
![Filter Chain Workflow](image-link-here)

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
    git clone https://github.com/your-username/your-repository.git
    ```

2. Navigate to the project directory:
    ```bash
    cd your-repository
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


