# JavaInternHomework2019
Task for Cognifide Intern in 2019

Application downloads books information from URL "https://www.googleapis.com/books/v1/volumes?q=java&maxResults=40".

It simplifies it and puts to a new endpoints:

http://localhost:8080/books/{isbn}   <--- shows information about book with given ISBN e.g. http://localhost:8080/books/9788324677610
(JSONObject)

http://localhost:8080/books/?category={category}   <--- shows all books with given category e.g. http://localhost:8080/books/?category=Computers
(List of JSONObjects)

http://localhost:8080/books/authors-rating    <--- shows list of authors and rating based on their books average rating descending.
(List of JSONObjects)



For my Spring based application I used Spring Boot and Maven 4.0.0 as a build tool.

For testing I used JUnit. I found it hard to use Mocks, because of Design Pattern, that I described below. 
I also didn't use RestAssured to test endpoints that I provide, because I couldn't solve the issue, that I need to run application first, and during 'clean install' they fail. It only tests an endpoint from which I download book info.

I used Singleton as a design pattern on master branch to simulate Managers classes. I did it, because I needed only one object, that is available everywhere. Later I changed it into Spring Beans to better use Spring Boot capabilities and make it thread safe. Making that I used Dependency Injection design pattern. 

For code diagnosis I used SonarLint. Only issue I didn't fix is to change printing the exceptions to log it. 
I didn't provide JavaDoc, because I think that code should be self-explanatory, so I tried to make it easy to read by following the rules of clean code.

#  Run instructions

To build the project use following command:

•	mvn clean install


After building the dashboard run following command to start it:

•	java -jar target/ cognifide-task-0.0.1-SNAPSHOT.jar


Application runs of port 8080


