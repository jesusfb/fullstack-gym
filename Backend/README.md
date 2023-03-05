# API for Gym using Spring Boot
 
This is the API for the Gym project. It is built using Spring Boot and Java.
It is used by the website and the android app to communicate with the database.

# Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Java](https://www.java.com/en/)
* [MariaDB](https://mariadb.org/)
* [Maven](https://maven.apache.org/)

<!-- TODO: libraries used -->
## Libraries Used
- Mockito - For Unit Testing
- JUnit - For Unit Testing
- Springframework - To create the URL mappings

<!-- TODO: add features of the api -->
## Features 
- Simple and comprehensive structure 
 - URL mappings
- Individual Unit Testing units
- Inclusive and easily updated information

<!-- TODO: how to build and run the api -->
# How to run the app
- Clone the repository
- Open the project in Intellij 
- Navigate to the /Backend/src/main/java/resources/application.properties
- Change the values according to the values used in the Database0
- Run the API by running /Backend/src/main/java/com.backend/ProjectApplication


# API Endpoints

<table>
    <tr>
        <td>API Documentation</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>Method</td>
        <td>URL</td>
        <td>Description</td>
    </tr>
    <tr>
        <td>POST</td>
        <td>http://localhost:8080/api/instructors/add</td>
        <td>Creates a new Instructor</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/instructors/all</td>
        <td>Returns a list of all the Instructors</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/instructors?instructor_id=1</td>
        <td>Returns the Instructor with ID = 1 </td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>http://localhost:8080/api/instructors/update?instructor_id=1</td>
        <td>Change the details for the Instructor with ID = 1.</td>
    </tr>
    <tr>
        <td>DELETE</td>
        <td>http://localhost:8080/api/instructors/delete?instructor_id=1</td>
        <td>Deletes the Instructor with ID = 1</td>
    </tr>
    <tr>
        <td>POST</td>
        <td>http://localhost:8080/api/plans/add</td>
        <td>Creates a new Plan</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/plans/all</td>
        <td>Returns a list of all the Plans</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/plans?plan_id=1</td>
        <td>Returns Plan with ID = 1</td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>http://localhost:8080/api/plans/update/?plan_id=1</td>
        <td>Changes the details for the Plan with ID = 1</td>
    </tr>
    <tr>
        <td>DELETE</td>
        <td>http://localhost:8080/api/plans/delete?plan_id=1</td>
        <td>Deletes the Plan with ID = 1</td>
    </tr>
    <tr>
        <td>POST </td>
        <td>http://localhost:8080/api/instructors/courses/add?instructor_id=1</td>
        <td>Create a new Course (Json Body) that is automatically assigned to the Instructor with ID = 1.</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/courses/all</td>
        <td>Returns a list with all the Courses</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/courses?course_id=1</td>
        <td>Returns the Course with ID = 1</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/plans/courses/all?plan_id=1</td>
        <td>Returns a list of Courses belonging to the Plan with ID = 1</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/instructors/courses/all?instructor_id=1</td>
        <td>Returns a list of Courses belonging to the Instructor with ID = 1</td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>http://localhost:8080/api/courses/update?course_id=1</td>
        <td>Changes the data for the Course with ID = 1</td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>http://localhost:8080/api/plans/courses/add?plan_id=1&amp;course_id=1</td>
        <td>Links the Course with ID = 1 to the Plan with ID = 1</td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>http://localhost:8080/api/courses/update/instructor?course_id=1&amp;instructor_id=1</td>
        <td>Changes / assigns to the course with ID = 1 the instructor with ID = 1</td>
    </tr>
    <tr>
        <td>DELETE </td>
        <td>http://localhost:8080/api/courses/delete?course_id=1</td>
        <td>Deletes the Course with ID  = 1</td>
    </tr>
    <tr>
        <td>DELETE </td>
        <td>http://localhost:8080/api/plans/delete/courses</td>
        <td></td>
    </tr>
    <tr>
        <td>POST </td>
        <td>http://localhost:8080/api/courses/schedule/add?course_id=1</td>
        <td>Creates a new Schedule which is automatically enrolled in the Course with ID = 1</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/courses/schedule/all</td>
        <td>Returns a list of the program (general and vague) for all ta courses</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/courses/schedule/get?course_id=1</td>
        <td>Returns all scheduled times for the course with ID = 1 (on a weekly basis)</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/courses/schedule/all/iso8601?weeks=2</td>
        <td>Returns the information needed in Calendar to react (the week determines how far ahead the results will go)</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/courses/schedule/all/epoch</td>
        <td>Returns all scheduled lessons with timestamps</td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>http://localhost:8080/api/courses/update/schedule?schedule_id=2</td>
        <td>Changes the data for the Schedule object with ID = 1</td>
    </tr>
    <tr>
        <td>DELETE</td>
        <td>http://localhost:8080/api/schedule/delete?schedule_id=1</td>
        <td>Deletes the Schedule object with ID = 1</td>
    </tr>
    <tr>
        <td>POST</td>
        <td>http://localhost:8080/api/events/register?user_id=1&amp;course_id=1&amp;start_tmp=434343441&amp;end_tmp=243432434</td>
        <td>It registers the user User with ID = 1 in the course with ID = 2. You also need to pass the start and end timestamp for this event</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/events/all</td>
        <td>Returns all registered Events for all users</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/events?user_id=1</td>
        <td>Returns all registered events for the user with id = 1</td>
    </tr>
    <tr>
        <td>DELETE</td>
        <td>http://localhost:8080/api/events/unregister?course_id=1&amp;user_id=1&amp;start_tmp=1343243243</td>
        <td>Deletes the Event associated with user 1 and course 1 and also has the specific timestamp</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/users/all</td>
        <td>Returns all registered Events for all users</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/users?user_id=1</td>
        <td>Returns User with ID = 1</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/plans/users/all?plan_id=1</td>
        <td>Returns a list of all Users registered in the Plan with ID = 1</td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>http://localhost:8080/api/users/update?user_id=1</td>
        <td>Changes the data for the User with ID = 1</td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>http://localhost:8080/api/users/update/plan?user_id=1&amp;plan_id=1</td>
        <td>Changes the Plan (based on ID) for the User with ID = 1</td>
    </tr>
    <tr>
        <td>DELETE</td>
        <td>http://localhost:8080/api/users/delete?user_id=1</td>
        <td>Deletes the User with ID = 1</td>
    </tr>
    <tr>
        <td>POST</td>
        <td>http://localhost:8080/api/images/instructors/upload?instructor_id=1</td>
        <td>It uploads to instructor with id = 1 the photo embedded in the body with key name = image , type = File</td>
    </tr>
    <tr>
        <td>DELETE</td>
        <td>http://localhost:8080/api/images/instructors/delete?instructor_id=1</td>
        <td>Deletes the image related to the instructor with id = 1</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http:/localhost:8080/api/images/get?name=personal_training.jpg</td>
        <td>Returns the image with the specified name</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:8080/api/auth/authenticate?email=&lt;email&gt;@gmail.gr&amp;password=&lt;password&gt;</td>
        <td>Authenticate the User with his credentials (email and password) and return a Bearer Token</td>
    </tr>
    <tr>
        <td>POST</td>
        <td>http:/localhost:8080/api/auth/register</td>
        <td>Registers the user in the database</td>
    </tr>
</table>
