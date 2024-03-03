# Mindtech-Apps homework

This application was created using Java 17 and Spring Boot 3.2.3

Steps to launch the application:
1. Use **docker-compose up -d** command to start the DB (you might need to delete or change the platform property, depending on the machine you use)
2. AFter it finishes, you should build the application by hitting **mvn clean install**
3. After the build is done, you can start the application with the **mvn spring-boot:run** command

There are 2 types of users already created when the spring boot app starts the first time:
One Customer user:
email: "test"
password: "test"

And one restaurant owner:
email: "testRestaurant"
password: "test"

When you log in / register a new user, you'll receive a JWT token in the response body. You'll need to add this to the header, if you want to access any other endpoint other than the login and register endpoint.
Header name: Authorization
Before the JWT token, you have to add 'Bearer '.
Example: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzA5NDgzMzg5LCJleHAiOjE3MDk0ODQ4Mjl9.6VoYBMFOgUOvEg5zA8lWuOXxpTqMWRA9214YefkSJNA
The token is alive for 1 hour, after that you have to log in again. This could be extended by using a refresh token, but sadly I didn't have time to implement that.

The swagger-UI is located on "http://localhost:8080/swagger-ui/index.html"
